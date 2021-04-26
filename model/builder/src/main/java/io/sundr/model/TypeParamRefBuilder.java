package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class TypeParamRefBuilder extends TypeParamRefFluentImpl<TypeParamRefBuilder>
    implements VisitableBuilder<TypeParamRef, TypeParamRefBuilder> {

  TypeParamRefFluent<?> fluent;
  Boolean validationEnabled;

  public TypeParamRefBuilder() {
    this(true);
  }

  public TypeParamRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent) {
    this(fluent, true);
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, TypeParamRef instance) {
    this(fluent, instance, true);
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, TypeParamRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRefBuilder(TypeParamRef instance) {
    this(instance, true);
  }

  public TypeParamRefBuilder(TypeParamRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeParamRef build() {
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
