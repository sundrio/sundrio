package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class RightUnsignedShiftBuilder extends RightUnsignedShiftFluent<RightUnsignedShiftBuilder>
    implements VisitableBuilder<RightUnsignedShift, RightUnsignedShiftBuilder> {
  public RightUnsignedShiftBuilder() {
    this(false);
  }

  public RightUnsignedShiftBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public RightUnsignedShiftBuilder(RightUnsignedShiftFluent<?> fluent) {
    this(fluent, false);
  }

  public RightUnsignedShiftBuilder(RightUnsignedShiftFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public RightUnsignedShiftBuilder(RightUnsignedShiftFluent<?> fluent, RightUnsignedShift instance) {
    this(fluent, instance, false);
  }

  public RightUnsignedShiftBuilder(RightUnsignedShiftFluent<?> fluent, RightUnsignedShift instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public RightUnsignedShiftBuilder(RightUnsignedShift instance) {
    this(instance, false);
  }

  public RightUnsignedShiftBuilder(RightUnsignedShift instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  RightUnsignedShiftFluent<?> fluent;
  Boolean validationEnabled;

  public RightUnsignedShift build() {
    RightUnsignedShift buildable = new RightUnsignedShift(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
