package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class DotClassFluent<A extends DotClassFluent<A>> extends BaseFluent<A> {

  private ClassRefBuilder classRef;

  public DotClassFluent() {
  }

  public DotClassFluent(DotClass instance) {
    this.copyInstance(instance);
  }

  public ClassRef buildClassRef() {
    return this.classRef != null ? this.classRef.build() : null;
  }

  protected void copyInstance(DotClass instance) {
    if (instance != null) {
      this.withClassRef(instance.getClassRef());
    }
  }

  public ClassRefNested<A> editClassRef() {
    return withNewClassRefLike(java.util.Optional.ofNullable(buildClassRef()).orElse(null));
  }

  public ClassRefNested<A> editOrNewClassRef() {
    return withNewClassRefLike(java.util.Optional.ofNullable(buildClassRef()).orElse(new ClassRefBuilder().build()));
  }

  public ClassRefNested<A> editOrNewClassRefLike(ClassRef item) {
    return withNewClassRefLike(java.util.Optional.ofNullable(buildClassRef()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    DotClassFluent that = (DotClassFluent) o;
    if (!java.util.Objects.equals(classRef, that.classRef))
      return false;
    return true;
  }

  public boolean hasClassRef() {
    return this.classRef != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(classRef, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (classRef != null) {
      sb.append("classRef:");
      sb.append(classRef);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withClassRef(ClassRef classRef) {
    this._visitables.remove("classRef");
    if (classRef != null) {
      this.classRef = new ClassRefBuilder(classRef);
      this._visitables.get("classRef").add(this.classRef);
    } else {
      this.classRef = null;
      this._visitables.get("classRef").remove(this.classRef);
    }
    return (A) this;
  }

  public ClassRefNested<A> withNewClassRef() {
    return new ClassRefNested(null);
  }

  public ClassRefNested<A> withNewClassRefLike(ClassRef item) {
    return new ClassRefNested(item);
  }

  public class ClassRefNested<N> extends ClassRefFluent<ClassRefNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) DotClassFluent.this.withClassRef(builder.build());
    }

    public N endClassRef() {
      return and();
    }

  }
}
