package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class TypeParamRefBuilder extends TypeParamRefFluent<TypeParamRefBuilder>
    implements VisitableBuilder<TypeParamRef, TypeParamRefBuilder> {
  public TypeParamRefBuilder() {
    this(false);
  }

  public TypeParamRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent) {
    this(fluent, false);
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, TypeParamRef instance) {
    this(fluent, instance, false);
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, TypeParamRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withName(instance.getName());
      fluent.withDimensions(instance.getDimensions());
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(TypeParamRef instance) {
    this(instance, false);
  }

  public TypeParamRefBuilder(TypeParamRef instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withName(instance.getName());
      this.withDimensions(instance.getDimensions());
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  TypeParamRefFluent<?> fluent;
  Boolean validationEnabled;

  public TypeParamRef build() {
    TypeParamRef buildable = new TypeParamRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

}
