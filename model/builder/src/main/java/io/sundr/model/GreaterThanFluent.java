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
public class GreaterThanFluent<A extends io.sundr.model.GreaterThanFluent<A>> extends BinaryExpressionFluent<A> {

  public GreaterThanFluent() {
  }

  public GreaterThanFluent(GreaterThan instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(GreaterThan instance) {
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
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
    GreaterThanFluent that = (GreaterThanFluent) o;
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