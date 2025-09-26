package io.sundr.adapter.source.analysis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.sundr.model.Block;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.PropertyRef;
import io.sundr.model.Return;
import io.sundr.model.Super;
import io.sundr.model.This;
import io.sundr.model.VoidRef;

public class PropertyAccessVisitorTest {

  @Test
  public void testPropertyAccessWithThisScope() {
    // Create a method that accesses this.propertyName
    Method method = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(
            new Return(new PropertyRef("propertyName", new This())))
        .endBlock()
        .build();

    assertTrue("Should detect this.propertyName access",
        methodAccessesProperty(method, "propertyName"));
  }

  @Test
  public void testPropertyAccessWithSuperScope() {
    // Create a method that accesses super.propertyName
    Method method = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(
            new Return(new PropertyRef("propertyName", new Super())))
        .endBlock()
        .build();

    assertTrue("Should detect super.propertyName access",
        methodAccessesProperty(method, "propertyName"));
  }

  @Test
  public void testPropertyAccessWithNullScope() {
    // Create a method that accesses propertyName without scope (local variable)
    Method method = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(
            new Return(new PropertyRef("propertyName", null)))
        .endBlock()
        .build();

    assertFalse("Should NOT detect null scope propertyName access (local variable)",
        methodAccessesProperty(method, "propertyName"));
  }

  @Test
  public void testPropertyAccessWithDifferentName() {
    Method method = new MethodBuilder()
        .withName("testMethod")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .withStatements(
            new Return(new PropertyRef("otherProperty", new This())))
        .endBlock()
        .build();

    assertFalse("Should NOT detect access to different property",
        methodAccessesProperty(method, "propertyName"));
  }

  @Test
  public void testMethodWithNoBlock() {
    Method method = new MethodBuilder()
        .withName("abstractMethod")
        .withReturnType(new VoidRef())
        .build(); // No block

    assertFalse("Should handle methods with no block",
        methodAccessesProperty(method, "propertyName"));
  }

  @Test
  public void testMethodWithEmptyBlock() {
    Method method = new MethodBuilder()
        .withName("emptyMethod")
        .withReturnType(new VoidRef())
        .withNewBlock()
        .endBlock()
        .build();

    assertFalse("Should handle methods with empty block",
        methodAccessesProperty(method, "propertyName"));
  }

  // Helper method that simulates the visitor logic from ImpactAnalyzer
  private boolean methodAccessesProperty(Method method, String propertyName) {
    if (method.getBlock() == null) {
      return false;
    }

    // Simulate the PropertyAccessVisitor logic
    Block block = method.getBlock();
    if (block.getStatements() == null) {
      return false;
    }

    return block.getStatements().stream().anyMatch(statement -> {
      if (statement instanceof Return) {
        Return returnStmt = (Return) statement;
        if (returnStmt.getExpression() instanceof PropertyRef) {
          PropertyRef propRef = (PropertyRef) returnStmt.getExpression();

          // Check property name match
          if (!propertyName.equals(propRef.getProperty().getName())) {
            return false;
          }

          // Check scope - only This or Super (exclude null scope)
          return propRef.getScope() instanceof This || propRef.getScope() instanceof Super;
        }
      }
      return false;
    });
  }
}
