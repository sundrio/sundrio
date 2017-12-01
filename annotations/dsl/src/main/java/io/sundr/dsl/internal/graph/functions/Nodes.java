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
import io.sundr.builder.TypedVisitor;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.processor.DslContextManager;
import io.sundr.dsl.internal.type.functions.Combine;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.type.functions.Generify;
import io.sundr.dsl.internal.utils.GraphUtils;
import io.sundr.dsl.internal.utils.TypeDefUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.BEGIN_SCOPE;
import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.END_SCOPE;
import static io.sundr.dsl.internal.Constants.IS_GENERATED;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.SCOPE_SUFFIX;
import static io.sundr.dsl.internal.utils.GraphUtils.exclusion;
import static io.sundr.dsl.internal.utils.GraphUtils.isSatisfied;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isBeginScope;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isEndScope;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isEntryPoint;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isTerminal;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isTransition;

public class Nodes {



    /**
     * {@link Function} that accepts a set of {@link TypeDef} and creates a the transition graph.
     * The graph is structured as a set of Trees (tree-like to be more accurate as there might be circles).
     */
    public static final Function<Set<TypeDef>, Set<Node<TypeDef>>> TO_GRAPH = new Function<Set<TypeDef>, Set<Node<TypeDef>>>() {
        public Set<Node<TypeDef>> apply(Set<TypeDef> clazzes) {
            Set<Node<TypeDef>> nodes = new LinkedHashSet<Node<TypeDef>>();
            Set<TypeDef> all = new LinkedHashSet(clazzes);
            for (TypeDef clazz : clazzes) {
                if (isEntryPoint(clazz)) {
                    nodes.add(TO_TREE.apply(NodeContext.builder()
                            .withItem(clazz)
                            .withAll(all)
                            .build()));
                }
            }
            return nodes;
        }
    };

