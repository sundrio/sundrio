package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class TypeParamDefBuilder extends TypeParamDefFluentImpl<TypeParamDefBuilder>
    implements VisitableBuilder<io.sundr.model.TypeParamDef, TypeParamDefBuilder> {
  public TypeParamDefBuilder() {
    this(false);
  }

  public TypeParamDefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent) {
    this(fluent, false);
  }

  public TypeParamDefBuilder(io.sundr.model.TypeParamDefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(io.sundr.model.TypeParamDefFluent<?> fluent, io.sundr.model.TypeParamDef instance) {
    this(fluent, instance, false);
  }

  public TypeParamDefBuilder(io.sundr.model.TypeParamDefFluent<?> fluent, io.sundr.model.TypeParamDef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withBounds(instance.getBounds());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(io.sundr.model.TypeParamDef instance) {
    this(instance, false);
  }

  public TypeParamDefBuilder(io.sundr.model.TypeParamDef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.TypeParamDefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.TypeParamDef build() {
    TypeParamDef buildable = new TypeParamDef(fluent.getName(), fluent.getBounds(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeParamDefBuilder that = (TypeParamDefBuilder) o;
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
