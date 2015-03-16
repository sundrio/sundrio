package me.builder.shape;

import me.builder.Builder;



public class CircleBuilder extends BaseCircleFluent<CircleBuilder> implements Builder<Circle> {

    @Override
    public Circle build() {
        return new Circle(x, y, radius);
    }
}
