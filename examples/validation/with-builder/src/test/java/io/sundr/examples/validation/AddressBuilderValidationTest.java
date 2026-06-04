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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.sundr.validation.ValidationError;
import io.sundr.validation.ValidationResult;

public class AddressBuilderValidationTest {

  // --- Validator DSL (works independently of builder) ---

  @Test
  public void validatorDsl_of_validAddress() {
    Address address = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("12345")
        .build();

    List<ValidationError> errors = AddressValidator.of(address)
        .validateStreet()
        .validateNumber(1)
        .validate();

    assertTrue(errors.isEmpty());
  }

  @Test
  public void validatorDsl_of_missingStreet() {
    Address address = new DefaultAddressBuilder()
        .withNumber(1)
        .build();

    List<ValidationError> errors = AddressValidator.of(address)
        .validateStreet()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("street", errors.get(0).getPath());
  }

  @Test
  public void validatorDsl_withContext_checkNumber_noArg() {
    Address address = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(0)
        .build();

    List<ValidationError> errors = AddressValidator
        .withContext(1)
        .of(address)
        .validateNumber()
        .validate();

    assertEquals(1, errors.size());
    assertEquals("number", errors.get(0).getPath());
  }

  @Test
  public void validatorDsl_staticValidate_allDefaults() {
    Address address = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .build();

    List<ValidationError> errors = AddressValidator.validate(address);

    assertTrue(errors.isEmpty());
  }

  // --- Builder DSL: jakarta validation ---

  @Test
  public void builderDsl_jakartaValidation_valid() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("12345")
        .usingValidation()
        .build();

    assertTrue(result.isValid());
  }

  @Test
  public void builderDsl_jakartaValidation_invalidZipCode() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidation()
        .build();

    assertFalse(result.isValid());
  }

  // --- Builder DSL: programmatic validators ---

  @Test
  public void builderDsl_validate_withLambda() {
    ValidationResult<?> result = new DefaultAddressBuilder()
        .withStreet("Sesame")
        .withNumber(1)
        .validate(address -> {
          if (address.getStreet() == null) {
            return List.of(new ValidationError("street", "required"));
          }
          return List.of();
        })
        .build();

    assertTrue(result.isValid());
  }

}
