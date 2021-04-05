/*
 * Copyright 2016 The original authors.
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

package io.sundr.codegen.converters.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;

public class DeclarationToJavaClazz implements Function<ClassOrInterfaceDeclaration, TypeDef> {

  private final ClassOrInterfaceTypeToTypeDef classOrInterfaceTypeToTypeDef = new ClassOrInterfaceTypeToTypeDef();

  public TypeDef apply(ClassOrInterfaceDeclaration item) {
    TypeDef superClassType = null;
    List<TypeDef> implementsTypes = new ArrayList<TypeDef>();
    List<TypeDef> genericTypes = new ArrayList<TypeDef>();

    for (ClassOrInterfaceType type : item.getImplements()) {
      if (item.isInterface()) {
        implementsTypes.add(classOrInterfaceTypeToTypeDef.apply(type));
      } else if (superClassType == null) {
        superClassType = classOrInterfaceTypeToTypeDef.apply(type);
      } else {
        throw new IllegalStateException("Multiple extends found and type is not an interface");
      }
    }

    for (ClassOrInterfaceType type : item.getImplements()) {
      implementsTypes.add(classOrInterfaceTypeToTypeDef.apply(type));
    }

    for (TypeParameter type : item.getTypeParameters()) {

    }

    return new TypeDefBuilder()
        .withPackageName("changeme")
        .withName(item.getName())
        .withKind(item.isInterface() ? Kind.INTERFACE : Kind.CLASS)
        .withModifiers(item.getModifiers())
        //.withExtendsList(superClassType)
        //.withInterfaces(implementsTypes.toArray(new TypeDef[implementsTypes.size()]))
        .build();
  }
}
