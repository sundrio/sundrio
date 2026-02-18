/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.examples.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.sundr.builder.Visitor;

public class TriangleTest {

  @Test
  public void testAddingTriangle() {
    Triangle triangle = new Triangle(0, 0, 1);

    Canvas canvas = new CanvasBuilder()
        .addToShapes(triangle)
        .accept(new Visitor<TriangleBuilder<Integer>>() {
          @Override
          public void visit(TriangleBuilder<Integer> builder) {
            builder.withX(10).withY(10);
          }
        })
        .build();

    assertFalse(canvas.getShapes().isEmpty());
    assertEquals(10, canvas.getShapes().get(0).getX());

    //Now let's 'revert' the change so that we can remove the item.
    //Reverting is needed in this case so that equals() on Triagnle returns true.
    canvas = new CanvasBuilder(canvas)
        .accept(new Visitor<TriangleBuilder>() {
          @Override
          public void visit(TriangleBuilder builder) {
            builder.withX(0).withY(0);
          }
        })
        .build();

    assertFalse(canvas.getShapes().isEmpty());
    assertEquals(0, canvas.getShapes().get(0).getX());

    canvas = new CanvasBuilder(canvas)
        .removeFromShapes(triangle)
        .build();

    assertTrue(canvas.getShapes().isEmpty());
  }

  @Test
  public void testAddingTriangleBuilder() {
    TriangleBuilder<Integer> triangle = new TriangleBuilder<Integer>().withSize(1);

    Canvas canvas = new CanvasBuilder()
        .addToShapes(triangle)
        .accept(new Visitor<TriangleBuilder<Integer>>() {
          @Override
          public void visit(TriangleBuilder<Integer> builder) {
            builder.withX(10).withY(10);
          }
        })
        .build();

    assertFalse(canvas.getShapes().isEmpty());
    assertEquals(10, canvas.getShapes().get(0).getX());

    //Note: The visitor is actually mutating the original triangle builder instance. So no need to revert the change.
    canvas = new CanvasBuilder(canvas)
        .removeFromShapes(triangle)
        .build();

    assertTrue(canvas.getShapes().isEmpty());
  }
}
