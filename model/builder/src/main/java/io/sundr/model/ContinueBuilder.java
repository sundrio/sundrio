package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ContinueBuilder extends ContinueFluent<ContinueBuilder> implements VisitableBuilder<Continue, ContinueBuilder> {
  public ContinueBuilder() {
    this(false);
  }

  public ContinueBuilder(Boolean validationEnabled) {
    this(new Continue(), validationEnabled);
  }

  public ContinueBuilder(ContinueFluent<?> fluent) {
    this(fluent, false);
  }

  public ContinueBuilder(ContinueFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Continue(), validationEnabled);
  }

  public ContinueBuilder(ContinueFluent<?> fluent, Continue instance) {
    this(fluent, instance, false);
  }

  public ContinueBuilder(ContinueFluent<?> fluent, Continue instance, Boolean validationEnabled) {
    this.fluent = fluent;
    instance = (instance != null ? instance : new Continue());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  public ContinueBuilder(Continue instance) {
    this(instance, false);
  }

  public ContinueBuilder(Continue instance, Boolean validationEnabled) {
    this.fluent = this;
    instance = (instance != null ? instance : new Continue());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  ContinueFluent<?> fluent;
  Boolean validationEnabled;

  public Continue build() {
    Continue buildable = new Continue();
    return buildable;
  }

}
