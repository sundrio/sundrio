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
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.functions.Singularize;
import io.sundr.codegen.model.*;
import io.sundr.codegen.utils.Getter;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.*;

import static io.sundr.builder.Constants.*;
import static io.sundr.builder.internal.functions.TypeAs.BUILDER;
import static io.sundr.builder.internal.functions.TypeAs.*;
import static io.sundr.builder.internal.utils.BuilderUtils.getInlineableConstructors;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.codegen.Constants.Q;
import static io.sundr.codegen.Constants.*;
import static io.sundr.codegen.functions.Collections.*;
import static io.sundr.codegen.model.Attributeable.*;
import static io.sundr.codegen.utils.StringUtils.capitalizeFirst;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
import static io.sundr.codegen.utils.TypeUtils.*;


class ToMethod {

    private static final String BUILDABLE_ARRAY_GETTER_TEXT = loadResourceQuietly(BUILDABLE_ARRAY_GETTER_SNIPPET);
    private static final String SIMPLE_ARRAY_GETTER_TEXT = loadResourceQuietly(SIMPLE_ARRAY_GETTER_SNIPPET);

    private enum GeneratorType {
        FIRST("First", "0"), LAST("Last", "%s.size() - 1"), INDEXED("", "index", true);


        GeneratorType(String name, String indexStatementFmt) {
            this(name, indexStatementFmt, false);
        }

        GeneratorType(String name, String indexStatementFmt, boolean appendIndexArg) {
            this.name = name;
            this.indexStatementFmt = indexStatementFmt;
            this.appendIndexArg = appendIndexArg;
        }

        private String indexStatement(Property property) {
            return String.format(indexStatementFmt, property.getName());
        }

        private void addIndexIfNeeded(MethodBuilder methodBuilder) {
            if (appendIndexArg) {
                methodBuilder.addToArguments(INDEX);
            }
        }

        private final String name;
        private final String indexStatementFmt;
        private final boolean appendIndexArg;
    }

    private interface GeneratorCustomizer {
        GeneratorCustomizer defaultCustomizer = new GeneratorCustomizer() {
            @Override
            public String methodPrefix(Property property) {
                return Getter.prefix(property);
            }

            @Override
            public String doWithItem() {
                return "";
            }
        };
        GeneratorCustomizer builderCustomizer = new GeneratorCustomizer() {
            @Override
            public String methodPrefix(Property property) {
                return "build";
            }

            @Override
            public String doWithItem() {
                return ".build()";
            }
        };

        String methodPrefix(Property property);

        String doWithItem();
    }

    private static class GetterGenerator {
        GetterGenerator(ToMethod.GeneratorType type) {
            this(type, ToMethod.GeneratorCustomizer.defaultCustomizer);
        }

        GetterGenerator(ToMethod.GeneratorType type, ToMethod.GeneratorCustomizer customizer) {
            this.type = type;
            this.customizer = customizer;
        }

        private EditableMethod method(Property property, TypeRef unwrapped) {
            final MethodBuilder methodBuilder = new MethodBuilder()
                    .withComments()
                    .withAnnotations()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(customizer.methodPrefix(property) + type.name + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                    .withReturnType(unwrapped);
            type.addIndexIfNeeded(methodBuilder);

            methodBuilder.withNewBlock()
                    .withStatements(new StringStatement("return this." + property.getName() + ".get(" + type.indexStatement(property) + ")" + customizer.doWithItem() + ";"))
                    .endBlock()
                    .build();
            return methodBuilder.build();
        }

        private final ToMethod.GeneratorCustomizer customizer;
        private final ToMethod.GeneratorType type;
    }

    private static final GetterGenerator GET_FIRST = new GetterGenerator(GeneratorType.FIRST);
    private static final GetterGenerator BUILD_FIRST = new GetterGenerator(GeneratorType.FIRST, GeneratorCustomizer.builderCustomizer);
    private static final GetterGenerator GET_LAST = new GetterGenerator(GeneratorType.LAST);
    private static final GetterGenerator BUILD_LAST = new GetterGenerator(GeneratorType.LAST, GeneratorCustomizer.builderCustomizer);
    private static final GetterGenerator GET_INDEXED = new GetterGenerator(GeneratorType.INDEXED);
    private static final GetterGenerator BUILD_INDEXED = new GetterGenerator(GeneratorType.INDEXED, GeneratorCustomizer.builderCustomizer);

    private enum MatchingType {
        BUILD("item.build()", "null"), HAS("true", "false"), GET("item", "null"), REMOVE(null, null);

        private EditableMethod method(Property property, TypeRef returnType, TypeDef predicate, TypeRef builderRef, List<AnnotationRef> annotations, List<String> comments) {
            return new MethodBuilder()
                    .withComments(comments)
                    .withAnnotations(annotations)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(operationName() + Singularize.FUNCTION.apply(property.getNameCapitalized()))
                    .addNewArgument()
                    .withName("predicate")
                    .withTypeRef(predicate.toReference(builderRef))
                    .endArgument()
                    .withReturnType(returnType)
                    .withNewBlock()
                    .withStatements(statement(property, builderRef))
                    .endBlock()
                    .build();
        }

        private String operationName() {
            if (this != REMOVE) {
                return name().toLowerCase() + "Matching";
            } else {
                return "removeAllMatchingFrom";
            }
        }

        private Statement statement(Property property, TypeRef builderRef) {
            if (match != null && nonMatch != null) {
                return new StringStatement("for (" + builderRef + " item: " + property.getName() + ") { if(predicate.apply(item)){ return " + match + ";} } return " + nonMatch + ";");
            } else {
                return new StringStatement("return " + property.getName() + ".removeIf(item -> predicate.apply(item));");
            }
        }

        MatchingType(String match, String nonMatch) {
            this.match = match;
            this.nonMatch = nonMatch;
        }

        private final String match;
        private final String nonMatch;
    }

