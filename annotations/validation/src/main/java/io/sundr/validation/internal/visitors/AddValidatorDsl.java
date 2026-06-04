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

package io.sundr.validation.internal.visitors;

import static io.sundr.validation.internal.ValidationMethod.DEFAULT_VALIDATION_PACKAGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import io.sundr.builder.Visitor;
import io.sundr.model.Argument;
import io.sundr.model.Assign;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Construct;
import io.sundr.model.Declare;
import io.sundr.model.Expression;
import io.sundr.model.ExpressionOrStatement;
import io.sundr.model.Foreach;
import io.sundr.model.Lambda;
import io.sundr.model.LocalVariable;
import io.sundr.model.Property;
import io.sundr.model.Return;
import io.sundr.model.Statement;
import io.sundr.model.This;
import io.sundr.model.Try;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.TypeRef;
import io.sundr.model.ValueRef;
import io.sundr.model.utils.Collections;
import io.sundr.validation.internal.ParamInfo;
import io.sundr.validation.internal.ValidationMethod;

/**
 * Generates a fluent DSL Validator class with:
 * <ul>
 * <li>{@code of(T item)} - creates validator with defaults</li>
 * <li>{@code withContext(args...).of(T item)} - creates validator with pre-supplied params</li>
 * <li>{@code validateXxx()} / {@code validateXxx(args)} - selects a validation to run</li>
 * <li>{@code validate()} - executes selected validations and returns errors</li>
 * </ul>
 */
public class AddValidatorDsl implements Visitor<TypeDefFluent<?>> {

  private final ClassRef targetTypeRef;
  private final ClassRef selfRef;
  private final List<ValidationMethod> methods;
  private final String validationPackage;

  public AddValidatorDsl(ClassRef targetTypeRef, ClassRef selfRef, List<ValidationMethod> methods,
      String validationPackage) {
    this.targetTypeRef = targetTypeRef;
    this.selfRef = selfRef;
    this.methods = methods;
    this.validationPackage = validationPackage;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    ClassRef validationErrorRef = ClassRef.forName(validationPackage + ".ValidationError");
    ClassRef validatorRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".Validator")
        .withArguments(targetTypeRef).build();
    ClassRef listOfErrorsRef = Collections.LIST.toReference(validationErrorRef);

    Map<String, ParamInfo> extraParamFields = collectExtraParamFields();
    Map<String, ParamInfo> ctorParamFields = collectConstructorParamFields();
    Map<String, ParamInfo> allContextFields = new LinkedHashMap<>();
    allContextFields.putAll(extraParamFields);
    allContextFields.putAll(ctorParamFields);

    // Fields: item, validators list, and all context params
    def.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(targetTypeRef).withName("item")
        .endField()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(Collections.LIST.toReference(validatorRef)).withName("validators")
        .endField();

    for (Map.Entry<String, ParamInfo> entry : allContextFields.entrySet()) {
      def.addNewField()
          .withNewModifiers().withPrivate().withFinal().endModifiers()
          .withTypeRef(toTypeRef(entry.getValue())).withName(entry.getKey())
          .endField();
    }

    // Private constructor: (T item, allContextParams...)
    addPrivateConstructor(def, allContextFields);

    // of(T item) - static factory with defaults
    addOfMethod(def, allContextFields);

    // withContext(allContextParams...) - returns a context with of(item) method
    if (!allContextFields.isEmpty()) {
      addWithContextMethod(def, allContextFields);
    }

    // Per-method validateXxx() and validateXxx(extraArgs...)
    for (ValidationMethod vm : methods) {
      if (!shouldSkipNoArgVariant(vm)) {
        addValidateMethodNoArg(def, vm, validatorRef, extraParamFields, ctorParamFields);
      }
      if (vm.hasExtraParams()) {
        addValidateMethodWithArgs(def, vm, validatorRef, ctorParamFields);
      }
    }

    // validate() - runs all selected validators
    addTerminalValidateMethod(def, listOfErrorsRef, validationErrorRef);

