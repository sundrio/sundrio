package me.codegen.functions;

import me.Function;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

public enum ClassToJavaType implements Function<Class, JavaType> {

    FUNCTION;

    @Override
    public JavaType apply(Class item) {
        return new JavaTypeBuilder()
                .withKind(ClassToJavaKind.FUNCTION.apply(item))
                .withClassName(item.getSimpleName())
                .withPackageName(item.getPackage().getName())
                .build();
    }
}
