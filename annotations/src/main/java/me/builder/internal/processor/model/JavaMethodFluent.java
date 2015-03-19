package me.builder.internal.processor.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaMethodFluent<T extends JavaMethodFluent<T>> implements Fluent<T> {

    private final Map<String, Object> attributes = new HashMap<>();

    public T withAttribute(String key, Object value) {
        attributes.put(key, value);
        return (T) this;
    }
    
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    private String name;

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getName() {
        return name;
    }

    private JavaType returnType;

    public T withReturnType(JavaType returnType) {
        this.returnType = returnType;
        return (T) this;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public ReturnTypeNested<T> addReturnType() {
        return new ReturnTypeNested<T>();
    }

    class ReturnTypeNested<N> extends JavaTypeFluent<ReturnTypeNested<N>> implements Nested<N> {
        private final JavaTypeBuilder builder = new JavaTypeBuilder();

        @Override
        public N and() {
            return (N) withReturnType(builder.build());
        }
    }

    private final List<JavaProperty> arguments = new ArrayList<>();

    public T withArguments(JavaProperty[] arguments) {
        for (JavaProperty item : arguments) {
            this.arguments.add(item);
        }
        return (T) this;
    }

    public T addArguments(JavaProperty arguments) {
        this.arguments.add(arguments);
        return (T) this;
    }

    public JavaProperty[] getArguments() {
        return arguments.toArray(new JavaProperty[arguments.size()]);
    }

    public ArgumentsNested<T> addArguments() {
        return new ArgumentsNested<T>();
    }

    public class ArgumentsNested<N> extends JavaPropertyFluent<ArgumentsNested<N>> implements Nested<N> {
        private final JavaPropertyBuilder builder = new JavaPropertyBuilder();

        @Override
        public N and() {
            return (N) addArguments(builder.build());
        }
    }

    private Set<JavaType> exceptions;

    public T withExceptions(Set<JavaType> exceptions) {
        this.exceptions = exceptions;
        return (T) this;
    }

    public Set<JavaType> getExceptions() {
        return exceptions;
    }

}