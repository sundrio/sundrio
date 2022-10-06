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

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;
import io.sundr.model.WildcardRef;
import io.sundr.model.utils.TypeArguments;
import io.sundr.model.utils.Types;
import io.sundr.model.visitors.ApplyTypeParamMappingToTypeArguments;

/**
 * This function can be thought as {@link Types#isInstanceOf(TypeRef, TypeDef, Function)} with added bonus that generic
 * arguments
 * are resolved.
 * <p>
 * For example, when {@code TypeCast.to(Map<?,?>)} is called on {@code HashMap<String, Integer}, the result will be
 * {@code Optional.of(Map<String, Integer>)}.
 * <p>
 * This works also for complex hierarchies with non-trivial type argument substitutions.
 * <p>
 * Limitation: Arguments involving wildcards are currently not supported.
 */
public class TypeCast implements Function<TypeRef, Optional<ClassRef>> {

  private final ClassRef expectedType;

  private TypeCast(ClassRef expectedType) {
    this.expectedType = expectedType;
  }

  /**
   * Create the function which casts to the specified target type.
   *
   * @param expectedType
   *        The type to which to cast. It must not be an array, and all type arguments (if any) must be unbounded wildcards
   */
  public static TypeCast to(ClassRef expectedType) {
    assertNoArray(expectedType);
    assertAllArgumentsAreWildcards(expectedType);
    return new TypeCast(expectedType);
  }

  private static void assertNoArray(ClassRef expectedType) {
    if (expectedType.getDimensions() != 0) {
      throw new IllegalArgumentException("Arrays are not supported: " + expectedType);
    }
  }

  private static void assertAllArgumentsAreWildcards(ClassRef expectedType) {
    for (TypeRef argument : expectedType.getArguments()) {
      if (!(argument instanceof WildcardRef) || !((WildcardRef) argument).getBounds().isEmpty()) {
        throw new IllegalArgumentException("Argument " + argument + " is not an unbounded wildcard in " + expectedType);
      }
    }
  }

  /**
   * Perform the type cast, if possible.
   *
   * @param type
   *        The type which will be cast
   * @return If the type can be cast to target type, {@code Optional.of(targetType<...>)} is returned with the type arguments
   *         resolved. If the
   *         type cannot be cast, {@code Optional.empty()} is returned.
   * @throws IllegalStateException
   *         when the type implements or extends target type multiple times with different arguments. Currently, this may also
   *         apply to multiple inheritance of wildcard types, even if they were compatible.
   */
  @Override
  public Optional<ClassRef> apply(TypeRef type) {
    if (type instanceof ClassRef) {
      Set<ClassRef> types = findMatchingTypes((ClassRef) type).collect(Collectors.toSet());

      if (types.size() > 1) {
        throw new IllegalStateException("Type " + type +
            " extends or implements " + expectedType + " multiple times: "
            + types + ". This is not legal in Java");
      }

      return types.stream().findAny();
    } else {
      return Optional.empty();
    }
  }

  private Stream<ClassRef> findMatchingTypes(ClassRef type) {
    if (type.getFullyQualifiedName().equals(expectedType.getFullyQualifiedName())) {
      return Stream.of(type);
    }

    TypeDef definition = GetDefinition.of(type);
    Stream<ClassRef> supertypes = Stream.concat(
        definition.getImplementsList().stream(),
        definition.getExtendsList().stream());

    return supertypes
        // as a corner-case, java.lang.Object extends itself:
        .filter(supertype -> !type.equals(supertype))
        .flatMap(this::findMatchingTypes)
        .map(supertype -> bindArguments(type, supertype));
  }

  private ClassRef bindArguments(ClassRef type, ClassRef supertype) {
    Map<String, TypeRef> mappings = TypeArguments.getGenericArgumentsMappings(type);
    return new ClassRefBuilder(supertype)
        .accept(new ApplyTypeParamMappingToTypeArguments(mappings))
        .build();
  }
}
