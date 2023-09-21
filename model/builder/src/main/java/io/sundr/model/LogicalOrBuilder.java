package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class LogicalOrBuilder extends LogicalOrFluent<LogicalOrBuilder>
    implements VisitableBuilder<LogicalOr, LogicalOrBuilder> {
  public LogicalOrBuilder() {
    this(false);
  }

  public LogicalOrBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public LogicalOrBuilder(LogicalOrFluent<?> fluent) {
    this(fluent, false);
  }

  public LogicalOrBuilder(LogicalOrFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public LogicalOrBuilder(LogicalOrFluent<?> fluent, LogicalOr instance) {
    this(fluent, instance, false);
  }

  public LogicalOrBuilder(LogicalOrFluent<?> fluent, LogicalOr instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public LogicalOrBuilder(LogicalOr instance) {
    this(instance, false);
  }

  public LogicalOrBuilder(LogicalOr instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  LogicalOrFluent<?> fluent;
  Boolean validationEnabled;

  public LogicalOr build() {
    LogicalOr buildable = new LogicalOr(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
