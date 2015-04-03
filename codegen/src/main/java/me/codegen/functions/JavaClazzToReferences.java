package me.codegen.functions;

import me.Function;
import me.codegen.model.JavaClazz;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;

import java.util.HashSet;
import java.util.Set;

public enum JavaClazzToReferences implements Function<JavaClazz, Set<JavaType>> {

    FUNCTION;

    @Override
    public Set<JavaType> apply(JavaClazz item) {
        Set<JavaType> result = new HashSet<>();

        result.addAll(JavaTypeToReferences.FUNCTION.apply(item.getType()));

        for (JavaProperty p : item.getFields()) {
            result.addAll(JavaPropertyToReferences.FUNCTION.apply(p));
        }
        for (JavaMethod m : item.getMethods()) {
            result.addAll(JavaMethodToReferences.FUNCTION.apply(m));
        }
        for (JavaClazz nested : item.getNested()) {
            result.addAll(apply(nested));
        }
        return result;
    }
}
