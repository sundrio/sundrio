package me.codegen.impl;

import me.builder.annotations.Buildable;

import java.util.Collections;
import java.util.Map;

public class AttributeSupport {
    
    private final Map<String, Object> attributes;

    @Buildable
    public AttributeSupport(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}
