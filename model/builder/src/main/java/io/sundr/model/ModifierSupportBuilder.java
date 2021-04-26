package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class ModifierSupportBuilder extends ModifierSupportFluentImpl<ModifierSupportBuilder>
    implements VisitableBuilder<ModifierSupport, ModifierSupportBuilder> {

  ModifierSupportFluent<?> fluent;
  Boolean validationEnabled;

  public ModifierSupportBuilder() {
    this(true);
  }

  public ModifierSupportBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent) {
    this(fluent, true);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance) {
    this(fluent, instance, true);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withModifiers(instance.getModifiers());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupport instance) {
    this(instance, true);
  }

  public ModifierSupportBuilder(ModifierSupport instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupport build() {
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
