package me.builder.internal.processor.model;

import me.codegen.Property;

import java.util.Map;

public class JavaProperty extends AttributeSupport implements Property<JavaType> {
    
    private final JavaType type;
    private final String name;

    public JavaProperty(JavaType type, String name, Map<String, Object> attributes) {
        super(attributes);
        this.type = type;
        this.name = name;
    }

    public String getNameCapitalized() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0,1).toUpperCase());
        sb.append(name.substring(1));
        return sb.toString();
    }

    public String getNameCapitalizedSingular() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0,1).toUpperCase());
        //TODO: We need to do something better here.
        if (name.endsWith("s")) {
            sb.append(name.substring(1, name.length() - 1));
        } else {
            sb.append(name.substring(1));
        }
        return sb.toString();
    }

    public String getGetter() {
        StringBuilder sb = new StringBuilder();
        if (type.isBoolean()) {
            sb.append("is");
        } else {
            sb.append("get");
        }
        sb.append(getNameCapitalized());
        return sb.toString();
    }

    @Override
    public JavaType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }
}
