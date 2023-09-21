package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ValueRefBuilder extends ValueRefFluent<ValueRefBuilder> implements VisitableBuilder<ValueRef, ValueRefBuilder> {
  public ValueRefBuilder() {
    this(false);
  }

  public ValueRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent) {
    this(fluent, false);
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent, ValueRef instance) {
    this(fluent, instance, false);
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent, ValueRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withValue(instance.getValue());
    }
    this.validationEnabled = validationEnabled;
  }

  public ValueRefBuilder(ValueRef instance) {
    this(instance, false);
  }

  public ValueRefBuilder(ValueRef instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withValue(instance.getValue());
    }
    this.validationEnabled = validationEnabled;
  }

  ValueRefFluent<?> fluent;
  Boolean validationEnabled;

  public ValueRef build() {
    ValueRef buildable = new ValueRef(fluent.getValue());
    return buildable;
  }

}
