package io.sundr.adapter.source.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import io.sundr.adapter.source.Project;
import io.sundr.adapter.source.change.Change;
import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.This;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.VoidRef;
import io.sundr.model.repo.DefinitionRepository;

public class ImpactAnalyzerTest {

  private ImpactAnalyzer analyzer;
  private Project project;
  private DefinitionRepository repository;

  @Before
  public void setUp() {
    project = Project.getProject();
    repository = DefinitionRepository.getRepository();
    analyzer = new ImpactAnalyzer(project, repository);
    repository.clear();
  }

  @Test
  public void testAnalyzeWithNullChangeSet() {
    ImpactAnalysisResult result = analyzer.analyze(null);

    assertNotNull(result);
    assertFalse(result.hasAnyImpact());
    assertEquals(0, result.getTotalImpactCount());
  }

  @Test
  public void testAnalyzeWithEmptyChangeSet() {
    TypeDef typeDef = createSampleTypeDef("com.example.TestClass");
    ChangeSet emptyChangeSet = new ChangeSet(typeDef, typeDef, Set.of(), Set.of());

    ImpactAnalysisResult result = analyzer.analyze(emptyChangeSet);

    assertNotNull(result);
    assertFalse(result.hasAnyImpact());
  }

  @Test
  public void testAnalyzeWithMethodChanges() {
    TypeDef oldTypeDef = createSampleTypeDef("com.example.TestClass");
    TypeDef newTypeDef = createSampleTypeDef("com.example.TestClass");

    Method addedMethod = new MethodBuilder()
        .withName("newMethod")
        .withReturnType(new VoidRef())
        .build();

    Set<Change<Method>> methodChanges = Set.of(Change.added(addedMethod));
    ChangeSet changeSet = new ChangeSet(oldTypeDef, newTypeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedTypeDefs().isEmpty());
    assertFalse(result.getAffectedMethodReferences().isEmpty());

    assertEquals("TestClass.newMethod", result.getDependencyTree().render());
  }

