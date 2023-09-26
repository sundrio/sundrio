package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ForBuilder extends ForFluent<ForBuilder> implements VisitableBuilder<For, ForBuilder> {
  public ForBuilder() {
    this.fluent = this;
  }

  public ForBuilder(ForFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ForBuilder(ForFluent<?> fluent, For instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ForBuilder(For instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ForFluent<?> fluent;

  public For build() {
    For buildable = new For(fluent.buildInit(), fluent.buildCompare(), fluent.buildUpdate(), fluent.buildBody());
    return buildable;
  }

}