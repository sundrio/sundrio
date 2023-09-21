package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class DeclareBuilder extends DeclareFluent<DeclareBuilder> implements VisitableBuilder<Declare, DeclareBuilder> {
  public DeclareBuilder() {
    this(false);
  }

  public DeclareBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public DeclareBuilder(DeclareFluent<?> fluent) {
    this(fluent, false);
  }

  public DeclareBuilder(DeclareFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public DeclareBuilder(DeclareFluent<?> fluent, Declare instance) {
    this(fluent, instance, false);
  }

  public DeclareBuilder(DeclareFluent<?> fluent, Declare instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withProperties(instance.getProperties());
      fluent.withValue(instance.getValue());
    }
    this.validationEnabled = validationEnabled;
  }

  public DeclareBuilder(Declare instance) {
    this(instance, false);
  }

  public DeclareBuilder(Declare instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withProperties(instance.getProperties());
      this.withValue(instance.getValue());
    }
    this.validationEnabled = validationEnabled;
  }

  DeclareFluent<?> fluent;
  Boolean validationEnabled;

  public Declare build() {
    Declare buildable = new Declare(fluent.buildProperties(), fluent.getValue());
    return buildable;
  }

}
