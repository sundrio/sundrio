package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class DoBuilder extends DoFluent<DoBuilder> implements VisitableBuilder<Do, DoBuilder> {

  DoFluent<?> fluent;

  public DoBuilder() {
    this.fluent = this;
  }

  public DoBuilder(DoFluent<?> fluent) {
    this.fluent = fluent;
  }

  public DoBuilder(Do instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public DoBuilder(DoFluent<?> fluent, Do instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Do build() {
    Do buildable = new Do(fluent.buildCondition(), fluent.buildStatement());
    return buildable;
  }

}
