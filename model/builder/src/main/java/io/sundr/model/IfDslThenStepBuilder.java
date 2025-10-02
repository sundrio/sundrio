package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class IfDslThenStepBuilder extends IfDslThenStepFluent<IfDslThenStepBuilder>
    implements VisitableBuilder<IfDslThenStep, IfDslThenStepBuilder> {

  IfDslThenStepFluent<?> fluent;

  public IfDslThenStepBuilder() {
    this.fluent = this;
  }

  public IfDslThenStepBuilder(IfDslThenStepFluent<?> fluent) {
    this.fluent = fluent;
  }

  public IfDslThenStepBuilder(IfDslThenStep instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public IfDslThenStepBuilder(IfDslThenStepFluent<?> fluent, IfDslThenStep instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public IfDslThenStep build() {
    IfDslThenStep buildable = new IfDslThenStep(fluent.buildCondition(), fluent.buildStatements());
    return buildable;
  }

}