package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ConstructBuilder extends ConstructFluent<ConstructBuilder>
    implements VisitableBuilder<Construct, ConstructBuilder> {
  public ConstructBuilder() {
    this.fluent = this;
  }

  public ConstructBuilder(ConstructFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ConstructBuilder(ConstructFluent<?> fluent, Construct instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ConstructBuilder(Construct instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ConstructFluent<?> fluent;

  public Construct build() {
    Construct buildable = new Construct(fluent.buildType(), fluent.buildParameters(), fluent.buildArguments());
    return buildable;
  }

}