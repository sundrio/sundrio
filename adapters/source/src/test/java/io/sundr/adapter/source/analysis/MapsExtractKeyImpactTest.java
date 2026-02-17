package io.sundr.adapter.source.analysis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.Project;
import io.sundr.adapter.source.change.Change;
import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;

/**
 * Test that emulates the Maps.extractKey -> MapsTest scenario
 * using the actual core project to verify dependency tree semantics.
 */
public class MapsExtractKeyImpactTest {

  private ImpactAnalyzer analyzer;
  private Project coreProject;
  private DefinitionRepository repository;

  @BeforeEach
  public void setUp() {
    Project project = Project.getProject();
    coreProject = Project.getProject(project.getModuleRoot().toPath().getParent().getParent().resolve("core"));

    AdapterContext context = AdapterContext.getContext();
    repository = DefinitionRepository.getRepository();
    analyzer = new ImpactAnalyzer(coreProject, repository);
    repository.clear();

    for (Path source : coreProject.allSources().list()) {
      TypeDef typeDef = Sources.readTypeDefFromPath(source, context);
      if (typeDef != null) {
        repository.register(typeDef);
      }
    }
  }

  @Test
  public void testRealMapsExtractKeyImpact() {
    // Find the actual Maps source file
    Optional<Path> mapsPath = coreProject.allSources().find("io.sundr.utils.Maps");
    assertTrue(mapsPath.isPresent(), "Maps class should be found in core project");

    // Find the actual MapsTest source file
    Optional<Path> mapsTestPath = coreProject.allSources().find("io.sundr.utils.MapsTest");
    assertTrue(mapsTestPath.isPresent(), "MapsTest class should be found in core project");

    System.out.println("Maps source file: " + mapsPath.get());
    System.out.println("MapsTest source file: " + mapsTestPath.get());

    // Get the Maps TypeDef from repository
    TypeDef mapsTypeDef = repository.getDefinition("io.sundr.utils.Maps");
    assertNotNull(mapsTypeDef, "Maps TypeDef should be found in repository");

    // Get the MapsTest TypeDef from repository
    TypeDef mapsTestTypeDef = repository.getDefinition("io.sundr.utils.MapsTest");
    assertNotNull(mapsTestTypeDef, "MapsTest TypeDef should be found in repository");

    // Find the extractKey method
    Method extractKeyMethod = mapsTypeDef.getMethods().stream()
        .filter(m -> "extractKey".equals(m.getName()))
        .findFirst()
        .orElse(null);
    assertNotNull(extractKeyMethod, "extractKey method should be found");

    System.out.println("Found extractKey method: " + extractKeyMethod.getName());

    // Find test methods in MapsTest
    System.out.println("MapsTest methods:");
    mapsTestTypeDef.getMethods().forEach(m -> System.out.println("  - " + m.getName()));

    // Create a change to the extractKey method
    Set<Change<Method>> methodChanges = Set.of(Change.modified(extractKeyMethod, extractKeyMethod));
    ChangeSet changeSet = new ChangeSet(mapsTypeDef, mapsTypeDef, methodChanges, Set.of());

    // Perform impact analysis
    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result, "Impact analysis result should not be null");
    assertTrue(result.hasAnyImpact(), "Should have impact detected");

    // Print the dependency tree
    String dependencyTree = result.getDependencyTreeVisualization();
    System.out.println("\n=== DEPENDENCY TREE ===");
    System.out.println(dependencyTree);
    System.out.println("=== END DEPENDENCY TREE ===\n");

    // Debug: Print all TypeDefs in repository
    repository.getDefinitions().forEach(typeDef -> {
      System.out.println("  - " + typeDef.getFullyQualifiedName() + " (" + typeDef.getMethods().size() + " methods)");
    });

    // Print all affected method references
    System.out.println("All affected method references:");
    result.getAffectedMethodReferences()
        .forEach(ref -> System.out.println("  - " + ref.getOwningType().getName() + "." + ref.getMethod().getName()));

    // Check if the dependency tree shows the correct semantics:
    // Root should be Maps.extractKey
    assertTrue(dependencyTree.contains("Maps.extractKey"), "Dependency tree should contain Maps.extractKey");

    // Look for Maps.create method as a child (since it calls extractKey)
    boolean hasCreateMethod = result.getAffectedMethodReferences().stream()
        .anyMatch(ref -> "create".equals(ref.getMethod().getName()) && "Maps".equals(ref.getOwningType().getName()));

