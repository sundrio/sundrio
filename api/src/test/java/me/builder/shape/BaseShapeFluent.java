package me.builder.shape;

public class BaseShapeFluent<T extends BaseShapeFluent<T>> implements ShapeFluent<T> {

    int x;
    int y;

    @Override
    public T withX(int x) {
        this.x=x;
        return (T) this;
    }

    @Override
    public T withY(int y) {
        this.y=y;
        return (T) this;
    }
}
