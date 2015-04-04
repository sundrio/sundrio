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

package io.sundr.builder.internal.functions;

import io.sundr.Function;
import io.sundr.codegen.model.AttributeSupport;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public enum ClazzTo implements Function<JavaClazz, JavaClazz> {

    FLUENT {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> methods = new LinkedHashSet<>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<>();
            Set<JavaProperty> properties = new LinkedHashSet<>();

            for (JavaProperty property : item.getFields()) {
                boolean buildable = (boolean) property.getType().getAttributes().get(BUILDABLE);
                if (property.isArray()) {
                    methods.add(ProtpertyToMethod.WITH_ARRAY.apply(property));
                    methods.add(ProtpertyToMethod.GETTER_ARRAY.apply(property));
                    properties.add(arrayAsList(property, buildable));
                } else {
                    properties.add(new JavaPropertyBuilder(property).addToAttributes(BUILDABLE, buildable).build());
                    methods.add(ProtpertyToMethod.GETTER.apply(property));
                    methods.add(ProtpertyToMethod.WITH.apply(property));
                }
            }

            for (JavaProperty property : properties) {
                if (property.getType().isCollection()) {
                    if (property.getType().getClassName().contains("Set") || property.getType().getClassName().contains("List")) {
                        methods.add(ProtpertyToMethod.ADD_TO_COLLECTION.apply(property));
                    } else if (property.getType().getClassName().contains("Map")) {
                        methods.add(ProtpertyToMethod.ADD_TO_MAP.apply(property));
                    }
                }

                if (isBuildable(property)) {
                    methods.add(ProtpertyToMethod.ADD_NESTED.apply(property));
                    nestedClazzes.add(toNestedClazz(property));
                }
            }

            return new JavaClazzBuilder(item)
                    .withType(TypeTo.FLUENT.apply(item.getType()))
                    .withFields(properties)
                    .withNested(nestedClazzes)
                    .withMethods(methods)
                    .build();
        }
    }, BUILDER {
        @Override
        public JavaClazz apply(JavaClazz item) {
            return new JavaClazzBuilder(item)
                    .withType(TypeTo.BUILDER.apply(item.getType()))
                    .addToAttributes(BUILDS, item.getType())
                    .build();
        }

    };

    private static final String BUILDABLE = "BUILDABLE";
    private static final String BUILDS = "BUILDS";

    private static JavaProperty arrayAsList(JavaProperty property, boolean buildable) {
        return new JavaPropertyBuilder(property)
                .withArray(false)
                .withType(TypeTo.ARRAY_AS_LIST.apply(property.getType()))
                .addToAttributes(BUILDABLE, buildable)
                .build();
    }

    private static JavaClazz toNestedClazz(JavaProperty property) {
        JavaType nestedType = PropretyTo.NESTED.apply(property);
        Set<JavaMethod> nestedMethods = new HashSet<>();
        nestedMethods.add(ProtpertyToMethod.AND.apply(property));
        nestedMethods.add(ProtpertyToMethod.END.apply(property));
        return createNestedClazz(nestedType, TypeTo.UNWRAP_COLLECTION_OF.apply(property.getType()), nestedMethods);
    }

    private static JavaClazz createNestedClazz(JavaType nestedType, JavaType originalType, Set<JavaMethod> methods) {
        Set<JavaProperty> properties = new HashSet<>();

        properties.add(new JavaPropertyBuilder()
                .withName("builder")
                .withType(TypeTo.SHALLOW_BUILDER.apply(originalType)).build());

        return new JavaClazzBuilder()
                .withType(nestedType)
                .withFields(properties)
                .withMethods(methods)
                .build();
    }

    /**
     * Checks if {@link io.sundr.codegen.model.JavaType} has the BUILDABLE attribute set to true.
     *
     * @param item The type to check.
     * @return
     */
    private static boolean isBuildable(AttributeSupport item) {
        if (item == null) {
            return false;
        } else if (item.getAttributes().containsKey(BUILDABLE)) {
            return (Boolean) item.getAttributes().get(BUILDABLE);
        }
        return false;
    }
}
