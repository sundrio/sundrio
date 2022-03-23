package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class WildcardRefBuilder extends WildcardRefFluentImpl<WildcardRefBuilder>
    implements VisitableBuilder<WildcardRef, io.sundr.model.WildcardRefBuilder> {
  public WildcardRefBuilder() {
    this(false);
  }

  public WildcardRefBuilder(Boolean validationEnabled) {
    this(new WildcardRef(), validationEnabled);
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent) {
    this(fluent, false);
  }

  public WildcardRefBuilder(io.sundr.model.WildcardRefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this(fluent, new WildcardRef(), validationEnabled);
  }

  public WildcardRefBuilder(io.sundr.model.WildcardRefFluent<?> fluent, io.sundr.model.WildcardRef instance) {
    this(fluent, instance, false);
  }

  public WildcardRefBuilder(io.sundr.model.WildcardRefFluent<?> fluent, io.sundr.model.WildcardRef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withBoundKind(instance.getBoundKind());
    fluent.withBounds(instance.getBounds());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public WildcardRefBuilder(io.sundr.model.WildcardRef instance) {
    this(instance, false);
  }

  public WildcardRefBuilder(io.sundr.model.WildcardRef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withBoundKind(instance.getBoundKind());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.WildcardRefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.WildcardRef build() {
    WildcardRef buildable = new WildcardRef(fluent.getBoundKind(), fluent.getBounds(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    WildcardRefBuilder that = (WildcardRefBuilder) o;
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
