package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ReturnFluent<A extends ReturnFluent<A>> extends BaseFluent<A> {
  public ReturnFluent() {
  }

  public ReturnFluent(Return instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(Return instance) {
    instance = (instance != null ? instance : new Return());

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
    ReturnFluent that = (ReturnFluent) o;
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
