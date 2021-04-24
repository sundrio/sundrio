package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.util.function.Supplier;

public class StringStatementFluentImpl<A extends StringStatementFluent<A>> extends StatementFluentImpl<A>
    implements StringStatementFluent<A> {

  private Supplier<String> provider;

  public StringStatementFluentImpl() {
  }

  public StringStatementFluentImpl(StringStatement instance) {
    this.withProvider(instance.getProvider());
  }

  public Supplier<String> getProvider() {
    return this.provider;
  }

  public <T> A withProvider(Supplier<String> provider) {
    this.provider = provider;
    return (A) this;
  }

  public Boolean hasProvider() {
    return this.provider != null;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    StringStatementFluentImpl that = (StringStatementFluentImpl) o;
    if (provider != null ? !provider.equals(that.provider) : that.provider != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(provider, super.hashCode());
  }

}
