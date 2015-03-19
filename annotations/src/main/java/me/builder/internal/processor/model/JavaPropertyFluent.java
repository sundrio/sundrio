package me.builder.internal.processor.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.HashMap;
import java.util.Map;

public class JavaPropertyFluent<T extends JavaPropertyFluent<T>> implements Fluent<T> {

    private final Map<String, Object> attributes = new HashMap<>();

    public T withAttribute(String key, Object value) {
        attributes.put(key, value);
        return (T) this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    private JavaType type;

    public T withType(JavaType type) {
        this.type=type;
        return (T) this;
    }

    public JavaType getType() {
        return type;
    }

    public TypeNested<T> addType() {
        return new TypeNested<T>();
    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {
        private final JavaTypeBuilder builder = new JavaTypeBuilder();


        public N endType() {
            return and();
        }
        
        @Override
        public N and() {
            return (N) withType(builder.build());
        }
    }
    private String name;

    public T withName(String name) {
        this.name=name;
        return (T) this;
    }

    public String getName() {
        return name;
    }

}