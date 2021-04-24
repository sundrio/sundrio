package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;

public class PrimitiveRefFluentImpl<A extends PrimitiveRefFluent<A>> extends TypeRefFluentImpl<A>
    implements PrimitiveRefFluent<A> {

  private String name;
  private int dimensions;

  public PrimitiveRefFluentImpl() {
  }

  public PrimitiveRefFluentImpl(PrimitiveRef instance) {
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
  }

  public String getName() {
    return this.name;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public Boolean hasName() {
    return this.name != null;
  }

  public A withNewName(StringBuilder arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(int[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(char[] arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(StringBuffer arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(byte[] arg1, int arg2) {
    return (A) withName(new String(arg1, arg2));
  }

  public A withNewName(byte[] arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(char[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(byte[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(byte[] arg1, int arg2, int arg3, int arg4) {
    return (A) withName(new String(arg1, arg2, arg3, arg4));
  }

  public A withNewName(String arg1) {
    return (A) withName(new String(arg1));
  }

  public int getDimensions() {
    return this.dimensions;
  }

  public A withDimensions(int dimensions) {
    this.dimensions = dimensions;
    return (A) this;
  }

  public Boolean hasDimensions() {
    return true;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PrimitiveRefFluentImpl that = (PrimitiveRefFluentImpl) o;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (dimensions != that.dimensions)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(name, dimensions, super.hashCode());
  }

}
