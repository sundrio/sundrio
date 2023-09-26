package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LessThanOrEqualBuilder extends LessThanOrEqualFluent<LessThanOrEqualBuilder>
    implements VisitableBuilder<LessThanOrEqual, LessThanOrEqualBuilder> {
  public LessThanOrEqualBuilder() {
    this.fluent = this;
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent, LessThanOrEqual instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LessThanOrEqualBuilder(LessThanOrEqual instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  LessThanOrEqualFluent<?> fluent;

  public LessThanOrEqual build() {
    LessThanOrEqual buildable = new LessThanOrEqual(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}