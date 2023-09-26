package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class DivideBuilder extends DivideFluent<DivideBuilder> implements VisitableBuilder<Divide, DivideBuilder> {
  public DivideBuilder() {
    this.fluent = this;
  }

  public DivideBuilder(DivideFluent<?> fluent) {
    this.fluent = fluent;
  }

  public DivideBuilder(DivideFluent<?> fluent, Divide instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public DivideBuilder(Divide instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  DivideFluent<?> fluent;

  public Divide build() {
    Divide buildable = new Divide(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}