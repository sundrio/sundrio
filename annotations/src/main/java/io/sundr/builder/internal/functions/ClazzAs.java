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
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.utils.StringUtils;

import javax.lang.model.element.Modifier;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.internal.utils.BuilderUtils.BUILDABLE;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.findGetter;
import static io.sundr.builder.internal.utils.BuilderUtils.getPropertyBuildableAncestors;
import static io.sundr.builder.internal.utils.BuilderUtils.hasDefaultConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.builder.internal.utils.BuilderUtils.isList;
import static io.sundr.builder.internal.utils.BuilderUtils.isMap;
import static io.sundr.builder.internal.utils.BuilderUtils.isSet;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public enum ClazzAs implements Function<JavaClazz, JavaClazz> {

    FLUENT {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> methods = new LinkedHashSet<JavaMethod>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<JavaClazz>();
            Set<JavaProperty> properties = new LinkedHashSet<JavaProperty>();

            JavaType fluentType = TypeAs.FLUENT.apply(item.getType());
            for (JavaProperty property : item.getFields()) {
                JavaProperty toAdd = property;
                boolean buildable = (Boolean) property.getType().getAttributes().get(BUILDABLE);
                if (property.isArray()) {
                    JavaProperty asList = arrayAsList(property, buildable);
                    methods.add(ToMethod.WITH_ARRAY.apply(property));
                    methods.add(ToMethod.GETTER_ARRAY.apply(property));
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(asList));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
                    toAdd = asList;
                } else if (isSet(property.getType()) || isList(property.getType())) {
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                    methods.add(ToMethod.WITH_ARRAY.apply(property));
                } else if (isMap(property.getType())) {
                    methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                } else {
                    toAdd = new JavaPropertyBuilder(property).addToAttributes(BUILDABLE, buildable).build();
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }

                Set<JavaProperty> descendants = getPropertyBuildableAncestors(toAdd);

                if (isMap(toAdd.getType())) {
                    properties.add(toAdd);
                } else if (isBuildable(toAdd)) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!toAdd.getType().isCollection() && !toAdd.isArray()) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                    }
                    methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(new JavaPropertyBuilder(toAdd).addToAttributes(MEMBER_OF, fluentType).build()));

                    JavaType builderType = TypeAs.VISITABLE_BUILDER.apply(toAdd.getType());
                    if (toAdd.getType().isCollection()) {
                        builderType = typeGenericOf(toAdd.getType(), builderType);
                    }

                    properties.add(new JavaPropertyBuilder(toAdd).withType(builderType).build());
                } else if (descendants.size() > 0 && toAdd.getType().isCollection()) {
                    properties.add(toAdd);

                    for (JavaProperty descendant : descendants) {
                        if (descendant.getType().isCollection()) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }
                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(new JavaPropertyBuilder(descendant).addToAttributes(MEMBER_OF, fluentType).build()));

                        JavaType builderType = TypeAs.VISITABLE_BUILDER.apply(descendant.getType());
                        if (descendant.getType().isCollection()) {
                            builderType = typeGenericOf(descendant.getType(), builderType);
                        }

                        properties.add(new JavaPropertyBuilder(descendant).withType(builderType).build());

                    }

                } else {
                    properties.add(toAdd);
                }
            }

            JavaMethod equals = new JavaMethodBuilder()
                    .addToModifiers(Modifier.PUBLIC)
                    .withReturnType(ClassToJavaType.FUNCTION.apply(boolean.class))
                    .addNewArgument().withName("o").withType(Constants.OBJECT).endArgument()
                    .withName("equals")
                    .addToAttributes(BODY, toEquals(fluentType, properties))
                    .build();

            methods.add(equals);

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
            Set<JavaMethod> constructors = new LinkedHashSet<JavaMethod>();
            Set<JavaMethod> methods = new LinkedHashSet<JavaMethod>();
            Set<JavaProperty> fields = new LinkedHashSet<JavaProperty>();

            JavaProperty fluentProperty = new JavaPropertyBuilder().withType(fluent).withName("fluent").build();
            fields.add(fluentProperty);

            JavaMethod emptyConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addToAttributes(BODY, hasDefaultConstructor(item) ? "this(new " + item.getType().getClassName() + "());" : "this.fluent = this;")
                    .build();

            JavaMethod fluentConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addNewArgument()
                    .withType(fluent)
                    .withName("fluent")
                    .and()
                    .addToAttributes(BODY, hasDefaultConstructor(item) ? "this(fluent, new " + item.getType().getClassName() + "());" : "this.fluent = fluent;")
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

            JavaMethod equals = new JavaMethodBuilder()
                    .withReturnType(ClassToJavaType.FUNCTION.apply(boolean.class))
                    .addNewArgument().withName("o").withType(Constants.OBJECT).endArgument()
                    .withName("equals")
                    .addToAttributes(BODY, toEquals(builderType, fields))
                    .build();

            methods.add(equals);

            return new JavaClazzBuilder(item)
                    .withType(builderType)
                    .withFields(fields)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .build();
        }

    }, EDITABLE_BUILDER {
        @Override
        public JavaClazz apply(JavaClazz item) {
            JavaClazz builder = BUILDER.apply(item);
            Set<JavaMethod> methods = new LinkedHashSet<JavaMethod>();
            for (JavaMethod m : builder.getMethods()) {
                if (m.getName().equals("build")) {

                    methods.add(new JavaMethodBuilder()
                            .withReturnType(TypeAs.EDITABLE.apply(m.getReturnType()))
                            .withName("build")
                            .addToAttributes(BODY, toBuild(EDITABLE.apply(item)))
                            .build());
                } else {
                    methods.add(m);
                }
            }
            return new JavaClazzBuilder(builder)
                    .withMethods(methods)
                    .build();

        }
    }, EDITABLE {
        @Override
        public JavaClazz apply(JavaClazz item) {
            JavaType type = item.getType();
            JavaType editableType = TypeAs.EDITABLE.apply(type);
            JavaType builderType = TypeAs.BUILDER.apply(type);

            Set<JavaMethod> constructors = new LinkedHashSet<JavaMethod>();
            Set<JavaMethod> methods = new LinkedHashSet<JavaMethod>();

            for (JavaMethod constructor : item.getConstructors()) {
                constructors.add(superConstructorOf(constructor, editableType));
            }

            JavaMethod edit = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .withName("edit")
                    .addToAttributes(BODY, "return new " + builderType.getSimpleName() + "(this);")
                    .build();

            methods.add(edit);

            return new JavaClazzBuilder()
                    .withType(editableType)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .build();
        }
    }, INLINEABLE {
        @Override
        public JavaClazz apply(JavaClazz item) {
            JavaType type = item.getType();
            JavaType builderType = TypeAs.SHALLOW_BUILDER.apply(item.getType());
            JavaType updateableType = TypeAs.INLINEABLE.apply(type);

            JavaProperty builderProperty = new JavaPropertyBuilder()
                    .withType(TypeAs.BUILDER.apply(item.getType()))
                    .withName("builder")
                    .addToModifiers(Modifier.PRIVATE)
                    .addToModifiers(Modifier.FINAL)
                    .build();

            JavaProperty visitorProperty = new JavaPropertyBuilder()
                    .withType(typeGenericOf(BuilderContextManager.getContext().getVisitorInterface().getType(), item.getType()))
                    .withName("visitor")
                    .addToModifiers(Modifier.PRIVATE)
                    .addToModifiers(Modifier.FINAL)
                    .build();


            JavaMethod update = new JavaMethodBuilder()
                    .withReturnType(item.getType())
                    .withName("update")
                    .addToAttributes(BODY, item.getType().getSimpleName() + " item = builder.build();visitor.visit(item);return item;")
                    .addToModifiers(Modifier.PUBLIC)
                    .build();

            JavaMethod constructor1 = new JavaMethodBuilder()
                    .withReturnType(updateableType)
                    .withName("")
                    .addNewArgument()
                        .withName("item")
                        .withType(item.getType())
                    .and()
                    .addNewArgument()
                        .withName("visitor")
                        .withType(visitorProperty.getType())
                    .and()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToAttributes(BODY, "this.builder=new "+builderType.getSimpleName()+"(this, item);this.visitor=visitor;")
                    .build();


            JavaMethod constructor2 = new JavaMethodBuilder()
                    .withReturnType(updateableType)
                    .withName("")
                    .addNewArgument()
                    .withName("visitor")
                    .withType(visitorProperty.getType())
                    .and()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToAttributes(BODY, "this.builder=new "+builderType.getSimpleName()+"(this);this.visitor=visitor;")
                    .build();


            return new JavaClazzBuilder()
                    .withType(updateableType)
                    .addToConstructors(constructor1)
                    .addToConstructors(constructor2)
                    .addToFields(builderProperty)
                    .addToFields(visitorProperty)
                    .addToMethods(update)
                    .build();
        }
    };

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


    private static String toEquals(JavaType type, Collection<JavaProperty> properties) {
        String simpleName = type.getClassName();
        JavaType superClass = type.getSuperClass();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("if (this == o) return true;").append("\n");
        sb.append("if (o == null || getClass() != o.getClass()) return false;").append("\n");
        //If base fluent is the superclass just skip.
        if (!Constants.BASE_FLUENT.getClassName().equals(superClass.getClassName())) {
            sb.append("if (!super.equals(o)) return false;").append("\n");
        }
        sb.append(simpleName).append(" that = (").append(simpleName).append(") o;").append("\n");
        for (JavaProperty property : properties) {
            String name = property.getName();
            if (BuilderUtils.isPrimitive(property.getType())) {
                sb.append("if (").append(name).append(" != ").append("that.").append(name).append(") return false;").append("\n");
            } else if (BuilderUtils.isDescendant(type, property.getType())) {
                sb.append("if (").append(name).append(" != null &&").append(name).append(" != this ? !").append(name).append(".equals(that.").append(name).append(") :")
                        .append("that.").append(name).append(" != null &&").append(name).append(" != this ) return false;").append("\n");
            } else {
                sb.append("if (").append(name).append(" != null ? !").append(name).append(".equals(that.").append(name).append(") :")
                        .append("that.").append(name).append(" != null) return false;").append("\n");
            }
        }

        sb.append("return true;").append("\n");
        return sb.toString();
    }

    private static JavaMethod superConstructorOf(JavaMethod constructor, JavaType constructorType) {
        return new JavaMethodBuilder(constructor)
                .withReturnType(constructorType)
                .addToAttributes(BODY, "super(" + StringUtils.join(constructor.getArguments(), new Function<JavaProperty, String>() {
                    @Override
                    public String apply(JavaProperty item) {
                        return item.getName();
                    }
                }, ", ") + ");")
                .build();
    }

}
