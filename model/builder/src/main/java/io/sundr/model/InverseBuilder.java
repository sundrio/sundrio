package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class InverseBuilder extends InverseFluent<InverseBuilder> implements VisitableBuilder<Inverse, InverseBuilder> {
  public InverseBuilder() {
    this.fluent = this;
  }

  public InverseBuilder(InverseFluent<?> fluent) {
    this.fluent = fluent;
  }

  public InverseBuilder(InverseFluent<?> fluent, Inverse instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public InverseBuilder(Inverse instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  InverseFluent<?> fluent;

  public Inverse build() {
    Inverse buildable = new Inverse(fluent.buildExpresion());
    return buildable;
  }

}