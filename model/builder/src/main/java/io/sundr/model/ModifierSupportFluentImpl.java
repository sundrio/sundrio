package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

public class ModifierSupportFluentImpl<A extends ModifierSupportFluent<A>> extends AttributeSupportFluentImpl<A>
    implements ModifierSupportFluent<A> {

  private int modifiers;

  public ModifierSupportFluentImpl() {
  }

  public ModifierSupportFluentImpl(ModifierSupport instance) {
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  public int getModifiers() {
    return this.modifiers;
  }

  public A withModifiers(int modifiers) {
    this.modifiers = modifiers;
    return (A) this;
  }

  public Boolean hasModifiers() {
    return true;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ModifierSupportFluentImpl that = (ModifierSupportFluentImpl) o;
    if (modifiers != that.modifiers)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(modifiers, super.hashCode());
  }

}
