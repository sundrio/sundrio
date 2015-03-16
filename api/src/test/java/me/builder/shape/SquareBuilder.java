package me.builder.shape;

import me.builder.Builder;

public class SquareBuilder extends BaseSquareFluent<SquareBuilder> implements Builder<Square> {

    @Override
    public Square build() {
        return new Square(x, y, size);
    }
}
