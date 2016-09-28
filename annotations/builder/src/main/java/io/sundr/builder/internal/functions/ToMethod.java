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
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.functions.Singularize;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.Statement;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static io.sundr.builder.Constants.BUILDABLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.DESCENDANTS;
import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.builder.Constants.GENERIC_TYPE_REF;
import static io.sundr.builder.Constants.N_REF;
import static io.sundr.builder.Constants.OUTER_CLASS;
import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.Constants.SIMPLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.T_REF;
import static io.sundr.builder.Constants.VOID;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_COLLECTION;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_LIST;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_MAP;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_SET;
import static io.sundr.builder.internal.functions.TypeAs.ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.VISITABLE_BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.combine;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.builder.internal.utils.BuilderUtils.isAbstract;
import static io.sundr.builder.internal.utils.BuilderUtils.isBoolean;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.builder.internal.utils.BuilderUtils.isList;
import static io.sundr.builder.internal.utils.BuilderUtils.isMap;
import static io.sundr.builder.internal.utils.BuilderUtils.isSet;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;


public class ToMethod {

    private static final String BUILDABLE_ARRAY_GETTER_TEXT = loadResourceQuietly(BUILDABLE_ARRAY_GETTER_SNIPPET);
    private static final String SIMPLE_ARRAY_GETTER_TEXT = loadResourceQuietly(SIMPLE_ARRAY_GETTER_SNIPPET);

