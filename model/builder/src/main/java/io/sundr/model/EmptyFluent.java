package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class EmptyFluent<A extends EmptyFluent<A>> extends BaseFluent<A> {

  public EmptyFluent() {
  }

  public EmptyFluent(Empty instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(Empty instance) {
    instance = (instance != null ? instance : new Empty());
    if (instance != null) {
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    EmptyFluent that = (EmptyFluent) o;
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
