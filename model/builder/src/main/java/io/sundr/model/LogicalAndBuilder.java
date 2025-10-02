package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LogicalAndBuilder extends LogicalAndFluent<LogicalAndBuilder>
    implements VisitableBuilder<LogicalAnd, LogicalAndBuilder> {

  LogicalAndFluent<?> fluent;

  public LogicalAndBuilder() {
    this.fluent = this;
  }

  public LogicalAndBuilder(LogicalAndFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LogicalAndBuilder(LogicalAnd instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public LogicalAndBuilder(LogicalAndFluent<?> fluent, LogicalAnd instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LogicalAnd build() {
    LogicalAnd buildable = new LogicalAnd(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}