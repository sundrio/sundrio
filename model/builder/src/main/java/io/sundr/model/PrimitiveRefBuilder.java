package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class PrimitiveRefBuilder extends PrimitiveRefFluentImpl<PrimitiveRefBuilder>
    implements io.sundr.model.builder.VisitableBuilder<PrimitiveRef, PrimitiveRefBuilder> {

  PrimitiveRefFluent<?> fluent;
  Boolean validationEnabled;

  public PrimitiveRefBuilder() {
    this(true);
  }

  public PrimitiveRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent) {
    this(fluent, true);
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, PrimitiveRef instance) {
    this(fluent, instance, true);
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, PrimitiveRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(PrimitiveRef instance) {
    this(instance, true);
  }

  public PrimitiveRefBuilder(PrimitiveRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRef build() {
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
