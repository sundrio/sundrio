package me.dsl.internal.processor;

import me.Converter;
import me.codegen.coverters.JavaClazzConverter;
import me.codegen.coverters.JavaMethodConverter;
import me.codegen.coverters.JavaPropertyConverter;
import me.codegen.coverters.JavaTypeConverter;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslProcessorContext {

    private final Elements elements;
    private final Types types;
    private final Converter<String, JavaType> typeConverter;
    private final Converter<VariableElement, JavaProperty> propertyConverter;
    private final JavaMethodConverter methodConverter;
    private final JavaClazzConverter clazzConverter;
    private final DependencyManager dependencyManager;

    public DslProcessorContext(Elements elements, Types types) {
        this.types = types;
        this.elements = elements;
        this.typeConverter = new JavaTypeConverter(elements, true);
        this.propertyConverter = new JavaPropertyConverter(typeConverter);
        this.methodConverter = new JavaMethodConverter(typeConverter, propertyConverter);
        this.clazzConverter = new JavaClazzConverter(types, typeConverter, methodConverter, propertyConverter);
        this.dependencyManager = new DependencyManager(elements, types);
    }

    public Types getTypes() {
        return types;
    }

    public Elements getElements() {
        return elements;
    }

    public Converter<String, JavaType> getTypeConverter() {
        return typeConverter;
    }

    public Converter<VariableElement, JavaProperty> getPropertyConverter() {
        return propertyConverter;
    }

    public JavaMethodConverter getMethodConverter() {
        return methodConverter;
    }

    public JavaClazzConverter getClazzConverter() {
        return clazzConverter;
    }

    public DependencyManager getDependencyManager() {
        return dependencyManager;
    }
}
