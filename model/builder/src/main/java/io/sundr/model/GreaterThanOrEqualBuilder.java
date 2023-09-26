package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class GreaterThanOrEqualBuilder extends GreaterThanOrEqualFluent<GreaterThanOrEqualBuilder>
    implements VisitableBuilder<GreaterThanOrEqual, GreaterThanOrEqualBuilder> {
  public GreaterThanOrEqualBuilder() {
    this.fluent = this;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent) {
    this.fluent = fluent;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent, GreaterThanOrEqual instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqual instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  GreaterThanOrEqualFluent<?> fluent;

  public GreaterThanOrEqual build() {
    GreaterThanOrEqual buildable = new GreaterThanOrEqual(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}