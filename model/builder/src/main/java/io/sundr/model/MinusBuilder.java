package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class MinusBuilder extends MinusFluent<MinusBuilder> implements VisitableBuilder<Minus, MinusBuilder> {
  public MinusBuilder() {
    this(false);
  }

  public MinusBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public MinusBuilder(MinusFluent<?> fluent) {
    this(fluent, false);
  }

  public MinusBuilder(MinusFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public MinusBuilder(MinusFluent<?> fluent, Minus instance) {
    this(fluent, instance, false);
  }

  public MinusBuilder(MinusFluent<?> fluent, Minus instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public MinusBuilder(Minus instance) {
    this(instance, false);
  }

  public MinusBuilder(Minus instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  MinusFluent<?> fluent;
  Boolean validationEnabled;

  public Minus build() {
    Minus buildable = new Minus(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
