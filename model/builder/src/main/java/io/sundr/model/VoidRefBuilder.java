package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class VoidRefBuilder extends VoidRefFluent<VoidRefBuilder> implements VisitableBuilder<VoidRef, VoidRefBuilder> {
  public VoidRefBuilder() {
    this(new VoidRef());
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent) {
    this(fluent, new VoidRef());
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public VoidRefBuilder(VoidRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  VoidRefFluent<?> fluent;

  public VoidRef build() {
    VoidRef buildable = new VoidRef(fluent.getAttributes());
    return buildable;
  }

}