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
      throw new IllegalStateException("The pinned arguments " + pinned + " match " + selected.size()
          + " overloads of " + overloads
          + "; pin more arguments, use a withXxxAny() pin or andNoOtherArgs() to disambiguate");
    }
    return selected.get(0);
  }
}
