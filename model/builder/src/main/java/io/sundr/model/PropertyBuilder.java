package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class PropertyBuilder extends PropertyFluent<PropertyBuilder> implements VisitableBuilder<Property, PropertyBuilder> {
  public PropertyBuilder() {
    this(false);
  }

  public PropertyBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public PropertyBuilder(PropertyFluent<?> fluent) {
    this(fluent, false);
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Property instance) {
    this(fluent, instance, false);
  }

  public PropertyBuilder(PropertyFluent<?> fluent, Property instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withAnnotations(instance.getAnnotations());
      fluent.withTypeRef(instance.getTypeRef());
      fluent.withName(instance.getName());
      fluent.withComments(instance.getComments());
      fluent.withEnumConstant(instance.isEnumConstant());
      fluent.withSynthetic(instance.isSynthetic());
      fluent.withModifiers(instance.getModifiers());
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public PropertyBuilder(Property instance) {
    this(instance, false);
  }

  public PropertyBuilder(Property instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withAnnotations(instance.getAnnotations());
      this.withTypeRef(instance.getTypeRef());
      this.withName(instance.getName());
      this.withComments(instance.getComments());
      this.withEnumConstant(instance.isEnumConstant());
      this.withSynthetic(instance.isSynthetic());
      this.withModifiers(instance.getModifiers());
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  PropertyFluent<?> fluent;
  Boolean validationEnabled;

  public Property build() {
    Property buildable = new Property(fluent.buildAnnotations(), fluent.buildTypeRef(), fluent.getName(), fluent.getComments(),
        fluent.isEnumConstant(), fluent.isSynthetic(), fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}
