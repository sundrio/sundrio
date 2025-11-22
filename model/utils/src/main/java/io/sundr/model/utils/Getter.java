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
import io.sundr.model.Field;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Return;
import io.sundr.model.RichTypeDef;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.VoidRef;
import io.sundr.model.functions.Assignable;

public class Getter {

  public static final String GET_PREFIX = "get";
  public static final String IS_PREFIX = "is";
  public static final String SHOULD_PREFIX = "should";
  public static final VoidRef VOID = new VoidRef();

  /**
   * Find the getter of the specified field in the type.
   *
   * @param clazz The class.
   * @param field The field.
   * @return The getter method if found. Throws exception if no getter is matched.
   */
  public static Method find(TypeDef clazz, Field field) {
    return find(clazz, field, false);
  }

  public static Optional<Method> findOptional(TypeDef clazz, Field field) {
    try {
      return Optional.of(find(clazz, field, false));
    } catch (SundrException e) {
      return Optional.empty();
    }
  }

  /**
   * Find the getter of the specified field in the type.
   *
   * @param clazz The class.
   * @param field The field.
   * @param acceptPrefixless Flag to accept prefixless getters.
   * @return The getter method if found. Throws exception if no getter is matched.
   */
  public static Method find(TypeDef clazz, Field field, boolean acceptPrefixless) {
    RichTypeDef richType = clazz instanceof RichTypeDef ? (RichTypeDef) clazz : TypeArguments.apply(clazz);
    //1st pass strict
    for (Method method : richType.getAllMethods()) {
      if ((Record.is(clazz) && isApplicable(method, field, true, true)) || (isApplicable(method, field, true, false))) {
        return method;
      }
    }
    //2nd pass relaxed
    for (Method method : richType.getAllMethods()) {
      if (isApplicable(method, field, false, false)) {
        return method;
      }
    }

    //3nd pass more relaxed
    if (acceptPrefixless) {
      for (Method method : richType.getAllMethods()) {
        if (isApplicable(method, field, false, true)) {
          return method;
        }
      }
    }
    throw new SundrException(
        "No getter found for field: [" + field.toString() + "] on class: " + clazz.getFullyQualifiedName()
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

    // Getters, should never ever throw exceptions. If they do their are not getters.
    // If we relax, this limitation, `throws` will leak into areas that is undesirable (usability & maintainance wise).
    if (method.getExceptions() != null && !method.getExceptions().isEmpty()) {
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

  private static boolean isApplicable(Method method, Field field) {
    return isApplicable(method, field, false, false);
  }

  /**
   * Returns true if method is a getter of field.
   * In strict mode it will not strip non-alphanumeric characters.
   */
  private static boolean isApplicable(Method method, Field field, boolean strict, boolean acceptPrefixless) {
    if (!Assignable.isAssignable(method.getReturnType()).from(field.getTypeRef())) {
      return false;
    }

    String capitalized = capitalizeFirst(field.getName());
    if (method.getName().endsWith(GET_PREFIX + capitalized)) {
      return true;
    }

    if (method.getName().endsWith(IS_PREFIX + capitalized)) {
      return true;
    }

    if (method.getName().endsWith(SHOULD_PREFIX + capitalized)) {
      return true;
    }

    if (acceptPrefixless && method.getName().endsWith(field.getName())) {
      return true;
    }

    if (!strict) {
      if (method.getName().endsWith(GET_PREFIX + field.getNameCapitalized())) {
        return true;
      }

      if (method.getName().endsWith(IS_PREFIX + field.getNameCapitalized())) {
        return true;
      }

      //Some frameworks/tools consider valid getters cases like: get$ref() (e.g. jsonschema2pojo).
      if (method.getName().endsWith(GET_PREFIX + field.getName()) && !Character.isAlphabetic(field.getName().charAt(0))) {
        return true;
      }

      if (method.getName().endsWith(IS_PREFIX + field.getName()) && !Character.isAlphabetic(field.getName().charAt(0))) {
        return true;
      }

      if (method.getName().endsWith(SHOULD_PREFIX + field.getName())
          && !Character.isAlphabetic(field.getName().charAt(0))) {
        return true;
      }

    }
    return false;
  }

  public static final Method forField(Field field) {
    return new MethodBuilder()
        .withName(name(field))
        .withReturnType(field.getTypeRef())
        .withNewBlock()
        .addToStatements(Return.This().ref(field))
        .endBlock()
        .build();
  }

  /**
   * Return the getter name for the specified {@link Field}.
   *
   * @param field The field.
   * @return The name.
   */
  public static String name(Field field) {
    return prefix(field) + field.getNameCapitalized();
  }

  /**
   * Return the field name for the specified getter {@link Method method}.
   *
   * @param method The method.
   * @return The name.
   */
  public static String fieldName(Method method) {
    if (!is(method)) {
      throw new IllegalArgumentException("Method: " + method + " is not a real getter.");
    }
    String name = method.getName();

    if (name.startsWith(GET_PREFIX)) {
      name = name.substring(GET_PREFIX.length());
    } else if (name.startsWith(IS_PREFIX)) {
      name = name.substring(IS_PREFIX.length());
    } else if (name.startsWith(SHOULD_PREFIX)) {
      name = name.substring(SHOULD_PREFIX.length());
    } else {
      throw new IllegalStateException("Method: " + method + " is a getter but couldn't find a valid prefix.");
    }

    if (name.length() == 1) {
      return name.toLowerCase();
    }
    return name.substring(0, 1).toLowerCase() + name.substring(1);
  }

  /**
   * Return the field name for the specified getter {@link Method method}.
   * This method will not check if the method is an actual getter and will return the method name if not.
   *
   * @param method The method.
   * @return The name, or the method name if method is not a typical getter..
   */
  public static String fieldNameSafe(Method method) {
    String name = method.getName();

    if (name.startsWith(GET_PREFIX)) {
      name = name.substring(GET_PREFIX.length());
    } else if (name.startsWith(IS_PREFIX)) {
      name = name.substring(IS_PREFIX.length());
    } else if (name.startsWith(SHOULD_PREFIX)) {
      name = name.substring(SHOULD_PREFIX.length());
    }

    if (name.length() == 1) {
      return name.toLowerCase();
    }
    return name.substring(0, 1).toLowerCase() + name.substring(1);
  }

  public static String prefix(Field field) {
    return Types.isPrimitive(field.getTypeRef()) && Types.isBoolean(field.getTypeRef()) ? IS_PREFIX : GET_PREFIX;
  }
}
