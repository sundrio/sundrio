package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

public class JavaPropertyFluent<T extends JavaPropertyFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    private JavaType type;
    private String name;
    private boolean array;

    public JavaType getType() {
        return this.type;
    }

    public T withType(JavaType type) {
        this.type = type;
        return (T) this;
    }

    public String getName() {
        return this.name;
    }

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public boolean isArray() {
        return this.array;
    }

    public T withArray(boolean array) {
        this.array = array;
        return (T) this;
    }

    public TypeNested<T> addType() {
        return new TypeNested<T>();
    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {

        public N endType() {
            return and();
        }

        public N and() {
            return (N) withType(new JavaTypeBuilder(this).build());
        }

    }


}
