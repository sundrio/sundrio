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

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

public class AddressValidationTest {

  Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void shouldAcceptNullStreet() {
    Address address = new DefaultAddressBuilder().build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptNullStreetViaConstructor() {
    Address address = new DefaultAddressBuilder(true).build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptNullStreetViaUsingValidation() {
    Address address = new DefaultAddressBuilder().usingValidation().build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptNullStreetViaUsingValidator() {
    Address address = new DefaultAddressBuilder().usingValidator(validator).build();
  }

  @Test
  public void shouldAcceptZeroNumber() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame").withNumber(0).build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptZeroNumberViaConstructor() {
    Address address = new DefaultAddressBuilder(true).withStreet("Sesame").withNumber(0).build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptZeroNumberViaUsingValidation() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame").withNumber(0).usingValidation().build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shoulNotdAcceptZeroNumberViaUsingValidator() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame").withNumber(0).usingValidator(validator).build();
  }

  @Test
  public void shouldAcceptAlphanumericZipCode() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptAlphanumericZipCodeViaConstructor() {
    Address address = new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptAlphanumericZipCodeViaUsingValidation() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidation()
        .build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptAlphanumericZipCodeViaUsingValidator() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("abcd")
        .usingValidator(validator)
        .build();
  }

  @Test
  public void shouldAcceptLongZipCode() {
    Address address = new DefaultAddressBuilder().withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptLongZipCodeViaConstructor() {
    Address address = new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptLongZipCodeViaUsingValidation() {
    Address address = new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .usingValidation()
        .build();
  }

  @Test(expected = ConstraintViolationException.class)
  public void shouldNotAcceptLongZipCodeViaUsingValidator() {
    Address address = new DefaultAddressBuilder(true).withStreet("Sesame")
        .withNumber(1)
        .withZipCode("1234567")
        .usingValidator(validator)
        .build();
  }

}
