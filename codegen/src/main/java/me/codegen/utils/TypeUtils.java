package me.codegen.utils;

import me.codegen.model.JavaKind;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

public final class TypeUtils {
    
    private TypeUtils() {
        //Utility Class
    }

    /**
     * Creates a new generic JavaType.
     * @param letter       The letter of the type.
     * @return
     */
    public static JavaType newGeneric(String letter) {
        return new JavaTypeBuilder().withKind(JavaKind.GENERIC).withClassName(letter).build();
    }

    /**
     * Sets one {@link me.codegen.model.JavaType} as a generic of an other.
     *
     * @param base       The base type.
     * @param generic    The generic type.
     * @return
     */
    public static JavaType typeGenericOf(JavaType base, JavaType... generic) {
        return new JavaTypeBuilder(base)
                .withGenericTypes(generic)
                .build();
    }

    /**
     * Sets one {@link me.codegen.model.JavaType} as a super class of an other.
     *
     * @param base       The base type.
     * @param superClass The super type.
     * @return
     */
    public static JavaType typeExtends(JavaType base, JavaType superClass) {
        return new JavaTypeBuilder(base)
                .withSuperClass(superClass)
                .build();
    }

}
