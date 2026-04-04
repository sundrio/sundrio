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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationError;
import io.sundr.validation.ValidationException;
import io.sundr.validation.ValidationResult;
import io.sundr.validation.Validator;

public class PersonBuilderValidationTest {

  private static final Validator<Person> NAME_VALIDATOR = person -> {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
      errors.add(new ValidationError("firstName", "must not be empty"));
    }
    if (person.getLastName() == null || person.getLastName().isEmpty()) {
      errors.add(new ValidationError("lastName", "must not be empty"));
    }
    return errors;
  };

  @Test
  public void shouldValidateSuccessfully() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validate(NAME_VALIDATOR)
        .build();

    assertTrue(result.isValid());
    assertEquals("John", result.get().getFirstName());
    assertEquals("Doe", result.get().getLastName());
  }

  @Test
  public void shouldDetectMissingFirstName() {
    ValidationResult<Person> result = new PersonBuilder()
        .withLastName("Doe")
        .validate(NAME_VALIDATOR)
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  @Test
  public void shouldDetectMissingBothNames() {
    ValidationResult<Person> result = new PersonBuilder()
        .validate(NAME_VALIDATOR)
        .build();

    assertFalse(result.isValid());
    assertEquals(2, result.getErrors().size());
  }

  @Test
  public void shouldThrowOnOrElseThrowWithInvalidResult() {
    assertThrows(ValidationException.class, () -> new PersonBuilder()
        .validate(NAME_VALIDATOR)
        .build()
        .orElseThrow());
  }

  @Test
  public void shouldReturnValueOnOrElseThrowWithValidResult() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validate(NAME_VALIDATOR)
        .build()
        .orElseThrow();

    assertEquals("John", person.getFirstName());
  }

  @Test
  public void shouldSupportMultipleValidators() {
    Validator<Person> firstNameLengthValidator = person -> {
      if (person.getFirstName() != null && person.getFirstName().length() < 2) {
        return Collections.singletonList(new ValidationError("firstName", "must be at least 2 characters"));
      }
      return Collections.emptyList();
    };

    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("J")
        .withLastName("Doe")
        .validate(NAME_VALIDATOR, firstNameLengthValidator)
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("must be at least 2 characters", result.getErrors().get(0).getMessage());
  }

  @Test
  public void shouldSupportLambdaValidators() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validate(person -> {
          if (person.getFirstName().equals(person.getLastName())) {
            return Collections.singletonList(new ValidationError("firstName", "must be different from lastName"));
          }
          return Collections.emptyList();
        })
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void shouldSupportBuildOrThrow() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validate(NAME_VALIDATOR)
        .buildOrThrow();

    assertEquals("John", person.getFirstName());
  }

  @Test
  public void shouldThrowOnBuildOrThrowWithInvalidResult() {
    assertThrows(ValidationException.class, () -> new PersonBuilder()
        .validate(NAME_VALIDATOR)
        .buildOrThrow());
  }
}
