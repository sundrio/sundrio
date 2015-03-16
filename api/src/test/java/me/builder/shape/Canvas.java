package me.builder.shape;

import me.builder.Editable;

import java.util.Collections;
import java.util.List;

public class Canvas implements Editable<CanvasBuilder> {

    private final List<Shape> shapes;

    public Canvas(List<Shape> shapes) {
        this.shapes = Collections.unmodifiableList(shapes);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public CanvasBuilder edit() {
        return new CanvasBuilder().withShapes(shapes);
    }
}
