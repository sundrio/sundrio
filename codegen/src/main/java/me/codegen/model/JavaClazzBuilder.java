package me.codegen.model;

import me.builder.Builder;

public class JavaClazzBuilder extends JavaClazzFluent<JavaClazzBuilder> implements Builder<JavaClazz> {

    private final JavaClazzFluent fluent;
    
    public JavaClazzBuilder() {
        this.fluent = this;
    }

    public JavaClazzBuilder(JavaClazzFluent fluent) {
        this.fluent = fluent;
    }
    
    public JavaClazzBuilder(JavaClazz instance) {
        this();
        withType(instance.getType());
        withConstructors(instance.getConstructors());
        withMethods(instance.getMethods());
        withFields(instance.getFields());
        withImports(instance.getImports());
        withAttributes(instance.getAttributes());
        withNested(instance.getNested());
    }
    
    public JavaClazz build() {
       return new JavaClazz( fluent.getType(), fluent.getConstructors(), fluent.getMethods(), fluent.getFields(), fluent.getImports(), fluent.getAttributes(), fluent.getNested() );
    }
}