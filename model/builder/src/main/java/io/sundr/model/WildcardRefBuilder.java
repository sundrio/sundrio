package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class WildcardRefBuilder extends WildcardRefFluentImpl<WildcardRefBuilder>
    implements VisitableBuilder<WildcardRef, WildcardRefBuilder> {
  public WildcardRefBuilder() {
    this(false);
  }

  public WildcardRefBuilder(Boolean validationEnabled) {
    this(new WildcardRef(), validationEnabled);
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent) {
    this(fluent, false);
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new WildcardRef(), validationEnabled);
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent, WildcardRef instance) {
    this(fluent, instance, false);
  }

  public WildcardRefBuilder(WildcardRefFluent<?> fluent, WildcardRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withBoundKind(instance.getBoundKind());
    fluent.withBounds(instance.getBounds());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public WildcardRefBuilder(WildcardRef instance) {
    this(instance, false);
  }

  public WildcardRefBuilder(WildcardRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withBoundKind(instance.getBoundKind());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  WildcardRefFluent<?> fluent;
  Boolean validationEnabled;

  public WildcardRef build() {
    WildcardRef buildable = new WildcardRef(fluent.getBoundKind(), fluent.getBounds(), fluent.getAttributes());
    return buildable;
  }

}
