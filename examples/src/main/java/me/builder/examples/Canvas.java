package me.builder.examples;

import me.builder.annotations.Buildable;

public class Canvas {
    
    private final Shape shape;

    @Buildable
    public Canvas(Shape shape) {
        this.shape = shape;
    }
}
