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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationError;
import io.sundr.validation.ValidationResult;
import io.sundr.validation.Validators;

public class GeneratedValidatorTest {

  @Test
  public void shouldUseGeneratedValidatorDirectly() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .build();

    List<ValidationError> errors = PersonValidator.validate(person);

    assertNotNull(errors);
    assertTrue(errors.isEmpty());
  }

  @Test
  public void shouldDetectValidationErrorsWithGeneratedValidator() {
    Person person = new PersonBuilder().build();

    List<ValidationError> errors = PersonValidator.validate(person);

    assertNotNull(errors);
    assertEquals(2, errors.size());
  }

  @Test
  public void shouldUseGeneratedValidatorWithBuilder() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validate(PersonValidator::validate)
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void shouldDetectErrorsWithBuilderAndGeneratedValidator() {
    ValidationResult<Person> result = new PersonBuilder()
        .validate(PersonValidator::validate)
        .build();

    assertFalse(result.isValid());
    assertEquals(2, result.getErrors().size());
  }

  @Test
  public void shouldUseValidatorsUtilityClass() {
    Person person = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .build();

    List<ValidationError> errors = Validators.of(person).validate();

    assertNotNull(errors);
    assertTrue(errors.isEmpty());
  }

  @Test
  public void shouldUseValidatorsValidateMethod() {
    Person person = new PersonBuilder().build();

    List<ValidationError> errors = Validators.of(person).validate();

    assertNotNull(errors);
    assertEquals(2, errors.size());
  }

  @Test
  public void shouldCheckIfValidatorExists() {
    assertTrue(Validators.hasValidator(Person.class));
  }

  @Test
  public void shouldGetValidatorForType() {
    assertNotNull(Validators.getValidator(Person.class));
  }

}
