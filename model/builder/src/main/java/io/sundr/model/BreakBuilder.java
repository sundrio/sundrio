package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class BreakBuilder extends BreakFluent<BreakBuilder> implements VisitableBuilder<Break, BreakBuilder> {
  public BreakBuilder() {
    this(new Break());
  }

  public BreakBuilder(BreakFluent<?> fluent) {
    this(fluent, new Break());
  }

  public BreakBuilder(BreakFluent<?> fluent, Break instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public BreakBuilder(Break instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  BreakFluent<?> fluent;

  public Break build() {
    Break buildable = new Break();
    return buildable;
  }

}