package me.builder.shape;

import me.builder.Builder;

public class CanvasBuilder extends CanvasFluent<CanvasBuilder> implements Builder<Canvas> {

    @Override
    public Canvas build() {
        return new Canvas(shapes);
    }
}
