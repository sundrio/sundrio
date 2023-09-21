package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PreDecrementBuilder extends PreDecrementFluent<PreDecrementBuilder>
    implements VisitableBuilder<PreDecrement, PreDecrementBuilder> {
  public PreDecrementBuilder() {
    this(false);
  }

  public PreDecrementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent) {
    this(fluent, false);
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent, PreDecrement instance) {
    this(fluent, instance, false);
  }

  public PreDecrementBuilder(PreDecrementFluent<?> fluent, PreDecrement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpression(instance.getExpression());
    }
    this.validationEnabled = validationEnabled;
  }

  public PreDecrementBuilder(PreDecrement instance) {
    this(instance, false);
  }

  public PreDecrementBuilder(PreDecrement instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpression(instance.getExpression());
    }
    this.validationEnabled = validationEnabled;
  }

  PreDecrementFluent<?> fluent;
  Boolean validationEnabled;

  public PreDecrement build() {
    PreDecrement buildable = new PreDecrement(fluent.buildExpression());
    return buildable;
  }

}
