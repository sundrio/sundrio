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
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.processor.Node;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEndScope;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;

public class ToNode implements Function<NodeContext, Node<JavaClazz>> {

    private final Function<NodeContext, Set<JavaClazz>> toNext;
    private final Function<NodeContext, Node<JavaClazz>> toGraph;

    public ToNode(Function<NodeContext, Set<JavaClazz>> toNext) {
        this.toNext = toNext;
        this.toGraph = this;
    }

    public ToNode(Function<NodeContext, Set<JavaClazz>> toNext, Function<NodeContext, Node<JavaClazz>> toGraph) {
        this.toNext = toNext;
        this.toGraph = toGraph;
    }

    public Node<JavaClazz> apply(NodeContext ctx) {
        Set<Node<JavaClazz>> nextVertices = new LinkedHashSet<Node<JavaClazz>>();
        //visited and path are the same only in the first iteration. see bellow:
        Set<JavaClazz> visited = new LinkedHashSet<JavaClazz>(ctx.getVisited());
        for (JavaClazz next : toNext.apply(ctx)) {
            NodeContext nextContext = ctx.contextOfChild(next).addToVisited(visited).build();
            Node<JavaClazz> subGraph = toGraph.apply(nextContext);
            //Let's keep track of types used so far in the loop so that we avoid using the same types, in different branches of the tree:
            //This is required so that we avoid extending the same generic interface with different parameters.
            visited.add(subGraph.getItem());

            if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem()) || isEndScope(subGraph.getItem())) {
                nextVertices.add(subGraph);
            }
        }
        return new Node<JavaClazz>(ctx.getItem(), nextVertices);
    }
}
