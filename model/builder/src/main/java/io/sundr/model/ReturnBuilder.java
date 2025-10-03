package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ReturnBuilder extends ReturnFluent<ReturnBuilder> implements VisitableBuilder<Return, ReturnBuilder> {

  ReturnFluent<?> fluent;

  public ReturnBuilder() {
    this.fluent = this;
  }

  public ReturnBuilder(ReturnFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ReturnBuilder(Return instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ReturnBuilder(ReturnFluent<?> fluent, Return instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Return build() {
    Return buildable = new Return(fluent.buildExpression());
    return buildable;
  }

}
