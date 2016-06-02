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

import java.util.ArrayList;
import java.util.List;
import static io.sundr.builder.Constants.BOXED_PRIMITIVE_TYPES;
import static io.sundr.builder.Constants.PRIMITIVE_TYPES;
import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableSuperClass;
import static io.sundr.builder.internal.utils.BuilderUtils.getNextGeneric;
import static io.sundr.codegen.utils.TypeUtils.classRefOf;


public class TypeAs {

    public static final Function<TypeDef, TypeDef> FLUENT_INTERFACE = new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            TypeDef fluent = SHALLOW_FLUENT.apply(item);
            List<TypeParamDef> generics = new ArrayList<TypeParamDef>(item.getParameters());

            TypeParamDef nextGeneric = getNextGeneric(item, generics);
            TypeParamRef nextGenericRef = nextGeneric.toReference();

            List<TypeRef> boundArguments = new ArrayList<TypeRef>();
            for (TypeParamDef parameter :item.getParameters()) {
                boundArguments.add(parameter.toReference());
            }
            boundArguments.add(nextGenericRef);

            TypeParamDef genericFluent = new TypeParamDefBuilder(nextGeneric)
                    .addNewBound()
                        .withDefinition(fluent)
                        .withArguments(boundArguments)
                    .endBound()
                    .build();

            generics.add(genericFluent);

            TypeDef buildableSuperClass = findBuildableSuperClass(item);
            TypeDef superClass = buildableSuperClass != null
                    ? SHALLOW_FLUENT.apply(buildableSuperClass)
                    : ctx.getFluentInterface();

            return new TypeDefBuilder(item)
                    .withKind(Kind.INTERFACE)
                    .withName(item.getName() + "Fluent")
                    .withPackageName(item.getPackageName())
                    .withParameters(generics)
                    .withExtendsList(superClass.toReference(nextGenericRef))
                    .withImplementsList()
                    .build();
        }
    };

    public static final Function<TypeDef, TypeDef> FLUENT_IMPL = new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            TypeDef fluent = SHALLOW_FLUENT.apply(item);

            List<TypeParamDef> parameters = new ArrayList<TypeParamDef>(item.getParameters());
            TypeParamDef nextParameter = getNextGeneric(item, parameters);
            TypeParamRef nextParameterRef = nextParameter.toReference();
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
                    .withExtendsList(superClass.toReference(nextParameterRef))
                    .withImplementsList(SHALLOW_FLUENT.apply(item).toInternalReference())
                    .build();
        }

    };


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
            List<TypeRef> parameters = new ArrayList<TypeRef>();
            for (TypeParamDef param : item.getParameters()) {
                parameters.add(param.toReference());
            }
            parameters.add(Q);
           return SHALLOW_FLUENT.apply(item).toReference(parameters.toArray(new TypeRef[parameters.size()]));
        }
    });

    public static final Function<TypeDef, TypeDef> BUILDER = CachingFunction.wrap(new Function<TypeDef, TypeDef>() {
        public TypeDef apply(TypeDef item) {
            TypeDef builder = SHALLOW_BUILDER.apply(item);
            TypeDef fluent = FLUENT_IMPL.apply(item);

            List<TypeRef> parameters = new ArrayList<TypeRef>();
            for (TypeParamDef param : item.getParameters()) {
                parameters.add(param.toReference());
            }
            parameters.add(builder.toInternalReference());
            return new TypeDefBuilder(item)
                    .withKind(Kind.CLASS)
                    .withName(item.getName() + "Builder")
                    .withParameters(item.getParameters())
                    .withExtendsList(fluent.toReference(parameters.toArray(new TypeRef[parameters.size()])))
                    .withImplementsList(BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(item.toInternalReference(), builder.toInternalReference()))
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
                    .withExtendsList(item.toInternalReference())
                    .withImplementsList(BuilderContextManager.getContext().getEditableInterface().toReference(SHALLOW_BUILDER.apply(item).toInternalReference()))
                    .withProperties()
                    .withMethods()
                    .withConstructors()
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

    public static final Function<TypeRef, TypeRef> VISITABLE_BUILDER = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef item) {
            TypeRef baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(item);
            if (baseType instanceof ClassRef) {
                baseType = new ClassRefBuilder((ClassRef)baseType).withArguments().build();
            }
            return BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(baseType, Q);
        }
    });


    public static final Function<TypeRef, TypeRef> LIST_OF = CachingFunction.wrap(new Function<TypeRef, TypeRef>() {
        public TypeRef apply(TypeRef item) {
            return Constants.LIST.toReference(item);
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
