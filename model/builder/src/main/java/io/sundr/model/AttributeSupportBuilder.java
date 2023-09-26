package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class AttributeSupportBuilder extends AttributeSupportFluent<AttributeSupportBuilder>
    implements VisitableBuilder<AttributeSupport, AttributeSupportBuilder> {
  public AttributeSupportBuilder() {
    this.fluent = this;
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent) {
    this.fluent = fluent;
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent, AttributeSupport instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public AttributeSupportBuilder(AttributeSupport instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  AttributeSupportFluent<?> fluent;

  public AttributeSupport build() {
    AttributeSupport buildable = new AttributeSupport(fluent.getAttributes());
    return buildable;
  }

}