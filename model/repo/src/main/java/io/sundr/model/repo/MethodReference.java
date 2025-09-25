/*
 * Copyright 2016 The original authors.
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

package io.sundr.model.repo;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import io.sundr.builder.Visitor;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.Expression;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.MethodCall;
import io.sundr.model.MethodCallBuilder;
import io.sundr.model.MethodCallFluent;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;

/**
 * Represents a method reference along with its owning TypeDef.
 * Provides functionality to analyze method dependencies and find all method references
 * that are called directly or transitively from a given method.
 */
public class MethodReference {

  // Cache for method resolution to improve performance
  private static final Map<String, Set<MethodReference>> RESULTION_CACHE = new ConcurrentHashMap<>();

  private final Method method;
  private final TypeDef owningType;

  /**
   * Visitor that collects all MethodCall instances found during AST traversal.
   */
  private static class MethodCallCollector implements Visitor<MethodCallFluent<?>> {
    private final Set<MethodCall> methodCalls = new HashSet<>();

    @Override
    public void visit(MethodCallFluent<?> methodCallFluent) {
      MethodCallBuilder builder = new MethodCallBuilder(methodCallFluent);
      MethodCall methodCall = builder.build();
      methodCalls.add(methodCall);
    }

    public Set<MethodCall> getMethodCalls() {
      return methodCalls;
    }
  }

  public MethodReference(Method method, TypeDef owningType) {
    this.method = method;
    this.owningType = owningType;
  }

  public Method getMethod() {
    return method;
  }

  public TypeDef getOwningType() {
    return owningType;
  }

  /**
   * Get all method references that are called directly from the given method (non-recursive).
   * This method analyzes only the method body to find direct method calls.
   *
   * @param method the method to analyze (cannot be null)
   * @param repository the definition repository containing type definitions (cannot be null)
   * @return set of direct method references
   * @throws IllegalArgumentException if method or repository is null
   */
  public static Set<MethodReference> getDirectMethodReferences(Method method, DefinitionRepository repository) {
    if (method == null) {
      throw new IllegalArgumentException("Method cannot be null");
    }
    if (repository == null) {
      throw new IllegalArgumentException("Repository cannot be null");
    }

    Set<MethodReference> result = new LinkedHashSet<>();

    if (method.getBlock() != null) {
      Set<MethodCall> methodCalls = findMethodCalls(method.getBlock());

      for (MethodCall methodCall : methodCalls) {
        Set<MethodReference> referencedMethods = resolveMethodCall(methodCall, repository);
        result.addAll(referencedMethods);
      }
    }

    return result;
  }

  /**
   * Get all method references that are called directly or transitively from the given method.
   * This method analyzes the method body and recursively follows method calls to find all
   * methods from types in the DefinitionRepository that are referenced.
   *
   * @param method the method to analyze (cannot be null)
   * @param repository the definition repository containing type definitions (cannot be null)
   * @return set of all method references (direct and transitive)
   * @throws IllegalArgumentException if method or repository is null
   */
  public static Set<MethodReference> getMethodReferences(Method method, DefinitionRepository repository) {
    if (method == null) {
      throw new IllegalArgumentException("Method cannot be null");
    }
    if (repository == null) {
      throw new IllegalArgumentException("Repository cannot be null");
    }

    Set<Method> visited = new HashSet<>();
    Set<MethodReference> result = new LinkedHashSet<>();
    collectMethodReferencesRecursively(method, repository, visited, result);
    return result;
  }

  /**
   * Get all method references that are called directly or transitively from the given method.
   * This method uses the default DefinitionRepository instance.
   *
   * @param method the method to analyze (cannot be null)
   * @return set of all method references (direct and transitive)
   * @throws IllegalArgumentException if method is null
   */
  public static Set<MethodReference> getMethodReferences(Method method) {
    if (method == null) {
      throw new IllegalArgumentException("Method cannot be null");
    }
    return getMethodReferences(method, DefinitionRepository.getRepository());
  }

  /**
   * Recursively collects method references by analyzing the given method's body and following
   * all method calls transitively. This method mutates the result set to accumulate findings.
   *
   * @param method the method to analyze
   * @param repository the repository to search for method definitions
   * @param visited set of already visited methods to prevent infinite recursion
   * @param result the set to accumulate method references into
   */
  private static void collectMethodReferencesRecursively(Method method, DefinitionRepository repository, Set<Method> visited,
      Set<MethodReference> result) {
    if (method == null || visited.contains(method)) {
      return;
    }

    visited.add(method);

    // Get direct method references and follow them recursively
    Set<MethodReference> directReferences = getDirectMethodReferences(method, repository);
    for (MethodReference referencedMethod : directReferences) {
      if (!visited.contains(referencedMethod.getMethod())) {
        result.add(referencedMethod);
        // Recursively analyze the referenced method
        collectMethodReferencesRecursively(referencedMethod.getMethod(), repository, visited, result);
      }
    }
  }

  /**
   * Find all method calls within a block using the visitor pattern.
   * This replaces the complex manual traversal with automatic AST traversal.
   * Creates a temporary method builder with the block to make it visitable.
   *
   * @param block the block to search for method calls
   * @return set of all method calls found within the block
   */
  private static Set<MethodCall> findMethodCalls(Block block) {
    MethodCallCollector collector = new MethodCallCollector();

    // Create a temporary method with the block to make it visitable
    MethodBuilder methodBuilder = new MethodBuilder()
        .withBlock(block);
    methodBuilder.accept(collector);

    return collector.getMethodCalls();
  }

