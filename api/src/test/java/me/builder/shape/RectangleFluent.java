package me.builder.shape;

public interface RectangleFluent<T extends RectangleFluent<T>> extends ShapeFluent<T> {

    T withWidth(int width);
    T withHeight(int height);

}
