package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PlusBuilder extends PlusFluent<PlusBuilder> implements VisitableBuilder<Plus, PlusBuilder> {

  PlusFluent<?> fluent;

  public PlusBuilder() {
    this.fluent = this;
  }

  public PlusBuilder(PlusFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PlusBuilder(Plus instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PlusBuilder(PlusFluent<?> fluent, Plus instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Plus build() {
    Plus buildable = new Plus(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}