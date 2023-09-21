package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class MethodBuilder extends MethodFluent<MethodBuilder> implements VisitableBuilder<Method, MethodBuilder> {
  public MethodBuilder() {
    this(false);
  }

  public MethodBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public MethodBuilder(MethodFluent<?> fluent) {
    this(fluent, false);
  }

  public MethodBuilder(MethodFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public MethodBuilder(MethodFluent<?> fluent, Method instance) {
    this(fluent, instance, false);
  }

  public MethodBuilder(MethodFluent<?> fluent, Method instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withComments(instance.getComments());
      fluent.withAnnotations(instance.getAnnotations());
      fluent.withParameters(instance.getParameters());
      fluent.withName(instance.getName());
      fluent.withReturnType(instance.getReturnType());
      fluent.withArguments(instance.getArguments());
      fluent.withVarArgPreferred(instance.isVarArgPreferred());
      fluent.withExceptions(instance.getExceptions());
      fluent.withDefaultMethod(instance.isDefaultMethod());
      fluent.withBlock(instance.getBlock());
      fluent.withModifiers(instance.getModifiers());
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public MethodBuilder(Method instance) {
    this(instance, false);
  }

  public MethodBuilder(Method instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withComments(instance.getComments());
      this.withAnnotations(instance.getAnnotations());
      this.withParameters(instance.getParameters());
      this.withName(instance.getName());
      this.withReturnType(instance.getReturnType());
      this.withArguments(instance.getArguments());
      this.withVarArgPreferred(instance.isVarArgPreferred());
      this.withExceptions(instance.getExceptions());
      this.withDefaultMethod(instance.isDefaultMethod());
      this.withBlock(instance.getBlock());
      this.withModifiers(instance.getModifiers());
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  MethodFluent<?> fluent;
  Boolean validationEnabled;

  public Method build() {
    Method buildable = new Method(fluent.getComments(), fluent.buildAnnotations(), fluent.buildParameters(), fluent.getName(),
        fluent.buildReturnType(), fluent.buildArguments(), fluent.isVarArgPreferred(), fluent.buildExceptions(),
        fluent.isDefaultMethod(), fluent.buildBlock(), fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}
