package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class BitwiseAndBuilder extends BitwiseAndFluent<BitwiseAndBuilder>
    implements VisitableBuilder<BitwiseAnd, BitwiseAndBuilder> {

  BitwiseAndFluent<?> fluent;

  public BitwiseAndBuilder() {
    this.fluent = this;
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent) {
    this.fluent = fluent;
  }

  public BitwiseAndBuilder(BitwiseAnd instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent, BitwiseAnd instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public BitwiseAnd build() {
    BitwiseAnd buildable = new BitwiseAnd(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
