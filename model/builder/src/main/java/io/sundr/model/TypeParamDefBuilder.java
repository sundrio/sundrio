package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class TypeParamDefBuilder extends TypeParamDefFluentImpl<TypeParamDefBuilder>
    implements VisitableBuilder<TypeParamDef, TypeParamDefBuilder> {

  TypeParamDefFluent<?> fluent;
  Boolean validationEnabled;

  public TypeParamDefBuilder() {
    this(true);
  }

  public TypeParamDefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent) {
    this(fluent, true);
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, TypeParamDef instance) {
    this(fluent, instance, true);
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, TypeParamDef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withName(instance.getName());
    fluent.withBounds(instance.getBounds());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(TypeParamDef instance) {
    this(instance, true);
  }

  public TypeParamDefBuilder(TypeParamDef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withName(instance.getName());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDef build() {
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
