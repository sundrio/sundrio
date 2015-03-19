package me.builder.internal.processor.model;

import me.builder.Fluent;
import me.builder.Nested;
import me.codegen.Method;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class JavaClazzFluent<T extends JavaClazzFluent<T>> implements Fluent<T> {

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

        @Override
        public N and() {
            return (N) withType(builder.build());
        }
    }


    private JavaMethod constructor;

    public T withConstructor(JavaMethod constructor) {
        this.constructor=constructor;
        return (T) this;
    }

    public JavaMethod getConstructor() {
        return constructor;
    }

    public ConstructorNested<T> addConstructor() {
        return new ConstructorNested<T>();
    }


    public class ConstructorNested<N> extends JavaMethodFluent<ConstructorNested<N>> implements Nested<N> {
        private final JavaMethodBuilder builder = new JavaMethodBuilder();

        @Override
        public N and() {
            return (N) withConstructor(builder.build());
        }
    }

    private Set<Method<JavaType,JavaProperty>> methods;

    public T withMethods(Set<Method<JavaType,JavaProperty>> methods) {
        this.methods=methods;
        return (T) this;
    }

    public Set<Method<JavaType,JavaProperty>> getMethods() {
        return methods;
    }

    private Set<JavaProperty> fields = new LinkedHashSet<>();

    public T withFields(Set<JavaProperty> fields) {
        this.fields=fields;
        return (T) this;
    }

    public T addField(JavaProperty field) {
        this.fields.add(field);
        return (T) this;
    }

    public Set<JavaProperty> getFields() {
        return fields;
    }

    private Set<JavaType> imports;

    public T withImports(Set<JavaType> imports) {
        this.imports=imports;
        return (T) this;
    }

    public Set<JavaType> getImports() {
        return imports;
    }

}