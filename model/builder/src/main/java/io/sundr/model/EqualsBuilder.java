package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class EqualsBuilder extends EqualsFluent<EqualsBuilder> implements VisitableBuilder<Equals, EqualsBuilder> {
  public EqualsBuilder() {
    this.fluent = this;
  }

  public EqualsBuilder(EqualsFluent<?> fluent) {
    this.fluent = fluent;
  }

  public EqualsBuilder(EqualsFluent<?> fluent, Equals instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public EqualsBuilder(Equals instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  EqualsFluent<?> fluent;

  public Equals build() {
    Equals buildable = new Equals(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}