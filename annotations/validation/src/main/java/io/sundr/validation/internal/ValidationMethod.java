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

import io.sundr.model.ClassRef;
import io.sundr.model.Construct;
import io.sundr.model.Expression;
import io.sundr.model.LocalVariable;
import io.sundr.model.Statement;

public class ValidationMethod {

  public static final String DEFAULT_VALIDATION_PACKAGE = "io.sundr.validation";

  private final String targetTypeFqn;
  private final String enclosingClass;
  private final String methodName;
  private final boolean isStatic;
  private final boolean returnsErrors;

  public ValidationMethod(String targetTypeFqn, String enclosingClass, String methodName,
      boolean isStatic, boolean returnsErrors) {
    this.targetTypeFqn = targetTypeFqn;
    this.enclosingClass = enclosingClass;
    this.methodName = methodName;
    this.isStatic = isStatic;
    this.returnsErrors = returnsErrors;
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

  /**
   * Creates the call expression for invoking this validation method.
   * For static methods: {@code EnclosingClass.methodName(item)}
   * For instance methods: {@code new EnclosingClass().methodName(item)}
   */
  public Expression callExpression(LocalVariable item) {
    ClassRef enclosingRef = ClassRef.forName(enclosingClass);
    return isStatic
        ? (Expression) enclosingRef.call(methodName, item)
        : (Expression) new Construct(enclosingRef).call(methodName, item);
  }

  /**
   * Same as {@link #callExpression(LocalVariable)} but returns a {@link Statement}.
   */
  public Statement callStatement(LocalVariable item) {
    ClassRef enclosingRef = ClassRef.forName(enclosingClass);
    return isStatic
        ? enclosingRef.call(methodName, item)
        : new Construct(enclosingRef).call(methodName, item);
  }

  public String checkMethodName() {
    String base = methodName.startsWith("validate") && methodName.length() > "validate".length()
        ? methodName.substring("validate".length())
        : methodName;
    return "check" + Character.toUpperCase(base.charAt(0)) + base.substring(1);
  }
}
