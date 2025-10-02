package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ReturnDslThisStepBuilder extends ReturnDslThisStepFluent<ReturnDslThisStepBuilder>
    implements VisitableBuilder<ReturnDslThisStep, ReturnDslThisStepBuilder> {

  ReturnDslThisStepFluent<?> fluent;

  public ReturnDslThisStepBuilder() {
    this(new ReturnDslThisStep());
  }

  public ReturnDslThisStepBuilder(ReturnDslThisStepFluent<?> fluent) {
    this(fluent, new ReturnDslThisStep());
  }

  public ReturnDslThisStepBuilder(ReturnDslThisStep instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ReturnDslThisStepBuilder(ReturnDslThisStepFluent<?> fluent, ReturnDslThisStep instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ReturnDslThisStep build() {
    ReturnDslThisStep buildable = new ReturnDslThisStep();
    return buildable;
  }

}