package me.builder.internal.functions;

import me.Function;
import me.builder.Builder;
import me.builder.Fluent;
import me.codegen.functions.ClassToJavaType;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static me.codegen.utils.TypeUtils.newGeneric;
import static me.codegen.utils.TypeUtils.typeExtends;
import static me.codegen.utils.TypeUtils.typeGenericOf;

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
            JavaType generic = typeExtends(T, builder);
            JavaType fluent = SHALLOW_FLUENT.apply(item);

            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Fluent")
                    .withGenericTypes(new JavaType[]{generic})
                    .withSuperClass(fluent)
                    .withInterfaces(new HashSet(Arrays.asList(BUILDER_INTERFACE)))
                    .build();

        }
    },

    SHALLOW_BUILDER {
        public JavaType apply(JavaType item) {
            return new JavaTypeBuilder(item)
                    .withClassName(item.getClassName() + "Builder")
                    .withGenericTypes(new JavaType[]{T})
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
     * Checks if {@link me.codegen.model.JavaType} has the BUILDABLE attribute set to true.
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
