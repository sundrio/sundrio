package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ThisBuilder extends ThisFluent<ThisBuilder> implements VisitableBuilder<This, ThisBuilder> {

  ThisFluent<?> fluent;

  public ThisBuilder() {
    this(new This());
  }

  public ThisBuilder(ThisFluent<?> fluent) {
    this(fluent, new This());
  }

  public ThisBuilder(This instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ThisBuilder(ThisFluent<?> fluent, This instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public This build() {
    This buildable = new This();
    return buildable;
  }

}