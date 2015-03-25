package me.codegen.model;

import me.builder.Builder;

public class AttributeSupportBuilder extends AttributeSupportFluent<AttributeSupportBuilder> implements Builder<AttributeSupport> {

    private final AttributeSupportFluent fluent;
    
    public AttributeSupportBuilder() {
        this.fluent = this;
    }

    public AttributeSupportBuilder(AttributeSupportFluent fluent) {
        this.fluent = fluent;
    }
    
    public AttributeSupportBuilder(AttributeSupport instance) {
        this();
        withAttributes(instance.getAttributes());
    }
    
    public AttributeSupport build() {
       return new AttributeSupport( fluent.getAttributes() );
    }
}