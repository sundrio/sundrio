package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PropertyRefBuilder extends PropertyRefFluent<PropertyRefBuilder>
    implements VisitableBuilder<PropertyRef, PropertyRefBuilder> {
  public PropertyRefBuilder() {
    this.fluent = this;
  }

  public PropertyRefBuilder(PropertyRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PropertyRefBuilder(PropertyRefFluent<?> fluent, PropertyRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public PropertyRefBuilder(PropertyRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  PropertyRefFluent<?> fluent;

  public PropertyRef build() {
    PropertyRef buildable = new PropertyRef(fluent.buildProperty(), fluent.buildScope());
    return buildable;
  }

}