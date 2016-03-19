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

package io.sundr.dsl.internal.utils;

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import static io.sundr.dsl.internal.Constants.BEGIN_SCOPE;
import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.END_SCOPE;
import static io.sundr.dsl.internal.Constants.FILTER;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isCardinalityMultiple;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;

public final class GraphUtils {

    private GraphUtils() {
        //Utility Class
    }

    public static Set<JavaClazz> exclusion(Set<JavaClazz> one, Collection<JavaType> excluded) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>();
        for (JavaClazz item : one) {
            if (!excluded.contains(item.getType()) || isTerminal(item) || isCardinalityMultiple(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static boolean isSatisfied(JavaClazz candidate, List<JavaType> path) {
        Set<String> keywordsAndScopes = new LinkedHashSet<String>();
        Set<String> visitedKeywords = getKeywords(path);
        Deque<String> activeScopes = getScopes(path);
        keywordsAndScopes.addAll(visitedKeywords);
        keywordsAndScopes.addAll(activeScopes);

        TransitionFilter filter = (TransitionFilter) candidate.getType().getAttributes().get(FILTER);
        Boolean multiple = (Boolean) candidate.getType().getAttributes().get(CARDINALITY_MULTIPLE);

        Set<String> keywords = (Set<String>) candidate.getType().getAttributes().get(KEYWORDS);
        if (!activeScopes.isEmpty() && !keywords.contains(activeScopes.getLast())) {
            return false;
        }

        //Eliminate circles if not explicitly specified
        if (!multiple && path.contains(candidate.getType())) {
            return false;
        }
        return filter.apply(visitedKeywords);
    }

    public static Set<String> getKeywords(Collection<JavaType> types) {
        Set<String> result = new LinkedHashSet<String>();
        for (JavaType type : types) {
            Set<String> keywords = (Set<String>) type.getAttributes().get(KEYWORDS);
            result.addAll(keywords != null ? keywords : Collections.<String>emptySet());
        }
        return result;
    }


    public static LinkedList<String> getScopes(Collection<JavaType> types) {
        Stack<String> stack = new Stack<String>();
        for (JavaType type : types) {

            String scope = (String) type.getAttributes().get(BEGIN_SCOPE);
            if (scope != null && !scope.isEmpty()) {
                stack.push(scope);
            }

            scope = (String) type.getAttributes().get(END_SCOPE);
            if (scope != null && !scope.isEmpty()) {
                try {
                    String found = stack.pop();
                    if (!scope.equals(found)) {
                        throw new IllegalStateException("End of scope:" + scope + " but active scope was:" + found);
                    }
                } catch (EmptyStackException e) {
                    throw new IllegalStateException("Expected active scope:" + scope + " but not was active.", e);
                }
            }
        }
        return new LinkedList<String>(stack);
    }
}
