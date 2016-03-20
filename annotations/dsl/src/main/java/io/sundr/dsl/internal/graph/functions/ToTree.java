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
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.graph.NodeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.dsl.internal.utils.JavaTypeUtils.isEndScope;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isTerminal;

public class ToTree implements Function<NodeContext, Node<JavaClazz>> {

    private final Function<NodeContext, Set<JavaClazz>> toNext;
    private final Function<NodeContext, Node<JavaClazz>> toTree;
    private final NodeRepository nodeRepository;

    public ToTree(Function<NodeContext, Set<JavaClazz>> toNext, NodeRepository nodeRepository) {
        this.toNext = toNext;
        this.nodeRepository = nodeRepository;
        this.toTree = this;
    }

    public ToTree(Function<NodeContext, Set<JavaClazz>> toNext, Function<NodeContext, Node<JavaClazz>> toTree, NodeRepository nodeRepository) {
        this.toNext = toNext;
        this.toTree = toTree;
        this.nodeRepository = nodeRepository;
    }

    public Node<JavaClazz> apply(NodeContext ctx) {
        Set<Node<JavaClazz>> nextVertices = new LinkedHashSet<Node<JavaClazz>>();

        //visited and path are the same only in the first iteration. see bellow:
        Set<JavaClazz> visited = new LinkedHashSet<JavaClazz>(ctx.getVisited());
        List<JavaClazz> nextCandidates = new ArrayList<JavaClazz>(toNext.apply(ctx));
        Collections.sort(nextCandidates, new CandidateComparator(ctx));

        for (JavaClazz next : nextCandidates) {
            NodeContext nextContext = ctx.contextOfChild(next)
                    .addToVisited(visited)
                    .addToVisited(next)
                    .build();

            Node<JavaClazz> subGraph = toTree.apply(nextContext);
            //Let's keep track of types used so far in the loop so that we avoid using the same types, in different branches of the tree:
            //This is required so that we avoid extending the same generic interface with different parameters.
            visited.add(subGraph.getItem());
            if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem()) || isEndScope(subGraph.getItem())) {
                nextVertices.add(subGraph);
            }
        }
        return nodeRepository.getOrCreateNode(ctx.getItem(), nextVertices);
    }

    private class CandidateComparator implements Comparator<JavaClazz> {

        private final NodeContext nodeContext;

        private CandidateComparator(NodeContext nodeContext) {
            this.nodeContext = nodeContext;
        }

        public int compare(JavaClazz left, JavaClazz right) {
            Set<JavaClazz> leftSet = toNext.apply(nodeContext.contextOfChild(left).addToVisited(left).build());
            Set<JavaClazz> rightSet = toNext.apply(nodeContext.contextOfChild(right).addToVisited(right).build());
            if (leftSet.contains(right) && rightSet.contains(left)) {
                return 0;
            } else if (leftSet.contains(right)) {
                return -1;
            } else if (rightSet.contains(left)) {
                return 1;
            }
            return 0;
        }
    }
}
