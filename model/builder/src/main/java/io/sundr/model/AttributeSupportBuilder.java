package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class AttributeSupportBuilder extends AttributeSupportFluent<AttributeSupportBuilder>
    implements VisitableBuilder<AttributeSupport, AttributeSupportBuilder> {

  AttributeSupportFluent<?> fluent;

  public AttributeSupportBuilder() {
    this.fluent = this;
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent) {
    this.fluent = fluent;
  }

  public AttributeSupportBuilder(AttributeSupport instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent, AttributeSupport instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public AttributeSupport build() {
    AttributeSupport buildable = new AttributeSupport(fluent.getAttributes());
    return buildable;
  }

}