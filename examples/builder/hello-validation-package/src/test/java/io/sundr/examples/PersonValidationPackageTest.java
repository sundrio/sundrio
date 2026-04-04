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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.sundr.examples.validation.ValidationResult;
import io.sundr.examples.validation.Validator;

public class PersonValidationPackageTest {

  private static final Validator<Person> NAME_VALIDATOR = person -> {
    java.util.List<io.sundr.examples.validation.ValidationError> errors = new java.util.ArrayList<>();
    if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
      errors.add(new io.sundr.examples.validation.ValidationError("firstName", "must not be empty"));
    }
    if (person.getLastName() == null || person.getLastName().isEmpty()) {
      errors.add(new io.sundr.examples.validation.ValidationError("lastName", "must not be empty"));
    }
    return errors;
  };

  @Test
  public void validationInfrastructureShouldBeInCustomPackage() {
    // Verify the validation classes are in io.sundr.examples.validation, not io.sundr.validation
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validate(NAME_VALIDATOR)
        .build();

    assertTrue(result.isValid());
    assertEquals("John", result.get().getFirstName());
  }

  @Test
  public void shouldDetectValidationErrors() {
    ValidationResult<Person> result = new PersonBuilder()
        .withLastName("Doe")
        .validate(NAME_VALIDATOR)
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  @Test
  public void jakartaValidationShouldUseCustomPackage() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("")
        .withLastName("Doe")
        .usingValidation()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void usingNewValidatorShouldWork() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator()
        .checkFirstName()
        .checkLastName()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void usingNewValidatorShouldDetectErrors() {
    ValidationResult<Person> result = new PersonBuilder()
        .withLastName("Doe")
        .usingNewValidator()
        .checkFirstName()
        .build();

    assertFalse(result.isValid());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  @Test
  public void usingNewValidatorWithJakartaShouldWork() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator()
        .usingJakartaValidation()
        .checkFirstName()
        .checkLastName()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void usingNewValidatorWithJakartaShouldDetectErrors() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("")
        .withLastName("Doe")
        .usingNewValidator()
        .usingJakartaValidation()
        .checkFirstName()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void usingNewValidatorWithJakartaAndEndValidation() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator()
        .usingJakartaValidation()
        .checkFirstName()
        .checkLastName()
        .endValidation()
        .build();

    assertTrue(result.isValid());
  }
}
