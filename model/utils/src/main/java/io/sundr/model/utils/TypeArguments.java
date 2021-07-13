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

package io.sundr.model.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.model.AttributeKey;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.visitors.ApplyTypeParamMappingToMethod;
import io.sundr.model.visitors.ApplyTypeParamMappingToProperty;
import io.sundr.model.visitors.ApplyTypeParamMappingToTypeArguments;
import io.sundr.utils.Predicates;

public class TypeArguments {

  public static final AttributeKey<TypeParamRef> ORIGINAL_TYPE_PARAMETER = new AttributeKey<>("ORIGINAL_TYPE_PARAMETER",
      String.class);

  private static final Predicate<ClassRef> INTERNAL_JDK = c -> c.equals(Types.OBJECT_REF)
      || c.getFullyQualifiedName().startsWith("jdk.internal");

  public static RichTypeDef apply(TypeDef definition) {
    // resolve hierarchy
    final List<ClassRef> classRefs = definition.getExtendsList()
        .stream()
        .flatMap(s -> Stream.concat(Stream.of(s), apply(s).getExtendsList().stream()))
        .collect(Collectors.toList());

    // resolve properties
    final List<Property> allProperties = applyToProperties(definition);
    final List<Method> allMethods = applyToMethods(definition);
    final List<Method> allConstructors = applyToConstructors(definition);

    // re-create TypeDef with all the needed information
    return new RichTypeDef(definition.getKind(), definition.getPackageName(),
        definition.getName(), definition.getComments(), definition.getAnnotations(), classRefs,
        definition.getImplementsList(), definition.getParameters(), definition.getProperties(), allProperties,
        definition.getConstructors(), allConstructors, definition.getMethods(), allMethods, definition.getOuterTypeName(),
        definition.getInnerTypes().stream().map(i -> TypeArguments.apply(i)).collect(Collectors.toList()),
        definition.getModifiers(), definition.getAttributes());
  }

  private static TypeDef apply(ClassRef ref) {
    List<TypeRef> arguments = ref.getArguments();
    TypeDef definition = GetDefinition.of(ref);
    if (arguments.isEmpty()) {
      return definition;
    }

    List<TypeParamDef> parameters = definition.getParameters();
    Map<String, TypeRef> mappings = new HashMap<>();
    for (int i = 0; i < arguments.size(); i++) {
      String name = parameters.get(i).getName();
      TypeRef typeRef = arguments.get(i);
      mappings.put(name, typeRef);
    }

    return new TypeDefBuilder(definition)
        .accept(new ApplyTypeParamMappingToProperty(mappings, ORIGINAL_TYPE_PARAMETER),
            new ApplyTypeParamMappingToMethod(mappings, ORIGINAL_TYPE_PARAMETER),
            new ApplyTypeParamMappingToTypeArguments(mappings))
        .build();
  }

  private static List<Property> applyToProperties(TypeDef definition) {
    return Stream
        .concat(definition.getProperties().stream(),
            definition.getExtendsList().stream().filter(INTERNAL_JDK.negate())
                .flatMap(e -> applyToProperties(apply(e)).stream()))
        .filter(Predicates.distinct(Property::withErasure)).collect(Collectors.toList());
  }

  private static List<Method> applyToMethods(TypeDef definition) {
    return Stream
        .concat(definition.getMethods().stream(),
            definition.getExtendsList().stream().filter(INTERNAL_JDK.negate()).flatMap(e -> applyToMethods(apply(e)).stream()))
        .filter(Predicates.distinct(m -> m.withErasure().getSignature())).collect(Collectors.toList());
  }

  private static List<Method> applyToConstructors(TypeDef definition) {
    return Stream.concat(definition.getConstructors().stream(),
        definition.getExtendsList().stream()
            .filter(INTERNAL_JDK.negate())
            .flatMap(e -> applyToConstructors(apply(e)).stream()))
        .collect(Collectors.toList());
  }
}
