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

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.sundr.SundrException;

/**
 * Factory for obtaining validators using reflection-based lookup.
 * <p>
 * This class looks for generated {@code XXXValidator} classes following the naming convention
 * where XXX is the simple name of the validated type. The validator class is expected to be
 * in the same package as the validated type.
 * <p>
 * Example usage:
 *
 * <pre>
 * // Validate an object
 * List&lt;ValidationError&gt; errors = Validators.of(person).validate();
 *
 * // Use with builder
 * builder.validate(Validators.of(Person.class)).build();
 * </pre>
 */
public final class Validators {

  private static final Map<Class<?>, ValidatorInfo> VALIDATOR_CACHE = new ConcurrentHashMap<>();
  private static final String VALIDATOR_SUFFIX = "Validator";
  private static final String VALIDATE_METHOD = "validate";

  private Validators() {
  }

  /**
   * Creates a validation context for the given object.
   * <p>
   * Looks for a class named {@code XXXValidator} in the same package as the object's class,
   * where XXX is the simple class name.
   *
   * @param item the object to validate
   * @param <T> the type of the object
   * @return a validation context that can be used to perform validation
   * @throws IllegalArgumentException if no validator is found for the type
   */
  @SuppressWarnings("unchecked")
  public static <T> ValidationContext<T> of(T item) {
    if (item == null) {
      return new ValidationContext<>(null, null);
    }
    Class<T> type = (Class<T>) item.getClass();
    Validator<T> validator = getValidator(type);
    if (validator == null) {
      throw new IllegalArgumentException("No validator found for type: " + type.getName()
          + ". Expected class: " + expectedValidatorName(type));
    }
    return new ValidationContext<>(item, validator);
  }

  /**
   * Gets the validator for the given type.
   *
   * @param type the class to get validator for
   * @param <T> the type
   * @return the validator
   * @throws IllegalArgumentException if no validator is found for the type
   */
  public static <T> Validator<T> of(Class<T> type) {
    Validator<T> validator = getValidator(type);
    if (validator == null) {
      throw new IllegalArgumentException("No validator found for type: " + type.getName()
          + ". Expected class: " + expectedValidatorName(type));
    }
    return validator;
  }

  private static String expectedValidatorName(Class<?> type) {
    Package pkg = type.getPackage();
    String prefix = pkg != null ? pkg.getName() + "." : "";
    return prefix + type.getSimpleName() + VALIDATOR_SUFFIX;
  }

  /**
   * Gets the validator for the given type, or null if none exists.
   * <p>
   * The validator is looked up by convention: for a class {@code com.example.Person},
   * it looks for {@code com.example.PersonValidator}.
   *
   * @param type the class to get validator for
   * @param <T> the type
   * @return the validator, or null if not found
   */
  @SuppressWarnings("unchecked")
  public static <T> Validator<T> getValidator(Class<T> type) {
    ValidatorInfo info = VALIDATOR_CACHE.computeIfAbsent(type, Validators::lookupValidator);
    return (Validator<T>) info.validator;
  }

  /**
   * Checks if a validator exists for the given type.
   *
   * @param type the class to check
   * @return true if a validator exists
   */
  public static boolean hasValidator(Class<?> type) {
    ValidatorInfo info = VALIDATOR_CACHE.computeIfAbsent(type, Validators::lookupValidator);
    return info.validator != null;
  }

  private static ValidatorInfo lookupValidator(Class<?> type) {
    Class<?> currentClass = type;
    while (currentClass != null && currentClass != Object.class) {
      ValidatorInfo info = tryLookupValidator(currentClass, type);
      if (info.validator != null) {
        return info;
      }
      currentClass = currentClass.getSuperclass();
    }
    return new ValidatorInfo(null);
  }

  private static ValidatorInfo tryLookupValidator(Class<?> validatorForType, Class<?> actualType) {
    String validatorClassName = expectedValidatorName(validatorForType);

    try {
      Class<?> validatorClass = Class.forName(validatorClassName, true, actualType.getClassLoader());
      Method validateMethod = validatorClass.getMethod(VALIDATE_METHOD, validatorForType);

      Validator<?> validator = item -> {
        try {
          @SuppressWarnings("unchecked")
          List<ValidationError> result = (List<ValidationError>) validateMethod.invoke(null, item);
          return result != null ? result : Collections.emptyList();
        } catch (ReflectiveOperationException e) {
          throw SundrException.launderThrowable(e);
        }
      };

      return new ValidatorInfo(validator);
    } catch (ClassNotFoundException e) {
      return new ValidatorInfo(null);
    } catch (NoSuchMethodException e) {
      return new ValidatorInfo(null);
    }
  }

  private static class ValidatorInfo {
    final Validator<?> validator;

    ValidatorInfo(Validator<?> validator) {
      this.validator = validator;
    }
  }

  /**
   * A context holding an object and its validator, allowing deferred validation.
   *
   * @param <T> the type of the object being validated
   */
  public static class ValidationContext<T> {
    private final T item;
    private final Validator<T> validator;

    ValidationContext(T item, Validator<T> validator) {
      this.item = item;
      this.validator = validator;
    }

    /**
     * Performs validation on the object.
     *
     * @return list of validation errors, empty if valid
     */
    public List<ValidationError> validate() {
      if (item == null || validator == null) {
        return Collections.emptyList();
      }
      return validator.validate(item);
    }
  }
}
