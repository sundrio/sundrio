/*
 *      Copyright 2016 The original authors.
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

package io.sundr.examples.shapes.v1;

import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.Inline;
import io.sundr.examples.shapes.AbstractShape;
import io.sundr.examples.shapes.Createable;
import io.sundr.examples.shapes.Updateable;

@Buildable(inline = {
    @Inline(type = Createable.class, value = "create", prefix = "Createable"),
    @Inline(type = Updateable.class, value = "update", prefix = "Updateable"),
}, ignore = { "notes" })
public class Circle<T extends Number> extends AbstractShape {

  private final T radius;
  private final Double area;

  public Circle(int x, int y, T radius) {
    super(x, y);
    this.radius = radius;
    this.area = Math.PI * Math.sqrt(radius.doubleValue());
  }

  public T getRadius() {
    return radius;
  }

}
