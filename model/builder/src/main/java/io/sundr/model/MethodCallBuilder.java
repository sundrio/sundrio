package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class MethodCallBuilder extends MethodCallFluent<MethodCallBuilder>
    implements VisitableBuilder<MethodCall, MethodCallBuilder> {
  public MethodCallBuilder() {
    this(false);
  }

  public MethodCallBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public MethodCallBuilder(MethodCallFluent<?> fluent) {
    this(fluent, false);
  }

  public MethodCallBuilder(MethodCallFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public MethodCallBuilder(MethodCallFluent<?> fluent, MethodCall instance) {
    this(fluent, instance, false);
  }

  public MethodCallBuilder(MethodCallFluent<?> fluent, MethodCall instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withName(instance.getName());
      fluent.withScope(instance.getScope());
      fluent.withParameters(instance.getParameters());
      fluent.withArguments(instance.getArguments());
    }
    this.validationEnabled = validationEnabled;
  }

  public MethodCallBuilder(MethodCall instance) {
    this(instance, false);
  }

  public MethodCallBuilder(MethodCall instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withName(instance.getName());
      this.withScope(instance.getScope());
      this.withParameters(instance.getParameters());
      this.withArguments(instance.getArguments());
    }
    this.validationEnabled = validationEnabled;
  }

  MethodCallFluent<?> fluent;
  Boolean validationEnabled;

  public MethodCall build() {
    MethodCall buildable = new MethodCall(fluent.getName(), fluent.buildScope(), fluent.buildParameters(),
        fluent.buildArguments());
    return buildable;
  }

}
