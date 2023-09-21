package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class TernaryBuilder extends TernaryFluent<TernaryBuilder> implements VisitableBuilder<Ternary, TernaryBuilder> {
  public TernaryBuilder() {
    this(false);
  }

  public TernaryBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TernaryBuilder(TernaryFluent<?> fluent) {
    this(fluent, false);
  }

  public TernaryBuilder(TernaryFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TernaryBuilder(TernaryFluent<?> fluent, Ternary instance) {
    this(fluent, instance, false);
  }

  public TernaryBuilder(TernaryFluent<?> fluent, Ternary instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withCondition(instance.getCondition());
      fluent.withResult(instance.getResult());
      fluent.withAlternative(instance.getAlternative());
    }
    this.validationEnabled = validationEnabled;
  }

  public TernaryBuilder(Ternary instance) {
    this(instance, false);
  }

  public TernaryBuilder(Ternary instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withResult(instance.getResult());
      this.withAlternative(instance.getAlternative());
    }
    this.validationEnabled = validationEnabled;
  }

  TernaryFluent<?> fluent;
  Boolean validationEnabled;

  public Ternary build() {
    Ternary buildable = new Ternary(fluent.buildCondition(), fluent.buildResult(), fluent.buildAlternative());
    return buildable;
  }

}
