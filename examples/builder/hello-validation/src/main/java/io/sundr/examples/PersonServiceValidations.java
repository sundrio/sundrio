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

import java.util.ArrayList;
import java.util.List;

import io.sundr.validation.ValidationError;
import io.sundr.validation.annotations.Validation;

public class PersonServiceValidations {

  private final List<String> bannedNames;

  public PersonServiceValidations(List<String> bannedNames) {
    this.bannedNames = bannedNames;
  }

  @Validation
  public List<ValidationError> validateNotBanned(Person person) {
    List<ValidationError> errors = new ArrayList<>();
    if (bannedNames != null && person.getFirstName() != null && bannedNames.contains(person.getFirstName())) {
      errors.add(new ValidationError("firstName", "is banned"));
    }
    return errors;
  }
}
