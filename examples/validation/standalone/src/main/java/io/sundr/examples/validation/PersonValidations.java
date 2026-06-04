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

public class PersonValidations {

  @Validation
  public static List<ValidationError> validateFirstName(Person person) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
      errors.add(new ValidationError("firstName", "must not be empty"));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateLastName(Person person) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getLastName() == null || person.getLastName().isEmpty()) {
      errors.add(new ValidationError("lastName", "must not be empty"));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateMinAge(Person person, int minAge) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getAge() < minAge) {
      errors.add(new ValidationError("age", "must be at least " + minAge));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateMaxAge(Person person, int maxAge) {
    List<ValidationError> errors = new ArrayList<>();
    if (maxAge > 0 && person.getAge() > maxAge) {
      errors.add(new ValidationError("age", "must be at most " + maxAge));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateNameLength(Person person, int limit) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getFirstName() != null && person.getFirstName().length() > limit) {
      errors.add(new ValidationError("firstName", "must be at most " + limit + " characters"));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateNamePattern(Person person, String limit) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getFirstName() != null && limit != null && !person.getFirstName().matches(limit)) {
      errors.add(new ValidationError("firstName", "must match pattern " + limit));
    }
    return errors;
  }
}
