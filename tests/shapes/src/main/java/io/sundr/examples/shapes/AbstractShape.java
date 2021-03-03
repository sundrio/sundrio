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

package io.sundr.examples.shapes;

import java.util.Optional;
import java.util.OptionalInt;

import io.sundr.builder.annotations.Buildable;

@Buildable
public abstract class AbstractShape implements Shape {

  private final int x;
  private final int y;

  private Optional<String> notes;

  private OptionalInt count;

  public AbstractShape(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Optional<String> getNotes() {
    return notes;
  }

  public void setNotes(Optional<String> notes) {
    this.notes = notes;
  }

  public OptionalInt getCount() {
    return count;
  }

  public void setCount(OptionalInt count) {
    this.count = count;
  }
}
