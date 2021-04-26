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

import io.sundr.codegen.utils.StringUtils;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class Construct implements Function<List<String>, String> {

  private final TypeDef typeDef;
  private final List<TypeRef> parameters;
  private final String staticFactoryMethod;

  public Construct(TypeDef typeDef) {
    this(typeDef, Collections.emptyList(), null);
  }

  public Construct(TypeDef typeDef, TypeRef parameter, String staticFactoryMethod) {
    this(typeDef, Arrays.asList(parameter), staticFactoryMethod);
  }

  public Construct(TypeDef typeDef, TypeRef... parameters) {
    this(typeDef, Arrays.asList(parameters));
  }

  public Construct(TypeDef typeDef, List<TypeRef> parameters) {
    this(typeDef, parameters, null);
  }

  public Construct(TypeDef typeDef, List<TypeRef> parameters, String staticFactoryMethod) {
    this.typeDef = typeDef;
    this.parameters = parameters;
    this.staticFactoryMethod = staticFactoryMethod;
  }

  @Override
  public String apply(List<String> item) {
    if (StringUtils.isNullOrEmpty(staticFactoryMethod)) {
      return usingConstructor(item);
    }
    return usingStaticFactoryMethod(item);
  }

  private String usingStaticFactoryMethod(List<String> item) {
    int size = item != null ? item.size() : 0;
    checkFactoryMethodArguments(size);

    if (size == 0) {
      return typeDef.getName() + "." + staticFactoryMethod + "()";
    }
    return typeDef.getName() + "." + staticFactoryMethod + "(" + item.stream().collect(Collectors.joining(", ")) + ")";
  }

  private String usingConstructor(List<String> item) {
    int size = item != null ? item.size() : 0;
    checkConstructorArguments(size);

    if (size == 0) {
      return "new " + typeDef.toReference(parameters) + "()";
    }

    return "new " + typeDef.toReference(parameters) + "(" + item.stream().collect(Collectors.joining(", ")) + ")";
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
    throw new IllegalArgumentException("No constructor found for " + typeDef.getName() + " with " + arguments + " arguments.");

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
