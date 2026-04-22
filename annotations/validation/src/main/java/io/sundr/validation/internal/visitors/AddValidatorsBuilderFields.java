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
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.LocalVariable;
import io.sundr.model.PrimitiveRefBuilder;
import io.sundr.model.This;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.utils.Collections;

/**
 * Adds the fields and constructor to a ValidatorsBuilder TypeDef:
 * a delegate {@code Builder}, a {@code List} of validators, and a {@code useJakartaValidation} flag.
 */
public class AddValidatorsBuilderFields implements Visitor<TypeDefFluent<?>> {

  private final ClassRef targetTypeRef;
  private final String validationPackage;

  public AddValidatorsBuilderFields(ClassRef targetTypeRef, String validationPackage) {
    this.targetTypeRef = targetTypeRef;
    this.validationPackage = validationPackage;
  }

  @Override
  public void visit(TypeDefFluent<?> def) {
    ClassRef builderInterfaceRef = new ClassRefBuilder()
        .withFullyQualifiedName("io.sundr.builder.Builder")
        .withArguments(targetTypeRef).build();
    ClassRef validatorRef = new ClassRefBuilder()
        .withFullyQualifiedName(validationPackage + ".Validator")
        .withArguments(targetTypeRef).build();

    LocalVariable builderArg = LocalVariable.newLocalVariable(builderInterfaceRef, "builder");

    def.addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(builderInterfaceRef).withName("builder")
        .endField()
        .addNewField()
        .withNewModifiers().withPrivate().withFinal().endModifiers()
        .withTypeRef(Collections.LIST.toReference(validatorRef)).withName("validators")
        .addToAttributes(Attributeable.INIT, "new java.util.ArrayList<>()")
        .endField()
        .addNewField()
        .withNewModifiers().withPrivate().endModifiers()
        .withTypeRef(new PrimitiveRefBuilder().withName("boolean").withDimensions(0).build())
        .withName("useJakartaValidation")
        .endField()
        .addNewConstructor()
        .withNewModifiers().withPublic().endModifiers()
        .addNewArgument().withName("builder").withTypeRef(builderInterfaceRef).endArgument()
        .withNewBlock().addToStatements(new Assign(This.ref("builder"), builderArg)).endBlock()
        .endConstructor();
  }
}
