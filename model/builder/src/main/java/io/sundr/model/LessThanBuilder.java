package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LessThanBuilder extends LessThanFluent<LessThanBuilder> implements VisitableBuilder<LessThan, LessThanBuilder> {

  LessThanFluent<?> fluent;

  public LessThanBuilder() {
    this.fluent = this;
  }

  public LessThanBuilder(LessThanFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LessThanBuilder(LessThan instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public LessThanBuilder(LessThanFluent<?> fluent, LessThan instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LessThan build() {
    LessThan buildable = new LessThan(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}