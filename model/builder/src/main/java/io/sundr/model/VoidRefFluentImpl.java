package io.sundr.model;

import java.lang.Object;

public class VoidRefFluentImpl<A extends VoidRefFluent<A>> extends TypeRefFluentImpl<A> implements VoidRefFluent<A> {

  public VoidRefFluentImpl() {
  }

  public VoidRefFluentImpl(VoidRef instance) {
    this.withAttributes(instance.getAttributes());
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    VoidRefFluentImpl that = (VoidRefFluentImpl) o;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(super.hashCode());
  }

}
