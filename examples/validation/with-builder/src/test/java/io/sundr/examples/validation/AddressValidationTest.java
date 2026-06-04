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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationException;
import io.sundr.validation.ValidationResult;
import jakarta.validation.Validator;

public class AddressValidationTest {

  Validator validator = jakarta.validation.Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void shouldBuildWithoutValidation() {
    Address address = new DefaultAddressBuilder().build();
    assertNotNull(address);
  }

  @Test
  public void shouldAcceptValidAddress() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("12345")
        .usingValidation()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void shouldDetectNullStreetViaUsingValidation() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .usingValidation()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void shouldThrowOnNullStreetViaUsingValidation() {
    assertThrows(ValidationException.class, () -> new DefaultAddressBuilder()
        .usingValidation()
        .build()
        .orElseThrow());
  }

  @Test
  public void shouldDetectNullStreetViaUsingValidator() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .usingValidator(validator)
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void shouldThrowOnNullStreetViaUsingValidator() {
    assertThrows(ValidationException.class, () -> new DefaultAddressBuilder()
        .usingValidator(validator)
        .build()
        .orElseThrow());
  }

  @Test
  public void shouldDetectZeroNumberViaUsingValidation() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(0)
        .usingValidation()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void shouldDetectAlphanumericZipCodeViaUsingValidation() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidation()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void shouldDetectLongZipCodeViaUsingValidation() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .usingValidation()
        .build();

    assertFalse(result.isValid());
  }

  @Test
  public void shouldDetectAlphanumericZipCodeViaUsingValidator() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidator(validator)
        .build();

    assertFalse(result.isValid());
  }
}
