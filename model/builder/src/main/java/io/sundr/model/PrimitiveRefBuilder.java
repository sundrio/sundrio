package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PrimitiveRefBuilder extends PrimitiveRefFluentImpl<PrimitiveRefBuilder>
    implements VisitableBuilder<PrimitiveRef, PrimitiveRefBuilder> {
  public PrimitiveRefBuilder() {
    this(false);
  }

  public PrimitiveRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent) {
    this(fluent, false);
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, PrimitiveRef instance) {
    this(fluent, instance, false);
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, PrimitiveRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public PrimitiveRefBuilder(PrimitiveRef instance) {
    this(instance, false);
  }

  public PrimitiveRefBuilder(PrimitiveRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  PrimitiveRefFluent<?> fluent;
  Boolean validationEnabled;

  public PrimitiveRef build() {
    PrimitiveRef buildable = new PrimitiveRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

}
