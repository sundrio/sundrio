package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class BitwiseOrBuilder extends BitwiseOrFluent<BitwiseOrBuilder>
    implements VisitableBuilder<BitwiseOr, BitwiseOrBuilder> {
  public BitwiseOrBuilder() {
    this.fluent = this;
  }

  public BitwiseOrBuilder(BitwiseOrFluent<?> fluent) {
    this.fluent = fluent;
  }

  public BitwiseOrBuilder(BitwiseOrFluent<?> fluent, BitwiseOr instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public BitwiseOrBuilder(BitwiseOr instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  BitwiseOrFluent<?> fluent;

  public BitwiseOr build() {
    BitwiseOr buildable = new BitwiseOr(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}