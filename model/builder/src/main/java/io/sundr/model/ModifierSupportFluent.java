package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ModifierSupportFluent<A extends ModifierSupportFluent<A>> extends AttributeSupportFluent<A> {
  public ModifierSupportFluent() {
  }

  public ModifierSupportFluent(ModifierSupport instance) {
    this.copyInstance(instance);
  }

  private ModifiersBuilder modifiers;

  protected void copyInstance(ModifierSupport instance) {
    if (instance != null) {
      this.withModifiers(instance.getModifiers());
      this.withAttributes(instance.getAttributes());
    }
  }

  public Modifiers buildModifiers() {
    return this.modifiers != null ? this.modifiers.build() : null;
  }

  public A withModifiers(Modifiers modifiers) {
    this._visitables.remove("modifiers");
    if (modifiers != null) {
      this.modifiers = new ModifiersBuilder(modifiers);
      this._visitables.get("modifiers").add(this.modifiers);
    } else {
      this.modifiers = null;
      this._visitables.get("modifiers").remove(this.modifiers);
    }
    return (A) this;
  }

  public boolean hasModifiers() {
    return this.modifiers != null;
  }

  public ModifiersNested<A> withNewModifiers() {
    return new ModifiersNested(null);
  }

  public ModifiersNested<A> withNewModifiersLike(Modifiers item) {
    return new ModifiersNested(item);
  }

  public ModifiersNested<A> editModifiers() {
    return withNewModifiersLike(java.util.Optional.ofNullable(buildModifiers()).orElse(null));
  }

  public ModifiersNested<A> editOrNewModifiers() {
    return withNewModifiersLike(java.util.Optional.ofNullable(buildModifiers()).orElse(new ModifiersBuilder().build()));
  }

  public ModifiersNested<A> editOrNewModifiersLike(Modifiers item) {
    return withNewModifiersLike(java.util.Optional.ofNullable(buildModifiers()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ModifierSupportFluent that = (ModifierSupportFluent) o;
    if (!java.util.Objects.equals(modifiers, that.modifiers))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(modifiers, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (modifiers != null) {
      sb.append("modifiers:");
      sb.append(modifiers);
    }
    sb.append("}");
    return sb.toString();
  }

  public class ModifiersNested<N> extends ModifiersFluent<ModifiersNested<N>> implements Nested<N> {
    ModifiersNested(Modifiers item) {
      this.builder = new ModifiersBuilder(this, item);
    }

    ModifiersBuilder builder;

    public N and() {
      return (N) ModifierSupportFluent.this.withModifiers(builder.build());
    }

    public N endModifiers() {
      return and();
    }

  }

}
