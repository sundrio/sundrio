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
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.functions.Collections;
import io.sundr.codegen.model.*;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.List;

import static io.sundr.builder.internal.utils.BuilderUtils.*;
import static io.sundr.codegen.Constants.*;


public class TypeAs {
    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> combine(final Function<T, T>... functions) {
        return item -> {
            T result = item;
            for (Function<T, T> f : functions) {
                result = f.apply(result);
            }
            return result;
        };
    }

    static final Function<TypeDef, TypeDef> SHALLOW_BUILDER = item -> new TypeDefBuilder(item)
            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
            .withName(item.getName() + "Builder")
            .withInnerTypes()
            .build();

    private static final Function<TypeDef, TypeDef> SHALLOW_FLUENT = item -> {
        List<TypeParamDef> parameters = new ArrayList<>(item.getParameters());
        parameters.add(getNextGeneric(item));

        return new TypeDefBuilder(item)
                .withKind(Kind.INTERFACE)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withName(item.getName() + "Fluent")
                .withParameters(parameters)
                .withInnerTypes()
                .build();
    };

    static final Function<TypeDef, TypeDef> FLUENT_INTERFACE = item -> {
        BuilderContext ctx = BuilderContextManager.getContext();
        TypeDef fluent = SHALLOW_FLUENT.apply(item);

        List<TypeParamDef> parameters = new ArrayList<>(item.getParameters());
        List<TypeRef> superClassParameters = new ArrayList<>();

        TypeParamDef nextParameter = getNextGeneric(item, parameters);

        ClassRef builableSuperClassRef = findBuildableSuperClassRef(item);

        TypeDef buildableSuperClass = findBuildableSuperClass(item);
        if (builableSuperClassRef != null) {
            superClassParameters.addAll(builableSuperClassRef.getArguments());
        }

        TypeParamDef parameterFluent = new TypeParamDefBuilder(nextParameter).addToBounds(fluent.toInternalReference()).build();
        parameters.add(parameterFluent);
        superClassParameters.add(parameterFluent.toReference());

        TypeDef superClass = buildableSuperClass != null
                ? SHALLOW_FLUENT.apply(buildableSuperClass)
                : ctx.getFluentInterface();

        return new TypeDefBuilder(item)
                .withKind(Kind.INTERFACE)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withName(item.getName() + "Fluent")
                .withPackageName(item.getPackageName())
                .withParameters(parameters)
                .withExtendsList(superClass.toReference(superClassParameters))
                .withImplementsList()
                .withInnerTypes()
                .build();
    };

    static final Function<TypeDef, TypeDef> FLUENT_IMPL = new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            TypeDef fluent = SHALLOW_FLUENT.apply(item);

            List<TypeParamDef> parameters = new ArrayList<>(item.getParameters());
            List<TypeRef> superClassParameters = new ArrayList<>();
            TypeParamDef nextParameter = getNextGeneric(item, parameters);

            ClassRef builableSuperClassRef = findBuildableSuperClassRef(item);
            if (builableSuperClassRef != null) {
                superClassParameters.addAll(builableSuperClassRef.getArguments());
            }

            TypeParamDef parameterFluent = new TypeParamDefBuilder(nextParameter).addToBounds(fluent.toInternalReference()).build();
            parameters.add(parameterFluent);
            superClassParameters.add(parameterFluent.toReference());

            TypeDef buildableSuperClass = findBuildableSuperClass(item);

            TypeDef superClass = buildableSuperClass != null
                    ? FLUENT_IMPL.apply(buildableSuperClass)
                    : ctx.getBaseFluentClass();

