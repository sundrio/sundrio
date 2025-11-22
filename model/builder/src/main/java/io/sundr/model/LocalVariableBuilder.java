package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class LocalVariableBuilder extends LocalVariableFluent<LocalVariableBuilder>
    implements VisitableBuilder<LocalVariable, LocalVariableBuilder> {

  LocalVariableFluent<?> fluent;

  public LocalVariableBuilder() {
    this.fluent = this;
  }

  public LocalVariableBuilder(LocalVariableFluent<?> fluent) {
    this.fluent = fluent;
  }

  public LocalVariableBuilder(LocalVariable instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public LocalVariableBuilder(LocalVariableFluent<?> fluent, LocalVariable instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public LocalVariable build() {
    LocalVariable buildable = new LocalVariable(fluent.getComments(), fluent.buildAnnotations(), fluent.buildTypeRef(),
        fluent.getName(), fluent.buildInitialValue(), fluent.isIsFinal(), fluent.getAttributes());
    return buildable;
  }

}
