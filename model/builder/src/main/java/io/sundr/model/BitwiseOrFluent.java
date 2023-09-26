package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class BitwiseOrFluent<A extends BitwiseOrFluent<A>> extends BinaryExpressionFluent<A> {
  public BitwiseOrFluent() {
  }

  public BitwiseOrFluent(BitwiseOr instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(BitwiseOr instance) {
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
    BitwiseOrFluent that = (BitwiseOrFluent) o;
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
