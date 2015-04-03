package me.codegen.coverters;

import me.Function;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.VariableElement;

public class JavaPropertyFunction implements Function<VariableElement, JavaProperty> {

    private final Function<String, JavaType> toType;

    public JavaPropertyFunction(Function<String, JavaType> toType) {
        this.toType = toType;
    }

    @Override
    public JavaProperty apply(VariableElement variableElement) {
        String name = variableElement.getSimpleName().toString();
        boolean isArray = variableElement.asType().toString().endsWith("[]");
        JavaType type = new JavaTypeBuilder(toType.apply(variableElement.asType().toString())).withArray(isArray).build();
        return new JavaPropertyBuilder()
                .withName(name)
                .withType(type)
                .withArray(isArray)
                .build();
    }

}
