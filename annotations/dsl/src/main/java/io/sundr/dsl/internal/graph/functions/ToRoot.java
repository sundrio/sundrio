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
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.graph.Node;
import io.sundr.dsl.internal.processor.DslRepository;
import io.sundr.dsl.internal.type.functions.Generics;

import java.util.LinkedHashSet;
import java.util.Set;

public class ToRoot implements Function<Node<JavaClazz>, JavaClazz> {

    private final DslRepository repository;
    private final Function<Node<JavaClazz>, JavaClazz> nodeToTransition;

    public ToRoot(DslRepository repository, Function<Node<JavaClazz>, JavaClazz> nodeToTransition) {
        this.repository = repository;
        this.nodeToTransition = nodeToTransition;
    }

    public JavaClazz apply(Node<JavaClazz> item) {
        Set<JavaType> interfaces = new LinkedHashSet<JavaType>();

        for (Node<JavaClazz> child : item.getTransitions()) {
            JavaClazz transitionInterface = nodeToTransition.apply(child);
            interfaces.add(transitionInterface.getType());
            repository.register(child.getItem());
            repository.register(transitionInterface);
        }

        JavaType rootType = new JavaTypeBuilder(item.getItem().getType())
                .withInterfaces(interfaces)
                .withGenericTypes(new JavaType[]{})
                .build();

        return new JavaClazzBuilder(item.getItem())
                .withType(Generics.UNWRAP.apply(rootType))
                .withMethods(new LinkedHashSet<JavaMethod>())
                .build();
    }
}
