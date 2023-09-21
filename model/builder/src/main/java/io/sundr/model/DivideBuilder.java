package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class DivideBuilder extends DivideFluent<DivideBuilder> implements VisitableBuilder<Divide, DivideBuilder> {
  public DivideBuilder() {
    this(false);
  }

  public DivideBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public DivideBuilder(DivideFluent<?> fluent) {
    this(fluent, false);
  }

  public DivideBuilder(DivideFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public DivideBuilder(DivideFluent<?> fluent, Divide instance) {
    this(fluent, instance, false);
  }

  public DivideBuilder(DivideFluent<?> fluent, Divide instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public DivideBuilder(Divide instance) {
    this(instance, false);
  }

  public DivideBuilder(Divide instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  DivideFluent<?> fluent;
  Boolean validationEnabled;

  public Divide build() {
    Divide buildable = new Divide(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
