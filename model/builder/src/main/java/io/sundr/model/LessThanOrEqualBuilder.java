package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class LessThanOrEqualBuilder extends LessThanOrEqualFluent<LessThanOrEqualBuilder>
    implements VisitableBuilder<LessThanOrEqual, LessThanOrEqualBuilder> {
  public LessThanOrEqualBuilder() {
    this(false);
  }

  public LessThanOrEqualBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent) {
    this(fluent, false);
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent, LessThanOrEqual instance) {
    this(fluent, instance, false);
  }

  public LessThanOrEqualBuilder(LessThanOrEqualFluent<?> fluent, LessThanOrEqual instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public LessThanOrEqualBuilder(LessThanOrEqual instance) {
    this(instance, false);
  }

  public LessThanOrEqualBuilder(LessThanOrEqual instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  LessThanOrEqualFluent<?> fluent;
  Boolean validationEnabled;

  public LessThanOrEqual build() {
    LessThanOrEqual buildable = new LessThanOrEqual(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
