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

import io.sundr.CachingFunction;
import io.sundr.Function;
import io.sundr.builder.Constants;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.PrimitiveRef;
import io.sundr.codegen.model.PrimitiveRefBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.TypeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.BOXED_PRIMITIVE_TYPES;
import static io.sundr.builder.Constants.PRIMITIVE_TYPES;
import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableSuperClass;
import static io.sundr.builder.internal.utils.BuilderUtils.getNextGeneric;
import static io.sundr.codegen.utils.TypeUtils.classRefOf;
import static io.sundr.codegen.utils.TypeUtils.newTypeParamRef;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public class TypeAs {

    public static final Function<TypeDef, TypeDef> FLUENT_INTERFACE = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();

            TypeDef fluent = SHALLOW_FLUENT.apply(item);
            List<TypeParamDef> generics = new ArrayList<TypeParamDef>(item.getParameters());

            TypeParamDef nextGeneric = getNextGeneric(item, generics);
            TypeParamRef nextGenericRef = nextGeneric.toReference();

            TypeParamDef genericFluent = new TypeParamDefBuilder(nextGeneric)
                    .addNewBound()
                        .withDefinition(fluent)
                        .withArguments(nextGeneric.toReference())
                    .endBound()
                    .build();

            generics.add(genericFluent);

            TypeDef buildableSuperClass = findBuildableSuperClass(item);
            TypeDef superClass = buildableSuperClass != null
                    ? SHALLOW_FLUENT.apply(buildableSuperClass)
                    : ctx.getBaseFluentClass();

            Set<ClassRef> extendsList = new HashSet<ClassRef>();

            if (!superClass.getFullyQualifiedName().equals(ctx.getBaseFluentClass().getFullyQualifiedName())) {
                extendsList.add(superClass.toReference(nextGenericRef));
            }
            extendsList.add(ctx.getFluentInterface().toReference(nextGenericRef));

            return new TypeDefBuilder(item)
                    .withKind(Kind.INTERFACE)
                    .withName(item.getName() + "Fluent")
                    .withPackageName(item.getPackageName())
                    .withParameters(generics)
                    .withExtendsList(extendsList)
                    .build();
        }
    });

    public static final Function<TypeDef, TypeDef> FLUENT_IMPL = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();

            TypeDef fluent = SHALLOW_FLUENT.apply(item);

            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>(item.getParameters());
            TypeParamDef nextParameter = getNextGeneric(item, parameters);
            TypeParamDef genericFluent = new TypeParamDefBuilder(nextParameter).addToBounds(fluent.toInternalReference()).build();
            parameters.add(genericFluent);

            TypeDef buildableSuperClass = findBuildableSuperClass(item);
            TypeDef superClass = buildableSuperClass != null
                    ? FLUENT_IMPL.apply(buildableSuperClass)
                    : ctx.getBaseFluentClass();

            return new TypeDefBuilder(item)
                    .withKind(Kind.CLASS)
                    .withName(item.getName() + "FluentImpl")
                    .withPackageName(item.getPackageName())
                    .withParameters(parameters)
                    .addToExtendsList(classRefOf(superClass))
                    .addToImplementsList(classRefOf(SHALLOW_FLUENT.apply(item)))
                    .build();
        }

    });
    public static final Function<TypeDef, TypeDef> SHALLOW_FLUENT = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>(item.getParameters());
            parameters.add(getNextGeneric(item));

            return new TypeDefBuilder(item)
                    .withKind(Kind.INTERFACE)
                    .withName(item.getName() + "Fluent")
                    .withParameters(parameters)
                    .build();
        }
    });


    public static final Function<TypeDef, ClassRef> FLUENT_REF = CachingFunction.wrap(new Function<TypeDef, ClassRef>() {
        public ClassRef apply(TypeDef item) {
            List<Object> parameters = new ArrayList<Object>(item.getParameters());
            parameters.add(Q);
           return classRefOf(SHALLOW_FLUENT.apply(item), parameters.toArray());
        }
    });

    public static final Function<TypeDef, TypeDef> BUILDER = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            ClassRef builder = classRefOf(SHALLOW_BUILDER.apply(item));
            List<TypeRef> parameterRefs = new ArrayList<TypeRef>();
            for (TypeParamDef parameter : item.getParameters()) {
                parameterRefs.add(newTypeParamRef(parameter.getName()));
            }
            parameterRefs.add(builder);
            ClassRef fluent = classRefOf(FLUENT_IMPL.apply(item), parameterRefs.toArray(new TypeRef[parameterRefs.size()]));

            return new TypeDefBuilder(item)
                    .withKind(Kind.CLASS)
                    .withName(item.getName() + "Builder")
                    .withParameters(item.getParameters())
                    .addToExtendsList(fluent)
                    .addToImplementsList(classRefOf(BuilderContextManager.getContext().getVisitableBuilderInterface(), item, builder))
                    .build();

        }
    });
    public static final Function<TypeDef, TypeDef> EDITABLE = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>();
            for (TypeParamDef generic : item.getParameters()) {
                parameters.add(generic);
            }
            return new TypeDefBuilder(item)
                    .withKind(Kind.CLASS)
                    .withName("Editable" + item.getName())
                    .withParameters(parameters)
                    .addToExtendsList(classRefOf(item))
                    .addToImplementsList(classRefOf(BuilderContextManager.getContext().getEditableInterface(), SHALLOW_BUILDER.apply(item)))
                    .build();

        }
    });

    public static final Function<TypeDef, TypeDef> SHALLOW_INLINEABLE = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            return item;
        }
    });

    public static final Function<TypeDef, TypeDef> INLINEABLE = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            TypeDef fluent = FLUENT_IMPL.apply(item);
            return new TypeDefBuilder(item)
                    .withParameters(item.getParameters())
                    .withExtendsList(classRefOf(fluent, SHALLOW_INLINEABLE.apply(item)))
                    .build();

        }
    });

    public static final Function<TypeDef, TypeDef> SHALLOW_BUILDER = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            return new TypeDefBuilder(item)
                    .withName(item.getName() + "Builder")
                    .build();
        }
    });

    public static final Function<TypeDef, TypeRef> VISITABLE_BUILDER = CachingFunction.wrap(new Function<TypeDef, TypeRef>() {
        public TypeRef apply(TypeDef item) {
            TypeRef baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(classRefOf(item));
            return classRefOf(BuilderContextManager.getContext().getVisitableBuilderInterface(), baseType, Q);
        }
    });


    public static final Function<TypeRef, TypeRef> LIST_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef item) {
            return classRefOf(Constants.LIST, item);

            //TODO: Need a home for: .withDefaultImplementation(Constants.ARRAY_LIST)
        }

    });

    public static final Function<TypeRef, TypeRef> ARRAY_AS_LIST = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef item) {
            return LIST_OF.apply(UNWRAP_ARRAY_OF.apply(item));

        }
    });

    public static final Function<TypeRef, TypeRef> ARRAY_LIST_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef item) {
            return classRefOf(Constants.ARRAY_LIST, item);
        }

    });

    public static final Function<TypeRef, TypeRef> UNWRAP_COLLECTION_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef type) {
            if (type instanceof ClassRef) {
                ClassRef classRef = (ClassRef) type;
                if (CollectionTypes.IS_COLLECTION.apply(classRef)) {
                    return classRef.getArguments().get(0);
                }
            }
            return type;
        }
    });

    public static final Function<TypeRef, TypeRef> UNWRAP_ARRAY_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {

        public TypeRef apply(TypeRef item) {
            if (item instanceof PrimitiveRef) {
                return new PrimitiveRefBuilder((PrimitiveRef) item).withDimensions(0).build();
            } else if (item instanceof ClassRef) {
                return new ClassRefBuilder((ClassRef) item).withDimensions(0).build();
            } else if (item instanceof TypeParamRef) {
                return new TypeParamRefBuilder((TypeParamRef) item).withDimensions(0).build();
            } else {
                return item;
            }
        }
    });

    public static final Function<TypeDef, TypeDef> REMOVE_GENERICS = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withParameters().build();
        }
    });

    public static final Function<TypeDef, TypeDef> REMOVE_SUPERCLASS = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withExtendsList().build();
        }
    });

    public static final Function<TypeDef, TypeDef> REMOVE_INTERFACES = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withImplementsList().build();
        }
    });


    public static final Function<TypeRef, TypeRef> BOXED_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef type) {
            int index=0;
            for (TypeRef primitive : PRIMITIVE_TYPES) {
                if (primitive.equals(type)) {
                    return BOXED_PRIMITIVE_TYPES[index];
                }
                index++;
            }
            return type;

        }
    });


    public static Function<TypeRef, TypeRef> ARRAY_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>(){

        public TypeRef apply(TypeRef type) {
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
        }
    });


    public static <T> Function<T,T> combine(final Function<T, T>... functions) {
        return new Function<T, T>() {
            public T apply(T item) {
                T result = item;
                for (Function<T, T> f : functions) {
                    result = f.apply(result);
                }
                return result;
            }
        };
    }
}
