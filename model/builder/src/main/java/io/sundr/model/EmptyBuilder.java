package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class EmptyBuilder extends EmptyFluent<EmptyBuilder> implements VisitableBuilder<Empty, EmptyBuilder> {

  EmptyFluent<?> fluent;

  public EmptyBuilder() {
    this(new Empty());
  }

  public EmptyBuilder(EmptyFluent<?> fluent) {
    this(fluent, new Empty());
  }

  public EmptyBuilder(Empty instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public EmptyBuilder(EmptyFluent<?> fluent, Empty instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Empty build() {
    Empty buildable = new Empty();
    return buildable;
  }

}
