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

public class PluralizeTest {

  @Test
  public void testPluralize() {

    assertEquals("addresses", Pluralize.FUNCTION.apply("address"));
    assertEquals("numbers", Pluralize.FUNCTION.apply("number"));
    assertEquals("vertices", Pluralize.FUNCTION.apply("vertex"));
    assertEquals("endpoints", Pluralize.FUNCTION.apply("endpoints"));
    assertEquals("ingresses", Pluralize.FUNCTION.apply("ingress"));
    assertEquals("http", Pluralize.FUNCTION.apply("http"));
    assertEquals("Http", Pluralize.FUNCTION.apply("Http"));
    assertEquals("https", Pluralize.FUNCTION.apply("https"));
    assertEquals("Https", Pluralize.FUNCTION.apply("Https"));
  }

}
