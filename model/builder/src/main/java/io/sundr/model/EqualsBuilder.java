package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class EqualsBuilder extends EqualsFluent<EqualsBuilder> implements VisitableBuilder<Equals, EqualsBuilder> {
  public EqualsBuilder() {
    this(false);
  }

  public EqualsBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public EqualsBuilder(EqualsFluent<?> fluent) {
    this(fluent, false);
  }

  public EqualsBuilder(EqualsFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public EqualsBuilder(EqualsFluent<?> fluent, Equals instance) {
    this(fluent, instance, false);
  }

  public EqualsBuilder(EqualsFluent<?> fluent, Equals instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public EqualsBuilder(Equals instance) {
    this(instance, false);
  }

  public EqualsBuilder(Equals instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  EqualsFluent<?> fluent;
  Boolean validationEnabled;

  public Equals build() {
    Equals buildable = new Equals(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
