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
import io.sundr.codegen.model.AnnotationRef;
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
import io.sundr.codegen.model.WildcardRefBuilder;
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

import static io.sundr.builder.Constants.BOOLEAN_REF;
import static io.sundr.builder.Constants.BUILDABLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.DEPRECATED_ANNOTATION;
import static io.sundr.builder.Constants.DESCENDANTS;
import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.builder.Constants.GENERIC_TYPE_REF;
import static io.sundr.builder.Constants.INDEX;
import static io.sundr.builder.Constants.N_REF;
import static io.sundr.builder.Constants.OUTER_CLASS;
import static io.sundr.builder.Constants.PREDICATE;
import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.Constants.SIMPLE_ARRAY_GETTER_SNIPPET;
import static io.sundr.builder.Constants.T_REF;
import static io.sundr.builder.Constants.VOID;
import static io.sundr.codegen.functions.Collections.COLLECTION;
import static io.sundr.codegen.functions.Collections.IS_COLLECTION;
import static io.sundr.codegen.functions.Collections.IS_LIST;
import static io.sundr.codegen.functions.Collections.IS_MAP;
import static io.sundr.codegen.functions.Collections.IS_SET;
import static io.sundr.builder.internal.functions.TypeAs.ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.VISITABLE_BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.combine;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.codegen.utils.TypeUtils.isAbstract;
import static io.sundr.codegen.utils.TypeUtils.isBoolean;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.codegen.utils.TypeUtils.isList;
import static io.sundr.codegen.utils.TypeUtils.isMap;
import static io.sundr.codegen.utils.TypeUtils.isPrimitive;
import static io.sundr.codegen.utils.TypeUtils.isSet;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;


public class ToMethod {

    private static final String BUILDABLE_ARRAY_GETTER_TEXT = loadResourceQuietly(BUILDABLE_ARRAY_GETTER_SNIPPET);
    private static final String SIMPLE_ARRAY_GETTER_TEXT = loadResourceQuietly(SIMPLE_ARRAY_GETTER_SNIPPET);

    public static final Function<Property, Method> WITH = FunctionFactory.cache(new Function<Property, Method>() {

        public Method apply(Property property) {
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            String argumentName = property.getName();
            String fieldName = property.getName();
            TypeRef type = property.getTypeRef();
            TypeRef unwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            List<Statement> statements = new ArrayList<Statement>();
            Set<Property> descendants = property.hasAttribute(DESCENDANTS) ? property.getAttribute(DESCENDANTS) : Collections.EMPTY_SET;

            if (property.hasAttribute(DESCENDANT_OF)) {
                Property descendantOf = (Property) property.getAttribute(DESCENDANT_OF);
                fieldName = descendantOf.getName();
            }

            if (isBuildable(unwraped)) {
                if (IS_COLLECTION.apply(type) || IS_MAP.apply(type)) {
                    statements.add(new StringStatement("_visitables.removeAll(this." + fieldName  + ");"));
                } else {
                    statements.add(new StringStatement("_visitables.remove(this." + fieldName + ");"));
                }
            }

            if (IS_COLLECTION.apply(type) || IS_MAP.apply(type)) {
                statements.add(new StringStatement("this." + fieldName + ".clear();"));
                if (IS_MAP.apply(type)) {
                    statements.add(new StringStatement("if (" + argumentName + " != null) {this." + fieldName + ".putAll(" + argumentName + ");} return (" + returnType + ") this;"));
                } else if (IS_LIST.apply(type) || IS_SET.apply(type)) {
                    String addToMethodName = "addTo" + property.getNameCapitalized();
                    statements.add(new StringStatement("if (" + argumentName + " != null) {for (" + unwraped.toString() + " item : " + argumentName + "){this." + addToMethodName + "(item);}} return (" + returnType + ") this;"));
                }
                return statements;
            } else if (isBuildable(unwraped) && !isAbstract(unwraped)) {
                TypeDef builder = BUILDER.apply(((ClassRef) unwraped).getDefinition());
                String builderClass = builder.toReference().getName();
                statements.add(new StringStatement("if (" + argumentName + "!=null){ this." + fieldName + "= new " + builderClass + "("+argumentName+"); _visitables.add(this." + fieldName + ");} return (" + returnType + ") this;"));
                return statements;
            } else if (!descendants.isEmpty()) {
                for (Property descendant : descendants) {
                    TypeRef dunwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(descendant.getTypeRef());
                    TypeDef builder = BUILDER.apply(((ClassRef) dunwraped).getDefinition());
                    String builderClass = builder.toReference().getName();
                    statements.add(new StringStatement("if (" + argumentName + " instanceof " + dunwraped + "){ this." + fieldName + "= new " + builderClass + "((" + dunwraped + ")" + argumentName + "); _visitables.add(this." + fieldName + ");}"));

                    alsoImport.add((ClassRef) dunwraped);
                    alsoImport.add(builder.toInternalReference());
                }
                statements.add(new StringStatement("return (" + returnType + ") this;"));
                return statements;
            }
            statements.add(new StringStatement("this." + fieldName + "=" + argumentName + "; return (" + returnType + ") this;"));
            return statements;
        }
    });

