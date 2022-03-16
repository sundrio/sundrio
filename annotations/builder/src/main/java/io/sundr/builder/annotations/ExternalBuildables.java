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

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExternalBuildables {

  boolean editableEnabled() default true;

  boolean validationEnabled() default false;

  boolean lazyCollectionInitEnabled() default true;

  boolean lazyMapInitEnabled() default true;

  boolean generateBuilderPackage() default false;

  String builderPackage() default Constants.DEFAULT_BUILDER_PACKAGE;

  BuildableReference[] refs() default {};

  boolean includeInterfaces() default true;

  boolean includeAbstractClasses() default true;

  String[] value() default {};

  String[] includes() default {};

  String[] excludes() default {};

  Inline[] inline() default {};

  /**
   * Ignore properties
   * 
   * @return the names of the properties to ignore.
   */
  String[] ignore() default {};
}
