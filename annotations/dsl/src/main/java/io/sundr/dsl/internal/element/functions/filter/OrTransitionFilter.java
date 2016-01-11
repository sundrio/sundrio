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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrTransitionFilter implements TransitionFilter {

    private final Set<TransitionFilter> filters;

    public OrTransitionFilter(Set<TransitionFilter> filters) {
        this.filters = filters;
    }

    public OrTransitionFilter(TransitionFilter... filters) {
        this.filters = new HashSet<TransitionFilter>(Arrays.asList(filters));
    }

    public Boolean apply(Set<String> item) {
        for (TransitionFilter filter : filters) {
            if (filter.apply(item)) {
                return true;
            }
        }
        return false;
    }
}