    public static final Function<Property, Method> WITH_ARRAY = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ?  property.getAttribute(GENERIC_TYPE_REF) : T_REF;

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

    public static final Function<Property, Method> HAS = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(final Property property) {
            String prefix =  "has";
            String methodName = prefix + property.getNameCapitalized();
            List<Statement> statements = new ArrayList<Statement>();

            if (isPrimitive(property.getTypeRef())) {
                statements.add(new StringStatement("return true;"));
            } else if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
                statements.add(new StringStatement("return " + property.getName() + "!= null && !"+property.getName()+".isEmpty();"));
            } else {
                statements.add(new StringStatement("return this." + property.getName() + "!=null;"));
            }

           return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(methodName)
                    .withReturnType(BOOLEAN_REF)
                    .withArguments()
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .build();
        }
    });

    public static final Function<Property, List<Method>> GETTER = FunctionFactory.cache(new Function<Property, List<Method>>() {
        public List<Method> apply(final Property property) {
            List<Method> methods = new ArrayList<Method>();
            TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF).apply(property.getTypeRef());

            String prefix = isBoolean(property.getTypeRef()) ? "is" : "get";
            String getterName = prefix + property.getNameCapitalized();
            String builderName = "build" + property.getNameCapitalized();
            List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
            List<String> comments = new ArrayList<String>();
            List<Statement> statements = new ArrayList<Statement>();
            boolean isNested = false;
            boolean isList = isList(property.getTypeRef());
            boolean isSet = isSet(property.getTypeRef());

            TreeSet<Property> descendants = new TreeSet<Property>(new Comparator<Property>() {
                public int compare(Property left, Property right) {
                    return left.getName().compareTo(right.getName());
                }
            });
            descendants.addAll(Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property));

            if (isMap(property.getTypeRef())) {
                statements.add(new StringStatement("return this." + property.getName() + ";"));
            } else if (isBuildable(unwrapped)) {
                isNested = true;
                annotations.add(DEPRECATED_ANNOTATION);
                comments.add("This method has been deprecated, please use method "+builderName+" instead.");
                if (isList || isSet) {
                    statements.add(new StringStatement("return build(" + property.getName() + ");"));
                } else {
                    statements.add(new StringStatement("return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;"));
                }
            } else if (!descendants.isEmpty()) {
                isNested = true;
                annotations.add(DEPRECATED_ANNOTATION);
                comments.add("This method has been deprecated, please use method "+builderName+" instead.");
                if (isList || isSet) {
                    statements.add(new StringStatement("return build(" + property.getName() + ");"));
                } else {
                    statements.add(new StringStatement("return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;"));
                }
            } else {
                statements.add(new StringStatement("return this." + property.getName() + ";"));
            }


            Method getter = new MethodBuilder()
                    .withComments(comments)
                    .withAnnotations(annotations)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(getterName)
                    .withReturnType(property.getTypeRef())
                    .withArguments(new Property[]{})
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .build();

            methods.add(getter);
            if (isNested) {
                methods.add(new MethodBuilder(getter)
                        .removeFromAnnotations(DEPRECATED_ANNOTATION)
                        .withComments()
                        .withName("build" + property.getNameCapitalized())
                        .build());

                if (isList) {
                    methods.add(new MethodBuilder()
                            .withComments()
                            .withAnnotations()
                            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                            .withName("build" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                            .withReturnType(unwrapped)
                            .addToArguments(INDEX)
                            .withNewBlock()
                            .withStatements(new StringStatement("return this." + property.getName() + ".get(index).build();"))
                            .endBlock()
                            .build());

                    methods.add(new MethodBuilder()
                            .withComments()
                            .withAnnotations()
                            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                            .withName("buildFirst" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                            .withReturnType(unwrapped)
                            .withNewBlock()
                            .withStatements(new StringStatement("return this." + property.getName() + ".get(0).build();"))
                            .endBlock()
                            .build());

                    methods.add(new MethodBuilder()
                            .withComments()
                            .withAnnotations()
                            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                            .withName("buildLast" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                            .withReturnType(unwrapped)
                            .withNewBlock()
                            .withStatements(new StringStatement("return this." + property.getName() + ".get(" + property.getName() + ".size() - 1).build();"))
                            .endBlock()
                            .build());

                    TypeRef builderRef = BuilderContextManager.getContext().getBuilderInterface().toReference(new WildcardRefBuilder().withBounds(unwrapped).build());
                    methods.add(new MethodBuilder()
                            .withComments()
                            .withAnnotations()
                            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                            .withName("buildMatching" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                            .addNewArgument()
                            .withName("predicate")
                            .withTypeRef(PREDICATE.toReference(builderRef))
                            .endArgument()
                            .withReturnType(unwrapped)
                            .withNewBlock()
                            .withStatements(new StringStatement("for ("+builderRef+" item: "+property.getName()+") { if(predicate.apply(item)){return item.build();} } return null;"))
                            .endBlock()
                            .build());
                }
            } else if (isList) {

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations(annotations)
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName(prefix +  Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .withReturnType(unwrapped)
                        .addToArguments(INDEX)
                        .withNewBlock()
                        .withStatements(new StringStatement("return this." + property.getName() + ".get(index);"))
                        .endBlock()
                        .build());

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations(annotations)
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName(prefix + "First" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .withReturnType(unwrapped)
                        .withNewBlock()
                        .withStatements(new StringStatement("return this." + property.getName() + ".get(0);"))
                        .endBlock()
                        .build());

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations(annotations)
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName(prefix + "Last" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .withReturnType(unwrapped)
                        .withNewBlock()
                        .withStatements(new StringStatement("return this." + property.getName() + ".get(" + property.getName() + ".size() - 1);"))
                        .endBlock()
                        .build());

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations(annotations)
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName(prefix + "Matching" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .addNewArgument()
                            .withName("predicate")
                            .withTypeRef(PREDICATE.toReference(unwrapped))
                        .endArgument()
                        .withReturnType(unwrapped)
                        .withNewBlock()
                        .withStatements(new StringStatement("for ("+unwrapped+" item: "+property.getName()+") { if(predicate.apply(item)){return item;} } return null;"))
                        .endBlock()
                        .build());
            }

            return methods;
        }
    });

    public static final Function<Property, List<Method>> GETTER_ARRAY = FunctionFactory.cache(new Function<Property, List<Method>>() {
        public List<Method> apply(Property property) {
            List<Method> methods = new ArrayList<Method>();
            List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
            List<String> comments = new ArrayList<String>();

            String prefix = isBoolean(property.getTypeRef()) ? "is" : "get";
            String getterName = prefix + property.getNameCapitalized();
            String builderName = "build" + property.getNameCapitalized();
            TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF).apply(property.getTypeRef());
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

            if (isBuildable) {
                annotations.add(DEPRECATED_ANNOTATION);
                comments.add("This method has been deprecated, please use method "+builderName+" instead.");
            }
            Method getter =  new MethodBuilder()
                    .withComments(comments)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(getterName)
                    .withReturnType(property.getTypeRef())
                    .withArguments()
                    .withNewBlock()
                    .addNewStringStatementStatement(body)
                    .endBlock()
                    .build();

            methods.add(getter);
            if (isBuildable) {
                methods.add(new MethodBuilder(getter)
                        .removeFromAnnotations(DEPRECATED_ANNOTATION)
                        .withComments()
                        .withName(builderName)
                        .build());

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName("build" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .withReturnType(unwrapped)
                        .addToArguments(INDEX)
                        .withNewBlock()
                        .withStatements(new StringStatement("return this." + property.getName() + ".get(index).build();"))
                        .endBlock()
                        .build());

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName("buildFirst" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .withReturnType(unwrapped)
                        .withNewBlock()
                        .withStatements(new StringStatement("return this." + property.getName() + ".get(0).build();"))
                        .endBlock()
                        .build());

                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName("buildLast" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .withReturnType(unwrapped)
                        .withNewBlock()
                        .withStatements(new StringStatement("return this." + property.getName() + ".get(" + property.getName() + ".size() - 1).build();"))
                        .endBlock()
                        .build());

                TypeRef builderRef = BuilderContextManager.getContext().getBuilderInterface().toReference(new WildcardRefBuilder().withBounds(unwrapped).build());
                methods.add(new MethodBuilder()
                        .withComments()
                        .withAnnotations()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName("buildMatching" + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                        .addNewArgument()
                        .withName("predicate")
                        .withTypeRef(PREDICATE.toReference(builderRef))
                        .endArgument()
                        .withReturnType(unwrapped)
                        .withNewBlock()
                        .withStatements(new StringStatement("for ("+builderRef+" item: "+property.getName()+") { if(predicate.apply(item)){return item.build();} } return null;"))
                        .endBlock()
                        .build());
            }
            return methods;
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

    public static final Function<Property, List<Method>> ADD_TO_COLLECTION = FunctionFactory.cache(new Function<Property, List<Method>>() {
        public List<Method> apply(final Property property) {
            List<Method> methods = new ArrayList<Method>();
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDF);

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            final TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef());
            List<ClassRef> alsoImport = new ArrayList<ClassRef>();

            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(unwrapped.withDimensions(1))
                    .build();

            Property unwrappedProperty = new PropertyBuilder(property)
                    .withName("item")
                    .withTypeRef(unwrapped).build();

            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

            String addVarargMethodName = "addTo" + property.getNameCapitalized();
            String setMethodName = "setTo" + property.getNameCapitalized();
            String addAllMethodName = "addAllTo" + BuilderUtils.fullyQualifiedNameDiff(baseType, originTypeDef) + property.getNameCapitalized();

            List<Statement> statements = new ArrayList<Statement>();
            Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);

            String propertyName = property.getName();
            if (property.hasAttribute(Constants.DESCENDANT_OF)) {
                Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
                if (attrValue != null) {
                    propertyName = (attrValue).getName();
                }
            }

            Method addSingleItemAtIndex = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withName(addVarargMethodName)
                    .withReturnType(returnType)
                    .addToArguments(INDEX)
                    .addToArguments(unwrappedProperty)
                    .withNewBlock()
                    .withStatements(new StringStatement("this." + propertyName + ".add(index, item); return (" + returnType + ")this;"))
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();


            Method setSingleItemAtIndex = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withName(setMethodName)
                    .withReturnType(returnType)
                    .addToArguments(INDEX)
                    .addToArguments(unwrappedProperty)
                    .withNewBlock()
                    .withStatements(new StringStatement("this." +propertyName + ".set(index, item); return (" + returnType + ")this;"))
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();


            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                final ClassRef targetType = (ClassRef) unwrapped;

                String targetClass = targetType.getName();
                parameters.addAll(targetType.getDefinition().getParameters());
                String builderClass = targetClass + "Builder";

                //We need to do it more
                alsoImport.add(TypeAs.BUILDER.apply(targetType.getDefinition()).toInternalReference());
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.add(builder);this." + propertyName + ".add(builder);} return (" + returnType + ")this;"));

                addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(new StringStatement(builderClass + " builder = new " + builderClass + "(item);_visitables.add(index >= 0 ? index : _visitables.size(), builder);this." + propertyName + ".add(index >= 0 ? index : "+propertyName+".size(), builder); return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

                setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(new StringStatement(builderClass + " builder = new " + builderClass + "(item);_visitables.set(index >= 0 ? index : _visitables.size(), builder);this." + propertyName + ".set(index >= 0 ? index : "+propertyName+".size(), builder); return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) unwrapped;
                parameters.addAll(targetType.getDefinition().getParameters());
                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) { "));
                statements.add(createAddToDescendants("addTo", descendants, targetType, returnType, false));
                statements.add(new StringStatement("} return (" + returnType + ")this;"));

                addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                            .withStatements(createAddToDescendants("addTo", descendants, targetType, returnType, true), new StringStatement("return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

                setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(createAddToDescendants("setTo", descendants, targetType, returnType, true), new StringStatement("return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

            }  else {
                statements.add(new StringStatement("for (" + unwrapped.toString() + " item : items) {this." + property.getName() + ".add(item);} return (" + returnType + ")this;"));
            }



            Method addVaragToCollection = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withName(addVarargMethodName)
                    .withReturnType(returnType)
                    .withArguments(item)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();


            Method addAllToCollection = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withName(addAllMethodName)
                    .withReturnType(returnType)
                    .withArguments(new PropertyBuilder(item).withTypeRef(COLLECTION.toReference(unwrapped)).build())
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();

            methods.add(addSingleItemAtIndex);
            methods.add(setSingleItemAtIndex);
            methods.add(addVaragToCollection);
            methods.add(addAllToCollection);

            return methods;
        }

        private Statement createAddToDescendants(final String prefix, Set<Property> descendants, TypeRef targetType, TypeRef returnType, final boolean useIndex) {
            return new StringStatement(StringUtils.join(descendants, new Function<Property, String>() {

                public String apply(Property item) {
                    TypeRef itemRef = TypeAs.combine(UNWRAP_COLLECTION_OF, ARRAY_OF).apply(item.getTypeRef());
                    String className = ((ClassRef) itemRef).getName();
                    String methodName = prefix + captializeFirst(item.getName());
                    return "if (item instanceof " + className + "){" + methodName + "("+ (useIndex ? "index, " : "") +"(" + className + ")item);}\n";
                }
            }, " else "));
        }
    });

    public static final Function<Property, List<Method>> REMOVE_FROM_COLLECTION = FunctionFactory.cache(new Function<Property, List<Method>>() {
        public List<Method> apply(final Property property) {
            List<Method> methods = new ArrayList<Method>();
            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDF);

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            final TypeRef unwrapped = TypeAs.combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef());
            List<ClassRef> alsoImport = new ArrayList<ClassRef>();
            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(unwrapped.withDimensions(1))
                    .build();

            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();

            String removeVarargMethodName = "removeFrom" + property.getNameCapitalized();
            String removeAllMethdoName = "removeAllFrom" + BuilderUtils.fullyQualifiedNameDiff(baseType, originTypeDef) + property.getNameCapitalized();

            List<Statement> statements = new ArrayList<Statement>();

            Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);
            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                final ClassRef targetType = (ClassRef) unwrapped;
                String propertyName = property.getName();
                if (property.hasAttribute(Constants.DESCENDANT_OF)) {
                    Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
                    if (attrValue != null) {
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

            Method removeVarargFromCollection = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(removeVarargMethodName)
                    .withParameters(parameters)
                    .withReturnType(returnType)
                    .withArguments(item)
                    .withVarArgPreferred(true)
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .build();


            Method removeAllFromCollection = new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withParameters(parameters)
                    .withName(removeAllMethdoName)
                    .withReturnType(returnType)
                    .withArguments(new PropertyBuilder(item).withTypeRef(COLLECTION.toReference(unwrapped)).build())
                    .withNewBlock()
                    .withStatements(statements)
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();

            methods.add(removeVarargFromCollection);
            methods.add(removeAllFromCollection);

            return methods;
        }
    });

    public static final Function<Property, Method> ADD_MAP_TO_MAP = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? (TypeRef) property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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

            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDF);

            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
            TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

            List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef arg : baseType.getArguments()) {
                typeArguments.add(arg);
            }
            typeArguments.add(returnType);


            ClassRef rewraped = nestedType.toReference(typeArguments);
            ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

            boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
            String prefix = isCollection ? "addNew" : "withNew";


            prefix += BuilderUtils.fullyQualifiedNameDiff(baseType, originTypeDef);
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
            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDF);

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            Set<Method> result = new LinkedHashSet<Method>();
            TypeRef unwrappedType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
            TypeDef baseType = BuilderContextManager.getContext().getBuildableRepository().getBuildable(unwrappedType);

            for (Method constructor : getInlineableConstructors(property)) {
                boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
                String ownPrefix = isCollection ? "addNew" : "withNew";

                ownPrefix += BuilderUtils.fullyQualifiedNameDiff(baseType.toInternalReference(), originTypeDef);
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

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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
                    .addNewStringStatementStatement("return new " + rewrapedImpl.getName() + "("+ (isCollection ? "-1, ": "") +"item);")
                    .endBlock()
                    .build();

        }
    };

    public static final Function<Property, Method> WITH_NEW_LIKE_NESTED_AT_INDEX = new Function<Property, Method>() {

        @Override
        public Method apply(Property property) {
            Method method = WITH_NEW_LIKE_NESTED.apply(property);

            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

            List<TypeRef> typeArguments = new ArrayList<TypeRef>();
            for (TypeRef ignore : baseType.getArguments()) {
                typeArguments.add(Q);
            }
            typeArguments.add(returnType);
            ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

            return new MethodBuilder(method)
                    .addToArguments(0, INDEX)
                    .editBlock()
                        .withStatements(new StringStatement("return new " + rewrapedImpl.getName() + "(index, item);"))
                    .endBlock()
                    .build();
        }
    };

    public static final Function<Property, Method> EDIT_NESTED =new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDF);

            ClassRef baseType = (ClassRef) TypeAs.UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            //Let's reload the class from the repository if available....
            TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
            if (propertyTypeDef != null) {
                baseType = propertyTypeDef.toInternalReference();
            }

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? (TypeRef) property.getAttribute(GENERIC_TYPE_REF) : T_REF;
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
            prefix += BuilderUtils.fullyQualifiedNameDiff(property.getTypeRef(), originTypeDef);
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

    public static final Function<Property, Method> EDIT_AT_INDEX_NESTED = new Function<Property, Method>() {
        public Method apply(Property property) {
            String suffix = Singularize.FUNCTION.apply(captializeFirst(property.getName()));
            Method method = EDIT_NESTED.apply(property);

            return new MethodBuilder(method)
                    .withArguments(INDEX)
                    .editBlock()
                        .withStatements(new StringStatement("return addNew" + suffix + "Like(index, build" + suffix + "(index));"))
                    .endBlock()
                    .build();
        }
    };

    public static final Function<Property, Method> AND = new Function<Property, Method>() {
        public Method apply(Property property) {
            String classPrefix = getClassPrefix(property);


            boolean isArray = TypeUtils.isArray(property.getTypeRef());
            boolean isList = TypeUtils.isList(property.getTypeRef());
            boolean isSet = TypeUtils.isSet(property.getTypeRef());

            String prefix = isArray || isList ? "addTo" : "with";
            String withMethodName = prefix + captializeFirst(property.getName());

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(N_REF)
                    .withName("and")
                    .withNewBlock()
                    .addNewStringStatementStatement("return (N) " + classPrefix + withMethodName + "("+
                            (isArray || isList ? "index, " : "")
                            + "builder.build());")
                    .endBlock()
                    .build();

        }

        private String getClassPrefix(Property property) {
            TypeDef memberOf = property.getAttribute(OUTER_CLASS);
            if (memberOf != null) {
                return memberOf.getName() + ".this.";
            } else return "";
        }

    };

    public static final Function<Property, Method> END = FunctionFactory.cache(new Function<Property, Method>() {
        public Method apply(Property property) {
            TypeDef originTypeDef =  property.getAttribute(Constants.ORIGIN_TYPEDF);
            String methodName = "end" + BuilderUtils.fullyQualifiedNameDiff(property.getTypeRef(), originTypeDef) + captializeFirst(IS_COLLECTION.apply(property.getTypeRef())
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
