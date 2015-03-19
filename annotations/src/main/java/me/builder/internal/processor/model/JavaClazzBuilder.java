package me.builder.internal.processor.model;

import me.builder.Builder;

public class JavaClazzBuilder extends JavaClazzFluent<JavaClazzBuilder> implements Builder<JavaClazz> {

    public JavaClazz build() {
       return new JavaClazz( getType(), getConstructor(), getMethods(), getFields(), getImports(), getAttributes() );
    }
}