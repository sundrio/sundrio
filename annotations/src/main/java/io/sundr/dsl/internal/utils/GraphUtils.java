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

import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.TRANSITIONS;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEntryPoint;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isCardinalityMultiple;

public final class GraphUtils {

    private GraphUtils() {
        //Utility Class
    }

    public static Set<Node<JavaClazz>> createGraph(Set<JavaClazz> clazzes) {
        Set<Node<JavaClazz>> nodes = new LinkedHashSet<>();
        for (JavaClazz clazz : clazzes) {
            if (isEntryPoint(clazz)) {
                nodes.add(createGraph(clazz, new LinkedHashSet<String>(), clazzes, new LinkedHashSet<JavaType>()));

            }
        }
        return nodes;
    }

    public static Node<JavaClazz> createGraph(JavaClazz root, Set<String> previous, Set<JavaClazz> all, Set<JavaType> visited) {
        Boolean usePrevious = JavaTypeUtils.usePreviousTransitions(root);
        Set<JavaClazz> next = new LinkedHashSet<>();
        Set<JavaType> visitedPath = new LinkedHashSet<>(visited);
        Set<String> transitions = usePrevious ? previous : (Set<String>) root.getType().getAttributes().get(TRANSITIONS);

        if (!JavaTypeUtils.isTerminal(root)) {
            for (JavaClazz candidate : exclusion(all, visited)) {
                if (!JavaTypeUtils.isEntryPoint(candidate)) {
                    Set<String> keywords = (Set<String>) candidate.getType().getAttributes().get(KEYWORDS);
                    for (String keyword : keywords) {
                        if (transitions.contains(keyword)) {
                            next.add(candidate);
                        }
                    }
                }
            }
            next.remove(root);
            visitedPath.add(root.getType());
        }

        Set<Node<JavaClazz>> nextVertices = new LinkedHashSet<>();
        Set<JavaType> levelInterfaces = new LinkedHashSet<>();
        levelInterfaces.addAll(visitedPath);

        for (JavaClazz c : next) {
            Node<JavaClazz> subGraph = createGraph(c, usePrevious ? previous : transitions, all, levelInterfaces);
            levelInterfaces.add(subGraph.getItem().getType());
            levelInterfaces.addAll(subGraph.getItem().getType().getInterfaces());
            nextVertices.add(subGraph);
        }
        return new Node<>(root, nextVertices);
    }

    private static Set<JavaClazz> exclusion(Set<JavaClazz> one, Set<JavaType> excluded) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        for (JavaClazz item : one) {
            if (!excluded.contains(item.getType()) || isTerminal(item) || isCardinalityMultiple(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
