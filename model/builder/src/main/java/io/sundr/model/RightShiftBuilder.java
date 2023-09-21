package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class RightShiftBuilder extends RightShiftFluent<RightShiftBuilder>
    implements VisitableBuilder<RightShift, RightShiftBuilder> {
  public RightShiftBuilder() {
    this(false);
  }

  public RightShiftBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public RightShiftBuilder(RightShiftFluent<?> fluent) {
    this(fluent, false);
  }

  public RightShiftBuilder(RightShiftFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public RightShiftBuilder(RightShiftFluent<?> fluent, RightShift instance) {
    this(fluent, instance, false);
  }

  public RightShiftBuilder(RightShiftFluent<?> fluent, RightShift instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public RightShiftBuilder(RightShift instance) {
    this(instance, false);
  }

  public RightShiftBuilder(RightShift instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  RightShiftFluent<?> fluent;
  Boolean validationEnabled;

  public RightShift build() {
    RightShift buildable = new RightShift(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
