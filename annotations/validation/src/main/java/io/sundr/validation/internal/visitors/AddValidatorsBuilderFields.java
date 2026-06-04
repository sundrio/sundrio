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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.model.Assign;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.LocalVariable;
import io.sundr.model.PrimitiveRefBuilder;
import io.sundr.model.Statement;
import io.sundr.model.This;
import io.sundr.model.TypeDefFluent;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Collections;
import io.sundr.validation.internal.ParamInfo;
import io.sundr.validation.internal.ValidationMethod;

/**
 * Adds the fields and constructor(s) to a ValidatorsBuilder TypeDef:
 * a delegate {@code Builder}, a {@code List} of validators, a {@code useJakartaValidation} flag,
 * and any constructor-arg fields required by non-static validation method enclosing classes.
 */
public class AddValidatorsBuilderFields implements Visitor<TypeDefFluent<?>> {

  private final ClassRef targetTypeRef;
  private final List<ValidationMethod> methods;
  private final String validationPackage;

  public AddValidatorsBuilderFields(ClassRef targetTypeRef, List<ValidationMethod> methods, String validationPackage) {
    this.targetTypeRef = targetTypeRef;
    this.methods = methods;
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
        .endField();

    Map<String, ParamInfo> ctorParamFields = collectConstructorParamFields();

    for (Map.Entry<String, ParamInfo> entry : ctorParamFields.entrySet()) {
      String fieldName = entry.getKey();
      ParamInfo param = entry.getValue();
      TypeRef typeRef = toTypeRef(param);
      def.addNewField()
          .withNewModifiers().withPrivate().endModifiers()
          .withTypeRef(typeRef).withName(fieldName)
          .endField();
    }

    // Primary constructor: just Builder<T>
    def.addNewConstructor()
        .withNewModifiers().withPublic().endModifiers()
        .addNewArgument().withName("builder").withTypeRef(builderInterfaceRef).endArgument()
        .withNewBlock().addToStatements(new Assign(This.ref("builder"), builderArg)).endBlock()
        .endConstructor();

    // Overloaded constructor with constructor-arg fields (if any)
    if (!ctorParamFields.isEmpty()) {
      List<Statement> assignments = new ArrayList<>();
      assignments.add(new Assign(This.ref("builder"), builderArg));

      // Build the constructor using addNewArgument fluent API
      TypeDefFluent<?>.ConstructorsNested<?> ctorBuilder = def.addNewConstructor()
          .withNewModifiers().withPublic().endModifiers()
          .addNewArgument().withName("builder").withTypeRef(builderInterfaceRef).endArgument();

      for (Map.Entry<String, ParamInfo> entry : ctorParamFields.entrySet()) {
        String fieldName = entry.getKey();
        ParamInfo param = entry.getValue();
        TypeRef typeRef = toTypeRef(param);
        ctorBuilder = ctorBuilder.addNewArgument().withName(fieldName).withTypeRef(typeRef).endArgument();
        assignments.add(new Assign(This.ref(fieldName), LocalVariable.newLocalVariable(typeRef, fieldName)));
      }

      ctorBuilder.withNewBlock().withStatements(assignments).endBlock()
          .endConstructor();
    }
  }

  /**
   * Collects all distinct constructor param fields needed across all validation methods.
   * Field names are prefixed with the enclosing class simple name (lowercased) to avoid collisions
   * when multiple enclosing classes have constructor params.
   */
  Map<String, ParamInfo> collectConstructorParamFields() {
    Map<String, ParamInfo> fields = new java.util.TreeMap<>();
    for (ValidationMethod vm : methods) {
      if (!vm.hasConstructorParams()) {
        continue;
      }
      for (ParamInfo param : vm.getConstructorParams()) {
        String fieldName = constructorParamFieldName(vm, param);
        fields.putIfAbsent(fieldName, param);
      }
    }
    return fields;
  }

  static String constructorParamFieldName(ValidationMethod vm, ParamInfo param) {
    String enclosingSimple = vm.getEnclosingClass();
    int dot = enclosingSimple.lastIndexOf('.');
    if (dot >= 0) {
      enclosingSimple = enclosingSimple.substring(dot + 1);
    }
    return Character.toLowerCase(enclosingSimple.charAt(0)) + enclosingSimple.substring(1) + "_" + param.getName();
  }

  static TypeRef toTypeRef(ParamInfo param) {
    if (param.isPrimitive()) {
      return new PrimitiveRefBuilder().withName(param.getTypeFqn()).withDimensions(0).build();
    }
    return ClassRef.forName(param.getTypeFqn());
  }
}
