package me.builder.internal.processor.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaTypeFluent<T extends JavaTypeFluent<T>> implements Fluent<T> {

    private final Map<String, Object> attributes = new HashMap<>();

    public T withAttribute(String key, Object value) {
        attributes.put(key, value);
        return (T) this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    private String packageName;

    public T withPackageName(String packageName) {
        this.packageName=packageName;
        return (T) this;
    }

    public String getPackageName() {
        return packageName;
    }

    private String className;

    public T withClassName(String className) {
        this.className=className;
        return (T) this;
    }

    public String getClassName() {
        return className;
    }

    private boolean array;

    public T withArray(boolean array) {
        this.array=array;
        return (T) this;
    }

    public boolean isArray() {
        return array;
    }

    private boolean collection;

    public T withCollection(boolean collection) {
        this.collection=collection;
        return (T) this;
    }

    public boolean isCollection() {
        return collection;
    }

    private boolean concrete;

    public T withConcrete(boolean concrete) {
        this.concrete=concrete;
        return (T) this;
    }

    public boolean isConcrete() {
        return concrete;
    }

    private JavaType defaultImplementation;

    public T withDefaultImplementation(JavaType defaultImplementation) {
        this.defaultImplementation=defaultImplementation;
        return (T) this;
    }

    public JavaType getDefaultImplementation() {
        return defaultImplementation;
    }

    public DefaultImplementationNested<T> addDefaultImplementation() {
        return new DefaultImplementationNested<T>();
    }

    class DefaultImplementationNested<N> extends JavaTypeFluent<DefaultImplementationNested<N>> implements Nested<N> {
        private final JavaTypeBuilder builder = new JavaTypeBuilder();

        @Override
        public N and() {
            return (N) withDefaultImplementation(builder.build());
        }
    }
    private JavaType superClass;

    public T withSuperClass(JavaType superClass) {
        this.superClass=superClass;
        return (T) this;
    }

    public JavaType getSuperClass() {
        return superClass;
    }

    public SuperClassNested<T> addSuperClass() {
        return new SuperClassNested<T>();
    }

    class SuperClassNested<N> extends JavaTypeFluent<SuperClassNested<N>> implements Nested<N> {
        private final JavaTypeBuilder builder = new JavaTypeBuilder();

        @Override
        public N and() {
            return (N) withSuperClass(builder.build());
        }
    }
    private final List<JavaType> genericTypes = new ArrayList<>();

    public T withGenericTypes(JavaType[] genericTypes) {
        for (JavaType item : genericTypes) {
            this.genericTypes.add(item);
        }
        return (T) this;
    }

    public T addGenericTypes(JavaType genericTypes) {
        this.genericTypes.add(genericTypes);
        return (T) this;
    }

    public JavaType[] getGenericTypes() {
        return genericTypes.toArray(new JavaType[genericTypes.size()]);
    }

    public GenericTypesNested<T> addGenericTypes() {
        return new GenericTypesNested<T>();
    }

    class GenericTypesNested<N> extends JavaTypeFluent<GenericTypesNested<N>> implements Nested<N> {
        private final JavaTypeBuilder builder = new JavaTypeBuilder();

        @Override
        public N and() {
            return (N) addGenericTypes(builder.build());
        }
    }
}