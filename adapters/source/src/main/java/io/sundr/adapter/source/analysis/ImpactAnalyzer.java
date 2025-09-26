package io.sundr.adapter.source.analysis;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import io.sundr.adapter.source.Project;
import io.sundr.adapter.source.change.Change;
import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.adapter.source.change.ChangeType;
import io.sundr.builder.Visitor;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.PropertyRef;
import io.sundr.model.PropertyRefFluent;
import io.sundr.model.Super;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.repo.MethodReference;
import io.sundr.utils.Dependencies;

/**
 * Analyzes the impact of changes on a codebase.
 * Given a ChangeSet, performs impact analysis to find all affected files, TypeDefs, and MethodReferences.
 */
public class ImpactAnalyzer {

  private final Project project;
  private final DefinitionRepository repository;

  public ImpactAnalyzer(Project project) {
    this.project = project;
    this.repository = DefinitionRepository.getRepository();
  }

  public ImpactAnalyzer(Project project, DefinitionRepository repository) {
    this.project = project;
    this.repository = repository;
  }

  /**
   * Performs impact analysis for the given ChangeSet.
   *
   * @param changeSet the changes to analyze
   * @return the impact analysis result
   */
  public ImpactAnalysisResult analyze(ChangeSet changeSet) {
    if (changeSet == null || !changeSet.hasChanges()) {
      return new ImpactAnalysisResult(Set.of(), Set.of(), Dependencies.newTree());
    }

    Set<Path> affectedFiles = new HashSet<>();
    Set<TypeDef> affectedTypeDefs = new HashSet<>();
    Dependencies.DependencyTree<MethodReference> dependencyTree = Dependencies
        .newTree(methodRef -> methodRef.getOwningType().getName() + "." + methodRef.getMethod().getName());

    // Start with the directly changed TypeDef
    TypeDef changedTypeDef = changeSet.getNewTypeDef() != null ? changeSet.getNewTypeDef() : changeSet.getOldTypeDef();
    if (changedTypeDef != null) {
      affectedTypeDefs.add(changedTypeDef);
    }

    // Analyze method changes and build dependency tree
    analyzeMethodChangesWithDependencyTree(changeSet, dependencyTree);

    // Analyze property changes
    Set<MethodReference> propertyImpacts = analyzePropertyChanges(changeSet);
    // Add property impacts to dependency tree
    for (MethodReference propertyImpact : propertyImpacts) {
      dependencyTree.addDependency(propertyImpact);
    }

    // Infer TypeDefs and Paths from MethodReferences in dependency tree
    for (MethodReference methodRef : dependencyTree.getAllNodes()) {
      affectedTypeDefs.add(methodRef.getOwningType());
    }

    for (TypeDef typeDef : affectedTypeDefs) {
      findFileForTypeDef(typeDef).ifPresent(affectedFiles::add);
    }

    return new ImpactAnalysisResult(affectedFiles, affectedTypeDefs, dependencyTree);
  }

  private void analyzeMethodChangesWithDependencyTree(ChangeSet changeSet,
      Dependencies.DependencyTree<MethodReference> dependencyTree) {
    for (Change<Method> methodChange : changeSet.getMethodChanges()) {
      Method changedMethod = methodChange.getElement();
      TypeDef owningType = changeSet.getNewTypeDef() != null ? changeSet.getNewTypeDef() : changeSet.getOldTypeDef();

      if (changedMethod != null && owningType != null) {
        // Find the method in the repository to ensure we use the same object instance
        Method repositoryMethod = findMethodInRepository(changedMethod, owningType);

        if (repositoryMethod == null) {
          repositoryMethod = changedMethod; // fallback to original if not found
        }

        MethodReference rootMethodRef = new MethodReference(repositoryMethod, owningType);

        // For removed or modified methods, find all methods that reference this method
        if (methodChange.getChangeType() == ChangeType.REMOVED || methodChange.getChangeType() == ChangeType.MODIFIED) {
          // Start building the tree with the changed method as root
          dependencyTree.addDependency(rootMethodRef);
          buildTransitiveDependencyTree(rootMethodRef, dependencyTree, new HashSet<>());
        } else {
          // For added methods, just add the root
          dependencyTree.addDependency(rootMethodRef);
        }

        // For added or modified methods, use MethodReference.getMethodReferences() to get dependencies
        if (methodChange.getChangeType() == ChangeType.ADDED || methodChange.getChangeType() == ChangeType.MODIFIED) {
          Set<MethodReference> callees = MethodReference.getMethodReferences(changedMethod, repository);

          // Build dependency tree: the changed method depends on its callees
          for (MethodReference callee : callees) {
            dependencyTree.addDependency(rootMethodRef, callee);
          }
        }
      }
    }
  }

  private Set<MethodReference> analyzeMethodChanges(ChangeSet changeSet) {
    Dependencies.DependencyTree<MethodReference> tempTree = Dependencies.newTree();
    analyzeMethodChangesWithDependencyTree(changeSet, tempTree);
    return tempTree.getAllNodes();
  }

