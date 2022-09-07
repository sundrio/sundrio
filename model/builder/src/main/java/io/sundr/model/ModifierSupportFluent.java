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

  public Modifiers buildModifiers();

  public A withModifiers(Modifiers modifiers);

  public Boolean hasModifiers();

  public ModifierSupportFluent.ModifiersNested<A> withNewModifiers();

  public ModifierSupportFluent.ModifiersNested<A> withNewModifiersLike(Modifiers item);

  public ModifierSupportFluent.ModifiersNested<A> editModifiers();

  public ModifierSupportFluent.ModifiersNested<A> editOrNewModifiers();

  public ModifierSupportFluent.ModifiersNested<A> editOrNewModifiersLike(Modifiers item);

  public interface ModifiersNested<N> extends Nested<N>, ModifiersFluent<ModifierSupportFluent.ModifiersNested<N>> {
    public N and();

    public N endModifiers();

  }

}
