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

import io.sundr.FunctionFactory;
import io.sundr.Function;
import io.sundr.builder.Constants;
import io.sundr.builder.TypedVisitor;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.Statement;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.ARRAY_LIST;
import static io.sundr.builder.Constants.GENERATED;
import static io.sundr.builder.Constants.GENERIC_TYPE_REF;
import static io.sundr.builder.Constants.INIT;
import static io.sundr.builder.Constants.LINKED_HASH_MAP;
import static io.sundr.builder.Constants.LINKED_HASH_SET;
import static io.sundr.builder.Constants.LIST;
import static io.sundr.builder.Constants.OBJECT;
import static io.sundr.builder.Constants.OUTER_CLASS;
import static io.sundr.builder.Constants.OUTER_INTERFACE;
import static io.sundr.builder.Constants.SET;
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

public class ClazzAs {

    public static final Function<TypeDef, TypeDef> FLUENT_INTERFACE = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            Set<Method> methods = new LinkedHashSet<Method>();
            Set<TypeDef> nestedClazzes = new LinkedHashSet<TypeDef>();
            TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);
            TypeDef fluentImplType = TypeAs.FLUENT_IMPL.apply(item);

            //The generic letter is always the last
            final TypeParamDef genericType = fluentType.getParameters().get(fluentType.getParameters().size() - 1);

            for (Property property : item.getProperties()) {
                final TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF).apply(property.getTypeRef());
                if (property.isStatic()) {
                    continue;
                }
                if (!hasBuildableConstructorWithArgument(item, property) && !hasOrInheritsSetter(item, property)) {
                    continue;
                }

                Property toAdd = new PropertyBuilder(property)
                        .withModifiers(0)
                        .addToAttributes(OUTER_INTERFACE, fluentType)
                        .addToAttributes(OUTER_CLASS, fluentImplType)
                        .addToAttributes(GENERIC_TYPE_REF, genericType.toReference())
                        .build();

                boolean isBuildable = isBuildable(unwrapped);
                boolean isArray = isArray(toAdd.getTypeRef());
                boolean isSet = isSet(toAdd.getTypeRef());
                boolean isList = isList(toAdd.getTypeRef());
                boolean isMap = isMap(toAdd.getTypeRef());
                boolean isCollection = isSet || isList;

                if (isArray) {
                    Property asList = arrayAsList(toAdd);
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
                Set<Property> descendants = Decendants.PROPERTY_BUILDABLE_DECENDANTS.apply(toAdd);

                if (isMap) {
                    //
                } else if (isBuildable) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!isCollection && !isArray) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                    }
                    methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(toAdd));
                } else if (!descendants.isEmpty() && isCollection) {
                    for (Property descendant : descendants) {
                        if (isCollection(descendant.getTypeRef())) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }

                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(descendant));
                    }
                }
            }

            return new TypeDefBuilder(fluentType)
                    .withInnerTypes(nestedClazzes)
                    .withMethods(methods)
                    .build();

        }
    });

    public static final Function<TypeDef, TypeDef> FLUENT_IMPL = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            Set<Method> constructors = new LinkedHashSet<Method>();
            Set<Method> methods = new LinkedHashSet<Method>();
            Set<TypeDef> nestedClazzes = new LinkedHashSet<TypeDef>();
            final Set<Property> properties = new LinkedHashSet<Property>();
            TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);
            final TypeDef fluentImplType = TypeAs.FLUENT_IMPL.apply(item);

            //The generic letter is always the last
            final TypeParamDef genericType = fluentImplType.getParameters().get(fluentImplType.getParameters().size() - 1);

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

            for (final Property property : item.getProperties()) {
                final TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF).apply(property.getTypeRef());

                if (property.isStatic()) {
                    continue;
                }
                if (!hasBuildableConstructorWithArgument(item, property) && !hasOrInheritsSetter(item, property)) {
                    continue;
                }


                final boolean isBuildable = isBuildable(unwrapped);
                final boolean isArray = isArray(property.getTypeRef());
                final boolean isSet = isSet(property.getTypeRef());
                final boolean isList = isList(property.getTypeRef());
                final boolean isMap = isMap(property.getTypeRef());
                final boolean isCollection = isSet || isList;

                Property toAdd = new PropertyBuilder(property)
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE))
                        .addToAttributes(OUTER_INTERFACE, fluentType)
                        .addToAttributes(OUTER_CLASS, fluentImplType)
                        .addToAttributes(GENERIC_TYPE_REF, genericType.toReference())
                        .accept(new TypedVisitor<PropertyBuilder>() {
                            public void visit(PropertyBuilder builder) {
                                if (isArray || isList) {
                                    builder.addToAttributes(INIT, "new " + ARRAY_LIST.toReference(unwrapped) + "()");
                                } else if (isSet) {
                                    builder.addToAttributes(INIT, "new " + LINKED_HASH_SET.toReference(unwrapped) + "()");
                                } else if (isMap) {
                                    List<TypeRef> arguments = ((ClassRef)property.getTypeRef()).getArguments();
                                    builder.addToAttributes(INIT, "new " + LINKED_HASH_MAP.toReference(arguments.toArray(new TypeRef[arguments.size()])) + "()");
                                }
                            }
                        }).build();

                if (isArray) {
                    Property asList = arrayAsList(toAdd);
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
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }

                Set<Property> descendants = Decendants.PROPERTY_BUILDABLE_DECENDANTS.apply(toAdd);
                if (isMap) {
                    properties.add(toAdd);
                } else if (isBuildable) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!isCollection && !isArray) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                    }
                    methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(toAdd));

                    properties.add(buildableField(toAdd));
                } else if (!descendants.isEmpty() && isCollection) {
                    properties.add(toAdd);
                    for (Property descendant : descendants) {
                        if (isCollection(descendant.getTypeRef())) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }
                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(descendant));
                        properties.add(buildableField(descendant));
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
                    .withNewBlock()
                    .withStatements(toEquals(fluentImplType, properties))
                    .endBlock()
                    .build();

            methods.add(equals);

            return new TypeDefBuilder(fluentImplType)
                    .withConstructors(constructors)
                    .withProperties(properties)
                    .withInnerTypes(nestedClazzes)
                    .withMethods(methods)
                    .build();
        }
    });


    public static final Function<TypeDef, TypeDef> BUILDER = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {

            TypeDef builderType = TypeAs.BUILDER.apply(item);
            ClassRef instanceRef = item.toInternalReference();

            ClassRef fluent = TypeAs.FLUENT_REF.apply(item);

            Set<Method> constructors = new LinkedHashSet<Method>();
            Set<Method> methods = new LinkedHashSet<Method>();
            Set<Property> fields = new LinkedHashSet<Property>();

            Property fluentProperty = new PropertyBuilder().withTypeRef(fluent).withName("fluent").build();
            fields.add(fluentProperty);

            Method emptyConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withNewBlock()
                    .addNewStringStatementStatement(hasDefaultConstructor(item) ? "this(new " + item.getName() + "());" : "this.fluent = this;")
                    .endBlock()
                    .build();

            Method fluentConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(fluent)
                    .withName("fluent")
                    .and()
                    .withNewBlock()
                    .addNewStringStatementStatement(hasDefaultConstructor(item) ? "this(fluent, new " + item.getName() + "());" : "this.fluent = fluent;")
                    .endBlock()
                    .build();

            Method instanceAndFluentCosntructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
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
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
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
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(instanceRef)
                    .withName("build")
                    .withNewBlock()
                    .withStatements(toBuild(item, item))
                    .endBlock()
                    .build();

            methods.add(build);

            Method equals = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
                    .addNewArgument().withName("o").withTypeRef(Constants.OBJECT.toReference()).endArgument()
                    .withName("equals")
                    .withNewBlock()
                    .withStatements(toEquals(builderType, fields))
                    .endBlock()
                    .build();

            methods.add(equals);

            return new TypeDefBuilder(builderType)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withProperties(fields)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .build();
        }

    });

    public static final Function<TypeDef, TypeDef> EDITABLE_BUILDER = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(final TypeDef item) {
            final TypeDef editable = EDITABLE.apply(item);
            return new TypeDefBuilder(BUILDER.apply(item)).accept(new TypedVisitor<MethodBuilder>() {
                public void visit(MethodBuilder builder) {
                    if (builder.getName() != null && builder.getName().equals("build")) {
                        builder.withReturnType(editable.toInternalReference());
                        builder.withNewBlock()
                                .withStatements(toBuild(editable, editable))
                                .endBlock();
                    }
                }
            }).build();
        }
    });

    public static final Function<TypeDef, TypeDef> EDITABLE = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            TypeDef editableType = TypeAs.EDITABLE.apply(item);
            TypeDef builderType = TypeAs.BUILDER.apply(item);

            Set<Method> constructors = new LinkedHashSet<Method>();
            Set<Method> methods = new LinkedHashSet<Method>();

            for (Method constructor : item.getConstructors()) {
                constructors.add(superConstructorOf(constructor, editableType));
            }

            Method edit = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(builderType.toInternalReference())
                    .withName("edit")
                    .withNewBlock()
                    .addNewStringStatementStatement("return new " + builderType.getName() + "(this);")
                    .endBlock()
                    .build();

            methods.add(edit);

            //We need to treat the editable classes as buildables themselves.
            return CodegenContext.getContext().getDefinitionRepository().register(
                    BuilderContextManager.getContext().getBuildableRepository().register(new TypeDefBuilder(editableType)
                            .withConstructors(constructors)
                            .withMethods(methods)
                            .addToAttributes(BUILDABLE, true)
                            .addToAttributes(GENERATED, true) // We want to know that its a generated type...
                            .build())
            );
        }
    });

    private static Property arrayAsList(Property property) {
        return new PropertyBuilder(property)
                .withTypeRef(TypeAs.ARRAY_AS_LIST.apply(TypeAs.BOXED_OF.apply(property.getTypeRef())))
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
                String cast = property.getTypeRef() instanceof TypeParamRef ? "(" + property.getTypeRef().toString() + ")" : "";
                statements.add(new StringStatement(new StringBuilder().append(ref).append(".with").append(property.getNameCapitalized()).append("(").append(cast).append("instance.").append(getter.getName()).append("()); ").toString()));
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
            target = BuilderContextManager.getContext().getBuildableRepository().getBuildable(target.getExtendsList().iterator().next());
        }

        return statements;
    }

    private static List<Statement> toBuild(final TypeDef clazz, final TypeDef instanceType) {
        Method constructor = findBuildableConstructor(clazz);
        List<Statement> statements = new ArrayList<Statement>();

        statements.add(new StringStatement(new StringBuilder()
                .append(instanceType.getName()).append(" buildable = new ").append(instanceType.getName()).append("(")
                .append(StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                    public String apply(Property item) {
                        String prefix = isBoolean(item.getTypeRef()) ? "is" : "get";
                        //String cast = genericTypes.contains(item.getTypeRef().getFullyQualifiedName()) ? "("+item.getType().getFullyQualifiedName()+")" : "";
                        return "fluent." + prefix + item.getNameCapitalized() + "()";
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
            target = BuilderContextManager.getContext().getBuildableRepository().getBuildable(target.getExtendsList().iterator().next());
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
                .addNewStringStatementStatement("super(" + StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                    public String apply(Property item) {
                        return item.getName();
                    }
                }, ", ") + ");")
                .endBlock()
                .build();
    }

    private static Property buildableField(Property property) {
        TypeRef typeRef = property.getTypeRef();
        TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF).apply(typeRef);
        ClassRef classRef = (ClassRef) typeRef;
        TypeRef builderType = TypeAs.VISITABLE_BUILDER.apply(unwrapped);

        if (isList(classRef)) {
            return new PropertyBuilder(property).withTypeRef(LIST.toReference(builderType))
                    .addToAttributes(INIT, " new " + ARRAY_LIST.toReference(builderType) + "()")
                    .build();
        } else if (isSet(classRef)) {
            return new PropertyBuilder(property).withTypeRef(SET.toReference(builderType))
                    .addToAttributes(INIT, " new " + LINKED_HASH_SET.toReference(builderType) + "()")
                    .build();
        } else {
            return new PropertyBuilder(property).withTypeRef(builderType).build();
        }
    }
}
