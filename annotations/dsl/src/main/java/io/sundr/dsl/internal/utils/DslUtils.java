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
import io.sundr.dsl.internal.type.functions.Generify;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.INTERMEDIATE_CLASSES;

public final class DslUtils {

    private DslUtils() {
        //Utility Class
    }

    public static JavaClazz createRootInterface(Node<JavaClazz> current) {
        Set<JavaType> interfaces = new LinkedHashSet<JavaType>();
        Set<JavaClazz> intermediate = new LinkedHashSet<JavaClazz>();

        for (Node<JavaClazz> child : current.getTransitions()) {
            JavaClazz transitionInterface = createTransitionInterface(child);
            interfaces.add(transitionInterface.getType());
            intermediate.add(child.getItem());
            intermediate.add(transitionInterface);
        }
        JavaType rootType = new JavaTypeBuilder(current.getItem().getType())
                .withInterfaces(interfaces)
                .withGenericTypes(new JavaType[]{})
                .build();

        return new JavaClazzBuilder(current.getItem())
                .withType(Generics.UNWRAP.apply(rootType))
                .withMethods(new LinkedHashSet<JavaMethod>())
                .addToAttributes(INTERMEDIATE_CLASSES, intermediate)
                .build();
    }
    
    public static JavaClazz createTransitionInterface(Node<JavaClazz> current) {
        Set<JavaClazz> intermediate = new LinkedHashSet<JavaClazz>();

        if (current.getTransitions().isEmpty()) {
            return current.getItem();
        } else {
        //    Node next = current.getTransitions().iterator().next();
        //    JavaClazz clazz = current.getItem();
        //    JavaClazz nextClazz = createTransitionInterface(next);
        //    JavaClazz transition = transition(clazz, nextClazz);
        //    intermediate.add(nextClazz);

        //    return addIntermediate(transition, intermediate);

            JavaClazz clazz = current.getItem();
            Set<JavaClazz> toCombine = new LinkedHashSet<JavaClazz>();

            for (Node<JavaClazz> v : current.getTransitions()) {
                toCombine.add(createTransitionInterface(v));
            }

            JavaClazz nextClazz = toCombine.size() == 1
                    ? toCombine.iterator().next()
                    : Combine.FUNCTION.apply(Generify.FUNCTION.apply(toCombine));

            intermediate.addAll(toCombine);
            if (JavaTypeUtils.isCardinalityMultiple(clazz)) {
                //1st pass create the self ref
                JavaClazz selfRef = transition(clazz, nextClazz);
                Set<JavaClazz> toReCombine = new LinkedHashSet<JavaClazz>(toCombine);
                toReCombine.add(selfRef);
                JavaClazz reCombined = Combine.FUNCTION.apply(toReCombine);

                //2nd pass recreate the combination
                selfRef =  transition(clazz, reCombined);
                toReCombine = new LinkedHashSet<JavaClazz>(toCombine);
                toReCombine.add(selfRef);
                reCombined = Combine.FUNCTION.apply(toReCombine);
                intermediate.add(reCombined);
                intermediate.add(nextClazz);
                return transition(clazz, reCombined);
            } else {
                intermediate.add(nextClazz);
                return addIntermediate(transition(clazz, nextClazz), intermediate);
            }
        }
    }

    public static JavaClazz transition(JavaClazz from, JavaClazz to) {
        JavaType transitionInterface = new JavaTypeBuilder(from.getType())
                .withGenericTypes(to.getType()).build();

        return new JavaClazzBuilder(from)
                .withType(transitionInterface)
                .build();

    }

    public static boolean isExtendedBy(JavaType candidate, JavaType target) {
        for (JavaType type : target.getInterfaces()) {
            if (type.getFullyQualifiedName().equals(candidate.getFullyQualifiedName())) {
                return true;
            }
        }
        return false;
    }

    public static JavaClazz addIntermediate(JavaClazz current, Set<JavaClazz> intermediate) {
        Set<JavaClazz> newIntermediate = new HashSet<JavaClazz>(intermediate);
        if (current.getAttributes().containsKey(INTERMEDIATE_CLASSES)) {
            newIntermediate.addAll((Set<JavaClazz>) current.getAttributes().get(INTERMEDIATE_CLASSES));
        }
        return new JavaClazzBuilder(current).addToAttributes(INTERMEDIATE_CLASSES, newIntermediate).build();

    }
}
