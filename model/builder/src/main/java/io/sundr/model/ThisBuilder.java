package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ThisBuilder extends ThisFluent<ThisBuilder> implements VisitableBuilder<This, ThisBuilder> {
  public ThisBuilder() {
    this(false);
  }

  public ThisBuilder(Boolean validationEnabled) {
    this(new This(), validationEnabled);
  }

  public ThisBuilder(ThisFluent<?> fluent) {
    this(fluent, false);
  }

  public ThisBuilder(ThisFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new This(), validationEnabled);
  }

  public ThisBuilder(ThisFluent<?> fluent, This instance) {
    this(fluent, instance, false);
  }

  public ThisBuilder(ThisFluent<?> fluent, This instance, Boolean validationEnabled) {
    this.fluent = fluent;
    instance = (instance != null ? instance : new This());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  public ThisBuilder(This instance) {
    this(instance, false);
  }

  public ThisBuilder(This instance, Boolean validationEnabled) {
    this.fluent = this;
    instance = (instance != null ? instance : new This());

    if (instance != null) {
    }
    this.validationEnabled = validationEnabled;
  }

  ThisFluent<?> fluent;
  Boolean validationEnabled;

  public This build() {
    This buildable = new This();
    return buildable;
  }

}
