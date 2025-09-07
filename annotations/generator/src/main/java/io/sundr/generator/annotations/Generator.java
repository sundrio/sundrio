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

package io.sundr.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

import io.sundr.model.TypeDef;

/**
 * An annotation that triggers the generation of classes using custom generator functions.
 *
 * The annotation accepts a class that implements Function&lt;TypeDef, TypeDef&gt;.
 * The generator function will be applied to the TypeDef of the annotated class, and the resulting
 * TypeDef will be used to generate a new class.
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface Generator {

  /**
   * The generator class that implements Function&lt;TypeDef, TypeDef&gt;.
   *
   * @return the generator classes to apply
   */
  Class<? extends Function<TypeDef, TypeDef>> value();
}
