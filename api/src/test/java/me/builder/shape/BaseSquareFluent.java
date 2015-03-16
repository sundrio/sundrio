package me.builder.shape;

public class BaseSquareFluent<T extends BaseSquareFluent<T>> extends BaseShapeFluent<T> implements SquareFluent<T> {

    int size=0;

    @Override
    public T withSize(int size) {
        this.size = size;
        return (T) this;
    }
}
