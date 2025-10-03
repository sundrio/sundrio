package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ModifiersBuilder extends ModifiersFluent<ModifiersBuilder>
    implements VisitableBuilder<Modifiers, ModifiersBuilder> {

  ModifiersFluent<?> fluent;

  public ModifiersBuilder() {
    this(new Modifiers());
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent) {
    this(fluent, new Modifiers());
  }

  public ModifiersBuilder(Modifiers instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent, Modifiers instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public Modifiers build() {
    Modifiers buildable = new Modifiers(fluent.isPrivate(), fluent.isProtected(), fluent.isPublic(), fluent.isAbstract(),
        fluent.isFinal(), fluent.isNative(), fluent.isStatic(), fluent.isSynchronized(), fluent.isTransient());
    return buildable;
  }

}
