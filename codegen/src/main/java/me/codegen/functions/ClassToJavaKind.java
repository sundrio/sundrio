package me.codegen.functions;

import me.Function;
import me.codegen.model.JavaKind;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

public enum ClassToJavaKind implements Function<Class, JavaKind> {

    FUNCTION;

    @Override
    public JavaKind apply(Class item) {
        if (item.isInterface()) {
            return JavaKind.INTERFACE;
        } else if (item.isEnum()) {
            return JavaKind.ENUM;
        } else {
            return JavaKind.CLASS;
        }
    }
}
