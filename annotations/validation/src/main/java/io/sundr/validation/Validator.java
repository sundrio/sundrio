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
import java.util.Collections;
import java.util.List;

/**
 * A functional interface for validating objects.
 * Returns a list of validation errors, or an empty list if the object is valid.
 *
 * @param <T> the type of object to validate
 */
@FunctionalInterface
public interface Validator<T> {

  /**
   * Validates the given item.
   *
   * @param item the item to validate
   * @return a list of validation errors, or an empty list if valid
   */
  List<ValidationError> validate(T item);

  /**
   * Combines this validator with another, running both and collecting all errors.
   *
   * @param other the other validator to run after this one
   * @return a new validator that runs both validators
   */
  default Validator<T> andThen(Validator<T> other) {
    return item -> {
      List<ValidationError> errors = new ArrayList<>(this.validate(item));
      errors.addAll(other.validate(item));
      return errors;
    };
  }

  /**
   * Creates a validator that always returns valid (no errors).
   *
   * @param <T> the type of object
   * @return a validator that accepts everything
   */
  static <T> Validator<T> none() {
    return item -> Collections.emptyList();
  }

  /**
   * Combines multiple validators into one that runs all of them.
   *
   * @param validators the validators to combine
   * @param <T> the type of object to validate
   * @return a combined validator
   */
  @SafeVarargs
  static <T> Validator<T> compose(Validator<T>... validators) {
    return item -> {
      List<ValidationError> errors = new ArrayList<>();
      for (Validator<T> v : validators) {
        errors.addAll(v.validate(item));
      }
      return errors;
    };
  }
}
