package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PreDecrementBuilder extends PreDecrementFluent<PreDecrementBuilder>
    implements VisitableBuilder<PreDecrement, PreDecrementBuilder> {
  public PreDecrementBuilder() {
    this.fluent = this;
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent, PreDecrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PreDecrementBuilder(PreDecrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  PreDecrementFluent<?> fluent;

  public PreDecrement build() {
    PreDecrement buildable = new PreDecrement(fluent.buildExpression());
    return buildable;
  }

}