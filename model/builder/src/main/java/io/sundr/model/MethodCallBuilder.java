package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class MethodCallBuilder extends MethodCallFluent<MethodCallBuilder>
    implements VisitableBuilder<MethodCall, MethodCallBuilder> {
  public MethodCallBuilder() {
    this.fluent = this;
  }

  public MethodCallBuilder(MethodCallFluent<?> fluent) {
    this.fluent = fluent;
  }

  public MethodCallBuilder(MethodCallFluent<?> fluent, MethodCall instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public MethodCallBuilder(MethodCall instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  MethodCallFluent<?> fluent;

  public MethodCall build() {
    MethodCall buildable = new MethodCall(fluent.getName(), fluent.buildScope(), fluent.buildParameters(),
        fluent.buildArguments());
    return buildable;
  }

}