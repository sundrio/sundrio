package me.codegen.model;

import me.builder.Builder;

public class JavaMethodBuilder extends JavaMethodFluent<JavaMethodBuilder> implements Builder<JavaMethod> {

    private final JavaMethodFluent fluent;
    
    public JavaMethodBuilder() {
        this.fluent = this;
    }

    public JavaMethodBuilder(JavaMethodFluent fluent) {
        this.fluent = fluent;
    }
    
    public JavaMethodBuilder(JavaMethod instance) {
        this();
        withName(instance.getName());
        withReturnType(instance.getReturnType());
        withArguments(instance.getArguments());
        withExceptions(instance.getExceptions());
        withAttributes(instance.getAttributes());
    }
    
    public JavaMethod build() {
       return new JavaMethod( fluent.getName(), fluent.getReturnType(), fluent.getArguments(), fluent.getExceptions(), fluent.getAttributes() );
    }
}