    // static validate(T item) - convenience method that runs all validations with defaults
    addStaticValidateAll(def, listOfErrorsRef);
  }

  private void addPrivateConstructor(TypeDefFluent<?> def, Map<String, ParamInfo> allContextFields) {
    ClassRef validatorRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".Validator")
        .withArguments(targetTypeRef).build();
    ClassRef validatorsListRef = Collections.LIST.toReference(validatorRef);

    LocalVariable itemArg = LocalVariable.newLocalVariable(targetTypeRef, "item");
    LocalVariable validatorsArg = LocalVariable.newLocalVariable(validatorsListRef, "validators");
    List<Statement> assignments = new ArrayList<>();
    assignments.add(new Assign(This.ref("item"), itemArg));
    assignments.add(new Assign(This.ref("validators"), validatorsArg));

    TypeDefFluent<?>.ConstructorsNested<?> ctorBuilder = def.addNewConstructor()
        .withNewModifiers().endModifiers()
        .addNewArgument().withName("item").withTypeRef(targetTypeRef).endArgument()
        .addNewArgument().withName("validators").withTypeRef(validatorsListRef).endArgument();

    for (Map.Entry<String, ParamInfo> entry : allContextFields.entrySet()) {
      String name = entry.getKey();
      TypeRef typeRef = toTypeRef(entry.getValue());
      ctorBuilder = ctorBuilder.addNewArgument().withName(name).withTypeRef(typeRef).endArgument();
      assignments.add(new Assign(This.ref(name), LocalVariable.newLocalVariable(typeRef, name)));
    }

    ctorBuilder.withNewBlock().withStatements(assignments).endBlock()
        .endConstructor();
  }

  private void addOfMethod(TypeDefFluent<?> def, Map<String, ParamInfo> allContextFields) {
    List<Expression> constructArgs = new ArrayList<>();
    constructArgs.add(LocalVariable.newLocalVariable(targetTypeRef, "item"));
    constructArgs.add(new Construct(Collections.ARRAY_LIST.toReference()));
    for (Map.Entry<String, ParamInfo> entry : allContextFields.entrySet()) {
      constructArgs.add(ValidationMethod.defaultExpression(entry.getValue()));
    }

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(selfRef)
        .withName("of")
        .addNewArgument().withName("item").withTypeRef(targetTypeRef).endArgument()
        .withNewBlock()
        .addToStatements(new Return(new Construct(selfRef, constructArgs)))
        .endBlock()
        .endMethod();
  }

  private void addWithContextMethod(TypeDefFluent<?> def, Map<String, ParamInfo> allContextFields) {
    ClassRef contextRef = ClassRef.forName(selfRef.getFullyQualifiedName() + "Context");

    List<Expression> contextArgs = new ArrayList<>();
    TypeDefFluent<?>.MethodsNested<?> methodBuilder = def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(contextRef)
        .withName("withContext");

    for (Map.Entry<String, ParamInfo> entry : allContextFields.entrySet()) {
      String name = entry.getKey();
      TypeRef typeRef = toTypeRef(entry.getValue());
      methodBuilder = methodBuilder.addNewArgument().withName(name).withTypeRef(typeRef).endArgument();
      contextArgs.add(LocalVariable.newLocalVariable(typeRef, name));
    }

    methodBuilder.withNewBlock()
        .addToStatements(new Return(new Construct(contextRef, contextArgs)))
        .endBlock()
        .endMethod();
  }

  private boolean shouldSkipNoArgVariant(ValidationMethod vm) {
    if (!vm.hasExtraParams()) {
      return false;
    }
    return methods.stream()
        .filter(m -> m != vm && !m.hasExtraParams())
        .anyMatch(m -> m.getMethodName().equals(vm.getMethodName()));
  }

  private void addValidateMethodNoArg(TypeDefFluent<?> def, ValidationMethod vm,
      ClassRef validatorRef, Map<String, ParamInfo> extraParamFields, Map<String, ParamInfo> ctorParamFields) {

    List<Expression> ctorFieldRefs = buildConstructorFieldRefs(vm, ctorParamFields);
    List<Expression> extraArgRefs = vm.getExtraParams().stream()
        .map(p -> (Expression) This.ref(extraParamFieldName(vm, p)))
        .collect(Collectors.toList());

    ExpressionOrStatement lambda = vm.isReturnsErrors()
        ? createErrorReturningLambda(vm, extraArgRefs, ctorFieldRefs)
        : createThrowingLambda(vm, extraArgRefs, ctorFieldRefs);

    Map<String, ParamInfo> allContextFields = new LinkedHashMap<>();
    allContextFields.putAll(extraParamFields);
    allContextFields.putAll(ctorParamFields);

    def.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef)
        .withName(vm.getMethodName())
        .withNewBlock()
        .withStatements(buildImmutableAddStatements(lambda, allContextFields))
        .endBlock()
        .endMethod();
  }

  private void addValidateMethodWithArgs(TypeDefFluent<?> def, ValidationMethod vm,
      ClassRef validatorRef, Map<String, ParamInfo> ctorParamFields) {

    Map<String, ParamInfo> extraParamFields = collectExtraParamFields();

    List<Expression> ctorFieldRefs = buildConstructorFieldRefs(vm, ctorParamFields);
    List<Expression> extraArgRefs = vm.getExtraParams().stream()
        .map(p -> (Expression) LocalVariable.newLocalVariable(toTypeRef(p), p.getName()))
        .collect(Collectors.toList());

    ExpressionOrStatement lambda = vm.isReturnsErrors()
        ? createErrorReturningLambda(vm, extraArgRefs, ctorFieldRefs)
        : createThrowingLambda(vm, extraArgRefs, ctorFieldRefs);

    Map<String, ParamInfo> allContextFields = new LinkedHashMap<>();
    allContextFields.putAll(extraParamFields);
    allContextFields.putAll(ctorParamFields);

    List<Argument> methodArgs = vm.getExtraParams().stream()
        .map(p -> Argument.newArgument(toTypeRef(p), p.getName()))
        .collect(Collectors.toList());

    TypeDefFluent<?>.MethodsNested<?> methodBuilder = def.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef)
        .withName(vm.getMethodName());

    for (Argument arg : methodArgs) {
      methodBuilder = methodBuilder.addNewArgument().withName(arg.getName()).withTypeRef(arg.getTypeRef()).endArgument();
    }

    methodBuilder.withNewBlock()
        .withStatements(buildImmutableAddStatements(lambda, allContextFields))
        .endBlock()
        .endMethod();
  }

  /**
   * Builds statements that create a new list from this.validators, add the lambda,
   * and return a new instance with the expanded list.
   *
   * <pre>
   * List newValidators = new ArrayList(this.validators);
   * newValidators.add(lambda);
   * return new PersonValidator(this.item, newValidators, this.field1, ...);
   * </pre>
   */
  private List<Statement> buildImmutableAddStatements(ExpressionOrStatement lambda,
      Map<String, ParamInfo> allContextFields) {
    ClassRef validatorItemRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".Validator")
        .withArguments(targetTypeRef).build();
    ClassRef validatorsListRef = Collections.LIST.toReference(validatorItemRef);

    LocalVariable newValidators = LocalVariable.newLocalVariable(validatorsListRef, "newValidators");

    List<Expression> constructArgs = new ArrayList<>();
    constructArgs.add(This.ref("item"));
    constructArgs.add(newValidators);
    for (String name : allContextFields.keySet()) {
      constructArgs.add(This.ref(name));
    }

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(newValidators, new Construct(Collections.ARRAY_LIST.toReference(), This.ref("validators"))));
    statements.add(newValidators.call("add", (Expression) lambda));
    statements.add(new Return(new Construct(selfRef, constructArgs)));
    return statements;
  }

  private void addTerminalValidateMethod(TypeDefFluent<?> def, ClassRef listOfErrorsRef,
      ClassRef validationErrorRef) {
    ClassRef validatorRefForItem = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".Validator")
        .withArguments(targetTypeRef).build();

    LocalVariable errors = LocalVariable.newLocalVariable(listOfErrorsRef, "errors");
    LocalVariable v = LocalVariable.newLocalVariable(validatorRefForItem, "v");
    LocalVariable result = LocalVariable.newLocalVariable(listOfErrorsRef, "result");

    def.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(listOfErrorsRef)
        .withName("validate")
        .withNewBlock()
        .addToStatements(new Declare(errors, new Construct(Collections.ARRAY_LIST.toReference())))
        .addToStatements(new Foreach(v, This.ref("validators"),
            new Block(errors.call("addAll", v.call("validate", This.ref("item"))))))
        .addToStatements(new Return(errors))
        .endBlock()
        .endMethod();
  }

  private Map<String, ParamInfo> collectExtraParamFields() {
    Map<String, ParamInfo> fields = new TreeMap<>();
    for (ValidationMethod vm : methods) {
      for (ParamInfo param : vm.getExtraParams()) {
        String fieldName = extraParamFieldName(vm, param);
        fields.putIfAbsent(fieldName, param);
      }
    }
    return fields;
  }

  private Map<String, ParamInfo> collectConstructorParamFields() {
    Map<String, ParamInfo> fields = new TreeMap<>();
    for (ValidationMethod vm : methods) {
      if (!vm.hasConstructorParams()) {
        continue;
      }
      for (ParamInfo param : vm.getConstructorParams()) {
        String fieldName = AddValidatorsBuilderFields.constructorParamFieldName(vm, param);
        fields.putIfAbsent(fieldName, param);
      }
    }
    return fields;
  }

  private List<Expression> buildConstructorFieldRefs(ValidationMethod vm, Map<String, ParamInfo> ctorParamFields) {
    if (!vm.hasConstructorParams()) {
      return java.util.Collections.emptyList();
    }
    return vm.getConstructorParams().stream()
        .map(p -> (Expression) This.ref(AddValidatorsBuilderFields.constructorParamFieldName(vm, p)))
        .collect(Collectors.toList());
  }

  /**
   * Returns the field name for an extra param. Uses the plain param name when unambiguous.
   * When multiple methods share the same param name (with different types), prefixes with
   * the method base name: e.g. validateFoo(Person, int limit) -> "fooLimit".
   */
  private String extraParamFieldName(ValidationMethod vm, ParamInfo param) {
    String paramName = param.getName();

    boolean collision = methods.stream()
        .filter(m -> m != vm && m.hasExtraParams())
        .flatMap(m -> m.getExtraParams().stream())
        .anyMatch(p -> p.getName().equals(paramName) && !p.getTypeFqn().equals(param.getTypeFqn()));

    if (!collision) {
      return paramName;
    }

    String base = vm.methodBaseName();
    return base + Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1);
  }

  private ExpressionOrStatement createErrorReturningLambda(ValidationMethod vm,
      List<Expression> extraArgs, List<Expression> ctorArgs) {
    LocalVariable lambdaItem = LocalVariable.newLocalVariable("item");
    Expression callExpr = vm.callExpressionFull(lambdaItem, extraArgs, ctorArgs);

    if (!DEFAULT_VALIDATION_PACKAGE.equals(validationPackage)) {
      ClassRef customErrorRef = ClassRef.forName(validationPackage + ".ValidationError");
      ClassRef baseErrorRef = ClassRef.forName(DEFAULT_VALIDATION_PACKAGE + ".ValidationError");
      LocalVariable lambdaErrors = LocalVariable.newLocalVariable(Collections.LIST.toReference(customErrorRef), "errors");
      LocalVariable baseError = LocalVariable.newLocalVariable(baseErrorRef, "__e");
      return new Lambda("item", new Block(
          new Declare(lambdaErrors, new Construct(Collections.ARRAY_LIST.toReference())),
          new Foreach(baseError, callExpr,
              new Block(lambdaErrors.call("add", new Construct(customErrorRef,
                  baseError.call("getPath"), baseError.call("getMessage"), baseError.call("getInvalidValue"))))),
          new Return(lambdaErrors)));
    }

    return new Lambda("item", callExpr);
  }

  private ExpressionOrStatement createThrowingLambda(ValidationMethod vm,
      List<Expression> extraArgs, List<Expression> ctorArgs) {
    ClassRef validationExceptionRef = ClassRef.forName(validationPackage + ".ValidationException");
    ClassRef runtimeExceptionRef = ClassRef.forName("java.lang.RuntimeException");
    ClassRef validationErrorRef = ClassRef.forName(validationPackage + ".ValidationError");
    ClassRef listOfErrorsRef = Collections.LIST.toReference(validationErrorRef);

    LocalVariable lambdaItem = LocalVariable.newLocalVariable("item");
    LocalVariable lambdaErrors = LocalVariable.newLocalVariable(listOfErrorsRef, "errors");
    LocalVariable e = LocalVariable.newLocalVariable(validationExceptionRef, "e");
    LocalVariable re = LocalVariable.newLocalVariable(runtimeExceptionRef, "re");

    Statement callStmt = vm.callStatementFull(lambdaItem, extraArgs, ctorArgs);

    Try tryCatch = new Try(
        new Block(callStmt),
        Arrays.asList(
            new Try.Catch(Property.newProperty(validationExceptionRef, "e"),
                new Block(lambdaErrors.call("addAll", e.call("getErrors")))),
            new Try.Catch(Property.newProperty(runtimeExceptionRef, "re"),
                new Block(lambdaErrors.call("add",
                    new Construct(validationErrorRef, ValueRef.from(""), re.call("getMessage")))))),
        Optional.empty());

    return new Lambda("item", new Block(
        new Declare(lambdaErrors, new Construct(Collections.ARRAY_LIST.toReference())),
        tryCatch,
        new Return(lambdaErrors)));
  }

  private void addStaticValidateAll(TypeDefFluent<?> def, ClassRef listOfErrorsRef) {
    // Generates: public static List<ValidationError> validate(T item) { return of(item).validateX().validateY()...validate(); }
    // Build a chain: of(item).validateX().validateY()...validate()
    Expression chain = selfRef.call("of", LocalVariable.newLocalVariable(targetTypeRef, "item"));
    for (ValidationMethod vm : methods) {
      chain = ((Expression) chain).call(vm.getMethodName());
    }
    chain = ((Expression) chain).call("validate");

    def.addNewMethod()
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(listOfErrorsRef)
        .withName("validate")
        .addNewArgument().withName("item").withTypeRef(targetTypeRef).endArgument()
        .withNewBlock()
        .addToStatements(new Return(chain))
        .endBlock()
        .endMethod();
  }

  /**
   * Returns true if a separate context class is needed (when there are extra params or constructor params).
   */
  public boolean needsContextClass() {
    return !collectExtraParamFields().isEmpty() || !collectConstructorParamFields().isEmpty();
  }

  /**
   * Builds the separate XxxValidatorContext class that holds pre-supplied params and has an of(T) method.
   */
  public io.sundr.model.TypeDef buildContextClass() {
    Map<String, ParamInfo> allContextFields = new LinkedHashMap<>();
    allContextFields.putAll(collectExtraParamFields());
    allContextFields.putAll(collectConstructorParamFields());

    String contextName = selfRef.getName() + "Context";
    String contextPackage = selfRef.getFullyQualifiedName().contains(".")
        ? selfRef.getFullyQualifiedName().substring(0, selfRef.getFullyQualifiedName().lastIndexOf('.'))
        : "";

    io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder()
        .withPackageName(contextPackage)
        .withName(contextName)
        .withKind(io.sundr.model.Kind.CLASS)
        .withNewModifiers().withPublic().withFinal().endModifiers();

    for (Map.Entry<String, ParamInfo> entry : allContextFields.entrySet()) {
      builder.addNewField()
          .withNewModifiers().withPrivate().withFinal().endModifiers()
          .withTypeRef(toTypeRef(entry.getValue())).withName(entry.getKey())
          .endField();
    }

    List<Statement> assignments = new ArrayList<>();
    io.sundr.model.TypeDefBuilder.ConstructorsNested<?> ctorBuilder = builder.addNewConstructor()
        .withNewModifiers().endModifiers();

    for (Map.Entry<String, ParamInfo> entry : allContextFields.entrySet()) {
      String name = entry.getKey();
      TypeRef typeRef = toTypeRef(entry.getValue());
      ctorBuilder = ctorBuilder.addNewArgument().withName(name).withTypeRef(typeRef).endArgument();
      assignments.add(new Assign(This.ref(name), LocalVariable.newLocalVariable(typeRef, name)));
    }
    ctorBuilder.withNewBlock().withStatements(assignments).endBlock().endConstructor();

    List<Expression> outerConstructArgs = new ArrayList<>();
    outerConstructArgs.add(LocalVariable.newLocalVariable(targetTypeRef, "item"));
    outerConstructArgs.add(new Construct(Collections.ARRAY_LIST.toReference()));
    for (String name : allContextFields.keySet()) {
      outerConstructArgs.add(This.ref(name));
    }

    builder.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef)
        .withName("of")
        .addNewArgument().withName("item").withTypeRef(targetTypeRef).endArgument()
        .withNewBlock()
        .addToStatements(new Return(new Construct(selfRef, outerConstructArgs)))
        .endBlock()
        .endMethod();

    return builder.build();
  }

  static TypeRef toTypeRef(ParamInfo param) {
    return AddValidatorsBuilderFields.toTypeRef(param);
  }
}
