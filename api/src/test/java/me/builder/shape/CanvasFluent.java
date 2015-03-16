package me.builder.shape;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CanvasFluent<F extends CanvasFluent<F>> implements Fluent<F> {

    List<Shape> shapes = new CopyOnWriteArrayList<Shape>();

    F withShapes(List<Shape> shapes) {
        this.shapes = shapes;
        return (F) this;
    }

    F addShape(Shape shape) {
        this.shapes.add(shape);
        return (F) this;
    }

    CircleNested<F> addCircle() {
        return new CircleNested<F>();
    }


    class CircleNested<T> extends BaseCircleFluent<CircleNested<T>> implements Nested<T> {
        private CircleBuilder builder = new CircleBuilder();

        @Override
        public T  and() {
            return (T) addShape(builder.build());
        }
    }
}
