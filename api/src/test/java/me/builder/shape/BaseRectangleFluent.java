package me.builder.shape;

public class BaseRectangleFluent<T extends BaseRectangleFluent<T>> extends BaseShapeFluent<T> implements RectangleFluent<T> {

    int width=0;
    int height=0;

    @Override
    public T withWidth(int width) {
        this.width = width;
        return (T) this;
    }

    @Override
    public T withHeight(int height) {
        this.height = height;
        return (T) this;
    }
}
