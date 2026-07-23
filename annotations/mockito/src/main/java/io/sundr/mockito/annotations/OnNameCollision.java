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

package io.sundr.mockito.annotations;

/**
 * The policy applied when the name of a mock DSL class to generate is already taken by an
 * existing type (a hand-written class, a class on the compile classpath, or one generated in a
 * previous round).
 */
public enum OnNameCollision {

  /**
   * Generate under a fallback name instead: the suffix {@code Generated} is inserted before the
   * configured suffix (for example {@code FooMock} becomes {@code FooGeneratedMock}). If the
   * fallback name is also taken, generation fails with an error.
   */
  RENAME,

  /**
   * Fail the compilation with an error, leaving it to the developer to rename the existing type
   * or configure a different {@code prefix} / {@code suffix}.
   */
  FAIL
}
