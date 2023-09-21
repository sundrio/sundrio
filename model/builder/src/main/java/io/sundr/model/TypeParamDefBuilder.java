package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class TypeParamDefBuilder extends TypeParamDefFluent<TypeParamDefBuilder>
    implements VisitableBuilder<TypeParamDef, TypeParamDefBuilder> {
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

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, TypeParamDef instance) {
    this(fluent, instance, false);
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, TypeParamDef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withName(instance.getName());
      fluent.withBounds(instance.getBounds());
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public TypeParamDefBuilder(TypeParamDef instance) {
    this(instance, false);
  }

  public TypeParamDefBuilder(TypeParamDef instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withName(instance.getName());
      this.withBounds(instance.getBounds());
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  TypeParamDefFluent<?> fluent;
  Boolean validationEnabled;

  public TypeParamDef build() {
    TypeParamDef buildable = new TypeParamDef(fluent.getName(), fluent.buildBounds(), fluent.getAttributes());
    return buildable;
  }

}
