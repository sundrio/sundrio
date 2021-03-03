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

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import io.sundr.Function;
import io.sundr.codegen.model.TypeRef;

public class TypeParameterToJavaType implements Function<TypeParameter, TypeRef> {

  public TypeRef apply(TypeParameter item) {
    List<TypeRef> interfaces = new ArrayList<TypeRef>();

    for (ClassOrInterfaceType classOrInterfaceType : item.getTypeBound()) {

    }
    return null;
  }
}
