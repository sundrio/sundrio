package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class InverseBuilder extends InverseFluent<InverseBuilder> implements VisitableBuilder<Inverse, InverseBuilder> {

  InverseFluent<?> fluent;

  public InverseBuilder() {
    this.fluent = this;
  }

  public InverseBuilder(InverseFluent<?> fluent) {
    this.fluent = fluent;
  }

  public InverseBuilder(Inverse instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public InverseBuilder(InverseFluent<?> fluent, Inverse instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Inverse build() {
    Inverse buildable = new Inverse(fluent.buildExpresion());
    return buildable;
  }

}
