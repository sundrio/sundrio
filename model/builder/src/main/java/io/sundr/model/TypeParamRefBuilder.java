package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class TypeParamRefBuilder extends TypeParamRefFluentImpl<TypeParamRefBuilder>
    implements VisitableBuilder<io.sundr.model.TypeParamRef, io.sundr.model.TypeParamRefBuilder> {
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

  public TypeParamRefBuilder(io.sundr.model.TypeParamRefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(io.sundr.model.TypeParamRefFluent<?> fluent, io.sundr.model.TypeParamRef instance) {
    this(fluent, instance, false);
  }

  public TypeParamRefBuilder(io.sundr.model.TypeParamRefFluent<?> fluent, io.sundr.model.TypeParamRef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(io.sundr.model.TypeParamRef instance) {
    this(instance, false);
  }

  public TypeParamRefBuilder(io.sundr.model.TypeParamRef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.TypeParamRefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.TypeParamRef build() {
    TypeParamRef buildable = new TypeParamRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeParamRefBuilder that = (TypeParamRefBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(fluent, validationEnabled, super.hashCode());
  }

}
