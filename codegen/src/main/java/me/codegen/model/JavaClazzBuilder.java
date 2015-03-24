package me.codegen.model;

import me.builder.Builder;

public class JavaClazzBuilder extends JavaClazzFluent<JavaClazzBuilder> implements Builder<JavaClazz> {

    public JavaClazzBuilder() {
    }
    
    public JavaClazzBuilder(JavaClazz instance) {
        withType(instance.getType());
        withConstructors(instance.getConstructors());
        withMethods(instance.getMethods());
        withFields(instance.getFields());
        withImports(instance.getImports());
        withAttributes(instance.getAttributes());
    }
    
    public JavaClazz build() {
       return new JavaClazz( getType(), getConstructors(), getMethods(), getFields(), getImports(), getAttributes() );
    }
}