package me.codegen.model;

import me.builder.Builder;

public class AttributeSupportBuilder extends AttributeSupportFluent<AttributeSupportBuilder> implements Builder<AttributeSupport> {

    public AttributeSupportBuilder() {
    }
    
    public AttributeSupportBuilder(AttributeSupport instance) {
        withAttributes(instance.getAttributes());
    }
    
    public AttributeSupport build() {
       return new AttributeSupport( getAttributes() );
    }
}