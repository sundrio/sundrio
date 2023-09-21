package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class MultiplyBuilder extends MultiplyFluent<MultiplyBuilder> implements VisitableBuilder<Multiply, MultiplyBuilder> {
  public MultiplyBuilder() {
    this(false);
  }

  public MultiplyBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public MultiplyBuilder(MultiplyFluent<?> fluent) {
    this(fluent, false);
  }

  public MultiplyBuilder(MultiplyFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public MultiplyBuilder(MultiplyFluent<?> fluent, Multiply instance) {
    this(fluent, instance, false);
  }

  public MultiplyBuilder(MultiplyFluent<?> fluent, Multiply instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public MultiplyBuilder(Multiply instance) {
    this(instance, false);
  }

  public MultiplyBuilder(Multiply instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  MultiplyFluent<?> fluent;
  Boolean validationEnabled;

  public Multiply build() {
    Multiply buildable = new Multiply(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
