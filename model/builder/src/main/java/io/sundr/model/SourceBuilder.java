package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class SourceBuilder extends SourceFluent<SourceBuilder> implements VisitableBuilder<Source, SourceBuilder> {

  SourceFluent<?> fluent;

  public SourceBuilder() {
    this(new Source());
  }

  public SourceBuilder(SourceFluent<?> fluent) {
    this(fluent, new Source());
  }

  public SourceBuilder(Source instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public SourceBuilder(SourceFluent<?> fluent, Source instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Source build() {
    Source buildable = new Source(fluent.buildTypes());
    return buildable;
  }

}