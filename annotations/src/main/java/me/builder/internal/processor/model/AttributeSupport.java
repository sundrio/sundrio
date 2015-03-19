package me.builder.internal.processor.model;

import java.util.Collections;
import java.util.Map;

public class AttributeSupport {
    
    private final Map<String, Object> attributes;

    public AttributeSupport(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}
