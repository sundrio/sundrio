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

package io.sundr.dsl.internal.processor;

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.internal.functions.Combination;
import io.sundr.dsl.internal.functions.Generics;
import io.sundr.dsl.internal.functions.Transition;

import javax.lang.model.element.ExecutableElement;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static io.sundr.dsl.internal.processor.JavaTypeUtils.executableToInterface;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.merge;

public final class DslUtils {

    private DslUtils() {
        //Utility Class
    }

    static Set<JavaClazz> createGenericInterfaces(DslProcessorContext context, Collection<ExecutableElement> elements) {
        Map<String, JavaClazz> byName = new LinkedHashMap<>();
        for (ExecutableElement current : elements) {
            JavaClazz clazz = executableToInterface(context, current);
            String name = clazz.getType().getFullyQualifiedName();
            if (byName.containsKey(name)) {
                JavaClazz other = byName.remove(name);
                byName.put(name, merge(other, clazz));
            } else {
                byName.put(name, clazz);
            }
        }
        return new LinkedHashSet<>(byName.values());
    }

    static JavaClazz createRootInterface(Vertx<JavaClazz> current, Set<JavaClazz> intermediate) {
        JavaClazz rootInterface = null;
        Set<JavaType> interfaces = new LinkedHashSet<>();
        for (Vertx<JavaClazz> child : current.getTransitions()) {
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
    
    static JavaClazz createTransitionInterface(Vertx<JavaClazz> current, Set<JavaClazz> intermediate) {
        if (current.getTransitions().isEmpty()) {
            return current.getItem();
        } else if (current.getTransitions().size() == 1) {
            Vertx next = current.getTransitions().iterator().next();
            JavaClazz clazz = current.getItem();
            JavaClazz nextClazz = createTransitionInterface(next, intermediate);
            JavaClazz transition = Transition.create(clazz, nextClazz);
            //intermediate.add(transition);
            intermediate.add(nextClazz);
            return transition;
        } else {
            JavaClazz clazz = current.getItem();
            Set<JavaClazz> toCombine = new LinkedHashSet<>();

            for (Vertx<JavaClazz> v : current.getTransitions()) {
                toCombine.add(createTransitionInterface(v, intermediate));
            }
            JavaClazz combined = Combination.create(toCombine);
            intermediate.addAll(toCombine);
            intermediate.add(combined);
            return Transition.create(clazz, combined);
        }
    }
    
}
