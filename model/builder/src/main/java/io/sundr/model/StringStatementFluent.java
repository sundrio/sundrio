package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.function.Supplier;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class StringStatementFluent<A extends StringStatementFluent<A>> extends BaseFluent<A> {
  public StringStatementFluent() {
  }

  public StringStatementFluent(StringStatement instance) {
    this.copyInstance(instance);
  }

  private Supplier<String> supplier;

  protected void copyInstance(StringStatement instance) {
    if (instance != null) {
      this.withSupplier(instance.getSupplier());
    }
  }

  public Supplier<String> getSupplier() {
    return this.supplier;
  }

  public <T> A withSupplier(Supplier<String> supplier) {
    this.supplier = supplier;
    return (A) this;
  }

  public boolean hasSupplier() {
    return this.supplier != null;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    StringStatementFluent that = (StringStatementFluent) o;
    if (!java.util.Objects.equals(supplier, that.supplier))
      return false;

    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(supplier, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (supplier != null) {
      sb.append("supplier:");
      sb.append(supplier);
    }
    sb.append("}");
    return sb.toString();
  }

}
