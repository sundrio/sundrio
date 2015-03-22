package me.codegen.model;

import me.builder.Builder;

public class JavaMethodBuilder extends JavaMethodFluent<JavaMethodBuilder> implements Builder<JavaMethod> {

    public JavaMethodBuilder() {
    }
    
    public JavaMethodBuilder(JavaMethod instance) {
        withName(instance.getName());
        withReturnType(instance.getReturnType());
        withArguments(instance.getArguments());
        withExceptions(instance.getExceptions());
        withAttributes(instance.getAttributes());
    }
    
    public JavaMethod build() {
       return new JavaMethod( getName(), getReturnType(), getArguments(), getExceptions(), getAttributes() );
    }
}