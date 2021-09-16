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

package io.sundr.model.utils;

import static io.sundr.utils.Strings.capitalizeFirst;

import java.util.Optional;
import java.util.stream.Collectors;

import io.sundr.SundrException;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.RichTypeDef;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDef;
import io.sundr.model.VoidRef;
import io.sundr.model.functions.Assignable;

public class Getter {

  public static final String GET_PREFIX = "get";
  public static final String IS_PREFIX = "is";
  public static final String SHOULD_PREFIX = "should";
  public static final VoidRef VOID = new VoidRef();

  /**
   * Find the getter of the specified property in the type.
   * 
   * @param clazz The class.
   * @param property The property.
   * @return The getter method if found. Throws exception if no getter is matched.
   */
  public static Method find(TypeDef clazz, Property property) {
    return find(clazz, property, false);
  }

  public static Optional<Method> findOptional(TypeDef clazz, Property property) {
    try {
      return Optional.of(find(clazz, property, false));
    } catch (SundrException e) {
      return Optional.empty();
    }
  }

  /**
   * Find the getter of the specified property in the type.
   * 
   * @param clazz The class.
   * @param property The property.
   * @param acceptPrefixless Flag to accept prefixless getters.
   * @return The getter method if found. Throws exception if no getter is matched.
   */
  public static Method find(TypeDef clazz, Property property, boolean acceptPrefixless) {
    RichTypeDef richType = clazz instanceof RichTypeDef ? (RichTypeDef) clazz : TypeArguments.apply(clazz);
    //1st pass strict
    for (Method method : richType.getAllMethods()) {
      if ((Record.is(clazz) && isApplicable(method, property, true, true)) || (isApplicable(method, property, true, false))) {
        return method;
      }
    }
    //2nd pass relaxed
    for (Method method : richType.getAllMethods()) {
      if (isApplicable(method, property, false, false)) {
        return method;
      }
    }

    //3nd pass more relaxed
    if (acceptPrefixless) {
      for (Method method : richType.getAllMethods()) {
        if (isApplicable(method, property, false, true)) {
          return method;
        }
      }
    }
    throw new SundrException(
        "No getter found for property: [" + property.toString() + "] on class: " + clazz.getFullyQualifiedName()
            + ", getters found: ["
            + richType.getAllMethods().stream().filter(Getter::is).map(m -> m.getReturnType() + " " + m.getName())
                .collect(Collectors.joining(","))
            + "]");
  }

  /**
   * Checks if the specified method is a getter.
   * 
   * @param method The specified method.
   * @return True if getter, false otherwise.
   */
  public static boolean is(Method method) {
    return is(method, false);
  }

  /**
   * Checks if the specified method is a getter.
   * 
   * @param method The method.
   * @param acceptPrefixless Flag to enable support of prefixless getters.
   * @return
   */
  public static boolean is(Method method, boolean acceptPrefixless) {
    int length = method.getName().length();

    if (method.isPrivate() || method.isStatic()) {
      return false;
    }

    if (!method.getArguments().isEmpty()) {
      return false;
    }

    if (method.getReturnType().equals(VOID)) {
      return false;
    }

    if (acceptPrefixless) {
      return true;
    }

    if (method.getName().startsWith(GET_PREFIX)) {
      return length > GET_PREFIX.length();
    }

    if (method.getName().startsWith(IS_PREFIX) && Types.isBoolean(method.getReturnType())) {
      return length > IS_PREFIX.length();
    }

    if (method.getName().startsWith(SHOULD_PREFIX) && Types.isBoolean(method.getReturnType())) {
      return length > SHOULD_PREFIX.length();
    }
    return false;
  }

  private static boolean isApplicable(Method method, Property property) {
    return isApplicable(method, property, false, false);
  }

  /**
   * Returns true if method is a getter of property.
   * In strict mode it will not strip non-alphanumeric characters.
   */
  private static boolean isApplicable(Method method, Property property, boolean strict, boolean acceptPrefixless) {
    if (!Assignable.isAssignable(method.getReturnType()).from(property.getTypeRef())) {
      return false;
    }

    String capitalized = capitalizeFirst(property.getName());
    if (method.getName().endsWith(GET_PREFIX + capitalized)) {
      return true;
    }

    if (method.getName().endsWith(IS_PREFIX + capitalized)) {
      return true;
    }

    if (method.getName().endsWith(SHOULD_PREFIX + capitalized)) {
      return true;
    }

    if (acceptPrefixless && method.getName().endsWith(property.getName())) {
      return true;
    }

    if (!strict) {
      if (method.getName().endsWith(GET_PREFIX + property.getNameCapitalized())) {
        return true;
      }

      if (method.getName().endsWith(IS_PREFIX + property.getNameCapitalized())) {
        return true;
      }

      //Some frameworks/tools consider valid getters cases like: get$ref() (e.g. jsonschema2pojo).
      if (method.getName().endsWith(GET_PREFIX + property.getName()) && !Character.isAlphabetic(property.getName().charAt(0))) {
        return true;
      }

      if (method.getName().endsWith(IS_PREFIX + property.getName()) && !Character.isAlphabetic(property.getName().charAt(0))) {
        return true;
      }

      if (method.getName().endsWith(SHOULD_PREFIX + property.getName())
          && !Character.isAlphabetic(property.getName().charAt(0))) {
        return true;
      }

    }
    return false;
  }

  public static final Method forProperty(Property property) {
    return new MethodBuilder()
        .withName(name(property))
        .withReturnType(property.getTypeRef())
        .withNewBlock()
        .addToStatements(new StringStatement("return this." + property + ";"))
        .endBlock()
        .build();
  }

  /**
   * Return the getter name for the specified {@link Property}.
   * 
   * @param property The property.
   * @return The name.
   */
  public static String name(Property property) {
    return prefix(property) + property.getNameCapitalized();
  }

  /**
   * Return the property name for the specified getter {@link Method method}.
   * 
   * @param method The method.
   * @return The name.
   */
  public static String propertyName(Method method) {
    if (!is(method)) {
      throw new IllegalArgumentException("Method: " + method + " is not a real getter.");
    }
    String name = method.getName();

    if (name.startsWith(GET_PREFIX)) {
      return name.substring(GET_PREFIX.length());
    } else if (name.startsWith(IS_PREFIX)) {
      return name.substring(IS_PREFIX.length());
    } else if (name.startsWith(SHOULD_PREFIX)) {
      name = name.substring(SHOULD_PREFIX.length());
    } else {
      throw new IllegalStateException("Method: " + method + " is a getter but couldn't find a valid prefix.");
    }

    if (name.length() == 1) {
      return name.toUpperCase();
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  /**
   * Return the property name for the specified getter {@link Method method}.
   * This method will not check if the method is an actual getter and will return the method name if not.
   * 
   * @param method The method.
   * @return The name, or the method name if method is not a typical getter..
   */
  public static String propertyNameSafe(Method method) {
    if (!is(method)) {
      return method.getName();
    }
    String name = method.getName();

    if (name.startsWith(GET_PREFIX)) {
      return name.substring(GET_PREFIX.length());
    } else if (name.startsWith(IS_PREFIX)) {
      return name.substring(IS_PREFIX.length());
    } else if (name.startsWith(SHOULD_PREFIX)) {
      name = name.substring(SHOULD_PREFIX.length());
    }

    if (name.length() == 1) {
      return name.toUpperCase();
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  public static String prefix(Property property) {
    return Types.isPrimitive(property.getTypeRef()) && Types.isBoolean(property.getTypeRef()) ? IS_PREFIX : GET_PREFIX;
  }
}
