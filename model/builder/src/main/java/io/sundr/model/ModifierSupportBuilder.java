package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class ModifierSupportBuilder extends ModifierSupportFluentImpl<ModifierSupportBuilder>
    implements VisitableBuilder<ModifierSupport, io.sundr.model.ModifierSupportBuilder> {
  public ModifierSupportBuilder() {
    this(false);
  }

  public ModifierSupportBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(io.sundr.model.ModifierSupportFluent<?> fluent) {
    this(fluent, false);
  }

  public ModifierSupportBuilder(io.sundr.model.ModifierSupportFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(io.sundr.model.ModifierSupportFluent<?> fluent, io.sundr.model.ModifierSupport instance) {
    this(fluent, instance, false);
  }

  public ModifierSupportBuilder(io.sundr.model.ModifierSupportFluent<?> fluent, io.sundr.model.ModifierSupport instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withModifiers(instance.getModifiers());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(io.sundr.model.ModifierSupport instance) {
    this(instance, false);
  }

  public ModifierSupportBuilder(io.sundr.model.ModifierSupport instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.ModifierSupportFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.ModifierSupport build() {
    ModifierSupport buildable = new ModifierSupport(fluent.getModifiers(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ModifierSupportBuilder that = (ModifierSupportBuilder) o;
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
