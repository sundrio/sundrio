package io.sundr.adapter.source.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.Project;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.MethodCall;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.repo.MethodCallCollector;

/**
 * Test the MethodCallCollector against real source files from the core module
 * to verify it correctly detects method calls.
 */
public class MethodCallCollectorTest {

  private Project coreProject;
  private DefinitionRepository repository;
  private AdapterContext context;

  @Before
  public void setUp() {
    Project project = Project.getProject();
    coreProject = Project.getProject(project.getModuleRoot().toPath().getParent().getParent().resolve("core"));

    context = AdapterContext.getContext();
    repository = DefinitionRepository.getRepository();
    repository.clear();

    // Load some TypeDefs from core module
    for (Path source : coreProject.allSources().list()) {
      try {
        TypeDef typeDef = Sources.readTypeDefFromPath(source, context);
        if (typeDef != null) {
          repository.register(typeDef);
        }
      } catch (Exception e) {
        // Skip files that can't be parsed
        System.out.println("Skipping " + source + ": " + e.getMessage());
      }
    }
  }

  @Test
  public void testMethodCallCollectorOnMapsTestClass() {
    // Get the MapsTest TypeDef
    TypeDef mapsTestTypeDef = repository.getDefinition("io.sundr.utils.MapsTest");
    assertNotNull("MapsTest should be found in repository", mapsTestTypeDef);

    // Test the shouldCreateFromMapping method specifically
    Method shouldCreateFromMappingMethod = mapsTestTypeDef.getMethods().stream()
        .filter(m -> "shouldCreateFromMapping".equals(m.getName()))
        .findFirst()
        .orElse(null);
    assertNotNull("shouldCreateFromMapping method should exist", shouldCreateFromMappingMethod);

    System.out.println("Testing MethodCallCollector on shouldCreateFromMapping method");

    // Check the method has a block
    assertNotNull("Method should have a block", shouldCreateFromMappingMethod.getBlock());
    System.out.println("Method block has " + shouldCreateFromMappingMethod.getBlock().getStatements().size() + " statements");

    // Print each statement for debugging
    for (int i = 0; i < shouldCreateFromMappingMethod.getBlock().getStatements().size(); i++) {
      io.sundr.model.Statement stmt = shouldCreateFromMappingMethod.getBlock().getStatements().get(i);
      System.out.println("  [" + i + "] " + stmt.getClass().getSimpleName() + ": " + stmt.render());
    }

    // Use MethodCallCollector to find method calls
    MethodCallCollector collector = new MethodCallCollector();

    // Make the method visitable and apply the collector
    MethodBuilder methodBuilder = new MethodBuilder(shouldCreateFromMappingMethod);
    methodBuilder.accept(collector);

    Set<MethodCall> methodCalls = collector.getMethodCalls();

    System.out.println("MethodCallCollector found " + methodCalls.size() + " method calls:");
    for (MethodCall call : methodCalls) {
      System.out.println("  - " + call.getName() + " (scope: " +
          (call.getScope() != null ? call.getScope().render() : "null") + ")");
    }

    // We expect to find at least the Maps.create call
    boolean foundMapsCreate = methodCalls.stream()
        .anyMatch(call -> "create".equals(call.getName()) &&
            call.getScope() != null &&
            call.getScope().render().contains("Maps"));

    if (foundMapsCreate) {
      System.out.println("✅ Found Maps.create call as expected");
    } else {
      System.out.println("❌ Did not find Maps.create call");
      System.out.println("Expected to find a call to 'create' method with 'Maps' scope");
    }

    assertTrue("Should find Maps.create method call", foundMapsCreate);
  }

