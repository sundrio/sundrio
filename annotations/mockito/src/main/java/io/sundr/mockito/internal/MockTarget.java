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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.sundr.model.Argument;
import io.sundr.model.ArgumentBuilder;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.utils.Strings;

/**
 * A type for which a mock DSL is generated, together with the generation options.
 */
public class MockTarget {

  private static final Set<String> NO_ARG_OBJECT_METHODS = new HashSet<>(Arrays.asList(
      "hashCode", "toString", "getClass", "clone", "finalize", "notify", "notifyAll"));

  private final TypeDef definition;
  private final String mockName;
  private final boolean verification;
  private final Map<String, List<Method>> methodsByName = new LinkedHashMap<>();
  private final Map<String, String> skippedMethods = new LinkedHashMap<>();
  private final Map<String, ClassRef> classTypeVarErasures = new LinkedHashMap<>();

  public MockTarget(TypeDef definition, String mockName, boolean verification) {
    this.definition = definition;
    this.mockName = mockName;
    this.verification = verification;
    for (TypeParamDef parameter : definition.getParameters()) {
      List<ClassRef> bounds = parameter.getBounds();
      classTypeVarErasures.put(parameter.getName(), bounds.isEmpty() ? TypeDef.OBJECT_REF : bounds.get(0));
    }
    index(definition);
  }

  public TypeDef getDefinition() {
    return definition;
  }

  public boolean isVerificationEnabled() {
    return verification;
  }

  /**
   * The reference to the mocked type used throughout the generated DSL. Type parameters are erased
   * to the raw type: the generated mock class is not itself generic, and methods that use a type
   * variable are not stubbable, so keeping {@code <T>} would only leave the type variable unresolved.
   */
  public ClassRef getTargetRef() {
    ClassRef ref = definition.toInternalReference();
    if (ref.getArguments().isEmpty()) {
      return ref;
    }
    return new ClassRefBuilder(ref).withArguments(Collections.emptyList()).build();
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
    Map<String, List<Method>> grouped = collectMethods(definition).stream()
        .filter(this::isStubbable)
        .map(this::eraseTypeVariables)
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
   * Collects the target's own methods together with every method inherited from a supertype,
   * substituting each supertype's type variables with the arguments the target binds them to (for
   * example {@code CrudRepo<Domain, String>} turns an inherited {@code List<T> findAll(List<ID>)}
   * into {@code List<Domain> findAll(List<String>)}). The source adapter only reports directly
   * declared methods, so without this an interface that inherits its whole surface (a Spring Data
   * repository, say) would generate an empty DSL. Methods overridden lower in the hierarchy win, and
   * {@link Object} is not traversed.
   */
  private List<Method> collectMethods(TypeDef definition) {
    Map<String, Method> bySignature = new LinkedHashMap<>();
    collectMethods(definition, Collections.emptyMap(), bySignature, new HashSet<>());
    return new ArrayList<>(bySignature.values());
  }

  private void collectMethods(TypeDef definition, Map<String, TypeRef> substitutions,
      Map<String, Method> bySignature, Set<String> visited) {
    if (definition == null || !visited.add(definition.getFullyQualifiedName())) {
      return;
    }
    for (Method method : definition.getMethods()) {
      Method substituted = substituteTypeVariables(method, substitutions);
      bySignature.putIfAbsent(signatureKey(substituted), substituted);
    }
    for (ClassRef superRef : supertypes(definition)) {
      TypeDef superDef = GetDefinition.of(superRef);
      if (superDef == null || TypeDef.OBJECT.getFullyQualifiedName().equals(superRef.getFullyQualifiedName())) {
        continue;
      }
      collectMethods(superDef, composeSubstitutions(superDef, superRef, substitutions), bySignature, visited);
    }
  }

  private static List<ClassRef> supertypes(TypeDef definition) {
    List<ClassRef> supertypes = new ArrayList<>(definition.getExtendsList());
    supertypes.addAll(definition.getImplementsList());
    return supertypes;
  }

  /**
   * Builds the type-variable bindings visible inside {@code superDef} when reached through
   * {@code superRef}: each of the supertype's parameters mapped to the matching argument in
   * {@code superRef}, with any argument that is itself a variable resolved through the caller's own
   * {@code outer} substitutions so bindings chain across several levels of inheritance.
   */
  private static Map<String, TypeRef> composeSubstitutions(TypeDef superDef, ClassRef superRef,
      Map<String, TypeRef> outer) {
    Map<String, TypeRef> result = new LinkedHashMap<>();
    List<TypeParamDef> parameters = superDef.getParameters();
    List<TypeRef> arguments = superRef.getArguments();
    for (int i = 0; i < parameters.size() && i < arguments.size(); i++) {
      result.put(parameters.get(i).getName(), resolve(arguments.get(i), outer));
    }
    return result;
  }

