package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class RightUnsignedShiftFluent<A extends RightUnsignedShiftFluent<A>> extends BinaryExpressionFluent<A> {
  public RightUnsignedShiftFluent() {
  }

  public RightUnsignedShiftFluent(RightUnsignedShift instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(RightUnsignedShift instance) {
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
    RightUnsignedShiftFluent that = (RightUnsignedShiftFluent) o;
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
