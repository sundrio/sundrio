package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class GreaterThanBuilder extends GreaterThanFluent<GreaterThanBuilder>
    implements VisitableBuilder<GreaterThan, GreaterThanBuilder> {
  public GreaterThanBuilder() {
    this(false);
  }

  public GreaterThanBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public GreaterThanBuilder(GreaterThanFluent<?> fluent) {
    this(fluent, false);
  }

  public GreaterThanBuilder(GreaterThanFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public GreaterThanBuilder(GreaterThanFluent<?> fluent, GreaterThan instance) {
    this(fluent, instance, false);
  }

  public GreaterThanBuilder(GreaterThanFluent<?> fluent, GreaterThan instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public GreaterThanBuilder(GreaterThan instance) {
    this(instance, false);
  }

  public GreaterThanBuilder(GreaterThan instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  GreaterThanFluent<?> fluent;
  Boolean validationEnabled;

  public GreaterThan build() {
    GreaterThan buildable = new GreaterThan(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
