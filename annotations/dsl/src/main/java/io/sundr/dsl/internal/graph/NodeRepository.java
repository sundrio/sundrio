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

package io.sundr.dsl.internal.graph;

import io.sundr.codegen.model.JavaClazz;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NodeRepository {

    private final Map<String, Node<JavaClazz>> map = new HashMap<String, Node<JavaClazz>>();

    public Node<JavaClazz> createNode(JavaClazz item, Set<Node<JavaClazz>> transitions) {
        return new Node<JavaClazz>(item, transitions);
    }

    public Node<JavaClazz> get(JavaClazz item) {
        String key = getKey(item);
        return map.get(key);
    }

    public Node<JavaClazz> getOrCreateNode(JavaClazz item, Set<Node<JavaClazz>> transitions) {
        Node<JavaClazz> node;
        String key = getKey(item);
        if (map.containsKey(key)) {
            node = map.get(key);
            node.getTransitions().addAll(transitions);
            return node;
        } else {
            node = new Node<JavaClazz>(item, transitions);
            map.put(key, node);
        }
        return node;
    }

    public void clear() {
        map.clear();
    }

    private static String getKey(JavaClazz clazz) {
        return clazz.getType().getFullyQualifiedName();
    }
}
