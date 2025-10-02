package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PrimitiveRefBuilder extends PrimitiveRefFluent<PrimitiveRefBuilder>
    implements VisitableBuilder<PrimitiveRef, PrimitiveRefBuilder> {

  PrimitiveRefFluent<?> fluent;

  public PrimitiveRefBuilder() {
    this.fluent = this;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PrimitiveRefBuilder(PrimitiveRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, PrimitiveRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PrimitiveRef build() {
    PrimitiveRef buildable = new PrimitiveRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

}