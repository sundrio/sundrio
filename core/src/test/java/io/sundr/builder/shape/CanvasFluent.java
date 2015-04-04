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

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CanvasFluent<F extends CanvasFluent<F>> implements Fluent<F> {

    List<Shape> shapes = new CopyOnWriteArrayList<Shape>();

    F withShapes(List<Shape> shapes) {
        this.shapes = shapes;
        return (F) this;
    }

    F addShape(Shape shape) {
        this.shapes.add(shape);
        return (F) this;
    }

    CircleNested<F> addCircle() {
        return new CircleNested<F>();
    }


    class CircleNested<T> extends BaseCircleFluent<CircleNested<T>> implements Nested<T> {
        private CircleBuilder builder = new CircleBuilder();

        @Override
        public T  and() {
            return (T) addShape(builder.build());
        }
    }
}
