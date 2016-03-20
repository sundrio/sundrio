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
import io.sundr.builder.Visitor;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.graph.NodeContext;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.processor.ClassRepository;
import io.sundr.dsl.internal.utils.JavaTypeUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.BEGIN_SCOPE;
import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.END_SCOPE;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.SCOPE_SUFFIX;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isBeginScope;

/**
 * {@link Function} that detects scopes (paths marked by begin/end annotations) and replaces them by a single interface.
 */
public class ToScope implements Function<Set<JavaClazz>, Set<JavaClazz>> {

    private final ClassRepository repository;
    private final Function<Node<JavaClazz>, JavaClazz> nodeToTransition;
    private final Function<NodeContext, Node<JavaClazz>> toGraph;

    public ToScope(ClassRepository repository, Function<Node<JavaClazz>, JavaClazz> nodeToTransition, Function<NodeContext, Node<JavaClazz>> toGraph) {
        this.repository = repository;
        this.nodeToTransition = nodeToTransition;
        this.toGraph = toGraph;
    }

    public Set<JavaClazz> apply(Set<JavaClazz> clazzes) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>(clazzes);
        Set<JavaClazz> all = new LinkedHashSet(clazzes);
        for (JavaClazz clazz : clazzes) {
            if (isBeginScope(clazz)) {

                Boolean multiple = JavaTypeUtils.isCardinalityMultiple(clazz);
                JavaClazz current;

                if (multiple) {
                    current = new JavaClazzBuilder(clazz)
                            .editType()
                                .addToAttributes(CARDINALITY_MULTIPLE, false)
                            .endType()
                    .build();
                    all.remove(clazz);
                    all.add(current);
                } else {
                    current = clazz;
                }

                Node node = toGraph.apply(NodeContext.builder()
                        .withItem(current)
                        .withAll(all)
                        .build());

                Set<JavaClazz> scopeClasses = scopeClasses(node);
                repository.register(scopeClasses);
                JavaClazz scopeInterface = nodeToTransition.apply(node);
                JavaType scopeInterfaceType = scopeInterface.getType();

                scopeInterface = new JavaClazzBuilder()
                                        .withNewTypeLike(clazz.getType())
                                            .withPackageName(scopeInterfaceType.getPackageName())
                                            .withClassName(scopeInterfaceType.getClassName()+SCOPE_SUFFIX)
                                            .withInterfaces(scopeInterfaceType)
                                            .addToAttributes(CARDINALITY_MULTIPLE, multiple)
                                            .addToAttributes(KEYWORDS, scopeKeywords(scopeClasses))
                                        .endType()
                                .accept(new Visitor<JavaTypeBuilder>() {
                                    public void visit(JavaTypeBuilder element) {
                                        element.getAttributes().remove(BEGIN_SCOPE);
                                        element.getAttributes().remove(END_SCOPE);
                                    }
                                })
                                .build();


                result.removeAll(scopeClasses);
                result.add(scopeInterface);
            }
        }
        return result;
    }

    public Set<JavaClazz> scopeClasses(Node<JavaClazz> node) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>();
        result.add(node.getItem());
        for (Node<JavaClazz> transition : node.getTransitions()) {
            result.addAll(scopeClasses(transition));
        }

        return result;
    }

    public Set<String> scopeKeywords(Collection<JavaClazz> clazzes) {
        Set<String> result = new LinkedHashSet<String>();
        for (JavaClazz clazz : clazzes) {
            Set<String> keywords = (Set<String>) clazz.getType().getAttributes().get(KEYWORDS);
            result.addAll(keywords != null ? keywords : Collections.<String>emptySet());
        }
        return result;
    }
}
