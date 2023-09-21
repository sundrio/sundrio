package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class IfBuilder extends IfFluent<IfBuilder> implements VisitableBuilder<If, IfBuilder> {
  public IfBuilder() {
    this(false);
  }

  public IfBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public IfBuilder(IfFluent<?> fluent) {
    this(fluent, false);
  }

  public IfBuilder(IfFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public IfBuilder(IfFluent<?> fluent, If instance) {
    this(fluent, instance, false);
  }

  public IfBuilder(IfFluent<?> fluent, If instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withCondition(instance.getCondition());
      fluent.withStatement(instance.getStatement());
      fluent.withElseStatement(instance.getElseStatement());
    }
    this.validationEnabled = validationEnabled;
  }

  public IfBuilder(If instance) {
    this(instance, false);
  }

  public IfBuilder(If instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withStatement(instance.getStatement());
      this.withElseStatement(instance.getElseStatement());
    }
    this.validationEnabled = validationEnabled;
  }

  IfFluent<?> fluent;
  Boolean validationEnabled;

  public If build() {
    If buildable = new If(fluent.buildCondition(), fluent.buildStatement(), fluent.getElseStatement());
    return buildable;
  }

}
