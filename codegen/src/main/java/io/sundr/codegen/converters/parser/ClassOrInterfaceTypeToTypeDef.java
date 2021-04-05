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
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;

import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;

public class ClassOrInterfaceTypeToTypeDef implements Function<ClassOrInterfaceType, TypeDef> {

  private Function<TypeParameter, TypeDef> typeParamterToTypeDef;

  public TypeDef apply(ClassOrInterfaceType item) {
    List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

    for (Type type : item.getTypeArgs()) {
      new TypeParamDefBuilder()
          .build();
    }

    return new TypeDefBuilder()
        .withName(item.getName())
        .withParameters(parameters)
        .build();
  }
}
