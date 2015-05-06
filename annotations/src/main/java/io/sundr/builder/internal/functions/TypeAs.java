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
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static io.sundr.codegen.utils.TypeUtils.newGeneric;
import static io.sundr.codegen.utils.TypeUtils.typeExtends;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public enum TypeAs implements Function<JavaType, JavaType> {


    FLUENT {
        @Override
        public JavaType apply(JavaType item) {
            JavaType fluent = SHALLOW_FLUENT.apply(item);
            List<JavaType> generics = new ArrayList<>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            JavaType generic = typeExtends(T, fluent);
            generics.add(generic);

            JavaType superClass = isBuildable(item.getSuperClass()) ?
                    SHALLOW_FLUENT.apply(item.getSuperClass()) :
                    OBJECT;

            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withPackageName(item.getPackageName())
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(superClass)
                    .withInterfaces(new HashSet(Arrays.asList(BuilderContextManager.getContext().getFluentInterface().getType())))
                    .build();
        }

    },
    SHALLOW_FLUENT {
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            generics.add(T);
            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }
    },
    GENERIC_FLUENT {
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            generics.add(Q);
            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }
    },
    BUILDER {
        @Override
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            JavaType builder = SHALLOW_BUILDER.apply(item);
            generics.add(builder);
            JavaType fluent = typeGenericOf(SHALLOW_FLUENT.apply(item), generics.toArray(new JavaType[generics.size()]));
            generics.remove(builder);

            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(fluent)
                    .withInterfaces(new HashSet(Arrays.asList(typeGenericOf(BuilderContextManager.getContext().getBuilderInterface().getType(), item))))
                    .build();

        }
    }, EDITABLE {
        @Override
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new JavaTypeBuilder(item)
                    .withClassName("Editable" + item.getClassName())
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withSuperClass(item)
                    .withInterfaces(new HashSet(Arrays.asList(typeGenericOf(BuilderContextManager.getContext().getEditableInterface().getType(), SHALLOW_BUILDER.apply(item)))))
                    .build();

        }
    }, SHALLOW_BUILDER {
        public JavaType apply(JavaType item) {
            List<JavaType> generics = new ArrayList<>();
            for (JavaType generic : item.getGenericTypes()) {
                generics.add(generic);
            }
            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .build();
        }

    },

    LIST_OF {
        @Override
        public JavaType apply(JavaType item) {
            return new JavaTypeBuilder(LIST)
                    .withCollection(true)
                    .withGenericTypes(new JavaType[]{item})
                    .withDefaultImplementation(ARRAY_LIST_OF.apply(item))
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
            return typeGenericOf(ARRAY_LIST, item);
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
    };


    private static final String BUILDABLE = "BUILDABLE";
    private static final JavaType T = newGeneric("T");
    private static final JavaType Q = newGeneric("?");
    private static final JavaType OBJECT = ClassToJavaType.FUNCTION.apply(Object.class);
    private static final JavaType LIST = ClassToJavaType.FUNCTION.apply(List.class);
    private static final JavaType ARRAY_LIST = ClassToJavaType.FUNCTION.apply(ArrayList.class);

    /**
     * Checks if {@link io.sundr.codegen.model.JavaType} has the BUILDABLE attribute set to true.
     *
     * @param type The type to check.
     * @return
     */
    private static boolean isBuildable(JavaType type) {
        if (type == null) {
            return false;
        } else if (type.getAttributes().containsKey(BUILDABLE)) {
            return (Boolean) type.getAttributes().get(BUILDABLE);
        }
        return false;
    }
}
