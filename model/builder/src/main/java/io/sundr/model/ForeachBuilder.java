package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ForeachBuilder extends ForeachFluent<ForeachBuilder> implements VisitableBuilder<Foreach, ForeachBuilder> {
  public ForeachBuilder() {
    this(false);
  }

  public ForeachBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ForeachBuilder(ForeachFluent<?> fluent) {
    this(fluent, false);
  }

  public ForeachBuilder(ForeachFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ForeachBuilder(ForeachFluent<?> fluent, Foreach instance) {
    this(fluent, instance, false);
  }

  public ForeachBuilder(ForeachFluent<?> fluent, Foreach instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withDeclare(instance.getDeclare());
      fluent.withExpression(instance.getExpression());
      fluent.withBody(instance.getBody());
    }
    this.validationEnabled = validationEnabled;
  }

  public ForeachBuilder(Foreach instance) {
    this(instance, false);
  }

  public ForeachBuilder(Foreach instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withDeclare(instance.getDeclare());
      this.withExpression(instance.getExpression());
      this.withBody(instance.getBody());
    }
    this.validationEnabled = validationEnabled;
  }

  ForeachFluent<?> fluent;
  Boolean validationEnabled;

  public Foreach build() {
    Foreach buildable = new Foreach(fluent.buildDeclare(), fluent.buildExpression(), fluent.buildBody());
    return buildable;
  }

}
