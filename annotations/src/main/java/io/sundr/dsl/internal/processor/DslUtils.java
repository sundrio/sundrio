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
import io.sundr.codegen.model.JavaKind;
import io.sundr.dsl.internal.functions.Combine;
import io.sundr.dsl.internal.functions.Generics;

import javax.lang.model.element.ExecutableElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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


    static Set<JavaClazz> createTransitionInterface(DslProcessorContext context, JavaClazz current, Set<JavaClazz> next) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        String interfaceName = current.getType().getClassName();
        Map<String, JavaClazz> nextInterfacesByName = new LinkedHashMap<>();
        Set<JavaClazz> nextInterfaces = new LinkedHashSet<>();

        for (JavaClazz clazz : next) {
            String name = clazz.getType().getFullyQualifiedName();
            if (nextInterfacesByName.containsKey(name)) {
                JavaClazz other = nextInterfacesByName.remove(name);
                nextInterfacesByName.put(name, merge(other, clazz));
            } else {
                nextInterfacesByName.put(name, clazz);
            }
        }
        nextInterfaces.addAll(nextInterfacesByName.values());
        Combine combine = new Combine(result);
        JavaClazz dependencyInterface = combine.apply(nextInterfaces);

        JavaClazz entryInterface = new JavaClazzBuilder()
                .addType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(dependencyInterface.getType().getPackageName())
                .withClassName(interfaceName)
                .addToInterfaces(Generics.UNWRAP.apply(dependencyInterface.getType()))
                .endType()
                .build();

        result.add(entryInterface);
        result.add(dependencyInterface);
        return result;
    }
}
