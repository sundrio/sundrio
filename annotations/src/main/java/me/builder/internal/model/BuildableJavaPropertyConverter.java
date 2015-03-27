package me.builder.internal.model;

import me.Converter;
import me.codegen.coverters.JavaPropertyConverter;
import me.codegen.model.JavaProperty;
import me.codegen.model.JavaPropertyBuilder;
import me.codegen.model.JavaType;

import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class BuildableJavaPropertyConverter implements Converter<VariableElement, JavaProperty> {
    
    private static final String BUILDABLE = "BUILDABLE";
    private Converter<VariableElement, JavaProperty> delegate;
    
    public BuildableJavaPropertyConverter(Converter<String, JavaType> toType) {
        this.delegate = new JavaPropertyConverter(toType);
    }

    @Override
    public JavaProperty covert(VariableElement variableElement) {
        JavaProperty property = delegate.covert(variableElement);
        boolean isBuildable = property.getType().getAttributes().containsKey(BUILDABLE) ?
                (boolean) property.getType().getAttributes().get(BUILDABLE) : false;
        Map<String, Object> attributes = new HashMap<>(property.getAttributes());
        attributes.put(BUILDABLE, isBuildable);
        return new JavaPropertyBuilder(property).withAttributes(attributes).build();
    }
}