  private Set<MethodReference> analyzePropertyChanges(ChangeSet changeSet) {
    Set<MethodReference> affectedMethodReferences = new HashSet<>();

    for (Change<Property> propertyChange : changeSet.getPropertyChanges()) {
      Property changedProperty = propertyChange.getElement();

      if (changedProperty != null) {
        // Find all methods that access this property
        Set<MethodReference> propertyAccessors = findMethodsAccessingProperty(changedProperty);
        affectedMethodReferences.addAll(propertyAccessors);
      }
    }

    return affectedMethodReferences;
  }

  private Set<MethodReference> findMethodReferencesToMethod(MethodReference targetMethod) {
    Set<MethodReference> callers = new HashSet<>();

    // Search through all TypeDefs in the repository to find methods that directly call the target method
    for (TypeDef typeDef : repository.getDefinitions()) {
      for (Method method : typeDef.getMethods()) {
        try {
          // Use direct method references to find only immediate callers
          Set<MethodReference> directReferences = MethodReference.getDirectMethodReferences(method, repository);
          if (directReferences.contains(targetMethod)) {
            MethodReference callingMethod = new MethodReference(method, typeDef);
            callers.add(callingMethod);
          }
        } catch (Exception e) {
          // Skip methods that can't be analyzed
        }
      }
    }

    return callers;
  }

  private void buildTransitiveDependencyTree(MethodReference target,
      Dependencies.DependencyTree<MethodReference> dependencyTree, Set<MethodReference> visited) {
    if (visited.contains(target)) {
      return; // Avoid infinite recursion
    }
    visited.add(target);

    // Find direct callers of the target method
    Set<MethodReference> directCallers = findMethodReferencesToMethod(target);

    for (MethodReference caller : directCallers) {
      // Add the caller as a child of the target (target impacts caller)
      dependencyTree.addDependency(target, caller);

      // Recursively build the tree for the caller (find who calls the caller)
      buildTransitiveDependencyTree(caller, dependencyTree, visited);
    }
  }

  private Set<MethodReference> findMethodReferencesFromMethod(Method method) {
    try {
      return MethodReference.getMethodReferences(method, repository);
    } catch (Exception e) {
      // Skip methods that can't be analyzed
      return Set.of();
    }
  }

  private Set<MethodReference> findMethodsAccessingProperty(Property property) {
    Set<MethodReference> accessors = new HashSet<>();
    String propertyName = property.getName();

    // Use visitor pattern to find actual property accesses with 'this' or 'super' scope
    for (TypeDef typeDef : repository.getDefinitions()) {
      for (Method method : typeDef.getMethods()) {
        if (methodAccessesProperty(method, propertyName)) {
          MethodReference methodRef = new MethodReference(method, typeDef);
          accessors.add(methodRef);
        }
      }
    }

    return accessors;
  }

  /**
   * Uses the visitor pattern to check if a method accesses the given property on 'this' or 'super'.
   */
  private boolean methodAccessesProperty(Method method, String propertyName) {
    if (method.getBlock() == null) {
      return false;
    }

    PropertyAccessVisitor visitor = new PropertyAccessVisitor(propertyName);

    // Create a temporary method builder to make the method visitable
    MethodBuilder methodBuilder = new MethodBuilder(method);
    methodBuilder.accept(visitor);

    return visitor.foundPropertyAccess();
  }

  /**
   * Visitor that searches for PropertyRef instances with 'this' or 'super' scope matching a specific property name.
   */
  private static class PropertyAccessVisitor implements Visitor<PropertyRefFluent<?>> {
    private final String targetPropertyName;
    private boolean foundAccess = false;

    public PropertyAccessVisitor(String targetPropertyName) {
      this.targetPropertyName = targetPropertyName;
    }

    @Override
    public void visit(PropertyRefFluent<?> propertyRefFluent) {
      // Check if this PropertyRef matches our target property
      if (propertyRefFluent.hasProperty() &&
          targetPropertyName.equals(propertyRefFluent.buildProperty().getName())) {

        // Only consider explicit 'this' or 'super' scope (exclude null scope)
        if (propertyRefFluent.hasScope()) {
          Object scope = propertyRefFluent.buildScope();
          if (scope instanceof This || scope instanceof Super) {
            foundAccess = true;
          }
        }
      }
    }

    public boolean foundPropertyAccess() {
      return foundAccess;
    }
  }

  private java.util.Optional<Path> findFileForTypeDef(TypeDef typeDef) {
    if (typeDef == null) {
      return java.util.Optional.empty();
    }

    String fqcn = typeDef.getFullyQualifiedName();

    // Try to find in source files first
    java.util.Optional<Path> sourceFile = project.findJavaSourceFile(fqcn);
    if (sourceFile.isPresent()) {
      return sourceFile;
    }

    // Try to find in test files
    return project.findJavaTestFile(fqcn);
  }

  private Method findMethodInRepository(Method targetMethod, TypeDef owningType) {
    // Find the TypeDef in the repository
    TypeDef repositoryType = repository.getDefinition(owningType.getFullyQualifiedName());
    if (repositoryType == null) {
      return null;
    }

    // Find the method with the same erasure
    String targetErasure = targetMethod.getErasure();

    for (Method method : repositoryType.getMethods()) {
      if (targetErasure.equals(method.getErasure())) {
        return method;
      }
    }
    return null;
  }

  private String capitalize(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}