  private static TypeRef resolve(TypeRef type, Map<String, TypeRef> substitutions) {
    if (type instanceof TypeParamRef) {
      TypeRef bound = substitutions.get(((TypeParamRef) type).getName());
      return bound != null ? bound : type;
    }
    return type;
  }

  private static Method substituteTypeVariables(Method method, Map<String, TypeRef> substitutions) {
    if (substitutions.isEmpty()) {
      return method;
    }
    List<Argument> arguments = method.getArguments().stream()
        .map(argument -> new ArgumentBuilder(argument)
            .withTypeRef(substitute(argument.getTypeRef(), substitutions)).build())
        .collect(Collectors.toList());
    return new MethodBuilder(method)
        .withReturnType(substitute(method.getReturnType(), substitutions))
        .withArguments(arguments)
        .build();
  }

  private static TypeRef substitute(TypeRef type, Map<String, TypeRef> substitutions) {
    if (type instanceof TypeParamRef) {
      TypeRef bound = substitutions.get(((TypeParamRef) type).getName());
      return bound != null ? bound : type;
    }
    if (type instanceof ClassRef) {
      ClassRef classRef = (ClassRef) type;
      if (classRef.getArguments().isEmpty()) {
        return type;
      }
      List<TypeRef> arguments = classRef.getArguments().stream()
          .map(argument -> substitute(argument, substitutions))
          .collect(Collectors.toList());
      return new ClassRefBuilder(classRef).withArguments(arguments).build();
    }
    return type;
  }

  private static String signatureKey(Method method) {
    return method.getName() + method.getArguments().stream()
        .map(argument -> argument.getTypeRef().render())
        .collect(Collectors.joining(",", "(", ")"));
  }

  /**
   * Overloads share one stub builder, which requires a single return type. A parameter name whose
   * type differs across overloads is not a failure: its shared slot is widened to {@code Object}
   * (see {@code DslMethods.unionArguments}), so {@code withXxx(Object)}, {@code withXxxAny()} and
   * matchers all still work and the invoked overload is selected at runtime from the actual
   * arguments. Returns the reason a group genuinely cannot be unified, null otherwise.
   */
  private static String overloadIssue(List<Method> overloads) {
    if (overloads.size() == 1) {
      return null;
    }
    long returnTypes = overloads.stream().map(m -> m.getReturnType().render()).distinct().count();
    if (returnTypes > 1) {
      return "overloads with different return types are not supported yet";
    }
    return null;
  }

  /**
   * Whether the method is one of {@link Object}'s own methods, matched by name and signature. A
   * user-declared method that merely shares a name (for example {@code clone(String, Principal)} or
   * a two-argument {@code equals}) is not an {@link Object} method and stays stubbable; only the
   * real overrides ({@code clone()}, {@code equals(Object)}, {@code wait(...)}, and the other no-arg
   * ones) are skipped, since they cannot be meaningfully stubbed.
   */
  private static boolean isObjectMethod(Method method) {
    String name = method.getName();
    int arity = method.getArguments().size();
    if (NO_ARG_OBJECT_METHODS.contains(name)) {
      return arity == 0;
    }
    if ("equals".equals(name)) {
      return arity == 1;
    }
    if ("wait".equals(name)) {
      return arity <= 2;
    }
    return false;
  }

  private boolean isStubbable(Method method) {
    if (!method.getModifiers().isPublic() || method.getModifiers().isStatic() || method.getModifiers().isFinal()) {
      return false;
    }
    if (isObjectMethod(method)) {
      return false;
    }
    Map<String, ClassRef> erasures = erasuresFor(method);
    if (containsUnerasableTypeVariable(method.getReturnType(), erasures)) {
      return false;
    }
    for (Argument argument : method.getArguments()) {
      if (containsUnerasableTypeVariable(argument.getTypeRef(), erasures)) {
        return false;
      }
    }
    return true;
  }

  /**
   * The type-variable erasures usable when stubbing a method: the mocked type's own parameters plus
   * any method-level type variable that appears <em>only</em> in the return type. A free variable in
   * the return position erases safely to {@code Object} (the terminal is just {@code thenReturn}),
   * but one in an argument position cannot: the adapter discards the method's {@code <S extends T>}
   * clause, so the real bound (e.g. {@code save(S extends Domain)}) is unknown and erasing the
   * argument to {@code Object} would not compile against the bounded signature. Argument free
   * variables therefore stay unerasable, which makes {@link #isStubbable} skip the method.
   */
  private Map<String, ClassRef> erasuresFor(Method method) {
    Map<String, ClassRef> erasures = new LinkedHashMap<>(classTypeVarErasures);
    for (TypeParamDef parameter : method.getParameters()) {
      List<ClassRef> bounds = parameter.getBounds();
      erasures.put(parameter.getName(), bounds.isEmpty() ? TypeDef.OBJECT_REF : bounds.get(0));
    }
    Set<String> argumentVariables = new HashSet<>();
    for (Argument argument : method.getArguments()) {
      collectFreeVariables(argument.getTypeRef(), argumentVariables);
    }
    Set<String> returnVariables = new HashSet<>();
    collectFreeVariables(method.getReturnType(), returnVariables);
    for (String variable : returnVariables) {
      if (!argumentVariables.contains(variable)) {
        erasures.putIfAbsent(variable, TypeDef.OBJECT_REF);
      }
    }
    return erasures;
  }

