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
import io.sundr.dsl.internal.processor.Node;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.EXCLUSIVE;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.REQUIRES_ALL;
import static io.sundr.dsl.internal.Constants.REQUIRES_ANY;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEntryPoint;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isCardinalityMultiple;

public final class GraphUtils {

    private GraphUtils() {
        //Utility Class
    }

    public static Set<Node<JavaClazz>> createGraph(Set<JavaClazz> clazzes) {
        Set<Node<JavaClazz>> nodes = new LinkedHashSet<Node<JavaClazz>>();
        for (JavaClazz clazz : clazzes) {
            if (isEntryPoint(clazz)) {
                nodes.add(createGraph(clazz, clazzes, new LinkedHashSet<JavaType>(), new LinkedHashSet<JavaType>()));

            }
        }
        return nodes;
    }

    public static Node<JavaClazz> createGraph(JavaClazz root, Set<JavaClazz> all, Set<JavaType> path, Set<JavaType> visited) {
        Set<JavaClazz> next = new LinkedHashSet<JavaClazz>();
        Set<JavaType> currentPath = new LinkedHashSet<JavaType>(path);

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
        Set<JavaType> levelInterfaces = new LinkedHashSet<JavaType>();
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

    private static Set<JavaClazz> exclusion(Set<JavaClazz> one, Set<JavaType> excluded) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>();
        for (JavaClazz item : one) {
            if (!excluded.contains(item.getType()) || isTerminal(item) || isCardinalityMultiple(item)) {
                result.add(item);
            }
        }
        return result;
    }


    private static boolean isSatisfied(JavaClazz candidate, Set<JavaType> visited) {
        Set<String> visitedKeywords = getKeywords(visited);

        Boolean multiple = (Boolean) candidate.getType().getAttributes().get(CARDINALITY_MULTIPLE);
        Set<String> requiresAll = (Set<String>) candidate.getType().getAttributes().get(REQUIRES_ALL);
        Set<String> requiresAny = (Set<String>) candidate.getType().getAttributes().get(REQUIRES_ANY);
        Set<String> exclusive = (Set<String>) candidate.getType().getAttributes().get(EXCLUSIVE);

        //Eliminate circles if not supported
        if (!multiple && visited.contains(candidate.getType())) {
            return false;
        }

        //Check if path contains exclusive keywords
        for (String e : exclusive) {
            if (visitedKeywords.contains(e)) {
                return false;
            }
        }

        //Check if "All" requirements are meet
        for (String a : requiresAll) {
            if (!visitedKeywords.contains(a)) {
                return false;
            }
        }

        for (String a : requiresAny) {
            if (visitedKeywords.contains(a)) {
                return true;
            }
        }

        return requiresAny.isEmpty();
    }

    private static Set<String> getKeywords(Set<JavaType> types) {
        Set<String> result = new LinkedHashSet<String>();
        for (JavaType type : types) {
            Set<String> keywords = (Set<String>) type.getAttributes().get(KEYWORDS);
            result.addAll(keywords != null ? keywords : Collections.<String>emptySet());
        }
        return result;
    }
}
