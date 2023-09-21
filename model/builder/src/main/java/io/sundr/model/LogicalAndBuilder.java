package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class LogicalAndBuilder extends LogicalAndFluent<LogicalAndBuilder>
    implements VisitableBuilder<LogicalAnd, LogicalAndBuilder> {
  public LogicalAndBuilder() {
    this(false);
  }

  public LogicalAndBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public LogicalAndBuilder(LogicalAndFluent<?> fluent) {
    this(fluent, false);
  }

  public LogicalAndBuilder(LogicalAndFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public LogicalAndBuilder(LogicalAndFluent<?> fluent, LogicalAnd instance) {
    this(fluent, instance, false);
  }

  public LogicalAndBuilder(LogicalAndFluent<?> fluent, LogicalAnd instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public LogicalAndBuilder(LogicalAnd instance) {
    this(instance, false);
  }

  public LogicalAndBuilder(LogicalAnd instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  LogicalAndFluent<?> fluent;
  Boolean validationEnabled;

  public LogicalAnd build() {
    LogicalAnd buildable = new LogicalAnd(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
