package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class GreaterThanBuilder extends GreaterThanFluent<GreaterThanBuilder>
    implements VisitableBuilder<GreaterThan, GreaterThanBuilder> {
  public GreaterThanBuilder() {
    this.fluent = this;
  }

  public GreaterThanBuilder(GreaterThanFluent<?> fluent) {
    this.fluent = fluent;
  }

  public GreaterThanBuilder(GreaterThanFluent<?> fluent, GreaterThan instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public GreaterThanBuilder(GreaterThan instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  GreaterThanFluent<?> fluent;

  public GreaterThan build() {
    GreaterThan buildable = new GreaterThan(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}