package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ModuloBuilder extends ModuloFluent<ModuloBuilder> implements VisitableBuilder<Modulo, ModuloBuilder> {
  public ModuloBuilder() {
    this(false);
  }

  public ModuloBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ModuloBuilder(ModuloFluent<?> fluent) {
    this(fluent, false);
  }

  public ModuloBuilder(ModuloFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ModuloBuilder(ModuloFluent<?> fluent, Modulo instance) {
    this(fluent, instance, false);
  }

  public ModuloBuilder(ModuloFluent<?> fluent, Modulo instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public ModuloBuilder(Modulo instance) {
    this(instance, false);
  }

  public ModuloBuilder(Modulo instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  ModuloFluent<?> fluent;
  Boolean validationEnabled;

  public Modulo build() {
    Modulo buildable = new Modulo(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
