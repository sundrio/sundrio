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

package io.sundr.dsl.internal.functions;


import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.COMBINATION_OF;
import static io.sundr.dsl.internal.Constants.IS_COMPOSITE;
import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TERMINATING_TYPES;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.getTerminatingTypes;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.hasReturnType;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.isComposite;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.isTerminal;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.stripSuffix;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.toInterfaceName;

public class Combine implements Function<Set<JavaClazz>, JavaClazz> {

    private final Set<JavaClazz> intermediate;

    public Combine() {
        this(new LinkedHashSet<JavaClazz>());
    }

    public Combine(Set<JavaClazz> intermediate) {
        this.intermediate = intermediate;
    }

    @Override
    public JavaClazz apply(Set<JavaClazz> items) {
        return combine(items, intermediate);
    }

    public static JavaClazz combineTwo(JavaClazz left, JavaClazz right) {
        boolean isTerminal = false;
        JavaType originalReturnType = TRANSPARENT;
        Set<JavaType> genericTypes = new LinkedHashSet<>();
        Set<JavaType> interfaceTypes = new LinkedHashSet<>();
        Set<JavaType> terminatingTypes = new LinkedHashSet<>();

        String className = toInterfaceName(
                stripSuffix(left.getType().getClassName())
                        + stripSuffix(right.getType().getClassName())
        );

        terminatingTypes.addAll(getTerminatingTypes(left.getType()));
        terminatingTypes.addAll(getTerminatingTypes(right.getType()));
        for (JavaType type : terminatingTypes) {
            genericTypes.add(Generics.MAP.apply(type));
        }

        if (isTerminal(left) && isTerminal(right)) {
            isTerminal = true;
            interfaceTypes.add(left.getType());
            interfaceTypes.add(right.getType());
        } else if (isTerminal(left)) {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(new JavaTypeBuilder(right.getType()).withGenericTypes(new JavaType[]{left.getType()}).build());
            originalReturnType = (JavaType) left.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (isTerminal(right)) {
            interfaceTypes.add(right.getType());
            interfaceTypes.add(new JavaTypeBuilder(left.getType()).withGenericTypes(new JavaType[]{right.getType()}).build());
            originalReturnType = (JavaType) right.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (hasReturnType(left)) {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(new JavaTypeBuilder(right.getType()).withGenericTypes(new JavaType[]{left.getType()}).build());
            originalReturnType = (JavaType) left.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (hasReturnType(right)) {
            interfaceTypes.add(right.getType());
            interfaceTypes.add(new JavaTypeBuilder(left.getType()).withGenericTypes(new JavaType[]{right.getType()}).build());
            originalReturnType = (JavaType) right.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (!isComposite(left) && isComposite(right)) {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(new JavaTypeBuilder(right.getType()).withGenericTypes(new JavaType[]{left.getType()}).build());
            originalReturnType = (JavaType) left.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else if (isComposite(left) && !isComposite(right)) {
            interfaceTypes.add(right.getType());
            interfaceTypes.add(new JavaTypeBuilder(left.getType()).withGenericTypes(new JavaType[]{right.getType()}).build());
            originalReturnType = (JavaType) right.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
        } else {
            interfaceTypes.add(left.getType());
            interfaceTypes.add(right.getType());
            genericTypes.add(Generics.MAP.apply(TRANSPARENT));
        }

        return new JavaClazzBuilder()
                .addType()
                .withKind(JavaKind.INTERFACE)
                .withClassName(className)
                .withPackageName(left.getType().getPackageName())
                .withInterfaces(interfaceTypes)
                .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()]))
                .addToAttributes(ORIGINAL_RETURN_TYPE, originalReturnType)
                .addToAttributes(TERMINATING_TYPES, terminatingTypes)
                .addToAttributes(IS_TERMINAL, isTerminal)
                .addToAttributes(IS_COMPOSITE, true)
                .addToAttributes(COMBINATION_OF, Arrays.asList(left, right))
                .endType()
                .build();
    }

    private static JavaClazz combine(Set<JavaClazz> clazzes, Set<JavaClazz> alsoRequired) {
        if (clazzes.size() <= 1) {
            return clazzes.iterator().next();
        } else if (clazzes.size() == 2) {
            Iterator<JavaClazz> iterator = clazzes.iterator();
            return combineTwo(iterator.next(), iterator.next());
        } else {
            Set<JavaClazz> subSet = new LinkedHashSet<>(clazzes);
            Iterator<JavaClazz> iterator = subSet.iterator();
            JavaClazz first = iterator.next();
            JavaClazz second = iterator.next();
            subSet.remove(first);
            subSet.remove(second);
            
            JavaClazz cobmined = combineTwo(first, second);
            alsoRequired.add(cobmined);
            Set<JavaClazz> newSet = new LinkedHashSet<>();
            newSet.add(cobmined);
            newSet.addAll(subSet);
            return combine(newSet, alsoRequired);
        }
    }
}
