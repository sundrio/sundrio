package me.builder.shape;

import me.builder.Fluent;

public interface ShapeFluent<T extends ShapeFluent<T>> extends Fluent<T> {

    T withX(int x);
    T withY(int y);
}
