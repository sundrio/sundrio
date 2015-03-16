package me.builder.shape;

public class BaseCircleFluent<T extends BaseCircleFluent<T>> extends BaseShapeFluent<T> implements CircleFluent<T> {

    int radius;

    @Override
    public T withRadius(int radius) {
        this.radius = radius;
        return (T) this;
    }
}
