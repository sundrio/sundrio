package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PreIncrementBuilder extends PreIncrementFluent<PreIncrementBuilder>
    implements VisitableBuilder<PreIncrement, PreIncrementBuilder> {
  public PreIncrementBuilder() {
    this.fluent = this;
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent, PreIncrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PreIncrementBuilder(PreIncrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  PreIncrementFluent<?> fluent;

  public PreIncrement build() {
    PreIncrement buildable = new PreIncrement(fluent.buildExpression());
    return buildable;
  }

}