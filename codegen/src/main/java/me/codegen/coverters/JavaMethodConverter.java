package me.codegen.coverters;

import me.Converter;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaProperty;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class JavaMethodConverter implements Converter<JavaMethod, ExecutableElement> {

    private final Converter<JavaProperty, VariableElement> toJavaProperty;

    public JavaMethodConverter(Converter<JavaProperty, VariableElement> toJavaProperty) {
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaMethod covert(ExecutableElement executableElement) {
        JavaMethodBuilder constructorBuilder = new JavaMethodBuilder();
        //Populate constructor parameters
        for (VariableElement variableElement : executableElement.getParameters()) {
            constructorBuilder = constructorBuilder.addToArguments(
                    toJavaProperty.covert(variableElement));
        }
        return constructorBuilder.build();
    }
}
