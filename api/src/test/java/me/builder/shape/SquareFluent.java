package me.builder.shape;

public interface SquareFluent<T extends SquareFluent<T>> extends ShapeFluent<T> {

    T withSize(int size);
}
