package me.builder.internal.processor.model;

import me.builder.Builder;

public class JavaTypeBuilder extends JavaTypeFluent<JavaTypeBuilder> implements Builder<JavaType> {

    public JavaType build() {
       return new JavaType( getPackageName(), getClassName(), isArray(), isCollection(), isConcrete(), getDefaultImplementation(), getSuperClass(), getGenericTypes(), getAttributes() );
    }
}