package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class NegativeBuilder extends NegativeFluent<NegativeBuilder> implements VisitableBuilder<Negative, NegativeBuilder> {
  public NegativeBuilder() {
    this(false);
  }

  public NegativeBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public NegativeBuilder(NegativeFluent<?> fluent) {
    this(fluent, false);
  }

  public NegativeBuilder(NegativeFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public NegativeBuilder(NegativeFluent<?> fluent, Negative instance) {
    this(fluent, instance, false);
  }

  public NegativeBuilder(NegativeFluent<?> fluent, Negative instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  public NegativeBuilder(Negative instance) {
    this(instance, false);
  }

  public NegativeBuilder(Negative instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
    }
    this.validationEnabled = validationEnabled;
  }

  NegativeFluent<?> fluent;
  Boolean validationEnabled;

  public Negative build() {
    Negative buildable = new Negative(fluent.buildExpresion());
    return buildable;
  }

}
