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

import java.util.Objects;

/**
 * Represents a validation error with information about the property path,
 * error message, and optionally the invalid value.
 */
public final class ValidationError {

  private final String path;
  private final String message;
  private final Object invalidValue;

  public ValidationError(String message) {
    this(null, message, null);
  }

  public ValidationError(String path, String message) {
    this(path, message, null);
  }

  public ValidationError(String path, String message, Object invalidValue) {
    this.path = path;
    this.message = message;
    this.invalidValue = invalidValue;
  }

  public String getPath() {
    return path;
  }

  public String getMessage() {
    return message;
  }

  public Object getInvalidValue() {
    return invalidValue;
  }

  public ValidationError withPathPrefix(String prefix) {
    if (prefix == null || prefix.isEmpty()) {
      return this;
    }
    String newPath = path == null || path.isEmpty() ? prefix : prefix + "." + path;
    return new ValidationError(newPath, message, invalidValue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ValidationError that = (ValidationError) o;
    return Objects.equals(path, that.path) &&
        Objects.equals(message, that.message) &&
        Objects.equals(invalidValue, that.invalidValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, message, invalidValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (path != null && !path.isEmpty()) {
      sb.append(path).append(": ");
    }
    sb.append(message);
    if (invalidValue != null) {
      sb.append(" (was: ").append(invalidValue).append(")");
    }
    return sb.toString();
  }
}
