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

package io.sundr.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception thrown when validation fails.
 * Contains the list of validation errors that caused the failure.
 */
public class ValidationException extends RuntimeException {

  private final List<ValidationError> errors;

  public ValidationException(String message) {
    super(message);
    this.errors = Collections.emptyList();
  }

  public ValidationException(ValidationError error) {
    super(error.toString());
    this.errors = Collections.singletonList(error);
  }

  public ValidationException(ValidationError... errors) {
    super(formatErrors(Arrays.asList(errors)));
    this.errors = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(errors)));
  }

  public ValidationException(List<ValidationError> errors) {
    super(formatErrors(errors));
    this.errors = errors != null ? Collections.unmodifiableList(new ArrayList<>(errors)) : Collections.emptyList();
  }

  public ValidationException(String message, List<ValidationError> errors) {
    super(message);
    this.errors = errors != null ? Collections.unmodifiableList(new ArrayList<>(errors)) : Collections.emptyList();
  }

  public List<ValidationError> getErrors() {
    return errors;
  }

  private static String formatErrors(List<ValidationError> errors) {
    if (errors == null || errors.isEmpty()) {
      return "Validation failed";
    }
    if (errors.size() == 1) {
      return "Validation failed: " + errors.get(0);
    }
    return "Validation failed with " + errors.size() + " errors: " +
        errors.stream().map(ValidationError::toString).collect(Collectors.joining("; "));
  }
}
