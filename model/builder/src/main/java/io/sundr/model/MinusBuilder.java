package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class MinusBuilder extends MinusFluent<MinusBuilder> implements VisitableBuilder<Minus, MinusBuilder> {

  MinusFluent<?> fluent;

  public MinusBuilder() {
    this.fluent = this;
  }

  public MinusBuilder(MinusFluent<?> fluent) {
    this.fluent = fluent;
  }

  public MinusBuilder(Minus instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public MinusBuilder(MinusFluent<?> fluent, Minus instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Minus build() {
    Minus buildable = new Minus(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}