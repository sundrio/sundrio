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
import io.sundr.model.Return;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.VoidRef;
import io.sundr.model.repo.DefinitionRepository;

public class ImpactAnalysisIntegrationTest {

  private Project project;
  private ImpactAnalyzer analyzer;
  private DefinitionRepository repository;

  @Before
  public void setUp() {
    // Use current project for testing
    project = Project.getProject();
    repository = DefinitionRepository.getRepository();
    analyzer = new ImpactAnalyzer(project, repository);
  }

  @Test
  public void testMethodChangeImpactAnalysis() {
    // Create a sample TypeDef with methods
    TypeDef oldTypeDef = new TypeDefBuilder()
        .withPackageName("com.example")
        .withName("Calculator")
        .addNewMethod()
        .withName("add")
        .withReturnType(ClassRef.forClass(int.class))
        .addNewArgument()
        .withName("a")
        .withTypeRef(ClassRef.forClass(int.class))
        .endArgument()
        .addNewArgument()
        .withName("b")
        .withTypeRef(ClassRef.forClass(int.class))
        .endArgument()
        .endMethod()
        .build();

    TypeDef newTypeDef = new TypeDefBuilder(oldTypeDef)
        .editMethod(0)
        .withNewBlock()
        .withStatements(new Return(Property.newProperty("a").plus(Property.newProperty("a"))))
        .endBlock()
        .endMethod()
        .build();

    // Create a ChangeSet
    Method oldMethod = oldTypeDef.getMethods().get(0);
    Method newMethod = newTypeDef.getMethods().get(0);

    ChangeSet changeSet = new ChangeSet(
        oldTypeDef,
        newTypeDef,
        Set.of(Change.modified(oldMethod, newMethod)),
        Set.of());

    // Analyze impact
    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    // Verify results
    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedTypeDefs().isEmpty());
    assertFalse(result.getAffectedMethodReferences().isEmpty());

    // Should include the directly changed type
    assertTrue(result.getAffectedTypeDefs().contains(newTypeDef));
  }

  @Test
  public void testPropertyChangeImpactAnalysis() {
    TypeDef oldTypeDef = new TypeDefBuilder()
        .withPackageName("com.example")
        .withName("Person")
        .addNewProperty()
        .withName("name")
        .withTypeRef(ClassRef.forClass(String.class))
        .endProperty()
        .build();

    TypeDef newTypeDef = new TypeDefBuilder(oldTypeDef)
        .editProperty(0)
        .withTypeRef(ClassRef.forClass(Object.class)) // Changed type
        .endProperty()
        .build();

    Property oldProperty = oldTypeDef.getProperties().get(0);
    Property newProperty = newTypeDef.getProperties().get(0);

    ChangeSet changeSet = new ChangeSet(
        oldTypeDef,
        newTypeDef,
        Set.of(),
        Set.of(Change.modified(oldProperty, newProperty)));

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    assertNotNull(result);
    assertTrue(result.hasAnyImpact());
    assertFalse(result.getAffectedTypeDefs().isEmpty());
  }

  @Test
  public void testEmptyChangeSet() {
    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example")
        .withName("Empty")
        .build();

    ChangeSet emptyChangeSet = new ChangeSet(typeDef, typeDef, Set.of(), Set.of());

    ImpactAnalysisResult result = analyzer.analyze(emptyChangeSet);

    assertNotNull(result);
    assertFalse(result.hasAnyImpact());
    assertEquals(0, result.getTotalImpactCount());
  }

  @Test
  public void testNullChangeSet() {
    ImpactAnalysisResult result = analyzer.analyze(null);

    assertNotNull(result);
    assertFalse(result.hasAnyImpact());
    assertEquals(0, result.getTotalImpactCount());
  }

  @Test
  public void testImpactAnalysisResultGrouping() {
    // Create a more complex change set
    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("com.example.service")
        .withName("UserService")
        .addNewMethod()
        .withName("createUser")
        .withReturnType(new VoidRef())
        .endMethod()
        .build();

    Method addedMethod = new MethodBuilder()
        .withName("deleteUser")
        .withReturnType(new VoidRef())
        .build();

    ChangeSet changeSet = new ChangeSet(
        null,
        typeDef,
        Set.of(Change.added(addedMethod)),
        Set.of());

    ImpactAnalysisResult result = analyzer.analyze(changeSet);

    // Test grouping methods
    var filesByDirectory = result.getAffectedFilesByDirectory();
    var typesByPackage = result.getAffectedTypeDefsByPackage();

    assertNotNull(filesByDirectory);
    assertNotNull(typesByPackage);

    // Should have grouped types by package
    assertTrue(typesByPackage.containsKey("com.example.service"));
  }

  @Test
  public void testProjectIntegrationWithImpactAnalysis() {
    // Test integration with actual Project instance
    ImpactAnalysisResult result = project.analyzeImpact(createSampleChangeSet());

    assertNotNull(result);
    // Result can be empty if no dependencies found, which is fine for this test
  }

  private ChangeSet createSampleChangeSet() {
    TypeDef typeDef = new TypeDefBuilder()
        .withPackageName("io.sundr.test")
        .withName("TestClass")
        .addNewMethod()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .endMethod()
        .build();

    return new ChangeSet(null, typeDef, Set.of(), Set.of());
  }
}