    public static final Function<NodeContext, Node<TypeDef>> TO_TREE = new Function<NodeContext, Node<TypeDef>>() {
        public Node<TypeDef> apply(NodeContext context) {
            Set<Node<TypeDef>> nextVertices = new LinkedHashSet<Node<TypeDef>>();

            //visited and path are the same only in the first iteration. see bellow:
            Set<TypeDef> visited = new LinkedHashSet<TypeDef>(context.getVisited());
            List<TypeDef> nextCandidates = new ArrayList<TypeDef>(TO_NEXT.apply(context));
            Collections.sort(nextCandidates, new CandidateComparator(context));

            for (TypeDef next : nextCandidates) {
                NodeContext nextContext = context.contextOfChild(next)
                        .addToVisited(visited)
                        .addToVisited(next)
                        .build();

                Node<TypeDef> subGraph = TO_TREE.apply(nextContext);
                //Let's keep track of types used so far in the loop so that we avoid using the same types, in different branches of the tree:
                //This is required so that we avoid extending the same generic interface with different parameters.
                visited.add(subGraph.getItem());
                if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem()) || isEndScope(subGraph.getItem())) {
                    nextVertices.add(subGraph);
                }
            }
            return DslContextManager.getContext().getNodeRepository().getOrCreateNode(context.getItem(), nextVertices);
        }
    };


    /**
     * {@link Function} that accepts a {@link NodeContext} and finds which classes can follow next.
     */
    public static final Function<NodeContext, Set<TypeDef>> TO_NEXT = new Function<NodeContext, Set<TypeDef>>() {
        public Set<TypeDef> apply(NodeContext context) {
            Set<TypeDef> result = new LinkedHashSet<TypeDef>();
            Boolean inScope = !context.getActiveScopes().isEmpty();

            if (inScope && isEndScope(context.getItem()) || isTerminal(context.getItem())) {
                return result;
            }

            List<TypeDef> currentPath = context.getPathTypes();
            currentPath.add(context.getItem());
            for (TypeDef candidate : exclusion(context.getAll(), context.getVisitedTypes())) {
                if (!isEntryPoint(candidate) && isSatisfied(candidate, currentPath)) {
                    result.add(candidate);
                }
            }
            result.remove(context.getItem());
            return result;
        }
    };


    public static final Function<Node<TypeDef>, TypeDef> TO_ROOT = new Function<Node<TypeDef>, TypeDef>() {
        public TypeDef apply(Node<TypeDef> item) {
            List<ClassRef> interfaces = new ArrayList<ClassRef>();

            for (Node<TypeDef> child : item.getTransitions()) {
                ClassRef transitionInterface = TO_TRANSITION.apply(child);
                interfaces.add(transitionInterface);
                CodegenContext.getContext().getDefinitionRepository().register(child.getItem(), IS_GENERATED);
            }

            TypeDef rootType = new TypeDefBuilder(item.getItem())
                    .withExtendsList(interfaces)
                    .withParameters()
                    .withMethods()
                    .build();

            return new TypeDefBuilder(Generics.UNWRAP.apply(rootType))
                    .withMethods(new ArrayList<Method>())
                    .build();
        }
    };


    public static final Function<Node<TypeDef>, ClassRef> TO_TRANSITION = new Function<Node<TypeDef>, ClassRef>() {
        public ClassRef apply(Node<TypeDef> current) {
            if (current.getTransitions().isEmpty()) {
                return current.getItem().toInternalReference();
            } else {
                TypeDef clazz = current.getItem();
                Set<ClassRef> toCombine = new LinkedHashSet<ClassRef>();

                for (Node<TypeDef> v : current.getTransitions()) {
                    toCombine.add(apply(v));
                }

                ClassRef nextClazz = toCombine.size() == 1
                        ? toCombine.iterator().next()
                        : Combine.TYPEREFS.apply(Generify.CLASSREFS.apply(toCombine)).toInternalReference();

                if (TypeDefUtils.isCardinalityMultiple(clazz)) {
                    //1st pass create the self ref
                    final ClassRef selfRef = transition(clazz, nextClazz);
                    Set<ClassRef> toReCombine = new LinkedHashSet<ClassRef>(toCombine);
                    toReCombine.add(selfRef);
                    TypeDef reCombinedType = Combine.TYPEREFS.apply(toReCombine);
                    final ClassRef reCombinedRef = reCombinedType.toInternalReference();

                    reCombinedType = new TypeDefBuilder(reCombinedType).accept(new TypedVisitor<TypeDefBuilder>() {
                        public void visit(TypeDefBuilder builder) {
                            List<ClassRef> updatedInterfaces = new ArrayList<ClassRef>();
                            for (ClassRef interfaceRef : builder.getExtendsList()) {
                                if (interfaceRef.equals(selfRef)) {
                                    updatedInterfaces.add(selfRef.getDefinition().toReference(reCombinedRef));
                                } else {
                                    updatedInterfaces.add(interfaceRef);
                                }
                            }
                            builder.withExtendsList(updatedInterfaces);
                        }
                    }).build();

                    ClassRef reCombined = reCombinedType.toInternalReference();
                    //2nd pass recreate the combination
                    ClassRef updatedSelfRef = transition(clazz, reCombined);
                    toReCombine = new LinkedHashSet<ClassRef>(toCombine);
                    toReCombine.add(updatedSelfRef);
                    reCombined = Combine.TYPEREFS.apply(toReCombine).toInternalReference();
                    DslContextManager.getContext().getDefinitionRepository().register(reCombinedType, IS_GENERATED);
                    DslContextManager.getContext().getDefinitionRepository().register(nextClazz.getDefinition(), IS_GENERATED);
                    return transition(clazz, reCombined);
                } else {
                    //If we have a couple of classes to combine that are non-multiple
                    // we may end up with intermediate garbage in the registry, which are masking the real thing
                    if (!isTransition(nextClazz)
                            //&&
                            //DslContextManager.getContext().getDefinitionRepository().getDefinition(nextClazz.getDefinition().getFullyQualifiedName()) == null
                            ) {
                        DslContextManager.getContext().getDefinitionRepository().register(nextClazz.getDefinition(), IS_GENERATED);
                    }
                    return transition(clazz, nextClazz);
                }
            }
        }

        public ClassRef transition(TypeDef from, ClassRef to) {
            return from.toReference(to);

        }
    };

    public static final Function<Node<TypeDef>, Node<TypeDef>> TO_UNCYCLIC = new Function<Node<TypeDef>, Node<TypeDef>>() {
        public Node<TypeDef> apply(Node<TypeDef> node) {
            visit(node, new LinkedHashSet<Node<TypeDef>>());
            return node;
        }

        private boolean visit(Node<TypeDef> node, Set<Node<TypeDef>> visited) {
            if (visited.add(node)) {
                for (Node<TypeDef> child : new LinkedHashSet<Node<TypeDef>>(node.getTransitions())) {
                    Set<Node<TypeDef>> branchNodes = new LinkedHashSet<Node<TypeDef>>(visited);
                    if (!visit(child, branchNodes)) {
                        node.getTransitions().remove(child);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final Function<NodeContext, Node<TypeDef>> TO_UNWRAPPED = new Function<NodeContext, Node<TypeDef>>() {
        public Node<TypeDef> apply(NodeContext ctx) {
            Node<TypeDef> current = DslContextManager.getContext().getNodeRepository().get(ctx.getItem());
            Set<Node<TypeDef>> next = new LinkedHashSet<Node<TypeDef>>();

            for (Node<TypeDef> candidate : current.getTransitions()) {
                List<TypeDef> currentPath = ctx.getPathTypes();
                currentPath.add(ctx.getItem());

                if (GraphUtils.isSatisfied(candidate.getItem(), currentPath)) {
                    Node<TypeDef> subGraph = apply(ctx.contextOfChild(candidate.getItem()).build());
                    if (subGraph.getTransitions().size() > 0 || isTerminal(subGraph.getItem()) || isEndScope(subGraph.getItem())) {
                        next.add(subGraph);
                    }
                }
            }
            return DslContextManager.getContext().getNodeRepository().createNode(ctx.getItem(), next);
        }
    };


    public static Function<Set<TypeDef>, Set<TypeDef>> TO_SCOPE = new Function<Set<TypeDef>, Set<TypeDef>>() {
        public Set<TypeDef> apply(Set<TypeDef> clazzes) {
            Set<TypeDef> result = new LinkedHashSet<TypeDef>(clazzes);
            Set<TypeDef> all = new LinkedHashSet(clazzes);
            for (TypeDef clazz : clazzes) {
                if (isBeginScope(clazz)) {

                    Boolean multiple = TypeDefUtils.isCardinalityMultiple(clazz);
                    TypeDef current;

                    if (multiple) {
                        current = new TypeDefBuilder(clazz)
                                .addToAttributes(CARDINALITY_MULTIPLE, false)
                                .build();

                        all.remove(clazz);
                        all.add(current);
                    } else {
                        current = clazz;
                    }

                    Node node = TO_TREE.apply(NodeContext.builder()
                            .withItem(current)
                            .withAll(all)
                            .build());

                    Set<TypeDef> scopeClasses = scopeClasses(node);
                    for (TypeDef scopeClass : scopeClasses) {
                        DslContextManager.getContext().getDefinitionRepository().register(scopeClass, IS_GENERATED);
                    }
                    ClassRef scopeInterface = TO_TRANSITION.apply(node);

                    result.removeAll(scopeClasses);
                    result.add(new TypeDefBuilder(clazz)
                            .withKind(Kind.INTERFACE)
                            .withPackageName(scopeInterface.getDefinition().getPackageName())
                            .withName(scopeInterface.getDefinition().getName() + SCOPE_SUFFIX)
                            .withExtendsList(scopeInterface)
                            .withMethods()
                            .addToAttributes(CARDINALITY_MULTIPLE, multiple)
                            .addToAttributes(KEYWORDS, scopeKeywords(scopeClasses))
                            .accept(new TypedVisitor<TypeDefBuilder>() {
                                public void visit(TypeDefBuilder builder) {
                                    builder.getAttributes().remove(BEGIN_SCOPE);
                                    builder.getAttributes().remove(END_SCOPE);
                                }
                            })
                            .build());
                }
            }
            return result;
        }


        public Set<TypeDef> scopeClasses(Node<TypeDef> node) {
            Set<TypeDef> result = new LinkedHashSet<TypeDef>();
            result.add(node.getItem());
            for (Node<TypeDef> transition : node.getTransitions()) {
                result.addAll(scopeClasses(transition));
            }

            return result;
        }

        public Set<String> scopeKeywords(Collection<TypeDef> clazzes) {
            Set<String> result = new LinkedHashSet<String>();
            for (TypeDef clazz : clazzes) {
                Set<String> keywords = (Set<String>) clazz.getAttributes().get(KEYWORDS);
                result.addAll(keywords != null ? keywords : Collections.<String>emptySet());
            }
            return result;
        }
    };

    private static class CandidateComparator implements Comparator<TypeDef> {

        private final NodeContext nodeContext;

        private CandidateComparator(NodeContext nodeContext) {
            this.nodeContext = nodeContext;
        }

        public int compare(TypeDef left, TypeDef right) {
            Set<TypeDef> leftSet = TO_NEXT.apply(nodeContext.contextOfChild(left).addToVisited(left).build());
            Set<TypeDef> rightSet = TO_NEXT.apply(nodeContext.contextOfChild(right).addToVisited(right).build());
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

