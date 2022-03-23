package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class TypeRefFluentImpl<A extends TypeRefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeRefFluent<A> {
  public TypeRefFluentImpl() {
  }

  public TypeRefFluentImpl(io.sundr.model.TypeRef instance) {
    this.withAttributes(instance.getAttributes());
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeRefFluentImpl that = (TypeRefFluentImpl) o;
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
