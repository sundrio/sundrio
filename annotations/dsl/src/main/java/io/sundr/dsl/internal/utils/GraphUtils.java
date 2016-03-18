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
import io.sundr.dsl.internal.element.functions.filter.AndTransitionFilter;
import io.sundr.dsl.internal.element.functions.filter.RequiresAllFilter;
import io.sundr.dsl.internal.element.functions.filter.RequiresAnyFilter;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;
import io.sundr.dsl.internal.processor.Node;

import java.util.ArrayList;
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
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEntryPoint;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;

public final class GraphUtils {

    private GraphUtils() {
        //Utility Class
    }

    /**
     * Creates a transition graph with the specified classes.
     * @param clazzes   The classes
     * @return          A graph with all the required nodes.
     */
    public static Set<Node<JavaClazz>> createGraph(Set<JavaClazz> clazzes) {
        Set<Node<JavaClazz>> nodes = new LinkedHashSet<Node<JavaClazz>>();
        for (JavaClazz clazz : clazzes) {
            if (isEntryPoint(clazz)) {
                nodes.add(createGraph(clazz, clazzes, new ArrayList<JavaType>(), new ArrayList<JavaType>()));
            }
        }
        return nodes;
    }

    public static Node<JavaClazz> createGraph(JavaClazz root, Set<JavaClazz> all, List<JavaType> path, List<JavaType> visited) {
        Set<JavaClazz> next = new LinkedHashSet<JavaClazz>();
        List<JavaType> currentPath = new ArrayList<JavaType>(path);

        if (!isTerminal(root)) {
            currentPath.add(root.getType());
            for (JavaClazz candidate : exclusion(all, visited)) {
                if (!isEntryPoint(candidate) && isSatisfied(candidate, currentPath)) {
                    next.add(candidate);
                }
            }
            next.remove(root);
        }

        Set<Node<JavaClazz>> nextVertices = new LinkedHashSet<Node<JavaClazz>>();
        List<JavaType> levelInterfaces = new ArrayList<JavaType>();
        levelInterfaces.addAll(visited);

        for (JavaClazz c : next) {
            Node<JavaClazz> subGraph = createGraph(c, all, currentPath, levelInterfaces);
            levelInterfaces.add(subGraph.getItem().getType());
            levelInterfaces.addAll(subGraph.getItem().getType().getInterfaces());
            if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem())) {
                nextVertices.add(subGraph);
            }
        }
        return new Node<JavaClazz>(root, nextVertices);
    }

    private static Set<JavaClazz> exclusion(Set<JavaClazz> one, Collection<JavaType> excluded) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>();
        for (JavaClazz item : one) {
            if (!excluded.contains(item.getType()) || isTerminal(item) || isCardinalityMultiple(item)) {
                result.add(item);
            }
        }
        return result;
    }

    private static boolean isSatisfied(JavaClazz candidate, List<JavaType> visited) {
        Set<String> keywordsAndScopes = new LinkedHashSet<String>();
        Set<String> visitedKeywords = getKeywords(visited);
        Deque<String> activeScopes = getScopes(visited);
        keywordsAndScopes.addAll(visitedKeywords);
        keywordsAndScopes.addAll(activeScopes);

        TransitionFilter filter = (TransitionFilter) candidate.getType().getAttributes().get(FILTER);
        Boolean multiple = (Boolean) candidate.getType().getAttributes().get(CARDINALITY_MULTIPLE);

        Set<String> keywords = (Set<String>) candidate.getType().getAttributes().get(KEYWORDS);
        if (!activeScopes.isEmpty() && !keywords.contains(activeScopes.getLast())) {
            return false;
        }

        //Eliminate circles if not explicitly specified
        if (!multiple && visited.contains(candidate.getType())) {
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
