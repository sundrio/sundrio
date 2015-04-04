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
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.TargetName;

import javax.lang.model.element.ExecutableElement;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.processor.JavaTypeUtils.combine;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.executableToInterface;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.toInterfaceName;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.unwrapGenerics;

public final class DslUtils {
    
    private DslUtils() {
        //Utility Class
    }
    
    static Set<JavaClazz> createGenericInterfaces(DslProcessorContext context, Collection<ExecutableElement> elements) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        for (ExecutableElement current : elements) {
            if (current.getAnnotation(EntryPoint.class) != null) {
                //Let's skip entry points and focus on generic types.
                continue;
            }
            result.add(executableToInterface(context, current));
        }
        return result;
    }


    static Set<JavaClazz> createTransitionInterface(DslProcessorContext context, ExecutableElement current, Set<ExecutableElement> next) {
        Set<JavaClazz> result = new LinkedHashSet<>();
        JavaMethod method = context.getToMethod().apply(current);
        TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(method.getName());

        Set<JavaClazz> nextInterfaces = new LinkedHashSet<>();
        for (ExecutableElement element : next) {
            nextInterfaces.add(executableToInterface(context, element));
        }

        JavaClazz dependencyInterface = combine(nextInterfaces, result);
        
        JavaClazz entryInterface = new JavaClazzBuilder()
                .addType()
                    .withKind(JavaKind.INTERFACE)
                    .withPackageName(dependencyInterface.getType().getPackageName())
                    .withClassName(interfaceName)
                    .addToInterfaces(unwrapGenerics(dependencyInterface.getType()))
                .endType()
                .build();
        
        result.add(entryInterface);
        result.add(dependencyInterface);
        return result;
    }

    static JavaMethod createEntryPointMethod(DslProcessorContext context, ExecutableElement current) {
        JavaMethod method = context.getToMethod().apply(current);
        TargetName targetInterfaceName = current.getAnnotation(TargetName.class);

        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(method.getName());

        JavaType returnType = new JavaTypeBuilder()
                .withClassName(interfaceName)
                .withPackageName(context.getElements().getPackageOf(current).getQualifiedName().toString())
                .build();
        
        return new JavaMethodBuilder(method).withReturnType(returnType).build();
    }
}
