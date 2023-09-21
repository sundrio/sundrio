package io.sundr.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AssignTest {

  private static final ClassRef BOOLEAN = ClassRef.forName("java.lang.Boolean");
  private static final ClassRef STRING = ClassRef.forName("java.lang.String");
  private static final ClassRef OBJECT = ClassRef.forName("java.lang.Object");

  @Test
  public void testBooleanAssignment() {
    Property a = Property.newProperty(STRING, "a");
    Assign assign = new Assign(a, false);
    assertEquals("a = false", assign.render());
    assertEquals("a = false;", assign.asStatement().render());
  }

  @Test
  public void testNumberAssignment() {
    Property a = Property.newProperty(STRING, "a");
    Assign assign = new Assign(a, 1);
    assertEquals("a = 1", assign.render());
    assertEquals("a = 1;", assign.asStatement().render());
  }

  @Test
  public void testValueNumberArrayAssignment() {
    Property a = Property.newProperty(STRING, "a");
    Assign assign = new Assign(a, 1, 2);
    assertEquals("a = {1, 2}", assign.render());
    assertEquals("a = {1, 2};", assign.asStatement().render());
  }

  @Test
  public void testValueStringAssignment() {
    Property a = Property.newProperty(STRING, "a");
    Assign assign = new Assign(a, "hello world");
    assertEquals("a = \"hello world\"", assign.render());
    assertEquals("a = \"hello world\";", assign.asStatement().render());
  }

  @Test
  public void testValueStringArrayAssignment() {
    Property a = Property.newProperty(OBJECT, "a");
    Assign assign = new Assign(a, "hello", "world");
    assertEquals("a = {\"hello\", \"world\"}", assign.render());
    assertEquals("a = {\"hello\", \"world\"};", assign.asStatement().render());
  }

  @Test
  public void testValueRefAssignment() {
    Property a = Property.newProperty(STRING, "a");
    Property b = Property.newProperty(STRING, "b");

    Assign assign = new Assign(a, b);
    assertEquals("a = b", assign.render());
    assertEquals("a = b;", assign.asStatement().render());
  }

  @Test
  public void testClassRefAssignment() {
    Property a = Property.newProperty(STRING, "a");
    Assign assign = new Assign(a, STRING);
    assertEquals("a = java.lang.String.class", assign.render());
    assertEquals("a = java.lang.String.class;", assign.asStatement().render());
  }

  @Test
  public void testAssingWithExpression() {
    Property a = Property.newProperty(BOOLEAN, "a");
    Property b = Property.newProperty(STRING, "b");
    Property c = Property.newProperty(STRING, "c");
    Expression expr = new Equals(b, c);

    Assign assign = new Assign(a, expr);
    assertEquals("a = b == c", assign.render());
    assertEquals("a = b == c;", assign.asStatement().render());
  }
}
