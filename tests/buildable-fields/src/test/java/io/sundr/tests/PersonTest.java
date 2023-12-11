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

import java.sql.Date;
import java.util.Arrays;

import org.junit.Test;

public class PersonTest {

  @Test
  public void testGeneratedBuilder() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withBirthDate(new Date(System.currentTimeMillis()))
        .build();
  }

  @Test
  public void shoudNotHaveWithAge() {
    PersonBuilder builder = new PersonBuilder();
    assertFalse(Arrays.stream(builder.getClass().getMethods()).anyMatch(m -> m.getName().equals("withAge")));
  }
}
