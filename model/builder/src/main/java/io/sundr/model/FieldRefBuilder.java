package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class FieldRefBuilder extends FieldRefFluent<FieldRefBuilder> implements VisitableBuilder<FieldRef, FieldRefBuilder> {

  FieldRefFluent<?> fluent;

  public FieldRefBuilder() {
    this.fluent = this;
  }

  public FieldRefBuilder(FieldRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public FieldRefBuilder(FieldRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public FieldRefBuilder(FieldRefFluent<?> fluent, FieldRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public FieldRef build() {
    FieldRef buildable = new FieldRef(fluent.buildField(), fluent.buildScope());
    return buildable;
  }

}
