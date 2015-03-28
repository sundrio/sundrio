package me.codegen.coverters;

import me.Function;
import me.codegen.model.JavaMethod;
import me.codegen.model.JavaMethodBuilder;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

public class JavaMethodFunction implements Function<ExecutableElement, JavaMethod> {

    private final Function<String, JavaType> toJavaType;
    private final Function<VariableElement, JavaProperty> toJavaProperty;

    public JavaMethodFunction(Function<String, JavaType> toJavaType, Function<VariableElement, JavaProperty> toJavaProperty) {
        this.toJavaType = toJavaType;
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaMethod apply(ExecutableElement executableElement) {
        JavaMethodBuilder methodBuilder = new JavaMethodBuilder()
                .withName(executableElement.getSimpleName().toString())
                .withReturnType(toJavaType.apply(executableElement.getReturnType().toString()));
        //Populate constructor parameters
        for (VariableElement variableElement : executableElement.getParameters()) {
            methodBuilder = methodBuilder
                    .withName(executableElement.getSimpleName().toString())
                    .withReturnType(toJavaType.apply(executableElement.getReturnType().toString()))
                    .addToArguments(toJavaProperty.apply(variableElement));
            for (TypeMirror thrownType : executableElement.getThrownTypes()) {
                methodBuilder = methodBuilder.addToExceptions(toJavaType.apply(thrownType.toString()));
            }
        }
        return methodBuilder.build();
    }
}
