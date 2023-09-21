package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class LeftShiftBuilder extends LeftShiftFluent<LeftShiftBuilder>
    implements VisitableBuilder<LeftShift, LeftShiftBuilder> {
  public LeftShiftBuilder() {
    this(false);
  }

  public LeftShiftBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public LeftShiftBuilder(LeftShiftFluent<?> fluent) {
    this(fluent, false);
  }

  public LeftShiftBuilder(LeftShiftFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public LeftShiftBuilder(LeftShiftFluent<?> fluent, LeftShift instance) {
    this(fluent, instance, false);
  }

  public LeftShiftBuilder(LeftShiftFluent<?> fluent, LeftShift instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public LeftShiftBuilder(LeftShift instance) {
    this(instance, false);
  }

  public LeftShiftBuilder(LeftShift instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  LeftShiftFluent<?> fluent;
  Boolean validationEnabled;

  public LeftShift build() {
    LeftShift buildable = new LeftShift(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
