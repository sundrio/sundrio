package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class StatementAdapterBuilder extends StatementAdapterFluent<StatementAdapterBuilder>
    implements VisitableBuilder<StatementAdapter, StatementAdapterBuilder> {
  public StatementAdapterBuilder() {
    this.fluent = this;
  }

  public StatementAdapterBuilder(StatementAdapterFluent<?> fluent) {
    this.fluent = fluent;
  }

  public StatementAdapterBuilder(StatementAdapterFluent<?> fluent, StatementAdapter instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public StatementAdapterBuilder(StatementAdapter instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  StatementAdapterFluent<?> fluent;

  public StatementAdapter build() {
    StatementAdapter buildable = new StatementAdapter(fluent.buildExpression());
    return buildable;
  }

}