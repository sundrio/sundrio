package io.sundr.model;

import java.lang.Object;

import io.sundr.model.builder.BaseFluent;

public class StatementFluentImpl<A extends StatementFluent<A>> extends io.sundr.model.builder.BaseFluent<A>
    implements StatementFluent<A> {

  public StatementFluentImpl() {
  }

  public StatementFluentImpl(Statement instance) {
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    StatementFluentImpl that = (StatementFluentImpl) o;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(super.hashCode());
  }

}
