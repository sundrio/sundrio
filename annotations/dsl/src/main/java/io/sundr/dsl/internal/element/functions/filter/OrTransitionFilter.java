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

package io.sundr.dsl.internal.element.functions.filter;

import java.util.Collection;

import io.sundr.model.TypeDef;

public class OrTransitionFilter implements TransitionFilter {

  private final TransitionFilter[] filters;

  public OrTransitionFilter(TransitionFilter... filters) {
    this.filters = filters;
  }

  public Boolean apply(Collection<TypeDef> items) {
    for (TransitionFilter filter : filters) {
      if (filter.apply(items)) {
        return true;
      }
    }
    return false;
  }
}
