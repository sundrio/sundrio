package me.codegen.model;

import me.builder.Fluent;

import java.util.HashMap;
import java.util.Map;

public class AttributeSupportFluent<T extends AttributeSupportFluent<T>> implements Fluent<T> {


    private Map<String,Object> attributes  = new HashMap();
    
	public T withAttributes(Map attributes) {
		this.attributes=attributes;
		return (T)this;
	}
	public T addAttributes(String key, Object value) {
		this.attributes.put(key, value);
		return (T) this;
	}
	public Map<String,Object> getAttributes() {
		return this.attributes;
	}

}