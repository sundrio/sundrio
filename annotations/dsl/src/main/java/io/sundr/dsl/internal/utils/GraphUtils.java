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
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isBeginScope;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isCardinalityMultiple;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEndScope;
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
        Set<JavaClazz> all = new LinkedHashSet(clazzes);
        for (JavaClazz clazz : clazzes) {
            if (isEntryPoint(clazz)) {
                nodes.add(createGraph(clazz, all, new ArrayList<JavaType>(), new ArrayList<JavaType>()));
            }
        }
        return nodes;
    }

    public static Node<JavaClazz> createGraph(JavaClazz root, Set<JavaClazz> all, List<JavaType> path, List<JavaType> visited) {
        Set<JavaClazz> next = new LinkedHashSet<JavaClazz>();
        List<JavaType> currentPath = new ArrayList<JavaType>(path);

        if (isTerminal(root)) {
            //do nothing
        } else if
             //(false) {
                (isBeginScope(root) || !getScopes(path).isEmpty()) {
            Set<JavaClazz> scopeNext = new LinkedHashSet<JavaClazz>();
            List<JavaType> scopeVisited = new ArrayList<JavaType>();
            List<JavaType> scopePath = new ArrayList<JavaType>();

            if (isBeginScope(root)) {
                scopePath.add(root.getType());
                scopeVisited.add(root.getType());
            } else {
                scopeVisited.addAll(currentPath);
                scopeVisited.add(root.getType());
                scopePath.addAll(currentPath);
                scopePath.add(root.getType());
            }

            if (!isEndScope(root)) {
                for (JavaClazz candidate : exclusion(all, scopeVisited)) {
                    if (!isEntryPoint(candidate) && isSatisfied(candidate, scopeVisited)) {
                        scopeNext.add(candidate);
                    }
                }
            }

            if (!scopeNext.isEmpty()) {
                Node<JavaClazz> node = createNode(root, scopeNext, all, scopePath);

                if (isBeginScope(root)) {
                    //Substitute root, with the scope node.
                    //We also need to remove all scope types from the all pool.
                    all.remove(root);
                    for (Node scopeTransition : node.getTransitions()) {
                        all.remove(scopeTransition.getItem());
                    }
                    return createNode(DslUtils.createRootInterface(node), new LinkedHashSet<JavaClazz>(), new LinkedHashSet<JavaClazz>(), new ArrayList<JavaType>());
                } else {
                    return node;
                }
            }
        }  else {
            currentPath.add(root.getType());
            for (JavaClazz candidate : exclusion(all, visited)) {
                if (!isEntryPoint(candidate) && isSatisfied(candidate, currentPath)) {
                    next.add(candidate);
                }
            }
            next.remove(root);
        }

        return createNode(root, next, all, currentPath);
    }


    /**
     * Creates a {@link Node} that defines DSL transitions from the current interface to the next.
     * @param current   The specified current {@link JavaClazz}.
     * @param next      The list of {@link JavaClazz} that are allowed as direct transitions.
     * @param all       All available {@link JavaClazz} instances.
     * @param path      The path from the graph root to the current {@link JavaClazz}.
     * @return
     */
    private static Node<JavaClazz> createNode(JavaClazz current, Set<JavaClazz> next, Set<JavaClazz> all, List<JavaType> path) {
        Set<Node<JavaClazz>> nextVertices = new LinkedHashSet<Node<JavaClazz>>();
        //visited and path are the same only in the first iterration. see bellow:
        List<JavaType> visited = new ArrayList<JavaType>();
        visited.addAll(path);

        for (JavaClazz c : next) {
            Node<JavaClazz> subGraph = createGraph(c, all, path, visited);
            //Let's keep track of types used so far in the loop so that we avoid using the same types, in different branches of the tree:
            //This is required so that we avoid extending the same generic interface with different parameters.
            visited.add(subGraph.getItem().getType());
            visited.addAll(subGraph.getItem().getType().getInterfaces());
            if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem()) || isEndScope(subGraph.getItem())) {
                nextVertices.add(subGraph);
            }
        }
        return new Node<JavaClazz>(current, nextVertices);
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
