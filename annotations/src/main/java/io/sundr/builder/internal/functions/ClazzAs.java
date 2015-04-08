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

import java.util.LinkedHashSet;
import java.util.Set;

public enum ClazzAs implements Function<JavaClazz, JavaClazz> {

    FLUENT {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> methods = new LinkedHashSet<>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<>();
            Set<JavaProperty> properties = new LinkedHashSet<>();

            for (JavaProperty property : item.getFields()) {
                boolean buildable = (boolean) property.getType().getAttributes().get(BUILDABLE);
                if (property.isArray()) {
                    methods.add(ToMethod.WITH_ARRAY.apply(property));
                    methods.add(ToMethod.GETTER_ARRAY.apply(property));
                    properties.add(arrayAsList(property, buildable));
                } else {
                    properties.add(new JavaPropertyBuilder(property).addToAttributes(BUILDABLE, buildable).build());
                    methods.add(ToMethod.GETTER.apply(property));
                    methods.add(ToMethod.WITH.apply(property));
                }
            }

            for (JavaProperty property : properties) {
                if (property.getType().isCollection()) {
                    if (property.getType().getClassName().contains("Set") || property.getType().getClassName().contains("List")) {
                        methods.add(ToMethod.ADD_TO_COLLECTION.apply(property));
                    } else if (property.getType().getClassName().contains("Map")) {
                        methods.add(ToMethod.ADD_TO_MAP.apply(property));
                    }
                }

                if (isBuildable(property)) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(property));
                    nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(property));
                }
            }

            return new JavaClazzBuilder(item)
                    .withType(TypeAs.FLUENT.apply(item.getType()))
                    .withFields(properties)
                    .withNested(nestedClazzes)
                    .withMethods(methods)
                    .build();
        }
    }, BUILDER {
        @Override
        public JavaClazz apply(JavaClazz item) {
            return new JavaClazzBuilder(item)
                    .withType(TypeAs.BUILDER.apply(item.getType()))
                    .addToAttributes(BUILDS, item.getType())
                    .build();
        }

    };

    private static final String BUILDABLE = "BUILDABLE";
    private static final String BUILDS = "BUILDS";

    private static JavaProperty arrayAsList(JavaProperty property, boolean buildable) {
        return new JavaPropertyBuilder(property)
                .withArray(false)
                .withType(TypeAs.ARRAY_AS_LIST.apply(property.getType()))
                .addToAttributes(BUILDABLE, buildable)
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