            return new TypeDefBuilder(item)
                    .withKind(Kind.CLASS)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .withName(item.getName() + "FluentImpl")
                    .withPackageName(item.getPackageName())
                    .withParameters(parameters)
                    .withExtendsList(superClass.toReference(superClassParameters))
                    .withImplementsList(SHALLOW_FLUENT.apply(item).toInternalReference())
                    .withInnerTypes()
                    .build();
        }

    };


    static final Function<TypeDef, ClassRef> FLUENT_REF = item -> {
        List<TypeRef> parameters = new ArrayList<>();
        for (TypeParamDef param : item.getParameters()) {
            parameters.add(param.toReference());
        }
        parameters.add(Q);
        return SHALLOW_FLUENT.apply(item).toReference(parameters);
    };

    public static final Function<TypeDef, TypeDef> BUILDER = item -> {
        TypeDef builder = SHALLOW_BUILDER.apply(item);
        TypeDef fluent = FLUENT_IMPL.apply(item);

        List<TypeRef> parameters = new ArrayList<>();
        for (TypeParamDef param : item.getParameters()) {
            parameters.add(param.toReference());
        }
        parameters.add(builder.toInternalReference());
        return new TypeDefBuilder(item)
                .withKind(Kind.CLASS)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withName(item.getName() + "Builder")
                .withParameters(item.getParameters())
                .withInnerTypes()
                .withExtendsList(fluent.toReference(parameters))
                .withImplementsList(BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(item.toInternalReference(), builder.toInternalReference()))
                .build();
    };

    static final Function<TypeDef, TypeDef> EDITABLE = item -> {
        List<TypeParamDef> parameters = new ArrayList<>(item.getParameters());
        return new TypeDefBuilder(item)
                .withKind(Kind.CLASS)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withName("Editable" + item.getName())
                .withParameters(parameters)
                .withExtendsList(item.toInternalReference())
                .withImplementsList(BuilderContextManager.getContext().getEditableInterface().toReference(SHALLOW_BUILDER.apply(item).toInternalReference()))
                .withInnerTypes()
                .withProperties()
                .withMethods()
                .withConstructors()
                .build();

    };

    public static final Function<TypeRef, ClassRef> VISITABLE_BUILDER = new Function<TypeRef, ClassRef>() {
        public ClassRef apply(TypeRef item) {
            TypeRef baseType = combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(item);
            if (baseType instanceof ClassRef) {
                baseType = new ClassRefBuilder((ClassRef)baseType).withArguments().build();
            }
            WildcardRef wildcardRef = new WildcardRefBuilder().addToBounds(baseType).build();
            return BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(wildcardRef, Q);
        }
    };

    public static final Function<TypeRef, TypeRef> UNWRAP_ARRAY_OF = item -> {
        if (item instanceof PrimitiveRef) {
            return new PrimitiveRefBuilder((PrimitiveRef) item).withDimensions(0).build();
        } else if (item instanceof ClassRef) {
            return new ClassRefBuilder((ClassRef) item).withDimensions(0).build();
        } else if (item instanceof TypeParamRef) {
            return new TypeParamRefBuilder((TypeParamRef) item).withDimensions(0).build();
        } else {
            return item;
        }
    };

    //TODO: Need a home for: .withDefaultImplementation(Constants.ARRAY_LIST)
    private static final Function<TypeRef, TypeRef> LIST_OF = FunctionFactory.cache(Collections.LIST::toReference);

    static final Function<TypeRef, TypeRef> ARRAY_AS_LIST = FunctionFactory.cache(item -> LIST_OF.apply(UNWRAP_ARRAY_OF.apply(item)));

    public static final Function<TypeRef, TypeRef> UNWRAP_COLLECTION_OF = type -> {
        if (type instanceof ClassRef) {
            ClassRef classRef = (ClassRef) type;
            if (Collections.IS_COLLECTION.apply(classRef)) {
                return classRef.getArguments().get(0);
            }
        }
        return type;
    };

    public static final Function<TypeRef, TypeRef> UNWRAP_OPTIONAL_OF = type -> {
        if (type instanceof ClassRef) {
            ClassRef classRef = (ClassRef) type;
            if (TypeUtils.isOptional(classRef)) {
                return classRef.getArguments().get(0);
            }

            if (TypeUtils.isOptionalInt(classRef)) {
                return new TypeDefBuilder().withPackageName("java.lang").withName("Integer").build().toReference();
            }

            if (TypeUtils.isOptionalLong(classRef)) {
                return new TypeDefBuilder().withPackageName("java.lang").withName("Long").build().toReference();
            }

            if (TypeUtils.isOptionalDouble(classRef)) {
                return new TypeDefBuilder().withPackageName("java.lang").withName("Double").build().toReference();
            }
        }
        return type;
    };

    static final Function<TypeRef, TypeRef> BOXED_OF = FunctionFactory.cache(type -> {
        int index = 0;
        for (TypeRef primitive : PRIMITIVE_TYPES) {
            if (primitive.equals(type)) {
                return BOXED_PRIMITIVE_TYPES[index];
            }
            index++;
        }
        return type;
    });

    static final Function<TypeRef, String> PARSER_OF = FunctionFactory.cache(type -> {
        int index = 0;
        for (TypeRef primitive : PRIMITIVE_TYPES) {
            if (primitive.equals(type)) {
                return BOXED_PARSE_METHOD[index];
            }
            index++;
        }
        return null;
    });



    static Function<TypeRef, TypeRef> ARRAY_OF = type -> {
        if (type instanceof ClassRef) {
            return new ClassRefBuilder((ClassRef) type)
                    .withDimensions(1)
                    .build();

        } else if (type instanceof PrimitiveRef) {
            return new PrimitiveRefBuilder((PrimitiveRef) type)
                    .withDimensions(1)
                    .build();

        } else if (type instanceof TypeParamRef) {
            return new TypeParamRefBuilder((TypeParamRef) type)
                    .withDimensions(1)
                    .build();
        } else {
            throw new IllegalStateException("A property type should be either class, primitive or param referemce.");
        }
    };
}
