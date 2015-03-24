package me.codegen.model;

import me.codegen.Method;

import java.util.Map;
import java.util.Set;

public class JavaMethod extends AttributeSupport implements Method<JavaType, JavaProperty> {

    private final String name;
    private final JavaType returnType;
    private final JavaProperty[] arguments;
    private final Set<JavaType> exceptions;

    public JavaMethod(String name, JavaType returnType, JavaProperty[] arguments, Set<JavaType> exceptions, Map<String, Object> attributes) {
        super(attributes);
        this.name = name;
        this.returnType = returnType;
        this.arguments = arguments;
        this.exceptions = exceptions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public JavaType getReturnType() {
        return returnType;
    }

    public JavaProperty[] getArguments() {
        return arguments;
    }

    @Override
    public Set<JavaType> getExceptions() {
        return exceptions;
    }
}
