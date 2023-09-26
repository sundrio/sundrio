package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class NegativeBuilder extends NegativeFluent<NegativeBuilder> implements VisitableBuilder<Negative, NegativeBuilder> {
  public NegativeBuilder() {
    this.fluent = this;
  }

  public NegativeBuilder(NegativeFluent<?> fluent) {
    this.fluent = fluent;
  }

  public NegativeBuilder(NegativeFluent<?> fluent, Negative instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public NegativeBuilder(Negative instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  NegativeFluent<?> fluent;

  public Negative build() {
    Negative buildable = new Negative(fluent.buildExpresion());
    return buildable;
  }

}