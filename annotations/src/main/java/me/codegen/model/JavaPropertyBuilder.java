package me.codegen.model;

import me.builder.Builder;

public class JavaPropertyBuilder extends JavaPropertyFluent<JavaPropertyBuilder> implements Builder<JavaProperty> {

    public JavaPropertyBuilder() {
    }
    
    public JavaPropertyBuilder(JavaProperty instance) {
        withType(instance.getType());
        withName(instance.getName());
        withAttributes(instance.getAttributes());
        withArray(instance.isArray());
    }
    
    public JavaProperty build() {
       return new JavaProperty( getType(), getName(), getAttributes(), isArray() );
    }
}