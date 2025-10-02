package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class SuperBuilder extends SuperFluent<SuperBuilder> implements VisitableBuilder<Super, SuperBuilder> {

  SuperFluent<?> fluent;

  public SuperBuilder() {
    this(new Super());
  }

  public SuperBuilder(SuperFluent<?> fluent) {
    this(fluent, new Super());
  }

  public SuperBuilder(Super instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public SuperBuilder(SuperFluent<?> fluent, Super instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Super build() {
    Super buildable = new Super();
    return buildable;
  }

}