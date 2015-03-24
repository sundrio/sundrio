package me.codegen.coverters;

import me.Converter;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;

import javax.lang.model.element.VariableElement;

public class JavaPropertyConverter implements Converter<JavaProperty, VariableElement> {

    private final Converter<JavaType, String> toType;

    public JavaPropertyConverter(Converter<JavaType, String> toType) {
        this.toType = toType;
    }

    @Override
    public JavaProperty covert(VariableElement variableElement) {
        String name = variableElement.getSimpleName().toString();
        boolean isArray = variableElement.asType().toString().endsWith("[]");
        JavaType type = toType.covert(variableElement.asType().toString());
        return new JavaPropertyBuilder()
                .withName(name)
                .withType(type)
                .withArray(isArray)
                .build();
    }

}
