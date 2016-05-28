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
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.Statement;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.Constants.OBJECT;
import static io.sundr.builder.Constants.REPLACEABLE;
import static io.sundr.builder.internal.utils.BuilderUtils.BUILDABLE;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.findGetter;
import static io.sundr.builder.internal.utils.BuilderUtils.hasBuildableConstructorWithArgument;
import static io.sundr.builder.internal.utils.BuilderUtils.hasDefaultConstructor;
import static io.sundr.builder.internal.utils.BuilderUtils.hasOrInheritsSetter;
import static io.sundr.builder.internal.utils.BuilderUtils.hasSetter;
import static io.sundr.builder.internal.utils.BuilderUtils.isArray;
import static io.sundr.builder.internal.utils.BuilderUtils.isBoolean;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.builder.internal.utils.BuilderUtils.isCollection;
import static io.sundr.builder.internal.utils.BuilderUtils.isList;
import static io.sundr.builder.internal.utils.BuilderUtils.isMap;
import static io.sundr.builder.internal.utils.BuilderUtils.isSet;
import static io.sundr.codegen.utils.TypeUtils.classRefOf;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public enum ClazzAs implements Function<TypeDef, TypeDef> {

    FLUENT_INTERFACE {
        public TypeDef apply(TypeDef item) {
            Set<Method> methods = new LinkedHashSet<Method>();
            Set<TypeDef> nestedClazzes = new LinkedHashSet<TypeDef>();
            TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);

            //The generic letter is always the last
            final TypeParamDef genericTypeLetter = fluentType.getParameters().get(fluentType.getParameters().size() - 1);

            for (Property property : item.getProperties()) {
                if (property.isStatic()) {
                    continue;
                }
                if (!hasBuildableConstructorWithArgument(item, property) && !hasOrInheritsSetter(item, property) ) {
                    continue;
                }

                Property toAdd = new PropertyBuilder(property)
                        .withModifiers(0)
                        .build();

                boolean isBuildable = isBuildable(toAdd.getTypeRef());
                boolean isArray =  isArray(toAdd.getTypeRef());
                boolean isSet =  isSet(toAdd.getTypeRef());
                boolean isList =  isList(toAdd.getTypeRef());
                boolean isMap =  isMap(toAdd.getTypeRef());
                boolean isCollection = isSet || isList;

                if (isArray) {
                    Property asList = arrayAsList(toAdd, isBuildable);
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                    methods.add(ToMethod.GETTER_ARRAY.apply(toAdd));
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(asList));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
                    toAdd = asList;
                } else if (isSet || isList) {
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                } else if (isMap) {
                    methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.ADD_MAP_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_MAP_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                } else {
                    toAdd = new PropertyBuilder(toAdd).addToAttributes(BUILDABLE, isBuildable).build();
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }
                Set<Property> descendants = Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(toAdd);

                if (isMap) {
                    //
                } else if (isBuildable) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (isCollection && !isArray(toAdd.getTypeRef())) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                    }
                    methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(new PropertyBuilder(toAdd).addToAttributes(MEMBER_OF, fluentType).build()));
                } else if (!descendants.isEmpty() && isCollection) {
                    for (Property descendant : descendants) {
                        if (isCollection(descendant.getTypeRef())) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }
                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(new PropertyBuilder(descendant).addToAttributes(MEMBER_OF, fluentType).build()));
                    }
                }
            }

            return new TypeDefBuilder(fluentType)
                    .withInnerTypes(nestedClazzes)
                    .withMethods(methods)
                    //The return type of builder methods is always T, we need to fix that.
                    .accept(new Visitor<MethodBuilder>() {
                        public void visit(MethodBuilder builder) {
                            if (builder.getReturnType().equals(Constants.T_REF) && builder.getReturnType().getAttributes().containsKey(REPLACEABLE)) {
                                builder.withReturnType(genericTypeLetter.toReference());
                            } else if (builder.getReturnType() instanceof ClassRef && Arrays.asList(((ClassRef)builder.getReturnType()).getArguments()).contains(Constants.T_REF)) {
                                List<TypeRef> generics = new ArrayList<TypeRef>();

                                for (TypeRef g : ((ClassRef)builder.getReturnType()).getArguments()) {
                                    if (g.equals(Constants.T) && g.getAttributes().containsKey(REPLACEABLE)) {
                                        generics.add(genericTypeLetter.toReference());
                                    } else {
                                        generics.add(g);
                                    }
                                }
                                TypeRef updatedReturnType = new ClassRefBuilder(((ClassRef)builder.getReturnType())).withArguments(generics).build();
                                builder.withReturnType(updatedReturnType);
                            }
                        }
                    }).build();
        }
    }, FLUENT_IMPL {

        public TypeDef apply(TypeDef item) {
            Set<Method> constructors = new LinkedHashSet<Method>();
            Set<Method> methods = new LinkedHashSet<Method>();
            Set<TypeDef> nestedClazzes = new LinkedHashSet<TypeDef>();
            Set<Property> properties = new LinkedHashSet<Property>();
            final TypeDef fluentType = TypeAs.FLUENT_IMPL.apply(item);

            //The generic letter is always the last
            final TypeParamDef genericTypeLetter = fluentType.getParameters().get(fluentType.getParameters().size() - 1);

            Method emptyConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .build();

            Method instanceConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(item.toReference())
                    .withName("instance").and()
                    .withNewBlock()
                        .withStatements(toInstanceConstructorBody(item, ""))
                    .endBlock()
                    .build();

            constructors.add(emptyConstructor);
            constructors.add(instanceConstructor);

            for (Property property : item.getProperties()) {
                if (property.isStatic()) {
                    continue;
                }
                if (!hasBuildableConstructorWithArgument(item, property) && !hasOrInheritsSetter(item, property) ) {
                    continue;
                }

                Property toAdd = new PropertyBuilder(property)
                        .withModifiers(0)
                        .build();

                boolean isBuildable = isBuildable(toAdd.getTypeRef());
                boolean isArray =  isArray(toAdd.getTypeRef());
                boolean isSet =  isSet(toAdd.getTypeRef());
                boolean isList =  isList(toAdd.getTypeRef());
                boolean isMap =  isMap(toAdd.getTypeRef());
                boolean isCollection = isSet || isList;

                if (isArray) {
                    Property asList = arrayAsList(toAdd, isBuildable);
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                    methods.add(ToMethod.GETTER_ARRAY.apply(toAdd));
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(asList));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
                    toAdd = asList;
                } else if (isSet || isList) {
                    methods.add(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                    methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
                } else if (isMap) {
                    methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.ADD_MAP_TO_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.REMOVE_MAP_FROM_MAP.apply(toAdd));
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                } else {
                    toAdd = new PropertyBuilder(toAdd).addToAttributes(BUILDABLE, isBuildable).build();
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }


                Set<Property> descendants = Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(toAdd);
                if (isMap) {
                    properties.add(toAdd);
                } else if (isBuildable) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!isCollection && !isArray) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                    }
                    methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(new PropertyBuilder(toAdd).addToAttributes(MEMBER_OF, fluentType).build()));

                    ClassRef classRef = (ClassRef) toAdd.getTypeRef();
                    TypeRef builderType = TypeAs.VISITABLE_BUILDER.apply(classRef.getDefinition());
                    if (isCollection) {
                        builderType = classRefOf(classRef.getDefinition(), builderType);
                    }

                    properties.add(new PropertyBuilder(toAdd).withTypeRef(builderType).build());
                } else if (!descendants.isEmpty() && isCollection) {
                    properties.add(toAdd);

                    for (Property descendant : descendants) {
                        if (isCollection(descendant.getTypeRef())) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }
                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethods.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(new PropertyBuilder(descendant).addToAttributes(MEMBER_OF, fluentType).build()));

                        ClassRef classRef = (ClassRef) descendant.getTypeRef();
                        TypeRef builderType = TypeAs.VISITABLE_BUILDER.apply(classRef.getDefinition());
                        if (isCollection(descendant.getTypeRef())) {
                            builderType = classRefOf(classRef.getDefinition(), builderType);
                        }

                        properties.add(new PropertyBuilder(descendant).withTypeRef(builderType).build());

                    }

                } else {
                    properties.add(toAdd);
                }
            }

            Method equals = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
                    .addNewArgument().withName("o").withTypeRef(Constants.OBJECT.toReference()).endArgument()
                    .withName("equals")
                    .addToAttributes(BODY, toEquals(fluentType, properties))
                    .build();

            methods.add(equals);

            return new TypeDefBuilder(fluentType)
                    .withConstructors(constructors)
                    .withProperties(properties)
                    .withInnerTypes(nestedClazzes)
                    .withMethods(methods)
                    //The return type of builder methods is always T, we need to fix that.
                    .accept(new Visitor<MethodBuilder>() {
                        public void visit(MethodBuilder builder) {
                            if (builder.getReturnType() != null && builder.getReturnType().equals(Constants.T_REF) && builder.getReturnType().getAttributes().containsKey(REPLACEABLE)) {
                                builder.withReturnType(genericTypeLetter.toReference());
                                String body = (String) builder.getAttributes().get(BODY);
                                if (body != null) {
                                    body = body.replaceAll("\\(T\\)", "\\(" + genericTypeLetter.getName() + "\\)");
                                    builder.getAttributes().put(BODY, body);
                                }
                            } else if (builder.getReturnType() instanceof ClassRef && Arrays.asList(((ClassRef)builder.getReturnType()).getArguments()).contains(Constants.T_REF)) {
                                List<TypeRef> generics = new ArrayList<TypeRef>();
                                for (TypeRef g : ((ClassRef) builder.getReturnType()).getArguments()) {
                                    if (g.equals(Constants.T) && g.getAttributes().containsKey(REPLACEABLE)) {
                                        generics.add(genericTypeLetter.toReference());
                                    } else {
                                        generics.add(g);
                                    }
                                }
                                String body = (String) builder.getAttributes().get(BODY);
                                if (body != null) {
                                    body = body.replaceAll("<T>", "<" + genericTypeLetter.toString() + ">");
                                    builder.getAttributes().put(BODY, body);
                                }
                                TypeRef updatedReturnType = new ClassRefBuilder(((ClassRef)builder.getReturnType())).withArguments(generics).build();
                                builder.withReturnType(updatedReturnType);
                            }
                        }
                    }).build();
        }
    }, BUILDER {
        public TypeDef apply(TypeDef item) {
            TypeDef builderType = TypeAs.BUILDER.apply(item);

            ClassRef instanceRef = item.toInternalReference();

            ClassRef fluent = TypeAs.FLUENT_REF.apply(item);
            TypeRef builderReturnType = builderType.toReference(item.toReference());
            Set<Method> constructors = new LinkedHashSet<Method>();
            Set<Method> methods = new LinkedHashSet<Method>();
            Set<Property> fields = new LinkedHashSet<Property>();

            Property fluentProperty = new PropertyBuilder().withTypeRef(fluent).withName("fluent").build();
            fields.add(fluentProperty);

            Method emptyConstructor = new MethodBuilder()
                    .withReturnType(builderReturnType)
                    .withNewBlock()
                        .addNewStringStatementStatement(hasDefaultConstructor(item) ? "this(new " + item.getName() + "());" : "this.fluent = this;")
                    .endBlock()
                    .build();

            Method fluentConstructor = new MethodBuilder()
                    .addNewArgument()
                    .withTypeRef(fluent)
                    .withName("fluent")
                    .and()
                    .withNewBlock()
                        .addNewStringStatementStatement(hasDefaultConstructor(item) ? "this(fluent, new " + item.getName() + "());" : "this.fluent = fluent;")
                    .endBlock()
                    .build();

            Method instanceAndFluentCosntructor = new MethodBuilder()
                    .addNewArgument()
                    .withTypeRef(fluent)
                    .withName("fluent")
                    .and()
                    .addNewArgument()
                    .withTypeRef(instanceRef)
                    .withName("instance").and()
                    .withNewBlock()
                        .withStatements(toInstanceConstructorBody(item, "fluent"))
                    .endBlock()
                    .build();

            Method instanceConstructor = new MethodBuilder()
                    .addNewArgument()
                    .withTypeRef(instanceRef)
                    .withName("instance").and()
                    .withNewBlock()
                        .withStatements(toInstanceConstructorBody(item, "this"))
                    .endBlock()
                    .build();

            constructors.add(emptyConstructor);
            constructors.add(fluentConstructor);
            constructors.add(instanceAndFluentCosntructor);
            constructors.add(instanceConstructor);

            Method build = new MethodBuilder()
                    .withReturnType(instanceRef)
                    .withName("build")
                    .withNewBlock()
                        .withStatements(toBuild(item, item))
                    .endBlock()
                    .build();

            methods.add(build);

            Method equals = new MethodBuilder()
                    .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
                    .addNewArgument().withName("o").withTypeRef(Constants.OBJECT.toReference()).endArgument()
                    .withName("equals")
                    .withNewBlock()
                        .withStatements(toEquals(builderType, fields))
                    .endBlock()
                    .build();

            methods.add(equals);

            return new TypeDefBuilder(builderType)
                    .withProperties(fields)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .build();
        }

    }, EDITABLE_BUILDER {
        public TypeDef apply(TypeDef item) {
            TypeDef builder = BUILDER.apply(item);
            Set<Method> methods = new LinkedHashSet<Method>();
            for (Method m : builder.getMethods()) {
                if (m.getName().equals("build")) {

                    TypeDef editable = EDITABLE.apply(item);
                    methods.add(new MethodBuilder()
                            .withReturnType(editable.toInternalReference())
                            .withName("build")
                            .addToAttributes(BODY, toBuild(editable, editable))
                            .build());
                } else {
                    methods.add(m);
                }
            }
            return new TypeDefBuilder(builder)
                    .withMethods(methods)
                    .build();

        }
    }, EDITABLE {
        public TypeDef apply(TypeDef item) {
            TypeDef editableType = TypeAs.EDITABLE.apply(item);
            TypeDef builderType = TypeAs.BUILDER.apply(item);

            Set<Method> constructors = new LinkedHashSet<Method>();
            Set<Method> methods = new LinkedHashSet<Method>();

            for (Method constructor : item.getConstructors()) {
                constructors.add(superConstructorOf(constructor, editableType));
            }

            Method edit = new MethodBuilder()
                    .withReturnType(builderType.toReference())
                    .withName("edit")
                    .addToAttributes(BODY, "return new " + builderType.getName() + "(this);")
                    .build();

            methods.add(edit);

            return new TypeDefBuilder(editableType)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .addToAttributes(BUILDABLE, true)
                    .build();
        }
    };

    private static Property arrayAsList(Property property, boolean buildable) {
        return new PropertyBuilder(property)
                .withTypeRef(TypeAs.ARRAY_AS_LIST.apply(TypeAs.BOXED_OF.apply(property.getTypeRef())))
                .addToAttributes(BUILDABLE, buildable)
                .build();
    }

    private static List<Statement> toInstanceConstructorBody(TypeDef clazz, String fluent) {
        Method constructor = findBuildableConstructor(clazz);
        List<Statement> statements = new ArrayList<Statement>();
        String ref = fluent;

        //We may use a reference to fluent or we may use directly "this". So we need to check.
        if (fluent != null && !fluent.isEmpty()) {
            statements.add(new StringStatement("this.fluent = " + fluent + "; "));
        } else {
            ref = "this";
        }

        for (Property property : constructor.getArguments()) {
            Method getter = findGetter(clazz, property);
            if (getter != null) {
                statements.add(new StringStatement(new StringBuilder().append(ref).append(".with").append(property.getNameCapitalized()).append("(instance.").append(getter.getName()).append("()); ").toString()));
            } else {
                throw new IllegalStateException("Could not find getter for property:" + property + " in class:" + clazz);
            }
        }

        TypeDef target = clazz;
        //Iterate parent objects and check for properties with setters but not ctor arguments.
        while (target != null && !OBJECT.equals(target) && BuilderUtils.isBuildable(target)) {
            for (Property property : target.getProperties()) {
                if (!hasBuildableConstructorWithArgument(target, property) && hasSetter(target, property)) {
                    String withName = "with" + property.getNameCapitalized();
                    String getterName = BuilderUtils.findGetter(target, property).getName();
                    statements.add(new StringStatement(new StringBuilder().append(ref).append(".").append(withName).append("(instance.").append(getterName).append("());\n").toString()));
                }
            }
            target = BuilderContextManager.getContext().getRepository().getBuildable(target.getExtendsList().iterator().next());
        }

        return statements;
    }

    private static List<Statement> toBuild(final TypeDef clazz, final TypeDef instanceType) {
        Method constructor = findBuildableConstructor(clazz);
        List<Statement> statements = new ArrayList<Statement>();

        statements.add(new StringStatement(new StringBuilder().toString()));

        statements.add(new StringStatement(new StringBuilder()
                .append(instanceType.getName()).append(" buildable = new ").append(instanceType.getName()).append("(")
                .append(StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                    public String apply(Property item) {
                        String prefix = isBoolean(item.getTypeRef()) ? "is" : "get";
                        //String cast = genericTypes.contains(item.getTypeRef().getFullyQualifiedName()) ? "("+item.getType().getFullyQualifiedName()+")" : "";
                        return  "fluent." + prefix + item.getNameCapitalized() + "()";
                    }
                }, ","))
                .append(");")
                .toString()));


        TypeDef target = clazz;

        //Iterate parent objects and check for properties with setters but not ctor arguments.
        while (target != null && !OBJECT.equals(target) && BuilderUtils.isBuildable(target)) {
            for (Property property : target.getProperties()) {
                if (!hasBuildableConstructorWithArgument(target, property) && hasSetter(target, property)) {
                    String setterName = "set" + property.getNameCapitalized();
                    String getterName = BuilderUtils.findGetter(target, property).getName();
                    statements.add(new StringStatement(new StringBuilder()
                            .append("buildable.").append(setterName).append("(fluent.").append(getterName).append("());")
                            .toString()));

                }
            }
            target = BuilderContextManager.getContext().getRepository().getBuildable(target.getExtendsList().iterator().next());
        }

        statements.add(new StringStatement("validate(buildable);"));
        statements.add(new StringStatement("return buildable;"));
        return statements;
    }


    private static List<Statement> toEquals(TypeDef type, Collection<Property> properties) {
        List<Statement> statements = new ArrayList<Statement>();

        String simpleName = type.getName();
        ClassRef superClass = type.getExtendsList().iterator().next();
        statements.add(new StringStatement("if (this == o) return true;"));
        statements.add(new StringStatement("if (o == null || getClass() != o.getClass()) return false;"));

        //If base fluent is the superclass just skip.
        if (!Constants.BASE_FLUENT.getFullyQualifiedName().equals(superClass.getDefinition().getFullyQualifiedName())) {
            statements.add(new StringStatement("if (!super.equals(o)) return false;"));
        }
        statements.add(new StringStatement(new StringBuilder().append(simpleName).append(" that = (").append(simpleName).append(") o;").toString()));

        for (Property property : properties) {
            String name = property.getName();
            if (BuilderUtils.isPrimitive(property.getTypeRef())) {
                statements.add(new StringStatement(new StringBuilder().append("if (").append(name).append(" != ").append("that.").append(name).append(") return false;").toString()));
            } else if (property.getTypeRef() instanceof ClassRef && Decendants.isDescendant(type, ((ClassRef) property.getTypeRef()).getDefinition())) {
                statements.add(new StringStatement(new StringBuilder()
                        .append("if (").append(name).append(" != null &&").append(name).append(" != this ? !").append(name).append(".equals(that.").append(name).append(") :")
                        .append("that.").append(name).append(" != null &&").append(name).append(" != this ) return false;").append("\n")
                        .toString()));
            } else {
                statements.add(new StringStatement(new StringBuilder().append("if (").append(name).append(" != null ? !").append(name).append(".equals(that.").append(name).append(") :")
                        .append("that.").append(name).append(" != null) return false;").toString()));

            }
        }

        statements.add(new StringStatement("return true;"));
        return statements;
    }

    private static Method superConstructorOf(Method constructor, TypeDef constructorType) {
        return new MethodBuilder(constructor)
                .withReturnType(constructorType.toReference())
                .withNewBlock()
                    .addNewStringStatementStatement( "super(" + StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                        public String apply(Property item) {
                            return item.getName();
                        }
                    }, ", ") + ");")
                .endBlock()
                .build();
    }
}
