package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LessThanOrEqualBuilder extends LessThanOrEqualFluent<LessThanOrEqualBuilder>
    implements VisitableBuilder<LessThanOrEqual, LessThanOrEqualBuilder> {

  LessThanOrEqualFluent<?> fluent;

  public LessThanOrEqualBuilder() {
    this.fluent = this;
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LessThanOrEqualBuilder(LessThanOrEqual instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent, LessThanOrEqual instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LessThanOrEqual build() {
    LessThanOrEqual buildable = new LessThanOrEqual(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}