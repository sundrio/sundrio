package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ModuloBuilder extends ModuloFluent<ModuloBuilder> implements VisitableBuilder<Modulo, ModuloBuilder> {
  public ModuloBuilder() {
    this.fluent = this;
  }

  public ModuloBuilder(ModuloFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ModuloBuilder(ModuloFluent<?> fluent, Modulo instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ModuloBuilder(Modulo instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ModuloFluent<?> fluent;

  public Modulo build() {
    Modulo buildable = new Modulo(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}