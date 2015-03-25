package me.codegen.model;

import me.builder.Builder;

public class JavaPropertyBuilder extends JavaPropertyFluent<JavaPropertyBuilder> implements Builder<JavaProperty> {

    private final JavaPropertyFluent fluent;
    
    public JavaPropertyBuilder() {
        this.fluent = this;
    }

    public JavaPropertyBuilder(JavaPropertyFluent fluent) {
        this.fluent = fluent;
    }
    
    public JavaPropertyBuilder(JavaProperty instance) {
        this();
        withType(instance.getType());
        withName(instance.getName());
        withAttributes(instance.getAttributes());
        withArray(instance.isArray());
    }
    
    public JavaProperty build() {
       return new JavaProperty( fluent.getType(), fluent.getName(), fluent.getAttributes(), fluent.isArray() );
    }
}