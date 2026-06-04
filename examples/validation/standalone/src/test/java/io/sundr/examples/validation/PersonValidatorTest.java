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

package io.sundr.examples.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationError;

public class PersonValidatorTest {

  // --- of(person): basic DSL without context ---

  @Test
  public void of_validPerson_selectAll_noErrors() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validateLastName()
        .validate();

    assertNotNull(errors);
    assertTrue(errors.isEmpty());
  }

  @Test
  public void of_missingFirstName_detectsError() {
    Person person = new Person(null, "Doe", 25);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("firstName", errors.get(0).getPath());
  }

  @Test
  public void of_missingBothNames_detectsBothErrors() {
    Person person = new Person(null, null, 0);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validateLastName()
        .validate();

    assertEquals(2, errors.size());
  }

  @Test
  public void of_selectiveValidation_onlyChecksSelected() {
    Person person = new Person("John", null, 0);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateFirstName()
        .validate();

    assertTrue(errors.isEmpty());
  }

  // --- of(person): extra method params passed explicitly ---

  @Test
  public void of_validateMinAge_withExplicitArg_belowThreshold_fails() {
    Person person = new Person("John", "Doe", 15);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateMinAge(18)
        .validate();

    assertEquals(1, errors.size());
    assertEquals("age", errors.get(0).getPath());
  }

  @Test
  public void of_validateMinAge_withExplicitArg_aboveThreshold_passes() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateMinAge(18)
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void of_validateMaxAge_withExplicitArg_aboveThreshold_fails() {
    Person person = new Person("John", "Doe", 70);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateMaxAge(65)
        .validate();

    assertEquals(1, errors.size());
  }

  @Test
  public void of_validateMinAge_and_validateMaxAge_bothExplicit_withinRange() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateMinAge(18)
        .validateMaxAge(65)
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void of_validateMinAge_and_validateMaxAge_bothExplicit_outsideRange() {
    Person person = new Person("John", "Doe", 10);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateMinAge(18)
        .validateMaxAge(5)
        .validate();

    assertEquals(2, errors.size());
  }

  // --- withContext(...).of(person): pre-supplied params ---

  @Test
  public void withContext_presuppliedMinAge_validateMinAge_noArg() {
    Person person = new Person("John", "Doe", 15);

    // withContext params: maxAge, minAge, nameLengthLimit, namePatternLimit, personAgeValidations_minAge
    List<ValidationError> errors = PersonValidator
        .withContext(0, 18, 0, null, 0)
        .of(person)
        .validateMinAge()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("age", errors.get(0).getPath());
  }

  @Test
  public void withContext_presuppliedMinAndMax_validateBoth_noArgs() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(65, 18, 0, null, 0)
        .of(person)
        .validateMinAge()
        .validateMaxAge()
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void withContext_presuppliedMaxAge_overrideWithExplicit() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(99, 0, 0, null, 0)
        .of(person)
        .validateMaxAge(21)
        .validate();

    assertEquals(1, errors.size());
  }

  @Test
  public void withContext_mixNoArgAndExplicit() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(0, 18, 0, null, 0)
        .of(person)
        .validateMinAge()
        .validateMaxAge(65)
        .validate();

    assertTrue(errors.isEmpty());
  }

  // --- withContext with constructor args ---

  @Test
  public void withContext_ctorArgs_validateAgeRange_noArg() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(65, 0, 0, null, 18)
        .of(person)
        .validateAgeRange()
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void withContext_ctorArgs_validateAgeRange_belowMin_fails() {
    Person person = new Person("John", "Doe", 15);

    List<ValidationError> errors = PersonValidator
        .withContext(0, 0, 0, null, 18)
        .of(person)
        .validateAgeRange()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("age", errors.get(0).getPath());
  }

  @Test
  public void withContext_ctorArgs_validateAgeRange_explicitMaxAge() {
    Person person = new Person("John", "Doe", 70);

    List<ValidationError> errors = PersonValidator
        .withContext(0, 0, 0, null, 18)
        .of(person)
        .validateAgeRange(65)
        .validate();

    assertEquals(1, errors.size());
  }

  // --- param name collision: nameLengthLimit (int) vs namePatternLimit (String) ---

  @Test
  public void withContext_collisionResolved_nameLengthLimit() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(0, 0, 3, null, 0)
        .of(person)
        .validateNameLength()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("firstName", errors.get(0).getPath());
  }

  @Test
  public void withContext_collisionResolved_namePatternLimit() {
    Person person = new Person("John123", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(0, 0, 0, "[A-Za-z]+", 0)
        .of(person)
        .validateNamePattern()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("firstName", errors.get(0).getPath());
  }

  @Test
  public void of_collisionResolved_explicitArgs() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator.of(person)
        .validateNameLength(3)
        .validateNamePattern("[A-Z].*")
        .validate();

    assertEquals(1, errors.size());
  }

  @Test
  public void withContext_collisionResolved_bothUsed() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(0, 0, 50, "[A-Za-z]+", 0)
        .of(person)
        .validateNameLength()
        .validateNamePattern()
        .validate();

    assertTrue(errors.isEmpty());
  }

  // --- static validate(person) convenience ---

  @Test
  public void staticValidate_runsAllWithDefaults() {
    // static validate uses defaults: minAge=0, maxAge=0, nameLengthLimit=0, namePatternLimit=null
    // validateNameLength with limit=0 flags any non-empty name (length > 0)
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator.validate(person);

    assertFalse(errors.isEmpty());
  }

  @Test
  public void staticValidate_missingNames_detectsErrors() {
    Person person = new Person(null, null, 0);

    List<ValidationError> errors = PersonValidator.validate(person);

    assertFalse(errors.isEmpty());
    assertTrue(errors.stream().anyMatch(e -> "firstName".equals(e.getPath())));
    assertTrue(errors.stream().anyMatch(e -> "lastName".equals(e.getPath())));
  }

  // --- full chain combining everything ---

  @Test
  public void fullChain_withContext_multipleValidations() {
    Person person = new Person("John", "Doe", 25);

    List<ValidationError> errors = PersonValidator
        .withContext(65, 18, 50, "[A-Za-z]+", 18)
        .of(person)
        .validateFirstName()
        .validateLastName()
        .validateMinAge()
        .validateMaxAge()
        .validateAgeRange()
        .validateNameLength()
        .validateNamePattern()
        .validate();

    assertTrue(errors.isEmpty());
  }
}
