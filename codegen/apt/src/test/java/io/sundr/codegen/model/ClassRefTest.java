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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassRefTest {

  @Test
  public void shouldGetClassName() throws Exception {
    ClassRef c = new ClassRefBuilder().withFullyQualifiedName("my.pkg.SomeClass").build();
    assertEquals("SomeClass", c.getName());
  }

  @Test
  public void shouldGetPackageName() throws Exception {
    ClassRef c = new ClassRefBuilder().withFullyQualifiedName("my.pkg.SomeClass").build();
    assertEquals("my.pkg", c.getPackageName());
  }

}
