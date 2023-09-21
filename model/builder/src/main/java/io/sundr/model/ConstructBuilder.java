package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ConstructBuilder extends ConstructFluent<ConstructBuilder>
    implements VisitableBuilder<Construct, ConstructBuilder> {
  public ConstructBuilder() {
    this(false);
  }

  public ConstructBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ConstructBuilder(ConstructFluent<?> fluent) {
    this(fluent, false);
  }

  public ConstructBuilder(ConstructFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ConstructBuilder(ConstructFluent<?> fluent, Construct instance) {
    this(fluent, instance, false);
  }

  public ConstructBuilder(ConstructFluent<?> fluent, Construct instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withType(instance.getType());
      fluent.withParameters(instance.getParameters());
    }
    this.validationEnabled = validationEnabled;
  }

  public ConstructBuilder(Construct instance) {
    this(instance, false);
  }

  public ConstructBuilder(Construct instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withType(instance.getType());
      this.withParameters(instance.getParameters());
    }
    this.validationEnabled = validationEnabled;
  }

  ConstructFluent<?> fluent;
  Boolean validationEnabled;

  public Construct build() {
    Construct buildable = new Construct(fluent.buildType(), fluent.buildParameters(), fluent.buildArguments());
    return buildable;
  }

}
