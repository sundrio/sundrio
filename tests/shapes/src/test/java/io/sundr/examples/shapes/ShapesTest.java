/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.examples.shapes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

import io.sundr.builder.PathAwareTypedVisitor;
import io.sundr.builder.Visitor;
import io.sundr.examples.shapes.v1.Circle;
import io.sundr.examples.shapes.v1.CircleBuilder;
import io.sundr.examples.shapes.v1.EditableCircle;
import io.sundr.examples.shapes.v1.Square;
import io.sundr.examples.shapes.v1.SquareBuilder;

public class ShapesTest {

  @Test
  public void testCircleBuilder() {
    Circle circle = new CircleBuilder<Integer>().withX(0).withY(0).withRadius(10).build();
    Assert.assertEquals(0, circle.getX());
    Assert.assertEquals(0, circle.getY());
    Assert.assertEquals(10, circle.getRadius());

  }

  @Test
  public void testCircleEdit() {
    EditableCircle<Integer> circle = new CircleBuilder<Integer>().withX(0).withY(0).withRadius(10).build();
    Assert.assertEquals(0, circle.getX());
    Assert.assertEquals(0, circle.getY());
    Assert.assertEquals(10, (int) circle.getRadius());
    Circle circle2 = circle.edit().build();
    Assert.assertEquals(0, circle2.getX());
    Assert.assertEquals(0, circle2.getY());
    Assert.assertEquals(10, circle2.getRadius());
    Circle circle3 = circle.edit().withX(1).withY(1).withRadius(20).build();
    Assert.assertEquals(1, circle3.getX());
    Assert.assertEquals(1, circle3.getY());
    Assert.assertEquals(20, circle3.getRadius());
  }

  @Test
  public void testVisitor() {
    Canvas canvas = new CanvasBuilder()
        .addNewCircleShape(0, 0, 10)
        .addNewSquareShape()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .and()
        .build();

    canvas = new CanvasBuilder(canvas).accept(CircleBuilder.class, b -> b.withRadius(100 + (int) b.getRadius())).build();
    Assert.assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
  }

  @Test
  public void testTypedVisitor() {
    Canvas canvas = new CanvasBuilder()
        .addNewCircleShape(0, 0, 10)
        .addNewSquareShape()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .and()
        .build();

    canvas = new CanvasBuilder(canvas).accept(new PathAwareTypedVisitor<CircleBuilder<Integer>, CanvasBuilder>() {
      @Override
      public void visit(List<Object> path, CircleBuilder<Integer> builder) {
        System.out.println("path:" + path);
        builder.withRadius(100 + builder.getRadius());
      }
    }).build();

    Assert.assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
  }

  @Test
  public void testVisitorFilter() {
    Canvas canvas = new CanvasBuilder()
        .addNewCircleShape(0, 0, 10)
        .addNewSquareShape()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .and()
        .build();

    //Add a visitor that is going to be filtered out.
    canvas = new CanvasBuilder(canvas).accept(new PathAwareTypedVisitor<CircleBuilder<Integer>, CanvasBuilder>() {
      @Override
      public void visit(CircleBuilder<Integer> builder) {
        builder.withRadius(100 + builder.getRadius());
      }

      @Override
      public Predicate<List<Object>> getRequirement() {
        return hasItem(CanvasBuilder.class, c -> !c.hasShapes());
      }
    }).build();

    Assert.assertEquals(10, ((Circle) canvas.getShapes().get(0)).getRadius());

    //Add a visitor that is not going to be filtered out.
    canvas = new CanvasBuilder(canvas).accept(new PathAwareTypedVisitor<CircleBuilder<Integer>, CanvasBuilder>() {
      @Override
      public void visit(CircleBuilder<Integer> builder) {
        builder.withRadius(200 + builder.getRadius());
      }

      @Override
      public Predicate<List<Object>> getRequirement() {
        return hasItem(CanvasBuilder.class, c -> c.hasShapes());
      }
    }).build();

    Assert.assertEquals(210, ((Circle) canvas.getShapes().get(0)).getRadius());

  }

  @Test
  public void testAddToWithVisitors() {
    CanvasBuilder canvasBuilder = new CanvasBuilder();
    canvasBuilder.addToShapes(new CircleBuilder<Integer>()
        .withX(0)
        .withY(0)
        .withRadius(10)
        .build());
    canvasBuilder.addToShapes(new SquareBuilder()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .build());
    Canvas canvas = canvasBuilder.build();

    canvas = new CanvasBuilder(canvas).accept(CircleBuilder.class, b -> b.withRadius(100 + (int) b.getRadius())).build();
    Assert.assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
  }

  @Test
  public void testAddToWithTypedVisitors() {
    Canvas canvas = new CanvasBuilder()
        .addToShapes(new CircleBuilder<Integer>()
            .withX(0)
            .withY(0)
            .withRadius(10)
            .build())
        .addToShapes(new SquareBuilder()
            .withY(10)
            .withY(20)
            .withHeight(30)
            .build())
        .build();

    canvas = new CanvasBuilder(canvas).accept(new Visitor<CircleBuilder<Integer>>() {
      @Override
      public void visit(CircleBuilder<Integer> builder) {
        builder.withRadius(100 + builder.getRadius());
      }
    }).build();

    Assert.assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
  }

