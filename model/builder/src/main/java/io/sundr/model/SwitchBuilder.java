package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class SwitchBuilder extends SwitchFluent<SwitchBuilder> implements VisitableBuilder<Switch, SwitchBuilder> {

  SwitchFluent<?> fluent;

  public SwitchBuilder() {
    this.fluent = this;
  }

  public SwitchBuilder(SwitchFluent<?> fluent) {
    this.fluent = fluent;
  }

  public SwitchBuilder(Switch instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public SwitchBuilder(SwitchFluent<?> fluent, Switch instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Switch build() {
    Switch buildable = new Switch(fluent.buildExpression(), fluent.getCases(), fluent.buildDefaultCase());
    return buildable;
  }

}