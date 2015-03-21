package me.builder.examples;

import me.builder.annotations.Buildable;

public class Shape {

    private final int x;
    private final int y;

    @Buildable
    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
