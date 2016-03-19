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
import io.sundr.builder.Visitor;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.processor.Node;
import io.sundr.dsl.internal.utils.DslUtils;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.BEGIN_SCOPE;
import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.END_SCOPE;
import static io.sundr.dsl.internal.Constants.INTERMEDIATE_CLASSES;
import static io.sundr.dsl.internal.utils.JavaTypeUtils.isBeginScope;

public class ToScopes implements Function<Set<JavaClazz>, Set<JavaClazz>> {

    private final Function<NodeContext, Node<JavaClazz>> toGraph;

    public ToScopes(Function<NodeContext, Node<JavaClazz>> toGraph) {
        this.toGraph = toGraph;
    }

    public Set<JavaClazz> apply(Set<JavaClazz> clazzes) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>(clazzes);
        Set<JavaClazz> all = new LinkedHashSet(clazzes);
        for (JavaClazz clazz : clazzes) {
            if (isBeginScope(clazz)) {
                Node node = toGraph.apply(NodeContext.builder()
                        .withItem(clazz)
                        .withAll(all)
                        .build());

                Set<JavaClazz> scopeClasses = scopeClasses(node);
                JavaClazz scopeInterface = DslUtils.createTransitionInterface(node);
                JavaType scopeInterfaceType = scopeInterface.getType();

                scopeInterface = new JavaClazzBuilder()
                                        .withNewTypeLike(clazz.getType())
                                            .withPackageName(scopeInterfaceType.getPackageName())
                                            .withClassName(scopeInterfaceType.getClassName()+"Scope")
                                            .withInterfaces(scopeInterfaceType)
                                            .addToAttributes(CARDINALITY_MULTIPLE, true)
                                        .endType()
                                .addToAttributes(INTERMEDIATE_CLASSES, scopeClasses)
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
}
