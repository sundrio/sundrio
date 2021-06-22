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

import java.util.Map;

import io.sundr.builder.TypedVisitor;
import io.sundr.model.MethodFluent;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.utils.Maps;

public class ApplyTypeParamMappingToMethod extends TypedVisitor<MethodFluent<?>> {

  private final Map<String, TypeRef> mappings;

  public ApplyTypeParamMappingToMethod(String name, TypeRef typeRef) {
    this(Maps.create(name, typeRef));
  }

  public ApplyTypeParamMappingToMethod(Map<String, TypeRef> mappings) {
    this.mappings = mappings;
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
      }
    }
  }
}