  @Test
  public void testMethodCallCollectorOnSimpleMethod() {
    // Test on a method that definitely should have method calls
    TypeDef mapsTypeDef = repository.getDefinition("io.sundr.utils.Maps");
    assertNotNull("Maps class should be found", mapsTypeDef);

    // Test the create(String) method which calls extractKey and extractValue
    Method createMethod = mapsTypeDef.getMethods().stream()
        .filter(m -> "create".equals(m.getName()) && m.getArguments().size() == 1)
        .findFirst()
        .orElse(null);
    assertNotNull("create(String) method should exist", createMethod);

    System.out.println("\nTesting MethodCallCollector on Maps.create(String) method");

    // Check method structure
    if (createMethod.getBlock() != null) {
      System.out.println("Method block has " + createMethod.getBlock().getStatements().size() + " statements");
      for (int i = 0; i < createMethod.getBlock().getStatements().size(); i++) {
        io.sundr.model.Statement stmt = createMethod.getBlock().getStatements().get(i);
        System.out.println("  [" + i + "] " + stmt.getClass().getSimpleName() + ": " + stmt.render());
      }
    } else {
      System.out.println("Method has no block");
    }

    // Use MethodCallCollector
    MethodCallCollector collector = new MethodCallCollector();
    MethodBuilder methodBuilder = new MethodBuilder(createMethod);
    methodBuilder.accept(collector);

    Set<MethodCall> methodCalls = collector.getMethodCalls();

    System.out.println("MethodCallCollector found " + methodCalls.size() + " method calls:");
    for (MethodCall call : methodCalls) {
      System.out.println("  - " + call.getName() + " (scope: " +
          (call.getScope() != null ? call.getScope().render() : "null") + ")");
    }

    // We expect to find extractKey and extractValue calls
    boolean foundExtractKey = methodCalls.stream()
        .anyMatch(call -> "extractKey".equals(call.getName()));
    boolean foundExtractValue = methodCalls.stream()
        .anyMatch(call -> "extractValue".equals(call.getName()));

    System.out.println("Found extractKey: " + foundExtractKey);
    System.out.println("Found extractValue: " + foundExtractValue);
  }

  @Test
  public void testMethodCallCollectorDebugOutput() {
    // Get a test method and inspect its structure in detail
    TypeDef mapsTestTypeDef = repository.getDefinition("io.sundr.utils.MapsTest");
    if (mapsTestTypeDef == null) {
      System.out.println("MapsTest not found, skipping debug test");
      return;
    }

    Method testMethod = mapsTestTypeDef.getMethods().stream()
        .filter(m -> "shouldCreateFromMapping".equals(m.getName()))
        .findFirst()
        .orElse(null);

    if (testMethod == null) {
      System.out.println("shouldCreateFromMapping method not found, skipping debug test");
      return;
    }

    System.out.println("\n=== DETAILED METHOD ANALYSIS ===");
    System.out.println("Method: " + testMethod.getName());
    System.out.println("Has block: " + (testMethod.getBlock() != null));

    if (testMethod.getBlock() != null) {
      System.out.println("Block class: " + testMethod.getBlock().getClass().getSimpleName());
      System.out.println("Number of statements: " + testMethod.getBlock().getStatements().size());

      for (int i = 0; i < testMethod.getBlock().getStatements().size(); i++) {
        io.sundr.model.Statement stmt = testMethod.getBlock().getStatements().get(i);
        System.out.println("\nStatement " + i + ":");
        System.out.println("  Class: " + stmt.getClass().getSimpleName());
        System.out.println("  Render: " + stmt.render());

        // If it's a Declare statement, examine its initializer
        if (stmt instanceof io.sundr.model.Declare) {
          io.sundr.model.Declare declare = (io.sundr.model.Declare) stmt;
          declare.getValue().ifPresent(v -> {
            System.out.println("  Value class: " + v.getClass().getSimpleName());
            System.out.println("  Value render: " + v.render());
          });
        }
      }
    }

    System.out.println("\n=== VISITOR TEST ===");
    MethodCallCollector collector = new MethodCallCollector();
    MethodBuilder methodBuilder = new MethodBuilder(testMethod);

    System.out.println("Applying visitor to method...");
    methodBuilder.accept(collector);

    Set<MethodCall> calls = collector.getMethodCalls();
    System.out.println("Visitor found " + calls.size() + " method calls");

    for (MethodCall call : calls) {
      System.out.println("  Found call: " + call.getName());
      if (call.getScope() != null) {
        System.out.println("    Scope: " + call.getScope().render());
        System.out.println("    Scope class: " + call.getScope().getClass().getSimpleName());
      }
      System.out.println("    Arguments: " + call.getArguments().size());
    }
  }
}
