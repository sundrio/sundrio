package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PositiveBuilder extends PositiveFluent<PositiveBuilder> implements VisitableBuilder<Positive, PositiveBuilder> {
  public PositiveBuilder() {
    this(false);
  }

  public PositiveBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PositiveBuilder(PositiveFluent<?> fluent) {
    this(fluent, false);
  }

  public PositiveBuilder(PositiveFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PositiveBuilder(PositiveFluent<?> fluent, Positive instance) {
    this(fluent, instance, false);
  }

  public PositiveBuilder(PositiveFluent<?> fluent, Positive instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  public PositiveBuilder(Positive instance) {
    this(instance, false);
  }

  public PositiveBuilder(Positive instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  PositiveFluent<?> fluent;
  Boolean validationEnabled;

  public Positive build() {
    Positive buildable = new Positive(fluent.buildExpresion());
    return buildable;
  }

}
