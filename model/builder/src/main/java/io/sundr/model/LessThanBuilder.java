package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class LessThanBuilder extends LessThanFluent<LessThanBuilder> implements VisitableBuilder<LessThan, LessThanBuilder> {
  public LessThanBuilder() {
    this(false);
  }

  public LessThanBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public LessThanBuilder(LessThanFluent<?> fluent) {
    this(fluent, false);
  }

  public LessThanBuilder(LessThanFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public LessThanBuilder(LessThanFluent<?> fluent, LessThan instance) {
    this(fluent, instance, false);
  }

  public LessThanBuilder(LessThanFluent<?> fluent, LessThan instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public LessThanBuilder(LessThan instance) {
    this(instance, false);
  }

  public LessThanBuilder(LessThan instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  LessThanFluent<?> fluent;
  Boolean validationEnabled;

  public LessThan build() {
    LessThan buildable = new LessThan(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
