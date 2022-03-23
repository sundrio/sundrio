package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class PrimitiveRefBuilder extends PrimitiveRefFluentImpl<PrimitiveRefBuilder>
    implements VisitableBuilder<PrimitiveRef, io.sundr.model.PrimitiveRefBuilder> {
  public PrimitiveRefBuilder() {
    this(false);
  }

  public PrimitiveRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(io.sundr.model.PrimitiveRefFluent<?> fluent) {
    this(fluent, false);
  }

  public PrimitiveRefBuilder(io.sundr.model.PrimitiveRefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(io.sundr.model.PrimitiveRefFluent<?> fluent, io.sundr.model.PrimitiveRef instance) {
    this(fluent, instance, false);
  }

  public PrimitiveRefBuilder(io.sundr.model.PrimitiveRefFluent<?> fluent, io.sundr.model.PrimitiveRef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(io.sundr.model.PrimitiveRef instance) {
    this(instance, false);
  }

  public PrimitiveRefBuilder(io.sundr.model.PrimitiveRef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.PrimitiveRefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.PrimitiveRef build() {
    PrimitiveRef buildable = new PrimitiveRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PrimitiveRefBuilder that = (PrimitiveRefBuilder) o;
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
