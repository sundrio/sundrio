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

package io.sundr.adapter.testing.general;

public class ClassWithAnnotation {

  @SimpleAnnotation(name = "foo")
  private String foo;

  @SimpleAnnotation(name = "bar", values = { 1, 2, 3, 5, 7 })
  private void bar() {
  }

  @SimpleAnnotation(name = "baz", values = { 1, 2, 3, 5, 7 })
  @SimpleAnnotation2(name = "baz2", values = { 1, 2, 3, 5, 7 })
  private void baz() {
  }

}
