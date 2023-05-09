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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Test;

public class DogTest {

  @Test
  public void testGeneratedBuilder() {
    Dog dog = new DogBuilder()
        .withName("Goodgirl")
        .build();
  }

  @Test
  public void testGeneratedBuilderEquality() {
    DogBuilder builder1 = new DogBuilder();
    DogBuilder builder2 = new DogBuilder()
        .withName("Goodgirl");
    assertNotEquals(builder1, builder2);
  }

  @Test
  public void shoudNotHaveWithKind() {
    DogBuilder builder = new DogBuilder();
    assertFalse(Arrays.stream(builder.getClass().getMethods()).anyMatch(m -> m.getName().equals("withKind")));
  }
}
