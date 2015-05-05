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
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.utils.StringUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.builder.internal.utils.BuilderUtils.BUILDABLE;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.findGetter;
import static io.sundr.builder.internal.utils.BuilderUtils.hasDefaultConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;

public enum ClazzAs implements Function<JavaClazz, JavaClazz> {

    FLUENT {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> methods = new LinkedHashSet<>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<>();
            Set<JavaProperty> properties = new LinkedHashSet<>();

            JavaType fluentType = TypeAs.FLUENT.apply(item.getType());
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
                    if (isSet(property.getType()) || isList(property.getType())) {
                        methods.add(ToMethod.ADD_TO_COLLECTION.apply(property));
                    } else if (property.getType().getClassName().contains("Map")) {
                        methods.add(ToMethod.ADD_TO_MAP.apply(property));
                    }
                }

                if (isBuildable(property) && !isMap(property.getType())) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(property));
                    methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(property));
                    nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(new JavaPropertyBuilder(property).addToAttributes(MEMBER_OF, fluentType).build()));
                }
            }

            return new JavaClazzBuilder(item)
                    .withType(fluentType)
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
                    .addToAttributes(BODY, hasDefaultConstructor(item) ? "this(new "+item.getType().getClassName()+"());" : "this.fluent = this;")
                    .build();
            
            JavaMethod fluentConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addNewArgument()
                    .withType(fluent)
                    .withName("fluent")
                    .and()
                    .addToAttributes(BODY, hasDefaultConstructor(item) ? "this(fluent, new "+item.getType().getClassName()+"());" : "this.fluent = fluent;")
                    .build();

            JavaMethod instanceAndFluentCosntructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addNewArgument()
                    .withType(fluent)
                    .withName("fluent")
                    .and()
                    .addNewArgument()
                    .withType(item.getType())
                    .withName("instance").and()
                    .addToAttributes(BODY, toInstanceConstructorBody(item, "fluent"))
                    .build();
            
            JavaMethod instanceConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addNewArgument()
                    .withType(item.getType())
                    .withName("instance").and()
                    .addToAttributes(BODY, toInstanceConstructorBody(item, "this"))
                    .build();

            constructors.add(emptyConstructor);
            constructors.add(fluentConstructor);
            constructors.add(instanceAndFluentCosntructor);
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
    static final String MEMBER_OF = "MEMBER_OF";
    private static final JavaType MAP = ClassToJavaType.FUNCTION.apply(Map.class);
    private static final JavaType LIST = ClassToJavaType.FUNCTION.apply(List.class);
    private static final JavaType SET = ClassToJavaType.FUNCTION.apply(Set.class);

    private static JavaProperty arrayAsList(JavaProperty property, boolean buildable) {
        return new JavaPropertyBuilder(property)
                .withArray(false)
                .withType(TypeAs.ARRAY_AS_LIST.apply(property.getType()))
                .addToAttributes(BUILDABLE, buildable)
                .build();
    }

    private static String toInstanceConstructorBody(JavaClazz clazz, String fluent) {
        JavaMethod constructor = findBuildableConstructor(clazz);
        StringBuilder sb = new StringBuilder();
        sb.append("this.fluent = " + fluent + "; ");
        for (JavaProperty property : constructor.getArguments()) {
            JavaMethod getter = findGetter(clazz, property);
            sb.append(fluent).append(".with").append(property.getNameCapitalized()).append("(instance.").append(getter.getName()).append("()); ");
        }
        return sb.toString();
    }

    private static String toBuild(JavaClazz clazz) {
        JavaMethod constructor = findBuildableConstructor(clazz);
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getType().getSimpleName()).append(" buildable = new ").append(clazz.getType().getSimpleName()).append("(");
        sb.append(StringUtils.join(constructor.getArguments(), new Function<JavaProperty, String>() {
            @Override
            public String apply(JavaProperty item) {
                String prefix = item.getType().isBoolean() ? "is" : "get";
                return "fluent." + prefix + item.getNameCapitalized() + "()";
            }
        }, ","));

        sb.append(");\n");
        sb.append("validate(buildable);\n");
        sb.append("return buildable;\n");
        return sb.toString();
    }

    private static boolean isMap(JavaType type) {
        return type.equals(MAP) || type.getInterfaces().contains(MAP);
    }

    private static boolean isList(JavaType type) {
        return type.equals(LIST) || type.getInterfaces().contains(LIST);
    }

    private static boolean isSet(JavaType type) {
        return type.equals(SET) || type.getInterfaces().contains(SET);
    }
}
