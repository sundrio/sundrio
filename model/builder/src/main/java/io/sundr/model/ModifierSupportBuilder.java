package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ModifierSupportBuilder extends ModifierSupportFluentImpl<ModifierSupportBuilder>
    implements VisitableBuilder<ModifierSupport, ModifierSupportBuilder> {
  public ModifierSupportBuilder() {
    this(false);
  }

  public ModifierSupportBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent) {
    this(fluent, false);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance) {
    this(fluent, instance, false);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withModifiers(instance.getModifiers());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ModifierSupportBuilder(ModifierSupport instance) {
    this(instance, false);
  }

  public ModifierSupportBuilder(ModifierSupport instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  ModifierSupportFluent<?> fluent;
  Boolean validationEnabled;

  public ModifierSupport build() {
    ModifierSupport buildable = new ModifierSupport(fluent.getModifiers(), fluent.getAttributes());
    return buildable;
  }

}
