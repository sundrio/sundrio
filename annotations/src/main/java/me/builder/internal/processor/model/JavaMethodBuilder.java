package me.builder.internal.processor.model;

import me.builder.Builder;

public class JavaMethodBuilder extends JavaMethodFluent<JavaMethodBuilder> implements Builder<JavaMethod> {

    public JavaMethod build() {
       return new JavaMethod( getName(), getReturnType(), getArguments(), getExceptions(), getAttributes() );
    }
}