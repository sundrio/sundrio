package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class TypeParamDefBuilder extends TypeParamDefFluent<TypeParamDefBuilder>
    implements VisitableBuilder<TypeParamDef, TypeParamDefBuilder> {
  public TypeParamDefBuilder() {
    this.fluent = this;
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public TypeParamDefBuilder(TypeParamDefFluent<?> fluent, TypeParamDef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public TypeParamDefBuilder(TypeParamDef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  TypeParamDefFluent<?> fluent;

  public TypeParamDef build() {
    TypeParamDef buildable = new TypeParamDef(fluent.getName(), fluent.buildBounds(), fluent.getAttributes());
    return buildable;
  }

}