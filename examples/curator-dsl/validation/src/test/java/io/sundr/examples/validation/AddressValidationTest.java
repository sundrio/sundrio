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

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class AddressValidationTest {

  Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void shouldAcceptNullStreet() {
    Address address = new DefaultAddressBuilder().build();
  }

  @Test
  public void shouldNotAcceptNullStreetViaConstructor() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder(true).build());
  }

  @Test
  public void shouldNotAcceptNullStreetViaUsingValidation() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder().usingValidation().build());
  }

  @Test
  public void shouldNotAcceptNullStreetViaUsingValidator() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder().usingValidator(validator).build());
  }

  @Test
  public void shouldAcceptZeroNumber() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame").withNumber(0).build();
  }

  @Test
  public void shouldNotAcceptZeroNumberViaConstructor() {
    assertThrows(ConstraintViolationException.class,
        () -> new DefaultAddressBuilder(true).withStreet("Sesame").withNumber(0).build());
  }

  @Test
  public void shouldNotAcceptZeroNumberViaUsingValidation() {
    assertThrows(ConstraintViolationException.class,
        () -> new DefaultAddressBuilder().withStreet("Sesame").withNumber(0).usingValidation().build());
  }

  @Test
  public void shoulNotdAcceptZeroNumberViaUsingValidator() {
    assertThrows(ConstraintViolationException.class,
        () -> new DefaultAddressBuilder().withStreet("Sesame").withNumber(0).usingValidator(validator).build());
  }

  @Test
  public void shouldAcceptAlphanumericZipCode() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .build();
  }

  @Test
  public void shouldNotAcceptAlphanumericZipCodeViaConstructor() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .build());
  }

  @Test
  public void shouldNotAcceptAlphanumericZipCodeViaUsingValidation() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidation()
        .build());
  }

  @Test
  public void shouldNotAcceptAlphanumericZipCodeViaUsingValidator() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidator(validator)
        .build());
  }

  @Test
  public void shouldAcceptLongZipCode() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .build();
  }

  @Test
  public void shouldNotAcceptLongZipCodeViaConstructor() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .build());
  }

  @Test
  public void shouldNotAcceptLongZipCodeViaUsingValidation() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .usingValidation()
        .build());
  }

  @Test
  public void shouldNotAcceptLongZipCodeViaUsingValidator() {
    assertThrows(ConstraintViolationException.class, () -> new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .usingValidator(validator)
        .build());
  }

}
