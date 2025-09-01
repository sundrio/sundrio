package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class IfDslConditionStepBuilder extends IfDslConditionStepFluent<IfDslConditionStepBuilder>
    implements VisitableBuilder<IfDslConditionStep, IfDslConditionStepBuilder> {
  public IfDslConditionStepBuilder() {
    this.fluent = this;
  }

  public IfDslConditionStepBuilder(IfDslConditionStepFluent<?> fluent) {
    this.fluent = fluent;
  }

  public IfDslConditionStepBuilder(IfDslConditionStepFluent<?> fluent, IfDslConditionStep instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public IfDslConditionStepBuilder(IfDslConditionStep instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  IfDslConditionStepFluent<?> fluent;

  public IfDslConditionStep build() {
    IfDslConditionStep buildable = new IfDslConditionStep(fluent.buildCondition());
    return buildable;
  }

}