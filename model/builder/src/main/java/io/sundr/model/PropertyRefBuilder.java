package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PropertyRefBuilder extends PropertyRefFluent<PropertyRefBuilder>
    implements VisitableBuilder<PropertyRef, PropertyRefBuilder> {
  public PropertyRefBuilder() {
    this(false);
  }

  public PropertyRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PropertyRefBuilder(PropertyRefFluent<?> fluent) {
    this(fluent, false);
  }

  public PropertyRefBuilder(PropertyRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PropertyRefBuilder(PropertyRefFluent<?> fluent, PropertyRef instance) {
    this(fluent, instance, false);
  }

  public PropertyRefBuilder(PropertyRefFluent<?> fluent, PropertyRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withProperty(instance.getProperty());
    }
    this.validationEnabled = validationEnabled;
  }

  public PropertyRefBuilder(PropertyRef instance) {
    this(instance, false);
  }

  public PropertyRefBuilder(PropertyRef instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withProperty(instance.getProperty());
    }
    this.validationEnabled = validationEnabled;
  }

  PropertyRefFluent<?> fluent;
  Boolean validationEnabled;

  public PropertyRef build() {
    PropertyRef buildable = new PropertyRef(fluent.buildProperty());
    return buildable;
  }

}
