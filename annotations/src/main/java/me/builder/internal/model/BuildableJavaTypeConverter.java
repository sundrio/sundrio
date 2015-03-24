package me.builder.internal.model;

import me.Converter;
import me.builder.annotations.Buildable;
import me.codegen.coverters.JavaTypeConverter;
import me.codegen.model.JavaType;
import me.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.Map;

public class BuildableJavaTypeConverter implements Converter<JavaType, String> {

    private static final String BUILDABLE = "BUILDABLE";
    private final Elements elements;
    private final Types types;
    private final Converter<JavaType, String> delegate;
    
    public BuildableJavaTypeConverter(Elements elements, Types types) {
        this.elements = elements;
        this.types = types;
        delegate = new JavaTypeConverter(elements, true);
    }

    @Override
    public JavaType covert(String fullName) {
        JavaType type = delegate.covert(fullName);
        boolean isBuildable = isBuildable(elements.getTypeElement(fullName));
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
