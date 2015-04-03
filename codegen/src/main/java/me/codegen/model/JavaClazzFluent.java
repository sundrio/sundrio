package me.codegen.model;

import me.builder.Fluent;
import me.builder.Nested;

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaClazzFluent<T extends JavaClazzFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    private JavaType type;
    private Set<JavaMethod> methods = new LinkedHashSet();
    private Set<JavaMethod> constructors = new LinkedHashSet();
    private Set<JavaProperty> fields = new LinkedHashSet();
    private Set<JavaType> imports = new LinkedHashSet();
    private Set<JavaClazz> nested = new LinkedHashSet();

    public JavaType getType() {
        return this.type;
    }

    public T withType(JavaType type) {
        this.type = type;
        return (T) this;
    }

    public Set<JavaMethod> getMethods() {
        return this.methods;
    }

    public T withMethods(Set<JavaMethod> methods) {
        this.methods.clear();
        this.methods.addAll(methods);
        return (T) this;
    }

    public Set<JavaMethod> getConstructors() {
        return this.constructors;
    }

    public T withConstructors(Set<JavaMethod> constructors) {
        this.constructors.clear();
        this.constructors.addAll(constructors);
        return (T) this;
    }

    public Set<JavaProperty> getFields() {
        return this.fields;
    }

    public T withFields(Set<JavaProperty> fields) {
        this.fields.clear();
        this.fields.addAll(fields);
        return (T) this;
    }

    public Set<JavaType> getImports() {
        return this.imports;
    }

    public T withImports(Set<JavaType> imports) {
        this.imports.clear();
        this.imports.addAll(imports);
        return (T) this;
    }

    public Set<JavaClazz> getNested() {
        return this.nested;
    }

    public T withNested(Set<JavaClazz> nested) {
        this.nested.clear();
        this.nested.addAll(nested);
        return (T) this;
    }

    public TypeNested<T> addType() {
        return new TypeNested<T>();
    }

    public T addToMethods(JavaMethod item) {
        this.methods.add(item);
        return (T) this;
    }

    public MethodsNested<T> addMethods() {
        return new MethodsNested<T>();
    }

    public T addToConstructors(JavaMethod item) {
        this.constructors.add(item);
        return (T) this;
    }

    public ConstructorsNested<T> addConstructors() {
        return new ConstructorsNested<T>();
    }

    public T addToFields(JavaProperty item) {
        this.fields.add(item);
        return (T) this;
    }

    public FieldsNested<T> addFields() {
        return new FieldsNested<T>();
    }

    public T addToImports(JavaType item) {
        this.imports.add(item);
        return (T) this;
    }

    public ImportsNested<T> addImports() {
        return new ImportsNested<T>();
    }

    public T addToNested(JavaClazz item) {
        this.nested.add(item);
        return (T) this;
    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {

        public N endType() {
            return and();
        }

        public N and() {
            return (N) withType(new JavaTypeBuilder(this).build());
        }

    }

    public class MethodsNested<N> extends JavaMethodFluent<MethodsNested<N>> implements Nested<N> {

        public N and() {
            return (N) addToMethods(new JavaMethodBuilder(this).build());
        }

        public N endMethods() {
            return and();
        }

    }

    public class ConstructorsNested<N> extends JavaMethodFluent<ConstructorsNested<N>> implements Nested<N> {

        public N endConstructors() {
            return and();
        }

        public N and() {
            return (N) addToConstructors(new JavaMethodBuilder(this).build());
        }

    }

    public class FieldsNested<N> extends JavaPropertyFluent<FieldsNested<N>> implements Nested<N> {

        public N endFields() {
            return and();
        }

        public N and() {
            return (N) addToFields(new JavaPropertyBuilder(this).build());
        }

    }

    public class ImportsNested<N> extends JavaTypeFluent<ImportsNested<N>> implements Nested<N> {

        public N endImports() {
            return and();
        }

        public N and() {
            return (N) addToImports(new JavaTypeBuilder(this).build());
        }

    }


}
