/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.examples.shapes;

import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;
import io.sundr.builder.annotations.Inline;

@Buildable(inline = {
        @Inline(type = Createable.class, value = "create", prefix = "Createable"),
        @Inline(type = Updateable.class, value = "update", prefix = "Updateable"),
}, refs = {@BuildableReference(AbstractShape.class)})
public class Triangle<T extends Number> extends AbstractShape {

    private final T size;

    public Triangle(int x, int y, T size) {
        super(x, y);
        this.size = size;
    }

    public T getSize() {
        return size;
    }
}
