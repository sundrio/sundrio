package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ModuloBuilder extends ModuloFluent<ModuloBuilder> implements VisitableBuilder<Modulo, ModuloBuilder> {

  ModuloFluent<?> fluent;

  public ModuloBuilder() {
    this.fluent = this;
  }

  public ModuloBuilder(ModuloFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ModuloBuilder(Modulo instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ModuloBuilder(ModuloFluent<?> fluent, Modulo instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Modulo build() {
    Modulo buildable = new Modulo(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}