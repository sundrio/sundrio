package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class NotBuilder extends NotFluent<NotBuilder> implements VisitableBuilder<Not, NotBuilder> {
  public NotBuilder() {
    this.fluent = this;
  }

  public NotBuilder(NotFluent<?> fluent) {
    this.fluent = fluent;
  }

  public NotBuilder(NotFluent<?> fluent, Not instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public NotBuilder(Not instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  NotFluent<?> fluent;

  public Not build() {
    Not buildable = new Not(fluent.buildExpresion());
    return buildable;
  }

}