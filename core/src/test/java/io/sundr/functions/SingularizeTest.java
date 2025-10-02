/*
 * Copyright 2016 The original authors.
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

package io.sundr.functions;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingularizeTest {

  @Test
  public void testSingularize() {

    assertEquals("address", Singularize.FUNCTION.apply("addresses"));
    assertEquals("Address", Singularize.FUNCTION.apply("Addresses"));

    assertEquals("number", Singularize.FUNCTION.apply("numbers"));
    assertEquals("Number", Singularize.FUNCTION.apply("Numbers"));

    assertEquals("child", Singularize.FUNCTION.apply("children"));
    assertEquals("Child", Singularize.FUNCTION.apply("Children"));

    assertEquals("https", Singularize.FUNCTION.apply("https"));
    assertEquals("Https", Singularize.FUNCTION.apply("Https"));

    assertEquals("http", Singularize.FUNCTION.apply("http"));
    assertEquals("Http", Singularize.FUNCTION.apply("Http"));
  }
}
