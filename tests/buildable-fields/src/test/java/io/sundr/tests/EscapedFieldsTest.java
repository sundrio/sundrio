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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EscapedFieldsTest {

  @Test
  public void builderSetsEscapedFields() {
    EscapedFields result = new EscapedFieldsBuilder()
        .withContinue("token")
        .withDefault("value")
        .build();

    assertEquals("token", result.getContinue());
    assertEquals("value", result.getDefault());
  }

  @Test
  public void builderFromInstanceCopiesEscapedFields() {
    EscapedFields original = new EscapedFields("token", "hour");
    original.setDefault("value");

    EscapedFields copy = new EscapedFieldsBuilder(original).build();

    assertEquals("token", copy.getContinue());
    assertEquals("value", copy.getDefault());
    assertEquals("hour", copy.get_1h());
  }
}
