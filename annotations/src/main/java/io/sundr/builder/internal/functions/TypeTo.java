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
import io.sundr.builder.Builder;
import io.sundr.builder.Fluent;
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

public enum TypeTo implements Function<JavaType, JavaType> {


    FLUENT {
        @Override
        public JavaType apply(JavaType item) {
            JavaType fluent = SHALLOW_FLUENT.apply(item);
            JavaType generic = typeExtends(T, fluent);

            JavaType superClass = isBuildable(item.getSuperClass()) ?
                    SHALLOW_FLUENT.apply(item.getSuperClass()) :
                    OBJECT;

            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withPackageName(item.getPackageName())
                    .withGenericTypes(new JavaType[]{generic})
                    .withSuperClass(superClass)
                    .withInterfaces(new HashSet(Arrays.asList(FLUENT_INTEFACE)))
                    .build();
        }

    },
    SHALLOW_FLUENT {
        public JavaType apply(JavaType item) {
            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(new JavaType[]{T})
                    .build();
        }
    },
    BUILDER {
        @Override
        public JavaType apply(JavaType item) {
            JavaType builder = SHALLOW_BUILDER.apply(item);
            JavaType fluent = typeGenericOf(SHALLOW_FLUENT.apply(item), builder);

            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(new JavaType[]{})
                    .withSuperClass(fluent)
                    .withInterfaces(new HashSet(Arrays.asList(typeGenericOf(BUILDER_INTERFACE, item))))
                    .build();

        }
    },

    SHALLOW_BUILDER {
        public JavaType apply(JavaType item) {
            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
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
    private static final JavaType FLUENT_INTEFACE = typeGenericOf(ClassToJavaType.FUNCTION.apply(Fluent.class), T);
    private static final JavaType BUILDER_INTERFACE = typeGenericOf(ClassToJavaType.FUNCTION.apply(Builder.class), T);
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
