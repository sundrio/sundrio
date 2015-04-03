package me.codegen.functions;

import me.Function;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;

import java.util.Set;

public enum JavaPropertyToReferences implements Function<JavaProperty, Set<JavaType>> {

    FUNCTION;

    @Override
    public Set<JavaType> apply(JavaProperty item) {
        return JavaTypeToReferences.FUNCTION.apply(item.getType());
    }
}
