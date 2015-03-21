package me.builder.examples.other;

import me.builder.annotations.Buildable;
import me.builder.examples.Shape;

public class Square extends Shape {

    private final Long size;
    
    @Buildable
    public Square(int x, int y, Long size) {
        super(x, y);
        this.size = size;
    }

    public Long getSize() {
        return size;
    }
}
