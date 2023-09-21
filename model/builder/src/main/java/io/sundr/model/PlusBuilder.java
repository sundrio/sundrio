package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PlusBuilder extends PlusFluent<PlusBuilder> implements VisitableBuilder<Plus, PlusBuilder> {
  public PlusBuilder() {
    this(false);
  }

  public PlusBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PlusBuilder(PlusFluent<?> fluent) {
    this(fluent, false);
  }

  public PlusBuilder(PlusFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PlusBuilder(PlusFluent<?> fluent, Plus instance) {
    this(fluent, instance, false);
  }

  public PlusBuilder(PlusFluent<?> fluent, Plus instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public PlusBuilder(Plus instance) {
    this(instance, false);
  }

  public PlusBuilder(Plus instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  PlusFluent<?> fluent;
  Boolean validationEnabled;

  public Plus build() {
    Plus buildable = new Plus(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
