package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class EnclosedBuilder extends EnclosedFluent<EnclosedBuilder> implements VisitableBuilder<Enclosed, EnclosedBuilder> {
  public EnclosedBuilder() {
    this(false);
  }

  public EnclosedBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public EnclosedBuilder(EnclosedFluent<?> fluent) {
    this(fluent, false);
  }

  public EnclosedBuilder(EnclosedFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public EnclosedBuilder(EnclosedFluent<?> fluent, Enclosed instance) {
    this(fluent, instance, false);
  }

  public EnclosedBuilder(EnclosedFluent<?> fluent, Enclosed instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  public EnclosedBuilder(Enclosed instance) {
    this(instance, false);
  }

  public EnclosedBuilder(Enclosed instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  EnclosedFluent<?> fluent;
  Boolean validationEnabled;

  public Enclosed build() {
    Enclosed buildable = new Enclosed(fluent.buildExpresion());
    return buildable;
  }

}
