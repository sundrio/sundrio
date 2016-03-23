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
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.internal.utils.BuilderUtils.getNextGeneric;
import static io.sundr.builder.internal.utils.BuilderUtils.isBuildable;
import static io.sundr.codegen.utils.TypeUtils.typeExtends;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public enum TypeAs implements Function<JavaType, JavaType> {

    FLUENT_INTERFACE {
        @Override
        public JavaType apply(JavaType item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            JavaType fluent = SHALLOW_FLUENT.apply(item);
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }

            JavaType generic = getNextGeneric(item, generics);
            JavaType genericFluent = typeExtends(generic, fluent);
            generics.add(genericFluent);

            JavaType superClass = isBuildable(item.getSuperClass()) ?
                    SHALLOW_FLUENT.apply(item.getSuperClass()) :
                    ctx.getBaseFluentClass().getType();

            Set<JavaType> interfaceTypes = new HashSet<JavaType>();
            if (!superClass.getFullyQualifiedName().equals(ctx.getBaseFluentClass().getType().getFullyQualifiedName())) {
                interfaceTypes.add(typeGenericOf(superClass, generic));
            }
            interfaceTypes.add(typeGenericOf(ctx.getFluentInterface().getType(), generic));

            return new JavaTypeBuilder(item)
                    .withKind(JavaKind.INTERFACE)
                    .withClassName(item.getClassName() + "Fluent")
                    .withPackageName(item.getPackageName())
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withInterfaces(interfaceTypes)
                    .build();
        }

    }, FLUENT_IMPL {
        @Override
        public JavaType apply(JavaType item) {
            BuilderContext ctx = BuilderContextManager.getContext();
            JavaType fluent = SHALLOW_FLUENT.apply(item);
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }

            JavaType generic = getNextGeneric(item, generics);
            JavaType genericFluent = typeExtends(generic, fluent);
            generics.add(genericFluent);

            JavaType superClass = isBuildable(item.getSuperClass()) ?
                    typeGenericOf(FLUENT_IMPL.apply(item.getSuperClass()), generic) :
                    typeGenericOf(ctx.getBaseFluentClass().getType(), generic);

            return new JavaTypeBuilder(item)
                    .withKind(JavaKind.CLASS)
                    .withClassName(item.getClassName() + "FluentImpl")
                    .withPackageName(item.getPackageName())
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(superClass)
                    .withInterfaces(SHALLOW_FLUENT.apply(item))
                    .build();
        }

    },
    SHALLOW_FLUENT {
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(REMOVE_SUPERCLASS.apply(generic));
            }
            generics.add(BuilderUtils.getNextGeneric(item));
            return new JavaTypeBuilder(item)
                    .withKind(JavaKind.INTERFACE)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }
    },
    GENERIC_FLUENT {
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            generics.add(Q);
            return REMOVE_GENERICS_BOUNDS.apply(new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build());
        }
    },
    BUILDER {
        @Override
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            JavaType builder = SHALLOW_BUILDER.apply(item);
            generics.add(builder);
            JavaType fluent = TypeAs.REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(FLUENT_IMPL.apply(item), generics.toArray(new JavaType[generics.size()])));
            generics.remove(builder);

            return new JavaTypeBuilder(item)
                    .withKind(JavaKind.CLASS)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(fluent)
                    .withInterfaces(new HashSet(Arrays.asList(
                            TypeAs.REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(BuilderContextManager.getContext().getVisitableBuilderInterface().getType(), item, builder))
                    )))
                    .build();

        }
    }, EDITABLE {
        @Override
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new JavaTypeBuilder(item)
                    .withKind(JavaKind.CLASS)
                    .withClassName("Editable" + item.getClassName())
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(REMOVE_GENERICS_BOUNDS.apply(item))
                    .withInterfaces(new HashSet(Arrays.asList(REMOVE_GENERICS_BOUNDS.apply(typeGenericOf(BuilderContextManager.getContext().getEditableInterface().getType(), SHALLOW_BUILDER.apply(item))))))
                    .build();

        }
    }, SHALLOW_INLINEABLE {
        @Override
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new JavaTypeBuilder(item)
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }
    }, INLINEABLE {
        @Override
        public JavaType apply(JavaType item) {
            JavaType fluent = FLUENT_IMPL.apply(item);
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new JavaTypeBuilder(item)
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(typeGenericOf(fluent, SHALLOW_INLINEABLE.apply(item)))
                    .build();

        }
    }, SHALLOW_BUILDER {
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<JavaType>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }

            return TypeAs.REMOVE_GENERICS_BOUNDS.apply(new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build());
        }

    }, VISITABLE_BUILDER {
        @Override
        public JavaType apply(JavaType item) {
            JavaType baseType = TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF).apply(item);
            baseType = new JavaTypeBuilder(baseType).withGenericTypes().build();
            return new JavaTypeBuilder(BuilderContextManager.getContext().getVisitableBuilderInterface().getType())
                    .withGenericTypes(new JavaType[]{baseType, Q})
                    .build();
        }
    },

    LIST_OF {
        @Override
        public JavaType apply(JavaType item) {
            return new JavaTypeBuilder(Constants.LIST)
                    .withCollection(true)
                    .withGenericTypes(new JavaType[]{item})
                    .withDefaultImplementation(Constants.ARRAY_LIST)
                    .build();
        }

    },
    ARRAY_AS_LIST {
        public JavaType apply(JavaType item) {
            return LIST_OF.apply(UNWRAP_ARRAY_OF.apply(item));

        }
    },
    ARRAY_LIST_OF {
        public JavaType apply(JavaType item) {
            return typeGenericOf(Constants.ARRAY_LIST, item);
        }

    }, UNWRAP_COLLECTION_OF {
        public JavaType apply(JavaType type) {
            if (type.isCollection()) {
                return type.getGenericTypes()[0];
            } else {
                return type;
            }
        }

    }, UNWRAP_ARRAY_OF {
        public JavaType apply(JavaType type) {
            return new JavaTypeBuilder(type).withArray(false).build();
        }
    }, REMOVE_GENERICS {
        public JavaType apply(JavaType type) {
            return new JavaTypeBuilder(type).withGenericTypes().build();
        }
    }, REMOVE_GENERICS_BOUNDS {
        public JavaType apply(JavaType type) {
            return new JavaTypeBuilder(type).accept(new Visitor<JavaTypeBuilder>() {
                public void visit(JavaTypeBuilder builder) {
                    if (builder.getGenericTypes().length > 0) {
                        List<JavaType> generics = new ArrayList<JavaType>();
                        for (JavaType generic : builder.getGenericTypes()) {
                            generics.add(REMOVE_SUPERCLASS.apply(generic));
                        }
                        builder.withGenericTypes(generics.toArray(new JavaType[generics.size()]));
                    }
                }
            }).build();
        }
    },REMOVE_SUPERCLASS {
        public JavaType apply(JavaType type) {
            return new JavaTypeBuilder(type).withSuperClass(null).build();
        }
    };


    public static Function<JavaType, JavaType> combine(final Function<JavaType, JavaType>... functions) {
        return new Function<JavaType, JavaType>() {
            @Override
            public JavaType apply(JavaType item) {
                JavaType result = item;
                for (Function<JavaType, JavaType> f : functions) {
                    result = f.apply(result);
                }
                return result;
            }
        };
    }
}
