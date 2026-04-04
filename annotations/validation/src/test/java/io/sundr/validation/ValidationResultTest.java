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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

public class ValidationResultTest {

  @Test
  public void shouldCreateValidResult() {
    ValidationResult<String> result = ValidationResult.valid("test");
    assertTrue(result.isValid());
    assertEquals("test", result.get());
    assertTrue(result.getErrors().isEmpty());
  }

  @Test
  public void shouldCreateInvalidResultWithSingleError() {
    ValidationError error = new ValidationError("field", "error message");
    ValidationResult<String> result = ValidationResult.invalid(error);
    assertFalse(result.isValid());
    assertEquals(1, result.getErrors().size());
    assertEquals("field", result.getErrors().get(0).getPath());
  }

  @Test
  public void shouldCreateInvalidResultWithMultipleErrors() {
    List<ValidationError> errors = Arrays.asList(
        new ValidationError("field1", "error 1"),
        new ValidationError("field2", "error 2"));
    ValidationResult<String> result = ValidationResult.invalid(errors);
    assertFalse(result.isValid());
    assertEquals(2, result.getErrors().size());
  }

  @Test
  public void shouldThrowOnGetWhenInvalid() {
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    assertThrows(ValidationException.class, result::get);
  }

  @Test
  public void shouldReturnDefaultValueOnOrElseWhenInvalid() {
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    assertEquals("default", result.orElse("default"));
  }

  @Test
  public void shouldReturnValueOnOrElseWhenValid() {
    ValidationResult<String> result = ValidationResult.valid("test");
    assertEquals("test", result.orElse("default"));
  }

  @Test
  public void shouldMapValidResult() {
    ValidationResult<String> result = ValidationResult.valid("test");
    ValidationResult<Integer> mapped = result.map(String::length);
    assertTrue(mapped.isValid());
    assertEquals(4, mapped.get());
  }

  @Test
  public void shouldThrowOnMapWhenInvalid() {
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    assertThrows(ValidationException.class, () -> result.map(String::length));
  }

  @Test
  public void shouldFlatMapValidResult() {
    ValidationResult<String> result = ValidationResult.valid("test");
    ValidationResult<Integer> flatMapped = result.flatMap(s -> ValidationResult.valid(s.length()));
    assertTrue(flatMapped.isValid());
    assertEquals(4, flatMapped.get());
  }

  @Test
  public void shouldThrowOnFlatMapWhenInvalid() {
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    assertThrows(ValidationException.class, () -> result.flatMap(s -> ValidationResult.valid(s.length())));
  }

  @Test
  public void shouldExecuteIfValidConsumer() {
    AtomicBoolean executed = new AtomicBoolean(false);
    ValidationResult<String> result = ValidationResult.valid("test");
    result.ifValid(s -> executed.set(true));
    assertTrue(executed.get());
  }

  @Test
  public void shouldNotExecuteIfValidConsumerWhenInvalid() {
    AtomicBoolean executed = new AtomicBoolean(false);
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    result.ifValid(s -> executed.set(true));
    assertFalse(executed.get());
  }

  @Test
  public void shouldExecuteIfInvalidConsumer() {
    AtomicReference<List<ValidationError>> capturedErrors = new AtomicReference<>();
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    result.ifInvalid(capturedErrors::set);
    assertEquals(1, capturedErrors.get().size());
  }

  @Test
  public void shouldThrowCustomExceptionOnOrElseThrow() {
    ValidationError error = new ValidationError("field", "error");
    ValidationResult<String> result = ValidationResult.invalid(error);
    RuntimeException ex = assertThrows(RuntimeException.class,
        () -> result.orElseThrow(errors -> new RuntimeException("Custom: " + errors.size())));
    assertEquals("Custom: 1", ex.getMessage());
  }

  @Test
  public void shouldRejectNullValueForValid() {
    assertThrows(NullPointerException.class, () -> ValidationResult.valid(null));
  }

  @Test
  public void shouldRejectEmptyErrorsForInvalid() {
    assertThrows(IllegalArgumentException.class, () -> ValidationResult.invalid(Collections.emptyList()));
  }
}
