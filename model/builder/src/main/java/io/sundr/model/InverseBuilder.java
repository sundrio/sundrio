package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class InverseBuilder extends InverseFluent<InverseBuilder> implements VisitableBuilder<Inverse, InverseBuilder> {
  public InverseBuilder() {
    this(false);
  }

  public InverseBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public InverseBuilder(InverseFluent<?> fluent) {
    this(fluent, false);
  }

  public InverseBuilder(InverseFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public InverseBuilder(InverseFluent<?> fluent, Inverse instance) {
    this(fluent, instance, false);
  }

  public InverseBuilder(InverseFluent<?> fluent, Inverse instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  public InverseBuilder(Inverse instance) {
    this(instance, false);
  }

  public InverseBuilder(Inverse instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  InverseFluent<?> fluent;
  Boolean validationEnabled;

  public Inverse build() {
    Inverse buildable = new Inverse(fluent.buildExpresion());
    return buildable;
  }

}
