package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class TryBuilder extends TryFluent<TryBuilder> implements VisitableBuilder<Try, TryBuilder> {

  TryFluent<?> fluent;

  public TryBuilder() {
    this.fluent = this;
  }

  public TryBuilder(TryFluent<?> fluent) {
    this.fluent = fluent;
  }

  public TryBuilder(Try instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public TryBuilder(TryFluent<?> fluent, Try instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Try build() {
    Try buildable = new Try(fluent.buildTryBlock(), fluent.getCatchBlocks(), fluent.buildFinallyBlock());
    return buildable;
  }

}
