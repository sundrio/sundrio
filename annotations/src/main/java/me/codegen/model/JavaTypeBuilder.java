package me.codegen.model;

import me.builder.Builder;

public class JavaTypeBuilder extends JavaTypeFluent<JavaTypeBuilder> implements Builder<JavaType> {

    public JavaTypeBuilder() {
    }
    
    public JavaTypeBuilder(JavaType instance) {
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
       return new JavaType( getKind(), getPackageName(), getClassName(), isCollection(), isConcrete(), getDefaultImplementation(), getSuperClass(), getInterfaces(), getGenericTypes(), getAttributes() );
    }
}