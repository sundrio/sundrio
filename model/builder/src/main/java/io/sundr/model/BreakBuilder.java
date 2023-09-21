package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class BreakBuilder extends BreakFluent<BreakBuilder> implements VisitableBuilder<Break, BreakBuilder> {
  public BreakBuilder() {
    this(false);
  }

  public BreakBuilder(Boolean validationEnabled) {
    this(new Break(), validationEnabled);
  }

  public BreakBuilder(BreakFluent<?> fluent) {
    this(fluent, false);
  }

  public BreakBuilder(BreakFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Break(), validationEnabled);
  }

  public BreakBuilder(BreakFluent<?> fluent, Break instance) {
    this(fluent, instance, false);
  }

  public BreakBuilder(BreakFluent<?> fluent, Break instance, Boolean validationEnabled) {
    this.fluent = fluent;
    instance = (instance != null ? instance : new Break());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  public BreakBuilder(Break instance) {
    this(instance, false);
  }

  public BreakBuilder(Break instance, Boolean validationEnabled) {
    this.fluent = this;
    instance = (instance != null ? instance : new Break());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  BreakFluent<?> fluent;
  Boolean validationEnabled;

  public Break build() {
    Break buildable = new Break();
    return buildable;
  }

}
