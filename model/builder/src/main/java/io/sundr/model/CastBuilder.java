package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class CastBuilder extends CastFluent<CastBuilder> implements VisitableBuilder<Cast, CastBuilder> {
  public CastBuilder() {
    this.fluent = this;
  }

  public CastBuilder(CastFluent<?> fluent) {
    this.fluent = fluent;
  }

  public CastBuilder(CastFluent<?> fluent, Cast instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public CastBuilder(Cast instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  CastFluent<?> fluent;

  public Cast build() {
    Cast buildable = new Cast(fluent.buildType(), fluent.buildExpression());
    return buildable;
  }

}