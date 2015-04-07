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

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static io.sundr.dsl.internal.Constants.IS_COMPOSITE;
import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TERMINATING_TYPES;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.*;

public class Transition {

    public static JavaClazz create(JavaClazz from, JavaClazz to) {
        String className = toInterfaceName(stripSuffix(from.getType().getClassName()) +
                "To" +
                stripSuffix(to.getType().getClassName()));

        JavaType transitionInterface = new JavaTypeBuilder(from.getType())
                .withGenericTypes(new JavaType[]{to.getType()}).build();

        return new JavaClazzBuilder()
                .addType()
                .withKind(JavaKind.INTERFACE)
                .withClassName(className)
                .withPackageName(from.getType().getPackageName())
                .withGenericTypes(to.getType().getGenericTypes())
                .withInterfaces(new LinkedHashSet<>(Arrays.asList(transitionInterface)))
                .addToAttributes(ORIGINAL_RETURN_TYPE, to.getType().getAttributes().get(ORIGINAL_RETURN_TYPE))
                .addToAttributes(TERMINATING_TYPES, to.getType().getAttributes().get(TERMINATING_TYPES))
                .addToAttributes(IS_TERMINAL, false)
                .addToAttributes(IS_COMPOSITE, false)
                .endType()
                .build();

    }
}
