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

package io.sundr.model;

import java.util.function.Function;

public interface Mappable<F> {

  /**
   * Accepts a converter {@link Function} and applies it to the current object.
   *
   * @param <T> the mapping output
   * @param mapper the mapper function to use
   * @return the result of the mapping
   */
  default <T> T map(Function<F, T> mapper) {
    return mapper.apply((F) this);
  }
}
