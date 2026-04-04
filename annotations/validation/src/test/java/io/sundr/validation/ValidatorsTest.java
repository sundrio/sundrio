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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ValidatorsTest {

  @Test
  public void shouldReturnNullValidatorWhenNotFound() {
    Validator<String> validator = Validators.getValidator(String.class);
    assertNull(validator);
  }

  @Test
  public void shouldReturnFalseWhenNoValidatorExists() {
    assertFalse(Validators.hasValidator(String.class));
  }

  @Test
  public void shouldThrowWhenUsingOfWithNoValidatorForClass() {
    assertThrows(IllegalArgumentException.class, () -> Validators.of(String.class));
  }

  @Test
  public void shouldThrowWhenUsingOfWithNoValidatorForObject() {
    assertThrows(IllegalArgumentException.class, () -> Validators.of("test"));
  }

  @Test
  public void shouldReturnEmptyListForNullObject() {
    List<ValidationError> errors = Validators.of((Object) null).validate();
    assertNotNull(errors);
    assertTrue(errors.isEmpty());
  }
}
