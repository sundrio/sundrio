package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class MethodBuilder extends MethodFluent<MethodBuilder> implements VisitableBuilder<Method, MethodBuilder> {
  public MethodBuilder() {
    this.fluent = this;
  }

  public MethodBuilder(MethodFluent<?> fluent) {
    this.fluent = fluent;
  }

  public MethodBuilder(MethodFluent<?> fluent, Method instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public MethodBuilder(Method instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  MethodFluent<?> fluent;

  public Method build() {
    Method buildable = new Method(fluent.getComments(), fluent.buildAnnotations(), fluent.buildParameters(), fluent.getName(),
        fluent.buildReturnType(), fluent.buildArguments(), fluent.isVarArgPreferred(), fluent.buildExceptions(),
        fluent.isDefaultMethod(), fluent.buildBlock(), fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}