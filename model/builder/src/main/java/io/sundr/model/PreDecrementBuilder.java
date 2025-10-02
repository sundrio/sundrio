package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PreDecrementBuilder extends PreDecrementFluent<PreDecrementBuilder>
    implements VisitableBuilder<PreDecrement, PreDecrementBuilder> {

  PreDecrementFluent<?> fluent;

  public PreDecrementBuilder() {
    this.fluent = this;
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PreDecrementBuilder(PreDecrement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent, PreDecrement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PreDecrement build() {
    PreDecrement buildable = new PreDecrement(fluent.buildExpression());
    return buildable;
  }

}