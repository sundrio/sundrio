package me.codegen.functions;

import me.Function;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;

import java.util.HashSet;
import java.util.Set;

public enum JavaMethodToReferences implements Function<JavaMethod, Set<JavaType>> {

    FUNCTION;

    @Override
    public Set<JavaType> apply(JavaMethod item) {
        Set<JavaType> result = new HashSet<>();
        result.addAll(JavaTypeToReferences.FUNCTION.apply(item.getReturnType()));
        for (JavaType t : item.getExceptions()) {
            result.addAll(JavaTypeToReferences.FUNCTION.apply(t));
        }
        for (JavaProperty p : item.getArguments()) {
            result.addAll(JavaPropertyToReferences.FUNCTION.apply(p));
        }
        return result;
    }
}
