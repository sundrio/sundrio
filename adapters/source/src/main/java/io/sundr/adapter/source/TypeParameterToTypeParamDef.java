/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.adapter.source;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeRef;

public class TypeParameterToTypeParamDef implements Function<TypeParameter, TypeParamDef> {

  private final Function<ClassOrInterfaceType, TypeRef> classOrInterfaceToTypeRef;

  public TypeParameterToTypeParamDef(Function<ClassOrInterfaceType, TypeRef> classOrInterfaceToTypeRef) {
    this.classOrInterfaceToTypeRef = classOrInterfaceToTypeRef;
  }

  @Override
  public TypeParamDef apply(TypeParameter typeParameter) {
    return new TypeParamDefBuilder().withName(typeParameter.getName()).withBounds(typeParameter.getTypeBound().stream()
        .map(b -> (ClassRef) classOrInterfaceToTypeRef.apply(b)).collect(Collectors.toList())).build();
  }
}
