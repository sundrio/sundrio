/*
 * Copyright 2016 The original authors.
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

package io.sundr;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

public class FunctionFactory<X, Y> implements Function<X, Y> {

  private final Map<X, Y> cache;
  private final Function<X, Y> function;
  private final Function<X, Y> fallback;
  private final Predicate<X> fallbackPredicate;
  private final int maximumRecursionLevel;
  private final int maximumNestingDepth;

  private final Stack<X> ownStack;
  private static final Stack globalStack = new Stack();

  public FunctionFactory(Map<X, Y> cache, Function<X, Y> function, Function<X, Y> fallback, Predicate<X> fallbackPredicate,
      int maximumRecursionLevel, int maximumNestingDepth, Stack<X> ownStack) {
    this.cache = cache;
    this.function = function;
    this.fallback = fallback;
    this.fallbackPredicate = fallbackPredicate;
    this.maximumRecursionLevel = maximumRecursionLevel;
    this.maximumNestingDepth = maximumNestingDepth;
    this.ownStack = ownStack;
  }

  public Y apply(X item) {
    Y result;
    synchronized (function) {
      ownStack.push(item);
      globalStack.push(item);
      try {
        result = cache != null ? cache.get(item) : null;
        if (result == null) {
          int recursionLevel = ownStack != null ? Collections.frequency(ownStack, item) : 0;
          int nestingDepth = globalStack.size();
          boolean recursionLevelExceeded = recursionLevel > maximumRecursionLevel && maximumRecursionLevel > 0;
          boolean nestringDeptExceeded = nestingDepth > maximumNestingDepth && maximumNestingDepth > 0;
          boolean predicateMatched = fallbackPredicate != null && fallbackPredicate.test(item);
          if ((recursionLevelExceeded || nestringDeptExceeded || predicateMatched) && fallback != null) {
            result = fallback.apply(item);
          } else {
            result = function.apply(item);
            cacheIfEnabled(item, result);
          }
        }
      } finally {
        ownStack.pop();
        globalStack.pop();
      }
      return result;
    }
  }

  private void cacheIfEnabled(X item, Y result) {
    if (cache != null) {
      cache.put(item, result);
    }
  }

  public static <X, Y> FunctionFactory<X, Y> cache(Function<X, Y> function) {
    return new FunctionFactory<X, Y>(new HashMap<X, Y>(), function, null, null, 0, 0, new Stack<X>());
  }

  public static <X, Y> FunctionFactory<X, Y> wrap(Function<X, Y> function) {
    return new FunctionFactory<X, Y>(null, function, null, null, 0, 0, new Stack<X>());
  }

  public FunctionFactory<X, Y> withFallback(Function<X, Y> fallback) {
    return new FunctionFactory<X, Y>(cache, function, fallback, fallbackPredicate, maximumRecursionLevel, maximumNestingDepth,
        ownStack);
  }

  public FunctionFactory<X, Y> withMaximumRecursionLevel(int maximumRecursionLevel) {
    return new FunctionFactory<X, Y>(cache, function, fallback, fallbackPredicate, maximumRecursionLevel, maximumNestingDepth,
        ownStack);
  }

  public FunctionFactory<X, Y> withMaximumNestingDepth(int maximumNestingDepth) {
    return new FunctionFactory<X, Y>(cache, function, fallback, fallbackPredicate, maximumRecursionLevel, maximumNestingDepth,
        ownStack);
  }

  public FunctionFactory<X, Y> withFallbackPredicate(Predicate<X> fallbackPredicate) {
    return new FunctionFactory<X, Y>(cache, function, fallback, fallbackPredicate, maximumRecursionLevel, maximumNestingDepth,
        ownStack);
  }
}
