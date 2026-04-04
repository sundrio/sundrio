/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.validation.internal.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import io.sundr.codegen.apt.processor.AbstractCodeGeneratingProcessor;
import io.sundr.model.Assign;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Construct;
import io.sundr.model.Declare;
import io.sundr.model.Expression;
import io.sundr.model.Field;
import io.sundr.model.FieldBuilder;
import io.sundr.model.Foreach;
import io.sundr.model.Kind;
import io.sundr.model.Lambda;
import io.sundr.model.LocalVariable;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.PrimitiveRefBuilder;
import io.sundr.model.Property;
import io.sundr.model.Return;
import io.sundr.model.Ternary;
import io.sundr.model.This;
import io.sundr.model.Try;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.ValueRef;
import io.sundr.validation.annotations.Validation;

@SupportedAnnotationTypes("io.sundr.validation.annotations.Validation")
public class ValidationProcessor extends AbstractCodeGeneratingProcessor {

  private static final String VALIDATOR_SUFFIX = "Validator";
  private static final String DEFAULT_VALIDATION_PACKAGE = "io.sundr.validation";
  private static final String BUILDABLE_ANNOTATION = "io.sundr.builder.annotations.Buildable";
  private static final String EXTERNAL_BUILDABLES_ANNOTATION = "io.sundr.builder.annotations.ExternalBuildables";

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      return false;
    }

    Map<String, List<ValidationMethod>> validatorsByType = new HashMap<>();

    for (TypeElement annotation : annotations) {
      for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
        if (element.getKind() != ElementKind.METHOD) {
          processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
              "@Validation can only be applied to methods", element);
          continue;
        }

        ExecutableElement method = (ExecutableElement) element;
        ValidationMethod validationMethod = extractValidationMethod(method);
        if (validationMethod == null) {
          continue;
        }

        validatorsByType.computeIfAbsent(validationMethod.targetTypeFqn, k -> new ArrayList<>())
            .add(validationMethod);
      }
    }

    for (Map.Entry<String, List<ValidationMethod>> entry : validatorsByType.entrySet()) {
      String targetTypeFqn = entry.getKey();
      List<ValidationMethod> validationMethods = entry.getValue();
      String validationPackage = resolveValidationPackage(targetTypeFqn);

      TypeDef validatorClass = generateValidator(targetTypeFqn, validationMethods, validationPackage);
      generate(validatorClass);
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
          "Generated " + validatorClass.getPackageName() + "." + validatorClass.getName());

      TypeDef validatorsBuilder = generateValidatorsBuilder(targetTypeFqn, validationMethods, validationPackage);
      generate(validatorsBuilder);
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
          "Generated " + validatorsBuilder.getPackageName() + "." + validatorsBuilder.getName());
    }

    return false;
  }

  private ValidationMethod extractValidationMethod(ExecutableElement method) {
    if (method.getParameters().size() != 1) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "@Validation method must have exactly one parameter", method);
      return null;
    }

    boolean isStatic = method.getModifiers().contains(Modifier.STATIC);
    TypeElement enclosingType = (TypeElement) method.getEnclosingElement();

    if (!isStatic) {
      boolean hasNoArgConstructor = enclosingType.getEnclosedElements().stream()
          .filter(e -> e.getKind() == ElementKind.CONSTRUCTOR)
          .map(e -> (ExecutableElement) e)
          .anyMatch(c -> c.getParameters().isEmpty() && c.getModifiers().contains(Modifier.PUBLIC));

      if (!hasNoArgConstructor) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
            "@Validation on instance method requires enclosing class to have a public no-arg constructor", method);
        return null;
      }
    }

    Validation annotation = method.getAnnotation(Validation.class);
    TypeMirror targetType;

    try {
      Class<?> value = annotation.value();
      if (value != void.class) {
        targetType = processingEnv.getElementUtils()
            .getTypeElement(value.getCanonicalName()).asType();
      } else {
        targetType = method.getParameters().get(0).asType();
      }
    } catch (javax.lang.model.type.MirroredTypeException e) {
      TypeMirror mirror = e.getTypeMirror();
      if (mirror.getKind() == TypeKind.VOID) {
        targetType = method.getParameters().get(0).asType();
      } else {
        targetType = mirror;
      }
    }

    TypeMirror returnType = method.getReturnType();
    boolean returnsErrors = isListOfValidationError(returnType);
    boolean returnsItem = processingEnv.getTypeUtils().isSameType(returnType, targetType);
    boolean isVoid = returnType.getKind() == TypeKind.VOID;

    if (!returnsErrors && !returnsItem && !isVoid) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "@Validation method must return List<ValidationError>, the validated type, or void", method);
      return null;
    }

    String enclosingClass = enclosingType.getQualifiedName().toString();
    String methodName = method.getSimpleName().toString();
    String targetTypeFqn = getTypeFqn(targetType);

    return new ValidationMethod(targetTypeFqn, enclosingClass, methodName, isStatic, returnsErrors);
  }

  private boolean isListOfValidationError(TypeMirror type) {
    if (type.getKind() != TypeKind.DECLARED) {
      return false;
    }

    DeclaredType declaredType = (DeclaredType) type;
    TypeElement typeElement = (TypeElement) declaredType.asElement();
    String typeName = typeElement.getQualifiedName().toString();

    if (!typeName.equals("java.util.List")) {
      return false;
    }

    List<? extends TypeMirror> typeArgs = declaredType.getTypeArguments();
    if (typeArgs.size() != 1) {
      return false;
    }

    TypeMirror arg = typeArgs.get(0);
    if (arg.getKind() != TypeKind.DECLARED) {
      return false;
    }

    TypeElement argElement = (TypeElement) ((DeclaredType) arg).asElement();
    String argFqn = argElement.getQualifiedName().toString();
    return argFqn.equals("io.sundr.validation.ValidationError") || argFqn.endsWith(".validation.ValidationError");
  }

  private String getTypeFqn(TypeMirror type) {
    if (type.getKind() == TypeKind.DECLARED) {
      TypeElement element = (TypeElement) ((DeclaredType) type).asElement();
      return element.getQualifiedName().toString();
    }
    return type.toString();
  }

  private String getSimpleName(String fqn) {
    int lastDot = fqn.lastIndexOf('.');
    return lastDot > 0 ? fqn.substring(lastDot + 1) : fqn;
  }

  private String getPackageName(String fqn) {
    int lastDot = fqn.lastIndexOf('.');
    return lastDot > 0 ? fqn.substring(0, lastDot) : "";
  }

  private String resolveValidationPackage(String targetTypeFqn) {
    TypeElement targetType = processingEnv.getElementUtils().getTypeElement(targetTypeFqn);
    if (targetType == null) {
      return DEFAULT_VALIDATION_PACKAGE;
    }
    for (javax.lang.model.element.AnnotationMirror mirror : targetType.getAnnotationMirrors()) {
      String annotationName = ((TypeElement) mirror.getAnnotationType().asElement()).getQualifiedName().toString();
      if (BUILDABLE_ANNOTATION.equals(annotationName) || EXTERNAL_BUILDABLES_ANNOTATION.equals(annotationName)) {
        for (Map.Entry<? extends javax.lang.model.element.ExecutableElement, ? extends javax.lang.model.element.AnnotationValue> e : mirror
            .getElementValues().entrySet()) {
          if ("validationPackage".equals(e.getKey().getSimpleName().toString())) {
            return e.getValue().getValue().toString();
          }
        }
      }
    }
    return DEFAULT_VALIDATION_PACKAGE;
  }

  private TypeDef generateValidator(String targetTypeFqn, List<ValidationMethod> methods, String validationPackage) {
    String targetSimpleName = getSimpleName(targetTypeFqn);
    String targetPackage = getPackageName(targetTypeFqn);
    String validatorName = targetSimpleName + VALIDATOR_SUFFIX;

    ClassRef targetTypeRef = new ClassRefBuilder()
        .withFullyQualifiedName(targetTypeFqn)
        .build();

    ClassRef listOfErrorsRef = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.List")
        .withArguments(
            new ClassRefBuilder().withFullyQualifiedName(validationPackage + ".ValidationError").build())
        .build();

    ClassRef arrayListRef = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.ArrayList")
        .build();

    LocalVariable item = LocalVariable.newLocalVariable(targetTypeRef, "item");
    LocalVariable errors = LocalVariable.newLocalVariable(listOfErrorsRef, "errors");

    List<io.sundr.model.Statement> validateStatements = new ArrayList<>();
    validateStatements.add(new Declare(errors, new Construct(arrayListRef)));

    for (ValidationMethod vm : methods) {
      validateStatements.add(createValidationInvocation(vm, item, errors, validationPackage));
    }

    validateStatements.add(new Return(errors));

    Method validateMethod = new MethodBuilder()
        .withName("validate")
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(listOfErrorsRef)
        .addNewArgument().withName("item").withTypeRef(targetTypeRef).endArgument()
        .withNewBlock()
        .withStatements(validateStatements)
        .endBlock()
        .build();

    return new TypeDefBuilder()
        .withPackageName(targetPackage)
        .withName(validatorName)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers()
        .addToMethods(validateMethod)
        .build();
  }

  private io.sundr.model.Statement createValidationInvocation(ValidationMethod vm, LocalVariable item,
      LocalVariable errors, String validationPackage) {
    ClassRef enclosingRef = new ClassRefBuilder().withFullyQualifiedName(vm.enclosingClass).build();

    if (vm.returnsErrors) {
      Expression callExpr = vm.isStatic
          ? (Expression) enclosingRef.call(vm.methodName, item)
          : (Expression) new Construct(enclosingRef).call(vm.methodName, item);
      if (!DEFAULT_VALIDATION_PACKAGE.equals(validationPackage)) {
        return convertAndAddErrors(callExpr, errors, validationPackage);
      }
      return errors.call("addAll", callExpr);
    } else {
      ClassRef validationExceptionRef = new ClassRefBuilder()
          .withFullyQualifiedName(validationPackage + ".ValidationException").build();
      ClassRef runtimeExceptionRef = new ClassRefBuilder()
          .withFullyQualifiedName("java.lang.RuntimeException").build();
      ClassRef validationErrorRef = new ClassRefBuilder()
          .withFullyQualifiedName(validationPackage + ".ValidationError").build();

      LocalVariable e = LocalVariable.newLocalVariable(validationExceptionRef, "e");
      LocalVariable re = LocalVariable.newLocalVariable(runtimeExceptionRef, "re");

      io.sundr.model.Statement invocation = vm.isStatic
          ? enclosingRef.call(vm.methodName, item)
          : new Construct(enclosingRef).call(vm.methodName, item);

      return new Try(
          new Block(invocation),
          Arrays.asList(
              new Try.Catch(Property.newProperty(validationExceptionRef, "e"),
                  new Block(errors.call("addAll", e.call("getErrors")))),
              new Try.Catch(Property.newProperty(runtimeExceptionRef, "re"),
                  new Block(errors.call("add",
                      new Construct(validationErrorRef,
                          io.sundr.model.ValueRef.from(""), re.call("getMessage")))))),
          Optional.empty());
    }
  }

  private io.sundr.model.Statement convertAndAddErrors(Expression sourceCallExpr, LocalVariable errors,
      String validationPackage) {
    ClassRef baseErrorRef = new ClassRefBuilder()
        .withFullyQualifiedName(DEFAULT_VALIDATION_PACKAGE + ".ValidationError").build();
    ClassRef customErrorRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".ValidationError").build();
    LocalVariable baseError = LocalVariable.newLocalVariable(baseErrorRef, "__e");
    return new Foreach(baseError, sourceCallExpr,
        new Block(errors.call("add", new Construct(customErrorRef,
            baseError.call("getPath"), baseError.call("getMessage"), baseError.call("getInvalidValue")))));
  }

  private TypeDef generateValidatorsBuilder(String targetTypeFqn, List<ValidationMethod> methods, String validationPackage) {
    String targetSimpleName = getSimpleName(targetTypeFqn);
    String targetPackage = getPackageName(targetTypeFqn);
    String validatorsBuilderName = targetSimpleName + "ValidatorsBuilder";

    ClassRef targetTypeRef = new ClassRefBuilder()
        .withFullyQualifiedName(targetTypeFqn)
        .build();

    ClassRef builderInterfaceRef = new ClassRefBuilder()
        .withFullyQualifiedName("io.sundr.builder.Builder")
        .withArguments(targetTypeRef)
        .build();

    ClassRef sundrValidatorRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".Validator")
        .withArguments(targetTypeRef)
        .build();

    ClassRef listOfValidatorsRef = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.List")
        .withArguments(sundrValidatorRef)
        .build();

    ClassRef arrayListRef = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.ArrayList")
        .build();

    ClassRef validatingBuilderRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".ValidatingBuilder")
        .withArguments(targetTypeRef)
        .build();

    ClassRef validationResultRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".ValidationResult")
        .withArguments(targetTypeRef)
        .build();

    ClassRef selfRef = new ClassRefBuilder()
        .withFullyQualifiedName(targetPackage.isEmpty() ? validatorsBuilderName : targetPackage + "." + validatorsBuilderName)
        .build();

    PrimitiveRef booleanRef = new PrimitiveRefBuilder().withName("boolean").withDimensions(0).build();

    // Fields
    Field builderField = new FieldBuilder()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(builderInterfaceRef)
        .withName("builder")
        .build();

    Field validatorsField = new FieldBuilder()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(listOfValidatorsRef)
        .withName("validators")
        .addToAttributes(io.sundr.model.Attributeable.INIT, "new java.util.ArrayList<>()")
        .build();

    Field useJakartaValidationField = new FieldBuilder()
        .withNewModifiers().withPrivate().endModifiers()
        .withTypeRef(booleanRef)
        .withName("useJakartaValidation")
        .build();

    // Constructor
    LocalVariable builderArg = LocalVariable.newLocalVariable(builderInterfaceRef, "builder");
    Method constructor = new MethodBuilder()
        .withName(validatorsBuilderName)
        .withNewModifiers().withPublic().endModifiers()
        .addNewArgument().withName("builder").withTypeRef(builderInterfaceRef).endArgument()
        .withNewBlock()
        .addToStatements(new Assign(This.ref("builder"), builderArg))
        .endBlock()
        .build();

    // check* methods
    List<Method> checkMethods = new ArrayList<>();
    for (ValidationMethod vm : methods) {
      String methodBase = vm.methodName.startsWith("validate") && vm.methodName.length() > "validate".length()
          ? vm.methodName.substring("validate".length())
          : vm.methodName;
      String checkMethodName = "check" + Character.toUpperCase(methodBase.charAt(0)) + methodBase.substring(1);
      ClassRef enclosingRef = new ClassRefBuilder().withFullyQualifiedName(vm.enclosingClass).build();

      io.sundr.model.ExpressionOrStatement validatorExpr;
      if (vm.returnsErrors) {
        LocalVariable lambdaItem = LocalVariable.newLocalVariable("item");
        Expression lambdaCallExpr = vm.isStatic
            ? (Expression) enclosingRef.call(vm.methodName, lambdaItem)
            : (Expression) new Construct(enclosingRef).call(vm.methodName, lambdaItem);
        if (!DEFAULT_VALIDATION_PACKAGE.equals(validationPackage)) {
          ClassRef customErrorRef = new ClassRefBuilder()
              .withFullyQualifiedName(validationPackage + ".ValidationError").build();
          ClassRef listOfCustomErrorsRef = new ClassRefBuilder()
              .withFullyQualifiedName("java.util.List")
              .withArguments(customErrorRef).build();
          LocalVariable lambdaErrors = LocalVariable.newLocalVariable(listOfCustomErrorsRef, "errors");
          validatorExpr = new Lambda("item", new Block(
              new Declare(lambdaErrors,
                  new Construct(new ClassRefBuilder().withFullyQualifiedName("java.util.ArrayList").build())),
              convertAndAddErrors(lambdaCallExpr, lambdaErrors, validationPackage),
              new Return(lambdaErrors)));
        } else {
          validatorExpr = new Lambda("item", lambdaCallExpr);
        }
      } else {
        ClassRef validationExceptionRef = new ClassRefBuilder()
            .withFullyQualifiedName(validationPackage + ".ValidationException").build();
        ClassRef runtimeExceptionRef = new ClassRefBuilder()
            .withFullyQualifiedName("java.lang.RuntimeException").build();
        ClassRef validationErrorRef = new ClassRefBuilder()
            .withFullyQualifiedName(validationPackage + ".ValidationError").build();
        ClassRef listOfErrorsRef = new ClassRefBuilder()
            .withFullyQualifiedName("java.util.List")
            .withArguments(new ClassRefBuilder().withFullyQualifiedName(validationPackage + ".ValidationError").build())
            .build();

        LocalVariable lambdaItem = LocalVariable.newLocalVariable("item");
        LocalVariable lambdaErrors = LocalVariable.newLocalVariable(listOfErrorsRef, "errors");
        LocalVariable e = LocalVariable.newLocalVariable(validationExceptionRef, "e");
        LocalVariable re = LocalVariable.newLocalVariable(runtimeExceptionRef, "re");

        io.sundr.model.Statement invocation = vm.isStatic
            ? enclosingRef.call(vm.methodName, lambdaItem)
            : new Construct(enclosingRef).call(vm.methodName, lambdaItem);

        Try tryCatch = new Try(
            new Block(invocation),
            Arrays.asList(
                new Try.Catch(Property.newProperty(validationExceptionRef, "e"),
                    new Block(lambdaErrors.call("addAll", e.call("getErrors")))),
                new Try.Catch(Property.newProperty(runtimeExceptionRef, "re"),
                    new Block(lambdaErrors.call("add",
                        new Construct(validationErrorRef,
                            io.sundr.model.ValueRef.from(""), re.call("getMessage")))))),
            Optional.empty());

        validatorExpr = new Lambda("item", new Block(
            new Declare(lambdaErrors, new Construct(arrayListRef)),
            tryCatch,
            new Return(lambdaErrors)));
      }

      checkMethods.add(new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(selfRef)
          .withName(checkMethodName)
          .withNewBlock()
          .addToStatements(This.ref("validators").call("add", (io.sundr.model.Expression) validatorExpr))
          .addToStatements(new Return(new This()))
          .endBlock()
          .build());
    }

    // usingJakartaValidation() method
    Method jakartaValidation = new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef)
        .withName("usingJakartaValidation")
        .withNewBlock()
        .addToStatements(new Assign(This.ref("useJakartaValidation"), new ValueRef(true)))
        .addToStatements(new Return(new This()))
        .endBlock()
        .build();

    // endValidation() method — delegates to ValidatingBuilder.withJakartaValidation when flag is set
    Expression withJakartaValidationCall = validatingBuilderRef.call("withJakartaValidation",
        This.ref("builder"), This.ref("validators"));
    Expression regularConstruct = new Construct(validatingBuilderRef,
        This.ref("builder"), This.ref("validators"));
    Method endValidation = new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(validatingBuilderRef)
        .withName("endValidation")
        .withNewBlock()
        .addToStatements(new Return(new Ternary(This.ref("useJakartaValidation"),
            withJakartaValidationCall, regularConstruct)))
        .endBlock()
        .build();

    // build() shortcut method
    Method build = new MethodBuilder()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(validationResultRef)
        .withName("build")
        .withNewBlock()
        .addToStatements(new Return(new This().call("endValidation").call("build")))
        .endBlock()
        .build();

    TypeDefBuilder typeDefBuilder = new TypeDefBuilder()
        .withPackageName(targetPackage)
        .withName(validatorsBuilderName)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers()
        .addToFields(builderField)
        .addToFields(validatorsField)
        .addToFields(useJakartaValidationField)
        .addToConstructors(constructor)
        .addToMethods(jakartaValidation);

    for (Method m : checkMethods) {
      typeDefBuilder.addToMethods(m);
    }

    return typeDefBuilder
        .addToMethods(endValidation)
        .addToMethods(build)
        .build();
  }

  private static class ValidationMethod {
    final String targetTypeFqn;
    final String enclosingClass;
    final String methodName;
    final boolean isStatic;
    final boolean returnsErrors;

    ValidationMethod(String targetTypeFqn, String enclosingClass, String methodName,
        boolean isStatic, boolean returnsErrors) {
      this.targetTypeFqn = targetTypeFqn;
      this.enclosingClass = enclosingClass;
      this.methodName = methodName;
      this.isStatic = isStatic;
      this.returnsErrors = returnsErrors;
    }
  }
}
