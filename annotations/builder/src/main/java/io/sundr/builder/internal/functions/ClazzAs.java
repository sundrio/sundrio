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
import io.sundr.FunctionFactory;
import io.sundr.Provider;
import io.sundr.builder.Constants;
import io.sundr.builder.TypedVisitor;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.Block;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.*;
import static io.sundr.builder.internal.utils.BuilderUtils.*;
import static io.sundr.codegen.model.Attributeable.ALSO_IMPORT;
import static io.sundr.codegen.model.Attributeable.INIT;

public class ClazzAs {

    public static final Function<TypeDef, TypeDef> FLUENT_INTERFACE = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            List<Method> methods = new ArrayList<Method>();
            List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
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
                        .addToAttributes(ORIGIN_TYPEDF, item)
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
                boolean isAbstract = isAbstract(unwrapped);

                Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(toAdd);
                toAdd = new PropertyBuilder(toAdd).addToAttributes(DESCENDANTS, descendants).build();

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
                    toAdd = new PropertyBuilder(toAdd).addToAttributes(BUILDABLE_ENABLED, isBuildable).build();
                    methods.add(ToMethod.GETTER.apply(toAdd));
                    methods.add(ToMethod.WITH.apply(toAdd));
                }

                if (isMap) {
                    //
                } else if (isBuildable && !isAbstract) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!isCollection && !isArray) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                        methods.add(ToMethod.EDIT_OR_NEW.apply(toAdd));
                        methods.add(ToMethod.EDIT_OR_NEW_LIKE.apply(toAdd));
                    }
                    methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(toAdd));
                } else if (!descendants.isEmpty()) {
                    for (Property descendant : descendants) {
                        if (isCollection(descendant.getTypeRef())) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        } else {
                            methods.add(ToMethod.WITH.apply(descendant));
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
            List<Method> constructors = new ArrayList<Method>();
            List<Method> methods = new ArrayList<Method>();
            List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
            final List<Property> properties = new ArrayList<Property>();
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
                final boolean isAbstract = isAbstract(unwrapped);

                Property toAdd = new PropertyBuilder(property)
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE))
                        .addToAttributes(ORIGIN_TYPEDF, item)
                        .addToAttributes(OUTER_INTERFACE, fluentType)
                        .addToAttributes(OUTER_CLASS, fluentImplType)
                        .addToAttributes(GENERIC_TYPE_REF, genericType.toReference())
                        .accept(new TypedVisitor<PropertyBuilder>() {
                            public void visit(PropertyBuilder builder) {
                                if (isArray || isList) {
                                    ClassRef listRef =  ARRAY_LIST.toReference(unwrapped);
                                    builder.addToAttributes(INIT, "new " + listRef+ "()")
                                            .addToAttributes(ALSO_IMPORT, Arrays.asList(listRef));
                                } else if (isSet) {
                                    ClassRef setRef = LINKED_HASH_SET.toReference(unwrapped);
                                    builder.addToAttributes(INIT, "new " + setRef + "()")
                                            .addToAttributes(ALSO_IMPORT, Arrays.asList(setRef));
                                } else if (isMap) {
                                    List<TypeRef> arguments = ((ClassRef)property.getTypeRef()).getArguments();
                                    ClassRef mapRef = LINKED_HASH_MAP.toReference(arguments.toArray(new TypeRef[arguments.size()]));
                                    builder.addToAttributes(INIT, "new " + mapRef  + "()")
                                            .addToAttributes(ALSO_IMPORT, Arrays.asList(mapRef));
                                }
                            }
                        }).build();

                Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(toAdd);
                toAdd = new PropertyBuilder(toAdd).addToAttributes(DESCENDANTS, descendants).build();

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

                if (isMap) {
                    properties.add(toAdd);
                } else if (isBuildable && !isAbstract) {
                    methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
                    methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
                    if (!isCollection && !isArray) {
                        methods.add(ToMethod.EDIT_NESTED.apply(toAdd));
                        methods.add(ToMethod.EDIT_OR_NEW.apply(toAdd));
                        methods.add(ToMethod.EDIT_OR_NEW_LIKE.apply(toAdd));
                    }
                    methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(toAdd));
                    nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(toAdd));
                    properties.add(buildableField(toAdd));
                } else if (descendants.isEmpty()) {
                    properties.add(toAdd);
                } else if (!descendants.isEmpty()) {
                    properties.add(buildableField(toAdd));
                    for (Property descendant : descendants) {
                        if (isCollection(descendant.getTypeRef())) {
                            methods.add(ToMethod.ADD_TO_COLLECTION.apply(descendant));
                            methods.add(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
                        }  else {
                            methods.add(ToMethod.WITH.apply(descendant));
                        }
                        methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
                        methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
                        methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(descendant));
                        nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(descendant));
                    }
                } else {
                    properties.add(buildableField(toAdd));
                }
            }

            Method equals = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
                    .addNewArgument().withName("o").withTypeRef(Constants.OBJECT.toReference()).endArgument()
                    .withName("equals")
                    .withBlock(new Block(new Provider<List<Statement>>() {
                        @Override
                        public List<Statement> get() {
                            return toEquals(fluentImplType, properties);
                        }
                    })).build();

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
        public TypeDef apply(final TypeDef item) {
            final Modifier[] modifiers = item.isAbstract()
                    ? new Modifier[]{Modifier.PUBLIC, Modifier.ABSTRACT}
                    : new Modifier[]{Modifier.PUBLIC};

            final TypeDef builderType = TypeAs.BUILDER.apply(item);
            ClassRef instanceRef = item.toInternalReference();

            ClassRef fluent = TypeAs.FLUENT_REF.apply(item);

            List<Method> constructors = new ArrayList<Method>();
            List<Method> methods = new ArrayList<Method>();
            final List<Property> fields = new ArrayList<Property>();

            Property fluentProperty = new PropertyBuilder().withTypeRef(fluent).withName("fluent").build();
            Property validationEnabledProperty = new PropertyBuilder().withTypeRef(BOOLEAN_REF).withName("validationEnabled").build();

            fields.add(fluentProperty);
            fields.add(validationEnabledProperty);

            Method emptyConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withNewBlock()
                    .addNewStringStatementStatement("this(true);")
                    .endBlock()
                    .build();

            Method validationConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                        .withTypeRef(BOOLEAN_REF)
                        .withName("validationEnabled")
                    .and()
                    .withNewBlock()
                    .addToStatements(new StringStatement(new Provider<String>() {
                        @Override
                        public String get() {
                            return hasDefaultConstructor(item) ? "this(new " + item.getName() + "(), validationEnabled);" : "this.fluent = this; this.validationEnabled=validationEnabled;";
                        }
                    }))
                    .endBlock()
                    .build();


            Method fluentConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(fluent)
                    .withName("fluent")
                    .and()
                    .withNewBlock()
                    .addNewStringStatementStatement("this(fluent, true);")
                    .endBlock()
                    .build();

            Method fluentAndValidationConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(fluent)
                    .withName("fluent")
                    .and()
                    .addNewArgument()
                    .withTypeRef(BOOLEAN_REF)
                    .withName("validationEnabled")
                    .and()
                    .withNewBlock()
                    .addToStatements(new StringStatement(new Provider<String>() {
                        @Override
                        public String get() {
                            return hasDefaultConstructor(item) ? "this(fluent, new " + item.getName() + "(), validationEnabled);" : "this.fluent = fluent; this.validationEnabled=validationEnabled;";
                        }
                    }))
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
                    .addNewStringStatementStatement("this(fluent, instance, true);")
                    .endBlock()
                    .build();



            Method instanceAndFluentAndValidationCosntructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(fluent)
                    .withName("fluent")
                    .and()
                    .addNewArgument()
                    .withTypeRef(instanceRef)
                    .withName("instance").and()
                    .addNewArgument()
                    .withTypeRef(BOOLEAN_REF)
                    .withName("validationEnabled")
                    .and()
                    .withBlock(new Block(new Provider<List<Statement>>() {
                        @Override
                        public List<Statement> get() {
                            List<Statement> instanceAndFluentConstructorStatements = toInstanceConstructorBody(item, "fluent");
                            instanceAndFluentConstructorStatements.add(new StringStatement("this.validationEnabled = validationEnabled; "));
                            return instanceAndFluentConstructorStatements;
                        }
                    })).build();

            Method instanceConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(instanceRef)
                    .withName("instance").and()
                    .withNewBlock()
                        .addNewStringStatementStatement("this(instance,true);")
                    .endBlock()
                    .build();


            Method instanceAndValidationConstructor = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addNewArgument()
                    .withTypeRef(instanceRef)
                    .withName("instance").and()
                    .addNewArgument()
                    .withTypeRef(BOOLEAN_REF)
                    .withName("validationEnabled")
                    .and()
                    .withBlock(new Block(new Provider<List<Statement>>() {
                        @Override
                        public List<Statement> get() {
                            List<Statement> statements = toInstanceConstructorBody(item, "this");
                            statements.add(new StringStatement("this.validationEnabled = validationEnabled; "));
                            return statements;
                        }
                    })).build();

            constructors.add(emptyConstructor);
            constructors.add(validationConstructor);
            constructors.add(fluentConstructor);
            constructors.add(fluentAndValidationConstructor);
            constructors.add(instanceAndFluentCosntructor);
            constructors.add(instanceAndFluentAndValidationCosntructor);
            constructors.add(instanceConstructor);
            constructors.add(instanceAndValidationConstructor);

            Method build = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(modifiers))
                    .withReturnType(instanceRef)
                    .withName("build")
                    .withBlock(new Block(new Provider<List<Statement>>() {
                        @Override
                        public List<Statement> get() {
                            return toBuild(item, item);
                        }
                    })).build();
            methods.add(build);

            Method equals = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
                    .addNewArgument().withName("o").withTypeRef(Constants.OBJECT.toReference()).endArgument()
                    .withName("equals")
                    .withBlock(new Block(new Provider<List<Statement>>() {
                        @Override
                        public List<Statement> get() {
                            return toEquals(builderType, fields);
                        }
                    })).build();

            methods.add(equals);

            return new TypeDefBuilder(builderType)
                    .withModifiers(TypeUtils.modifiersToInt(modifiers))
                    .withProperties(fields)
                    .withConstructors(constructors)
                    .withMethods(methods)
                    .build();
        }

    });

    public static final Function<TypeDef, TypeDef> EDITABLE_BUILDER = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(final TypeDef item) {
            final Modifier[] modifiers = item.isAbstract()
                    ? new Modifier[]{Modifier.PUBLIC, Modifier.ABSTRACT}
                    : new Modifier[]{Modifier.PUBLIC};

            final TypeDef editable = EDITABLE.apply(item);
            return new TypeDefBuilder(BUILDER.apply(item)).accept(new TypedVisitor<MethodBuilder>() {
                public void visit(MethodBuilder builder) {
                    if (builder.getName() != null && builder.getName().equals("build")) {
                        builder.withModifiers(TypeUtils.modifiersToInt(modifiers));
                        builder.withReturnType(editable.toInternalReference());
                        builder.withBlock(new Block(new Provider<List<Statement>>() {
                            @Override
                            public List<Statement> get() {
                                return toBuild(editable, editable);
                            }
                        }));
                    }
                }
            }).build();
        }
    });

    public static final Function<TypeDef, TypeDef> EDITABLE = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            Modifier[] modifiers = item.isAbstract()
                    ? new Modifier[]{Modifier.PUBLIC, Modifier.ABSTRACT}
                    : new Modifier[]{Modifier.PUBLIC};

            TypeDef editableType = TypeAs.EDITABLE.apply(item);
            final TypeDef builderType = TypeAs.BUILDER.apply(item);

            List<Method> constructors = new ArrayList<Method>();
            List<Method> methods = new ArrayList<Method>();

            for (Method constructor : item.getConstructors()) {
                constructors.add(superConstructorOf(constructor, editableType));
            }

            Method edit = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(modifiers))
                    .withReturnType(builderType.toInternalReference())
                    .withName("edit")
                    .withNewBlock()
                        .addToStatements(new StringStatement(new Provider<String>() {
                            @Override
                            public String get() {
                                return "return new " + builderType.getName() + "(this);";
                            }
                        }))
                    .endBlock()
                    .build();

            methods.add(edit);

            //We need to treat the editable classes as buildables themselves.
            return CodegenContext.getContext().getDefinitionRepository().register(
                    BuilderContextManager.getContext().getBuildableRepository().register(new TypeDefBuilder(editableType)
                            .withModifiers(TypeUtils.modifiersToInt(modifiers))
                            .withConstructors(constructors)
                            .withMethods(methods)
                            .addToAttributes(BUILDABLE_ENABLED, true)
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

            if (!target.getExtendsList().isEmpty()) {
                target = BuilderContextManager.getContext().getBuildableRepository().getBuildable(target.getExtendsList().iterator().next());
            } else {
                return statements;
            }
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

        BuilderContext context = BuilderContextManager.getContext();
        if (context.isValidationEnabled()) {
            statements.add(new StringStatement(context.getBuilderPackage() + ".ValidationUtils.validate(buildable);"));
        }
        statements.add(new StringStatement("return buildable;"));
        return statements;
    }


    private static List<Statement> toEquals(TypeDef type, Collection<Property> properties) {
        List<Statement> statements = new ArrayList<Statement>();

        String simpleName = type.getName();
        ClassRef superClass = type.getExtendsList().isEmpty() ? TypeDef.OBJECT_REF : type.getExtendsList().iterator().next();
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
            } else if (property.getTypeRef() instanceof ClassRef && Descendants.isDescendant(type, ((ClassRef) property.getTypeRef()).getDefinition())) {
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
        ClassRef builderType = TypeAs.VISITABLE_BUILDER.apply(unwrapped);


        if (isList(classRef)) {
            ClassRef listRef =  ARRAY_LIST.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(LIST.toReference(builderType))
                    .addToAttributes(INIT, " new " + listRef + "()")
                    .addToAttributes(ALSO_IMPORT, alsoImport(property, listRef, builderType))
                    .build();
        } else if (isSet(classRef)) {
            ClassRef setRef = LINKED_HASH_SET.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(SET.toReference(builderType))
                    .addToAttributes(INIT, " new " + setRef+ "()")
                    .addToAttributes(ALSO_IMPORT,  alsoImport(property, setRef, builderType))
                    .build();
        } else {
            return new PropertyBuilder(property).withTypeRef(builderType)
                    .addToAttributes(ALSO_IMPORT, alsoImport(property, builderType))
                    .build();
        }
    }

    private static List<ClassRef> alsoImportAsList(Attributeable attributeable) {
        List<ClassRef> result = new ArrayList<ClassRef>();
        if (attributeable.hasAttribute(ALSO_IMPORT)) {
            result.addAll(attributeable.getAttribute(ALSO_IMPORT));
        }
        return result;
    }

    private static List<ClassRef> alsoImport(Attributeable attributeable, ClassRef... refs) {
        List<ClassRef> result = alsoImportAsList(attributeable);
        for (ClassRef ref : refs) {
            result.add(ref);
        }
        return result;
    }
}
