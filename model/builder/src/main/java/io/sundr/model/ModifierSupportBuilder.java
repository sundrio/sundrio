package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ModifierSupportBuilder extends ModifierSupportFluent<ModifierSupportBuilder>
    implements VisitableBuilder<ModifierSupport, ModifierSupportBuilder> {
  public ModifierSupportBuilder() {
    this.fluent = this;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ModifierSupportBuilder(ModifierSupport instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ModifierSupportFluent<?> fluent;

  public ModifierSupport build() {
    ModifierSupport buildable = new ModifierSupport(fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}