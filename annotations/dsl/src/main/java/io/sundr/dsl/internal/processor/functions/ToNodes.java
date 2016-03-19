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

package io.sundr.dsl.internal.processor.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.dsl.internal.processor.Node;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEntryPoint;

public class ToNodes implements Function<Set<JavaClazz>, Set<Node<JavaClazz>>> {

    private final Function<NodeContext, Node<JavaClazz>> toGraph;

    public ToNodes(Function<NodeContext, Node<JavaClazz>> toGraph) {
        this.toGraph = toGraph;
    }

    public Set<Node<JavaClazz>> apply(Set<JavaClazz> clazzes) {
        Set<Node<JavaClazz>> nodes = new LinkedHashSet<Node<JavaClazz>>();
        Set<JavaClazz> all = new LinkedHashSet(clazzes);
        for (JavaClazz clazz : clazzes) {
            if (isEntryPoint(clazz)) {
                nodes.add(toGraph.apply(NodeContext.builder()
                        .withItem(clazz)
                        .withAll(all)
                        .build()));
            }
        }
        return nodes;
    }
}
