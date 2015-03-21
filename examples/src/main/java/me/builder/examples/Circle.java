package me.builder.examples;

import me.builder.annotations.Buildable;

public class Circle extends Shape {

    private final Long radius;

    @Buildable
    public Circle(int x, int y, long radius) {
        super(x, y);
        this.radius = radius;
    }

    public Long getRadius() {
        return radius;
    }
}
