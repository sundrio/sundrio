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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import io.sundr.builder.PathAwareTypedVisitor;
import io.sundr.builder.Visitor;
import io.sundr.examples.shapes.v1.Circle;
import io.sundr.examples.shapes.v1.CircleBuilder;
import io.sundr.examples.shapes.v1.EditableCircle;
import io.sundr.examples.shapes.v1.Square;
import io.sundr.examples.shapes.v1.SquareBuilder;
import io.sundr.examples.v2.CitemBuilder;

public class ShapesTest {

  static final Shape ANON_SHAPE = new Shape() {

    @Override
    public int getX() {
      return 0;
    }

    @Override
    public int getY() {
      return 0;
    }

    @Override
    public Optional<String> getNotes() {
      return Optional.empty();
    }
  };

  @Test
  public void testArtistBuilder() {
    Artist artist = new ArtistBuilder().withNewOddity().withField("x").endOddity().build();
    assertEquals("x", artist.getOddity().getField());
  }

  @Test
  public void testCircleBuilder() {
    Circle circle = new CircleBuilder<Integer>().withX(0).withY(0).withRadius(10).build();
    assertEquals(0, circle.getX());
    assertEquals(0, circle.getY());
    assertEquals(10, circle.getRadius());

  }

  @Test
  public void testCircleEdit() {
    EditableCircle<Integer> circle = new CircleBuilder<Integer>().withX(0).withY(0).withRadius(10).build();
    assertEquals(0, circle.getX());
    assertEquals(0, circle.getY());
    assertEquals(10, (int) circle.getRadius());
    Circle circle2 = circle.edit().build();
    assertEquals(0, circle2.getX());
    assertEquals(0, circle2.getY());
    assertEquals(10, circle2.getRadius());
    Circle circle3 = circle.edit().withX(1).withY(1).withRadius(20).build();
    assertEquals(1, circle3.getX());
    assertEquals(1, circle3.getY());
    assertEquals(20, circle3.getRadius());
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
    assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
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
      public void visit(List<Entry<String, Object>> path, CircleBuilder<Integer> builder) {
        builder.withRadius(100 + builder.getRadius());
      }
    }).build();

    assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
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
      public Predicate<List<Entry<String, Object>>> getRequirement() {
        return hasItem(CanvasBuilder.class, c -> !c.hasShapes());
      }
    }).build();

    assertEquals(10, ((Circle) canvas.getShapes().get(0)).getRadius());

    //Add a visitor that is not going to be filtered out.
    canvas = new CanvasBuilder(canvas).accept(new PathAwareTypedVisitor<CircleBuilder<Integer>, CanvasBuilder>() {
      @Override
      public void visit(CircleBuilder<Integer> builder) {
        builder.withRadius(200 + builder.getRadius());
      }

      @Override
      public Predicate<List<Entry<String, Object>>> getRequirement() {
        return hasItem(CanvasBuilder.class, c -> c.hasShapes());
      }
    }).build();

    assertEquals(210, ((Circle) canvas.getShapes().get(0)).getRadius());

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
    assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
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

    assertEquals(110, ((Circle) canvas.getShapes().get(0)).getRadius());
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

    assertEquals(1, canvas.getShapes().size());

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
    assertNull(circle.getNotes()); //This field is added to ignore properties.
    Square square = (Square) canvas.getShapes().get(1);
    assertEquals(Optional.of("square1"), square.getNotes());
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

    assertTrue(builder.hasShapes());
    builder.withShapes();
    assertFalse(builder.hasShapes());
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

    assertNotNull(canvas.getNamedShapes());
    assertEquals(4, canvas.getNamedShapes().size(), "Should contain 4 named shapes.");
    assertTrue(canvas.getNamedShapes().containsKey("v1Circle"));
    assertTrue(canvas.getNamedShapes().get("v1Circle") instanceof Circle);
    assertEquals(10, canvas.getNamedShapes().get("v1Circle").getX());
    assertEquals(20, canvas.getNamedShapes().get("v1Circle").getY());
    assertTrue(canvas.getNamedShapes().containsKey("v2Circle"));
    assertTrue(canvas.getNamedShapes().get("v2Circle") instanceof io.sundr.examples.shapes.v2.Circle);
    assertEquals(30, canvas.getNamedShapes().get("v2Circle").getX());
    assertEquals(40, canvas.getNamedShapes().get("v2Circle").getY());
  }

  @Test
  public void testEditNamedShape() {
    Canvas canvas = new Canvas(null, Collections.singletonMap("testShape", new Square(1, 2, 3)), null, null, null, null);
    Canvas modifiedCanvas = new CanvasBuilder(canvas)
        .editValueInSquareNamedShapes("testShape")
        .withX(5)
        .and().build();

    assertNotNull(modifiedCanvas.getNamedShapes());
    assertEquals(1, modifiedCanvas.getNamedShapes().size(), "Should contain only one shape.");
    assertTrue(modifiedCanvas.getNamedShapes().containsKey("testShape"));
    assertEquals(5, modifiedCanvas.getNamedShapes().get("testShape").getX());
    assertEquals(2, modifiedCanvas.getNamedShapes().get("testShape").getY());
  }

  @Test
  public void testExceptionOnWrongInstanceType() {
    Canvas canvas = new Canvas(null, Collections.singletonMap("testShape", new Square(1, 2, 3)), null, null, null, null);
    assertThrows(RuntimeException.class, () -> new CanvasBuilder(canvas)
        .editOrAddValueInCircleNamedShapes("testShape").and().build());
  }

  @Test
  public void testWithNullBuildable() {
    Canvas canvas = new CanvasBuilder(new CanvasBuilder().withNewSquareCanvasShape(1, 1, 1).build())
        .withCanvasShape(null).build();

    assertNull(canvas.getCanvasShape());
  }

  @Test
  public void testWithUnknownType() {
    assertThrows(IllegalStateException.class, () -> new CanvasBuilder()
        .withCanvasShape(ANON_SHAPE).build());
  }

  @Test
  public void testAddToUnknownType() {
    assertThrows(IllegalStateException.class, () -> new CanvasBuilder()
        .addToShapes(0, ANON_SHAPE).build());
  }

  @Test
  public void testSetToUnknownType() {
    assertThrows(IllegalStateException.class, () -> new CanvasBuilder()
        .setToShapes(0, ANON_SHAPE).build());
  }

  @Test
  public void addToBuilderIndexing() {
    EditableCanvas canvas = new CanvasBuilder()
        .addToShapes(-1, new SquareBuilder()).build();
    assertEquals(1, canvas.getShapes().size());
  }

  @Test
  public void testDeprecatedField() throws Exception {
    assertNotNull(CanvasBuilder.class.getMethod("hasCanvasShape").getAnnotation(Deprecated.class));
  }

  @Test
  public void testWithBuildableVisitableTracking() throws Exception {
    CitemBuilder builder = new CitemBuilder();
    builder.withNewLabel().endLabel().withNewLabel().endLabel();
    assertEquals(1, builder.getVisitableMap().get().get("label").size());
  }

  @Test
  public void testBuildablePropertyMethodNames() throws Exception {
    CanvasBuilder builder = new CanvasBuilder();
    // should contain V1 begin / end methods
    builder.withNewV1().endV1();
    builder.withNewV2().endV2();
    // should not contain any methods with an additional prefix
    assertFalse(Stream.of(CanvasBuilder.class.getMethods()).map(m -> m.getName())
        .anyMatch(n -> n.contains("V1V1") || n.contains("V2V2")));
  }

}
