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

import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.PrimitiveRefBuilder;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeParamRefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.VoidRef;
import io.sundr.model.WildcardRef;

public class TypeToTypeRef implements Function<Type, TypeRef> {

  private final Function<ClassOrInterfaceType, TypeRef> classOrInterfaceToTypeRef;

  public TypeToTypeRef(Function<ClassOrInterfaceType, TypeRef> classOrInterfaceToTypeRef) {
    this.classOrInterfaceToTypeRef = classOrInterfaceToTypeRef;
  }

  @Override
  public TypeRef apply(Type type) {
    if (type instanceof VoidType) {
      return new VoidRef();
    } else if (type instanceof WildcardType) {
      return new WildcardRef();
    } else if (type instanceof ReferenceType) {
      ReferenceType referenceType = (ReferenceType) type;
      int dimensions = referenceType.getArrayCount();
      TypeRef typeRef = apply(referenceType.getType());
      if (dimensions == 0) {
        return typeRef;
      } else if (typeRef instanceof ClassRef) {
        return new ClassRefBuilder((ClassRef) typeRef).withDimensions(dimensions).build();
      } else if (typeRef instanceof PrimitiveRef) {
        return new PrimitiveRefBuilder((PrimitiveRef) typeRef).withDimensions(dimensions).build();
      } else if (typeRef instanceof TypeParamRef) {
        return new TypeParamRefBuilder((TypeParamRef) typeRef).withDimensions(dimensions).build();
      }
    } else if (type instanceof PrimitiveType) {
      PrimitiveType primitiveType = (PrimitiveType) type;
      return new PrimitiveRefBuilder().withName(primitiveType.getType().name()).build();
    } else if (type instanceof ClassOrInterfaceType) {
      return classOrInterfaceToTypeRef.apply((ClassOrInterfaceType) type);
    }
    throw new IllegalArgumentException("Can't handle type:[" + type + "].");
  }

}
