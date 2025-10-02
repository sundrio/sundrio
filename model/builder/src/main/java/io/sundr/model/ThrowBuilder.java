package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ThrowBuilder extends ThrowFluent<ThrowBuilder> implements VisitableBuilder<Throw, ThrowBuilder> {

  ThrowFluent<?> fluent;

  public ThrowBuilder() {
    this.fluent = this;
  }

  public ThrowBuilder(ThrowFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ThrowBuilder(Throw instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ThrowBuilder(ThrowFluent<?> fluent, Throw instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Throw build() {
    Throw buildable = new Throw(fluent.buildException());
    return buildable;
  }

}