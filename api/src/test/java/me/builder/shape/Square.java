package me.builder.shape;

public class Square implements Rectangle {

    private final int x;
    private final int y;
    private final int edgeLength;

    public Square(int x, int y, int edgeLength) {
        this.x = x;
        this.y = y;
        this.edgeLength = edgeLength;
    }

    @Override
    public int getWidth() {
        return edgeLength;
    }

    @Override
    public int getHeight() {
        return edgeLength;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
