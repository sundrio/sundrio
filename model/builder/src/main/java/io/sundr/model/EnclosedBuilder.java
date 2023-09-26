package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class EnclosedBuilder extends EnclosedFluent<EnclosedBuilder> implements VisitableBuilder<Enclosed, EnclosedBuilder> {
  public EnclosedBuilder() {
    this.fluent = this;
  }

  public EnclosedBuilder(EnclosedFluent<?> fluent) {
    this.fluent = fluent;
  }

  public EnclosedBuilder(EnclosedFluent<?> fluent, Enclosed instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public EnclosedBuilder(Enclosed instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  EnclosedFluent<?> fluent;

  public Enclosed build() {
    Enclosed buildable = new Enclosed(fluent.buildExpresion());
    return buildable;
  }

}