package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class WildcardRefBuilder extends WildcardRefFluent<WildcardRefBuilder>
    implements VisitableBuilder<WildcardRef, WildcardRefBuilder> {
  public WildcardRefBuilder() {
    this(new WildcardRef());
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent) {
    this(fluent, new WildcardRef());
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent, WildcardRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public WildcardRefBuilder(WildcardRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  WildcardRefFluent<?> fluent;

  public WildcardRef build() {
    WildcardRef buildable = new WildcardRef(fluent.getBoundKind(), fluent.buildBounds(), fluent.getAttributes());
    return buildable;
  }

}