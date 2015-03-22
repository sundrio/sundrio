package me.codegen.model;

import me.builder.Builder;

public class JavaClazzBuilder extends JavaClazzFluent<JavaClazzBuilder> implements Builder<JavaClazz> {

    public JavaClazzBuilder() {
    }
    
    public JavaClazzBuilder(JavaClazz instance) {
        withType(instance.getType());
        withConstructor(instance.getConstructor());
        withMethods(instance.getMethods());
        withFields(instance.getFields());
        withImports(instance.getImports());
        withAttributes(instance.getAttributes());
    }
    
    public JavaClazz build() {
       return new JavaClazz( getType(), getConstructor(), getMethods(), getFields(), getImports(), getAttributes() );
    }
}