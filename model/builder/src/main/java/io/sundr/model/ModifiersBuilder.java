package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class ModifiersBuilder extends ModifiersFluentImpl<ModifiersBuilder>
    implements VisitableBuilder<Modifiers, io.sundr.model.ModifiersBuilder> {
  public ModifiersBuilder() {
    this(false);
  }

  public ModifiersBuilder(Boolean validationEnabled) {
    this(new Modifiers(), validationEnabled);
  }

  public ModifiersBuilder(ModifiersFluent<?> fluent) {
    this(fluent, false);
  }

  public ModifiersBuilder(io.sundr.model.ModifiersFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this(fluent, new Modifiers(), validationEnabled);
  }

  public ModifiersBuilder(io.sundr.model.ModifiersFluent<?> fluent, io.sundr.model.Modifiers instance) {
    this(fluent, instance, false);
  }

  public ModifiersBuilder(io.sundr.model.ModifiersFluent<?> fluent, io.sundr.model.Modifiers instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withPrivate(instance.isPrivate());
    fluent.withProtected(instance.isProtected());
    fluent.withPublic(instance.isPublic());
    fluent.withAbstract(instance.isAbstract());
    fluent.withFinal(instance.isFinal());
    fluent.withNative(instance.isNative());
    fluent.withStatic(instance.isStatic());
    fluent.withSynchronized(instance.isSynchronized());
    fluent.withTransient(instance.isTransient());
    this.validationEnabled = validationEnabled;
  }

  public ModifiersBuilder(io.sundr.model.Modifiers instance) {
    this(instance, false);
  }

  public ModifiersBuilder(io.sundr.model.Modifiers instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withPrivate(instance.isPrivate());
    this.withProtected(instance.isProtected());
    this.withPublic(instance.isPublic());
    this.withAbstract(instance.isAbstract());
    this.withFinal(instance.isFinal());
    this.withNative(instance.isNative());
    this.withStatic(instance.isStatic());
    this.withSynchronized(instance.isSynchronized());
    this.withTransient(instance.isTransient());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.ModifiersFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.Modifiers build() {
    Modifiers buildable = new Modifiers(fluent.isPrivate(), fluent.isProtected(), fluent.isPublic(), fluent.isAbstract(),
        fluent.isFinal(), fluent.isNative(), fluent.isStatic(), fluent.isSynchronized(), fluent.isTransient());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ModifiersBuilder that = (ModifiersBuilder) o;
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
