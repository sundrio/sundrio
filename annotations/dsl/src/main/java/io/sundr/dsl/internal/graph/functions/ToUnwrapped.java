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
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.graph.NodeRepository;
import io.sundr.dsl.internal.utils.GraphUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEndScope;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;

/**
 * {@Function} that accepts a merged graph (all possible transitions) and replies tranisition rules so that it unwraps it.
 */
public class ToUnwrapped implements Function<NodeContext, Node<JavaClazz>> {

    private final NodeRepository nodeRepository;

    public ToUnwrapped(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Node<JavaClazz> apply(NodeContext ctx) {
        Node<JavaClazz> current = nodeRepository.get(ctx.getItem());
        Set<Node<JavaClazz>> next = new LinkedHashSet<Node<JavaClazz>>();

        for (Node<JavaClazz> candidate : current.getTransitions()) {
            List<JavaType> currentPath = ctx.getPathTypes();
            currentPath.add(ctx.getItem().getType());

            if (GraphUtils.isSatisfied(candidate.getItem(), currentPath)) {
                Node<JavaClazz> subGraph = apply(ctx.contextOfChild(candidate.getItem()).build());
                if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem()) || isEndScope(subGraph.getItem())) {
                    next.add(subGraph);
                }
            }
        }
        return nodeRepository.createNode(ctx.getItem(), next);
    }
}
