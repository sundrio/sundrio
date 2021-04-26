package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class VoidRefBuilder extends VoidRefFluentImpl<VoidRefBuilder> implements VisitableBuilder<VoidRef, VoidRefBuilder> {

  VoidRefFluent<?> fluent;
  Boolean validationEnabled;

  public VoidRefBuilder() {
    this(true);
  }

  public VoidRefBuilder(Boolean validationEnabled) {
    this(new VoidRef(), validationEnabled);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent) {
    this(fluent, true);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new VoidRef(), validationEnabled);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance) {
    this(fluent, instance, true);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public VoidRefBuilder(VoidRef instance) {
    this(instance, true);
  }

  public VoidRefBuilder(VoidRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public VoidRef build() {
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
