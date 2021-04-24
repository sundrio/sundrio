package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class MethodBuilder extends MethodFluentImpl<MethodBuilder>
    implements io.sundr.model.builder.VisitableBuilder<Method, MethodBuilder> {

  MethodFluent<?> fluent;
  Boolean validationEnabled;

  public MethodBuilder() {
    this(true);
  }

  public MethodBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public MethodBuilder(MethodFluent<?> fluent) {
    this(fluent, true);
  }

  public MethodBuilder(MethodFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public MethodBuilder(MethodFluent<?> fluent, Method instance) {
    this(fluent, instance, true);
  }

  public MethodBuilder(MethodFluent<?> fluent, Method instance, Boolean validationEnabled) {
    this.fluent = fluent;
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
    this.validationEnabled = validationEnabled;
  }

  public MethodBuilder(Method instance) {
    this(instance, true);
  }

  public MethodBuilder(Method instance, Boolean validationEnabled) {
    this.fluent = this;
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
    this.validationEnabled = validationEnabled;
  }

  public Method build() {
    Method buildable = new Method(fluent.getComments(), fluent.getAnnotations(), fluent.getParameters(), fluent.getName(),
        fluent.getReturnType(), fluent.getArguments(), fluent.isVarArgPreferred(), fluent.getExceptions(),
        fluent.isDefaultMethod(), fluent.getBlock(), fluent.getModifiers(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    MethodBuilder that = (MethodBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(fluent, validationEnabled, super.hashCode());
  }

}
