package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class GreaterThanOrEqualFluent<A extends GreaterThanOrEqualFluent<A>> extends BinaryExpressionFluent<A> {
  public GreaterThanOrEqualFluent() {
  }

  public GreaterThanOrEqualFluent(GreaterThanOrEqual instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(GreaterThanOrEqual instance) {
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
    GreaterThanOrEqualFluent that = (GreaterThanOrEqualFluent) o;
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
