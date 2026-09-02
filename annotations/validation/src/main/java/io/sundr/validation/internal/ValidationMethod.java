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

package io.sundr.validation.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.sundr.model.ClassRef;
import io.sundr.model.Expression;
import io.sundr.model.LocalVariable;
import io.sundr.model.Statement;
import io.sundr.model.ValueRef;

public class ValidationMethod {

  public static final String DEFAULT_VALIDATION_PACKAGE = "io.sundr.validation";

  private final String targetTypeFqn;
  private final String enclosingClass;
  private final String methodName;
  private final boolean isStatic;
  private final boolean returnsErrors;
  private final List<ParamInfo> extraParams;
  private final List<ParamInfo> constructorParams;

  public ValidationMethod(String targetTypeFqn, String enclosingClass, String methodName,
      boolean isStatic, boolean returnsErrors,
      List<ParamInfo> extraParams, List<ParamInfo> constructorParams) {
    this.targetTypeFqn = targetTypeFqn;
    this.enclosingClass = enclosingClass;
    this.methodName = methodName;
    this.isStatic = isStatic;
    this.returnsErrors = returnsErrors;
    this.extraParams = extraParams != null ? extraParams : Collections.emptyList();
    this.constructorParams = constructorParams != null ? constructorParams : Collections.emptyList();
  }

  public ValidationMethod(String targetTypeFqn, String enclosingClass, String methodName,
      boolean isStatic, boolean returnsErrors) {
    this(targetTypeFqn, enclosingClass, methodName, isStatic, returnsErrors,
        Collections.emptyList(), Collections.emptyList());
  }

  public String getTargetTypeFqn() {
    return targetTypeFqn;
  }

  public String getEnclosingClass() {
    return enclosingClass;
  }

  public String getMethodName() {
    return methodName;
  }

  public boolean isStatic() {
    return isStatic;
  }

  public boolean isReturnsErrors() {
    return returnsErrors;
  }

  public List<ParamInfo> getExtraParams() {
    return extraParams;
  }

  public List<ParamInfo> getConstructorParams() {
    return constructorParams;
  }

  public boolean hasExtraParams() {
    return !extraParams.isEmpty();
  }

  public boolean hasConstructorParams() {
    return !constructorParams.isEmpty();
  }

  /**
   * Creates the call expression using default values for all extra params and constructor args.
   */
  public Expression callExpression(LocalVariable item) {
    List<Expression> defaultExtras = extraParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    List<Expression> defaultCtorArgs = constructorParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    return buildCallExpression(item, defaultExtras, defaultCtorArgs);
  }

  /**
   * Creates the call expression with the provided extra args (constructor args use defaults).
   */
  public Expression callExpression(LocalVariable item, List<Expression> extraArgs) {
    List<Expression> defaultCtorArgs = constructorParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    return buildCallExpression(item, extraArgs, defaultCtorArgs);
  }

  /**
   * Creates the call expression with the provided constructor args (extra method params use defaults).
   */
  public Expression callExpressionWithConstructorArgs(LocalVariable item, List<Expression> ctorArgs) {
    List<Expression> defaultExtras = extraParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    return buildCallExpression(item, defaultExtras, ctorArgs);
  }

  /**
   * Creates the call expression with full control over both extra args and constructor args.
   */
  public Expression callExpressionFull(LocalVariable item, List<Expression> extraArgs, List<Expression> ctorArgs) {
    return buildCallExpression(item, extraArgs, ctorArgs);
  }

  /**
   * Same as {@link #callExpression(LocalVariable)} but returns a {@link Statement}.
   */
  public Statement callStatement(LocalVariable item) {
    List<Expression> defaultExtras = extraParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    List<Expression> defaultCtorArgs = constructorParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    return buildCallStatement(item, defaultExtras, defaultCtorArgs);
  }

  /**
   * Same as {@link #callExpression(LocalVariable, List)} but returns a {@link Statement}.
   */
  public Statement callStatement(LocalVariable item, List<Expression> extraArgs) {
    List<Expression> defaultCtorArgs = constructorParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    return buildCallStatement(item, extraArgs, defaultCtorArgs);
  }

  /**
   * Same as {@link #callExpressionWithConstructorArgs(LocalVariable, List)} but returns a {@link Statement}.
   */
  public Statement callStatementWithConstructorArgs(LocalVariable item, List<Expression> ctorArgs) {
    List<Expression> defaultExtras = extraParams.stream()
        .map(ValidationMethod::defaultExpression)
        .collect(Collectors.toList());
    return buildCallStatement(item, defaultExtras, ctorArgs);
  }

  /**
   * Same as {@link #callExpressionFull(LocalVariable, List, List)} but returns a {@link Statement}.
   */
  public Statement callStatementFull(LocalVariable item, List<Expression> extraArgs, List<Expression> ctorArgs) {
    return buildCallStatement(item, extraArgs, ctorArgs);
  }

  /**
   * Returns the method base name, stripped of the "validate" prefix with the first character lowered.
   * E.g. "validateMinAge" -> "minAge", "validateFoo" -> "foo", "check" -> "check".
   */
  public String methodBaseName() {
    String base = methodName.startsWith("validate") && methodName.length() > "validate".length()
        ? methodName.substring("validate".length())
        : methodName;
    return Character.toLowerCase(base.charAt(0)) + base.substring(1);
  }

  public String checkMethodName() {
    String base = methodBaseName();
    return "check" + Character.toUpperCase(base.charAt(0)) + base.substring(1);
  }

  private Expression buildCallExpression(LocalVariable item, List<Expression> extraArgs, List<Expression> ctorArgs) {
    ClassRef enclosingRef = ClassRef.forName(enclosingClass);
    Expression[] allMethodArgs = buildMethodArgs(item, extraArgs);
    if (isStatic) {
      return (Expression) enclosingRef.call(methodName, allMethodArgs);
    }
    Expression scope = ctorArgs.isEmpty()
        ? enclosingRef.construct()
        : enclosingRef.construct(ctorArgs);
    return (Expression) scope.call(methodName, allMethodArgs);
  }

  private Statement buildCallStatement(LocalVariable item, List<Expression> extraArgs, List<Expression> ctorArgs) {
    ClassRef enclosingRef = ClassRef.forName(enclosingClass);
    Expression[] allMethodArgs = buildMethodArgs(item, extraArgs);
    if (isStatic) {
      return enclosingRef.call(methodName, allMethodArgs);
    }
    Expression scope = ctorArgs.isEmpty()
        ? enclosingRef.construct()
        : enclosingRef.construct(ctorArgs);
    return scope.call(methodName, allMethodArgs);
  }

  private Expression[] buildMethodArgs(LocalVariable item, List<Expression> extraArgs) {
    List<Expression> all = new ArrayList<>();
    all.add(item);
    if (extraArgs != null) {
      all.addAll(extraArgs);
    }
    return all.toArray(new Expression[0]);
  }

  public static Expression defaultExpression(ParamInfo param) {
    if (!param.isPrimitive()) {
      return ValueRef.NULL;
    }
    switch (param.getTypeFqn()) {
      case "boolean":
        return new ValueRef(false);
      case "char":
        return new ValueRef('\u0000');
      case "long":
        return new ValueRef(0L);
      case "double":
        return new ValueRef(0.0);
      case "float":
        return new ValueRef(0.0f);
      default:
        return new ValueRef(0);
    }
  }
}