    public static final Function<Property, Method> WITH = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            String methodName = "with" + property.getNameCapitalized();
            List<ClassRef> alsoImport = new ArrayList<ClassRef>();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(property)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                    .withStatements(getStatements(property, alsoImport))
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();
        }

        private List<Statement> getStatements(Property property, List<ClassRef> alsoImport) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            String name = property.getName();
            TypeRef type = property.getTypeRef();
            TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            List<Statement> statements = new ArrayList<Statement>();
            Set<Property> descendants = property.getAttributes().containsKey(DESCENDANTS) ? (Set<Property>) property.getAttributes().get(DESCENDANTS) : Collections.EMPTY_SET;

            if (IS_COLLECTION.apply(type) || IS_MAP.apply(type)) {
                statements.add(new StringStatement("this." + name + ".clear();"));
                if (IS_MAP.apply(type)) {
                    statements.add(new StringStatement("if (" + name + " != null) {this." + name + ".putAll(" + name + ");} return (" + returnType + ") this;"));
                } else if (IS_LIST.apply(type) || IS_SET.apply(type)) {
                    String addToMethodName = "addTo" + property.getNameCapitalized();
                    statements.add(new StringStatement("if (" + name + " != null) {for (" + unwraped.toString() + " item : " + name + "){this." + addToMethodName + "(item);}} return (" + returnType + ") this;"));
                }
                return statements;
            } else if (isBuildable(unwraped) && !isAbstract(unwraped)) {
                TypeDef builder = BUILDER.apply(((ClassRef) unwraped).getDefinition());
                String propertyName = property.getName();
                String builderClass = builder.toReference().getName();
                if (property.getAttributes().containsKey(DESCENDANT_OF)) {
                    Property descendantOf = (Property) property.getAttributes().get(DESCENDANT_OF);
                    propertyName = descendantOf.getName();
                }
                statements.add(new StringStatement("if (" + propertyName + "!=null){ this." + propertyName + "= new " + builderClass + "("+name+"); _visitables.add(this." + propertyName + ");} return (" + returnType + ") this;"));
                return statements;
            } else if (!descendants.isEmpty()) {
                for (Property descendant : descendants) {
                    TypeRef dunwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(descendant.getTypeRef());
                    TypeDef builder = BUILDER.apply(((ClassRef) dunwraped).getDefinition());
                    String propertyName = property.getName();
                    String builderClass = builder.toReference().getName();
                    statements.add(new StringStatement("if (" + propertyName + " instanceof " + dunwraped + "){ this." + propertyName + "= new " + builderClass + "((" + dunwraped + ")" + propertyName + "); _visitables.add(this." + propertyName + ");}"));

                    alsoImport.add((ClassRef) dunwraped);
                    alsoImport.add(builder.toInternalReference());
                }
                statements.add(new StringStatement("return (" + returnType + ") this;"));
                return statements;
            }
            statements.add(new StringStatement("this." + property.getName() + "=" + property.getName() + "; return (" + returnType + ") this;"));
            return statements;
        }
    });

    public static final Function<Property, Method> WITH_ARRAY = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;

            String methodName = "with" + property.getNameCapitalized();
            TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            String addToMethodName = "addTo" + property.getNameCapitalized();

            TypeRef arrayType = ARRAY_OF.apply(unwraped);
            Property arrayProperty = new PropertyBuilder(property).withTypeRef(arrayType).build();

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(arrayProperty)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                    .addNewStringStatementStatement("this." + property.getName() + ".clear(); if (" + property.getName() + " != null) {for (" + unwraped.toString() + " item :" + property.getName() + "){ this." + addToMethodName + "(item);}} return (" + returnType + ") this;")
                    .endBlock()
                    .build();
        }

    });

    public static final Function<Property, Method> GETTER = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(final Property property) {
            TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            String prefix = isBoolean(property.getTypeRef()) ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            final List<Statement> statements = new ArrayList<Statement>();

            TreeSet<Property> descendants = new TreeSet<Property>(new Comparator<Property>() {
                public int compare(Property left, Property right) {
                    return left.getName().compareTo(right.getName());
                }
            });
            descendants.addAll(Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property));

            if (isMap(property.getTypeRef())) {
                statements.add(new StringStatement("return this." + property.getName() + ";"));
            } else if (isBuildable(unwrapped)) {
                if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
                    statements.add(new StringStatement("return build(" + property.getName() + ");"));
                } else {
                    statements.add(new StringStatement("return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;"));
                }
            } else if (!descendants.isEmpty()) {
                if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
                    statements.add(new StringStatement("return build(" + property.getName() + ");"));
                } else {
                    statements.add(new StringStatement("return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;"));
                }
            } else {
                statements.add(new StringStatement("return this." + property.getName() + ";"));
            }

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(property.getTypeRef())
                    .withArguments(new Property[]{})
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, Method> GETTER_ARRAY = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            String prefix = isBoolean(property.getTypeRef()) ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            TypeRef type = property.getTypeRef();
            Boolean isBuildable = isBuildable(type);
            TypeRef targetType = isBuildable ? VISITABLE_BUILDER.apply(type) : TypeAs.UNWRAP_ARRAY_OF.apply(type);
            String body = String.format(isBuildable ? BUILDABLE_ARRAY_GETTER_TEXT : SIMPLE_ARRAY_GETTER_TEXT,
                    type.toString(),
                    targetType.toString(),
                    property.getName(),
                    targetType.toString(),
                    property.getName()
            );

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(property.getTypeRef())
                    .withArguments()
                    .withNewBlock()
                    .addNewStringStatementStatement(body)
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, Method> SETTER = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            String methodName = "set" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(VOID)
                    .withArguments()
                    .withNewBlock()
                    .addNewStringStatementStatement("this." + property.getName() + "=" + property.getName() + ";")
                    .endBlock()
                    .build();
        }
    });


    public static final Function<Property, Method> ADD_TO_COLLECTION = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(final Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            final TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef());
            List<ClassRef> alsoImport = new ArrayList<ClassRef>();

            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(unwrapped.withDimensions(1))
                    .build();

            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

            String methodName = "addTo" + property.getNameCapitalized();
            List<Statement> statements = new ArrayList<Statement>();
            Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);
            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                final ClassRef targetType = (ClassRef) unwrapped;
                String propertyName = property.getName();
                if (property.getAttributes().containsKey(Constants.DESCENDANT_OF)) {
                    Object attrValue = property.getAttributes().get(Constants.DESCENDANT_OF);
                    if (attrValue instanceof Property) {
                        propertyName = ((Property)attrValue).getName();
                    }
                }
                String targetClass = targetType.getName();
                parameters.addAll(targetType.getDefinition().getParameters());
                String builderClass = targetClass + "Builder";

                //We need to do it more
                alsoImport.add(TypeAs.BUILDER.apply(targetType.getDefinition()).toInternalReference());
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.add(builder);this." + propertyName + ".add(builder);} return (" + returnType + ")this;"));
            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) unwrapped;
                parameters.addAll(targetType.getDefinition().getParameters());
                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) {" + StringUtils.join(descendants, new Function<Property, String>() {

                    public String apply(Property item) {
                        TypeRef itemRef = TypeAs.combine(UNWRAP_COLLECTION_OF, ARRAY_OF).apply(item.getTypeRef());
                        String className = ((ClassRef) itemRef).getName();
                        String addToMethodName = "addTo" + captializeFirst(item.getName());
                        return "if (item instanceof " + className + "){" + addToMethodName + "((" + className + ")item);}\n";
                    }
                }, " else ") + "} return (" + returnType + ")this;"));
            }  else {
                statements.add(new StringStatement("for (" + unwrapped.toString() + " item : items) {this." + property.getName() + ".add(item);} return (" + returnType + ")this;"));
            }

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(item)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();
        }
    });

    public static final Function<Property, Method> REMOVE_FROM_COLLECTION = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(final Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            final TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef());
            List<ClassRef> alsoImport = new ArrayList<ClassRef>();
            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(unwrapped.withDimensions(1))
                    .build();

            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

            String methodName = "removeFrom" + property.getNameCapitalized();
            List<Statement> statements = new ArrayList<Statement>();

            Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);
            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                final ClassRef targetType = (ClassRef) unwrapped;
                String propertyName = property.getName();
                if (property.getAttributes().containsKey(Constants.DESCENDANT_OF)) {
                    Object attrValue = property.getAttributes().get(Constants.DESCENDANT_OF);
                    if (attrValue instanceof Property) {
                        propertyName = ((Property)attrValue).getName();
                    }
                }
                String targetClass = targetType.getName();
                parameters.addAll(targetType.getDefinition().getParameters());
                String builderClass = targetClass + "Builder";

                //We need to do it more elegantly
                alsoImport.add(TypeAs.BUILDER.apply(targetType.getDefinition()).toInternalReference());
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.remove(builder);this." + propertyName + ".remove(builder);} return (" + returnType + ")this;"));
            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) unwrapped;
                parameters.addAll(targetType.getDefinition().getParameters());
                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) {" + StringUtils.join(descendants, new Function<Property, String>() {
                    public String apply(Property item) {
                        TypeRef itemRef = TypeAs.combine(UNWRAP_COLLECTION_OF, ARRAY_OF).apply(item.getTypeRef());
                        String className = ((ClassRef) itemRef).getName();
                        String removeFromMethodName = "removeFrom" + captializeFirst(item.getName());
                        return "if (item instanceof " + className + "){" + removeFromMethodName + "((" + className + ")item);}\n";
                    }
                }, " else ") + "} return (" + returnType + ")this;"));

            } else {
                statements.add(new StringStatement("for (" + unwrapped.toString() + " item : items) {this." + property.getName() + ".remove(item);} return (" + returnType + ")this;"));
            }

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withParameters(parameters)
                    .withReturnType(returnType)
                    .withArguments(item)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, Method> ADD_MAP_TO_MAP = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            ClassRef mapType = (ClassRef) property.getTypeRef();
            Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
            String methodName = "addTo" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(mapProperty)
                    .withNewBlock()
                    .addNewStringStatementStatement("if(map != null) { this." + property.getName() + ".putAll(map);} return (" + returnType + ")this;")
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, Method> ADD_TO_MAP = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            ClassRef mapType = (ClassRef) property.getTypeRef();
            TypeRef keyType = mapType.getArguments().get(0);
            TypeRef valueType = mapType.getArguments().get(1);


            Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
            Property valueProperty = new PropertyBuilder().withName("value").withTypeRef(valueType).build();
            String methodName = "addTo" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(new Property[]{keyProperty, valueProperty})
                    .withNewBlock()
                    .addNewStringStatementStatement("if(key != null && value != null) {this." + property.getName() + ".put(key, value);} return (" + returnType + ")this;")
                    .endBlock()
                    .build();
        }
    });


    public static final Function<Property, Method> REMOVE_MAP_FROM_MAP = FunctionFactory.cache(new Function<Property, Method>() {

        public Method apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            TypeRef mapType = property.getTypeRef();
            Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
            String methodName = "removeFrom" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(mapProperty)
                    .withNewBlock()
                    .addNewStringStatementStatement("if(map != null) { for(Object key : map.keySet()) {this." + property.getName() + ".remove(key);}} return (" + returnType + ")this;")
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, Method> REMOVE_FROM_MAP = FunctionFactory.cache(new Function<Property, Method>() {

        public Method apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            ClassRef mapType = (ClassRef) property.getTypeRef();
            TypeRef keyType = mapType.getArguments().get(0);

            Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
            String methodName = "removeFrom" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(returnType)
                    .withArguments(keyProperty)
                    .withNewBlock()
                    .addNewStringStatementStatement("if(key != null) {this." + property.getName() + ".remove(key);} return (" + returnType + ")this;")
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, Method> WITH_NEW_NESTED = new Function<Property, Method>() {
        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

            List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef arg : baseType.getArguments()) {
                typeArguments.add(arg);
            }
            typeArguments.add(returnType);


            ClassRef rewraped = nestedType.toReference(typeArguments.toArray(new TypeRef[typeArguments.size()]));
            ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments.toArray(new TypeRef[typeArguments.size()]));

            boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
            String prefix = isCollection ? "addNew" : "withNew";

            prefix += TypeUtils.fullyQualifiedNameDiff(baseType);
            String methodName = prefix + captializeFirst(isCollection
                    ? Singularize.FUNCTION.apply(property.getName())
                    : property.getName());

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .withNewBlock()
                    .addNewStringStatementStatement("return new " + rewrapedImpl.getName() + "();")
                    .endBlock()
                    .build();

        }
    };

    public static final Function<Property, Set<Method>> WITH_NESTED_INLINE = new Function<Property, Set<Method>>() {
        public Set<Method> apply(Property property) {
            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            Set<Method> result = new LinkedHashSet<Method>();
            TypeRef unwrappedType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            TypeDef baseType = BuilderContextManager.getContext().getBuildableRepository().getBuildable(unwrappedType);

            for (Method constructor : getInlineableConstructors(property)) {
                boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
                String ownPrefix = isCollection ? "addNew" : "withNew";

                ownPrefix += BuilderUtils.fullyQualifiedNameDiff(property);
                String ownName = ownPrefix + captializeFirst(isCollection
                        ? Singularize.FUNCTION.apply(property.getName())
                        : property.getName());

                String delegatePrefix = IS_COLLECTION.apply(property.getTypeRef()) ? "addTo" : "with";
                String delegateName = delegatePrefix + captializeFirst(property.getName());

                String args = StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
                    public String apply(Property item) {
                        return item.getName();
                    }
                }, ", ");


                result.add(new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withReturnType(returnType)
                        .withArguments(constructor.getArguments())
                        .withName(ownName)
                        .withParameters(baseType.getParameters())
                        .withNewBlock()
                        .addNewStringStatementStatement("return (" + returnType + ")" + delegateName + "(new " + baseType.getName() + "(" + args + "));")
                        .endBlock()
                        .build());
            }

            return result;
        }
    };

    public static final Function<Property, Method> EDIT_OR_NEW = new Function<Property, Method>() {
        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) property.getTypeRef();
            ClassRef builderType = TypeAs.SHALLOW_BUILDER.apply(baseType.getDefinition()).toReference();

            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);

            List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef ignore : baseType.getArguments()) {
                typeArguments.add(Q);
            }
            typeArguments.add(returnType);
            ClassRef rewraped = nestedType.toReference(typeArguments);

            String prefix = "editOrNew";
            String methodNameBase = captializeFirst(property.getName());
            String methodName = prefix + methodNameBase;

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .withNewBlock()
                    .addNewStringStatementStatement("return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null ? get" + methodNameBase + "(): new " + builderType.getName() + "().build());")
                    .endBlock()
                    .build();

        }
    };

    public static final Function<Property, Method> EDIT_OR_NEW_LIKE = new Function<Property, Method>() {
        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) property.getTypeRef();
            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);

            List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef ignore : baseType.getArguments()) {
                typeArguments.add(Q);
            }
            typeArguments.add(returnType);
            ClassRef rewraped = nestedType.toReference(typeArguments);

            String prefix = "editOrNew";
            String suffix = "Like";
            String methodNameBase = captializeFirst(property.getName());
            String methodName = prefix + methodNameBase + suffix;

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .addNewArgument()
                    .withName("item")
                    .withTypeRef(baseType)
                    .endArgument()
                    .withNewBlock()
                    .addNewStringStatementStatement("return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null ? get"+methodNameBase+"(): item);")
                    .endBlock()
                    .build();

        }
    };

    public static final Function<Property, Method> WITH_NEW_LIKE_NESTED = new Function<Property, Method>() {
        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

            List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef ignore : baseType.getArguments()) {
                typeArguments.add(Q);
            }
            typeArguments.add(returnType);

            ClassRef rewraped = nestedType.toReference(typeArguments);
            ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

            boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());

            String prefix = isCollection ? "addNew" : "withNew";
            String suffix = "Like";
            String methodName = prefix + captializeFirst(isCollection
                    ? Singularize.FUNCTION.apply(property.getName())
                    : property.getName()) + suffix;

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .addNewArgument()
                    .withName("item")
                    .withTypeRef(baseType)
                    .endArgument()
                    .withNewBlock()
                    .addNewStringStatementStatement("return new " + rewrapedImpl.getName() + "(item);")
                    .endBlock()
                    .build();

        }
    };

    public static final Function<Property, Method> EDIT_NESTED =new Function<Property, Method>() {
        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.getAttributes().containsKey(GENERIC_TYPE_REF) ? (TypeRef) property.getAttributes().get(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

            Set<TypeParamDef> parameters = new LinkedHashSet<TypeParamDef>(baseType.getDefinition().getParameters());
            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef ignore : baseType.getArguments()) {
                typeArguments.add(Q);
            }
            typeArguments.add(returnType);

            ClassRef rewraped = nestedType.toReference(typeArguments);
            ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

            String prefix = "edit";
            prefix += BuilderUtils.fullyQualifiedNameDiff(property);
            String methodNameBase = captializeFirst(property.getName());
            String methodName = prefix + methodNameBase;

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .withNewBlock()
                    .addNewStringStatementStatement("return withNew" + methodNameBase + "Like(get" + methodNameBase + "());")
                    .endBlock()
                    .build();

        }
    };

    public static final Function<Property, Method> AND = new Function<Property, Method>() {
        public Method apply(Property property) {
            String classPrefix = getClassPrefix(property);
            String prefix = IS_COLLECTION.apply(property.getTypeRef()) ? "addTo" : "with";
            String withMethodName = prefix + captializeFirst(property.getName());
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(N_REF)
                    .withName("and")
                    .withNewBlock()
                    .addNewStringStatementStatement("return (N) " + classPrefix + withMethodName + "(builder.build());")
                    .endBlock()
                    .build();

        }

        private String getClassPrefix(Property property) {
            Object memberOf = property.getAttributes().get(OUTER_CLASS);
            if (memberOf instanceof TypeDef) {
                return ((TypeDef) memberOf).getName() + ".this.";
            } else return "";
        }

    };

    public static final Function<Property, Method> END = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            String methodName = "end" + BuilderUtils.fullyQualifiedNameDiff(property) + captializeFirst(IS_COLLECTION.apply(property.getTypeRef())
                    ? Singularize.FUNCTION.apply(property.getName())
                    : property.getName());

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(N_REF)
                    .withName(methodName)
                    .withNewBlock()
                    .addNewStringStatementStatement("return and();")
                    .endBlock()
                    .build();
        }
    });

}
