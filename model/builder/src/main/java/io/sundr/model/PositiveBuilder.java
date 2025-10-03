package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PositiveBuilder extends PositiveFluent<PositiveBuilder> implements VisitableBuilder<Positive, PositiveBuilder> {

  PositiveFluent<?> fluent;

  public PositiveBuilder() {
    this.fluent = this;
  }

  public PositiveBuilder(PositiveFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PositiveBuilder(Positive instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PositiveBuilder(PositiveFluent<?> fluent, Positive instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Positive build() {
    Positive buildable = new Positive(fluent.buildExpresion());
    return buildable;
  }

}
