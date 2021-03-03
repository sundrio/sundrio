package io.sundr.codegen.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class PropertyBuilder extends PropertyFluentImpl<PropertyBuilder>
    implements VisitableBuilder<Property, PropertyBuilder> {

  PropertyFluent<?> fluent;
  Boolean validationEnabled;

  public PropertyBuilder() {
    this(true);
  }

  public PropertyBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PropertyBuilder(PropertyFluent<?> fluent) {
    this(fluent, true);
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Property instance) {
    this(fluent, instance, true);
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Property instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withAnnotations(instance.getAnnotations());
    fluent.withTypeRef(instance.getTypeRef());
    fluent.withName(instance.getName());
    fluent.withComments(instance.getComments());
    fluent.withModifiers(instance.getModifiers());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public PropertyBuilder(Property instance) {
    this(instance, true);
  }

  public PropertyBuilder(Property instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withAnnotations(instance.getAnnotations());
    this.withTypeRef(instance.getTypeRef());
    this.withName(instance.getName());
    this.withComments(instance.getComments());
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public EditableProperty build() {
    EditableProperty buildable = new EditableProperty(fluent.getAnnotations(), fluent.getTypeRef(), fluent.getName(),
        fluent.getComments(), fluent.getModifiers(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PropertyBuilder that = (PropertyBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

}
