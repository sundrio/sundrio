package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class SourceBuilder extends SourceFluentImpl<SourceBuilder> implements VisitableBuilder<Source, SourceBuilder> {
  public SourceBuilder() {
    this(false);
  }

  public SourceBuilder(Boolean validationEnabled) {
    this(new Source(), validationEnabled);
  }

  public SourceBuilder(SourceFluent<?> fluent) {
    this(fluent, false);
  }

  public SourceBuilder(SourceFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Source(), validationEnabled);
  }

  public SourceBuilder(SourceFluent<?> fluent, Source instance) {
    this(fluent, instance, false);
  }

  public SourceBuilder(SourceFluent<?> fluent, Source instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withTypes(instance.getTypes());
    this.validationEnabled = validationEnabled;
  }

  public SourceBuilder(Source instance) {
    this(instance, false);
  }

  public SourceBuilder(Source instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withTypes(instance.getTypes());
    this.validationEnabled = validationEnabled;
  }

  SourceFluent<?> fluent;
  Boolean validationEnabled;

  public Source build() {
    Source buildable = new Source(fluent.getTypes());
    return buildable;
  }

}
