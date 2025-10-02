package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PreIncrementBuilder extends PreIncrementFluent<PreIncrementBuilder>
    implements VisitableBuilder<PreIncrement, PreIncrementBuilder> {

  PreIncrementFluent<?> fluent;

  public PreIncrementBuilder() {
    this.fluent = this;
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PreIncrementBuilder(PreIncrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent, PreIncrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PreIncrement build() {
    PreIncrement buildable = new PreIncrement(fluent.buildExpression());
    return buildable;
  }

}