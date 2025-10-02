package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class WildcardRefBuilder extends WildcardRefFluent<WildcardRefBuilder>
    implements VisitableBuilder<WildcardRef, WildcardRefBuilder> {

  WildcardRefFluent<?> fluent;

  public WildcardRefBuilder() {
    this(new WildcardRef());
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent) {
    this(fluent, new WildcardRef());
  }

  public WildcardRefBuilder(WildcardRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent, WildcardRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public WildcardRef build() {
    WildcardRef buildable = new WildcardRef(fluent.getBoundKind(), fluent.buildBounds(), fluent.getAttributes());
    return buildable;
  }

}