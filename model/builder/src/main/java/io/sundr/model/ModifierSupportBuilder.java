package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ModifierSupportBuilder extends ModifierSupportFluent<ModifierSupportBuilder>
    implements VisitableBuilder<ModifierSupport, ModifierSupportBuilder> {

  ModifierSupportFluent<?> fluent;

  public ModifierSupportBuilder() {
    this.fluent = this;
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ModifierSupportBuilder(ModifierSupport instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ModifierSupportBuilder(ModifierSupportFluent<?> fluent, ModifierSupport instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ModifierSupport build() {
    ModifierSupport buildable = new ModifierSupport(fluent.buildModifiers(), fluent.getAttributes());
    return buildable;
  }

}
