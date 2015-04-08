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
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.utils.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.codegen.utils.TypeUtils.newGeneric;

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
            JavaType builderType = TypeAs.BUILDER.apply(item.getType());
            JavaType fluent = TypeAs.GENERIC_FLUENT.apply(item.getType());
            Set<JavaMethod> constructors = new LinkedHashSet<>();
            Set<JavaMethod> methods = new LinkedHashSet<>();
            Set<JavaProperty> fields = new LinkedHashSet<>();
            
            JavaProperty fluentProperty = new JavaPropertyBuilder().withType(fluent).withName("fluent").build();
            fields.add(fluentProperty);
            
            JavaMethod emptyConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addToAttributes(BODY, "this.fluent = this;")
                    .build();
            JavaMethod fluentConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addArguments()
                    .withType(fluent)
                    .withName("fluent")
                    .and()
                    .addToAttributes(BODY, "this.fluent = fluent;")
                    .build();

            JavaMethod instanceConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addArguments()
                    .withType(item.getType())
                    .withName("instance").and()
                    .addToAttributes(BODY, toInstanceConstructorBody(item))
                    .build();

            constructors.add(emptyConstructor);
            constructors.add(fluentConstructor);
            constructors.add(instanceConstructor);

            JavaMethod build = new JavaMethodBuilder()
                    .withReturnType(item.getType())
                    .withName("build")
                    .addToAttributes(BODY, toBuild(item))
                    .build();

            methods.add(build);

            return new JavaClazzBuilder(item)
                    .withType(builderType)
                    .withFields(fields)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .build();
        }

    };

    static final String BODY = "BODY";
    private static final String BUILDABLE = "BUILDABLE";

    private static JavaProperty arrayAsList(JavaProperty property, boolean buildable) {
        return new JavaPropertyBuilder(property)
                .withArray(false)
                .withType(TypeAs.ARRAY_AS_LIST.apply(property.getType()))
                .addToAttributes(BUILDABLE, buildable)
                .build();
    }

    private static String toInstanceConstructorBody(JavaClazz clazz) {
        JavaMethod constructor = clazz.getConstructors().iterator().next();
        StringBuilder sb = new StringBuilder();
        sb.append("this(); ");
        for (JavaProperty property : constructor.getArguments()) {
            sb.append("with").append(property.getNameCapitalized()).append("(instance.").append(property.getGetter()).append("()); ");
        }
        return sb.toString();
    }

    private static String toBuild(JavaClazz clazz) {
        JavaMethod constructor = clazz.getConstructors().iterator().next();
        StringBuilder sb = new StringBuilder();
        sb.append("return new ").append(clazz.getType().getSimpleName()).append("(");
        sb.append(StringUtils.join(constructor.getArguments(), new Function<JavaProperty, String>() {
            @Override
            public String apply(JavaProperty item) {
                return "fluent." + item.getGetter() + "()";
            }
        }, ","));

        sb.append(");\n");
        return sb.toString();
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
