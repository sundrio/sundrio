package me.builder.internal.processor.model;

import me.builder.Builder;

public class JavaPropertyBuilder extends JavaPropertyFluent<JavaPropertyBuilder> implements Builder<JavaProperty> {

    public JavaProperty build() {
       return new JavaProperty( getType(), getName(), getAttributes() );
    }
}