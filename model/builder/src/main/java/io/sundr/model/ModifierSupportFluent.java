package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;
import java.util.Optional;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ModifierSupportFluent<A extends io.sundr.model.ModifierSupportFluent<A>> extends AttributeSupportFluent<A> {

  private ModifiersBuilder modifiers;

  public ModifierSupportFluent() {
  }

  public ModifierSupportFluent(ModifierSupport instance) {
    this.copyInstance(instance);
  }

  public Modifiers buildModifiers() {
    return this.modifiers != null ? this.modifiers.build() : null;
  }

  protected void copyInstance(ModifierSupport instance) {
    if (instance != null) {
      this.withModifiers(instance.getModifiers());
      this.withAttributes(instance.getAttributes());
    }
  }

  public ModifiersNested<A> editModifiers() {
    return this.withNewModifiersLike(Optional.ofNullable(this.buildModifiers()).orElse(null));
  }

  public ModifiersNested<A> editOrNewModifiers() {
    return this.withNewModifiersLike(Optional.ofNullable(this.buildModifiers()).orElse(new ModifiersBuilder().build()));
  }

  public ModifiersNested<A> editOrNewModifiersLike(Modifiers item) {
    return this.withNewModifiersLike(Optional.ofNullable(this.buildModifiers()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    ModifierSupportFluent that = (ModifierSupportFluent) o;
    if (!(Objects.equals(modifiers, that.modifiers))) {
      return false;
    }
    return true;
  }

  public boolean hasModifiers() {
    return this.modifiers != null;
  }

  public int hashCode() {
    return Objects.hash(modifiers);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(modifiers == null)) {
      sb.append("modifiers:");
      sb.append(modifiers);
    }
    sb.append("}");
    return sb.toString();
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

  public ModifiersNested<A> withNewModifiers() {
    return new ModifiersNested(null);
  }

  public ModifiersNested<A> withNewModifiersLike(Modifiers item) {
    return new ModifiersNested(item);
  }

  public class ModifiersNested<N> extends ModifiersFluent<ModifiersNested<N>> implements Nested<N> {

    ModifiersBuilder builder;

    ModifiersNested(Modifiers item) {
      this.builder = new ModifiersBuilder(this, item);
    }

    public N and() {
      return (N) ModifierSupportFluent.this.withModifiers(builder.build());
    }

    public N endModifiers() {
      return and();
    }

  }
}
