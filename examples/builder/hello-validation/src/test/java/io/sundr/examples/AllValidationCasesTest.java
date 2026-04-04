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

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationException;
import io.sundr.validation.ValidationResult;

/**
 * Demonstrates all validation patterns available when validation-annotations is on the classpath.
 *
 * Case i: Jakarta bean validation via @NotBlank on Person fields + usingValidation()
 * Case ii: usingValidation() — auto-bootstraps jakarta default validator
 * Case iii: usingValidator(jakarta.validation.Validator) — explicit jakarta validator instance
 * Case iv: validateAll() — runs all registered sundrio @Validation methods
 * Case v: usingNewValidator().checkFirstName().checkLastName() — per-method fluent
 */
public class AllValidationCasesTest {

  // Case i: Jakarta bean validation annotations on Person (@NotBlank)
  @Test
  public void jakartaBeanValidationAnnotations_valid() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingValidation()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void jakartaBeanValidationAnnotations_detectsBlankFirstName() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("")
        .withLastName("Doe")
        .usingValidation()
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  @Test
  public void jakartaBeanValidationAnnotations_detectsBothBlanks() {
    ValidationResult<Person> result = new PersonBuilder()
        .usingValidation()
        .build();

    assertFalse(result.isValid());
    assertEquals(2, result.getErrors().size());
  }

  // Case ii: usingValidation() — same as case i but emphasizing default validator bootstrap
  @Test
  public void usingValidation_bootstrapsDefaultJakartaProvider() {
    Person person = new PersonBuilder()
        .withFirstName("Jane")
        .withLastName("Smith")
        .usingValidation()
        .build()
        .orElseThrow();

    assertEquals("Jane", person.getFirstName());
  }

  @Test
  public void usingValidation_throwsOnInvalid() {
    assertThrows(ValidationException.class, () -> new PersonBuilder()
        .withFirstName("")
        .usingValidation()
        .build()
        .orElseThrow());
  }

  // Case iii: usingValidator(jakarta.validation.Validator) — explicit validator instance
  @Test
  public void usingValidator_withExplicitJakartaValidator() {
    jakarta.validation.Validator jakartaValidator = jakarta.validation.Validation
        .buildDefaultValidatorFactory()
        .getValidator();

    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingValidator(jakartaValidator)
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void usingValidator_detectsConstraintViolations() {
    jakarta.validation.Validator jakartaValidator = jakarta.validation.Validation
        .buildDefaultValidatorFactory()
        .getValidator();

    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("")
        .withLastName("Doe")
        .usingValidator(jakartaValidator)
        .build();

    assertFalse(result.isValid());
  }

  // Case iv: validateAll() — runs all registered sundrio @Validation annotated methods
  @Test
  public void validateAll_runsAllRegisteredSundrioValidators() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .validateAll()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void validateAll_detectsMissingFirstName() {
    ValidationResult<Person> result = new PersonBuilder()
        .withLastName("Doe")
        .validateAll()
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  @Test
  public void validateAll_detectsMissingBothNames() {
    ValidationResult<Person> result = new PersonBuilder()
        .validateAll()
        .build();

    assertFalse(result.isValid());
    assertEquals(2, result.getErrors().size());
  }

  // Case v: per-method fluent builder via usingNewValidator()
  @Test
  public void usingValidators_perMethodFluent_valid() {
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
  public void usingValidators_onlyCheckFirstName() {
    // Only validates firstName — lastName is null but not checked
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .usingNewValidator()
        .checkFirstName()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void usingValidators_detectsInvalidFirstName() {
    ValidationResult<Person> result = new PersonBuilder()
        .withLastName("Doe")
        .usingNewValidator()
        .checkFirstName()
        .checkLastName()
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  // Case vi: usingNewValidator().usingJakartaValidation() — combines custom validators with jakarta bean validation
  @Test
  public void usingNewValidator_withJakartaValidation_valid() {
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
  public void usingNewValidator_withJakartaValidation_detectsBlankViaJakarta() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("")
        .withLastName("Doe")
        .usingNewValidator()
        .usingJakartaValidation()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void usingNewValidator_withJakartaValidation_combinesBothValidators() {
    ValidationResult<Person> result = new PersonBuilder()
        .withLastName("Doe")
        .usingNewValidator()
        .usingJakartaValidation()
        .checkFirstName()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void usingNewValidator_endValidation_withJakartaValidation() {
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
