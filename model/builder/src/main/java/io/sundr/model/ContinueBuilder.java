package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ContinueBuilder extends ContinueFluent<ContinueBuilder> implements VisitableBuilder<Continue, ContinueBuilder> {
  public ContinueBuilder() {
    this(new Continue());
  }

  public ContinueBuilder(ContinueFluent<?> fluent) {
    this(fluent, new Continue());
  }

  public ContinueBuilder(ContinueFluent<?> fluent, Continue instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ContinueBuilder(Continue instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ContinueFluent<?> fluent;

  public Continue build() {
    Continue buildable = new Continue();
    return buildable;
  }

}