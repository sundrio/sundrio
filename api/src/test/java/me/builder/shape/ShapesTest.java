package me.builder.shape;

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
        Circle circle = new CircleBuilder().withX(0).withY(0).withRadius(10).build();
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
    public void testCanvasBuilder() {
        Canvas canvas = new CanvasBuilder().addCircle().withY(1).withX(0).withRadius(10).and().build();
        Assert.assertFalse(canvas.getShapes().isEmpty());
    }
}
