package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class TypeDefBuilder extends TypeDefFluent<TypeDefBuilder> implements VisitableBuilder<TypeDef, TypeDefBuilder> {

  TypeDefFluent<?> fluent;

  public TypeDefBuilder() {
    this.fluent = this;
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public TypeDefBuilder(TypeDef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public TypeDefBuilder(TypeDefFluent<?> fluent, TypeDef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public TypeDef build() {
    TypeDef buildable = new TypeDef(fluent.getKind(), fluent.getPackageName(), fluent.getName(), fluent.getComments(),
        fluent.buildAnnotations(), fluent.buildExtendsList(), fluent.buildImplementsList(), fluent.buildParameters(),
        fluent.buildProperties(), fluent.buildConstructors(), fluent.buildMethods(), fluent.getOuterTypeName(),
        fluent.buildInnerTypes(), fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}