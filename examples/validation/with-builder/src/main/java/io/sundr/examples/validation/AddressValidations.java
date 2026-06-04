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

import java.util.ArrayList;
import java.util.List;

import io.sundr.validation.ValidationError;
import io.sundr.validation.annotations.Validation;

public class AddressValidations {

  @Validation
  public static List<ValidationError> validateStreet(Address address) {
    List<ValidationError> errors = new ArrayList<>();
    if (address.getStreet() == null || address.getStreet().isEmpty()) {
      errors.add(new ValidationError("street", "must not be empty"));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateNumber(Address address, int minNumber) {
    List<ValidationError> errors = new ArrayList<>();
    if (address.getNumber() < minNumber) {
      errors.add(new ValidationError("number", "must be at least " + minNumber));
    }
    return errors;
  }
}