  /**
   * Find all methods in a TypeDef that match the given method name.
   *
   * @param typeDef the TypeDef to search in
   * @param methodName the method name to match
   * @return set of MethodReference objects for matching methods
   */
  private static Set<MethodReference> findMethodsByName(TypeDef typeDef, String methodName) {
    Set<MethodReference> methods = new HashSet<>();
    for (Method method : typeDef.getMethods()) {
      if (methodName.equals(method.getName())) {
        methods.add(new MethodReference(method, typeDef));
      }
    }
    return methods;
  }

  /**
   * Find methods in a TypeDef that match the given method signature using erasure-based comparison.
   * This provides more precise matching than name-only matching.
   *
   * @param typeDef the TypeDef to search in
   * @param methodName the method name to match
   * @param parameterTypes the parameter types to match (using erasure)
   * @return set of MethodReference objects for matching methods
   */
  private static Set<MethodReference> findMethodsBySignature(TypeDef typeDef, String methodName,
      List<Property> parameterTypes) {
    // Create a target method with the given signature for erasure comparison
    Method targetMethod = new MethodBuilder()
        .withName(methodName)
        .withArguments(parameterTypes != null ? parameterTypes : List.of())
        .build();

    String targetErasure = targetMethod.getErasure();

    Set<MethodReference> methods = new HashSet<>();
    for (Method method : typeDef.getMethods()) {
      if (targetErasure.equals(method.getErasure())) {
        methods.add(new MethodReference(method, typeDef));
      }
    }
    return methods;
  }

  /**
   * Resolves a method call to find all possible method references it could refer to.
   * Handles both scoped method calls (with explicit receiver) and unscoped method calls
   * (local, superclass, or static imports).
   *
   * @param methodCall the method call to resolve
   * @param repository the repository to search for method definitions
   * @return set of method references that match the method call
   */
  private static Set<MethodReference> resolveMethodCall(MethodCall methodCall, DefinitionRepository repository) {
    // Create cache key based on method call details
    String cacheKey = createMethodCallCacheKey(methodCall, repository);

    // Check cache first
    Set<MethodReference> cachedResult = RESULTION_CACHE.get(cacheKey);
    if (cachedResult != null) {
      return new HashSet<>(cachedResult); // Return copy to avoid mutation
    }

    Set<MethodReference> methods = new HashSet<>();

    // Get the scope of the method call to determine the target type
    Expression scope = methodCall.getScope();
    if (scope != null) {
      // Scoped method call - search in specific types
      if (scope instanceof io.sundr.model.This || scope instanceof io.sundr.model.Super) {
        // Special case for 'this' and 'super' - search in all types since we don't have context
        // TODO: This is a limitation - ideally we'd know the current type context
        for (TypeDef typeDef : repository.getDefinitions()) {
          methods.addAll(findMethodsByName(typeDef, methodCall.getName()));
        }
      } else {
        Set<ClassRef> scopeReferences = scope.getReferences();
        for (ClassRef classRef : scopeReferences) {
          TypeDef typeDef = repository.getDefinition(classRef.getFullyQualifiedName());
          if (typeDef != null) {
            methods.addAll(findMethodsByName(typeDef, methodCall.getName()));
          }
        }
      }
    } else {
      // Unscoped method call - likely local method call, super type method, or static import
      // Search through all registered TypeDefs for methods with matching name
      for (TypeDef typeDef : repository.getDefinitions()) {
        methods.addAll(findMethodsByName(typeDef, methodCall.getName()));
      }
    }

    // Cache the result for future lookups
    RESULTION_CACHE.put(cacheKey, new HashSet<>(methods));

    return methods;
  }

  /**
   * Creates a cache key for method call resolution.
   *
   * @param methodCall the method call to create a key for
   * @param repository the repository context
   * @return a cache key string
   */
  private static String createMethodCallCacheKey(MethodCall methodCall, DefinitionRepository repository) {
    StringBuilder key = new StringBuilder();
    key.append(methodCall.getName());

    // Include scope information
    Expression scope = methodCall.getScope();
    if (scope != null) {
      key.append("@").append(scope.getClass().getSimpleName());
      if (!(scope instanceof io.sundr.model.This || scope instanceof io.sundr.model.Super)) {
        // For non-this/super scopes, include the scope details
        key.append(":").append(scope.toString());
      }
    } else {
      key.append("@null");
    }

    // Include repository state (simple hash of all type names)
    key.append("#").append(repository.getDefinitions().stream()
        .mapToInt(td -> td.getFullyQualifiedName().hashCode())
        .sum());

    return key.toString();
  }

  /**
   * Clears the method resolution cache. This should be called when the repository
   * changes to ensure cached results remain valid.
   */
  public static void clearCache() {
    RESULTION_CACHE.clear();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    MethodReference that = (MethodReference) obj;

    // Use erasure-based comparison for methods to handle object identity issues
    return method.getErasure().equals(that.method.getErasure()) &&
        owningType.getFullyQualifiedName().equals(that.owningType.getFullyQualifiedName());
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(method.getErasure(), owningType.getFullyQualifiedName());
  }

  @Override
  public String toString() {
    return owningType.getName() + "." + method.getName();
  }
}
