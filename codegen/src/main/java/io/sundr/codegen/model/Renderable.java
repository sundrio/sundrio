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

package io.sundr.codegen.model;

public interface Renderable {

  /**
   * Render the type into a {@link String} for the purpose of code generation.
   * This is slightly different from the `toString()` method as `toString()` is mostly needed for logging / debugging and should
   * be idempotent.
   * Regarding idempotency this method may yield different result based on the context it is used even if the internal state of
   * the object does not change.
   * An example of such case, is a reference to class that may change based on the package from which it's refenced.
   *
   * @return the {@link String} representation of the object as it's meant to appear in the generated code.
   */
  default String render() {
    return toString();
  }

  /**
   * Render the type into a {@link String} for the purpose of code generation.
   * This is slightly different from the `toString()` method as `toString()` is mostly needed for logging / debugging.
   * In contrast with its no-arg equivallent this method is idempotent.
   *
   * @param enclosingType The type that encoses the current {@link Renderable).
   * @return the {@link String} representation of the object as it's meant to appear in the generated code.
   */
  default String render(TypeDef enclosingType) {
    return toString();
  }
}
