package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class IndexBuilder extends IndexFluent<IndexBuilder> implements VisitableBuilder<Index, IndexBuilder> {
  public IndexBuilder() {
    this.fluent = this;
  }

  public IndexBuilder(IndexFluent<?> fluent) {
    this.fluent = fluent;
  }

  public IndexBuilder(IndexFluent<?> fluent, Index instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public IndexBuilder(Index instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  IndexFluent<?> fluent;

  public Index build() {
    Index buildable = new Index(fluent.buildScope(), fluent.buildExpression());
    return buildable;
  }

}