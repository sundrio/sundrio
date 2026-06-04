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

package io.sundr.builder.internal.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.apt.AptContext;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

/**
 * Utilities for inspecting validation-related types using the sundrio model.
 */
public final class ValidationUtils {

  private static final String VALIDATION_ANNOTATION_FQN = "io.sundr.validation.annotations.Validation";

  private ValidationUtils() {
  }

  /**
   * Returns constructor parameters needed by enclosing classes of @Validation instance methods
   * targeting the given type. Discovers candidate classes via naming conventions and package scanning,
   * adapts them to the sundrio model, and inspects their methods and constructors.
   *
   * @param targetTypeFqn the fully qualified name of the validated type (e.g. "io.sundr.examples.Person")
   * @param aptContext provides access to the annotation processing environment and adapter layer
   * @return sorted list of constructor parameters as {@link Property} objects, empty if none needed
   */
  public static List<Property> getValidationConstructorParams(String targetTypeFqn, AptContext aptContext) {
    Elements elements = aptContext.getElements();
    if (elements.getTypeElement(VALIDATION_ANNOTATION_FQN) == null) {
      return Collections.emptyList();
    }

    List<Property> result = new ArrayList<>();
    Set<String> scanned = new HashSet<>();

    for (String candidateFqn : findValidationEnclosingClasses(targetTypeFqn, elements)) {
      if (!scanned.add(candidateFqn)) {
        continue;
      }
      TypeElement typeElement = elements.getTypeElement(candidateFqn);
      if (typeElement == null) {
        continue;
      }
      TypeDef enclosingTypeDef = Adapters.adaptType(typeElement, aptContext.getAdapterContext());
      if (hasInstanceValidationMethodsFor(enclosingTypeDef, targetTypeFqn)) {
        result.addAll(getRequiredConstructorParams(enclosingTypeDef));
      }
    }

    result.sort(Comparator.comparing(Property::getName));
    return result;
  }

  /**
   * Checks whether the given type definition contains non-static @Validation methods
   * whose first argument matches the specified target type.
   */
  static boolean hasInstanceValidationMethodsFor(TypeDef typeDef, String targetTypeFqn) {
    return typeDef.getMethods().stream()
        .filter(m -> !m.getModifiers().isStatic())
        .filter(m -> !m.getArguments().isEmpty())
        .filter(m -> m.getAnnotations().stream()
            .anyMatch(a -> a.getClassRef().getFullyQualifiedName().equals(VALIDATION_ANNOTATION_FQN)))
        .anyMatch(m -> {
          TypeRef firstArgType = m.getArguments().get(0).getTypeRef();
          return firstArgType instanceof ClassRef
              && ((ClassRef) firstArgType).getFullyQualifiedName().equals(targetTypeFqn);
        });
  }

  /**
   * Returns the constructor parameters from the first public constructor (with arguments)
   * of the given type, but only if the type has no public no-arg constructor.
   * Property names use the same {@code enclosingClassName_paramName} convention as
   * the generated ValidatorsBuilder fields.
   */
  static List<Property> getRequiredConstructorParams(TypeDef typeDef) {
    List<Method> publicConstructors = typeDef.getConstructors().stream()
        .filter(c -> c.getModifiers().isPublic())
        .collect(Collectors.toList());

    boolean hasNoArgCtor = publicConstructors.stream().anyMatch(c -> c.getArguments().isEmpty());
    if (hasNoArgCtor) {
      return Collections.emptyList();
    }

    String prefix = Character.toLowerCase(typeDef.getName().charAt(0)) + typeDef.getName().substring(1) + "_";

    return publicConstructors.stream()
        .findFirst()
        .map(ctor -> ctor.getArguments().stream()
            .map(arg -> Property.newProperty(arg.getTypeRef(), prefix + arg.getName()))
            .collect(Collectors.toList()))
        .orElse(Collections.emptyList());
  }

  private static List<String> findValidationEnclosingClasses(String targetTypeFqn, Elements elements) {
    List<String> candidates = new ArrayList<>();
    String pkg = targetTypeFqn.contains(".") ? targetTypeFqn.substring(0, targetTypeFqn.lastIndexOf('.')) : "";
    String simpleName = targetTypeFqn.contains(".") ? targetTypeFqn.substring(targetTypeFqn.lastIndexOf('.') + 1)
        : targetTypeFqn;

    String base = pkg.isEmpty() ? simpleName : pkg + "." + simpleName;
    candidates.add(base + "Validations");

    TypeElement targetTypeElement = elements.getTypeElement(targetTypeFqn);
    if (targetTypeElement != null) {
      Element pkgElement = targetTypeElement.getEnclosingElement();
      if (pkgElement != null && pkgElement.getKind() == ElementKind.PACKAGE) {
        for (Element sibling : pkgElement.getEnclosedElements()) {
          if (sibling.getKind().isClass()) {
            String siblingName = ((TypeElement) sibling).getQualifiedName().toString();
            if (siblingName.endsWith("Validations") || siblingName.endsWith("Validator")) {
              candidates.add(siblingName);
            }
          }
        }
      }
    }

    return candidates;
  }
}
