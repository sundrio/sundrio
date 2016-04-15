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
import io.sundr.builder.Visitor;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.StringUtils;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.Constants.REPLACEABLE;
import static io.sundr.builder.internal.utils.BuilderUtils.BUILDABLE;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.findGetter;
import static io.sundr.builder.internal.utils.BuilderUtils.hasDefaultConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.builder.internal.utils.BuilderUtils.isList;
import static io.sundr.builder.internal.utils.BuilderUtils.isMap;
import static io.sundr.builder.internal.utils.BuilderUtils.isSet;
import static io.sundr.builder.internal.utils.BuilderUtils.*;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public enum ClazzAs implements Function<JavaClazz, JavaClazz> {

    FLUENT_INTERFACE {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> methods = new LinkedHashSet<JavaMethod>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<JavaClazz>();
            JavaType fluentType = TypeAs.FLUENT_INTERFACE.apply(item.getType());
            //The generic letter is always the last
            final JavaType genericTypeLetter = new JavaTypeBuilder(fluentType.getGenericTypes()[fluentType.getGenericTypes().length -1]).withInterfaces().withSuperClass(null).build();

            for (JavaProperty property : item.getFields()) {
                if (property.getModifiers().contains(Modifier.STATIC)) {
                    continue;
                }
                if (!hasSetter(item, property) && !hasBuildableConstructorWithArgument(item, property)) {
                    continue;
                }

                JavaProperty toAdd = new JavaPropertyBuilder(property).withModifiers(Collections.<Modifier>emptySet()).build();
                boolean buildable = (Boolean) toAdd.getType().getAttributes().get(BUILDABLE);
                if (toAdd.isArray()) {
                    JavaProperty asList = arrayAsList(toAdd, buildable);
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                    methods.add(ToMethod.GETTER_ARRAY.apply(toAdd));
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(asList));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
                    toAdd = asList;
                } else if (isSet(toAdd.getType()) || isList(toAdd.getType())) {
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                } else if (isMap(toAdd.getType())) {
                    methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.ADD_MAP_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_MAP_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                } else {
                    toAdd = new JavaPropertyBuilder(toAdd).addToAttributes(BUILDABLE, buildable).build();
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }

                Set<JavaProperty> descendants = Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(toAdd);

                if (isMap(toAdd.getType())) {
                    //
                } else if (isBuildable(toAdd)) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!toAdd.getType().isCollection() && !toAdd.isArray()) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                    }
                    methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(new JavaPropertyBuilder(toAdd).addToAttributes(MEMBER_OF, fluentType).build()));
                } else if (!descendants.isEmpty() && toAdd.getType().isCollection()) {
                    for (JavaProperty descendant : descendants) {
                        if (descendant.getType().isCollection()) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }
                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(new JavaPropertyBuilder(descendant).addToAttributes(MEMBER_OF, fluentType).build()));
                    }
                }
            }

            return new JavaClazzBuilder(item)
                    .withType(fluentType)
                    .withNested(nestedClazzes)
                    .withMethods(methods)
                    //The return type of builder methods is always T, we need to fix that.
                    .accept(new Visitor<JavaMethodBuilder>() {
                        public void visit(JavaMethodBuilder builder) {
                            if (builder.getReturnType().equals(Constants.T) && builder.getReturnType().getAttributes().containsKey(REPLACEABLE)) {
                                builder.withReturnType(genericTypeLetter);
                            } else if (Arrays.asList(builder.getReturnType().getGenericTypes()).contains(Constants.T)) {
                                List<JavaType> generics = new ArrayList<JavaType>();
                                for (JavaType g : builder.getReturnType().getGenericTypes()) {
                                    if (g.equals(Constants.T) && g.getAttributes().containsKey(REPLACEABLE)) {
                                        generics.add(genericTypeLetter);
                                    } else {
                                        generics.add(g);
                                    }
                                }
                                builder.editReturnType().withGenericTypes(generics.toArray(new JavaType[generics.size()])).endReturnType();
                            }
                        }
                    }).build();
        }
    }, FLUENT_IMPL {
        @Override
        public JavaClazz apply(JavaClazz item) {
            Set<JavaMethod> constructors = new LinkedHashSet<JavaMethod>();
            Set<JavaMethod> methods = new LinkedHashSet<JavaMethod>();
            Set<JavaClazz> nestedClazzes = new LinkedHashSet<JavaClazz>();
            Set<JavaProperty> properties = new LinkedHashSet<JavaProperty>();
            final JavaType fluentType = TypeAs.FLUENT_IMPL.apply(item.getType());
            //The generic letter is always the last
            final JavaType genericTypeLetter = new JavaTypeBuilder(fluentType.getGenericTypes()[fluentType.getGenericTypes().length -1]).withInterfaces().withSuperClass(null).build();

            JavaMethod emptyConstructor = new JavaMethodBuilder()
                    .withReturnType(fluentType)
                    .addToAttributes(BODY, "")
                    .build();

            JavaMethod instanceConstructor = new JavaMethodBuilder()
                    .withReturnType(fluentType)
                    .addNewArgument()
                    .withType(TypeAs.REMOVE_GENERICS_BOUNDS.apply(item.getType()))
                    .withName("instance").and()
                    .addToAttributes(BODY, toInstanceConstructorBody(item, ""))
                    .build();

            constructors.add(emptyConstructor);
            constructors.add(instanceConstructor);

            for (JavaProperty property : item.getFields()) {
                if (property.getModifiers().contains(Modifier.STATIC)) {
                    continue;
                }
                if (!hasSetter(item, property) && !hasBuildableConstructorWithArgument(item, property)) {
                    continue;
                }
                JavaProperty toAdd = new JavaPropertyBuilder(property).withModifiers(Collections.<Modifier>emptySet()).build();
                boolean buildable = (Boolean) toAdd.getType().getAttributes().get(BUILDABLE);
                if (toAdd.isArray()) {
                    JavaProperty asList = arrayAsList(toAdd, buildable);
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                    methods.add(ToMethod.GETTER_ARRAY.apply(toAdd));
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(asList));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
                    toAdd = asList;
                } else if (isSet(toAdd.getType()) || isList(toAdd.getType())) {
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                } else if (isMap(toAdd.getType())) {
                    methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.ADD_MAP_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_MAP_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                } else {
                    toAdd = new JavaPropertyBuilder(toAdd).addToAttributes(BUILDABLE, buildable).build();
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }

                Set<JavaProperty> descendants = Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(toAdd);

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
                } else if (!descendants.isEmpty() && toAdd.getType().isCollection()) {
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
                    .withConstructors(constructors)
                    .withFields(properties)
                    .withNested(nestedClazzes)
                    .withMethods(methods)
                    //The return type of builder methods is always T, we need to fix that.
                    .accept(new Visitor<JavaMethodBuilder>() {
                        public void visit(JavaMethodBuilder builder) {
                            if (builder.getReturnType().equals(Constants.T) && builder.getReturnType().getAttributes().containsKey(REPLACEABLE)) {
                                builder.withReturnType(genericTypeLetter);
                                String body = (String) builder.getAttributes().get(BODY);
                                if (body != null) {
                                    body = body.replaceAll("\\(T\\)", "\\(" + genericTypeLetter.getClassName() + "\\)");
                                    builder.getAttributes().put(BODY, body);
                                }
                            } else if (Arrays.asList(builder.getReturnType().getGenericTypes()).contains(Constants.T)) {
                                List<JavaType> generics = new ArrayList<JavaType>();
                                for (JavaType g : builder.getReturnType().getGenericTypes()) {
                                    if (g.equals(Constants.T) && g.getAttributes().containsKey(REPLACEABLE)) {
                                        generics.add(genericTypeLetter);
                                    } else {
                                        generics.add(g);
                                    }
                                }
                                String body = (String) builder.getAttributes().get(BODY);
                                if (body != null) {
                                    body = body.replaceAll("<T>", "<" + genericTypeLetter.getClassName() + ">");
                                    builder.getAttributes().put(BODY, body);
                                }
                                builder.editReturnType().withGenericTypes(generics.toArray(new JavaType[generics.size()])).endReturnType();
                            }
                        }
                    }).build();
        }
    }, BUILDER {
        @Override
        public JavaClazz apply(JavaClazz item) {
            JavaType builderType = TypeAs.BUILDER.apply(item.getType());
            JavaType instanceType = TypeAs.REMOVE_GENERICS_BOUNDS.apply(item.getType());
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
                    .withType(instanceType)
                    .withName("instance").and()
                    .addToAttributes(BODY, toInstanceConstructorBody(item, "fluent"))
                    .build();

            JavaMethod instanceConstructor = new JavaMethodBuilder()
                    .withReturnType(builderType)
                    .addNewArgument()
                    .withType(instanceType)
                    .withName("instance").and()
                    .addToAttributes(BODY, toInstanceConstructorBody(item, "this"))
                    .build();

            constructors.add(emptyConstructor);
            constructors.add(fluentConstructor);
            constructors.add(instanceAndFluentCosntructor);
            constructors.add(instanceConstructor);

            JavaMethod build = new JavaMethodBuilder()
                    .withReturnType(instanceType)
                    .withName("build")
                    .addToAttributes(BODY, toBuild(item, instanceType))
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

                    JavaClazz editable = EDITABLE.apply(item);
                    methods.add(new JavaMethodBuilder()
                            .withReturnType(TypeAs.EDITABLE.apply(m.getReturnType()))
                            .withName("build")
                            .addToAttributes(BODY, toBuild(editable, TypeAs.REMOVE_GENERICS_BOUNDS.apply(editable.getType())))
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
            JavaType builderType = TypeAs.combine(TypeAs.BUILDER, TypeAs.REMOVE_GENERICS_BOUNDS).apply(type);

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
    };

    private static JavaProperty arrayAsList(JavaProperty property, boolean buildable) {
        return new JavaPropertyBuilder(property)
                .withArray(false)
                .withType(TypeAs.ARRAY_AS_LIST.apply(TypeAs.BOXED_OF.apply(property.getType())))
                .addToAttributes(BUILDABLE, buildable)
                .build();
    }

    private static String toInstanceConstructorBody(JavaClazz clazz, String fluent) {
        JavaMethod constructor = findBuildableConstructor(clazz);
        StringBuilder sb = new StringBuilder();
        String ref = fluent;

        //We may use a reference to fluent or we may use directly "this". So we need to check.
        if (fluent != null && !fluent.isEmpty()) {
            sb.append("this.fluent = " + fluent + "; ");
        } else {
            ref = "this";
        }

        for (JavaProperty property : constructor.getArguments()) {
            JavaMethod getter = findGetter(clazz, property);
            if (getter != null) {
                sb.append(ref).append(".with").append(property.getNameCapitalized()).append("(instance.").append(getter.getName()).append("()); ");
            } else {
                throw new IllegalStateException("Could not find getter for property:" + property + " in class:" + clazz);
            }
        }
        return sb.toString();
    }

    private static String toBuild(final JavaClazz clazz, final JavaType instanceType) {
        JavaMethod constructor = findBuildableConstructor(clazz);
        StringBuilder sb = new StringBuilder();
        sb.append(instanceType.getSimpleName()).append(" buildable = new ").append(instanceType.getSimpleName()).append("(");
        //TODO: Need to find a cleaner way to check when to cast generic types. Unfortunately, I can't get it working via JavaKind.
        final Set<String> genericTypes = new HashSet<String>();
        for (JavaType genericType : instanceType.getGenericTypes()) {
            genericTypes.add(genericType.getFullyQualifiedName());
        }
        sb.append(StringUtils.join(constructor.getArguments(), new Function<JavaProperty, String>() {
            @Override
            public String apply(JavaProperty item) {
                String prefix = item.getType().isBoolean() ? "is" : "get";
                String cast = genericTypes.contains(item.getType().getFullyQualifiedName()) ? "("+item.getType().getFullyQualifiedName()+")" : "";
                return cast + "fluent." + prefix + item.getNameCapitalized() + "()";
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
            } else if (Decendants.isDescendant(type, property.getType())) {
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
