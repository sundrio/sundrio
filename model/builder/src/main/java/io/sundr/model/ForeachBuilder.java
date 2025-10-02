package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ForeachBuilder extends ForeachFluent<ForeachBuilder> implements VisitableBuilder<Foreach, ForeachBuilder> {

  ForeachFluent<?> fluent;

  public ForeachBuilder() {
    this.fluent = this;
  }

  public ForeachBuilder(ForeachFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ForeachBuilder(Foreach instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ForeachBuilder(ForeachFluent<?> fluent, Foreach instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Foreach build() {
    Foreach buildable = new Foreach(fluent.buildDeclare(), fluent.buildExpression(), fluent.buildBody());
    return buildable;
  }

}