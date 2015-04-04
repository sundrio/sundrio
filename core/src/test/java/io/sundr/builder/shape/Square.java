/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.builder.shape;

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
