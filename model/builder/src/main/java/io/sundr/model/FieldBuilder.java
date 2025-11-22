package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class FieldBuilder extends FieldFluent<FieldBuilder> implements VisitableBuilder<Field, FieldBuilder> {

  FieldFluent<?> fluent;

  public FieldBuilder() {
    this.fluent = this;
  }

  public FieldBuilder(FieldFluent<?> fluent) {
    this.fluent = fluent;
  }

  public FieldBuilder(Field instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public FieldBuilder(FieldFluent<?> fluent, Field instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Field build() {
    Field buildable = new Field(fluent.buildModifiers(), fluent.getAttributes(), fluent.getComments(),
        fluent.buildAnnotations(), fluent.buildTypeRef(), fluent.getName(), fluent.buildInitialValue(), fluent.isEnumConstant(),
        fluent.isSynthetic());
    return buildable;
  }

}
