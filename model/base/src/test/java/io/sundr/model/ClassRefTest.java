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

import java.util.Collections;

import org.junit.Test;

public class ClassRefTest {

  ClassRef SOME_CLASS = new ClassRef("my.pkg.SomeClass", 0, Collections.emptyList(), Collections.emptyMap());
  ClassRef SOME_INNER_CLASS = new ClassRef("my.pkg.SomeClass.Inner", 0, Collections.emptyList(), Collections.emptyMap());
  ClassRef FROM_TYPEDEF_CLASS = TypeDef.forName("my.pkg.SomeClass.Inner").toInternalReference();

  @Test
  public void shouldGetClassName() throws Exception {
    assertEquals("SomeClass", SOME_CLASS.getName());
    assertEquals("SomeClass.Inner", SOME_INNER_CLASS.getName());
    assertEquals("SomeClass.Inner", FROM_TYPEDEF_CLASS.getName());
  }

  @Test
  public void shouldGetPackageName() throws Exception {
    assertEquals("my.pkg", SOME_CLASS.getPackageName());
    assertEquals("my.pkg", SOME_INNER_CLASS.getPackageName());
    assertEquals("my.pkg", FROM_TYPEDEF_CLASS.getPackageName());
  }

  @Test
  public void shouldGetFullyQualifiedName() throws Exception {
    assertEquals("my.pkg.SomeClass", SOME_CLASS.getFullyQualifiedName());
    assertEquals("my.pkg.SomeClass.Inner", SOME_INNER_CLASS.getFullyQualifiedName());
    assertEquals("my.pkg.SomeClass.Inner", FROM_TYPEDEF_CLASS.getFullyQualifiedName());
  }
}
