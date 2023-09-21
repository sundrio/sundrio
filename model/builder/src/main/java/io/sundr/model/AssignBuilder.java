package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class AssignBuilder extends AssignFluent<AssignBuilder> implements VisitableBuilder<Assign, AssignBuilder> {
  public AssignBuilder() {
    this(false);
  }

  public AssignBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public AssignBuilder(AssignFluent<?> fluent) {
    this(fluent, false);
  }

  public AssignBuilder(AssignFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public AssignBuilder(AssignFluent<?> fluent, Assign instance) {
    this(fluent, instance, false);
  }

  public AssignBuilder(AssignFluent<?> fluent, Assign instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withTarget(instance.getTarget());
      fluent.withValue(instance.getValue());
    }
    this.validationEnabled = validationEnabled;
  }

  public AssignBuilder(Assign instance) {
    this(instance, false);
  }

  public AssignBuilder(Assign instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withTarget(instance.getTarget());
      this.withValue(instance.getValue());
    }
    this.validationEnabled = validationEnabled;
  }

  AssignFluent<?> fluent;
  Boolean validationEnabled;

  public Assign build() {
    Assign buildable = new Assign(fluent.buildTarget(), fluent.buildValue());
    return buildable;
  }

}
