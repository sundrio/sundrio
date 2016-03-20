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

package io.sundr.dsl.internal.graph.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.dsl.internal.graph.Node;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Breaks loop and converts the graph into a tree.
 */
public class ToUncyclic implements Function<Node<JavaClazz>, Node<JavaClazz>> {

    public Node<JavaClazz> apply(Node<JavaClazz> node) {
        visit(node, new LinkedHashSet<Node<JavaClazz>>());
        return node;
    }

    private static boolean visit(Node<JavaClazz> node, Set<Node<JavaClazz>> visited) {
        if (visited.add(node)) {
            for (Node<JavaClazz> child : new LinkedHashSet<Node<JavaClazz>>(node.getTransitions())) {
                Set<Node<JavaClazz>> branchNodes = new LinkedHashSet<Node<JavaClazz>>(visited);
                if (!visit(child, branchNodes)) {
                    node.getTransitions().remove(child);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
