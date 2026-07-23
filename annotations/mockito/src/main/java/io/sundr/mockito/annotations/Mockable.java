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

package io.sundr.mockito.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a type for mock DSL generation.
 * <p>
 * For an annotated type {@code Foo} a companion class named {@code prefix + Foo + suffix}
 * (by default {@code FooMock}) is generated in the same package, exposing a typed, fluent
 * stubbing and verification DSL on top of Mockito:
 *
 * <pre>
 * FooMock.mock(mock).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
 * FooMock.mock(mock).verify().create().withId("MY_ID").called();
 * </pre>
 *
 * Arguments that are not pinned with a {@code withXxx} method default to Mockito
 * {@code any()} matchers of the appropriate type.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Mockable {

  /**
   * @return the prefix prepended to the target type name to form the generated class name.
   */
  String prefix() default "";

  /**
   * @return the suffix appended to the target type name to form the generated class name.
   */
  String suffix() default "Mock";

  /**
   * @return the policy applied when the generated class name is already taken by an existing type.
   */
  OnNameCollision onNameCollision() default OnNameCollision.RENAME;

  /**
   * @return whether the verification half of the DSL should be generated.
   */
  boolean verification() default true;

  /**
   * @return method name patterns to include; empty means all stubbable methods.
   */
  String[] includes() default {};

  /**
   * @return method name patterns to exclude.
   */
  String[] excludes() default {};
}
