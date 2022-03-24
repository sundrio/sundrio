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

package io.sundr.builder.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.sundr.builder.Constants;

/**
 * An annotation that triggers the generation of a {@link io.sundr.builder.Builder}.
 * The annotation may be added to either the type or constructor.
 *
 * When added to a constructor all fields of the constructor will be exposed to the builder.
 * Additionally, fields (both declared and inherited) that are writeable via setter will be taken into account.
 * To exclude fields from the builder you can use the `ignore` property.
 *
 * When the annotation is added to interfaces or abstract classes, fluent interfaces and implementation will be
 * generated but no builder. Buildable classes that extend, implement buildable abstract classes or interfaces respectively
 * will reuse the generated fluent types.
 *
 * Generic fields are meant to be processed at the level they are resolved.
 *
 */
@Target({ ElementType.CONSTRUCTOR, ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface Buildable {

  boolean editableEnabled() default true;

  boolean validationEnabled() default false;

  boolean lazyCollectionInitEnabled() default true;

  boolean lazyMapInitEnabled() default true;

  boolean generateBuilderPackage() default false;

  String builderPackage() default Constants.DEFAULT_BUILDER_PACKAGE;

  BuildableReference[] refs() default {};

  Inline[] inline() default {};

  /**
   * Properties to ignore
   *
   * In most cases the sturcture of the class will designate the properties to expose to the builder.
   *
   * However, there might be cases that we need to explicitly request a field not to be exposed.
   * Example: Extending a 3rd party class that exposes setters, or requiring setter for a field that the builder should not
   * expose).
   *
   * In such cases `ignore` can be used.
   * 
   * @return the names of the properties to ignore.
   */
  String[] ignore() default {};
}
