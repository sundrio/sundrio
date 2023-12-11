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

package io.sundr.utils;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class MapsTest {

  @Test
  public void shouldCreateMapWithOneElement() throws Exception {
    Map<Integer, String> map = Maps.create(1, "one");
    assertEquals(1, map.size());
    assertEquals("one", map.get(1));
  }

  @Test
  public void shouldCreateMapWithTwoElements() throws Exception {
    Map<Integer, String> map = Maps.create(1, "one", 2, "two");
    assertEquals(2, map.size());
    assertEquals("one", map.get(1));
    assertEquals("two", map.get(2));
  }

  @Test
  public void shouldCreateMapWithThreeElements() throws Exception {
    Map<Integer, String> map = Maps.create(1, "one", 2, "two", 3, "three");
    assertEquals(3, map.size());
    assertEquals("one", map.get(1));
    assertEquals("two", map.get(2));
    assertEquals("three", map.get(3));
  }

  @Test
  public void shouldCreateFromMapping() throws Exception {
    Map<String, String> map = Maps.create("1=one, 2=two,3 = three");
    assertEquals(3, map.size());
    assertEquals("one", map.get("1"));
    assertEquals("two", map.get("2"));
    assertEquals("three", map.get("3"));
  }
}
