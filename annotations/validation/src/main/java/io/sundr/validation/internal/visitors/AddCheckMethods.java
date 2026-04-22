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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.sundr.builder.Visitor;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.Construct;
import io.sundr.model.Declare;
import io.sundr.model.Expression;
import io.sundr.model.ExpressionOrStatement;
import io.sundr.model.Foreach;
import io.sundr.model.Lambda;
import io.sundr.model.LocalVariable;
import io.sundr.model.Property;
import io.sundr.model.Return;
import io.sundr.model.This;
import io.sundr.model.Try;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.ValueRef;
import io.sundr.model.utils.Collections;
import io.sundr.validation.internal.ValidationMethod;

/**
 * Adds fluent {@code check*} methods to a ValidatorsBuilder TypeDef.
 * Each check method registers a validator lambda for the corresponding validation method.
 */
public class AddCheckMethods implements Visitor<TypeDefFluent<?>> {

  private final ClassRef selfRef;
  private final List<ValidationMethod> methods;
  private final String validationPackage;

  public AddCheckMethods(ClassRef selfRef, List<ValidationMethod> methods, String validationPackage) {
    this.selfRef = selfRef;
    this.methods = methods;
    this.validationPackage = validationPackage;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    for (ValidationMethod vm : methods) {
      ExpressionOrStatement validatorExpr = vm.isReturnsErrors()
          ? createErrorReturningLambda(vm)
          : createThrowingLambda(vm);

      def.addNewMethod()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(selfRef)
          .withName(vm.checkMethodName())
          .withNewBlock()
          .addToStatements(This.ref("validators").call("add", (Expression) validatorExpr))
          .addToStatements(new Return(new This()))
          .endBlock()
          .endMethod();
    }
  }

  private ExpressionOrStatement createErrorReturningLambda(ValidationMethod vm) {
    LocalVariable lambdaItem = LocalVariable.newLocalVariable("item");
    Expression callExpr = vm.callExpression(lambdaItem);

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

  private ExpressionOrStatement createThrowingLambda(ValidationMethod vm) {
    ClassRef validationExceptionRef = ClassRef.forName(validationPackage + ".ValidationException");
    ClassRef runtimeExceptionRef = ClassRef.forName("java.lang.RuntimeException");
    ClassRef validationErrorRef = ClassRef.forName(validationPackage + ".ValidationError");
    ClassRef listOfErrorsRef = Collections.LIST.toReference(validationErrorRef);

    LocalVariable lambdaItem = LocalVariable.newLocalVariable("item");
    LocalVariable lambdaErrors = LocalVariable.newLocalVariable(listOfErrorsRef, "errors");
    LocalVariable e = LocalVariable.newLocalVariable(validationExceptionRef, "e");
    LocalVariable re = LocalVariable.newLocalVariable(runtimeExceptionRef, "re");

    Try tryCatch = new Try(
        new Block(vm.callStatement(lambdaItem)),
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
}
