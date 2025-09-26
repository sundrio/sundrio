package io.sundr.adapter.source.analysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import io.sundr.adapter.source.Project;
import io.sundr.adapter.source.change.Change;
import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.VoidRef;
import io.sundr.model.repo.DefinitionRepository;

public class ImpactAnalysisPerformanceTest {

  private Project project;
  private ImpactAnalyzer analyzer;

  @Before
  public void setUp() {
    project = Project.getProject();
    analyzer = new ImpactAnalyzer(project, DefinitionRepository.getRepository());
  }

  @Test
  public void testAnalysisPerformanceWithMultipleMethods() {
    // Create a TypeDef with many methods to test performance
    TypeDefBuilder typeBuilder = new TypeDefBuilder()
        .withPackageName("com.example.performance")
        .withName("LargeClass");

    // Add 50 methods to simulate a larger class
    for (int i = 0; i < 50; i++) {
      typeBuilder.addNewMethod()
          .withName("method" + i)
          .withReturnType(new VoidRef())
          .addNewArgument()
          .withName("param" + i)
          .withTypeRef(ClassRef.forClass(String.class))
          .endArgument()
          .withNewBlock()
          .endBlock()
          .endMethod();
    }

    TypeDef oldTypeDef = typeBuilder.build();

    // Modify one method
    TypeDef newTypeDef = new TypeDefBuilder(oldTypeDef)
        .editMethod(0)
        .withNewBlock()
        .endBlock()
        .endMethod()
        .build();

    // Create change set
    Method oldMethod = oldTypeDef.getMethods().get(0);
    Method newMethod = newTypeDef.getMethods().get(0);

    ChangeSet changeSet = new ChangeSet(
        oldTypeDef,
        newTypeDef,
        Set.of(Change.modified(oldMethod, newMethod)),
        Set.of());

    // Measure performance
    long startTime = System.currentTimeMillis();
    ImpactAnalysisResult result = analyzer.analyze(changeSet);
    long duration = System.currentTimeMillis() - startTime;

    // Assertions
    assertNotNull(result);
    assertTrue("Analysis should complete within reasonable time (< 5 seconds)", duration < 5000);

    // Check metadata if available
    if (result.getAnalysisMetadata().containsKey("analysis_duration_ms")) {
      String durationStr = result.getAnalysisMetadata().get("analysis_duration_ms");
      long analysisDuration = Long.parseLong(durationStr);
      assertTrue("Internal analysis duration should be reasonable", analysisDuration < 1000);
    }

    System.out.println("Analysis of 50-method class took: " + duration + "ms");
  }

  @Test
  public void testAnalysisWithMultipleChanges() {
    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example.multiple")
        .withName("MultiChangeClass")
        .build();

    // Create multiple method changes
    Set<Change<Method>> methodChanges = new HashSet<>();

    for (int i = 0; i < 10; i++) {
      Method method = new MethodBuilder()
          .withName("method" + i)
          .withReturnType(new VoidRef())
          .build();
      methodChanges.add(Change.added(method));
    }

    ChangeSet changeSet = new ChangeSet(null, typeDef, methodChanges, Set.of());

    long startTime = System.currentTimeMillis();
    ImpactAnalysisResult result = analyzer.analyze(changeSet);
    long duration = System.currentTimeMillis() - startTime;

    assertNotNull(result);
    assertTrue("Multiple changes should be analyzed efficiently", duration < 2000);

    System.out.println("Analysis of 10 method additions took: " + duration + "ms");
  }

  @Test
  public void testCacheEfficiency() {
    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example.cache")
        .withName("CacheTestClass")
        .addNewMethod()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .endMethod()
        .build();

    Method method = typeDef.getMethods().get(0);
    ChangeSet changeSet = new ChangeSet(null, typeDef, Set.of(Change.added(method)), Set.of());

    // First analysis
    long startTime1 = System.currentTimeMillis();
    ImpactAnalysisResult result1 = analyzer.analyze(changeSet);
    long duration1 = System.currentTimeMillis() - startTime1;

    // Second analysis (should benefit from caching)
    long startTime2 = System.currentTimeMillis();
    ImpactAnalysisResult result2 = analyzer.analyze(changeSet);
    long duration2 = System.currentTimeMillis() - startTime2;

    assertNotNull(result1);
    assertNotNull(result2);

    // Note: This test is informational - caching benefits may vary
    System.out.println("First analysis: " + duration1 + "ms, Second analysis: " + duration2 + "ms");
  }
}
