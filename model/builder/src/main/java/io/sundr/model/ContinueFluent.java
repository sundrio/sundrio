package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ContinueFluent<A extends ContinueFluent<A>> extends BaseFluent<A> {
  public ContinueFluent() {
  }

  public ContinueFluent(Continue instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(Continue instance) {
    instance = (instance != null ? instance : new Continue());
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
    ContinueFluent that = (ContinueFluent) o;
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
