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

/**
 * An annotation that triggers the generation of classes using custom generator functions.
 *
 * The annotation accepts wraps around one or more generator classes that must implement Function&lt;TypeDef, TypeDef&gt;.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface Generators {

  /**
   * Array of generator annotations
   *
   * @return the array of generator annotations
   */
  Generator[] value();

}
