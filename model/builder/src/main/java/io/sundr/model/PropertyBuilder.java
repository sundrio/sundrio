package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class PropertyBuilder extends PropertyFluent<PropertyBuilder> implements VisitableBuilder<Property, PropertyBuilder> {

  PropertyFluent<?> fluent;

  public PropertyBuilder() {
    this.fluent = this;
  }

  public PropertyBuilder(PropertyFluent<?> fluent) {
    this.fluent = fluent;
  }

  public PropertyBuilder(Property instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Property instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Property build() {
    Property buildable = new Property(fluent.buildModifiers(), fluent.getAttributes(), fluent.getComments(),
        fluent.buildAnnotations(), fluent.buildTypeRef(), fluent.getName(), fluent.buildInitialValue(), fluent.isEnumConstant(),
        fluent.isSynthetic());
    return buildable;
  }

}
