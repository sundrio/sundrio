package me.codegen.model;

import me.builder.Fluent;

import java.util.HashMap;
import java.util.Map;

public class AttributeSupportFluent<T extends AttributeSupportFluent<T>> implements Fluent<T> {

    private Map<String, Object> attributes = new HashMap();

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public T withAttributes(Map<String, Object> attributes) {
        this.attributes.clear();
        this.attributes.putAll(attributes);
        return (T) this;
    }

    public T addToAttributes(String key, Object value) {
        this.attributes.put(key, value);
        return (T) this;
    }


}
