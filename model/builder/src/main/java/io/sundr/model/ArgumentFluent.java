package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ArgumentFluent<A extends io.sundr.model.ArgumentFluent<A>> extends VariableFluent<TypeRef, A> {

  private boolean isFinal;
  private VisitableBuilder<? extends TypeRef, ?> typeRef;

  public ArgumentFluent() {
  }

  public ArgumentFluent(Argument instance) {
    this.copyInstance(instance);
  }

  public TypeRef buildTypeRef() {
    return this.typeRef != null ? this.typeRef.build() : null;
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "ClassRef":

        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);

      case "PrimitiveRef":

        return (VisitableBuilder<T, ?>) new PrimitiveRefBuilder((PrimitiveRef) item);

      case "VoidRef":

        return (VisitableBuilder<T, ?>) new VoidRefBuilder((VoidRef) item);

      case "TypeParamRef":

        return (VisitableBuilder<T, ?>) new TypeParamRefBuilder((TypeParamRef) item);

      case "WildcardRef":

        return (VisitableBuilder<T, ?>) new WildcardRefBuilder((WildcardRef) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
  }

  protected void copyInstance(Argument instance) {
    if (instance != null) {
      this.withComments(instance.getComments());
      this.withAnnotations(instance.getAnnotations());
      this.withTypeRef(instance.getTypeRef());
      this.withName(instance.getName());
      this.withAttributes(instance.getAttributes());
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
    ArgumentFluent that = (ArgumentFluent) o;
    if (isFinal != that.isFinal) {
      return false;
    }
    if (!(Objects.equals(typeRef, that.typeRef))) {
      return false;
    }
    return true;
  }

  public boolean hasIsFinal() {
    return true;
  }

  public boolean hasTypeRef() {
    return this.typeRef != null;
  }

  public int hashCode() {
    return Objects.hash(isFinal, typeRef);
  }

  public boolean isIsFinal() {
    return this.isFinal;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("isFinal:");
    sb.append(isFinal);
    sb.append(",");
    if (!(typeRef == null)) {
      sb.append("typeRef:");
      sb.append(typeRef);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withIsFinal() {
    return withIsFinal(true);
  }

  public A withIsFinal(boolean isFinal) {
    this.isFinal = isFinal;
    return (A) this;
  }

  public ClassRefTypeNested<A> withNewClassRefType() {
    return new ClassRefTypeNested(null);
  }

  public ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item) {
    return new ClassRefTypeNested(item);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefType() {
    return new PrimitiveRefTypeNested(null);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item) {
    return new PrimitiveRefTypeNested(item);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefType() {
    return new TypeParamRefTypeNested(null);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item) {
    return new TypeParamRefTypeNested(item);
  }

  public VoidRefTypeNested<A> withNewVoidRefType() {
    return new VoidRefTypeNested(null);
  }

  public VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item) {
    return new VoidRefTypeNested(item);
  }

  public WildcardRefTypeNested<A> withNewWildcardRefType() {
    return new WildcardRefTypeNested(null);
  }

  public WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item) {
    return new WildcardRefTypeNested(item);
  }

  public A withTypeRef(TypeRef typeRef) {
    if (typeRef == null) {
      this.typeRef = null;
      this._visitables.remove("typeRef");
      return (A) this;
    } else {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(typeRef);
      this._visitables.get("typeRef").clear();
      this._visitables.get("typeRef").add(builder);
      this.typeRef = builder;
      return (A) this;
    }
  }

  public class ClassRefTypeNested<N> extends ClassRefFluent<ClassRefTypeNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefTypeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ArgumentFluent.this.withTypeRef(builder.build());
    }

    public N endClassRefType() {
      return and();
    }

  }

  public class PrimitiveRefTypeNested<N> extends PrimitiveRefFluent<PrimitiveRefTypeNested<N>> implements Nested<N> {

    PrimitiveRefBuilder builder;

    PrimitiveRefTypeNested(PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    public N and() {
      return (N) ArgumentFluent.this.withTypeRef(builder.build());
    }

    public N endPrimitiveRefType() {
      return and();
    }

  }

  public class TypeParamRefTypeNested<N> extends TypeParamRefFluent<TypeParamRefTypeNested<N>> implements Nested<N> {

    TypeParamRefBuilder builder;

    TypeParamRefTypeNested(TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    public N and() {
      return (N) ArgumentFluent.this.withTypeRef(builder.build());
    }

    public N endTypeParamRefType() {
      return and();
    }

  }

  public class VoidRefTypeNested<N> extends VoidRefFluent<VoidRefTypeNested<N>> implements Nested<N> {

    VoidRefBuilder builder;

    VoidRefTypeNested(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    public N and() {
      return (N) ArgumentFluent.this.withTypeRef(builder.build());
    }

    public N endVoidRefType() {
      return and();
    }

  }

  public class WildcardRefTypeNested<N> extends WildcardRefFluent<WildcardRefTypeNested<N>> implements Nested<N> {

    WildcardRefBuilder builder;

    WildcardRefTypeNested(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    public N and() {
      return (N) ArgumentFluent.this.withTypeRef(builder.build());
    }

    public N endWildcardRefType() {
      return and();
    }

  }
}
