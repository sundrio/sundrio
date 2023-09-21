package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class NotBuilder extends NotFluent<NotBuilder> implements VisitableBuilder<Not, NotBuilder> {
  public NotBuilder() {
    this(false);
  }

  public NotBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public NotBuilder(NotFluent<?> fluent) {
    this(fluent, false);
  }

  public NotBuilder(NotFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public NotBuilder(NotFluent<?> fluent, Not instance) {
    this(fluent, instance, false);
  }

  public NotBuilder(NotFluent<?> fluent, Not instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  public NotBuilder(Not instance) {
    this(instance, false);
  }

  public NotBuilder(Not instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  NotFluent<?> fluent;
  Boolean validationEnabled;

  public Not build() {
    Not buildable = new Not(fluent.buildExpresion());
    return buildable;
  }

}
