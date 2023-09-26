package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LeftShiftBuilder extends LeftShiftFluent<LeftShiftBuilder>
    implements VisitableBuilder<LeftShift, LeftShiftBuilder> {
  public LeftShiftBuilder() {
    this.fluent = this;
  }

  public LeftShiftBuilder(LeftShiftFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LeftShiftBuilder(LeftShiftFluent<?> fluent, LeftShift instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LeftShiftBuilder(LeftShift instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  LeftShiftFluent<?> fluent;

  public LeftShift build() {
    LeftShift buildable = new LeftShift(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}