package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ValueRefBuilder extends ValueRefFluent<ValueRefBuilder> implements VisitableBuilder<ValueRef, ValueRefBuilder> {

  ValueRefFluent<?> fluent;

  public ValueRefBuilder() {
    this.fluent = this;
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ValueRefBuilder(ValueRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ValueRefBuilder(ValueRefFluent<?> fluent, ValueRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ValueRef build() {
    ValueRef buildable = new ValueRef(fluent.getValue());
    return buildable;
  }

}
