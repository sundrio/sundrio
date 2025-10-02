package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class GreaterThanOrEqualBuilder extends GreaterThanOrEqualFluent<GreaterThanOrEqualBuilder>
    implements VisitableBuilder<GreaterThanOrEqual, GreaterThanOrEqualBuilder> {

  GreaterThanOrEqualFluent<?> fluent;

  public GreaterThanOrEqualBuilder() {
    this.fluent = this;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent) {
    this.fluent = fluent;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqual instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent, GreaterThanOrEqual instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public GreaterThanOrEqual build() {
    GreaterThanOrEqual buildable = new GreaterThanOrEqual(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}