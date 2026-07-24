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

package io.sundr.mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

/**
 * Matches overloads against the arguments pinned on a stubbing or verification builder.
 * <p>
 * By default an overload matches when it accepts all pinned names; in exact mode it must
 * have exactly the pinned names and nothing else.
 */
public final class OverloadSelector {

  private final List<Set<String>> overloads = new ArrayList<>();

  /**
   * Registers an overload by its parameter names, in declaration order.
   *
   * @param parameterNames the parameter names of the overload.
   * @return this selector for chaining.
   */
  public OverloadSelector overload(String... parameterNames) {
    overloads.add(new LinkedHashSet<>(Arrays.asList(parameterNames)));
    return this;
  }

  /**
   * Returns the indices of all overloads matching the pinned names.
   *
   * @param pinned the names pinned via {@code withXxx} methods.
   * @param exact when true, only the overload whose parameters are exactly the pinned names matches.
   * @return the indices of the matching overloads, in registration order.
   */
  public List<Integer> selectAll(Collection<String> pinned, boolean exact) {
    List<Integer> selected = new ArrayList<>();
    for (int i = 0; i < overloads.size(); i++) {
      Set<String> names = overloads.get(i);
      boolean matches = exact
          ? names.size() == pinned.size() && names.containsAll(pinned)
          : names.containsAll(pinned);
      if (matches) {
        selected.add(i);
      }
    }
    if (selected.isEmpty()) {
      throw new IllegalStateException("No overload " + (exact ? "has exactly" : "accepts")
          + " the pinned arguments " + pinned + "; available overloads: " + overloads);
    }
    return selected;
  }

  /**
   * Returns the single overload matching the pinned names, for operations that must
   * target one concrete signature, like positive verifications.
   *
   * @param pinned the names pinned via {@code withXxx} methods.
   * @param exact when true, only the overload whose parameters are exactly the pinned names matches.
   * @return the index of the matching overload.
   */
  public int selectOne(Collection<String> pinned, boolean exact) {
    List<Integer> selected = selectAll(pinned, exact);
    if (selected.size() > 1) {
      throw ambiguous(pinned, selected);
    }
    return selected.get(0);
  }

  /**
   * Returns the single overload to target for a positive verification, auto-selecting by which
   * overload was actually invoked when the pinned arguments alone match more than one.
   * <p>
   * When the pinned names match exactly one overload, that overload is returned unchanged. When
   * they match several, the recorded invocations on the mock are inspected: an overload counts as
   * invoked when at least one recorded invocation of {@code methodName} satisfies its matcher.
   * Exactly one invoked overload is returned. If none was invoked, the first candidate is returned
   * so native verification reports the missing invocation. If two or more distinct overloads were
   * invoked, the positive count is genuinely ambiguous and an {@link IllegalStateException} is thrown.
   *
   * @param pinned the names pinned via {@code withXxx} methods.
   * @param exact when true, only the overload whose parameters are exactly the pinned names matches.
   * @param mock the mock the verification targets.
   * @param methodName the mocked method name shared by the overloads.
   * @param perOverloadMatchers a matcher per registered overload, index-aligned with registration,
   *        testing whether a recorded invocation's arguments belong to that overload.
   * @return the index of the overload to verify.
   */
  public int selectOneInvoked(Collection<String> pinned, boolean exact, Object mock, String methodName,
      List<Predicate<Object[]>> perOverloadMatchers) {
    List<Integer> candidates = selectAll(pinned, exact);
    if (candidates.size() == 1) {
      return candidates.get(0);
    }

    List<Invocation> recorded = new ArrayList<>(Mockito.mockingDetails(mock).getInvocations());
    List<Integer> invoked = new ArrayList<>();
    for (Integer candidate : candidates) {
      Predicate<Object[]> matcher = perOverloadMatchers.get(candidate);
      for (Invocation invocation : recorded) {
        if (!invocation.getMethod().getName().equals(methodName)) {
          continue;
        }
        if (matcher.test(invocation.getArguments())) {
          invoked.add(candidate);
          break;
        }
      }
    }

    if (invoked.size() == 1) {
      return invoked.get(0);
    }
    if (invoked.isEmpty()) {
      return candidates.get(0);
    }
    throw ambiguous(pinned, invoked);
  }

  private IllegalStateException ambiguous(Collection<String> pinned, List<Integer> matches) {
    return new IllegalStateException("The pinned arguments " + pinned + " match " + matches.size()
        + " overloads of " + overloads
        + "; pin more arguments, use a withXxxAny() pin or andNoOtherArgs() to disambiguate");
  }
}
