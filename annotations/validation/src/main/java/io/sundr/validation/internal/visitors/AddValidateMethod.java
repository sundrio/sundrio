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
import java.util.List;
import java.util.Optional;

import io.sundr.builder.Visitor;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.Construct;
import io.sundr.model.Declare;
import io.sundr.model.Foreach;
import io.sundr.model.LocalVariable;
import io.sundr.model.Property;
import io.sundr.model.Return;
import io.sundr.model.Statement;
import io.sundr.model.Try;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.ValueRef;
import io.sundr.model.utils.Collections;
import io.sundr.validation.internal.ValidationMethod;

/**
 * Adds a static {@code validate} method to a Validator TypeDef.
 * The method collects errors from all validation methods and returns them.
 */
public class AddValidateMethod implements Visitor<TypeDefFluent<?>> {

  private final ClassRef targetTypeRef;
  private final List<ValidationMethod> methods;
  private final String validationPackage;

  public AddValidateMethod(ClassRef targetTypeRef, List<ValidationMethod> methods, String validationPackage) {
    this.targetTypeRef = targetTypeRef;
    this.methods = methods;
    this.validationPackage = validationPackage;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    ClassRef validationErrorRef = ClassRef.forName(validationPackage + ".ValidationError");
    ClassRef listOfErrorsRef = Collections.LIST.toReference(validationErrorRef);

    LocalVariable item = LocalVariable.newLocalVariable(targetTypeRef, "item");
    LocalVariable errors = LocalVariable.newLocalVariable(listOfErrorsRef, "errors");

    List<Statement> statements = new ArrayList<>();
    statements.add(new Declare(errors, new Construct(Collections.ARRAY_LIST.toReference())));
    for (ValidationMethod vm : methods) {
      statements.add(createInvocation(vm, item, errors));
    }
    statements.add(new Return(errors));

    def.addNewMethod()
        .withName("validate")
        .withNewModifiers().withPublic().withStatic().endModifiers()
        .withReturnType(listOfErrorsRef)
        .addNewArgument().withName("item").withTypeRef(targetTypeRef).endArgument()
        .withNewBlock().withStatements(statements).endBlock()
        .endMethod();
  }

  private Statement createInvocation(ValidationMethod vm, LocalVariable item, LocalVariable errors) {
    if (vm.isReturnsErrors()) {
      if (!DEFAULT_VALIDATION_PACKAGE.equals(validationPackage)) {
        return convertAndAddErrors(vm.callExpression(item), errors);
      }
      return errors.call("addAll", vm.callExpression(item));
    }
    return wrapThrowingCall(vm.callStatement(item), errors);
  }

  private Statement wrapThrowingCall(Statement invocation, LocalVariable errors) {
    ClassRef validationExceptionRef = ClassRef.forName(validationPackage + ".ValidationException");
    ClassRef runtimeExceptionRef = ClassRef.forName("java.lang.RuntimeException");
    ClassRef validationErrorRef = ClassRef.forName(validationPackage + ".ValidationError");

    LocalVariable e = LocalVariable.newLocalVariable(validationExceptionRef, "e");
    LocalVariable re = LocalVariable.newLocalVariable(runtimeExceptionRef, "re");

    return new Try(
        new Block(invocation),
        Arrays.asList(
            new Try.Catch(Property.newProperty(validationExceptionRef, "e"),
                new Block(errors.call("addAll", e.call("getErrors")))),
            new Try.Catch(Property.newProperty(runtimeExceptionRef, "re"),
                new Block(errors.call("add",
                    new Construct(validationErrorRef, ValueRef.from(""), re.call("getMessage")))))),
        Optional.empty());
  }

  private Statement convertAndAddErrors(io.sundr.model.Expression sourceCallExpr, LocalVariable errors) {
    ClassRef baseErrorRef = ClassRef.forName(DEFAULT_VALIDATION_PACKAGE + ".ValidationError");
    ClassRef customErrorRef = ClassRef.forName(validationPackage + ".ValidationError");
    LocalVariable baseError = LocalVariable.newLocalVariable(baseErrorRef, "__e");
    return new Foreach(baseError, sourceCallExpr,
        new Block(errors.call("add", new Construct(customErrorRef,
            baseError.call("getPath"), baseError.call("getMessage"), baseError.call("getInvalidValue")))));
  }
}
