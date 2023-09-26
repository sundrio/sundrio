package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class RightShiftFluent<A extends RightShiftFluent<A>> extends BinaryExpressionFluent<A> {
  public RightShiftFluent() {
  }

  public RightShiftFluent(RightShift instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(RightShift instance) {
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    RightShiftFluent that = (RightShiftFluent) o;
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
