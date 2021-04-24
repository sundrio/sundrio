/**
 * Copyright 2015 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.sundr.model;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Nameable extends Node {

  String PACKAGE_SEPARATOR_REGEX = "\\.";
  Predicate<String> IS_UPPER_CASE = w -> Character.isUpperCase(w.charAt(0));
  Predicate<String> IN_PACKAGE = until(IS_UPPER_CASE);
  Predicate<String> OUT_OF_PACKAGE = after(IS_UPPER_CASE);

  /**
   * Get the fully qualified name of the type.
   * The expected format is <<package>>.<<outerClass>>.<<inerClass>>.
   * 
   * @return the fully qualified name
   */
  String getFullyQualifiedName();

  /**
   * Get the name.
   * 
   * @return the name
   */
  default String getName() {
    return getClassName(getFullyQualifiedName());
  }

  /**
   * Get the package name
   * 
   * @return the package name.
   */
  default String getPackageName() {
    return getPackageName(getFullyQualifiedName());
  }

  static String getClassName(String fullyQualifiedName) {
    return Arrays.stream(fullyQualifiedName.split(PACKAGE_SEPARATOR_REGEX)).filter(after(IS_UPPER_CASE))
        .collect(Collectors.joining(DOT));
  }

  static String getPackageName(String fullyQualifiedName) {
    return Arrays.stream(fullyQualifiedName.split(PACKAGE_SEPARATOR_REGEX)).filter(until(IS_UPPER_CASE))
        .collect(Collectors.joining(DOT));
  }

  static <T> Predicate<T> until(final Predicate<T> predicate) {
    return new Predicate<T>() {
      private boolean hasMatched;

      @Override
      public boolean test(final T s) {
        hasMatched = hasMatched || predicate.test(s);
        return !hasMatched;
      }
    };
  }

  static <T> Predicate<T> after(final Predicate<T> predicate) {
    return new Predicate<T>() {
      private boolean hasMatched;

      @Override
      public boolean test(final T s) {
        hasMatched = hasMatched || predicate.test(s);
        return hasMatched;
      }
    };
  }
}