    static final Function<Property, Method> WITH = FunctionFactory.cache(new Function<Property, Method>() {

        public Method apply(Property property) {
            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            String methodName = "with" + property.getNameCapitalized();
            List<ClassRef> alsoImport = new ArrayList<>();
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
            TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
            List<Statement> statements = new ArrayList<>();
            Set<Property> descendants = property.hasAttribute(DESCENDANTS) ? property.getAttribute(DESCENDANTS) : Collections.emptySet();

            if (property.hasAttribute(DESCENDANT_OF)) {
                Property descendantOf = property.getAttribute(DESCENDANT_OF);
                fieldName = descendantOf.getName();
            }

            if (isBuildable(unwrapped) && !IS_COLLECTION.apply(type) && !IS_MAP.apply(type)) {
                statements.add(new StringStatement("_visitables.get(\"" + fieldName + "\").remove(this." + fieldName + ");"));
            }

            if (IS_COLLECTION.apply(type) || IS_MAP.apply(type)) {

                if (IS_MAP.apply(type)) {
                    statements.add(new StringStatement("if (" + fieldName + " == null) { this." + fieldName + " =  null;} else {this." + fieldName + " = " + property.getAttribute(INIT_FUNCTION).apply(Collections.singletonList(fieldName)) + ";} return (" + returnType + ") this;"));
                } else if (IS_LIST.apply(type) || IS_SET.apply(type)) {
                    statements.add(new StringStatement("if (this." + fieldName + " != null) { _visitables.get(\"" + fieldName + "\").removeAll(this." + fieldName + ");}"));

                    String addToMethodName = "addTo" + property.getNameCapitalized();
                    statements.add(new StringStatement("if (" + argumentName + " != null) {this." + fieldName + " = " + property.getAttribute(INIT_FUNCTION).apply(Collections.emptyList()) + "; for (" + unwrapped.toString() + " item : " + argumentName + "){this." + addToMethodName + "(item);}} else { this." + fieldName + " = null;} return (" + returnType + ") this;"));
                }
                return statements;
            }

            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                TypeDef builder = BUILDER.apply(((ClassRef) unwrapped).getDefinition());
                String builderClass = builder.toReference().getName();
                statements.add(new StringStatement("if (" + argumentName + "!=null){ this." + fieldName + "= new " + builderClass + "(" + argumentName + "); _visitables.get(\"" + fieldName + "\").add(this." + fieldName + ");} return (" + returnType + ") this;"));
                return statements;
            }

            if (!descendants.isEmpty()) {
                for (Property descendant : descendants) {
                    TypeRef dunwraped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(descendant.getTypeRef());
                    TypeDef builder = BUILDER.apply(((ClassRef) dunwraped).getDefinition());
                    String builderClass = builder.toReference().getName();
                    statements.add(new StringStatement("if (" + argumentName + " instanceof " + dunwraped + "){ this." + fieldName + "= new " + builderClass + "((" + dunwraped + ")" + argumentName + "); _visitables.get(\"" + fieldName + "\").add(this." + fieldName + ");}"));

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

    static final Function<Property, Method> WITH_ARRAY = FunctionFactory.cache(property -> {
        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;

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
                .addNewStringStatementStatement("if (this." + property.getName() + " != null) {this." + property.getName() + ".clear();}")
                .addNewStringStatementStatement("if (" + property.getName() + " != null) {for (" + unwraped.toString() + " item :" + property.getName() + "){ this." + addToMethodName + "(item);}} return (" + returnType + ") this;")
                .endBlock()
                .build();
    });

    static final Function<Property, List<Method>> WITH_OPTIONAL = FunctionFactory.cache(property -> {
        List<Method> methods = new ArrayList<>();
        TypeRef unwrapped = combine(UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        String methodName = "with" + property.getNameCapitalized();
        String fieldName = property.getName();
        String optionalSource = fieldName;                  //The expression we assign to the field (from optional if applicable).
        String source = fieldName;                          //The expression we assign to the field from no optional.
        String prepareSource = "";
        String prepareOptionalSource = "";

        if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
            TypeDef builder = BUILDER.apply(((ClassRef) unwrapped).getDefinition());
            prepareSource = builder.getName() + " b = new " + builder.getName() + "(" + fieldName + "); _visitables.get(\"" + fieldName + "\").add(b);";
            prepareOptionalSource = builder.getName() + " b = new " + builder.getName() + "(" + fieldName + ".get()); _visitables.get(\"" + fieldName + "\").add(b);";
            optionalSource = "Optional.of(b)";
            source = "b";
        }

        methods.add(
                new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName(methodName)
                        .withReturnType(returnType)
                        .withArguments(property)
                        .withNewBlock()
                        .addNewStringStatementStatement("if (" + fieldName + " == null || !" + fieldName + ".isPresent()) { this." + fieldName + " = " + property.getAttribute(INIT) + "; } else {" + prepareOptionalSource + " this." + fieldName + " = " + optionalSource + "; } return (" + returnType + ") this;")
                        .endBlock()
                        .build()
        );

        Property genericProperty = new PropertyBuilder(property).withTypeRef(unwrapped).build();
        methods.add(
                new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withName(methodName)
                        .withReturnType(returnType)
                        .withArguments(genericProperty)
                        .withNewBlock()
                        .addNewStringStatementStatement("if (" + fieldName + " == null) { this." + fieldName + " = " + property.getAttribute(INIT) + "; } else {" + prepareSource + " this." + fieldName + " = " + property.getAttribute(INIT_FUNCTION).apply(Collections.singletonList(source)) + "; } return (" + returnType + ") this;")
                        .endBlock()
                        .build()
        );

        return methods;
    });

    static final Function<Property, Method> HAS = FunctionFactory.cache(property -> {
        String prefix = "has";
        String methodName = prefix + property.getNameCapitalized();
        List<Statement> statements = new ArrayList<>();

        if (isPrimitive(property.getTypeRef())) {
            statements.add(new StringStatement("return true;"));
        } else if (isList(property.getTypeRef()) || isSet(property.getTypeRef())) {
            statements.add(new StringStatement("return " + property.getName() + " != null && !" + property.getName() + ".isEmpty();"));
        } else if (isOptional(property.getTypeRef()) || isOptionalInt(property.getTypeRef()) || isOptionalLong(property.getTypeRef()) || isOptionalDouble(property.getTypeRef())) {
            statements.add(new StringStatement("return " + property.getName() + " != null && " + property.getName() + ".isPresent();"));
        } else {
            statements.add(new StringStatement("return this." + property.getName() + " != null;"));
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
    });

    static final Function<Property, List<Method>> GETTER = FunctionFactory.cache(property -> {
        List<Method> methods = new ArrayList<>();
        TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

        TypeDef predicate = typeGenericOf(BuilderContextManager.getContext().getPredicateClass(), T);
        String getterName = Getter.name(property);
        String builderName = "build" + property.getNameCapitalized();
        List<AnnotationRef> annotations = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        boolean isNested = false;
        boolean isMap = isMap(property.getTypeRef());
        boolean isList = isList(property.getTypeRef());
        boolean isSet = isSet(property.getTypeRef());
        boolean isOptional = isOptional(property.getTypeRef()) || isOptionalDouble(property.getTypeRef()) || isOptionalInt(property.getTypeRef()) || isOptionalLong(property.getTypeRef());

        TreeSet<Property> descendants = new TreeSet<>(Comparator.comparing(Property::getName));
        descendants.addAll(Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property));

        if (isMap) {
            statements.add(new StringStatement("return this." + property.getName() + ";"));
        } else if (isBuildable(unwrapped)) {
            isNested = true;
            annotations.add(DEPRECATED_ANNOTATION);
            comments.add("This method has been deprecated, please use method " + builderName + " instead.");
            comments.add("@return The buildable object.");
            if (isList || isSet) {
                statements.add(new StringStatement("return build(" + property.getName() + ");"));
            } else if (isOptional) {
                statements.add(new StringStatement("return (" + property.getTypeRef() + ") (this." + property.getName() + "!=null && this." + property.getName() + ".isPresent() ? " + property.getAttribute(INIT_FUNCTION).apply(Collections.singletonList("this." + property.getName() + ".get().build()")) + " : " + property.getAttribute(INIT) + ");"));
            } else {
                statements.add(new StringStatement("return this." + property.getName() + "!=null?this." + property.getName() + ".build():null;"));
            }
        } else if (!descendants.isEmpty()) {
            isNested = true;
            annotations.add(DEPRECATED_ANNOTATION);
            comments.add("This method has been deprecated, please use method " + builderName + " instead.");
            comments.add("@return The buildable object.");
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
            TypeRef builderRef = BuilderUtils.buildableRef(unwrapped);

            methods.add(new MethodBuilder(getter)
                    .removeFromAnnotations(DEPRECATED_ANNOTATION)
                    .withComments()
                    .withName("build" + property.getNameCapitalized())
                    .build());

            if (isList) {
                methods.add(BUILD_INDEXED.method(property, unwrapped));
                methods.add(BUILD_FIRST.method(property, unwrapped));
                methods.add(BUILD_LAST.method(property, unwrapped));
            }

            if (isList || isSet) {
                methods.add(MatchingType.BUILD.method(property, unwrapped, predicate, builderRef, Collections.emptyList(), Collections.emptyList()));
                methods.add(MatchingType.HAS.method(property, BOOLEAN_REF, predicate, builderRef, Collections.emptyList(), Collections.emptyList()));
                methods.add(MatchingType.REMOVE.method(property, BOOLEAN_REF, predicate, builderRef, Collections.emptyList(), Collections.emptyList()));
            }
        } else if (isList) {

            methods.add(GET_INDEXED.method(property, unwrapped));
            methods.add(GET_FIRST.method(property, unwrapped));
            methods.add(GET_LAST.method(property, unwrapped));
            methods.add(MatchingType.GET.method(property, unwrapped, predicate, unwrapped, annotations, Collections.emptyList()));
            methods.add(MatchingType.HAS.method(property, BOOLEAN_REF, predicate, unwrapped, annotations, Collections.emptyList()));
        }
        return methods;
    });

    static final Function<Property, List<Method>> GETTER_ARRAY = FunctionFactory.cache(property -> {
        List<Method> methods = new ArrayList<>();
        List<AnnotationRef> annotations = new ArrayList<>();
        List<String> comments = new ArrayList<>();

        String getterName = Getter.name(property);
        String builderName = "build" + property.getNameCapitalized();
        TypeRef unwrapped = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(property.getTypeRef());
        TypeDef predicate = typeGenericOf(BuilderContextManager.getContext().getPredicateClass(), T);

        TypeRef type = property.getTypeRef();
        Boolean isBuildable = isBuildable(type);
        TypeRef targetType = isBuildable ? VISITABLE_BUILDER.apply(type) : UNWRAP_ARRAY_OF.apply(type);
        String body = String.format(isBuildable ? BUILDABLE_ARRAY_GETTER_TEXT : SIMPLE_ARRAY_GETTER_TEXT,
                property.getName(),
                property.getName(),
                type.toString(),
                unwrapped.toString(),
                targetType.toString(),
                property.getName()
        );

        if (isBuildable) {
            annotations.add(DEPRECATED_ANNOTATION);
            comments.add("This method has been deprecated, please use method " + builderName + " instead.");
        }
        Method getter = new MethodBuilder()
                .withAnnotations(annotations)
                .withComments(comments)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withName(getterName)
                .withReturnType(property.getTypeRef())
                .withArguments()
                .withNewBlock()
                .addNewStringStatementStatement(body)
                .endBlock()
                .addToAttributes(ALSO_IMPORT, isBuildable ? Collections.singletonList(BuilderContextManager.getContext().getVisitableBuilderInterface().toInternalReference()) : Collections.EMPTY_LIST)
                .build();

        methods.add(getter);

        if (isBuildable) {
            TypeRef builderRef = BuilderUtils.buildableRef(unwrapped);

            methods.add(new MethodBuilder(getter)
                    .removeFromAnnotations(DEPRECATED_ANNOTATION)
                    .withComments()
                    .withName(builderName)
                    .build());

            methods.add(BUILD_INDEXED.method(property, unwrapped));
            methods.add(BUILD_FIRST.method(property, unwrapped));
            methods.add(BUILD_LAST.method(property, unwrapped));

            methods.add(MatchingType.BUILD.method(property, unwrapped, predicate, builderRef, Collections.emptyList(), Collections.emptyList()));

            methods.add(MatchingType.HAS.method(property, BOOLEAN_REF, predicate, builderRef, Collections.emptyList(), Collections.emptyList()));
        }
        return methods;
    });

    static final Function<Property, List<Method>> ADD_TO_COLLECTION = FunctionFactory.cache(new Function<Property, List<Method>>() {
        public List<Method> apply(final Property property) {
            List<Method> methods = new ArrayList<>();
            TypeRef baseType = UNWRAP_COLLECTION_OF.apply(property.getTypeRef());

            TypeRef builderType = VISITABLE_BUILDER.apply(baseType);
            Property builderProperty = new PropertyBuilder(property).withName("builder").withTypeRef(builderType).build();

            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            final TypeRef unwrapped = BOXED_OF.apply(combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef()));
            List<ClassRef> alsoImport = new ArrayList<>();

            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(unwrapped.withDimensions(1))
                    .build();

            Property unwrappedProperty = new PropertyBuilder(property)
                    .withName("item")
                    .withTypeRef(unwrapped).build();

            List<TypeParamDef> parameters = new ArrayList<>();

            String addVarargMethodName = "addTo" + property.getNameCapitalized();
            String setMethodName = "setTo" + property.getNameCapitalized();
            String addAllMethodName = "addAllTo" + BuilderUtils.fullyQualifiedNameDiff(baseType, originTypeDef) + property.getNameCapitalized();

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
                    .withStatements(new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                            new StringStatement("this." + propertyName + ".add(index, item);"),
                            new StringStatement("return (" + returnType + ")this;"))
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
                    .withStatements(new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                            new StringStatement("this." + propertyName + ".set(index, item); return (" + returnType + ")this;"))
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();


            List<Statement> statements = new ArrayList<>();

            List<Statement> varArgInit = new ArrayList<>();
            List<Statement> collectionInit = new ArrayList<>();

            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                final ClassRef targetType = (ClassRef) unwrapped;

                String targetClass = targetType.getName();
                parameters.addAll(targetType.getDefinition().getParameters());
                String builderClass = targetClass + "Builder";

                //We need to do it more
                alsoImport.add(BUILDER.apply(targetType.getDefinition()).toInternalReference());
                statements.add(new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"));
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.get(\"" + propertyName + "\").add(builder);this." + propertyName + ".add(builder);} return (" + returnType + ")this;"));

                addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(
                                new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                                new StringStatement(builderClass + " builder = new " + builderClass + "(item);_visitables.get(\"" + propertyName + "\").add(index >= 0 ? index : _visitables.get(\"" + propertyName + "\").size(), builder);this." + propertyName + ".add(index >= 0 ? index : " + propertyName + ".size(), builder); return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

                setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(
                                new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                                new StringStatement(builderClass + " builder = new " + builderClass + "(item);"),
                                new StringStatement("if (index < 0 || index >= _visitables.get(\"" + propertyName + "\").size()) { _visitables.get(\"" + propertyName + "\").add(builder); } else { _visitables.get(\"" + propertyName + "\").set(index, builder);}"),
                                new StringStatement("if (index < 0 || index >= " + propertyName + ".size()) { " + propertyName + ".add(builder); } else { " + propertyName + ".set(index, builder);}"),
                                new StringStatement(" return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) unwrapped;
                parameters.addAll(targetType.getDefinition().getParameters());
                varArgInit.add(new StringStatement(" if (items != null && items.length > 0 && this." + propertyName + "== null) {this." + propertyName + " = new ArrayList<VisitableBuilder<? extends " + targetType + ",?>>();}"));
                collectionInit.add(new StringStatement(" if (items != null && items.size() > 0 && this." + propertyName + "== null) {this." + propertyName + " = new ArrayList<VisitableBuilder<? extends " + targetType + ",?>>();}"));

                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) { "));
                statements.add(createAddToDescendants("addTo", descendants, false));
                statements.add(createAddToDescendantsFallback(targetType.getName(), propertyName));
                statements.add(new StringStatement("} return (" + returnType + ")this;"));

                addSingleItemAtIndex = new MethodBuilder(addSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(createAddToDescendants("addTo", descendants, true), new StringStatement("return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

                setSingleItemAtIndex = new MethodBuilder(setSingleItemAtIndex)
                        .withParameters(parameters)
                        .editBlock()
                        .withStatements(createAddToDescendants("setTo", descendants, true), new StringStatement("return (" + returnType + ")this;"))
                        .endBlock()
                        .build();

                methods.add(new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withParameters(parameters)
                        .withName(addVarargMethodName)
                        .withReturnType(returnType)
                        .withArguments(builderProperty)
                        .withNewBlock()
                        .addToStatements(
                                new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                                new StringStatement("_visitables.get(\"" + propertyName + "\").add(builder);this." + propertyName + ".add(builder); return (" + returnType + ")this;")
                        )
                        .endBlock()
                        .build());

                methods.add(new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withParameters(parameters)
                        .withName(addVarargMethodName)
                        .withReturnType(returnType)
                        .withArguments(INDEX, builderProperty)
                        .withNewBlock()
                        .addToStatements(
                                new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                                new StringStatement("_visitables.get(\"" + propertyName + "\").add(index, builder);this." + propertyName + ".add(index, builder); return (" + returnType + ")this;")
                        )
                        .endBlock()
                        .build());

            } else {
                statements.add(new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"));
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
                    .addAllToStatements(varArgInit)
                    .addAllToStatements(statements)
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
                    .addAllToStatements(collectionInit)
                    .addAllToStatements(statements)
                    .endBlock()
                    .addToAttributes(Attributeable.ALSO_IMPORT, alsoImport)
                    .build();

            methods.add(addSingleItemAtIndex);
            methods.add(setSingleItemAtIndex);
            methods.add(addVaragToCollection);
            methods.add(addAllToCollection);

            return methods;
        }

        private Statement createAddToDescendants(final String prefix, Set<Property> descendants, final boolean useIndex) {
            return new StringStatement(StringUtils.join(descendants, item -> {
                TypeRef itemRef = combine(UNWRAP_COLLECTION_OF, ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(item.getTypeRef());
                String className = ((ClassRef) itemRef).getName();
                String methodName = prefix + item.getNameCapitalized();
                return "if (item instanceof " + className + "){" + methodName + "(" + (useIndex ? "index, " : "") + "(" + className + ")item);}\n";
            }, " else "));
        }

        private Statement createAddToDescendantsFallback(String type, String name) {
            return new StringStatement("else {  VisitableBuilder<? extends " + type + ",?> builder = builderOf(item); _visitables.get(\"" + name + "\").add(builder);this." + name + ".add(builder); }");
        }
    });


    static final Function<Property, List<Method>> REMOVE_FROM_COLLECTION = FunctionFactory.cache(new Function<Property, List<Method>>() {
        public List<Method> apply(final Property property) {
            List<Method> methods = new ArrayList<>();
            TypeRef baseType = UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
            TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

            TypeRef builderType = VISITABLE_BUILDER.apply(baseType);
            Property builderProperty = new PropertyBuilder(property).withName("builder").withTypeRef(builderType).build();

            TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
            final TypeRef unwrapped = BOXED_OF.apply(combine(UNWRAP_COLLECTION_OF).apply(property.getTypeRef()));
            List<ClassRef> alsoImport = new ArrayList<>();
            Property item = new PropertyBuilder(property)
                    .withName("items")
                    .withTypeRef(unwrapped.withDimensions(1))
                    .build();

            List<TypeParamDef> parameters = new ArrayList<>();

            String removeVarargMethodName = "removeFrom" + property.getNameCapitalized();
            String removeAllMethdoName = "removeAllFrom" + BuilderUtils.fullyQualifiedNameDiff(baseType, originTypeDef) + property.getNameCapitalized();

            String propertyName = property.getName();
            List<Statement> statements = new ArrayList<>();
            Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(property);
            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                final ClassRef targetType = (ClassRef) unwrapped;
                if (property.hasAttribute(Constants.DESCENDANT_OF)) {
                    Property attrValue = property.getAttribute(Constants.DESCENDANT_OF);
                    if (attrValue != null) {
                        propertyName = attrValue.getName();
                    }
                }
                String targetClass = targetType.getName();
                parameters.addAll(targetType.getDefinition().getParameters());
                String builderClass = targetClass + "Builder";

                //We need to do it more elegantly
                alsoImport.add(BUILDER.apply(targetType.getDefinition()).toInternalReference());
                statements.add(new StringStatement("for (" + targetClass + " item : items) {" + builderClass + " builder = new " + builderClass + "(item);_visitables.get(\"" + propertyName + "\").remove(builder);if (this." + propertyName + " != null) {this." + propertyName + ".remove(builder);}} return (" + returnType + ")this;"));
            } else if (!descendants.isEmpty()) {
                final ClassRef targetType = (ClassRef) unwrapped;
                parameters.addAll(targetType.getDefinition().getParameters());
                statements.add(new StringStatement("for (" + targetType.toString() + " item : items) {" + StringUtils.join(descendants, item1 -> {
                    TypeRef itemRef = combine(UNWRAP_COLLECTION_OF, ARRAY_OF).apply(item1.getTypeRef());
                    String className = ((ClassRef) itemRef).getName();
                    String removeFromMethodName = "removeFrom" + item1.getNameCapitalized();
                    return "if (item instanceof " + className + "){" + removeFromMethodName + "((" + className + ")item);}\n";
                }, " else ")));

                statements.add(createRemoveFromDescendantsFallback(targetType.getName(), property.getName()));
                statements.add(new StringStatement("} return (" + returnType + ")this;"));

                methods.add(new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withParameters(parameters)
                        .withName(removeVarargMethodName)
                        .withReturnType(returnType)
                        .withArguments(builderProperty)
                        .withNewBlock()
                        .addToStatements(
                                new StringStatement("if (this." + propertyName + " == null) {this." + propertyName + " = " + property.getAttribute(LAZY_INIT) + ";}"),
                                new StringStatement("_visitables.get(\"" + propertyName + "\").remove(builder);this." + propertyName + ".remove(builder); return (" + returnType + ")this;")
                        )
                        .endBlock()
                        .build());
            } else {
                statements.add(new StringStatement("for (" + unwrapped.toString() + " item : items) {if (this." + property.getName() + "!= null){ this." + property.getName() + ".remove(item);}} return (" + returnType + ")this;"));
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

            if (isBuildable(unwrapped) && !isAbstract(unwrapped)) {
                TypeDef predicate = typeGenericOf(BuilderContextManager.getContext().getPredicateClass(), T);
                TypeRef builder = BUILDER.apply(((ClassRef) unwrapped).getDefinition()).toInternalReference();
                methods.add(new MethodBuilder()
                        .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                        .withReturnType(returnType)
                        .withParameters(parameters)
                        .withName("removeAllMatchingFrom" + property.getNameCapitalized())
                        .addNewArgument()
                        .withName("predicate")
                        .withTypeRef(predicate.toReference(builder))
                        .endArgument()
                        .withNewBlock()
                        .addNewStringStatementStatement("for (" + unwrapped + " item : items) {")
                        .addNewStringStatementStatement(builder + " builder = new " + builder + "(item);")
                        .addNewStringStatementStatement("if (predicate.apply(builder)) {")
                        .addNewStringStatementStatement("_visitables.get(\"" + propertyName + "\").remove(builder);")
                        .addNewStringStatementStatement("if (this." + propertyName + " != null) {this." + propertyName + ".remove(builder);}")
                        .addNewStringStatementStatement("}}")
                        .addNewStringStatementStatement(" return (" + returnType + ")this;")
                        .endBlock()
                        .build());
            }

            return methods;
        }

        private Statement createRemoveFromDescendantsFallback(String type, String name) {
            return new StringStatement("else {  VisitableBuilder<? extends " + type + ",?> builder = builderOf(item); _visitables.get(\"" + name + "\").remove(builder);this." + name + ".remove(builder); }");
        }
    });

    static final Function<Property, Method> ADD_MAP_TO_MAP = FunctionFactory.cache(property -> {
        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeRef mapType = property.getTypeRef();
        Property mapProperty = new PropertyBuilder().withName("map").withTypeRef(mapType).build();
        String methodName = "addTo" + property.getNameCapitalized();
        return new MethodBuilder()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withName(methodName)
                .withReturnType(returnType)
                .withArguments(mapProperty)
                .withNewBlock()
                .addNewStringStatementStatement("if(this." + property.getName() + " == null && map != null) { this." + property.getName() + " = " + property.getAttribute(INIT_FUNCTION).apply(Collections.emptyList()) + "; }")
                .addNewStringStatementStatement("if(map != null) { this." + property.getName() + ".putAll(map);} return (" + returnType + ")this;")
                .endBlock()
                .build();
    });

    static final Function<Property, Method> ADD_TO_MAP = FunctionFactory.cache(property -> {
        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        if (!(property.getTypeRef() instanceof ClassRef)) {
            throw new IllegalStateException("Expected Map type and found:" + property.getTypeRef());
        }
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
                .addNewStringStatementStatement("if(this." + property.getName() + " == null && key != null && value != null) { this." + property.getName() + " = " + property.getAttribute(INIT_FUNCTION).apply(Collections.emptyList()) + "; }")
                .addNewStringStatementStatement("if(key != null && value != null) {this." + property.getName() + ".put(key, value);} return (" + returnType + ")this;")
                .endBlock()
                .build();
    });


    static final Function<Property, Method> REMOVE_MAP_FROM_MAP = FunctionFactory.cache(property -> {
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
                .addNewStringStatementStatement("if(this." + property.getName() + " == null) { return (" + returnType + ") this; }")
                .addNewStringStatementStatement("if(map != null) { for(Object key : map.keySet()) {if (this." + property.getName() + " != null){this." + property.getName() + ".remove(key);}}} return (" + returnType + ")this;")
                .endBlock()
                .build();
    });

    static final Function<Property, Method> REMOVE_FROM_MAP = FunctionFactory.cache(property -> {
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
                .addNewStringStatementStatement("if(this." + property.getName() + " == null) { return (" + returnType + ") this; }")
                .addNewStringStatementStatement("if(key != null && this." + property.getName() + " != null) {this." + property.getName() + ".remove(key);} return (" + returnType + ")this;")
                .endBlock()
                .build();
    });

    static final Function<Property, Method> WITH_NEW_NESTED = property -> {
        ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

        TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

        //Let's reload the class from the repository if available....
        TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
        if (propertyTypeDef != null) {
            baseType = propertyTypeDef.toInternalReference();
        }

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
        TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

        List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
        List<TypeRef> typeArguments = new ArrayList<>();
        for (TypeRef arg : baseType.getArguments()) {
            typeArguments.add(arg);
        }
        typeArguments.add(returnType);


        ClassRef rewraped = nestedType.toReference(typeArguments);
        ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

        boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
        String prefix = isCollection ? "addNew" : "withNew";


        prefix += BuilderUtils.fullyQualifiedNameDiff(baseType, originTypeDef);
        String methodName = (prefix + (isCollection
                ? Singularize.FUNCTION.apply(property.getNameCapitalized())
                : property.getNameCapitalized()));

        return new MethodBuilder()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withParameters(parameters)
                .withReturnType(rewraped)
                .withName(methodName)
                .withNewBlock()
                .addNewStringStatementStatement("return new " + rewrapedImpl.getName() + "();")
                .endBlock()
                .build();

    };

    static final Function<Property, Set<Method>> WITH_NESTED_INLINE = property -> {
        TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);

        if (originTypeDef.isEnum()) {
            return Collections.emptySet();
        }

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        Set<Method> result = new LinkedHashSet<>();
        TypeRef unwrappedType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
        TypeDef baseType = DefinitionRepository.getRepository().getDefinition(unwrappedType);

        for (Method constructor : getInlineableConstructors(property)) {
            boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());
            String ownPrefix = isCollection ? "addNew" : "withNew";

            ownPrefix += BuilderUtils.fullyQualifiedNameDiff(baseType.toInternalReference(), originTypeDef);
            String ownName = ownPrefix + (isCollection
                    ? Singularize.FUNCTION.apply(property.getNameCapitalized())
                    : property.getNameCapitalized());

            String delegatePrefix = IS_COLLECTION.apply(property.getTypeRef()) ? "addTo" : "with";
            String delegateName = delegatePrefix + property.getNameCapitalized();

            String args = StringUtils.join(constructor.getArguments(), Property::getName, ", ");


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
    };

    static final Function<Property, Method> EDIT_OR_NEW = property -> {
        ClassRef baseType = (ClassRef) property.getTypeRef();

        TypeRef unwrappedType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(baseType);
        if (!(unwrappedType instanceof ClassRef)) {
            throw new IllegalStateException("Expected Editable/Buildable type and found:" + unwrappedType);
        }

        ClassRef unwrappedClassRef = (ClassRef) unwrappedType;
        ClassRef builderType = SHALLOW_BUILDER.apply(unwrappedClassRef.getDefinition()).toReference();

        //Let's reload the class from the repository if available....
        TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
        if (propertyTypeDef != null) {
            baseType = propertyTypeDef.toInternalReference();
        }

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);

        List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
        List<TypeRef> typeArguments = new ArrayList<>();
        for (TypeRef ignore : baseType.getArguments()) {
            typeArguments.add(Q);
        }
        typeArguments.add(returnType);
        ClassRef rewraped = nestedType.toReference(typeArguments);

        String prefix = "editOrNew";
        String methodNameBase = property.getNameCapitalized();
        String methodName = prefix + methodNameBase;

        String statement =
                isOptional(baseType)
                        ? "return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null  && get" + methodNameBase + "().isPresent() ? get" + methodNameBase + "().get() : new " + builderType.getName() + "().build());"
                        : "return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null ? get" + methodNameBase + "(): new " + builderType.getName() + "().build());";
        return new MethodBuilder()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withParameters(parameters)
                .withReturnType(rewraped)
                .withName(methodName)
                .withNewBlock()
                .addNewStringStatementStatement(statement)
                .endBlock()
                .build();

    };

    static final Function<Property, Method> EDIT_OR_NEW_LIKE = property -> {
        TypeRef unwrappedType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

        if (!(unwrappedType instanceof ClassRef)) {
            throw new IllegalStateException("Expected Editable/Buildable type and found:" + unwrappedType);
        }

        ClassRef unwrappedClassRef = (ClassRef) unwrappedType;
        ClassRef baseType = (ClassRef) property.getTypeRef();

        //Let's reload the class from the repository if available....
        TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(unwrappedClassRef.getDefinition().getFullyQualifiedName());
        if (propertyTypeDef != null) {
            baseType = propertyTypeDef.toInternalReference();
        }

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);

        List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
        List<TypeRef> typeArguments = new ArrayList<>();
        for (TypeRef ignore : baseType.getArguments()) {
            typeArguments.add(Q);
        }
        typeArguments.add(returnType);
        ClassRef rewraped = nestedType.toReference(typeArguments);

        String prefix = "editOrNew";
        String suffix = "Like";
        String methodNameBase = property.getNameCapitalized();
        String methodName = prefix + methodNameBase + suffix;

        String statement = isOptional(property.getTypeRef())
                ? "return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null && get" + methodNameBase + "().isPresent() ? get" + methodNameBase + "().get(): item);"
                : "return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null ? get" + methodNameBase + "(): item);";

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
                .addNewStringStatementStatement(statement)
                .endBlock()
                .build();

    };

    static final Function<Property, Method> WITH_NEW_LIKE_NESTED = property -> {
        if (!(property.getTypeRef() instanceof ClassRef)) {
            throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
        }
        ClassRef baseType = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
        //Let's reload the class from the repository if available....
        TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((baseType).getDefinition().getFullyQualifiedName());
        if (propertyTypeDef != null) {
            baseType = propertyTypeDef.toInternalReference();
        }

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);
        TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

        List<TypeParamDef> parameters = baseType.getDefinition().getParameters();
        List<TypeRef> typeArguments = new ArrayList<>();
        for (TypeRef ignore : baseType.getArguments()) {
            typeArguments.add(Q);
        }
        typeArguments.add(returnType);

        ClassRef rewraped = nestedType.toReference(typeArguments);
        ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

        boolean isCollection = IS_COLLECTION.apply(property.getTypeRef());

        String prefix = isCollection ? "addNew" : "withNew";
        String suffix = "Like";
        String methodName = (prefix + (isCollection
                ? Singularize.FUNCTION.apply(property.getNameCapitalized())
                : property.getNameCapitalized()) + suffix);

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
                .addNewStringStatementStatement("return new " + rewrapedImpl.getName() + "(" + (isCollection ? "-1, " : "") + "item);")
                .endBlock()
                .build();

    };

    static final Function<Property, Method> WITH_NEW_LIKE_NESTED_AT_INDEX = property -> {
        Method method = WITH_NEW_LIKE_NESTED.apply(property);

        if (!(property.getTypeRef() instanceof ClassRef)) {
            throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
        }

        ClassRef baseType = (ClassRef) UNWRAP_COLLECTION_OF.apply(property.getTypeRef());
        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeDef nestedTypeImpl = PropertyAs.NESTED_CLASS_TYPE.apply(property);

        List<TypeRef> typeArguments = new ArrayList<>();
        for (TypeRef ignore : baseType.getArguments()) {
            typeArguments.add(Q);
        }
        typeArguments.add(returnType);
        ClassRef rewrapedImpl = nestedTypeImpl.toReference(typeArguments);

        return new MethodBuilder(method)
                .addToArguments(0, INDEX)
                .withName(method.getName().replaceFirst("add", "set"))
                .editBlock()
                .withStatements(new StringStatement("return new " + rewrapedImpl.getName() + "(index, item);"))
                .endBlock()
                .build();
    };

    static final Function<Property, List<Method>> EDIT_NESTED = property -> {
        List<Method> methods = new ArrayList<>();
        TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);
        if (!(property.getTypeRef() instanceof ClassRef)) {
            throw new IllegalStateException("Expected Nestable / Buildable type and found:" + property.getTypeRef());
        }
        ClassRef unwrapped = (ClassRef) combine(UNWRAP_COLLECTION_OF, UNWRAP_OPTIONAL_OF, UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());
        TypeRef builderRef = BuilderUtils.buildableRef(unwrapped);
        TypeDef predicate = typeGenericOf(BuilderContextManager.getContext().getPredicateClass(), T);

        //Let's reload the class from the repository if available....
        TypeDef propertyTypeDef = BuilderContextManager.getContext().getDefinitionRepository().getDefinition((unwrapped).getDefinition().getFullyQualifiedName());
        if (propertyTypeDef != null) {
            unwrapped = propertyTypeDef.toInternalReference();
        }

        TypeRef returnType = property.hasAttribute(GENERIC_TYPE_REF) ? property.getAttribute(GENERIC_TYPE_REF) : T_REF;
        TypeDef nestedType = PropertyAs.NESTED_INTERFACE_TYPE.apply(property);

        List<TypeRef> typeArguments = new ArrayList<>();
        for (TypeRef ignore : unwrapped.getArguments()) {
            typeArguments.add(Q);
        }
        typeArguments.add(returnType);

        ClassRef rewraped = nestedType.toReference(typeArguments);

        String prefix = "edit";
        prefix += BuilderUtils.fullyQualifiedNameDiff(property.getTypeRef(), originTypeDef);
        String methodNameBase = property.getNameCapitalized();
        String methodName = prefix + methodNameBase;

        String statement = isOptional(property.getTypeRef())
                ? "return withNew" + methodNameBase + "Like(get" + methodNameBase + "() != null ? get" + methodNameBase + "().orElse(null) : null);"
                : "return withNew" + methodNameBase + "Like(get" + methodNameBase + "());";

        Method base = new MethodBuilder()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withReturnType(rewraped)
                .withName(methodName)
                .withNewBlock()
                .addNewStringStatementStatement(statement)
                .endBlock()
                .build();


        if (isList(property.getTypeRef()) || isArray(property.getTypeRef())) {
            String suffix = Singularize.FUNCTION.apply(property.getNameCapitalized());
            methods.add(new MethodBuilder(base)
                    .withArguments(INDEX)
                    .withName("edit" + suffix)
                    .editBlock()
                    .withStatements(
                            new StringStatement("if (" + property.getName() + ".size() <= index) throw new RuntimeException(\"Can't edit " + property.getName() + ". Index exceeds size.\");"),
                            new StringStatement("return setNew" + suffix + "Like(index, build" + suffix + "(index));")
                    )
                    .endBlock()
                    .build());

            methods.add(new MethodBuilder(base)
                    .withName("editFirst" + suffix)
                    .withArguments()
                    .editBlock()
                    .withStatements(
                            new StringStatement("if (" + property.getName() + ".size() == 0) throw new RuntimeException(\"Can't edit first " + property.getName() + ". The list is empty.\");"),
                            new StringStatement("return setNew" + suffix + "Like(0, build" + suffix + "(0));"))
                    .endBlock()
                    .build());

            methods.add(new MethodBuilder(base)
                    .withName("editLast" + suffix)
                    .withArguments()
                    .editBlock()
                    .withStatements(
                            new StringStatement("int index = " + property.getName() + ".size() - 1;"),
                            new StringStatement("if (index < 0) throw new RuntimeException(\"Can't edit last " + property.getName() + ". The list is empty.\");"),
                            new StringStatement("return setNew" + suffix + "Like(index, build" + suffix + "(index));"))
                    .endBlock()
                    .build());

            methods.add(new MethodBuilder(base)
                    .withName("editMatching" + suffix)
                    .addNewArgument()
                    .withName("predicate")
                    .withTypeRef(predicate.toReference(builderRef))
                    .endArgument()
                    .editBlock()
                    .withStatements(
                            new StringStatement("int index = -1;"),
                            new StringStatement("for (int i=0;i<" + property.getName() + ".size();i++) { "),
                            new StringStatement("if (predicate.apply(" + property.getName() + ".get(i))) {index = i; break;}"),
                            new StringStatement("} "),
                            new StringStatement("if (index < 0) throw new RuntimeException(\"Can't edit matching " + property.getName() + ". No match found.\");"),
                            new StringStatement("return setNew" + suffix + "Like(index, build" + suffix + "(index));"))
                    .endBlock()
                    .build());
        } else {
            methods.add(base);
        }
        return methods;
    };


    static final Function<Property, Method> AND = new Function<Property, Method>() {
        public Method apply(Property property) {
            String classPrefix = getClassPrefix(property);


            boolean isArray = TypeUtils.isArray(property.getTypeRef());
            boolean isList = TypeUtils.isList(property.getTypeRef());

            String prefix = isArray || isList ? "setTo" : "with";
            String withMethodName = prefix + property.getNameCapitalized();

            return new MethodBuilder()
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withReturnType(N_REF)
                    .withName("and")
                    .withNewBlock()
                    .addNewStringStatementStatement("return (N) " + classPrefix + withMethodName + "(" +
                            (isArray || isList ? "index, " : "")
                            + "builder.build());")
                    .endBlock()
                    .build();

        }

        private String getClassPrefix(Property property) {
            TypeDef memberOf = property.getAttribute(OUTER_CLASS);
            if (memberOf != null) {
                return memberOf.getName() + ".this.";
            } else {
                return "";
            }
        }

    };

    static final Function<Property, Method> END = FunctionFactory.cache(property -> {
        TypeDef originTypeDef = property.getAttribute(Constants.ORIGIN_TYPEDEF);
        String methodName = "end" + BuilderUtils.fullyQualifiedNameDiff(property.getTypeRef(), originTypeDef) + capitalizeFirst(IS_COLLECTION.apply(property.getTypeRef())
                ? Singularize.FUNCTION.apply(property.getNameCapitalized())
                : property.getNameCapitalized());

        return new MethodBuilder()
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withReturnType(N_REF)
                .withName(methodName)
                .withNewBlock()
                .addNewStringStatementStatement("return and();")
                .endBlock()
                .build();
    });
}
