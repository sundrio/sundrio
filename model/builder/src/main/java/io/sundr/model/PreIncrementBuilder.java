package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PreIncrementBuilder extends PreIncrementFluent<PreIncrementBuilder>
    implements VisitableBuilder<PreIncrement, PreIncrementBuilder> {
  public PreIncrementBuilder() {
    this(false);
  }

  public PreIncrementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent) {
    this(fluent, false);
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent, PreIncrement instance) {
    this(fluent, instance, false);
  }

  public PreIncrementBuilder(PreIncrementFluent<?> fluent, PreIncrement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpression(instance.getExpression());
    }
    this.validationEnabled = validationEnabled;
  }

  public PreIncrementBuilder(PreIncrement instance) {
    this(instance, false);
  }

  public PreIncrementBuilder(PreIncrement instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpression(instance.getExpression());
    }
    this.validationEnabled = validationEnabled;
  }

  PreIncrementFluent<?> fluent;
  Boolean validationEnabled;

  public PreIncrement build() {
    PreIncrement buildable = new PreIncrement(fluent.buildExpression());
    return buildable;
  }

}
