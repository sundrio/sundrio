package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class RightUnsignedShiftBuilder extends RightUnsignedShiftFluent<RightUnsignedShiftBuilder>
    implements VisitableBuilder<RightUnsignedShift, RightUnsignedShiftBuilder> {
  public RightUnsignedShiftBuilder() {
    this.fluent = this;
  }

  public RightUnsignedShiftBuilder(RightUnsignedShiftFluent<?> fluent) {
    this.fluent = fluent;
  }

  public RightUnsignedShiftBuilder(RightUnsignedShiftFluent<?> fluent, RightUnsignedShift instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public RightUnsignedShiftBuilder(RightUnsignedShift instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  RightUnsignedShiftFluent<?> fluent;

  public RightUnsignedShift build() {
    RightUnsignedShift buildable = new RightUnsignedShift(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}