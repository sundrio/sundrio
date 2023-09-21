package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ModifiersBuilder extends ModifiersFluent<ModifiersBuilder>
    implements VisitableBuilder<Modifiers, ModifiersBuilder> {
  public ModifiersBuilder() {
    this(false);
  }

  public ModifiersBuilder(Boolean validationEnabled) {
    this(new Modifiers(), validationEnabled);
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent) {
    this(fluent, false);
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Modifiers(), validationEnabled);
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent, Modifiers instance) {
    this(fluent, instance, false);
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent, Modifiers instance, Boolean validationEnabled) {
    this.fluent = fluent;
    instance = (instance != null ? instance : new Modifiers());

    if (instance != null) {
      fluent.withPrivate(instance.isPrivate());
      fluent.withProtected(instance.isProtected());
      fluent.withPublic(instance.isPublic());
      fluent.withAbstract(instance.isAbstract());
      fluent.withFinal(instance.isFinal());
      fluent.withNative(instance.isNative());
      fluent.withStatic(instance.isStatic());
      fluent.withSynchronized(instance.isSynchronized());
      fluent.withTransient(instance.isTransient());
    }
    this.validationEnabled = validationEnabled;
  }

  public ModifiersBuilder(Modifiers instance) {
    this(instance, false);
  }

  public ModifiersBuilder(Modifiers instance, Boolean validationEnabled) {
    this.fluent = this;
    instance = (instance != null ? instance : new Modifiers());

    if (instance != null) {
      this.withPrivate(instance.isPrivate());
      this.withProtected(instance.isProtected());
      this.withPublic(instance.isPublic());
      this.withAbstract(instance.isAbstract());
      this.withFinal(instance.isFinal());
      this.withNative(instance.isNative());
      this.withStatic(instance.isStatic());
      this.withSynchronized(instance.isSynchronized());
      this.withTransient(instance.isTransient());
    }
    this.validationEnabled = validationEnabled;
  }

  ModifiersFluent<?> fluent;
  Boolean validationEnabled;

  public Modifiers build() {
    Modifiers buildable = new Modifiers(fluent.isPrivate(), fluent.isProtected(), fluent.isPublic(), fluent.isAbstract(),
        fluent.isFinal(), fluent.isNative(), fluent.isStatic(), fluent.isSynchronized(), fluent.isTransient());
    return buildable;
  }

}
