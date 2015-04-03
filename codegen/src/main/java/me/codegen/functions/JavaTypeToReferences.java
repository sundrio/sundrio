package me.codegen.functions;

import me.Function;
import me.codegen.model.JavaType;

import java.util.HashSet;
import java.util.Set;

public enum  JavaTypeToReferences implements Function<JavaType, Set<JavaType>> {

    FUNCTION;
    
    @Override
    public Set<JavaType> apply(JavaType item) {
        Set<JavaType> result = new HashSet<>();
        if (item != null) {
            result.add(item);

            result.addAll(apply(item.getSuperClass()));

            for (JavaType t : item.getInterfaces()) {
                result.addAll(apply(t));
            }

            for (JavaType t : item.getGenericTypes()) {
                result.addAll(apply(t));
            }

            if (item.getDefaultImplementation() != null) {
                result.addAll(apply(item.getDefaultImplementation()));
            }
        }
        return result;
    }
}