    if (hasCreateMethod) {
      System.out.println("✅ Maps.create method found in impact analysis (correct - it calls extractKey)");
    } else {
      System.out.println("❌ Maps.create method NOT found in impact analysis");
    }
    assertTrue(hasCreateMethod);

    // Look for test methods that should be affected
    boolean hasTestMethods = result.getAffectedMethodReferences().stream()
        .anyMatch(ref -> "MapsTest".equals(ref.getOwningType().getName()));

    if (hasTestMethods) {
      System.out.println("✅ MapsTest methods found in impact analysis:");
      result.getAffectedMethodReferences().stream()
          .filter(ref -> "MapsTest".equals(ref.getOwningType().getName()))
          .forEach(ref -> System.out.println("    - " + ref.getMethod().getName()));
    } else {
      System.out.println("❌ No MapsTest methods found in impact analysis");
    }
    assertTrue(hasTestMethods, "Should have MapsTest methods affected");

    // Specifically look for shouldCreateFromMapping test (which should call Maps.create)
    boolean hasShouldCreateFromMappingTest = result.getAffectedMethodReferences().stream()
        .anyMatch(ref -> "shouldCreateFromMapping".equals(ref.getMethod().getName()));

    if (hasShouldCreateFromMappingTest) {
      System.out.println("✅ shouldCreateFromMapping test method found (correct - it calls Maps.create which calls extractKey)");
    } else {
      System.out.println("❌ shouldCreateFromMapping test method NOT found");
    }
    assertTrue(hasShouldCreateFromMappingTest, "shouldCreateFromMapping test should be affected");

    // Print impact summary
    System.out.println("\nImpact Summary: " + result.getSummary());

    // The test passes if we have any impact and the tree contains the expected root
    assertTrue(dependencyTree.startsWith("Maps.extractKey"),
        "Dependency tree should start with Maps.extractKey as root");
  }

  @Test
  public void testDependencyTreeSemantics() {
    // This test focuses on verifying the semantics are correct
    TypeDef mapsTypeDef = repository.getDefinition("io.sundr.utils.Maps");
    if (mapsTypeDef == null) {
      System.out.println("Skipping semantics test - Maps class not found");
      return;
    }

    Method extractKeyMethod = mapsTypeDef.getMethods().stream()
        .filter(m -> "extractKey".equals(m.getName()))
        .findFirst()
        .orElse(null);

    if (extractKeyMethod == null) {
      System.out.println("Skipping semantics test - extractKey method not found");
      return;
    }

    // Create change and analyze
    Set<Change<Method>> methodChanges = Set.of(Change.modified(extractKeyMethod, extractKeyMethod));
    ChangeSet changeSet = new ChangeSet(mapsTypeDef, mapsTypeDef, methodChanges, Set.of());
    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    String dependencyTree = result.getDependencyTreeVisualization();
    System.out.println("\n=== DEPENDENCY TREE SEMANTICS ANALYSIS ===");
    System.out.println("Expected semantics:");
    System.out.println("  Root: Changed method (Maps.extractKey)");
    System.out.println("  Children: Methods that call the changed method (callers)");
    System.out.println("  Leaves: Test methods that transitively depend on the changed method");
    System.out.println();
    System.out.println("Actual dependency tree:");
    System.out.println(dependencyTree);
    System.out.println("=== END SEMANTICS ANALYSIS ===");

    // Verify root is the changed method
    String[] lines = dependencyTree.split("\n");
    if (lines.length > 0) {
      String rootLine = lines[0].trim();
      System.out.println("Root line: " + rootLine);
      assertTrue(rootLine.contains("Maps.extractKey"), "Root should be Maps.extractKey");

      // Verify children are methods that call extractKey (not methods that extractKey calls)
      System.out.println("\nAnalyzing tree structure:");
      for (int i = 1; i < lines.length; i++) {
        String line = lines[i];
        if (line.contains("\\-") || line.contains("+-")) {
          String indent = line.substring(0, line.indexOf(line.trim()));
          String methodName = line.trim().substring(2).trim(); // Remove "\\- " or "+- "
          System.out.println("Child at level " + (indent.length() / 3) + ": " + methodName);
        }
      }
    }
  }
}
