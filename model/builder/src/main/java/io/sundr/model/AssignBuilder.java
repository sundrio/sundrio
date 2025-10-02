package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class AssignBuilder extends AssignFluent<AssignBuilder> implements VisitableBuilder<Assign, AssignBuilder> {

  AssignFluent<?> fluent;

  public AssignBuilder() {
    this.fluent = this;
  }

  public AssignBuilder(AssignFluent<?> fluent) {
    this.fluent = fluent;
  }

  public AssignBuilder(Assign instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public AssignBuilder(AssignFluent<?> fluent, Assign instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Assign build() {
    Assign buildable = new Assign(fluent.buildTarget(), fluent.buildValue());
    return buildable;
  }

}