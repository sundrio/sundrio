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

import org.junit.Assert;
import org.junit.Test;

import io.sundr.builder.PathAwareTypedVisitor;
import io.sundr.builder.TypedVisitor;
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

        canvas = new CanvasBuilder(canvas).accept(new Visitor<CircleBuilder<Integer>>() {
            @Override
            public void visit(CircleBuilder<Integer> builder) {
                builder.withRadius(100 + builder.getRadius());
            }
        }).build();


        Assert.assertEquals(110, ((Circle)canvas.getShapes().get(0)).getRadius());
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
            public void visit(CircleBuilder<Integer> builder) {
                builder.withRadius(100 + builder.getRadius());
            }
        }).build();


        Assert.assertEquals(110, ((Circle)canvas.getShapes().get(0)).getRadius());
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

        canvas = new CanvasBuilder(canvas).accept(new Visitor<CircleBuilder<Integer>>() {
            @Override
            public void visit(CircleBuilder<Integer> builder) {
                builder.withRadius(100 + builder.getRadius());
            }
        }).build();


        Assert.assertEquals(110, ((Circle)canvas.getShapes().get(0)).getRadius());
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

        canvas = new CanvasBuilder(canvas).accept(new TypedVisitor<CircleBuilder<Integer>>() {
            @Override
            public void visit(CircleBuilder<Integer> builder) {
                builder.withRadius(100 + builder.getRadius());
            }
        }).build();


        Assert.assertEquals(110, ((Circle)canvas.getShapes().get(0)).getRadius());
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
                .withNotes("circle1")
                .and()
                .addNewSquareShape()
                .withY(10)
                .withY(20)
                .withHeight(30)
                .withNotes("square1")
                .and()
                .build();

        Circle circle = (Circle) canvas.getShapes().get(0);
        Assert.assertEquals("circle1", circle.getNotes());
        Square square = (Square) canvas.getShapes().get(1);
        Assert.assertEquals("square1", square.getNotes());
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
}
