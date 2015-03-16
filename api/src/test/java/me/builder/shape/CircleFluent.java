package me.builder.shape;

public interface CircleFluent<T extends CircleFluent<T>> extends ShapeFluent<T> {

    T withRadius(int radius);

}
