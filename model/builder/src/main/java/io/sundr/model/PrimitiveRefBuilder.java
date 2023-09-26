package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PrimitiveRefBuilder extends PrimitiveRefFluent<PrimitiveRefBuilder>
    implements VisitableBuilder<PrimitiveRef, PrimitiveRefBuilder> {
  public PrimitiveRefBuilder() {
    this.fluent = this;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PrimitiveRefBuilder(PrimitiveRefFluent<?> fluent, PrimitiveRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PrimitiveRefBuilder(PrimitiveRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  PrimitiveRefFluent<?> fluent;

  public PrimitiveRef build() {
    PrimitiveRef buildable = new PrimitiveRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

}