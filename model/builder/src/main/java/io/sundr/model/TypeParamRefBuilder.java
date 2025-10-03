package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class TypeParamRefBuilder extends TypeParamRefFluent<TypeParamRefBuilder>
    implements VisitableBuilder<TypeParamRef, TypeParamRefBuilder> {

  TypeParamRefFluent<?> fluent;

  public TypeParamRefBuilder() {
    this.fluent = this;
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public TypeParamRefBuilder(TypeParamRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, TypeParamRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public TypeParamRef build() {
    TypeParamRef buildable = new TypeParamRef(fluent.getName(), fluent.getDimensions(), fluent.getAttributes());
    return buildable;
  }

}
