package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LogicalOrBuilder extends LogicalOrFluent<LogicalOrBuilder>
    implements VisitableBuilder<LogicalOr, LogicalOrBuilder> {

  LogicalOrFluent<?> fluent;

  public LogicalOrBuilder() {
    this.fluent = this;
  }

  public LogicalOrBuilder(LogicalOrFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LogicalOrBuilder(LogicalOr instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public LogicalOrBuilder(LogicalOrFluent<?> fluent, LogicalOr instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LogicalOr build() {
    LogicalOr buildable = new LogicalOr(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
