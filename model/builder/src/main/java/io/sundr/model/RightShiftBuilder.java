package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class RightShiftBuilder extends RightShiftFluent<RightShiftBuilder>
    implements VisitableBuilder<RightShift, RightShiftBuilder> {
  public RightShiftBuilder() {
    this.fluent = this;
  }

  public RightShiftBuilder(RightShiftFluent<?> fluent) {
    this.fluent = fluent;
  }

  public RightShiftBuilder(RightShiftFluent<?> fluent, RightShift instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public RightShiftBuilder(RightShift instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  RightShiftFluent<?> fluent;

  public RightShift build() {
    RightShift buildable = new RightShift(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}