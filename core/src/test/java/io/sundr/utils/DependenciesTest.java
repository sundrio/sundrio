package io.sundr.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class DependenciesTest {

  @Test
  public void testTreeHeight1() {
    // Simple tree with just a root node
    var tree = Dependencies.<String> newTree()
        .addDependency("Root");

    String result = tree.render();
    assertEquals("Root", result);
  }

  @Test
  public void testTreeHeight2() {
    // Tree with root and direct children
    var tree = Dependencies.<String> newTree()
        .addDependency("Root")
        .addDependency("Root", "Child1", "Child2", "Child3");

    String result = tree.render();
    String expected = "Root\n" +
        "+- Child1\n" +
        "+- Child2\n" +
        "\\- Child3";
    assertEquals(expected, result);
  }

  @Test
  public void testTreeHeight3() {
    // Tree with 3 levels: root -> children -> grandchildren
    var tree = Dependencies.<String> newTree()
        .addDependency("Root")
        .addDependency("Root", "Child1", "Child2")
        .addDependency("Child1", "Grandchild1", "Grandchild2")
        .addDependency("Child2", "Grandchild3");

    String result = tree.render();
    String expected = "Root\n" +
        "+- Child1\n" +
        "|  +- Grandchild1\n" +
        "|  \\- Grandchild2\n" +
        "\\- Child2\n" +
        "   \\- Grandchild3";
    assertEquals(expected, result);
  }

  @Test
  public void testTreeHeight4() {
    // Tree with 4 levels: root -> children -> grandchildren -> great-grandchildren
    var tree = Dependencies.<String> newTree()
        .addDependency("Root")
        .addDependency("Root", "Child1", "Child2")
        .addDependency("Child1", "Grandchild1", "Grandchild2")
        .addDependency("Child2", "Grandchild3")
        .addDependency("Grandchild1", "GreatGrandchild1", "GreatGrandchild2")
        .addDependency("Grandchild2", "GreatGrandchild3")
        .addDependency("Grandchild3", "GreatGrandchild4");

    String result = tree.render();
    String expected = "Root\n" +
        "+- Child1\n" +
        "|  +- Grandchild1\n" +
        "|  |  +- GreatGrandchild1\n" +
        "|  |  \\- GreatGrandchild2\n" +
        "|  \\- Grandchild2\n" +
        "|     \\- GreatGrandchild3\n" +
        "\\- Child2\n" +
        "   \\- Grandchild3\n" +
        "      \\- GreatGrandchild4";
    assertEquals(expected, result);
  }

  @Test
  public void testTreeWithCycles() {
    // Test cycle detection
    var tree = Dependencies.<String> newTree()
        .addDependency("A")
        .addDependency("A", "B")
        .addDependency("B", "C")
        .addDependency("C", "A"); // Creates a cycle

    String result = tree.render();
    // In a cycle, all nodes appear as dependencies, so the fallback renders all nodes
    String expected = "A\n" +
        "\\- B\n" +
        "   \\- C\n" +
        "      \\- A (cycle)\n" +
        "B\n" +
        "\\- C\n" +
        "   \\- A\n" +
        "      \\- B (cycle)\n" +
        "C\n" +
        "\\- A\n" +
        "   \\- B\n" +
        "      \\- C (cycle)";
    assertEquals(expected, result);
  }

  @Test
  public void testMultipleRoots() {
    // Test forest with multiple root nodes
    var tree = Dependencies.<String> newTree()
        .addDependency("Root1")
        .addDependency("Root2")
        .addDependency("Root1", "Child1")
        .addDependency("Root2", "Child2");

    String result = tree.render();
    String expected = "Root1\n" +
        "\\- Child1\n" +
        "Root2\n" +
        "\\- Child2";
    assertEquals(expected, result);
  }

  @Test
  public void testWithCustomFormatter() {
    // Test with custom formatter
    var tree = Dependencies.<Integer> newTree(i -> "Node_" + i)
        .addDependency(1)
        .addDependency(1, 2, 3)
        .addDependency(2, 4);

    String result = tree.render();
    String expected = "Node_1\n" +
        "+- Node_2\n" +
        "|  \\- Node_4\n" +
        "\\- Node_3";
    assertEquals(expected, result);
  }

  @Test
  public void testRenderFrom() {
    // Test rendering from a specific node
    var tree = Dependencies.<String> newTree()
        .addDependency("Root")
        .addDependency("Root", "Child1", "Child2")
        .addDependency("Child1", "Grandchild1");

    String result = tree.renderFrom("Child1");
    String expected = "Child1\n" +
        "\\- Grandchild1";
    assertEquals(expected, result);
  }
}
