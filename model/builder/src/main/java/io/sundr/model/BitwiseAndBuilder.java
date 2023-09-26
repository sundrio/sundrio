package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class BitwiseAndBuilder extends BitwiseAndFluent<BitwiseAndBuilder>
    implements VisitableBuilder<BitwiseAnd, BitwiseAndBuilder> {
  public BitwiseAndBuilder() {
    this.fluent = this;
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent) {
    this.fluent = fluent;
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent, BitwiseAnd instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public BitwiseAndBuilder(BitwiseAnd instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  BitwiseAndFluent<?> fluent;

  public BitwiseAnd build() {
    BitwiseAnd buildable = new BitwiseAnd(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}