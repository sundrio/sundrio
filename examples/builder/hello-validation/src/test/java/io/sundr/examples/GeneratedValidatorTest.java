/*
 * Copyright 2015 The original authors.
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

package io.sundr.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationError;

public class GeneratedValidatorTest {

  @Test
  public void of_validPerson_noErrors() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .build();

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validateLastName()
        .validate();

    assertNotNull(errors);
    assertTrue(errors.isEmpty());
  }

  @Test
  public void of_missingNames_detectsErrors() {
    Person person = new PersonBuilder().build();

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validateLastName()
        .validate();

    assertNotNull(errors);
    assertEquals(2, errors.size());
  }

  @Test
  public void of_selectiveValidation_onlyChecksSelected() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .build();

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void of_withExtraArgs_validateMinAge() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(15)
        .build();

    List<ValidationError> errors = PersonValidator.of(person)
        .validateMinAge(18)
        .validate();

    assertEquals(1, errors.size());
    assertEquals("age", errors.get(0).getPath());
  }

  @Test
  public void withContext_presuppliedArgs_validateMinAge_noArg() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(15)
        .build();

    List<ValidationError> errors = PersonValidator
        .withContext(99, 18, 0, null)
        .of(person)
        .validateMinAge()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("age", errors.get(0).getPath());
  }

  @Test
  public void withContext_presuppliedArgs_validateMinAge_and_validateMaxAge_noArgs() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .build();

    List<ValidationError> errors = PersonValidator
        .withContext(65, 18, 0, null)
        .of(person)
        .validateMinAge()
        .validateMaxAge()
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void withContext_overrideArg_validateMaxAge_explicit() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .build();

    // withContext sets maxAge=99, but validateMaxAge(21) overrides
    List<ValidationError> errors = PersonValidator
        .withContext(99, 18, 0, null)
        .of(person)
        .validateMaxAge(21)
        .validate();

    assertEquals(1, errors.size());
    assertEquals("age", errors.get(0).getPath());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void withContext_ctorArgs_validateNotBanned() {
    Person person = new PersonBuilder()
        .withFirstName("BadName")
        .withLastName("Doe")
        .build();

    List<String> bannedNames = Arrays.asList("BadName", "Evil");

    List<ValidationError> errors = PersonValidator
        .withContext(0, 0, 0, bannedNames)
        .of(person)
        .validateNotBanned()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("firstName", errors.get(0).getPath());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void withContext_ctorArgs_and_extraArgs_mixed() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .build();

    List<String> bannedNames = Arrays.asList("BadName");

    List<ValidationError> errors = PersonValidator
        .withContext(65, 18, 18, bannedNames)
        .of(person)
        .validateMinAge()
        .validateMaxAge()
        .validateNotBanned()
        .validateAgeRange()
        .validate();

    assertTrue(errors.isEmpty());
  }
}
