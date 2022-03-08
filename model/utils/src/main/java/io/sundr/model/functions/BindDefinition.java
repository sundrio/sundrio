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

package io.sundr.model.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.sundr.builder.Visitor;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.MethodBuilder;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;

/**
 * Binds the {@link ClassRef} arguments to its {@link TypeDef} parameters.
 * In other words it creates a non-parameterized version of the {@link TypeDef}.
 */
public class BindDefinition implements Function<ClassRef, TypeDef> {

  public static BindDefinition FUNCTION = new BindDefinition();

  public static TypeDef of(ClassRef classRef) {
    return FUNCTION.apply(classRef);
  }

  @Override
  public TypeDef apply(ClassRef t) {
    List<TypeRef> arguments = t.getArguments();
    TypeDef definition = GetDefinition.of(t);
    if (arguments.isEmpty()) {
      return definition;
    }
    List<TypeParamDef> parameters = definition.getParameters();
    List<TypeParamDef> newParameters = definition.getParameters();

    Map<String, TypeRef> mappings = new HashMap<>();
    for (int i = 0; i < arguments.size(); i++) {
      String name = parameters.get(i).getName();
      TypeRef typeRef = arguments.get(i);
      mappings.put(name, typeRef);
      newParameters.remove(i);
    }

    return new TypeDefBuilder(definition)
        .withParameters(newParameters)
        .accept(mapClassRefArguments(mappings), mapGenericProperties(mappings), mapGenericReturnTypes(mappings))
        .build();
  }

  /**
   * Map generic properties to known {@link TypeRef} based on the specified mappings.
   * Example: Given a property {@code T size} and a map containing {@code T -> Integer} the final
   * property will be: {@code Integer type}.
   * 
   * @param mappings A map that maps class arguments names to types.
   * @return a visitors that performs the actual mapping.
   */
  private static Visitor<PropertyBuilder> mapGenericProperties(Map<String, TypeRef> mappings) {
    return new Visitor<PropertyBuilder>() {
      @Override
      public void visit(PropertyBuilder property) {
        TypeRef typeRef = property.buildTypeRef();
        if (typeRef instanceof TypeParamRef) {
          TypeParamRef typeParamRef = (TypeParamRef) typeRef;
          String key = typeParamRef.getName();
          TypeRef paramRef = mappings.get(key);
          if (paramRef != null) {
            property.withTypeRef(paramRef);
          }
        }
      }
    };
  }

  /**
   * Map generic properties to known {@link TypeRef} based on the specified mappings.
   * Example: Given a property {@code T size} and a map containing {@code T -> Integer} the final
   * property will be: {@code Integer type}.
   * 
   * @param mappings A map that maps class arguments names to types.
   * @return a visitors that performs the actual mapping.
   */
  private static Visitor<MethodBuilder> mapGenericReturnTypes(Map<String, TypeRef> mappings) {
    return new Visitor<MethodBuilder>() {
      @Override
      public void visit(MethodBuilder method) {
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
    };
  }

  /**
   * Map arguments, of {@link ClassRef} instances to known {@link TypeRef} based on the specified mappings.
   * Example: Given a class reference to {@code Shape<T>} and a map containing {@code T -> Integer}
   * the final reference will be: {@code Shape<Integer> type}.
   * 
   * @param mappings A map that maps class arguments names to types.
   * @return a visitors that performs the actual mapping.
   */
  private static Visitor<ClassRefBuilder> mapClassRefArguments(Map<String, TypeRef> mappings) {
    return new Visitor<ClassRefBuilder>() {
      @Override
      public void visit(ClassRefBuilder c) {
        List<TypeRef> arguments = new ArrayList<>();
        for (TypeRef arg : c.buildArguments()) {
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
        c.withArguments(arguments);
      }
    };
  }
}
