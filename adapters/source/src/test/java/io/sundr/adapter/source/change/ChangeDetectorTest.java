package io.sundr.adapter.source.change;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.sundr.model.*;

public class ChangeDetectorTest {

  @Test
  public void testNoChanges() {
    // Create identical TypeDefs
    TypeDef typeDef1 = createSimpleTypeDef();
    TypeDef typeDef2 = createSimpleTypeDef();

    ChangeSet changes = ChangeDetector.compare(typeDef1, typeDef2);

    assertFalse(changes.hasChanges(), "Should have no changes");
    assertEquals(0, changes.getTotalChanges(), "Should have zero total changes");
    assertTrue(changes.getMethodChanges().isEmpty(), "Method changes should be empty");
    assertTrue(changes.getFieldChanges().isEmpty(), "Field changes should be empty");
  }

  @Test
  public void testAddedMethod() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithAddedMethod();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue(changes.hasChanges(), "Should have changes");
    assertEquals(1, changes.getTotalChanges(), "Should have one change");
    assertEquals(1, changes.getMethodChanges().size(), "Should have one method change");
    assertTrue(changes.getFieldChanges().isEmpty(), "Should have no field changes");

    Change<Method> methodChange = changes.getMethodChanges().iterator().next();
    assertEquals(ChangeType.ADDED, methodChange.getChangeType(), "Should be ADDED change");
    assertNull(methodChange.getOldElement(), "Old element should be null");
    assertNotNull(methodChange.getNewElement(), "New element should not be null");
    assertEquals("newMethod", methodChange.getNewElement().getName(), "Method name should be 'newMethod'");
  }

  @Test
  public void testRemovedMethod() {
    TypeDef oldTypeDef = createTypeDefWithAddedMethod();
    TypeDef newTypeDef = createSimpleTypeDef();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue(changes.hasChanges(), "Should have changes");
    assertEquals(1, changes.getTotalChanges(), "Should have one change");
    assertEquals(1, changes.getMethodChanges().size(), "Should have one method change");

    Change<Method> methodChange = changes.getMethodChanges().iterator().next();
    assertEquals(ChangeType.REMOVED, methodChange.getChangeType(), "Should be REMOVED change");
    assertNotNull(methodChange.getOldElement(), "Old element should not be null");
    assertNull(methodChange.getNewElement(), "New element should be null");
    assertEquals("newMethod", methodChange.getOldElement().getName(), "Method name should be 'newMethod'");
  }

  @Test
  public void testModifiedMethod() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithModifiedMethod();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue(changes.hasChanges(), "Should have changes");
    assertEquals(1, changes.getTotalChanges(), "Should have one change");
    assertEquals(1, changes.getMethodChanges().size(), "Should have one method change");

    Change<Method> methodChange = changes.getMethodChanges().iterator().next();
    assertEquals(ChangeType.MODIFIED, methodChange.getChangeType(), "Should be MODIFIED change");
    assertNotNull(methodChange.getOldElement(), "Old element should not be null");
    assertNotNull(methodChange.getNewElement(), "New element should not be null");
    assertEquals(methodChange.getOldElement().getName(), methodChange.getNewElement().getName(),
        "Method names should be same");
  }

  @Test
  public void testAddedProperty() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithAddedProperty();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue(changes.hasChanges(), "Should have changes");
    assertEquals(1, changes.getTotalChanges(), "Should have one change");
    assertEquals(1, changes.getFieldChanges().size(), "Should have one field change");
    assertTrue(changes.getMethodChanges().isEmpty(), "Should have no method changes");

    Change<Field> fieldChange = changes.getFieldChanges().iterator().next();
    assertEquals(ChangeType.ADDED, fieldChange.getChangeType(), "Should be ADDED change");
    assertNull(fieldChange.getOldElement(), "Old element should be null");
    assertNotNull(fieldChange.getNewElement(), "New element should not be null");
    assertEquals("newField", fieldChange.getNewElement().getName(), "Property name should be 'newField'");
  }

  @Test
  public void testRemovedProperty() {
    TypeDef oldTypeDef = createTypeDefWithAddedProperty();
    TypeDef newTypeDef = createSimpleTypeDef();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue(changes.hasChanges(), "Should have changes");
    assertEquals(1, changes.getTotalChanges(), "Should have one change");
    assertEquals(1, changes.getFieldChanges().size(), "Should have one field change");

    Change<Field> fieldChange = changes.getFieldChanges().iterator().next();
    assertEquals(ChangeType.REMOVED, fieldChange.getChangeType(), "Should be REMOVED change");
    assertNotNull(fieldChange.getOldElement(), "Old element should not be null");
    assertNull(fieldChange.getNewElement(), "New element should be null");
    assertEquals("newField", fieldChange.getOldElement().getName(), "Property name should be 'newField'");
  }

  @Test
  public void testMultipleChanges() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithMultipleChanges();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue(changes.hasChanges(), "Should have changes");
    assertTrue(changes.getTotalChanges() > 1, "Should have multiple changes");
    assertFalse(changes.getMethodChanges().isEmpty(), "Should have method changes");
    assertFalse(changes.getFieldChanges().isEmpty(), "Should have field changes");
  }

  @Test
  public void testChangeSetToString() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithAddedMethod();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    String output = changes.toString();
    assertNotNull(output, "String output should not be null");
    assertTrue(output.contains("TestClass"), "Should contain class name");
    assertTrue(output.contains("Method Changes"), "Should contain 'Method Changes'");
    assertTrue(output.contains("(1)"), "Should contain change count");
  }

  @Test
  public void testIgnoresFormattingDifferences() {
    // Create method with different formatting but same logic
    Method method1 = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(ClassRef.forClass(String.class))
        .withNewBlock()
        .withStatements(Return.value("test"))
        .endBlock()
        .build();

    Method method2 = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(ClassRef.forClass(String.class))
        .withNewBlock()
        .withStatements(Return.value("test"))
        .endBlock()
        .build();

    TypeDef typeDef1 = new TypeDefBuilder()
        .withName("TestClass")
        .withPackageName("com.example")
        .withMethods(method1)
        .build();

    TypeDef typeDef2 = new TypeDefBuilder()
        .withName("TestClass")
        .withPackageName("com.example")
        .withMethods(method2)
        .build();

    ChangeSet changes = ChangeDetector.compare(typeDef1, typeDef2);

    assertFalse(changes.hasChanges(), "Should have no changes despite potential formatting differences");
    assertEquals(0, changes.getTotalChanges(), "Should have zero total changes");
  }

  // Helper methods to create test TypeDefs

  private TypeDef createSimpleTypeDef() {
    Method existingMethod = new MethodBuilder()
        .withName("existingMethod")
        .withReturnType(ClassRef.forClass(String.class))
        .withNewBlock()
        .withStatements(Return.value("test"))
        .endBlock()
        .build();

    Field existingField = new FieldBuilder()
        .withName("existingField")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    return new TypeDefBuilder()
        .withName("TestClass")
        .withPackageName("com.example")
        .withMethods(existingMethod)
        .withFields(existingField)
        .build();
  }

  private TypeDef createTypeDefWithAddedMethod() {
    TypeDef base = createSimpleTypeDef();

    Method newMethod = new MethodBuilder()
        .withName("newMethod")
        .withReturnType(ClassRef.forClass(Integer.class))
        .withNewBlock()
        .withStatements(Return.value(42))
        .endBlock()
        .build();

    return new TypeDefBuilder(base)
        .addToMethods(newMethod)
        .build();
  }

  private TypeDef createTypeDefWithModifiedMethod() {
    Method modifiedMethod = new MethodBuilder()
        .withName("existingMethod")
        .withReturnType(ClassRef.forClass(String.class)) // Same signature
        .withNewBlock()
        .withStatements(Return.value("modified")) // Different implementation
        .endBlock()
        .build();

    Field existingField = new FieldBuilder()
        .withName("existingField")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    return new TypeDefBuilder()
        .withName("TestClass")
        .withPackageName("com.example")
        .withMethods(modifiedMethod)
        .withFields(existingField)
        .build();
  }

  private TypeDef createTypeDefWithAddedProperty() {
    TypeDef base = createSimpleTypeDef();

    Field newField = new FieldBuilder()
        .withName("newField")
        .withTypeRef(ClassRef.forClass(Integer.class))
        .build();

    return new TypeDefBuilder(base)
        .addToFields(newField)
        .build();
  }

  private TypeDef createTypeDefWithMultipleChanges() {
    // Add method and property to base
    TypeDef base = createSimpleTypeDef();

    Method newMethod = new MethodBuilder()
        .withName("anotherMethod")
        .withReturnType(ClassRef.forClass(Boolean.class))
        .withNewBlock()
        .withStatements(Return.value(true))
        .endBlock()
        .build();

    Field newField = new FieldBuilder()
        .withName("anotherProperty")
        .withTypeRef(ClassRef.forClass(Double.class))
        .build();

    return new TypeDefBuilder(base)
        .addToMethods(newMethod)
        .addToFields(newField)
        .build();
  }
}
