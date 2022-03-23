package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class VoidRefBuilder extends VoidRefFluentImpl<VoidRefBuilder>
    implements VisitableBuilder<VoidRef, io.sundr.model.VoidRefBuilder> {
  public VoidRefBuilder() {
    this(false);
  }

  public VoidRefBuilder(Boolean validationEnabled) {
    this(new VoidRef(), validationEnabled);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent) {
    this(fluent, false);
  }

  public VoidRefBuilder(io.sundr.model.VoidRefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this(fluent, new VoidRef(), validationEnabled);
  }

  public VoidRefBuilder(io.sundr.model.VoidRefFluent<?> fluent, io.sundr.model.VoidRef instance) {
    this(fluent, instance, false);
  }

  public VoidRefBuilder(io.sundr.model.VoidRefFluent<?> fluent, io.sundr.model.VoidRef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public VoidRefBuilder(io.sundr.model.VoidRef instance) {
    this(instance, false);
  }

  public VoidRefBuilder(io.sundr.model.VoidRef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.VoidRefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.VoidRef build() {
    VoidRef buildable = new VoidRef(fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    VoidRefBuilder that = (VoidRefBuilder) o;
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
