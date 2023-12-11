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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.model.AttributeKey;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.ClassRefFluent;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.utils.Maps;

public class ApplyTypeParamMappingToTypeArguments implements Visitor<ClassRefFluent<?>> {
  private static final AttributeKey<TypeParamRef> ALREADY_REPLACED_KEY = new AttributeKey<>(
      ApplyTypeParamMappingToTypeArguments.class.getName() + ".ALREADY_REPLACED", boolean.class);

  private final Map<String, TypeRef> mappings;

  public ApplyTypeParamMappingToTypeArguments(String name, TypeRef typeRef) {
    this(Maps.create(name, typeRef));
  }

  public ApplyTypeParamMappingToTypeArguments(Map<String, TypeRef> mappings) {
    this.mappings = markTypeParamRefs(mappings);
  }

  @Override
  public void visit(ClassRefFluent<?> classRef) {
    if (classRef.hasAttributes() && classRef.getAttributes().remove(ALREADY_REPLACED_KEY) != null) {
      return;
    }
    List<TypeRef> arguments = new ArrayList<>();
    for (TypeRef arg : classRef.buildArguments()) {
      TypeRef mappedRef = arg;
      if (arg instanceof TypeParamRef) {
        TypeParamRef typeParamRef = (TypeParamRef) arg;
        TypeRef mapping = mappings.get(typeParamRef.getName());
        if (mapping != null) {
          mappedRef = mapping;
        }
      }
      arguments.add(mappedRef);
    }
    classRef.withArguments(arguments);
  }

  /**
   * This is to handle cyclic mappings, like {@code V -> List<V>}.
   * <p>
   * If unhandled, these would get expanded infinitely into {@code List<List<List<...>>>}.
   * <p>
   * To prevent this, each mapping target is marked with an attribute that stops further expansion. The attribute is removed in
   * {@link #visit(ClassRefFluent)}
   *
   * @param mappings
   *        Type argument replacement mappings
   * @return Mapping with values marked by an attribute
   */
  private static Map<String, TypeRef> markTypeParamRefs(Map<String, TypeRef> mappings) {
    Map<String, TypeRef> result = new HashMap<>(mappings.size());
    mappings.forEach((key, target) -> {
      if (target instanceof ClassRef) {
        target = new ClassRefBuilder((ClassRef) target).addToAttributes(ALREADY_REPLACED_KEY, true).build();
      }
      result.put(key, target);
    });
    return result;
  }
}
