package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.util.function.Supplier;

import io.sundr.builder.BaseFluent;

public class StringStatementFluentImpl<A extends StringStatementFluent<A>> extends BaseFluent<A>
    implements StringStatementFluent<A> {

  private Supplier<String> supplier;

  public StringStatementFluentImpl() {
  }

  public StringStatementFluentImpl(StringStatement instance) {
    this.withSupplier(instance.getSupplier());
  }

  public Supplier<String> getSupplier() {
    return this.supplier;
  }

  public <T extends Object> A withSupplier(Supplier<String> supplier) {
    this.supplier = supplier;
    return (A) this;
  }

  public Boolean hasSupplier() {
    return this.supplier != null;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    StringStatementFluentImpl that = (StringStatementFluentImpl) o;
    if (supplier != null ? !supplier.equals(that.supplier) : that.supplier != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(supplier, super.hashCode());
  }

}