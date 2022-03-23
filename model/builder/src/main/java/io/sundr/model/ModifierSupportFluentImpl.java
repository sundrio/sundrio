package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class ModifierSupportFluentImpl<A extends ModifierSupportFluent<A>> extends AttributeSupportFluentImpl<A>
    implements ModifierSupportFluent<A> {
  public ModifierSupportFluentImpl() {
  }

  public ModifierSupportFluentImpl(io.sundr.model.ModifierSupport instance) {
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  private int modifiers;

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

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("modifiers:");
    sb.append(modifiers);
    sb.append("}");
    return sb.toString();
  }

}
