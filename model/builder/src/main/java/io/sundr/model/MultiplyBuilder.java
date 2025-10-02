package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class MultiplyBuilder extends MultiplyFluent<MultiplyBuilder> implements VisitableBuilder<Multiply, MultiplyBuilder> {

  MultiplyFluent<?> fluent;

  public MultiplyBuilder() {
    this.fluent = this;
  }

  public MultiplyBuilder(MultiplyFluent<?> fluent) {
    this.fluent = fluent;
  }

  public MultiplyBuilder(Multiply instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public MultiplyBuilder(MultiplyFluent<?> fluent, Multiply instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Multiply build() {
    Multiply buildable = new Multiply(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}