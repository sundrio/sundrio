package io.sundr.adapter.source.analysis;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

import io.sundr.model.TypeDef;
import io.sundr.model.repo.MethodReference;
import io.sundr.utils.Dependencies;

/**
 * Result of an impact analysis operation.
 * Contains all affected files, TypeDefs, and a dependency tree of MethodReferences.
 * The dependency tree shows how changes propagate through the codebase and replaces
 * the previous flat list of affected method references.
 */
public class ImpactAnalysisResult {
  private final Set<Path> affectedFiles;
  private final Set<TypeDef> affectedTypeDefs;
  private final Map<String, String> analysisMetadata;
  private final Dependencies.DependencyTree<MethodReference> dependencyTree;

  public ImpactAnalysisResult(Set<Path> affectedFiles, Set<TypeDef> affectedTypeDefs,
      Dependencies.DependencyTree<MethodReference> dependencyTree) {
    this.affectedFiles = affectedFiles != null ? Set.copyOf(affectedFiles) : Set.of();
    this.affectedTypeDefs = affectedTypeDefs != null ? Set.copyOf(affectedTypeDefs) : Set.of();
    this.analysisMetadata = Map.of();
    this.dependencyTree = dependencyTree != null ? dependencyTree : Dependencies.newTree();
  }

  public ImpactAnalysisResult(Set<Path> affectedFiles, Set<TypeDef> affectedTypeDefs,
      Map<String, String> analysisMetadata, Dependencies.DependencyTree<MethodReference> dependencyTree) {
    this.affectedFiles = affectedFiles != null ? Set.copyOf(affectedFiles) : Set.of();
    this.affectedTypeDefs = affectedTypeDefs != null ? Set.copyOf(affectedTypeDefs) : Set.of();
    this.analysisMetadata = analysisMetadata != null ? Map.copyOf(analysisMetadata) : Map.of();
    this.dependencyTree = dependencyTree != null ? dependencyTree : Dependencies.newTree();
  }

  public Set<Path> getAffectedFiles() {
    return affectedFiles;
  }

  public Set<TypeDef> getAffectedTypeDefs() {
    return affectedTypeDefs;
  }

  /**
   * Returns all method references affected by the change.
   * This is derived from all nodes in the dependency tree.
   */
  public Set<MethodReference> getAffectedMethodReferences() {
    return dependencyTree.getAllNodes();
  }

  public boolean hasAnyImpact() {
    return !affectedFiles.isEmpty() || !affectedTypeDefs.isEmpty() || !getAffectedMethodReferences().isEmpty();
  }

  public int getTotalImpactCount() {
    return affectedFiles.size() + affectedTypeDefs.size() + getAffectedMethodReferences().size();
  }

  public Map<String, String> getAnalysisMetadata() {
    return analysisMetadata;
  }

  /**
   * Returns the dependency tree showing how changes propagate through method calls.
   * The tree shows the root cause (changed methods) and their impact on other methods.
   *
   * @return the dependency tree with MethodReference nodes
   */
  public Dependencies.DependencyTree<MethodReference> getDependencyTree() {
    return dependencyTree;
  }

  public Map<String, Set<Path>> getAffectedFilesByDirectory() {
    return affectedFiles.stream()
        .collect(java.util.stream.Collectors.groupingBy(
            path -> path.getParent().toString(),
            java.util.stream.Collectors.toSet()));
  }

  public Map<String, Set<TypeDef>> getAffectedTypeDefsByPackage() {
    return affectedTypeDefs.stream()
        .collect(java.util.stream.Collectors.groupingBy(
            TypeDef::getPackageName,
            java.util.stream.Collectors.toSet()));
  }

  /**
   * Returns a summary of the impact analysis for quick overview.
   */
  public String getSummary() {
    StringBuilder summary = new StringBuilder();
    summary.append("Impact Summary: ");

    if (!hasAnyImpact()) {
      summary.append("No impact detected");
      return summary.toString();
    }

    summary.append(affectedFiles.size()).append(" files, ");
    summary.append(affectedTypeDefs.size()).append(" types, ");
    summary.append(getAffectedMethodReferences().size()).append(" methods");

    if (!analysisMetadata.isEmpty()) {
      summary.append(" [");
      if (analysisMetadata.containsKey("analysis_duration_ms")) {
        summary.append("took ").append(analysisMetadata.get("analysis_duration_ms")).append("ms");
      }
      if (analysisMetadata.containsKey("error_count")) {
        String errors = analysisMetadata.get("error_count");
        if (!"0".equals(errors)) {
          summary.append(", ").append(errors).append(" errors");
        }
      }
      summary.append("]");
    }

    return summary.toString();
  }

  /**
   * Returns a string representation of the dependency tree showing impact propagation.
   */
  public String getDependencyTreeVisualization() {
    return dependencyTree.render();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Impact Analysis Result:\n");

    if (!affectedFiles.isEmpty()) {
      sb.append("  Affected Files (").append(affectedFiles.size()).append("):\n");
      affectedFiles.forEach(file -> sb.append("    ").append(file).append("\n"));
    }

    if (!affectedTypeDefs.isEmpty()) {
      sb.append("  Affected TypeDefs (").append(affectedTypeDefs.size()).append("):\n");
      affectedTypeDefs.forEach(typeDef -> sb.append("    ").append(typeDef.getFullyQualifiedName()).append("\n"));
    }

    Set<MethodReference> affectedMethodReferences = getAffectedMethodReferences();
    if (!affectedMethodReferences.isEmpty()) {
      sb.append("  Affected MethodReferences (").append(affectedMethodReferences.size()).append("):\n");
      affectedMethodReferences.forEach(methodRef -> sb.append("    ").append(methodRef).append("\n"));
    }

    if (!hasAnyImpact()) {
      sb.append("  No impact detected.\n");
    } else {
      String treeVisualization = getDependencyTreeVisualization();
      if (!treeVisualization.trim().isEmpty()) {
        sb.append("\n  Dependency Tree:\n");
        String[] lines = treeVisualization.split("\n");
        for (String line : lines) {
          sb.append("    ").append(line).append("\n");
        }
      }
    }

    return sb.toString();
  }
}
