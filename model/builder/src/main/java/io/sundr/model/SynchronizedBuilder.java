package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class SynchronizedBuilder extends SynchronizedFluent<SynchronizedBuilder>
    implements VisitableBuilder<Synchronized, SynchronizedBuilder> {

  SynchronizedFluent<?> fluent;

  public SynchronizedBuilder() {
    this.fluent = this;
  }

  public SynchronizedBuilder(SynchronizedFluent<?> fluent) {
    this.fluent = fluent;
  }

  public SynchronizedBuilder(Synchronized instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public SynchronizedBuilder(SynchronizedFluent<?> fluent, Synchronized instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Synchronized build() {
    Synchronized buildable = new Synchronized(fluent.buildLockExpression(), fluent.buildBody());
    return buildable;
  }

}
