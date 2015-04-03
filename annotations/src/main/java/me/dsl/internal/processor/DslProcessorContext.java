package me.dsl.internal.processor;

import me.Function;
import me.codegen.coverters.JavaClazzFunction;
import me.codegen.coverters.JavaMethodFunction;
import me.codegen.coverters.JavaPropertyFunction;
import me.codegen.coverters.JavaTypeFunction;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaType;
import me.dsl.internal.functions.ToTransitionAnnotations;
import me.dsl.internal.functions.ToTransitionClassName;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslProcessorContext {

    private final Elements elements;
    private final Types types;
    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final JavaMethodFunction toMethod;
    private final JavaClazzFunction toClazz;
    private final DependencyManager dependencyManager;
    private final ToTransitionAnnotations toTransitionAnnotations;
    private final ToTransitionClassName toTransitionClassName;

    public DslProcessorContext(Elements elements, Types types) {
        this.types = types;
        this.elements = elements;
        this.toType = new JavaTypeFunction(elements, true);
        this.toProperty = new JavaPropertyFunction(toType);
        this.toMethod = new JavaMethodFunction(toType, toProperty);
        this.toClazz = new JavaClazzFunction(toType, toMethod, toProperty);
        this.dependencyManager = new DependencyManager(elements);
        this.toTransitionAnnotations = new ToTransitionAnnotations(elements);
        toTransitionClassName = new ToTransitionClassName(elements);
    }

    public Types getTypes() {
        return types;
    }

    public Elements getElements() {
        return elements;
    }

    public Function<String, JavaType> getToType() {
        return toType;
    }

    public Function<VariableElement, JavaProperty> getToProperty() {
        return toProperty;
    }

    public JavaMethodFunction getToMethod() {
        return toMethod;
    }

    public JavaClazzFunction getToClazz() {
        return toClazz;
    }

    public DependencyManager getDependencyManager() {
        return dependencyManager;
    }

    public ToTransitionAnnotations getToTransitionAnnotations() {
        return toTransitionAnnotations;
    }

    public ToTransitionClassName getToTransitionClassName() {
        return toTransitionClassName;
    }
}
