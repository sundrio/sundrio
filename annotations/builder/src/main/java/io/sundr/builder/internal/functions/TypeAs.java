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
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.Constants.PRIMITIVE_TYPES;
import static io.sundr.builder.Constants.BOXED_PRIMITIVE_TYPES;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableSuperClass;
import static io.sundr.builder.internal.utils.BuilderUtils.getNextGeneric;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;
import static io.sundr.codegen.utils.TypeUtils.typeImplements;

public enum TypeAs implements Function<TypeDef, TypeDef> {

    FLUENT_INTERFACE {
        @Override
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            TypeDef fluent = SHALLOW_FLUENT.apply(item);
            List<TypeParamDef> generics = new ArrayList<TypeParamDef>();

            for (TypeParamDef generic : item.getParameters()) {
                generics.add(generic);
            }

            TypeParamDef genericFluent = new TypeParamDefBuilder(getNextGeneric(item, generics))
                    .addNewBound()
                    .withDefinition(fluent)
                    .endBound()
                    .build();

            generics.add(genericFluent);

            TypeDef buildableSuperClass = findBuildableSuperClass(item);
            TypeDef superClass = buildableSuperClass != null ?
                    SHALLOW_FLUENT.apply(buildableSuperClass) :
                    ctx.getBaseFluentClass().getType();

            Set<TypeDef> interfaceTypes = new HashSet<TypeDef>();
            if (!superClass.getFullyQualifiedName().equals(ctx.getBaseFluentClass().getType().getFullyQualifiedName())) {
                interfaceTypes.add(typeGenericOf(superClass, generic));
            }
            interfaceTypes.add(typeGenericOf(ctx.getFluentInterface().getType(), generic));

            return new TypeDefBuilder(item)
                    .withKind(JavaKind.INTERFACE)
                    .withClassName(item.getClassName() + "Fluent")
                    .withPackageName(item.getPackageName())
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .withInterfaces(interfaceTypes)
                    .build();
        }

    }, FLUENT_IMPL {
        @Override
        public TypeDef apply(TypeDef item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            TypeDef fluent = SHALLOW_FLUENT.apply(item);
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }

            TypeDef generic = getNextGeneric(item, generics);
            TypeDef genericFluent = typeImplements(generic, fluent);
            generics.add(genericFluent);

            TypeDef superClass = isBuildable(item.getSuperClass()) ?
                    typeGenericOf(FLUENT_IMPL.apply(item.getSuperClass()), generic) :
                    typeGenericOf(ctx.getBaseFluentClass().getType(), generic);

            return new TypeDefBuilder(item)
                    .withKind(JavaKind.CLASS)
                    .withClassName(item.getClassName() + "FluentImpl")
                    .withPackageName(item.getPackageName())
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .withSuperClass(superClass)
                    .withInterfaces(SHALLOW_FLUENT.apply(item))
                    .build();
        }

    },
    SHALLOW_FLUENT {
        public TypeDef apply(TypeDef item) {
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(REMOVE_INTERFACES.apply(generic));
            }
            generics.add(BuilderUtils.getNextGeneric(item));
            return new TypeDefBuilder(item)
                    .withKind(JavaKind.INTERFACE)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .build();
        }
    },
    GENERIC_FLUENT {
        public TypeDef apply(TypeDef item) {
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            generics.add(Q);
            return REMOVE_GENERICS_BOUNDS.apply(new TypeDefBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .build());
        }
    },
    BUILDER {
        @Override
        public TypeDef apply(TypeDef item) {
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            TypeDef builder = SHALLOW_BUILDER.apply(item);
            generics.add(builder);
            TypeDef fluent = TypeAs.REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(FLUENT_IMPL.apply(item), generics.toArray(new TypeDef[generics.size()])));
            generics.remove(builder);

            return new TypeDefBuilder(item)
                    .withKind(JavaKind.CLASS)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .withSuperClass(fluent)
                    .withInterfaces(new HashSet(Arrays.asList(
                            TypeAs.REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(BuilderContextManager.getContext().getVisitableBuilderInterface().getType(), item, builder))
                    )))
                    .build();

        }
    }, EDITABLE {
        @Override
        public TypeDef apply(TypeDef item) {
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new TypeDefBuilder(item)
                    .withKind(JavaKind.CLASS)
                    .withClassName("Editable" + item.getClassName())
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .withSuperClass(REMOVE_GENERICS_BOUNDS.apply(item))
                    .withInterfaces(new HashSet(Arrays.asList(REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(BuilderContextManager.getContext().getEditableInterface().getType(), SHALLOW_BUILDER.apply(item))))))
                    .build();

        }
    }, SHALLOW_INLINEABLE {
        @Override
        public TypeDef apply(TypeDef item) {
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new TypeDefBuilder(item)
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .build();
        }
    }, INLINEABLE {
        @Override
        public TypeDef apply(TypeDef item) {
            TypeDef fluent = FLUENT_IMPL.apply(item);
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new TypeDefBuilder(item)
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .withSuperClass(typeGenericOf(fluent, SHALLOW_INLINEABLE.apply(item)))
                    .build();

        }
    }, SHALLOW_BUILDER {
        public TypeDef apply(TypeDef item) {
            List<TypeDef> generics = new ArrayList<TypeDef>();
            for (TypeDef generic : item.getGenericTypes()) {
                generics.add(generic);
            }

            return TypeAs.REMOVE_GENERICS_BOUNDS.apply(new TypeDefBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(generics.toArray(new TypeDef[generics.size()]))
                    .build());
        }

    }, VISITABLE_BUILDER {
        @Override
        public TypeDef apply(TypeDef item) {
            TypeDef baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(item);
            baseType = new TypeDefBuilder(baseType).withGenericTypes().build();
            return new TypeDefBuilder(BuilderContextManager.getContext().getVisitableBuilderInterface().getType())
                    .withGenericTypes(new TypeDef[]{baseType, Q})
                    .build();
        }
    },

    LIST_OF {
        @Override
        public TypeDef apply(TypeDef item) {
            return new TypeDefBuilder(Constants.LIST)
                    .withCollection(true)
                    .withGenericTypes(new TypeDef[]{item})
                    .withDefaultImplementation(Constants.ARRAY_LIST)
                    .build();
        }

    },
    ARRAY_AS_LIST {
        public TypeDef apply(TypeDef item) {
            return LIST_OF.apply(UNWRAP_ARRAY_OF.apply(item));

        }
    },
    ARRAY_LIST_OF {
        public TypeDef apply(TypeDef item) {
            return typeGenericOf(Constants.ARRAY_LIST, item);
        }

    }, UNWRAP_COLLECTION_OF {
        public TypeDef apply(TypeDef type) {
            if (type.isCollection()) {
                return type.getGenericTypes()[0];
            } else {
                return type;
            }
        }

    }, UNWRAP_ARRAY_OF {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withArray(false).build();
        }
    }, REMOVE_GENERICS {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withGenericTypes().build();
        }
    }, REMOVE_GENERICS_BOUNDS {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).accept(new Visitor<TypeDefBuilder>() {
                public void visit(TypeDefBuilder builder) {
                    if (builder.getGenericTypes().length > 0) {
                        List<TypeDef> generics = new ArrayList<TypeDef>();
                        for (TypeDef generic : builder.getGenericTypes()) {
                            generics.add(REMOVE_INTERFACES.apply(generic));
                        }
                        builder.withGenericTypes(generics.toArray(new TypeDef[generics.size()]));
                    }
                }
            }).build();
        }
    },REMOVE_SUPERCLASS {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withSuperClass(null).build();
        }
    }, REMOVE_INTERFACES {
        public TypeDef apply(TypeDef type) {
            return new TypeDefBuilder(type).withInterfaces().build();
        }
    } , BOXED_OF {
        public TypeDef apply(TypeDef type) {
            int index=0;
            for (TypeDef primitive : PRIMITIVE_TYPES) {
                if (primitive.equals(type)) {
                    return BOXED_PRIMITIVE_TYPES[index];
                }
                index++;
            }
            return type;

        }
    };


    public static Function<TypeDef, TypeDef> combine(final Function<TypeDef, TypeDef>... functions) {
        return new Function<TypeDef, TypeDef>() {
            @Override
            public TypeDef apply(TypeDef item) {
                TypeDef result = item;
                for (Function<TypeDef, TypeDef> f : functions) {
                    result = f.apply(result);
                }
                return result;
            }
        };
    }
}
