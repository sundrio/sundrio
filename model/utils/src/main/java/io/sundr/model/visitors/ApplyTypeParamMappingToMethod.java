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

package io.sundr.model.visitors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import io.sundr.builder.Visitor;
import io.sundr.model.AttributeKey;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.MethodFluent;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.utils.Maps;

public class ApplyTypeParamMappingToMethod implements Visitor<MethodFluent<?>> {

  private final Map<String, TypeRef> mappings;
  private final Optional<AttributeKey<TypeParamRef>> attributeKey;

  public ApplyTypeParamMappingToMethod(String name, TypeRef typeRef) {
    this(Maps.create(name, typeRef));
  }

  public ApplyTypeParamMappingToMethod(Map<String, TypeRef> mappings) {
    this(mappings, Optional.empty());
  }

  public ApplyTypeParamMappingToMethod(Map<String, TypeRef> mappings, AttributeKey<TypeParamRef> attributeKey) {
    this(mappings, Optional.ofNullable(attributeKey));
  }

  public ApplyTypeParamMappingToMethod(Map<String, TypeRef> mappings, Optional<AttributeKey<TypeParamRef>> attributeKey) {
    this.mappings = mappings;
    this.attributeKey = attributeKey;
  }

  @Override
  public void visit(MethodFluent<?> method) {
    TypeRef typeRef = method.buildReturnType();
    if (typeRef instanceof TypeParamRef) {
      TypeParamRef typeParamRef = (TypeParamRef) typeRef;
      String key = typeParamRef.getName();
      TypeRef paramRef = mappings.get(key);
      if (paramRef != null) {
        method.withReturnType(paramRef);
        attributeKey.ifPresent(k -> method.addToAttributes(k, typeParamRef));
      }
    } else if (typeRef instanceof ClassRef) {
      ClassRef classRef = (ClassRef) typeRef;
      if (classRef.getArguments().stream().anyMatch(a -> a instanceof TypeParamRef)) {
        List<TypeRef> mappedArguments = classRef.getArguments().stream()
            .map(a -> a instanceof TypeParamRef ? mappings.getOrDefault(((TypeParamRef) a).getName(), a) : a)
            .collect(Collectors.toList());
        method.withReturnType(new ClassRefBuilder(classRef).withArguments(mappedArguments).build());
        attributeKey.ifPresent(k -> method.addToAttributes(k, classRef));
      }
    }
  }
}
