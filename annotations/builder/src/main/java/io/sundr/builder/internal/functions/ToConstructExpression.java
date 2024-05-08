/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder.internal.functions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.sundr.model.Expression;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;
import io.sundr.model.ValueRef;
import io.sundr.utils.Strings;

public class ToConstructExpression implements Function<List<Expression>, Expression> {

  private final TypeDef typeDef;
  private final List<TypeRef> parameters;
  private final String staticFactoryMethod;

  public ToConstructExpression(TypeDef typeDef) {
    this(typeDef, Collections.emptyList(), null);
  }

  public ToConstructExpression(TypeDef typeDef, TypeRef parameter, String staticFactoryMethod) {
    this(typeDef, Arrays.asList(parameter), staticFactoryMethod);
  }

  public ToConstructExpression(TypeDef typeDef, TypeRef... parameters) {
    this(typeDef, Arrays.asList(parameters));
  }

  public ToConstructExpression(TypeDef typeDef, List<TypeRef> parameters) {
    this(typeDef, parameters, null);
  }

  public ToConstructExpression(TypeDef typeDef, List<TypeRef> parameters, String staticFactoryMethod) {
    this.typeDef = typeDef;
    this.parameters = parameters;
    this.staticFactoryMethod = staticFactoryMethod;
  }

  @Override
  public Expression apply(List<Expression> item) {
    if (Strings.isNullOrEmpty(staticFactoryMethod)) {
      return usingConstructor(item);
    }
    return usingStaticFactoryMethod(item);
  }

  private Expression usingStaticFactoryMethod(List<Expression> item) {
    int size = item != null ? item.size() : 0;
    checkFactoryMethodArguments(size);

    if (size == 0) {
      return Expression.call(typeDef, staticFactoryMethod);
    }
    return Expression.call(typeDef, staticFactoryMethod, item.stream().map(ValueRef::new).toArray(Expression[]::new));
  }

  private Expression usingConstructor(List<Expression> item) {
    int size = item != null ? item.size() : 0;
    checkConstructorArguments(size);

    if (size == 0) {
      return Expression.createNew(typeDef.toReference(parameters));
    }
    return Expression.createNew(typeDef.toReference(parameters), item.stream().map(ValueRef::new).toArray(Expression[]::new));
  }

  /**
   * Checks that a constructor with the required number of arguments is found.
   *
   * @param arguments
   */
  private void checkConstructorArguments(int arguments) {
    if (arguments == 0 && (typeDef.getConstructors() == null || typeDef.getConstructors().isEmpty())) {
      return;
    }

    for (Method m : typeDef.getConstructors()) {
      int a = m.getArguments() != null ? m.getArguments().size() : 0;
      if (a == arguments) {
        return;
      }
    }
    throw new IllegalArgumentException(
        "No constructor found for " + typeDef.getName() + " with " + arguments + " arguments. Found:"
            + typeDef.getConstructors().stream().map(Method::toString).collect(Collectors.joining("\n\t", "\t", "\n")));
  }

  /**
   * Checks that a factory method with the required number of arguments is found.
   *
   * @param arguments
   */
  private void checkFactoryMethodArguments(int arguments) {
    for (Method m : typeDef.getMethods()) {
      int a = m.getArguments() != null ? m.getArguments().size() : 0;
      if (m.getName().equals(staticFactoryMethod) && a == arguments && m.isStatic()) {
        return;
      }
    }
    throw new IllegalArgumentException("No static method found on " + typeDef.getName() + " with name " + staticFactoryMethod
        + " and " + arguments + " arguments.");
  }
}
