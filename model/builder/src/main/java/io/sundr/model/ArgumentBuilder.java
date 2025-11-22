package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ArgumentBuilder extends ArgumentFluent<ArgumentBuilder> implements VisitableBuilder<Argument, ArgumentBuilder> {

  ArgumentFluent<?> fluent;

  public ArgumentBuilder() {
    this.fluent = this;
  }

  public ArgumentBuilder(ArgumentFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ArgumentBuilder(Argument instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ArgumentBuilder(ArgumentFluent<?> fluent, Argument instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Argument build() {
    Argument buildable = new Argument(fluent.getComments(), fluent.buildAnnotations(), fluent.buildTypeRef(), fluent.getName(),
        fluent.isIsFinal(), fluent.getAttributes());
    return buildable;
  }

}