  @Test
  public void testRemoveFrom() {
    Canvas canvas = new CanvasBuilder()
        .addToShapes(new CircleBuilder<Integer>()
            .withX(0)
            .withY(0)
            .withRadius(10)
            .build())
        .addToShapes(new SquareBuilder()
            .withY(10)
            .withY(20)
            .withHeight(30)
            .build())
        .build();

    Circle circle = (Circle) canvas.getShapes().get(0);
    canvas = new CanvasBuilder(canvas).removeFromShapes(circle).build();

    canvas = new CanvasBuilder(canvas).accept(new Visitor<CircleBuilder<Integer>>() {
      @Override
      public void visit(CircleBuilder<Integer> builder) {
        builder.withRadius(100 + builder.getRadius());
      }
    }).build();

    Assert.assertEquals(1, canvas.getShapes().size());

  }

  @Test
  public void testMultiType() {
    Canvas canvas = new CanvasBuilder()
        .addNewCircleShape()
        .withX(10)
        .withY(10)
        .withRadius(10.5)
        .and()
        .addNewCircleShape(0, 0, 10)
        .addNewSquareShape()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .and()
        .build();
  }

  @Test
  public void testWithSuperClassSetters() {
    Canvas canvas = new CanvasBuilder()
        .addNewCircleShape()
        .withX(10)
        .withY(10)
        .withRadius(10.5)
        .withNotes("circle1") //Even though 'notes' is ignored on circle, this field is inherited.
        .and()
        .addNewSquareShape()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .withNotes("square1")
        .and()
        .build();

    Circle circle = (Circle) canvas.getShapes().get(0);
    Assert.assertNull(circle.getNotes()); //This field is added to ignore properties.
    Square square = (Square) canvas.getShapes().get(1);
    Assert.assertEquals(Optional.of("square1"), square.getNotes());
  }

  @Test
  public void testHas() {
    CanvasBuilder builder = new CanvasBuilder()
        .addNewCircleShape(0, 0, 10)
        .addNewSquareShape()
        .withY(10)
        .withY(20)
        .withHeight(30)
        .endSquareShape();

    Assert.assertTrue(builder.hasShapes());
    builder.withShapes();
    Assert.assertFalse(builder.hasShapes());
  }

  @Test
  public void testAddNamedShapes() {
    Canvas canvas = new CanvasBuilder()
        .addNewValueToCircleNamedShapes("v1Circle")
        .withX(10).withY(20).withRadius(5.4).withNotes("CircleV1")
        .and().addNewValueToV2CircleNamedShapes("v2Circle")
        .withX(30).withY(40).withRadius(5.8).withNotes("CircleV2")
        .and().addNewValueToSquareNamedShapes("v1Square")
        .withX(50).withY(60).withNotes("SquareV1")
        .and().addNewValueToV2SquareNamedShapes("v2Square")
        .withX(70).withY(80).withNotes("v2Square")
        .and().build();

    Assert.assertNotNull(canvas.getNamedShapes());
    Assert.assertEquals("Should contain 4 named shapes.", 4, canvas.getNamedShapes().size());
    Assert.assertTrue(canvas.getNamedShapes().containsKey("v1Circle"));
    Assert.assertTrue(canvas.getNamedShapes().get("v1Circle") instanceof Circle);
    Assert.assertEquals(10, canvas.getNamedShapes().get("v1Circle").getX());
    Assert.assertEquals(20, canvas.getNamedShapes().get("v1Circle").getY());
    Assert.assertTrue(canvas.getNamedShapes().containsKey("v2Circle"));
    Assert.assertTrue(canvas.getNamedShapes().get("v2Circle") instanceof io.sundr.examples.shapes.v2.Circle);
    Assert.assertEquals(30, canvas.getNamedShapes().get("v2Circle").getX());
    Assert.assertEquals(40, canvas.getNamedShapes().get("v2Circle").getY());
  }

  @Test
  public void testEditNamedShape() {
    Canvas canvas = new Canvas(null, Collections.singletonMap("testShape", new Square(1, 2, 3)), null, null, null, null);
    Canvas modifiedCanvas = new CanvasBuilder(canvas)
        .editValueInSquareNamedShapes("testShape")
        .withX(5)
        .and().build();

    Assert.assertNotNull(modifiedCanvas.getNamedShapes());
    Assert.assertEquals("Should contain only one shape.", 1, modifiedCanvas.getNamedShapes().size());
    Assert.assertTrue(modifiedCanvas.getNamedShapes().containsKey("testShape"));
    Assert.assertEquals(5, modifiedCanvas.getNamedShapes().get("testShape").getX());
    Assert.assertEquals(2, modifiedCanvas.getNamedShapes().get("testShape").getY());
  }

  @Test(expected = RuntimeException.class)
  public void testExceptionOnWrongInstanceType() {
    Canvas canvas = new Canvas(null, Collections.singletonMap("testShape", new Square(1, 2, 3)), null, null, null, null);
    new CanvasBuilder(canvas)
        .editOrAddValueInCircleNamedShapes("testShape").and().build();
  }
}
