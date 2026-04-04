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
import java.util.Objects;
import java.util.Set;

import io.sundr.builder.Builder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * A builder wrapper that performs validation when building.
 * Instead of returning the built object directly, it returns a ValidationResult
 * that contains either the valid object or the validation errors.
 *
 * Supports both sundrio {@link io.sundr.validation.Validator} instances and optionally a
 * {@link Validator jakarta.validation.Validator} for bean validation constraint checking.
 *
 * @param <T> the type of object being built
 */
public class ValidatingBuilder<T> {

  private static volatile Validator defaultJakartaValidator;

  private static Validator getDefaultJakartaValidator() {
    if (defaultJakartaValidator == null) {
      synchronized (ValidatingBuilder.class) {
        if (defaultJakartaValidator == null) {
          defaultJakartaValidator = Validation.buildDefaultValidatorFactory().getValidator();
        }
      }
    }
    return defaultJakartaValidator;
  }

  private final Builder<T> delegate;
  private final List<io.sundr.validation.Validator<T>> validators;
  private final Validator jakartaValidator;

  public ValidatingBuilder(Builder<T> delegate, List<io.sundr.validation.Validator<T>> validators) {
    this(delegate, validators, null);
  }

  public ValidatingBuilder(Builder<T> delegate, List<io.sundr.validation.Validator<T>> validators, Validator jakartaValidator) {
    this.delegate = Objects.requireNonNull(delegate, "Delegate builder cannot be null");
    this.validators = validators != null ? new ArrayList<>(validators) : Collections.emptyList();
    this.jakartaValidator = jakartaValidator;
  }

  @SafeVarargs
  public ValidatingBuilder(Builder<T> delegate, io.sundr.validation.Validator<T> first,
      io.sundr.validation.Validator<T>... rest) {
    this.delegate = Objects.requireNonNull(delegate, "Delegate builder cannot be null");
    Objects.requireNonNull(first, "Validator cannot be null");
    this.validators = new ArrayList<>();
    this.jakartaValidator = null;
    this.validators.add(first);
    if (rest != null) {
      this.validators.addAll(Arrays.asList(rest));
    }
  }

  /**
   * Creates a ValidatingBuilder that uses the default jakarta validation provider.
   */
  public static <T> ValidatingBuilder<T> withJakartaValidation(Builder<T> delegate) {
    return new ValidatingBuilder<>(delegate, Collections.emptyList(), getDefaultJakartaValidator());
  }

  /**
   * Creates a ValidatingBuilder that uses the default jakarta validation provider alongside the supplied validators.
   */
  public static <T> ValidatingBuilder<T> withJakartaValidation(Builder<T> delegate,
      List<io.sundr.validation.Validator<T>> validators) {
    return new ValidatingBuilder<>(delegate, validators, getDefaultJakartaValidator());
  }

  /**
   * Creates a ValidatingBuilder that uses the supplied jakarta validator.
   */
  public static <T> ValidatingBuilder<T> withJakartaValidator(Builder<T> delegate, Validator jakartaValidator) {
    return new ValidatingBuilder<>(delegate, Collections.emptyList(),
        Objects.requireNonNull(jakartaValidator, "Jakarta validator cannot be null"));
  }

  /**
   * Builds the object and validates it using all configured validators.
   *
   * @return a ValidationResult containing either the valid object or validation errors
   */
  public ValidationResult<T> build() {
    T item = delegate.build();
    List<ValidationError> allErrors = new ArrayList<>();

    for (io.sundr.validation.Validator<T> validator : validators) {
      List<ValidationError> errors = validator.validate(item);
      if (errors != null) {
        allErrors.addAll(errors);
      }
    }

    if (jakartaValidator != null) {
      Set<ConstraintViolation<T>> violations = jakartaValidator.validate(item);
      for (ConstraintViolation<T> violation : violations) {
        allErrors.add(new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()));
      }
    }

    if (allErrors.isEmpty()) {
      return ValidationResult.valid(item);
    }
    return ValidationResult.invalid(allErrors);
  }

  /**
   * Builds the object and validates it, throwing ValidationException if invalid.
   *
   * @return the built object if valid
   * @throws ValidationException if validation fails
   */
  public T buildOrThrow() {
    return build().orElseThrow();
  }
}
