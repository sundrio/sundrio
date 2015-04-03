package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaTypeFluent<T extends JavaTypeFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    private JavaKind kind;
    private String packageName;
    private String className;
    private boolean array;
    private boolean collection;
    private boolean concrete;
    private JavaType defaultImplementation;
    private JavaType superClass;
    private Set<JavaType> interfaces = new LinkedHashSet();
    private List<JavaType> genericTypes = new ArrayList();

    public JavaKind getKind() {
        return this.kind;
    }

    public T withKind(JavaKind kind) {
        this.kind = kind;
        return (T) this;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public T withPackageName(String packageName) {
        this.packageName = packageName;
        return (T) this;
    }

    public String getClassName() {
        return this.className;
    }

    public T withClassName(String className) {
        this.className = className;
        return (T) this;
    }

    public boolean isArray() {
        return this.array;
    }

    public T withArray(boolean array) {
        this.array = array;
        return (T) this;
    }

    public boolean isCollection() {
        return this.collection;
    }

    public T withCollection(boolean collection) {
        this.collection = collection;
        return (T) this;
    }

    public boolean isConcrete() {
        return this.concrete;
    }

    public T withConcrete(boolean concrete) {
        this.concrete = concrete;
        return (T) this;
    }

    public JavaType getDefaultImplementation() {
        return this.defaultImplementation;
    }

    public T withDefaultImplementation(JavaType defaultImplementation) {
        this.defaultImplementation = defaultImplementation;
        return (T) this;
    }

    public JavaType getSuperClass() {
        return this.superClass;
    }

    public T withSuperClass(JavaType superClass) {
        this.superClass = superClass;
        return (T) this;
    }

    public Set<JavaType> getInterfaces() {
        return this.interfaces;
    }

    public T withInterfaces(Set<JavaType> interfaces) {
        this.interfaces.clear();
        this.interfaces.addAll(interfaces);
        return (T) this;
    }

    public T withGenericTypes(JavaType[] genericTypes) {
        this.genericTypes.clear();
        for (JavaType item : genericTypes) {
            this.genericTypes.add(item);
        }
        return (T) this;
    }

    public JavaType[] getGenericTypes() {
        return this.genericTypes.toArray(new JavaType[genericTypes.size()]);
    }

    public DefaultImplementationNested<T> addDefaultImplementation() {
        return new DefaultImplementationNested<T>();
    }

    public SuperClassNested<T> addSuperClass() {
        return new SuperClassNested<T>();
    }

    public T addToInterfaces(JavaType item) {
        this.interfaces.add(item);
        return (T) this;
    }

    public InterfacesNested<T> addInterfaces() {
        return new InterfacesNested<T>();
    }

    public T addToGenericTypes(JavaType item) {
        this.genericTypes.add(item);
        return (T) this;
    }

    public GenericTypesNested<T> addGenericTypes() {
        return new GenericTypesNested<T>();
    }

    public class DefaultImplementationNested<N> extends JavaTypeFluent<DefaultImplementationNested<N>> implements Nested<N> {

        public N and() {
            return (N) withDefaultImplementation(new JavaTypeBuilder(this).build());
        }

        public N endDefaultImplementation() {
            return and();
        }

    }

    public class SuperClassNested<N> extends JavaTypeFluent<SuperClassNested<N>> implements Nested<N> {

        public N and() {
            return (N) withSuperClass(new JavaTypeBuilder(this).build());
        }

        public N endSuperClass() {
            return and();
        }

    }

    public class InterfacesNested<N> extends JavaTypeFluent<InterfacesNested<N>> implements Nested<N> {

        public N endInterfaces() {
            return and();
        }

        public N and() {
            return (N) addToInterfaces(new JavaTypeBuilder(this).build());
        }

    }

    public class GenericTypesNested<N> extends JavaTypeFluent<GenericTypesNested<N>> implements Nested<N> {

        public N and() {
            return (N) addToGenericTypes(new JavaTypeBuilder(this).build());
        }

        public N endGenericTypes() {
            return and();
        }

    }


}
