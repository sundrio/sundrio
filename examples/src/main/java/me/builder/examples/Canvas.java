package me.builder.examples;

import me.builder.annotations.Buildable;
import me.builder.examples.other.Square;

public class Canvas {

    private final Square area;
    private final Shape[] shapes;

    @Buildable
    public Canvas(Square area, Shape[] shapes) {
        this.area = area;
        this.shapes = shapes;
    }
}
