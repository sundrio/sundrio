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
public class RequiresAnyFilter implements TransitionFilter {

    private final Set<String> keywords;

    public RequiresAnyFilter(Set<String> keywords) {
        this.keywords = keywords;
    }

    public RequiresAnyFilter(String... keywords) {
        this(new HashSet<String>(Arrays.asList(keywords)));
    }

    public Boolean apply(Set<String> path) {
        for (String keyword : keywords) {
            if (path.contains(keyword)) {
                return true;
            }
        }
        return keywords.isEmpty();
    }
}
