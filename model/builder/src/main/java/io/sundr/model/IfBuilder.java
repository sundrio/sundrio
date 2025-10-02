package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class IfBuilder extends IfFluent<IfBuilder> implements VisitableBuilder<If, IfBuilder> {

  IfFluent<?> fluent;

  public IfBuilder() {
    this.fluent = this;
  }

  public IfBuilder(IfFluent<?> fluent) {
    this.fluent = fluent;
  }

  public IfBuilder(If instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public IfBuilder(IfFluent<?> fluent, If instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public If build() {
    If buildable = new If(fluent.buildCondition(), fluent.buildStatement(), fluent.getElseStatement());
    return buildable;
  }

}