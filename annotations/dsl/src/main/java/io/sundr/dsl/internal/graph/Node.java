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

import io.sundr.codegen.utils.StringUtils;

import java.util.Set;

public class Node<T> {

    private final T item;
    private final Set<Node<T>> transitions;

    public Node(T item, Set<Node<T>> transitions) {
        this.item = item;
        this.transitions = transitions;
    }

    public T getItem() {
        return item;
    }

    public Set<Node<T>> getTransitions() {
        return transitions;
    }

    @Override
    public String toString() {
        return item + "[" +
                  StringUtils.join(transitions, ",") +
                "]";
    }
}
