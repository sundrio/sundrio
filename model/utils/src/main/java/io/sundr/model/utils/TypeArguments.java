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

import java.util.Arrays;
import java.util.Collections;
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

  public static RichTypeDef apply(ClassRef classRef) {
    TypeDef definition = applyGenericArguments(classRef);
    return apply(definition);
  }

  public static RichTypeDef apply(TypeDef definition) {
    // resolve hierarchy
    final List<ClassRef> classRefs = definition.getExtendsList()
        .stream()
        .flatMap(s -> Stream.concat(Stream.of(s), applyGenericArguments(s).getExtendsList().stream()))
        .collect(Collectors.toList());

    final List<TypeDef> typeDefs = classRefs.stream().map(TypeArguments::applyGenericArguments).collect(Collectors.toList());

    // resolve properties
    final List<Property> allProperties = applyToProperties(definition);
    final List<Method> allMethods = applyToMethods(definition);
    final List<Method> allConstructors = applyToConstructors(definition);

    // handle attributes
    // final Map<AttributeKey, Object> allAttributes = typeDefs.stream().map(t -> t.getAttributes()).reduce(new HashMap(),
    //     (subtotal, element) -> {
    //       Map<AttributeKey, Object> result = new HashMap<>();
    //       result.putAll(subtotal);
    //       Stream.concat(subtotal.keySet().stream(), element.keySet().stream()).distinct().forEach(key -> {
    //         if (!result.containsKey(key)) {
    //           result.put(key, element.getOrDefault(key, subtotal.get(key)));
    //         } else if (key.getType().isArray() && String.class.equals(key.getType().getComponentType())) {
    //           result.putAll(mergeStringArrays(key, subtotal, element));
    //         } else {
    //           result.put(key, element.getOrDefault(key, subtotal.get(key)));
    //         }
    //       });
    //       return result;
    //     });

    // re-create TypeDef with all the needed information
    return new RichTypeDef(definition.getKind(), definition.getPackageName(),
        definition.getName(), definition.getComments(), definition.getAnnotations(), classRefs,
        definition.getImplementsList(), definition.getParameters(), definition.getProperties(), allProperties,
        definition.getConstructors(), allConstructors, definition.getMethods(), allMethods, definition.getOuterTypeName(),
        definition.getInnerTypes().stream().map(i -> TypeArguments.apply(i)).collect(Collectors.toList()),
        definition.getModifiers(), definition.getAttributes());
  }

  /**
   * Given a reference to a generic class, determine a mapping between generic arguments definitions and instantiations.
   * <p>
   * For example, given a definition of {@code interface Map<K,V> {...}} and a reference {@code Map<String,Integer>},
   * the mapping will be {@code {K -> String, V -> Integer}}.
   * <p>
   * Raw references, that is, references that do not contain generic arguments (like {@code Map}) are accepted and always return
   * an empty result.
   * <p>
   * However, if the reference does contain generic arguments, their count must be equal to the definition.
   *
   * @param ref The class reference to evaluate. The corresponding definition will be loaded using {@link GetDefinition}
   */
  public static Map<String, TypeRef> getGenericArgumentsMappings(ClassRef ref) {
    return getGenericArgumentsMappings(ref, GetDefinition.of(ref));
  }

  /**
   * Given a reference to a generic class, determine a mapping between generic arguments definitions and instantiations.
   * <p>
   * For example, given a definition of {@code interface Map<K,V> {...}} and a reference {@code Map<String,Integer>},
   * the mapping will be {@code {K -> String, V -> Integer}}.
   * <p>
   * Raw references, that is, references that do not contain generic arguments (like {@code Map}) are accepted and always return
   * an empty result.
   * <p>
   * However, if the reference does contain generic arguments, their count must be equal to the definition.
   *
   * @param ref The class reference to evaluate.
   * @param definition The corresponding definition
   */
  public static Map<String, TypeRef> getGenericArgumentsMappings(ClassRef ref, TypeDef definition) {
    List<TypeRef> arguments = ref.getArguments();
    if (arguments.isEmpty()) {
      return Collections.emptyMap();
    }

    List<TypeParamDef> parameters = definition.getParameters();
    if (parameters.size() != arguments.size()) {
      throw new IllegalStateException("Incompatible reference " + ref + " to " + definition);
    }

    Map<String, TypeRef> mappings = new HashMap<>();
    for (int i = 0; i < arguments.size(); i++) {
      String name = parameters.get(i).getName();
      TypeRef typeRef = arguments.get(i);
      mappings.put(name, typeRef);
    }

    return mappings;
  }

  private static TypeDef applyGenericArguments(ClassRef ref) {
    TypeDef definition = GetDefinition.of(ref);
    Map<String, TypeRef> mappings = getGenericArgumentsMappings(ref, definition);

    if (mappings.isEmpty()) {
      return definition;
    } else {
      return new TypeDefBuilder(definition)
          .accept(new ApplyTypeParamMappingToTypeArguments(mappings)) // existing type arguments must be handled before methods and properties
          .accept(new ApplyTypeParamMappingToProperty(mappings, ORIGINAL_TYPE_PARAMETER),
              new ApplyTypeParamMappingToMethod(mappings, ORIGINAL_TYPE_PARAMETER))
          .build();
    }
  }

  private static List<Property> applyToProperties(TypeDef definition) {
    return Stream
        .concat(definition.getProperties().stream(),
            definition.getExtendsList().stream().filter(INTERNAL_JDK.negate())
                .flatMap(e -> applyToProperties(applyGenericArguments(e)).stream()))
        .filter(Predicates.distinct(Property::withErasure)).collect(Collectors.toList());
  }

  private static List<Method> applyToMethods(TypeDef definition) {
    return Stream
        .concat(definition.getMethods().stream(),
            definition.getExtendsList().stream().filter(INTERNAL_JDK.negate())
                .flatMap(e -> applyToMethods(applyGenericArguments(e)).stream()))
        .filter(Predicates.distinct(m -> m.withErasure().getSignature())).collect(Collectors.toList());
  }

  private static List<Method> applyToConstructors(TypeDef definition) {
    return Stream.concat(definition.getConstructors().stream(),
        definition.getExtendsList().stream()
            .filter(INTERNAL_JDK.negate())
            .flatMap(e -> applyToConstructors(applyGenericArguments(e)).stream()))
        .collect(Collectors.toList());
  }

  private static Map<AttributeKey, Object> mergeStringArrays(AttributeKey<String[]> key, Map<AttributeKey, Object> subtotal,
      Map<AttributeKey, Object> element) {
    Map<AttributeKey, Object> result = new HashMap<>();
    result.put(key, Stream.concat(Arrays.stream((String[]) subtotal.getOrDefault(key, new String[0])),
        Arrays.stream((String[]) element.getOrDefault(key, new String[0]))).toArray(size -> new String[size]));
    return result;
  }
}
