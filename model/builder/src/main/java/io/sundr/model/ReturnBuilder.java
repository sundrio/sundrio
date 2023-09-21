package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ReturnBuilder extends ReturnFluent<ReturnBuilder> implements VisitableBuilder<Return, ReturnBuilder> {
  public ReturnBuilder() {
    this(false);
  }

  public ReturnBuilder(Boolean validationEnabled) {
    this(new Return(), validationEnabled);
  }

  public ReturnBuilder(ReturnFluent<?> fluent) {
    this(fluent, false);
  }

  public ReturnBuilder(ReturnFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Return(), validationEnabled);
  }

  public ReturnBuilder(ReturnFluent<?> fluent, Return instance) {
    this(fluent, instance, false);
  }

  public ReturnBuilder(ReturnFluent<?> fluent, Return instance, Boolean validationEnabled) {
    this.fluent = fluent;
    instance = (instance != null ? instance : new Return());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  public ReturnBuilder(Return instance) {
    this(instance, false);
  }

  public ReturnBuilder(Return instance, Boolean validationEnabled) {
    this.fluent = this;
    instance = (instance != null ? instance : new Return());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  ReturnFluent<?> fluent;
  Boolean validationEnabled;

  public Return build() {
    Return buildable = new Return();
    return buildable;
  }

}
