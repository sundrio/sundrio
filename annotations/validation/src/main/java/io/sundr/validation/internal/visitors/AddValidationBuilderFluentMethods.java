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

import io.sundr.builder.Visitor;
import io.sundr.model.Assign;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Construct;
import io.sundr.model.Expression;
import io.sundr.model.Return;
import io.sundr.model.Ternary;
import io.sundr.model.This;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.ValueRef;

/**
 * Adds the fluent terminal methods to a ValidatorsBuilder TypeDef:
 * {@code usingJakartaValidation()}, {@code endValidation()}, and {@code build()}.
 */
public class AddValidationBuilderFluentMethods implements Visitor<TypeDefFluent<?>> {

  private final ClassRef selfRef;
  private final ClassRef targetTypeRef;
  private final String validationPackage;

  public AddValidationBuilderFluentMethods(ClassRef selfRef, ClassRef targetTypeRef, String validationPackage) {
    this.selfRef = selfRef;
    this.targetTypeRef = targetTypeRef;
    this.validationPackage = validationPackage;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    ClassRef validatingBuilderRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".ValidatingBuilder")
        .withArguments(targetTypeRef).build();
    ClassRef validationResultRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".ValidationResult")
        .withArguments(targetTypeRef).build();

    Expression withJakarta = validatingBuilderRef.call("withJakartaValidation",
        This.ref("builder"), This.ref("validators"));
    Expression withoutJakarta = new Construct(validatingBuilderRef,
        This.ref("builder"), This.ref("validators"));

    def.addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(selfRef).withName("usingJakartaValidation")
        .withNewBlock()
        .addToStatements(new Assign(This.ref("useJakartaValidation"), new ValueRef(true)))
        .addToStatements(new Return(new This()))
        .endBlock()
        .endMethod()
        .addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(validatingBuilderRef).withName("endValidation")
        .withNewBlock()
        .addToStatements(new Return(new Ternary(This.ref("useJakartaValidation"), withJakarta, withoutJakarta)))
        .endBlock()
        .endMethod()
        .addNewMethod()
        .withNewModifiers().withPublic().endModifiers()
        .withReturnType(validationResultRef).withName("build")
        .withNewBlock()
        .addToStatements(new Return(new This().call("endValidation").call("build")))
        .endBlock()
        .endMethod();
  }
}
