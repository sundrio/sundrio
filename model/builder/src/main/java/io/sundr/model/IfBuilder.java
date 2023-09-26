package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class IfBuilder extends IfFluent<IfBuilder> implements VisitableBuilder<If, IfBuilder> {
  public IfBuilder() {
    this.fluent = this;
  }

  public IfBuilder(IfFluent<?> fluent) {
    this.fluent = fluent;
  }

  public IfBuilder(IfFluent<?> fluent, If instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public IfBuilder(If instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  IfFluent<?> fluent;

  public If build() {
    If buildable = new If(fluent.buildCondition(), fluent.buildStatement(), fluent.getElseStatement());
    return buildable;
  }

}