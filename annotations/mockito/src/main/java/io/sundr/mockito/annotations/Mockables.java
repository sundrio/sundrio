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
 * Requests mock DSL generation for types that are not annotated themselves.
 * <p>
 * The natural home of this annotation is a small marker class in test sources, which keeps
 * production code untouched and makes the generated classes land in generated test sources:
 *
 * <pre>
 * &#64;Mockables({ OrchestratorTemplateService.class, PaymentGateway.class })
 * class MockTargets {
 * }
 * </pre>
 *
 * For every listed type a companion mock DSL class is generated in the type's package, exactly
 * as if the type carried {@link Mockable}. Additionally an aggregator class (by default named
 * {@code Mocks}) is generated in the marker's package, carrying one {@code mock} overload per
 * listed type plus {@code when}/{@code verify} passthroughs to Mockito, so that a single static
 * import covers both the fluent DSL and plain Mockito across the whole suite:
 *
 * <pre>
 * import static com.acme.tests.Mocks.*;
 *
 * mock(service).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
 * mock(service).verify().create().withId("MY_ID").called();
 *
 * when(service.other(any())).thenReturn(42); // plain Mockito, same import
 * </pre>
 */
@Target({ ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.SOURCE)
public @interface Mockables {

  /**
   * @return the types to generate mock DSL classes for.
   */
  Class<?>[] value() default {};

  /**
   * @return the prefix prepended to each target type name to form the generated class name.
   */
  String prefix() default "";

  /**
   * @return the suffix appended to each target type name to form the generated class name.
   */
  String suffix() default "Mock";

  /**
   * @return the policy applied when a generated class name is already taken by an existing type.
   */
  OnNameCollision onNameCollision() default OnNameCollision.RENAME;

  /**
   * @return the simple name of the aggregator class generated in the marker's package;
   *         an empty string disables aggregator generation.
   */
  String aggregator() default "Mocks";

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