  /**
   * Records every type-variable name in the type that is not a mocked-type parameter, recursing into
   * class-reference type arguments. These are the method-level (free) variables of the signature.
   */
  private void collectFreeVariables(TypeRef type, Set<String> names) {
    if (type instanceof TypeParamRef) {
      String name = ((TypeParamRef) type).getName();
      if (!classTypeVarErasures.containsKey(name)) {
        names.add(name);
      }
    } else if (type instanceof ClassRef) {
      for (TypeRef argument : ((ClassRef) type).getArguments()) {
        collectFreeVariables(argument, names);
      }
    }
  }

  /**
   * A type variable is unerasable when it is not among the given erasures: class parameters and the
   * method's own type parameters are erased to their bounds, but any other variable has no single
   * meaningful erasure and disqualifies the method.
   */
  private boolean containsUnerasableTypeVariable(TypeRef type, Map<String, ClassRef> erasures) {
    if (type instanceof TypeParamRef) {
      return !erasures.containsKey(((TypeParamRef) type).getName());
    }
    if (type instanceof ClassRef) {
      return ((ClassRef) type).getArguments().stream()
          .anyMatch(argument -> containsUnerasableTypeVariable(argument, erasures));
    }
    return false;
  }

  private Method eraseTypeVariables(Method method) {
    Map<String, ClassRef> erasures = erasuresFor(method);
    List<Argument> erasedArguments = method.getArguments().stream()
        .map(argument -> eraseArgument(argument, erasures))
        .collect(Collectors.toList());
    return new MethodBuilder(method)
        .withParameters(Collections.emptyList())
        .withReturnType(erase(method.getReturnType(), erasures))
        .withArguments(erasedArguments)
        .build();
  }

  /**
   * Erases an argument's type like {@link #erase}, additionally tagging it with
   * {@link Constants#TYPE_VARIABLE_ARG} when its declared type was a bare class type variable. The
   * type still erases to {@code Object} so the slot and mock invocation stay raw, but the tag lets
   * {@code DslMethods} re-expose the wither as generic so the call site infers the concrete type.
   */
  private Argument eraseArgument(Argument argument, Map<String, ClassRef> erasures) {
    TypeRef type = argument.getTypeRef();
    boolean classTypeVariable = type instanceof TypeParamRef
        && ((TypeParamRef) type).getDimensions() == 0
        && classTypeVarErasures.containsKey(((TypeParamRef) type).getName());
    ArgumentBuilder builder = new ArgumentBuilder(argument).withTypeRef(erase(type, erasures));
    if (classTypeVariable) {
      builder.addToAttributes(Constants.TYPE_VARIABLE_ARG, Boolean.TRUE);
    }
    return builder.build();
  }

  /**
   * Erases the type variables visible in a method: a bare variable becomes its bound (or
   * {@code Object}), and a class reference carrying such a variable among its arguments is erased to
   * its raw form, matching the raw target type the generated mock references.
   */
  private TypeRef erase(TypeRef type, Map<String, ClassRef> erasures) {
    if (type instanceof TypeParamRef) {
      TypeParamRef paramRef = (TypeParamRef) type;
      ClassRef erasure = erasures.get(paramRef.getName());
      if (erasure == null) {
        return type;
      }
      return new ClassRefBuilder(erasure).withDimensions(paramRef.getDimensions()).build();
    }
    if (type instanceof ClassRef) {
      ClassRef classRef = (ClassRef) type;
      boolean carriesVariable = classRef.getArguments().stream()
          .anyMatch(argument -> containsTypeVariable(argument, erasures));
      if (carriesVariable) {
        return new ClassRefBuilder(classRef).withArguments(Collections.emptyList()).build();
      }
    }
    return type;
  }

  private boolean containsTypeVariable(TypeRef type, Map<String, ClassRef> erasures) {
    if (type instanceof TypeParamRef) {
      return erasures.containsKey(((TypeParamRef) type).getName());
    }
    if (type instanceof ClassRef) {
      return ((ClassRef) type).getArguments().stream()
          .anyMatch(argument -> containsTypeVariable(argument, erasures));
    }
    return false;
  }
}
