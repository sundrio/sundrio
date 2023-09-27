package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class NewArrayBuilder extends NewArrayFluent<NewArrayBuilder> implements VisitableBuilder<NewArray, NewArrayBuilder> {
  public NewArrayBuilder() {
    this.fluent = this;
  }

  public NewArrayBuilder(NewArrayFluent<?> fluent) {
    this.fluent = fluent;
  }

  public NewArrayBuilder(NewArrayFluent<?> fluent, NewArray instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public NewArrayBuilder(NewArray instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  NewArrayFluent<?> fluent;

  public NewArray build() {
    NewArray buildable = new NewArray(fluent.buildType(), fluent.buildExpressions());
    return buildable;
  }

}