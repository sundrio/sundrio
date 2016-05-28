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

package io.sundr.dsl.internal.type.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.Arrays;

public final class Merge {
    
    private Merge() {}

    public static final Function<JavaType[], JavaType> TYPES = new Function<JavaType[], JavaType>() {
        @Override
        public JavaType apply(JavaType... items) {
            if (items == null || items.length == 0) {
                throw new IllegalArgumentException("Items cannot be empty.");
            } else if (items.length == 1) {
                return items[0];
            } else if (items.length == 2) {
                JavaTypeBuilder builder = new JavaTypeBuilder(items[0]);
                for (JavaType type : items[1].getInterfaces()) {
                    builder = builder.addToInterfaces(type);
                }

                for (JavaType type : items[1].getGenericTypes()) {
                    if (!Arrays.asList(items[0].getGenericTypes()).contains(type)) {
                        builder = builder.addToGenericTypes(type);
                    }
                }
                return builder.build();
            } else {
                JavaType[] rest = new JavaType[items.length - 1];
                System.arraycopy(items, 1, rest, 0, rest.length);
                return TYPES.apply(new JavaType[]{items[0], TYPES.apply(rest)});
            }
        }
    };


    public static final Function<JavaClazz[], JavaClazz> CLASSES = new Function<JavaClazz[], JavaClazz>() {
        @Override
        public JavaClazz apply(JavaClazz... items) {
            if (items == null || items.length == 0) {
                throw new IllegalArgumentException("Items cannot be empty.");
            } else if (items.length == 1) {
                return items[0];
            } else if (items.length == 2) {
                JavaType mergedType = TYPES.apply(new JavaType[]{items[0].getType(), items[1].getType()});

                JavaClazzBuilder builder = new JavaClazzBuilder(items[0]).withType(mergedType);
                for (Method constructor : items[1].getConstructors()) {
                    builder = builder.addToConstructors(constructor);
                }

                for (Method method : items[1].getMethods()) {
                    builder = builder.addToMethods(method);
                }
                for (Property property : items[1].getFields()) {
                    builder = builder.addToFields(property);
                }
                return builder.build();
            } else {
                JavaClazz[] rest = new JavaClazz[items.length - 1];
                System.arraycopy(items, 1, rest, 0, rest.length);
                CLASSES.apply(new JavaClazz[]{items[0], CLASSES.apply(rest)});
            }
            return null;

        }
    };
}
