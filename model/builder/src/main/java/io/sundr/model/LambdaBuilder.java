package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LambdaBuilder extends LambdaFluent<LambdaBuilder> implements VisitableBuilder<Lambda, LambdaBuilder> {

  LambdaFluent<?> fluent;

  public LambdaBuilder() {
    this.fluent = this;
  }

  public LambdaBuilder(LambdaFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LambdaBuilder(Lambda instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public LambdaBuilder(LambdaFluent<?> fluent, Lambda instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Lambda build() {
    Lambda buildable = new Lambda(fluent.getParameters(), fluent.buildStatement());
    return buildable;
  }

}
