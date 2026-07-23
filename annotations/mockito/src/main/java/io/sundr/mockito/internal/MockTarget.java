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

package io.sundr.mockito.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.sundr.model.Argument;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.utils.Strings;

/**
 * A type for which a mock DSL is generated, together with the generation options.
 */
public class MockTarget {

  private static final Set<String> OBJECT_METHODS = new HashSet<>(Arrays.asList(
      "equals", "hashCode", "toString", "getClass", "clone", "finalize", "wait", "notify", "notifyAll"));

  private final TypeDef definition;
  private final String mockName;
  private final boolean verification;
  private final Map<String, List<Method>> methodsByName = new LinkedHashMap<>();
  private final Map<String, String> skippedMethods = new LinkedHashMap<>();

  public MockTarget(TypeDef definition, String mockName, boolean verification) {
    this.definition = definition;
    this.mockName = mockName;
    this.verification = verification;
    index(definition);
  }

  public TypeDef getDefinition() {
    return definition;
  }

  public boolean isVerificationEnabled() {
    return verification;
  }

  public ClassRef getTargetRef() {
    return definition.toInternalReference();
  }

  public String getMockName() {
    return mockName;
  }

  public String getMockFqcn() {
    String pkg = definition.getPackageName();
    return pkg == null || pkg.isEmpty() ? getMockName() : pkg + "." + getMockName();
  }

  public ClassRef getMockRef() {
    return ClassRef.forName(getMockFqcn());
  }

  /**
   * Returns a reference to a class nested inside the generated mock DSL class.
   *
   * @param simpleName the simple name of the nested class.
   * @return the fully qualified reference to the nested class.
   */
  public ClassRef nestedRef(String simpleName) {
    return ClassRef.forName(getMockFqcn() + "." + simpleName);
  }

  public String getPackageName() {
    return definition.getPackageName();
  }

  /**
   * Returns the stubbable methods grouped by name, in declaration order.
   * Overloads share one DSL name; the overload to stub is selected at runtime from the
   * pinned arguments.
   *
   * @return the stubbable methods by name.
   */
  public Map<String, List<Method>> getMethodsByName() {
    return methodsByName;
  }

  /**
   * Returns the method names no DSL is generated for, mapped to the reason.
   *
   * @return the skipped method names and reasons.
   */
  public Map<String, String> getSkippedMethods() {
    return skippedMethods;
  }

  /**
   * Returns true when the target's method parameter names look like the {@code argN}
   * placeholders javac assigns to binaries compiled without {@code -parameters}.
   *
   * @return true if all stubbable-method parameter names are synthetic.
   */
  public boolean hasSyntheticParameterNames() {
    List<Argument> arguments = methodsByName.values().stream()
        .flatMap(List::stream)
        .flatMap(m -> m.getArguments().stream())
        .collect(Collectors.toList());
    return !arguments.isEmpty() && arguments.stream().allMatch(a -> a.getName().matches("arg\\d+"));
  }

  public static String stubClassName(String methodName) {
    return Strings.capitalizeFirst(methodName) + "Stub";
  }

  public static String verifyClassName(String methodName) {
    return Strings.capitalizeFirst(methodName) + "Verify";
  }

  public static String doStubClassName(String methodName) {
    return Strings.capitalizeFirst(methodName) + "DoStub";
  }

  /**
   * The answer-first router's simple name is prefixed with the mock name so that, referenced
   * from the aggregator across packages, no two routers import to the same leaf name.
   */
  public String doRouterName() {
    return mockName + "DoStub";
  }

  /**
   * The do-family stubber's simple name is prefixed with the mock name for the same
   * cross-package leaf-name uniqueness the router needs.
   */
  public String doStubberName() {
    return mockName + "DoStubber";
  }

  private void index(TypeDef definition) {
    Map<String, List<Method>> grouped = definition.getMethods().stream()
        .filter(MockTarget::isStubbable)
        .collect(Collectors.groupingBy(Method::getName, LinkedHashMap::new, Collectors.toList()));

    for (Map.Entry<String, List<Method>> entry : grouped.entrySet()) {
      String issue = overloadIssue(entry.getValue());
      if (issue == null) {
        methodsByName.put(entry.getKey(), entry.getValue());
      } else {
        skippedMethods.put(entry.getKey(), issue);
      }
    }
  }

  /**
   * Overloads share one stub builder, which requires a single return type and a single
   * type per parameter name. Returns the reason a group cannot be unified, null otherwise.
   */
  private static String overloadIssue(List<Method> overloads) {
    if (overloads.size() == 1) {
      return null;
    }
    long returnTypes = overloads.stream().map(m -> m.getReturnType().render()).distinct().count();
    if (returnTypes > 1) {
      return "overloads with different return types are not supported yet";
    }
    Map<String, String> typeByName = new LinkedHashMap<>();
    for (Method method : overloads) {
      for (Argument argument : method.getArguments()) {
        String type = argument.getTypeRef().render();
        String existing = typeByName.putIfAbsent(argument.getName(), type);
        if (existing != null && !existing.equals(type)) {
          return "parameter '" + argument.getName() + "' has conflicting types across overloads";
        }
      }
    }
    return null;
  }

  private static boolean isStubbable(Method method) {
    if (method.getModifiers().isStatic() || method.getModifiers().isPrivate() || method.getModifiers().isFinal()) {
      return false;
    }
    if (OBJECT_METHODS.contains(method.getName())) {
      return false;
    }
    if (!method.getParameters().isEmpty()) {
      return false;
    }
    if (containsTypeVariable(method.getReturnType())) {
      return false;
    }
    for (Argument argument : method.getArguments()) {
      if (containsTypeVariable(argument.getTypeRef())) {
        return false;
      }
    }
    return true;
  }

  private static boolean containsTypeVariable(TypeRef type) {
    if (type instanceof TypeParamRef) {
      return true;
    }
    if (type instanceof ClassRef) {
      return ((ClassRef) type).getArguments().stream().anyMatch(MockTarget::containsTypeVariable);
    }
    return false;
  }
}
