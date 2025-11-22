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

public class WithFullyQualifiedNameTest {

  @Test
  public void shouldGetPackageName() {
    assertEquals("java.util", WithFullyQualifiedName.getPackageName("java.util.List"));
    assertEquals("java.lang", WithFullyQualifiedName.getPackageName("java.lang.System.Logger"));
  }

  @Test
  public void shouldGetClassName() {
    assertEquals("Void", WithFullyQualifiedName.getClassName("Void"));
    assertEquals("List", WithFullyQualifiedName.getClassName("java.util.List"));
    assertEquals("System.Logger", WithFullyQualifiedName.getClassName("java.lang.System.Logger"));
  }

  @Test
  public void shouldGetOuterTypeName() {
    assertEquals(null, WithFullyQualifiedName.getOuterTypeName("Void"));
    assertEquals(null, WithFullyQualifiedName.getOuterTypeName("java.util.List"));
    assertEquals("java.lang.System", WithFullyQualifiedName.getOuterTypeName("java.lang.System.Logger"));
  }

  @Test
  public void shouldGetInnerTypeName() {
    assertEquals("Void", WithFullyQualifiedName.getInnerTypeName("Void"));
    assertEquals("List", WithFullyQualifiedName.getInnerTypeName("java.util.List"));
    assertEquals("Entry", WithFullyQualifiedName.getInnerTypeName("java.util.Map.Entry"));
    assertEquals("Logger", WithFullyQualifiedName.getInnerTypeName("java.lang.System.Logger"));
  }
}
