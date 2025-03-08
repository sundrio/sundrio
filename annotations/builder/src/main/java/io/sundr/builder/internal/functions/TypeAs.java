/*
 * Copyright 2015 The original authors.
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

package io.sundr.builder.internal.functions;

import static io.sundr.builder.internal.utils.BuilderUtils.*;
import static io.sundr.model.utils.Types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import io.sundr.FunctionFactory;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.model.*;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.utils.Collections;
import io.sundr.model.utils.Types;

public class TypeAs {
  @SuppressWarnings("unchecked")
  public static <T> Function<T, T> combine(final Function<T, T>... functions) {
    return item -> {
      T result = item;
      for (Function<T, T> f : functions) {
        result = f.apply(result);
      }
      return result;
    };
  }

  /**
   * Something to SomethingBuilder
   **/
  public static final Function<ClassRef, ClassRef> BUILDER_REF = item -> new ClassRefBuilder(item)
      .withFullyQualifiedName(item.getFullyQualifiedName() + "Builder")
      .withArguments(item.getArguments())
      .build();

  /**
   * Distance<Integer> to DistanceFluent<Integer, A>
   **/
  public static final Function<ClassRef, ClassRef> FLUENT_A_REF = item -> new ClassRefBuilder(item)
      .withFullyQualifiedName(item.getFullyQualifiedName() + "Fluent")
      .withArguments(appendNewGenericArgument(item))
      .build();

  /**
   * Distance<Integer> to DistanceFluent<Integer, ?>
   **/
  public static final Function<ClassRef, ClassRef> FLUENT_Q_REF = item -> new ClassRefBuilder(item)
      .withFullyQualifiedName(item.getFullyQualifiedName() + "Fluent")
      .addToArguments(Q)
      .build();

  public static final Function<ClassRef, ClassRef> FLUENT_REF = item -> {
    return new ClassRefBuilder(item).withFullyQualifiedName(item.getFullyQualifiedName() + "Fluent")
        .addToArguments(BUILDER_REF.apply(item))
        .build();
  };

  public static final Function<ClassRef, ClassRef> VISITABLE_BUILDER_REF = new Function<ClassRef, ClassRef>() {
    public ClassRef apply(ClassRef item) {
      ClassRef uwrapped = (ClassRef) combine(UNWRAP_OPTIONAL_OF, UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(item);
      if (uwrapped instanceof ClassRef) {
        uwrapped = new ClassRefBuilder((ClassRef) uwrapped).build();
      }
      if (Types.isAbstract(uwrapped) || GetDefinition.of(uwrapped).getKind() == Kind.INTERFACE) {
        WildcardRef wildcardRef = new WildcardRefBuilder().addToBounds(uwrapped).build();
        return BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(wildcardRef, Q);
      }
      return BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(uwrapped,
          BUILDER_REF.apply(uwrapped));
    }
  };

  public static final Function<ClassRef, ClassRef> VISITABLE_BUILDER_Q_REF = new Function<ClassRef, ClassRef>() {
    public ClassRef apply(ClassRef item) {
      ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(item);
      if (baseType instanceof ClassRef) {
        baseType = new ClassRefBuilder((ClassRef) baseType).build();
      }
      WildcardRef wildcardRef = new WildcardRefBuilder().addToBounds(baseType).build();
      return BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(wildcardRef, Q);
    }
  };

  static final Function<TypeDef, TypeDef> EDITABLE = item -> {
    List<TypeParamDef> parameters = new ArrayList<>(item.getParameters());
    return new TypeDefBuilder(item)
        .withKind(Kind.CLASS)
        .withNewModifiers().withPublic().endModifiers()
        .withName("Editable" + item.getName())
        .withParameters(parameters)
        .withExtendsList(item.toInternalReference())
        .withImplementsList(
            BuilderContextManager.getContext().getEditableInterface().toReference(BUILDER_REF.apply(item.toReference())))
        .withInnerTypes()
        .withProperties()
        .withMethods()
        .withConstructors()
        .build();

  };

  public static final Function<TypeRef, TypeRef> UNWRAP_ARRAY_OF = item -> {
    if (item instanceof PrimitiveRef) {
      return new PrimitiveRefBuilder((PrimitiveRef) item).withDimensions(0).build();
    } else if (item instanceof ClassRef) {
      return new ClassRefBuilder((ClassRef) item).withDimensions(0).build();
    } else if (item instanceof TypeParamRef) {
      return new TypeParamRefBuilder((TypeParamRef) item).withDimensions(0).build();
    } else {
      return item;
    }
  };

  //TODO: Need a home for: .withDefaultImplementation(Constants.ARRAY_LIST)
  private static final Function<TypeRef, TypeRef> LIST_OF = FunctionFactory.cache(Collections.LIST::toReference);

  public static final Function<TypeRef, TypeRef> ARRAY_AS_LIST = FunctionFactory
      .cache(item -> LIST_OF.apply(UNWRAP_ARRAY_OF.apply(item)));

  public static final Function<TypeRef, TypeRef> UNWRAP_COLLECTION_OF = type -> Collections.getCollectionElementType(type)
      .orElse(type);

  private static Optional<TypeRef> extractArgument(ClassRef classRef, Function<TypeRef, Boolean> typeCheckFn,
      int argumentIndex) {
    if (typeCheckFn.apply(classRef)) {
      if (classRef.getArguments().size() > argumentIndex) {
        return Optional.of(classRef.getArguments().get(argumentIndex));
      } else {
        // Fallback
        TypeDef typeDef = GetDefinition.of(classRef);
        return Stream
            .concat(typeDef.getExtendsList().stream(), typeDef.getImplementsList().stream())
            .map((ref) -> extractArgument(ref, typeCheckFn, argumentIndex))
            .reduce(Optional.empty(), (o, n) -> o.isPresent() ? o : n);
      }
    } else {
      return Optional.empty();
    }
  }

  public static final Function<TypeRef, TypeRef> UNWRAP_MAP_KEY_OF = type -> Collections.getMapKeyType(type).orElse(type);

  public static final Function<TypeRef, TypeRef> UNWRAP_MAP_VALUE_OF = type -> Collections.getMapValueType(type).orElse(type);

  public static final Function<TypeRef, TypeRef> UNWRAP_OPTIONAL_OF = type -> {
    if (type instanceof ClassRef) {
      ClassRef classRef = (ClassRef) type;

      Optional<TypeRef> genericOptional = extractArgument(classRef, Types::isOptional, 0);
      if (genericOptional.isPresent()) {
        return genericOptional.get();
      }

      if (Types.isOptionalInt(classRef)) {
        return new TypeDefBuilder().withPackageName("java.lang").withName("Integer").build().toReference();
      }

      if (Types.isOptionalLong(classRef)) {
        return new TypeDefBuilder().withPackageName("java.lang").withName("Long").build().toReference();
      }

      if (Types.isOptionalDouble(classRef)) {
        return new TypeDefBuilder().withPackageName("java.lang").withName("Double").build().toReference();
      }
    }
    return type;
  };

  public static final Function<TypeRef, TypeRef> BOXED_OF = FunctionFactory.cache(Types::box);

  static final Function<TypeRef, String> PARSER_OF = FunctionFactory.cache(type -> {
    int index = 0;
    for (TypeRef primitive : PRIMITIVE_TYPES) {
      if (primitive.equals(type)) {
        return BOXED_PARSE_METHOD[index];
      }
      index++;
    }
    return null;
  });

  static Function<TypeRef, TypeRef> ARRAY_OF = type -> {
    if (type instanceof ClassRef) {
      return new ClassRefBuilder((ClassRef) type)
          .withDimensions(1)
          .build();

    } else if (type instanceof PrimitiveRef) {
      return new PrimitiveRefBuilder((PrimitiveRef) type)
          .withDimensions(1)
          .build();

    } else if (type instanceof TypeParamRef) {
      return new TypeParamRefBuilder((TypeParamRef) type)
          .withDimensions(1)
          .build();
    } else {
      throw new IllegalStateException("A property type should be either class, primitive or param referemce.");
    }
  };
}
