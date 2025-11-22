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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import io.sundr.SundrException;
import io.sundr.model.Field;
import io.sundr.model.Method;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.Variable;
import io.sundr.model.repo.DefinitionRepository;

public class Setter {

  /**
   * Find the setter of the specified field in the type.
   *
   * @param type The class.
   * @param field The field.
   * @return The setter method if found. Throws exception if no setter is matched.
   */
  public static Method find(TypeDef type, Field field) {
    TypeDef current = type;
    while (current != null && !current.equals(TypeDef.OBJECT)) {
      // Sort methods by method name length.
      List<Method> sortedMethods = current.getMethods()
          .stream()
          .sorted(Comparator.comparingInt(m -> m.getName().length()))
          .collect(Collectors.toList());

      //1st pass strict
      for (Method method : sortedMethods) {
        if (isApplicable(method, field, true)) {
          return method;
        }
      }
      //2nd pass relaxed
      for (Method method : sortedMethods) {
        if (isApplicable(method, field, false)) {
          return method;
        }
      }

      if (!current.getExtendsList().iterator().hasNext()) {
        break;
      }
      String fqn = current.getExtendsList().iterator().next().getFullyQualifiedName();
      current = DefinitionRepository.getRepository().getDefinition(fqn);
    }
    throw new SundrException(
        "No setter found for field: " + field.getName() + " on class: " + type.getFullyQualifiedName());
  }

  /**
   * Find the setter of the specified field in the rich type.
   *
   * @param richType The class.
   * @param field The field.
   * @return The setter method if found. Throws exception if no setter is matched.
   */
  public static Method find(RichTypeDef richType, Field field) {
    // Sort methods by method name length.
    List<Method> sortedMethods = richType.getAllMethods()
        .stream()
        .sorted(Comparator.comparingInt(m -> m.getName().length()))
        .collect(Collectors.toList());

    //1st pass strict
    for (Method method : sortedMethods) {
      if (isApplicable(method, field, true)) {
        return method;
      }
    }
    //2nd pass relaxed
    for (Method method : sortedMethods) {
      if (isApplicable(method, field, false)) {
        return method;
      }
    }
    throw new SundrException(
        "No setter found for field: " + field.getName() + " on class: " + richType.getFullyQualifiedName());
  }

  public static boolean has(TypeDef clazz, Field field) {
    for (Method method : clazz.getMethods()) {
      if (isApplicable(method, field)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isApplicable(Method method, Variable<?> argument) {
    return isApplicable(method, argument, false);
  }

  /**
   * Returns true if method is a setter of field.
   * In strict mode it will not strip non-alphanumeric characters.
   */
  private static boolean isApplicable(Method method, Variable<?> argument, boolean strict) {
    if (method.getArguments().size() != 1) {
      return false;
    }

    if (!method.getArguments().get(0).getTypeRef().equals(argument.getTypeRef())) {
      return false;
    }

    String capitalized = capitalizeFirst(argument.getName());
    if (method.getName().endsWith("set" + capitalized)) {
      return true;
    }

    if (!strict && method.getName().endsWith("set" + argument.getNameCapitalized())) {
      return true;
    }
    return false;
  }

  public static boolean hasOrInherits(RichTypeDef clazz, Field field) {
    for (Method method : clazz.getAllMethods()) {
      if (isApplicable(method, field)) {
        return true;
      }
    }
    return false;
  }

  public static boolean hasOrInherits(TypeDef clazz, Field field) {
    TypeDef current = clazz;
    //Iterate parent objects and check for properties with setters but not ctor arguments.
    while (current != null && !current.equals(TypeDef.OBJECT)) {
      for (Method method : current.getMethods()) {
        if (isApplicable(method, field)) {
          return true;
        }
      }

      if (!current.getExtendsList().isEmpty()) {
        String fqn = current.getExtendsList().iterator().next().getFullyQualifiedName();
        current = DefinitionRepository.getRepository().getDefinition(fqn);
      } else {
        current = null;
      }
    }
    return false;
  }
}
