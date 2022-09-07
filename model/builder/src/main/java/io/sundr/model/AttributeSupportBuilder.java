package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class AttributeSupportBuilder extends AttributeSupportFluentImpl<AttributeSupportBuilder>
    implements VisitableBuilder<AttributeSupport, AttributeSupportBuilder> {
  public AttributeSupportBuilder() {
    this(false);
  }

  public AttributeSupportBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent) {
    this(fluent, false);
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent, AttributeSupport instance) {
    this(fluent, instance, false);
  }

  public AttributeSupportBuilder(AttributeSupportFluent<?> fluent, AttributeSupport instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public AttributeSupportBuilder(AttributeSupport instance) {
    this(instance, false);
  }

  public AttributeSupportBuilder(AttributeSupport instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  AttributeSupportFluent<?> fluent;
  Boolean validationEnabled;

  public AttributeSupport build() {
    AttributeSupport buildable = new AttributeSupport(fluent.getAttributes());
    return buildable;
  }

}
