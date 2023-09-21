package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class VoidRefBuilder extends VoidRefFluent<VoidRefBuilder> implements VisitableBuilder<VoidRef, VoidRefBuilder> {
  public VoidRefBuilder() {
    this(false);
  }

  public VoidRefBuilder(Boolean validationEnabled) {
    this(new VoidRef(), validationEnabled);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent) {
    this(fluent, false);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new VoidRef(), validationEnabled);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance) {
    this(fluent, instance, false);
  }

  public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    instance = (instance != null ? instance : new VoidRef());

    if (instance != null) {
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public VoidRefBuilder(VoidRef instance) {
    this(instance, false);
  }

  public VoidRefBuilder(VoidRef instance, Boolean validationEnabled) {
    this.fluent = this;
    instance = (instance != null ? instance : new VoidRef());

    if (instance != null) {
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  VoidRefFluent<?> fluent;
  Boolean validationEnabled;

  public VoidRef build() {
    VoidRef buildable = new VoidRef(fluent.getAttributes());
    return buildable;
  }

}
