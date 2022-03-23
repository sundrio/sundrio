package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class AttributeSupportBuilder extends AttributeSupportFluentImpl<AttributeSupportBuilder>
    implements VisitableBuilder<AttributeSupport, io.sundr.model.AttributeSupportBuilder> {
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

  public AttributeSupportBuilder(io.sundr.model.AttributeSupportFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public AttributeSupportBuilder(io.sundr.model.AttributeSupportFluent<?> fluent, io.sundr.model.AttributeSupport instance) {
    this(fluent, instance, false);
  }

  public AttributeSupportBuilder(io.sundr.model.AttributeSupportFluent<?> fluent, io.sundr.model.AttributeSupport instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public AttributeSupportBuilder(io.sundr.model.AttributeSupport instance) {
    this(instance, false);
  }

  public AttributeSupportBuilder(io.sundr.model.AttributeSupport instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.AttributeSupportFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.AttributeSupport build() {
    AttributeSupport buildable = new AttributeSupport(fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    AttributeSupportBuilder that = (AttributeSupportBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(fluent, validationEnabled, super.hashCode());
  }

}
