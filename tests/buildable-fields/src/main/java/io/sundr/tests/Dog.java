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

package io.sundr.tests;

import io.sundr.builder.annotations.Buildable;

/**
 * A simple Dog Pojo that demonstrates how to hide a field, using `ignore` on the buildable.
 * This is especially useful, when constructor arguments can't be used, due to parent class providing a setter
 * for the field we need to hide.
 */
@Buildable(ignore = "kind")
public class Dog extends Animal {

  private static final String KIND = "dog";

  public Dog(String name) {
    super(KIND, name);
  }
}
