package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class SwitchBuilder extends SwitchFluent<SwitchBuilder> implements VisitableBuilder<Switch, SwitchBuilder> {
  public SwitchBuilder() {
    this(false);
  }

  public SwitchBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public SwitchBuilder(SwitchFluent<?> fluent) {
    this(fluent, false);
  }

  public SwitchBuilder(SwitchFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public SwitchBuilder(SwitchFluent<?> fluent, Switch instance) {
    this(fluent, instance, false);
  }

  public SwitchBuilder(SwitchFluent<?> fluent, Switch instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpression(instance.getExpression());
      fluent.withCases(instance.getCases());
      fluent.withDefaultCase(instance.getDefaultCase());
    }
    this.validationEnabled = validationEnabled;
  }

  public SwitchBuilder(Switch instance) {
    this(instance, false);
  }

  public SwitchBuilder(Switch instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpression(instance.getExpression());
      this.withCases(instance.getCases());
      this.withDefaultCase(instance.getDefaultCase());
    }
    this.validationEnabled = validationEnabled;
  }

  SwitchFluent<?> fluent;
  Boolean validationEnabled;

  public Switch build() {
    Switch buildable = new Switch(fluent.buildExpression(), fluent.getCases(), fluent.buildDefaultCase());
    return buildable;
  }

}
