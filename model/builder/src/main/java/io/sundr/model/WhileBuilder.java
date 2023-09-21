package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class WhileBuilder extends WhileFluent<WhileBuilder> implements VisitableBuilder<While, WhileBuilder> {
  public WhileBuilder() {
    this(false);
  }

  public WhileBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public WhileBuilder(WhileFluent<?> fluent) {
    this(fluent, false);
  }

  public WhileBuilder(WhileFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public WhileBuilder(WhileFluent<?> fluent, While instance) {
    this(fluent, instance, false);
  }

  public WhileBuilder(WhileFluent<?> fluent, While instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withCondition(instance.getCondition());
      fluent.withStatement(instance.getStatement());
    }
    this.validationEnabled = validationEnabled;
  }

  public WhileBuilder(While instance) {
    this(instance, false);
  }

  public WhileBuilder(While instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withStatement(instance.getStatement());
    }
    this.validationEnabled = validationEnabled;
  }

  WhileFluent<?> fluent;
  Boolean validationEnabled;

  public While build() {
    While buildable = new While(fluent.buildCondition(), fluent.buildStatement());
    return buildable;
  }

}
