package io.sundr.adapter.source.change;

import static org.junit.Assert.*;

import org.junit.Test;

import io.sundr.model.*;

public class ChangeDetectorTest {

  @Test
  public void testNoChanges() {
    // Create identical TypeDefs
    TypeDef typeDef1 = createSimpleTypeDef();
    TypeDef typeDef2 = createSimpleTypeDef();

    ChangeSet changes = ChangeDetector.compare(typeDef1, typeDef2);

    assertFalse("Should have no changes", changes.hasChanges());
    assertEquals("Should have zero total changes", 0, changes.getTotalChanges());
    assertTrue("Method changes should be empty", changes.getMethodChanges().isEmpty());
    assertTrue("Property changes should be empty", changes.getPropertyChanges().isEmpty());
  }

  @Test
  public void testAddedMethod() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithAddedMethod();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue("Should have changes", changes.hasChanges());
    assertEquals("Should have one change", 1, changes.getTotalChanges());
    assertEquals("Should have one method change", 1, changes.getMethodChanges().size());
    assertTrue("Should have no property changes", changes.getPropertyChanges().isEmpty());

    Change<Method> methodChange = changes.getMethodChanges().iterator().next();
    assertEquals("Should be ADDED change", ChangeType.ADDED, methodChange.getChangeType());
    assertNull("Old element should be null", methodChange.getOldElement());
    assertNotNull("New element should not be null", methodChange.getNewElement());
    assertEquals("Method name should be 'newMethod'", "newMethod", methodChange.getNewElement().getName());
  }

  @Test
  public void testRemovedMethod() {
    TypeDef oldTypeDef = createTypeDefWithAddedMethod();
    TypeDef newTypeDef = createSimpleTypeDef();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue("Should have changes", changes.hasChanges());
    assertEquals("Should have one change", 1, changes.getTotalChanges());
    assertEquals("Should have one method change", 1, changes.getMethodChanges().size());

    Change<Method> methodChange = changes.getMethodChanges().iterator().next();
    assertEquals("Should be REMOVED change", ChangeType.REMOVED, methodChange.getChangeType());
    assertNotNull("Old element should not be null", methodChange.getOldElement());
    assertNull("New element should be null", methodChange.getNewElement());
    assertEquals("Method name should be 'newMethod'", "newMethod", methodChange.getOldElement().getName());
  }

  @Test
  public void testModifiedMethod() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithModifiedMethod();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue("Should have changes", changes.hasChanges());
    assertEquals("Should have one change", 1, changes.getTotalChanges());
    assertEquals("Should have one method change", 1, changes.getMethodChanges().size());

    Change<Method> methodChange = changes.getMethodChanges().iterator().next();
    assertEquals("Should be MODIFIED change", ChangeType.MODIFIED, methodChange.getChangeType());
    assertNotNull("Old element should not be null", methodChange.getOldElement());
    assertNotNull("New element should not be null", methodChange.getNewElement());
    assertEquals("Method names should be same",
        methodChange.getOldElement().getName(), methodChange.getNewElement().getName());
  }

  @Test
  public void testAddedProperty() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithAddedProperty();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue("Should have changes", changes.hasChanges());
    assertEquals("Should have one change", 1, changes.getTotalChanges());
    assertEquals("Should have one property change", 1, changes.getPropertyChanges().size());
    assertTrue("Should have no method changes", changes.getMethodChanges().isEmpty());

    Change<Property> propertyChange = changes.getPropertyChanges().iterator().next();
    assertEquals("Should be ADDED change", ChangeType.ADDED, propertyChange.getChangeType());
    assertNull("Old element should be null", propertyChange.getOldElement());
    assertNotNull("New element should not be null", propertyChange.getNewElement());
    assertEquals("Property name should be 'newProperty'", "newProperty", propertyChange.getNewElement().getName());
  }

  @Test
  public void testRemovedProperty() {
    TypeDef oldTypeDef = createTypeDefWithAddedProperty();
    TypeDef newTypeDef = createSimpleTypeDef();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue("Should have changes", changes.hasChanges());
    assertEquals("Should have one change", 1, changes.getTotalChanges());
    assertEquals("Should have one property change", 1, changes.getPropertyChanges().size());

    Change<Property> propertyChange = changes.getPropertyChanges().iterator().next();
    assertEquals("Should be REMOVED change", ChangeType.REMOVED, propertyChange.getChangeType());
    assertNotNull("Old element should not be null", propertyChange.getOldElement());
    assertNull("New element should be null", propertyChange.getNewElement());
    assertEquals("Property name should be 'newProperty'", "newProperty", propertyChange.getOldElement().getName());
  }

  @Test
  public void testMultipleChanges() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithMultipleChanges();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    assertTrue("Should have changes", changes.hasChanges());
    assertTrue("Should have multiple changes", changes.getTotalChanges() > 1);
    assertFalse("Should have method changes", changes.getMethodChanges().isEmpty());
    assertFalse("Should have property changes", changes.getPropertyChanges().isEmpty());
  }

  @Test
  public void testChangeSetToString() {
    TypeDef oldTypeDef = createSimpleTypeDef();
    TypeDef newTypeDef = createTypeDefWithAddedMethod();

    ChangeSet changes = ChangeDetector.compare(oldTypeDef, newTypeDef);

    String output = changes.toString();
    assertNotNull("String output should not be null", output);
    assertTrue("Should contain class name", output.contains("TestClass"));
    assertTrue("Should contain 'Method Changes'", output.contains("Method Changes"));
    assertTrue("Should contain change count", output.contains("(1)"));
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

    assertFalse("Should have no changes despite potential formatting differences", changes.hasChanges());
    assertEquals("Should have zero total changes", 0, changes.getTotalChanges());
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

    Property existingProperty = new PropertyBuilder()
        .withName("existingProperty")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    return new TypeDefBuilder()
        .withName("TestClass")
        .withPackageName("com.example")
        .withMethods(existingMethod)
        .withProperties(existingProperty)
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

    Property existingProperty = new PropertyBuilder()
        .withName("existingProperty")
        .withTypeRef(ClassRef.forClass(String.class))
        .build();

    return new TypeDefBuilder()
        .withName("TestClass")
        .withPackageName("com.example")
        .withMethods(modifiedMethod)
        .withProperties(existingProperty)
        .build();
  }

  private TypeDef createTypeDefWithAddedProperty() {
    TypeDef base = createSimpleTypeDef();

    Property newProperty = new PropertyBuilder()
        .withName("newProperty")
        .withTypeRef(ClassRef.forClass(Integer.class))
        .build();

    return new TypeDefBuilder(base)
        .addToProperties(newProperty)
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

    Property newProperty = new PropertyBuilder()
        .withName("anotherProperty")
        .withTypeRef(ClassRef.forClass(Double.class))
        .build();

    return new TypeDefBuilder(base)
        .addToMethods(newMethod)
        .addToProperties(newProperty)
        .build();
  }
}
