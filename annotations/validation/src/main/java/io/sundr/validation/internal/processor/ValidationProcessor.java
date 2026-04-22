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

import static io.sundr.validation.internal.ValidationMethod.DEFAULT_VALIDATION_PACKAGE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.validation.internal.ValidationMethod;
import io.sundr.validation.internal.visitors.AddCheckMethods;
import io.sundr.validation.internal.visitors.AddValidateMethod;
import io.sundr.validation.internal.visitors.AddValidationBuilderFluentMethods;
import io.sundr.validation.internal.visitors.AddValidatorsBuilderFields;

@SupportedAnnotationTypes("io.sundr.validation.annotations.Validation")
public class ValidationProcessor extends AbstractCodeGeneratingProcessor {

  private static final String VALIDATOR_SUFFIX = "Validator";
  private static final String VALIDATORS_BUILDER_SUFFIX = "ValidatorsBuilder";
  private static final String BUILDABLE_ANNOTATION = "io.sundr.builder.annotations.Buildable";
  private static final String EXTERNAL_BUILDABLES_ANNOTATION = "io.sundr.builder.annotations.ExternalBuildables";

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      return false;
    }

    Map<String, List<ValidationMethod>> validatorsByType = collectValidationMethods(annotations, roundEnv);

    for (Map.Entry<String, List<ValidationMethod>> entry : validatorsByType.entrySet()) {
      String targetTypeFqn = entry.getKey();
      List<ValidationMethod> methods = entry.getValue();
      String validationPackage = resolveValidationPackage(targetTypeFqn);

      generate(buildValidator(targetTypeFqn, methods, validationPackage));
      generate(buildValidatorsBuilder(targetTypeFqn, methods, validationPackage));
    }

    return false;
  }

  private TypeDef buildValidator(String targetTypeFqn, List<ValidationMethod> methods, String validationPackage) {
    return new TypeDefBuilder()
        .withPackageName(getPackageName(targetTypeFqn))
        .withName(getSimpleName(targetTypeFqn) + VALIDATOR_SUFFIX)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers()
        .accept(new AddValidateMethod(ClassRef.forName(targetTypeFqn), methods, validationPackage))
        .build();
  }

  private TypeDef buildValidatorsBuilder(String targetTypeFqn, List<ValidationMethod> methods, String validationPackage) {
    String targetPackage = getPackageName(targetTypeFqn);
    String builderName = getSimpleName(targetTypeFqn) + VALIDATORS_BUILDER_SUFFIX;
    String builderFqn = targetPackage.isEmpty() ? builderName : targetPackage + "." + builderName;

    ClassRef targetTypeRef = ClassRef.forName(targetTypeFqn);
    ClassRef selfRef = ClassRef.forName(builderFqn);

    return new TypeDefBuilder()
        .withPackageName(targetPackage)
        .withName(builderName)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers()
        .accept(new AddValidatorsBuilderFields(targetTypeRef, validationPackage))
        .accept(new AddCheckMethods(selfRef, methods, validationPackage))
        .accept(new AddValidationBuilderFluentMethods(selfRef, targetTypeRef, validationPackage))
        .build();
  }

  private Map<String, List<ValidationMethod>> collectValidationMethods(
      Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Map<String, List<ValidationMethod>> result = new HashMap<>();

    for (TypeElement annotation : annotations) {
      for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
        if (element.getKind() != ElementKind.METHOD) {
          processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
              "@Validation can only be applied to methods", element);
          continue;
        }

        ValidationMethod vm = extractValidationMethod((ExecutableElement) element);
        if (vm != null) {
          result.computeIfAbsent(vm.getTargetTypeFqn(), k -> new ArrayList<>()).add(vm);
        }
      }
    }

    return result;
  }

  private ValidationMethod extractValidationMethod(ExecutableElement method) {
    if (method.getParameters().size() != 1) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "@Validation method must have exactly one parameter", method);
      return null;
    }

    boolean isStatic = method.getModifiers().contains(Modifier.STATIC);
    TypeElement enclosingType = (TypeElement) method.getEnclosingElement();

    if (!isStatic && !hasPublicNoArgConstructor(enclosingType)) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "@Validation on instance method requires enclosing class to have a public no-arg constructor", method);
      return null;
    }

    TypeMirror targetType = method.getParameters().get(0).asType();
    TypeMirror returnType = method.getReturnType();
    boolean returnsErrors = isListOfValidationError(returnType);
    boolean returnsItem = processingEnv.getTypeUtils().isSameType(returnType, targetType);
    boolean isVoid = returnType.getKind() == TypeKind.VOID;

    if (!returnsErrors && !returnsItem && !isVoid) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
          "@Validation method must return List<ValidationError>, the validated type, or void", method);
      return null;
    }

    return new ValidationMethod(
        getTypeFqn(targetType),
        enclosingType.getQualifiedName().toString(),
        method.getSimpleName().toString(),
        isStatic,
        returnsErrors);
  }

  private boolean hasPublicNoArgConstructor(TypeElement type) {
    return type.getEnclosedElements().stream()
        .filter(e -> e.getKind() == ElementKind.CONSTRUCTOR)
        .map(e -> (ExecutableElement) e)
        .anyMatch(c -> c.getParameters().isEmpty() && c.getModifiers().contains(Modifier.PUBLIC));
  }

  private boolean isListOfValidationError(TypeMirror type) {
    if (type.getKind() != TypeKind.DECLARED) {
      return false;
    }

    DeclaredType declaredType = (DeclaredType) type;
    TypeElement typeElement = (TypeElement) declaredType.asElement();
    if (!typeElement.getQualifiedName().toString().equals("java.util.List")) {
      return false;
    }

    List<? extends TypeMirror> typeArgs = declaredType.getTypeArguments();
    if (typeArgs.size() != 1 || typeArgs.get(0).getKind() != TypeKind.DECLARED) {
      return false;
    }

    String argFqn = ((TypeElement) ((DeclaredType) typeArgs.get(0)).asElement()).getQualifiedName().toString();
    return argFqn.equals("io.sundr.validation.ValidationError") || argFqn.endsWith(".validation.ValidationError");
  }

  private String getTypeFqn(TypeMirror type) {
    if (type.getKind() == TypeKind.DECLARED) {
      return ((TypeElement) ((DeclaredType) type).asElement()).getQualifiedName().toString();
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
}
