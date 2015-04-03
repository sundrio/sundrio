package me.builder.internal.model;

import me.Function;
import me.builder.annotations.Buildable;
import me.codegen.coverters.JavaTypeFunction;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.Map;

public class ToBuildableJavaType implements Function<String, JavaType> {

    private static final String BUILDABLE = "BUILDABLE";
    private final Elements elements;
    private final Function<String, JavaType> delegate;
    
    public ToBuildableJavaType(Elements elements) {
        this.elements = elements;
        delegate = new JavaTypeFunction(elements, true);
    }

    @Override
    public JavaType apply(String fullName) {
        JavaType type = delegate.apply(fullName);
        TypeElement typeElement = elements.getTypeElement(fullName);
        boolean isBuildable = false;
        if (fullName.endsWith("[]")) {
            typeElement  = elements.getTypeElement(fullName.substring(0, fullName.length() - 2));
            isBuildable = isBuildable(typeElement);
        } else if (type.isCollection()) {
            for (JavaType genericType : type.getGenericTypes()) {
                isBuildable = isBuildable(elements.getTypeElement(genericType.getFullyQualifiedName()));
            }
        } else {
            isBuildable = isBuildable(typeElement);
        }
        Map<String, Object> attributes = new HashMap<>(type.getAttributes());
        attributes.put(BUILDABLE, isBuildable);
        
        return new JavaTypeBuilder(type).withAttributes(attributes).build();
    }

    private boolean isBuildable(TypeElement typeElement) {
        if (typeElement != null) {
            for (Element el : typeElement.getEnclosedElements()) {
                if (el.getAnnotation(Buildable.class) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
