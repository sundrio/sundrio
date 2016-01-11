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

/**
 * Function that determines if a set of visited keywords contains ALL of the specified requirements.
 */
public class RequiresOnlyFilter implements TransitionFilter {

    private final Boolean explicit;
    private final Boolean orNone;
    private final Set<String> keywords;
    private final TransitionFilter filter;

    public RequiresOnlyFilter(Boolean explicit, Boolean orNone, Set<String> keywords) {
        this.explicit = explicit;
        this.orNone = orNone;
        this.keywords = keywords;
        this.filter = orNone
                ? new OrTransitionFilter(new RequiresNoneOfFilter(), new RequiresAnyFilter(keywords))
                : new RequiresAnyFilter(keywords);
    }

    public RequiresOnlyFilter(Boolean explicit, Boolean orNone, String... keywords) {
        this(explicit, orNone, new HashSet<String>(Arrays.asList(keywords)));
    }

    public Boolean apply(Set<String> path) {
        if (!keywords.isEmpty()) {
            //Check that there is nothing undefined in the path.
            for (String keyword : path) {
                if (!keywords.contains(keyword)) {
                    return false;
                }
            }
            //Check that the requirements are met.
            return filter.apply(path);
        } else if (!explicit) {
            return true;
        } else {
            return path.isEmpty();
        }
    }
}
