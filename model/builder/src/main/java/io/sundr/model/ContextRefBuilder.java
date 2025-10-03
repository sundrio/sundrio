package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ContextRefBuilder extends ContextRefFluent<ContextRefBuilder>
    implements VisitableBuilder<ContextRef, ContextRefBuilder> {

  ContextRefFluent<?> fluent;

  public ContextRefBuilder() {
    this.fluent = this;
  }

  public ContextRefBuilder(ContextRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ContextRefBuilder(ContextRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ContextRefBuilder(ContextRefFluent<?> fluent, ContextRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ContextRef build() {
    ContextRef buildable = new ContextRef(fluent.getName());
    return buildable;
  }

}
