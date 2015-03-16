package me.builder.shape;

import me.builder.Editable;

public class Circle implements Shape, Editable<CircleBuilder> {

    private final int x;
    private final int y;
    private final int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public CircleBuilder edit() {
        return new CircleBuilder().withX(x).withY(y).withRadius(radius);
    }
}
