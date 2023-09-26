package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ValueRefBuilder extends ValueRefFluent<ValueRefBuilder> implements VisitableBuilder<ValueRef, ValueRefBuilder> {
  public ValueRefBuilder() {
    this.fluent = this;
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent, ValueRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ValueRefBuilder(ValueRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ValueRefFluent<?> fluent;

  public ValueRef build() {
    ValueRef buildable = new ValueRef(fluent.getValue());
    return buildable;
  }

}