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

package io.sundr.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Predicates {

  public static <T> Predicate<T> until(final Predicate<T> predicate) {
    return new Predicate<T>() {
      private boolean hasMatched;

      @Override
      public boolean test(final T s) {
        hasMatched = hasMatched || predicate.test(s);
        return !hasMatched;
      }
    };
  }

  public static <T> Predicate<T> after(final Predicate<T> predicate) {
    return new Predicate<T>() {
      private boolean hasMatched;

      @Override
      public boolean test(final T s) {
        hasMatched = hasMatched || predicate.test(s);
        return hasMatched;
      }
    };
  }

  public static <T> Predicate<T> distinct() {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(t, Boolean.TRUE) == null;
  }

  public static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
    Map<Object, Boolean> map = new ConcurrentHashMap<>();
    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

}
