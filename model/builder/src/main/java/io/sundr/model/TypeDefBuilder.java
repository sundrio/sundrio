package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class TypeDefBuilder extends TypeDefFluentImpl<TypeDefBuilder> implements VisitableBuilder<TypeDef, TypeDefBuilder> {

  TypeDefFluent<?> fluent;
  Boolean validationEnabled;

  public TypeDefBuilder() {
    this(true);
  }

  public TypeDefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent) {
    this(fluent, true);
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, TypeDef instance) {
    this(fluent, instance, true);
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, TypeDef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withKind(instance.getKind());
    fluent.withPackageName(instance.getPackageName());
    fluent.withName(instance.getName());
    fluent.withComments(instance.getComments());
    fluent.withAnnotations(instance.getAnnotations());
    fluent.withExtendsList(instance.getExtendsList());
    fluent.withImplementsList(instance.getImplementsList());
    fluent.withParameters(instance.getParameters());
    fluent.withProperties(instance.getProperties());
    fluent.withConstructors(instance.getConstructors());
    fluent.withMethods(instance.getMethods());
    fluent.withOuterTypeName(instance.getOuterTypeName());
    fluent.withInnerTypes(instance.getInnerTypes());
    fluent.withModifiers(instance.getModifiers());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeDefBuilder(TypeDef instance) {
    this(instance, true);
  }

  public TypeDefBuilder(TypeDef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withKind(instance.getKind());
    this.withPackageName(instance.getPackageName());
    this.withName(instance.getName());
    this.withComments(instance.getComments());
    this.withAnnotations(instance.getAnnotations());
    this.withExtendsList(instance.getExtendsList());
    this.withImplementsList(instance.getImplementsList());
    this.withParameters(instance.getParameters());
    this.withProperties(instance.getProperties());
    this.withConstructors(instance.getConstructors());
    this.withMethods(instance.getMethods());
    this.withOuterTypeName(instance.getOuterTypeName());
    this.withInnerTypes(instance.getInnerTypes());
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public TypeDef build() {
    TypeDef buildable = new TypeDef(fluent.getKind(), fluent.getPackageName(), fluent.getName(), fluent.getComments(),
        fluent.getAnnotations(), fluent.getExtendsList(), fluent.getImplementsList(), fluent.getParameters(),
        fluent.getProperties(), fluent.getConstructors(), fluent.getMethods(), fluent.getOuterTypeName(),
        fluent.getInnerTypes(), fluent.getModifiers(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeDefBuilder that = (TypeDefBuilder) o;
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
