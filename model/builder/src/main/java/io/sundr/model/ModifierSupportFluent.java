package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;

import io.sundr.builder.Nested;

/**
 * Generated
 */
public interface ModifierSupportFluent<A extends ModifierSupportFluent<A>> extends AttributeSupportFluent<A> {

  /**
   * This method has been deprecated, please use method buildModifiers instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public Modifiers getModifiers();

  public io.sundr.model.Modifiers buildModifiers();

  public A withModifiers(io.sundr.model.Modifiers modifiers);

  public Boolean hasModifiers();

  public ModifierSupportFluent.ModifiersNested<A> withNewModifiers();

  public io.sundr.model.ModifierSupportFluent.ModifiersNested<A> withNewModifiersLike(io.sundr.model.Modifiers item);

  public io.sundr.model.ModifierSupportFluent.ModifiersNested<A> editModifiers();

  public io.sundr.model.ModifierSupportFluent.ModifiersNested<A> editOrNewModifiers();

  public io.sundr.model.ModifierSupportFluent.ModifiersNested<A> editOrNewModifiersLike(io.sundr.model.Modifiers item);

  public interface ModifiersNested<N> extends Nested<N>, ModifiersFluent<ModifierSupportFluent.ModifiersNested<N>> {
    public N and();

    public N endModifiers();

  }

}
