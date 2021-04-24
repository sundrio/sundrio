package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.util.List;
import java.util.function.Supplier;

import io.sundr.model.builder.BaseFluent;

public class BlockFluentImpl<A extends BlockFluent<A>> extends io.sundr.model.builder.BaseFluent<A> implements BlockFluent<A> {

  private Supplier<List<Statement>> provider;

  public BlockFluentImpl() {
  }

  public BlockFluentImpl(Block instance) {
    this.withProvider(instance.getProvider());
  }

  public Supplier<List<Statement>> getProvider() {
    return this.provider;
  }

  public <T> A withProvider(Supplier<List<Statement>> provider) {
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
    BlockFluentImpl that = (BlockFluentImpl) o;
    if (provider != null ? !provider.equals(that.provider) : that.provider != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(provider, super.hashCode());
  }

}
