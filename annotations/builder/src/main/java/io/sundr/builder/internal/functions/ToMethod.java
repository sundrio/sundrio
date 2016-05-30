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
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static io.sundr.builder.Constants.BUILDABLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.MEMBER_OF;
import static io.sundr.builder.Constants.N_REF;
import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.Constants.SIMPLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.Constants.T_REF;
import static io.sundr.builder.Constants.VOID;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_COLLECTION;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_LIST;
import static io.sundr.builder.internal.functions.CollectionTypes.IS_SET;
import static io.sundr.builder.internal.functions.TypeAs.ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.VISITABLE_BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.combine;
import static io.sundr.builder.internal.utils.BuilderUtils.isBoolean;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.builder.internal.utils.BuilderUtils.isList;
import static io.sundr.builder.internal.utils.BuilderUtils.isMap;
import static io.sundr.builder.internal.utils.BuilderUtils.isSet;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
import static io.sundr.codegen.utils.StringUtils.singularize;
import static io.sundr.codegen.utils.TypeUtils.classRefOf;


public enum ToMethod implements Function<Property, Method> {

    WITH {
        public Method apply(Property property) {
            String methodName = "with" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(property)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                        .withStatements(getStementes(property))
                    .endBlock()
                    .build();
        }

        private List<Statement> getStementes(Property property) {
            String name = property.getName();
            TypeRef type = property.getTypeRef();
            TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());

            List<Statement> statements = new ArrayList<Statement>();

            if (IS_COLLECTION.apply(type)) {
                String className = ((ClassRef)type).getDefinition().getName();
                statements.add(new StringStatement("this." + name + ".clear();"));
                if (className.contains("Map")) {
                    statements.add(new StringStatement("if (" + name + " != null) {this." + name + ".putAll(" + name + ");} return (T) this;"));
                } else if (IS_LIST.apply(type) || IS_SET.apply(type)) {
                    String addToMethodName = "addTo" + property.getNameCapitalized();
                    statements.add(new StringStatement("if (" + name + " != null) {for (" + unwraped.toString() + " item : " + name + "){this." + addToMethodName + "(item);}} return (T) this;"));
                }
                return statements;
            } else if (isBuildable(unwraped)) {
                TypeDef builder = BUILDER.apply(((ClassRef)unwraped).getDefinition());
                String propertyName = property.getName();
                String builderClass = builder.getName();
                statements.add(new StringStatement("if (" + propertyName + "!=null){ this." + propertyName + "= new " + builderClass + "(" + propertyName + "); _visitables.add(this." + propertyName + ");} return (T) this;"));
                return statements;
            }
            statements.add(new StringStatement("this." + property.getName() + "=" + property.getName() + "; return (T) this;"));
            return statements;
        }

    }, WITH_ARRAY {
        public Method apply(Property property) {
            String methodName = "with" + property.getNameCapitalized();
            TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            String addToMethodName = "addTo" + property.getNameCapitalized();

            TypeRef arrayType = ARRAY_OF.apply(unwraped);
            Property arrayProperty = new PropertyBuilder(property).withTypeRef(arrayType).build();

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(arrayProperty)
                    .withNewBlock()
                        .addNewStringStatementStatement("this." + property.getName() + ".clear(); if (" + property.getName() + " != null) {for (" + unwraped.toString() + " item :" + property.getName() + "){ this." + addToMethodName + "(item);}} return (T) this;")
                    .endBlock()
                    .build();
        }

    }, GETTER {
        public Method apply(final Property property) {
            String prefix = isBoolean(property.getTypeRef()) ? "is" : "get";
            String methodName = prefix + property.getNameCapitalized();
            final List<Statement> statements = new ArrayList<Statement>();

            TreeSet<Property> descendants = new TreeSet<Property>(new Comparator<Property>() {
                public int compare(Property left, Property right) {
                    return left.getName().compareTo(right.getName());
                }
            });
            descendants.addAll(Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(property));

            if (isMap(property.getTypeRef())) {
                statements.add(new StringStatement("return this." + property.getName() + ";"));
            } else if (isBuildable(property.getTypeRef())) {
                if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
                    statements.add(new StringStatement("return build(" + property.getName() + ");"));
                } else {
                    statements.add(new StringStatement("return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;"));
                }
            } else if (!descendants.isEmpty()) {
                //TODO: This should also work for array types, so we should check....
                if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
                    String names = StringUtils.join(descendants, new Function<Property, String>() {
                        String className = TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef()).toString();
                        public String apply(Property item) {
                            return "this.<" + className + ">build(" + item.getName() + ")";
                        }
                    }, ", ");
                    statements.add(new StringStatement("return aggregate(" + names + ");"));
                } else {
                    //TODO: What are we doing in this case?
                    statements.add(new StringStatement("return aggregate(" + "return this." + property.getName() + ";" + ");"));
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
    }, GETTER_ARRAY {
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
                    type.toString(),
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
    },
    SETTER {
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
    },
    ADD_TO_COLLECTION {
        public Method apply(final Property property) {
            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(TypeAs.combine(UNWRAP_COLLECTION_OF, ARRAY_OF).apply(property.getTypeRef()))
                    .build();

            TypeRef typeRef = UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            String methodName = "addTo" + property.getNameCapitalized();
            List<Statement> statements = new ArrayList<Statement>();
            Set<Property> descendants = Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(property);
            if (isBuildable(typeRef)) {
                String targetClass = ((ClassRef)typeRef).getDefinition().getName();
                String builderClass = targetClass + "Builder";
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.add(builder);this." + property.getName() + ".add(builder);} return (T)this;"));
            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) typeRef;
                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) {" + StringUtils.join(descendants, new Function<Property, String>() {

                    public String apply(Property item) {
                        String addToMethodName = "addTo" + captializeFirst(item.getName());
                        return "if (item instanceof " + targetType.getDefinition().getName() + "){" + addToMethodName + "((" + targetType.getDefinition().getName() + ")item);}\n";
                    }
                }, " else ") + "} return (T)this;"));
            } else {
                statements.add(new StringStatement("for (" + typeRef.toString() + " item : items) {this." + property.getName() + ".add(item);} return (T)this;"));
            }

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(item)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                        .withStatements(statements)
                    .endBlock()
                    .build();
        }
    },
    REMOVE_FROM_COLLECTION {

        public Method apply(final Property property) {
            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(TypeAs.combine(UNWRAP_COLLECTION_OF, ARRAY_OF).apply(property.getTypeRef()))
                    .build();

            TypeRef typeRef = UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            String methodName = "removeFrom" + property.getNameCapitalized();
            List<Statement> statements = new ArrayList<Statement>();

            Set<Property> descendants = Decendants.PROPERTY_BUILDABLE_ANCESTORS.apply(property);
            if (isBuildable(typeRef)) {
                String targetClass = ((ClassRef)typeRef).getDefinition().getName();
                String builderClass = targetClass + "Builder";
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.remove(builder);this." + property.getName() + ".remove(builder);} return (T)this;"));
            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) typeRef;
                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) {" + StringUtils.join(descendants, new Function<Property, String>() {
                    public String apply(Property item) {
                        String removeFromMethodName = "removeFrom" + captializeFirst(item.getName());
                        return "if (item instanceof " + targetType.getDefinition().getName() + "){" + removeFromMethodName + "((" + targetType.getDefinition().getName() + ")item);}\n";
                    }
                }, " else ") + "} return (T)this;"));

            } else {
                statements.add(new StringStatement("for (" + typeRef.toString() + " item : items) {this." + property.getName() + ".remove(item);} return (T)this;"));
            }

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(item)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                        .withStatements(statements)
                    .endBlock()
                    .build();
        }
    },
    ADD_MAP_TO_MAP {

        public Method apply(Property property) {
            ClassRef mapType = (ClassRef) property.getTypeRef();
            Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
            String methodName = "addTo" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(mapProperty)
                    .withNewBlock()
                    .addNewStringStatementStatement("if(map != null) { this." + property.getName() + ".putAll(map);} return (T)this;")
                    .endBlock()
                    .build();
        }
    },
    ADD_TO_MAP {

        public Method apply(Property property) {
            ClassRef mapType = (ClassRef) property.getTypeRef();
            TypeRef keyType = mapType.getArguments().get(0);
            TypeRef valueType = mapType.getArguments().get(1);


            Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
            Property valueProperty = new PropertyBuilder().withName("value").withTypeRef(valueType).build();
            String methodName = "addTo" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(new Property[]{keyProperty, valueProperty})
                    .withNewBlock()
                        .addNewStringStatementStatement("if(key != null && value != null) {this." + property.getName() + ".put(key, value);} return (T)this;")
                    .endBlock()
                    .build();
        }
    }, REMOVE_MAP_FROM_MAP {

        public Method apply(Property property) {
            TypeRef mapType = property.getTypeRef();
            Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
            String methodName = "removeFrom" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(mapProperty)
                    .withNewBlock()
                        .addNewStringStatementStatement("if(map != null) { for(Object key : map.keySet()) {this." + property.getName() + ".remove(key);}} return (T)this;")
                    .endBlock()
                    .build();
        }
    }, REMOVE_FROM_MAP {

        public Method apply(Property property) {
            ClassRef mapType = (ClassRef) property.getTypeRef();
            TypeRef keyType = mapType.getArguments().get(0);

            Property keyProperty = new PropertyBuilder().withName("key").withTypeRef(keyType).build();
            String methodName = "removeFrom" + property.getNameCapitalized();
            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(T_REF)
                    .withArguments(keyProperty)
                    .withNewBlock()
                        .addNewStringStatementStatement("if(key != null) {this." + property.getName() + ".remove(key);} return (T)this;")
                    .endBlock()
                    .build();
        }
    }, WITH_NEW_NESTED {

        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            TypeDef nestedTypeImpl = PropertyAs.NESTED_TYPE.apply(property);

            Set<TypeParamDef> parameters = new LinkedHashSet<TypeParamDef>();
            for (TypeParamDef param : baseType.getDefinition().getParameters()) {
                parameters.add(param);
            }
            parameters.add(T);

            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef arg : baseType.getArguments()) {
                typeArguments.add(arg);
            }
            typeArguments.add(T_REF);


            ClassRef rewraped = classRefOf(nestedType, typeArguments.toArray()) ;
            ClassRef rewrapedImpl = classRefOf(nestedTypeImpl, typeArguments.toArray());

            boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
            String prefix = isCollection ? "addNew" : "withNew";
            String methodName = prefix + captializeFirst(isCollection
                    ? singularize(property.getName())
                    : property.getName());

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .withNewBlock()
                        .addNewStringStatementStatement("return new " + rewrapedImpl.getDefinition().getName() + "();")
                    .endBlock()
                    .build();

        }
    }, WITH_NEW_LIKE_NESTED {
        public Method apply(Property property) {
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            TypeDef nestedTypeImpl = PropertyAs.NESTED_TYPE.apply(property);

            List<TypeRef> generics = new ArrayList<TypeRef>();
            for (TypeRef ignore : baseType.getArguments()) {
                generics.add(Q);
            }
            generics.add(T_REF);

            ClassRef rewraped = classRefOf(nestedType, generics.toArray()) ;
            ClassRef rewrapedImpl = classRefOf(nestedTypeImpl, generics.toArray());

            boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());

            String prefix = isCollection ? "addNew" : "withNew";
            String suffix = "Like";
            String methodName = prefix + captializeFirst(isCollection
                    ? singularize(property.getName())
                    : property.getName()) + suffix;

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(rewraped)
                    .withName(methodName)
                    .addNewArgument()
                    .withName("item")
                    .withTypeRef(baseType)
                    .endArgument()
                    .withNewBlock()
                        .addNewStringStatementStatement("return new " + rewrapedImpl.getDefinition() + "(item);")
                    .endBlock()
                    .build();

        }
    }, EDIT_NESTED {

        public Method apply(Property property) {
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            //We need to repackage because we are nesting under this class.
            ClassRef rewraped = classRefOf(nestedType, T_REF);
            String prefix = "edit";
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
    }, AND {
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
            Object memberOf = property.getAttributes().get(MEMBER_OF);
            if (memberOf instanceof TypeDef) {
                return ((TypeDef) memberOf).getName() + ".this.";
            } else return "";
        }

    }, END {
        public Method apply(Property property) {

            String methodName = "end" + captializeFirst(IS_COLLECTION.apply(property.getTypeRef())
                    ? singularize(property.getName())
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
    };

    private static final String BUILDABLE_ARRAY_GETTER_TEXT = loadResourceQuietly(BUILDABLE_ARRAY_GETTER_SNIPPET);
    private static final String SIMPLE_ARRAY_GETTER_TEXT = loadResourceQuietly(SIMPLE_ARRAY_GETTER_SNIPPET);
}
