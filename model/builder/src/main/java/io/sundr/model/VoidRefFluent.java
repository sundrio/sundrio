package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class VoidRefFluent<A extends io.sundr.model.VoidRefFluent<A>> extends TypeRefFluent<A> {

  public VoidRefFluent() {
  }

  public VoidRefFluent(VoidRef instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(VoidRef instance) {
    instance = instance != null ? instance : new VoidRef();
    if (instance != null) {
      this.withAttributes(instance.getAttributes());
    }
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
    VoidRefFluent that = (VoidRefFluent) o;
    return true;
  }

  public int hashCode() {
    return Objects.hash();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("}");
    return sb.toString();
  }

}