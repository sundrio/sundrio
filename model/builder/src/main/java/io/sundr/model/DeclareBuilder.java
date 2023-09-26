package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class DeclareBuilder extends DeclareFluent<DeclareBuilder> implements VisitableBuilder<Declare, DeclareBuilder> {
  public DeclareBuilder() {
    this.fluent = this;
  }

  public DeclareBuilder(DeclareFluent<?> fluent) {
    this.fluent = fluent;
  }

  public DeclareBuilder(DeclareFluent<?> fluent, Declare instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public DeclareBuilder(Declare instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  DeclareFluent<?> fluent;

  public Declare build() {
    Declare buildable = new Declare(fluent.buildProperties(), fluent.getValue());
    return buildable;
  }

}