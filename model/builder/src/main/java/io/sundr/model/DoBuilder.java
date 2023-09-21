package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class DoBuilder extends DoFluent<DoBuilder> implements VisitableBuilder<Do, DoBuilder> {
  public DoBuilder() {
    this(false);
  }

  public DoBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public DoBuilder(DoFluent<?> fluent) {
    this(fluent, false);
  }

  public DoBuilder(DoFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public DoBuilder(DoFluent<?> fluent, Do instance) {
    this(fluent, instance, false);
  }

  public DoBuilder(DoFluent<?> fluent, Do instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withCondition(instance.getCondition());
      fluent.withStatement(instance.getStatement());
    }
    this.validationEnabled = validationEnabled;
  }

  public DoBuilder(Do instance) {
    this(instance, false);
  }

  public DoBuilder(Do instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withStatement(instance.getStatement());
    }
    this.validationEnabled = validationEnabled;
  }

  DoFluent<?> fluent;
  Boolean validationEnabled;

  public Do build() {
    Do buildable = new Do(fluent.buildCondition(), fluent.buildStatement());
    return buildable;
  }

}
