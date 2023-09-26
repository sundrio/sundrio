package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ForeachBuilder extends ForeachFluent<ForeachBuilder> implements VisitableBuilder<Foreach, ForeachBuilder> {
  public ForeachBuilder() {
    this.fluent = this;
  }

  public ForeachBuilder(ForeachFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ForeachBuilder(ForeachFluent<?> fluent, Foreach instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ForeachBuilder(Foreach instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ForeachFluent<?> fluent;

  public Foreach build() {
    Foreach buildable = new Foreach(fluent.buildDeclare(), fluent.buildExpression(), fluent.buildBody());
    return buildable;
  }

}