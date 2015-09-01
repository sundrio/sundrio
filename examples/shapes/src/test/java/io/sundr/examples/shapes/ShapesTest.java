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

import io.sundr.builder.Visitor;
import org.junit.Assert;
import org.junit.Test;

public class ShapesTest {

    @Test
    public void testCircleBuilder() {
        Circle circle = new CircleBuilder().withX(0).withY(0).withRadius(10).build();
        Assert.assertEquals(0, circle.getX());
        Assert.assertEquals(0, circle.getY());
        Assert.assertEquals(10, circle.getRadius());

    }

    @Test
    public void testCircleEdit() {
        EditableCircle circle = new CircleBuilder().withX(0).withY(0).withRadius(10).build();
        Assert.assertEquals(0, circle.getX());
        Assert.assertEquals(0, circle.getY());
        Assert.assertEquals(10, circle.getRadius());
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
                .addNewCircleShape()
                .withX(0)
                .withY(0)
                .withRadius(10)
                .and()
                .addNewSquareShape()
                .withY(10)
                .withY(20)
                .withHeight(30)
                .and()
                .build();

        canvas = new CanvasBuilder(canvas).accept(new Visitor() {
            @Override
            public void visit(Object element) {
                if (element instanceof CircleBuilder) {
                    CircleBuilder builder = (CircleBuilder) element;
                    builder.withRadius(100 + builder.getRadius());
                }
            }
        }).build();


        Assert.assertEquals(110, ((Circle)canvas.getShapes().get(0)).getRadius());

    }

    @Test
    public void testAddToWithVisitors() {
        Canvas canvas = new CanvasBuilder()
                .addToShapes(new CircleBuilder()
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

        canvas = new CanvasBuilder(canvas).accept(new Visitor() {
            @Override
            public void visit(Object element) {
                if (element instanceof CircleBuilder) {
                    CircleBuilder builder = (CircleBuilder) element;
                    builder.withRadius(100 + builder.getRadius());
                }
            }
        }).build();


        Assert.assertEquals(110, ((Circle)canvas.getShapes().get(0)).getRadius());
    }

    @Test
    public void testRemoveFrom() {
        Canvas canvas = new CanvasBuilder()
                .addToShapes(new CircleBuilder()
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

        canvas = new CanvasBuilder(canvas).accept(new Visitor() {
            @Override
            public void visit(Object element) {
                if (element instanceof CircleBuilder) {
                    CircleBuilder builder = (CircleBuilder) element;
                    builder.withRadius(100 + builder.getRadius());
                }
            }
        }).build();

        Assert.assertEquals(1, canvas.getShapes().size());

    }

}
