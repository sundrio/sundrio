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
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents the result of a validation operation.
 * Similar to Optional, but carries validation errors when invalid.
 *
 * @param <T> the type of the validated value
 */
public final class ValidationResult<T> {

  private final T value;
  private final List<ValidationError> errors;

  private ValidationResult(T value, List<ValidationError> errors) {
    this.value = value;
    this.errors = errors != null ? Collections.unmodifiableList(new ArrayList<>(errors)) : Collections.emptyList();
  }

  /**
   * Creates a valid result containing the given value.
   *
   * @param value the validated value (must not be null)
   * @param <T> the type of the value
   * @return a valid ValidationResult
   */
  public static <T> ValidationResult<T> valid(T value) {
    Objects.requireNonNull(value, "Value cannot be null for a valid result");
    return new ValidationResult<>(value, Collections.emptyList());
  }

  /**
   * Creates an invalid result containing the given errors.
   *
   * @param errors the validation errors (must not be empty)
   * @param <T> the type that would have been validated
   * @return an invalid ValidationResult
   */
  public static <T> ValidationResult<T> invalid(List<ValidationError> errors) {
    if (errors == null || errors.isEmpty()) {
      throw new IllegalArgumentException("Invalid result must have at least one error");
    }
    return new ValidationResult<>(null, errors);
  }

  /**
   * Creates an invalid result containing the given value and errors.
   * This allows access to the invalid value for inspection or partial use.
   *
   * @param value the invalid value
   * @param errors the validation errors (must not be empty)
   * @param <T> the type that was validated
   * @return an invalid ValidationResult containing the value
   */
  public static <T> ValidationResult<T> invalid(T value, List<ValidationError> errors) {
    if (errors == null || errors.isEmpty()) {
      throw new IllegalArgumentException("Invalid result must have at least one error");
    }
    return new ValidationResult<>(value, errors);
  }

  /**
   * Creates an invalid result containing a single error.
   *
   * @param error the validation error
   * @param <T> the type that would have been validated
   * @return an invalid ValidationResult
   */
  public static <T> ValidationResult<T> invalid(ValidationError error) {
    Objects.requireNonNull(error, "Error cannot be null");
    return new ValidationResult<>(null, Collections.singletonList(error));
  }

  /**
   * Returns true if this result represents a valid value.
   *
   * @return true if valid, false otherwise
   */
  public boolean isValid() {
    return errors.isEmpty();
  }

  /**
   * Returns the validated value.
   *
   * @return the value
   * @throws ValidationException if this result is invalid
   */
  public T get() {
    if (!isValid()) {
      throw new ValidationException(errors);
    }
    return value;
  }

  /**
   * Returns the list of validation errors.
   * Returns an empty list if this result is valid.
   *
   * @return the validation errors
   */
  public List<ValidationError> getErrors() {
    return errors;
  }

  /**
   * Applies the given function to the value if valid.
   *
   * @param mapper the function to apply
   * @param <R> the type of the result
   * @return a new ValidationResult with the mapped value, or the same invalid result
   * @throws ValidationException if this result is invalid
   */
  public <R> ValidationResult<R> map(Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(mapper, "Mapper cannot be null");
    if (!isValid()) {
      throw new ValidationException(errors);
    }
    return valid(mapper.apply(value));
  }

  /**
   * Applies the given function to the value if valid.
   *
   * @param mapper the function to apply
   * @param <R> the type of the result
   * @return the ValidationResult returned by the mapper, or the same invalid result
   * @throws ValidationException if this result is invalid
   */
  public <R> ValidationResult<R> flatMap(Function<? super T, ValidationResult<R>> mapper) {
    Objects.requireNonNull(mapper, "Mapper cannot be null");
    if (!isValid()) {
      throw new ValidationException(errors);
    }
    return Objects.requireNonNull(mapper.apply(value), "Mapper result cannot be null");
  }

  /**
   * Returns the value if valid, otherwise returns the other value.
   *
   * @param other the value to return if invalid
   * @return the value if valid, other otherwise
   */
  public T orElse(T other) {
    return isValid() ? value : other;
  }

  /**
   * Returns the value if valid, otherwise returns the result of the supplier.
   *
   * @param supplier the supplier to call if invalid
   * @return the value if valid, the supplier result otherwise
   */
  public T orElseGet(Supplier<? extends T> supplier) {
    Objects.requireNonNull(supplier, "Supplier cannot be null");
    return isValid() ? value : supplier.get();
  }

  /**
   * Returns the value if valid, otherwise throws ValidationException.
   *
   * @return the value
   * @throws ValidationException if invalid
   */
  public T orElseThrow() {
    return get();
  }

  /**
   * Returns the value if valid, otherwise throws an exception created by the supplier.
   *
   * @param exceptionSupplier function that creates the exception from the errors
   * @param <X> the exception type
   * @return the value
   * @throws X if invalid
   */
  public <X extends Throwable> T orElseThrow(Function<List<ValidationError>, ? extends X> exceptionSupplier) throws X {
    Objects.requireNonNull(exceptionSupplier, "Exception supplier cannot be null");
    if (!isValid()) {
      throw exceptionSupplier.apply(errors);
    }
    return value;
  }

  /**
   * Executes the given consumer if this result is valid.
   *
   * @param consumer the consumer to execute
   */
  public void ifValid(Consumer<? super T> consumer) {
    Objects.requireNonNull(consumer, "Consumer cannot be null");
    if (isValid()) {
      consumer.accept(value);
    }
  }

  /**
   * Executes the given consumer if this result is invalid.
   *
   * @param consumer the consumer to execute with the errors
   */
  public void ifInvalid(Consumer<List<ValidationError>> consumer) {
    Objects.requireNonNull(consumer, "Consumer cannot be null");
    if (!isValid()) {
      consumer.accept(errors);
    }
  }

  /**
   * Executes one of the given consumers depending on validity.
   *
   * @param validConsumer the consumer to execute if valid
   * @param invalidConsumer the consumer to execute if invalid
   */
  public void ifValidOrElse(Consumer<? super T> validConsumer, Consumer<List<ValidationError>> invalidConsumer) {
    Objects.requireNonNull(validConsumer, "Valid consumer cannot be null");
    Objects.requireNonNull(invalidConsumer, "Invalid consumer cannot be null");
    if (isValid()) {
      validConsumer.accept(value);
    } else {
      invalidConsumer.accept(errors);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ValidationResult<?> that = (ValidationResult<?>) o;
    return Objects.equals(value, that.value) && Objects.equals(errors, that.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, errors);
  }

  @Override
  public String toString() {
    if (isValid()) {
      return "ValidationResult.valid(" + value + ")";
    }
    return "ValidationResult.invalid(" + errors + ")";
  }
}
