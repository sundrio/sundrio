package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class InstanceOfBuilder extends InstanceOfFluent<InstanceOfBuilder>
    implements VisitableBuilder<InstanceOf, InstanceOfBuilder> {

  InstanceOfFluent<?> fluent;

  public InstanceOfBuilder() {
    this.fluent = this;
  }

  public InstanceOfBuilder(InstanceOfFluent<?> fluent) {
    this.fluent = fluent;
  }

  public InstanceOfBuilder(InstanceOf instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public InstanceOfBuilder(InstanceOfFluent<?> fluent, InstanceOf instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public InstanceOf build() {
    InstanceOf buildable = new InstanceOf(fluent.buildExpression(), fluent.buildType());
    return buildable;
  }

}