package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class TernaryBuilder extends TernaryFluent<TernaryBuilder> implements VisitableBuilder<Ternary, TernaryBuilder> {
  public TernaryBuilder() {
    this.fluent = this;
  }

  public TernaryBuilder(TernaryFluent<?> fluent) {
    this.fluent = fluent;
  }

  public TernaryBuilder(TernaryFluent<?> fluent, Ternary instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public TernaryBuilder(Ternary instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  TernaryFluent<?> fluent;

  public Ternary build() {
    Ternary buildable = new Ternary(fluent.buildCondition(), fluent.buildResult(), fluent.buildAlternative());
    return buildable;
  }

}