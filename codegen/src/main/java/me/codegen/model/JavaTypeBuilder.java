package me.codegen.model;

import me.builder.Builder;

public class JavaTypeBuilder extends JavaTypeFluent<JavaTypeBuilder> implements Builder<JavaType> {

    private final JavaTypeFluent fluent;
    
    public JavaTypeBuilder() {
        this.fluent = this;
    }

    public JavaTypeBuilder(JavaTypeFluent fluent) {
        this.fluent = fluent;
    }
    
    public JavaTypeBuilder(JavaType instance) {
        this();
        withKind(instance.getKind());
        withPackageName(instance.getPackageName());
        withClassName(instance.getClassName());
        withCollection(instance.isCollection());
        withConcrete(instance.isConcrete());
        withDefaultImplementation(instance.getDefaultImplementation());
        withSuperClass(instance.getSuperClass());
        withInterfaces(instance.getInterfaces());
        withGenericTypes(instance.getGenericTypes());
        withAttributes(instance.getAttributes());
    }
    
    public JavaType build() {
       return new JavaType( fluent.getKind(), fluent.getPackageName(), fluent.getClassName(), fluent.isCollection(), fluent.isConcrete(), fluent.getDefaultImplementation(), fluent.getSuperClass(), fluent.getInterfaces(), fluent.getGenericTypes(), fluent.getAttributes() );
    }
}