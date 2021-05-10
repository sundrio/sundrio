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

package io.sundr.adapter.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.PrimitiveRefBuilder;
import io.sundr.model.TypeParamRefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.VoidRefBuilder;
import io.sundr.model.WildcardRefBuilder;

public class TypeToTypeRef implements Function<Type, TypeRef> {

  private final Set<Class> references;

  public TypeToTypeRef(Set<Class> references) {
    this.references = references;
  }

  @Override
  public TypeRef apply(Type item) {
    if (item == null) {
      return new VoidRefBuilder().build();
    } else if (item instanceof WildcardType) {
      return new WildcardRefBuilder().withBounds(Arrays.asList(((WildcardType) item).getLowerBounds()).stream()
          .map(t -> apply(t)).collect(Collectors.toList())).build();
    } else if (item instanceof TypeVariable) {
      return new TypeParamRefBuilder().withName(((TypeVariable) item).getName()).build();
    } else if (item instanceof GenericArrayType) {
      Type target = item;
      int dimensions = 0;
      while (target instanceof GenericArrayType) {
        target = ((GenericArrayType) target).getGenericComponentType();
        dimensions++;
      }
      if (target instanceof Class) {
        references.add((Class) target);
      }
      TypeRef targetRef = apply(target);
      return targetRef.withDimensions(dimensions + targetRef.getDimensions());

    } else if (item instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) item;
      Type rawType = parameterizedType.getRawType();
      List<TypeRef> arguments = new ArrayList<TypeRef>();
      for (Type arg : parameterizedType.getActualTypeArguments()) {
        arguments.add(apply(arg));
        if (arg instanceof Class) {
          references.add((Class) arg);
        }
      }
      if (rawType instanceof Class) {
        references.add((Class) rawType);
      }
      return new ClassRefBuilder((ClassRef) apply(rawType))
          .withArguments(arguments)
          .build();
    } else if (Object.class.equals(item)) {
      return ClassRef.OBJECT;
    } else if (item instanceof Class) {
      Class c = (Class) item;
      if (c.isArray()) {
        Class target = c;
        int dimensions = 0;
        while (target.isArray()) {
          target = ((Class) target).getComponentType();
          dimensions++;
        }
        TypeRef targetRef = apply(target);
        references.add(target);
        return targetRef.withDimensions(dimensions + targetRef.getDimensions());
      }

      if (c.isPrimitive()) {
        return new PrimitiveRefBuilder().withName(c.getName()).withDimensions(0).build();
      } else {
        List<TypeRef> arguments = new ArrayList<TypeRef>();
        for (TypeVariable v : c.getTypeParameters()) {
          arguments.add(apply(v));
        }
        references.add((Class) item);
        String fqcn = c.getName().replaceAll(Pattern.quote("$"), ".");
        return new ClassRefBuilder()
            .withFullyQualifiedName(fqcn)
            .withArguments(arguments)
            .build();
      }
    }
    throw new IllegalArgumentException("Can't convert type:" + item + " to a TypeRef");
  }
}
