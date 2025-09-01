package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ReturnDslVariableStepBuilder extends ReturnDslVariableStepFluent<ReturnDslVariableStepBuilder>
    implements VisitableBuilder<ReturnDslVariableStep, ReturnDslVariableStepBuilder> {
  public ReturnDslVariableStepBuilder() {
    this.fluent = this;
  }

  public ReturnDslVariableStepBuilder(ReturnDslVariableStepFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ReturnDslVariableStepBuilder(ReturnDslVariableStepFluent<?> fluent, ReturnDslVariableStep instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ReturnDslVariableStepBuilder(ReturnDslVariableStep instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ReturnDslVariableStepFluent<?> fluent;

  public ReturnDslVariableStep build() {
    ReturnDslVariableStep buildable = new ReturnDslVariableStep(fluent.getName());
    return buildable;
  }

}