package me.builder.internal.model;

import me.Function;
import me.codegen.coverters.JavaPropertyFunction;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;

import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class ToBuildableJavaProperty implements Function<VariableElement, JavaProperty> {
    
    private static final String BUILDABLE = "BUILDABLE";
    private Function<VariableElement, JavaProperty> delegate;
    
    public ToBuildableJavaProperty(Function<String, JavaType> toType) {
        this.delegate = new JavaPropertyFunction(toType);
    }

    @Override
    public JavaProperty apply(VariableElement variableElement) {
        JavaProperty property = delegate.apply(variableElement);
        boolean isBuildable = property.getType().getAttributes().containsKey(BUILDABLE) ?
                (boolean) property.getType().getAttributes().get(BUILDABLE) : false;
        Map<String, Object> attributes = new HashMap<>(property.getAttributes());
        attributes.put(BUILDABLE, isBuildable);
        return new JavaPropertyBuilder(property).withAttributes(attributes).build();
    }
}
