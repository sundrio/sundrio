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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

  // Case vii: extra method params — no-arg checkAge() uses default (0)
  @Test
  public void checkAge_noArg_usesDefault() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkAge()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void checkAge_withMinAge_belowThreshold_fails() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(15)
        .usingNewValidator()
        .checkAge(18)
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("age", result.getErrors().get(0).getPath());
  }

  @Test
  public void checkAge_withMinAge_aboveThreshold_passes() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkAge(18)
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void checkAge_combinedWithOtherChecks() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkFirstName()
        .checkAge(18)
        .build();

    assertTrue(result.isValid());
  }

  // Case viii: constructor args via usingNewValidator(minAge, bannedNames)
  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withBannedNames_personBanned_fails() {
    List<String> bannedNames = Arrays.asList("BadName", "Evil");
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("BadName")
        .withLastName("Doe")
        .usingNewValidator(0, bannedNames)
        .checkNotBanned()
        .build();

    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("firstName", result.getErrors().get(0).getPath());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withBannedNames_personAllowed_passes() {
    List<String> bannedNames = Arrays.asList("BadName", "Evil");
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator(0, bannedNames)
        .checkNotBanned()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withEmptyBannedList_passes() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator(0, Collections.emptyList())
        .checkNotBanned()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void usingNewValidator_noArgs_checkNotBanned_nullCtorArg() {
    // No-arg usingNewValidator passes null/0 for constructor args
    // PersonServiceValidations handles null bannedNames gracefully
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator()
        .checkNotBanned()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withBannedNames_combinedWithStaticCheck() {
    List<String> bannedNames = Arrays.asList("BadName");
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator(0, bannedNames)
        .checkNotBanned()
        .checkFirstName()
        .build();

    assertTrue(result.isValid());
  }

  // Case ix: constructor arg (minAge) with checkAgeRange() no-arg and checkAgeRange(maxAge)
  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withMinAge_checkAgeRange_noArg_usesDefaultMaxAge() {
    // minAge=18 via constructor, maxAge defaults to 0 (no upper bound check)
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator(18, Collections.emptyList())
        .checkAgeRange()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withMinAge_checkAgeRange_noArg_belowMin_fails() {
    // minAge=18 via constructor, person is 15 -> fails
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(15)
        .usingNewValidator(18, Collections.emptyList())
        .checkAgeRange()
        .build();

    assertFalse(result.isValid());
    assertEquals("age", result.getErrors().get(0).getPath());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withMinAge_checkAgeRange_withMaxAge_withinRange_passes() {
    // minAge=18 via constructor, maxAge=65 via method arg, person is 25 -> passes
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator(18, Collections.emptyList())
        .checkAgeRange(65)
        .build();

    assertTrue(result.isValid());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withMinAge_checkAgeRange_withMaxAge_aboveMax_fails() {
    // minAge=18 via constructor, maxAge=21 via method arg, person is 25 -> fails
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator(18, Collections.emptyList())
        .checkAgeRange(21)
        .build();

    assertFalse(result.isValid());
    assertEquals("age", result.getErrors().get(0).getPath());
  }

  // Case x: mixed — constructor args + extra method params + banned names
  @Test
  @SuppressWarnings("unchecked")
  public void usingNewValidator_withBannedNames_andCheckAgeWithMinAge() {
    List<String> bannedNames = Arrays.asList("BadName");
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator(18, bannedNames)
        .checkNotBanned()
        .checkAge(18)
        .build();

    assertTrue(result.isValid());
  }

  // Case xi: multiple static methods with different extra args
  @Test
  public void checkMinAge_and_checkMaxAge_noArgs_usesDefaults() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkMinAge()
        .checkMaxAge()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void checkMinAge_withArg_belowThreshold_fails() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(15)
        .usingNewValidator()
        .checkMinAge(18)
        .build();

    assertFalse(result.isValid());
    assertEquals("age", result.getErrors().get(0).getPath());
  }

  @Test
  public void checkMaxAge_withArg_aboveThreshold_fails() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(70)
        .usingNewValidator()
        .checkMaxAge(65)
        .build();

    assertFalse(result.isValid());
    assertEquals("age", result.getErrors().get(0).getPath());
  }

  @Test
  public void checkMinAge_and_checkMaxAge_withArgs_withinRange_passes() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkMinAge(18)
        .checkMaxAge(65)
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void checkMinAge_and_checkMaxAge_withArgs_outsideRange_collectsBothErrors() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(10)
        .usingNewValidator()
        .checkMinAge(18)
        .checkMaxAge(5)
        .build();

    assertFalse(result.isValid());
    assertEquals(2, result.getErrors().size());
  }

  @Test
  public void checkMinAge_noArg_and_checkMaxAge_withArg_mixed() {
    // checkMinAge() uses default (0), checkMaxAge(30) uses explicit
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkMinAge()
        .checkMaxAge(30)
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void checkMinAge_withArg_and_checkMaxAge_noArg_mixed() {
    // checkMinAge(18) explicit, checkMaxAge() default (0 means no upper bound)
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator()
        .checkMinAge(18)
        .checkMaxAge()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void checkMinAge_and_checkMaxAge_withArgs_combinedWithCtorArgs() {
    List<String> bannedNames = Arrays.asList("BadName");
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .usingNewValidator(18, bannedNames)
        .checkMinAge(18)
        .checkMaxAge(65)
        .checkNotBanned()
        .checkAgeRange(65)
        .build();

    assertTrue(result.isValid());
  }

  // Regression: existing behavior unchanged
  @Test
  public void existingBehavior_usingNewValidator_checkFirstNameAndLastName() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .usingNewValidator()
        .checkFirstName()
        .checkLastName()
        .build();

    assertTrue(result.isValid());
  }

  // validateAll still works with defaults
  @Test
  public void validateAll_withExtraParams_usesDefaults() {
    ValidationResult<Person> result = new PersonBuilder()
        .withFirstName("John")
        .withLastName("Doe")
        .withAge(25)
        .validateAll()
        .build();

    assertTrue(result.isValid());
  }
}
