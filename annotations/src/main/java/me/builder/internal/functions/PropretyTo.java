package me.builder.internal.functions;

import me.Function;
import me.builder.Nested;
import me.codegen.functions.ClassToJavaType;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import java.util.Arrays;
import java.util.HashSet;

import static me.codegen.utils.TypeUtils.newGeneric;
import static me.codegen.utils.TypeUtils.typeGenericOf;
import static me.codegen.utils.StringUtils.captializeFirst;

public enum PropretyTo implements Function<JavaProperty, JavaType> {

    NESTED {
        @Override
        public JavaType apply(JavaProperty item) {
            JavaType nested = SHALLOW_NESTED.apply(item);
            //Not a typical fluent
            JavaType fluent = TypeTo.UNWRAP_COLLECTION_OF.apply(item.getType());
            JavaType superClassFluent = new JavaTypeBuilder(fluent)
                    .withClassName(TypeTo.UNWRAP_COLLECTION_OF.apply(item.getType()) + "Fluent")
                    .withGenericTypes(new JavaType[]{nested})
                    .build();

            return new JavaTypeBuilder(nested)
                    .withGenericTypes(new JavaType[]{N})
                    .withSuperClass(superClassFluent)
                    .withInterfaces(new HashSet(Arrays.asList(NESTED_INTEFACE)))
                    .build();
        }

    },
    SHALLOW_NESTED {
        public JavaType apply(JavaProperty property) {
            return new JavaTypeBuilder()
                    .withClassName(captializeFirst(property.getName() + "Nested"))
                    .withGenericTypes(new JavaType[]{N})
                    .build();
        }
    };

    private static final JavaType N = newGeneric("N");
    private static final JavaType NESTED_INTEFACE = typeGenericOf(ClassToJavaType.FUNCTION.apply(Nested.class), N);

}