  @Test
  public void testAnalyzeWithPropertyChanges() {
    TypeDef oldTypeDef = createSampleTypeDef("com.example.TestClass");
    TypeDef newTypeDef = createSampleTypeDef("com.example.TestClass");

    Property addedProperty = new PropertyBuilder()
        .withName("newProperty")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    Set<Change<Property>> propertyChanges = Set.of(Change.added(addedProperty));
    ChangeSet changeSet = new ChangeSet(oldTypeDef, newTypeDef, Set.of(), propertyChanges);

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedTypeDefs().isEmpty());
  }

  @Test
  public void testAnalyzeWithRemovedMethod() {
    TypeDef oldTypeDef = createSampleTypeDef("com.example.TestClass");
    TypeDef newTypeDef = createSampleTypeDef("com.example.TestClass");

    Method removedMethod = new MethodBuilder()
        .withName("removedMethod")
        .withReturnType(new VoidRef())
        .build();

    Set<Change<Method>> methodChanges = Set.of(Change.removed(removedMethod));
    ChangeSet changeSet = new ChangeSet(oldTypeDef, newTypeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedTypeDefs().isEmpty());
    assertFalse(result.getAffectedMethodReferences().isEmpty());
  }

  @Test
  public void testAnalyzeWithModifiedMethod() {
    TypeDef oldTypeDef = createSampleTypeDef("com.example.TestClass");
    TypeDef newTypeDef = createSampleTypeDef("com.example.TestClass");

    Method oldMethod = new MethodBuilder()
        .withName("modifiedMethod")
        .withReturnType(new VoidRef())
        .build();

    Method newMethod = new MethodBuilder()
        .withName("modifiedMethod")
        .withReturnType(ClassRef.forClass(String.class))
        .build();

    Set<Change<Method>> methodChanges = Set.of(Change.modified(oldMethod, newMethod));
    ChangeSet changeSet = new ChangeSet(oldTypeDef, newTypeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedTypeDefs().isEmpty());
    assertFalse(result.getAffectedMethodReferences().isEmpty());

    assertEquals("TestClass.modifiedMethod", result.getDependencyTree().render());
  }

  @Test
  public void testImpactAnalysisResultToString() {
    TypeDef typeDef = createSampleTypeDef("com.example.TestClass");
    Method method = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .build();

    Set<Change<Method>> methodChanges = Set.of(Change.added(method));
    ChangeSet changeSet = new ChangeSet(null, typeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);
    String resultString = result.toString();

    assertNotNull(resultString);
    assertTrue(resultString.contains("Impact Analysis Result"));
    assertTrue(resultString.contains("Affected TypeDefs"));
    assertTrue(resultString.contains("Affected MethodReferences"));
  }

  @Test
  public void testImpactAnalysisResultSummary() {
    TypeDef typeDef = createSampleTypeDef("com.example.TestClass");
    Method method = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .build();

    Set<Change<Method>> methodChanges = Set.of(Change.added(method));
    ChangeSet changeSet = new ChangeSet(null, typeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);
    String summary = result.getSummary();

    assertNotNull(summary);
    assertTrue(summary.contains("Impact Summary"));
    assertTrue(summary.contains("files") || summary.contains("types") || summary.contains("methods"));

    assertEquals("TestClass.testMethod", result.getDependencyTree().render());
  }

  @Test
  public void testDirectMethodDependency() {
    // Create callee method
    Method calleeMethod = new MethodBuilder()
        .withName("helperMethod")
        .withReturnType(new VoidRef())
        .build();

    // Create caller method that calls helperMethod
    Method callerMethod = new MethodBuilder()
        .withName("callingMethod")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("helperMethod"))
        .endBlock()
        .build();

    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example")
        .withName("TestClass")
        .withMethods(calleeMethod, callerMethod)
        .build();

    // Add to repository for method reference resolution
    repository.register(typeDef);

    Set<Change<Method>> methodChanges = Set.of(Change.modified(calleeMethod, calleeMethod));
    ChangeSet changeSet = new ChangeSet(typeDef, typeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedMethodReferences().isEmpty());

    String expected = "TestClass.helperMethod\n" +
        "\\- TestClass.callingMethod";
    assertEquals(expected, result.getDependencyTree().render());

  }

  @Test
  public void testTransitiveMethodDependency() {
    // Create a chain: methodA -> methodB -> methodC
    Method methodC = new MethodBuilder()
        .withName("methodC")
        .withReturnType(new VoidRef())
        .build();

    Method methodB = new MethodBuilder()
        .withName("methodB")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("methodC"))
        .endBlock()
        .build();

    Method methodA = new MethodBuilder()
        .withName("methodA")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("methodB"))
        .endBlock()
        .build();

    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example")
        .withName("TransitiveTest")
        .withMethods(methodA, methodB, methodC)
        .build();

    repository.register(typeDef);

    // Change methodC - should affect methodB and methodA transitively
    Set<Change<Method>> methodChanges = Set.of(Change.modified(methodC, methodC));
    ChangeSet changeSet = new ChangeSet(typeDef, typeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());

    // Should contain the changed method
    assertTrue(result.getAffectedMethodReferences().stream()
        .anyMatch(ref -> ref.getMethod().getName().equals("methodC")));

    String expected = "TransitiveTest.methodC\n" +
        "\\- TransitiveTest.methodB\n" +
        "   \\- TransitiveTest.methodA";
    assertEquals(expected, result.getDependencyTree().render());

  }

  @Test
  public void testTransitiveMethodDependencyWithMultipleBranches() {
    // Create a tree structure: methodA -> methodB -> methodC
    //                          methodD -> methodB
    //                          methodE -> methodB
    Method methodC = new MethodBuilder()
        .withName("methodC")
        .withReturnType(new VoidRef())
        .build();

    Method methodB = new MethodBuilder()
        .withName("methodB")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("methodC"))
        .endBlock()
        .build();

    Method methodA = new MethodBuilder()
        .withName("methodA")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("methodB"))
        .endBlock()
        .build();

    Method methodD = new MethodBuilder()
        .withName("methodD")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("methodB"))
        .endBlock()
        .build();

    Method methodE = new MethodBuilder()
        .withName("methodE")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(new This().call("methodD"))
        .endBlock()
        .build();

    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example")
        .withName("MultiBranchTest")
        .withMethods(methodA, methodB, methodC, methodD, methodE)
        .build();

    repository.register(typeDef);

    // Change methodC - should affect methodB, and then methodA, methodD, and methodE transitively
    Set<Change<Method>> methodChanges = Set.of(Change.modified(methodC, methodC));
    ChangeSet changeSet = new ChangeSet(typeDef, typeDef, methodChanges, Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());

    // Should contain the changed method
    assertTrue(result.getAffectedMethodReferences().stream()
        .anyMatch(ref -> ref.getMethod().getName().equals("methodC")));

    String expected = "MultiBranchTest.methodC\n" +
        "\\- MultiBranchTest.methodB\n" +
        "   +- MultiBranchTest.methodD\n" +
        "   |  \\- MultiBranchTest.methodE\n" +
        "   \\- MultiBranchTest.methodA";
    System.out.println(result.getDependencyTree().render());
    assertEquals(expected, result.getDependencyTree().render());
  }

  private TypeDef createSampleTypeDef(String fullyQualifiedName) {
    return new TypeDefBuilder()
        .withPackageName(fullyQualifiedName.substring(0, fullyQualifiedName.lastIndexOf('.')))
        .withName(fullyQualifiedName.substring(fullyQualifiedName.lastIndexOf('.') + 1))
        .build();
  }
}
