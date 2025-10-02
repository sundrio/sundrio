package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class DotClassBuilder extends DotClassFluent<DotClassBuilder> implements VisitableBuilder<DotClass, DotClassBuilder> {

  DotClassFluent<?> fluent;

  public DotClassBuilder() {
    this.fluent = this;
  }

  public DotClassBuilder(DotClassFluent<?> fluent) {
    this.fluent = fluent;
  }

  public DotClassBuilder(DotClass instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public DotClassBuilder(DotClassFluent<?> fluent, DotClass instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public DotClass build() {
    DotClass buildable = new DotClass(fluent.buildClassRef());
    return buildable;
  }

}