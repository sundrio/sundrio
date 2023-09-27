package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class InstanceOfBuilder extends InstanceOfFluent<InstanceOfBuilder>
    implements VisitableBuilder<InstanceOf, InstanceOfBuilder> {
  public InstanceOfBuilder() {
    this.fluent = this;
  }

  public InstanceOfBuilder(InstanceOfFluent<?> fluent) {
    this.fluent = fluent;
  }

  public InstanceOfBuilder(InstanceOfFluent<?> fluent, InstanceOf instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public InstanceOfBuilder(InstanceOf instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  InstanceOfFluent<?> fluent;

  public InstanceOf build() {
    InstanceOf buildable = new InstanceOf(fluent.buildExpression(), fluent.buildType());
    return buildable;
  }

}