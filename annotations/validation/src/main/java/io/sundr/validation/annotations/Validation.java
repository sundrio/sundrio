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

package io.sundr.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as a validation method.
 * <p>
 * The target type is inferred from the method's first parameter.
 * <p>
 * The annotated method must be either:
 * <ul>
 * <li>A static method</li>
 * <li>An instance method in a class with a public no-arg constructor</li>
 * </ul>
 * <p>
 * The method should have one of the following signatures:
 * <ul>
 * <li>{@code List<ValidationError> validate(T item)} - Returns list of errors (empty if valid)</li>
 * <li>{@code T validate(T item)} - Validates and returns the item, throws exception on failure</li>
 * <li>{@code void validate(T item)} - Throws exception on validation failure</li>
 * </ul>
 * <p>
 * The annotation processor generates a {@code XXXValidator} class (where XXX is the validated type)
 * that orchestrates all validation methods for that type. The generated validator can be used via:
 * <ul>
 * <li>Direct call: {@code PersonValidator.validate(person)}</li>
 * <li>Reflection lookup: {@code Validators.of(person)}</li>
 * <li>Builder integration: {@code builder.validate(PersonValidator::validate).build()}</li>
 * </ul>
 * <p>
 * Example usage:
 *
 * <pre>
 * public class PersonValidations {
 *     &#64;Validation
 *     public static List&lt;ValidationError&gt; validateName(Person person) {
 *         List&lt;ValidationError&gt; errors = new ArrayList&lt;&gt;();
 *         if (person.getName() == null) {
 *             errors.add(new ValidationError("name", "must not be null"));
 *         }
 *         return errors;
 *     }
 *
 *     &#64;Validation
 *     public static void validateAge(Person person) {
 *         if (person.getAge() &lt; 0) {
 *             throw new ValidationException(
 *                 Collections.singletonList(new ValidationError("age", "must be non-negative")));
 *         }
 *     }
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Validation {
}
