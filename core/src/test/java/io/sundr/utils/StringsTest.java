/*
 *      Copyright 2017 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.utils;

import org.junit.Test;

import static io.sundr.utils.Strings.capitalizeFirst;
import static io.sundr.utils.Strings.compact;
import static io.sundr.utils.Strings.indexOfAlphabetic;
import static org.junit.Assert.assertEquals;

public class StringsTest {

  @Test
  public void testCompact() throws Exception {
    assertEquals("withDefaultContact", compact("withDefaultContact", "Contact"));
    assertEquals("someRepeatedRepeated", compact("someRepeatedRepeated", "Repeated"));
  }

  @Test
  public void testIndexOfAlphabetic() {
    assertEquals(-1, indexOfAlphabetic(null));
    assertEquals(-1, indexOfAlphabetic(""));
    assertEquals(-1, indexOfAlphabetic("_$123"));
    assertEquals(0, indexOfAlphabetic("name"));
    assertEquals(1, indexOfAlphabetic("$name"));
    assertEquals(1, indexOfAlphabetic("_name"));
  }

  @Test
  public void testCapitalizeFirst() {
    assertEquals(null, capitalizeFirst(null));
    assertEquals("", capitalizeFirst(""));
    assertEquals("123", capitalizeFirst("123"));
    assertEquals("Dog", capitalizeFirst("Dog"));
    assertEquals("Dog", capitalizeFirst("dog"));
    assertEquals("$Dog", capitalizeFirst("$dog"));
    assertEquals("_Dog", capitalizeFirst("_dog"));
    assertEquals("$_Dog", capitalizeFirst("$_dog"));
    assertEquals("1Dog", capitalizeFirst("1dog"));
  }

}
