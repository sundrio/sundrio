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

package io.sundr.examples.shapes;

import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.Inline;

@Buildable(inline = {
        @Inline(type = Createable.class, value = "create", prefix = "Createable"),
        @Inline(type = Updateable.class, value = "update", prefix = "Updateable"),
})
public class Circle<T extends Number> implements Shape {

    private final int x;
    private final int y;
    private final T radius;
    private final Double area;

    private String notes;

    public Circle(int x, int y, T radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.area = Math.PI * Math.sqrt(radius.doubleValue());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public T getRadius() {
        return radius;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
