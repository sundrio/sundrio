package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class VoidRefFluent<A extends VoidRefFluent<A>> extends TypeRefFluent<A> {
  public VoidRefFluent() {
  }

  public VoidRefFluent(VoidRef instance) {
    instance = (instance != null ? instance : new VoidRef());

    if (instance != null) {
      this.withAttributes(instance.getAttributes());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    VoidRefFluent that = (VoidRefFluent) o;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("}");
    return sb.toString();
  }

}
