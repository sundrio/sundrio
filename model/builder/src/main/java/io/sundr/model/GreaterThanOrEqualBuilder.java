package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class GreaterThanOrEqualBuilder extends GreaterThanOrEqualFluent<GreaterThanOrEqualBuilder>
    implements VisitableBuilder<GreaterThanOrEqual, GreaterThanOrEqualBuilder> {
  public GreaterThanOrEqualBuilder() {
    this(false);
  }

  public GreaterThanOrEqualBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent) {
    this(fluent, false);
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent, GreaterThanOrEqual instance) {
    this(fluent, instance, false);
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqualFluent<?> fluent, GreaterThanOrEqual instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqual instance) {
    this(instance, false);
  }

  public GreaterThanOrEqualBuilder(GreaterThanOrEqual instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  GreaterThanOrEqualFluent<?> fluent;
  Boolean validationEnabled;

  public GreaterThanOrEqual build() {
    GreaterThanOrEqual buildable = new GreaterThanOrEqual(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
