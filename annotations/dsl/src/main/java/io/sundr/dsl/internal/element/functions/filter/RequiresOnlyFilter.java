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

import io.sundr.codegen.model.TypeDef;

import java.util.Collection;
import java.util.Set;

import static io.sundr.dsl.internal.utils.GraphUtils.getClasses;
import static io.sundr.dsl.internal.utils.GraphUtils.getKeywords;
import static io.sundr.dsl.internal.utils.GraphUtils.getMethods;

/**
 * Function that determines if a set of visited keywords contains ALL of the specified requirements.
 */
public class RequiresOnlyFilter implements TransitionFilter {

    private final Boolean explicit;

    private final Set<String> classes;
    private final Set<String> keywords;
    private final Set<String> methods;

    private final TransitionFilter filter;

    public RequiresOnlyFilter(Set<String> classes, Set<String> keywords, Set<String> methods, Boolean explicit, Boolean orNone) {
        this.classes = classes;
        this.keywords = keywords;
        this.methods = methods;
        this.explicit = explicit;
        this.filter = orNone
                ? new OrTransitionFilter(new RequiresNoneFilter(), new RequiresAnyFilter(classes, keywords, methods))
                : new RequiresAnyFilter(classes, keywords, methods);
    }

    public Boolean apply(Collection<TypeDef> items) {
        Set<String> pathClasses = getClasses(items);
        Set<String> pathKeywords = getKeywords(items);
        Set<String> pathMethods = getMethods(items);

        if (!classes.isEmpty()) {
            //Check that there is nothing undefined in the path.
            for (String c : pathClasses) {
                if (!classes.contains(c)) {
                    return false;
                }
            }
        }

        if (!keywords.isEmpty()) {
            //Check that there is nothing undefined in the path.
            for (String k : pathKeywords) {
                if (!keywords.contains(k)) {
                    return false;
                }
            }
        }

        if (!methods.isEmpty()) {
            //Check that there is nothing undefined in the path.
            for (String m : pathMethods) {
                if (!methods.contains(m)) {
                    return false;
                }
            }
        }

        if (!classes.isEmpty() || !keywords.isEmpty() || !methods.isEmpty()) {
            return filter.apply(items);
        } else if (!explicit) {
            return true;
        } else {
            return pathClasses.isEmpty() && pathKeywords.isEmpty() && pathMethods.isEmpty();
        }
    }
}
