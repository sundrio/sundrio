/*
 * Copyright 2015 The original authors.
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

package io.sundr.dsl.internal.utils;

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.processor.Node;
import io.sundr.dsl.internal.type.functions.Combine;
import io.sundr.dsl.internal.type.functions.Generics;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class DslUtils {

    private DslUtils() {
        //Utility Class
    }

    public static JavaClazz createRootInterface(Node<JavaClazz> current, Set<JavaClazz> intermediate) {
        JavaClazz rootInterface = null;
        Set<JavaType> interfaces = new LinkedHashSet<JavaType>();
        for (Node<JavaClazz> child : current.getTransitions()) {
            JavaClazz transitionInterface = createTransitionInterface(child, intermediate);
            interfaces.add(transitionInterface.getType());
            intermediate.add(transitionInterface);
            intermediate.add(child.getItem());
        }
        JavaType rootType = new JavaTypeBuilder(current.getItem().getType())
                .withInterfaces(interfaces)
                .withGenericTypes(new JavaType[]{})
                .build();
        return new JavaClazzBuilder(current.getItem())
                .withType(Generics.UNWRAP.apply(rootType))
                .withMethods(new LinkedHashSet<JavaMethod>())
                .build();
    }
    
    public static JavaClazz createTransitionInterface(Node<JavaClazz> current, Set<JavaClazz> intermediate) {
        if (current.getTransitions().isEmpty()) {
            return current.getItem();
        } else if (current.getTransitions().size() == 1) {
            Node next = current.getTransitions().iterator().next();
            JavaClazz clazz = current.getItem();
            JavaClazz nextClazz = createTransitionInterface(next, intermediate);
            JavaClazz transition = transition(clazz, nextClazz);
            intermediate.add(nextClazz);
            return transition;
        } else {
            JavaClazz clazz = current.getItem();
            Set<JavaClazz> toCombine = new LinkedHashSet<JavaClazz>();

            for (Node<JavaClazz> v : current.getTransitions()) {
                toCombine.add(createTransitionInterface(v, intermediate));
            }
            JavaClazz combined = Combine.FUNCTION.apply(toCombine);
            intermediate.addAll(toCombine);
            
            if (JavaTypeUtils.isCardinalityMultiple(clazz)) {
                JavaClazz selfRef = transition(clazz, combined);
                return transition(clazz, Combine.FUNCTION.apply(Arrays.asList(combined, selfRef)));
            } else {
                intermediate.add(combined);
                return transition(clazz, combined);
            }
        }
    }

    public static JavaClazz transition(JavaClazz from, JavaClazz to) {
        JavaType transitionInterface = new JavaTypeBuilder(from.getType())
                .withGenericTypes(new JavaType[]{to.getType()}).build();

        return new JavaClazzBuilder(from)
                .withType(transitionInterface)
                .build();

    }
}
