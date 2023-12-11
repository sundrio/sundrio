/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.codegen.api;

import java.util.function.Function;

/**
 * The part of the CodeGenerator that renders an instance of {@literal <}T{@literal >} into a {@link String}.
 */
public interface Renderer<T> {

  /**
   * The type of items this {@link Renderer} supports.
   *
   * @return the item class
   */
  Class<T> getType();

  Function<T, String> getFunction();

  /**
   * The rendering method.
   *
   * @param item the item to render
   * @return the {@link String} representation of the item.
   */
  default String render(T item) {
    return getFunction().apply(item);
  }
}
