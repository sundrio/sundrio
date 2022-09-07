package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class ModifierSupportFluentImpl<A extends ModifierSupportFluent<A>> extends AttributeSupportFluentImpl<A>
    implements ModifierSupportFluent<A> {
  public ModifierSupportFluentImpl() {
  }

  public ModifierSupportFluentImpl(ModifierSupport instance) {
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  private ModifiersBuilder modifiers;

  /**
   * This method has been deprecated, please use method buildModifiers instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public Modifiers getModifiers() {
    return this.modifiers != null ? this.modifiers.build() : null;
  }

  public Modifiers buildModifiers() {
    return this.modifiers != null ? this.modifiers.build() : null;
  }

  public A withModifiers(Modifiers modifiers) {
    _visitables.get("modifiers").remove(this.modifiers);
    if (modifiers != null) {
      this.modifiers = new ModifiersBuilder(modifiers);
      _visitables.get("modifiers").add(this.modifiers);
    } else {
      this.modifiers = null;
      _visitables.get("modifiers").remove(this.modifiers);
    }
    return (A) this;
  }

  public Boolean hasModifiers() {
    return this.modifiers != null;
  }

  public ModifierSupportFluent.ModifiersNested<A> withNewModifiers() {
    return new ModifierSupportFluentImpl.ModifiersNestedImpl();
  }

  public ModifierSupportFluent.ModifiersNested<A> withNewModifiersLike(Modifiers item) {
    return new ModifierSupportFluentImpl.ModifiersNestedImpl(item);
  }

  public ModifierSupportFluent.ModifiersNested<A> editModifiers() {
    return withNewModifiersLike(getModifiers());
  }

  public ModifierSupportFluent.ModifiersNested<A> editOrNewModifiers() {
    return withNewModifiersLike(getModifiers() != null ? getModifiers() : new ModifiersBuilder().build());
  }

  public ModifierSupportFluent.ModifiersNested<A> editOrNewModifiersLike(Modifiers item) {
    return withNewModifiersLike(getModifiers() != null ? getModifiers() : item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ModifierSupportFluentImpl that = (ModifierSupportFluentImpl) o;
    if (modifiers != null ? !modifiers.equals(that.modifiers) : that.modifiers != null)
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

  class ModifiersNestedImpl<N> extends ModifiersFluentImpl<ModifierSupportFluent.ModifiersNested<N>>
      implements ModifierSupportFluent.ModifiersNested<N>, Nested<N> {
    ModifiersNestedImpl(Modifiers item) {
      this.builder = new ModifiersBuilder(this, item);
    }

    ModifiersNestedImpl() {
      this.builder = new ModifiersBuilder(this);
    }

    ModifiersBuilder builder;

    public N and() {
      return (N) ModifierSupportFluentImpl.this.withModifiers(builder.build());
    }

    public N endModifiers() {
      return and();
    }

  }

}
