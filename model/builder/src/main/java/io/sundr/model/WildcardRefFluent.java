package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
public interface WildcardRefFluent<A extends WildcardRefFluent<A>> extends TypeRefFluent<A> {
  public WildcardRef.BoundKind getBoundKind();

  public A withBoundKind(io.sundr.model.WildcardRef.BoundKind boundKind);

  public Boolean hasBoundKind();

  public A addToBounds(VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToBounds(Integer index, io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder);

  public A addToBounds(java.lang.Integer index, io.sundr.model.TypeRef item);

  public A setToBounds(java.lang.Integer index, io.sundr.model.TypeRef item);

  public A addToBounds(io.sundr.model.TypeRef... items);

  public A addAllToBounds(Collection<io.sundr.model.TypeRef> items);

  public A removeFromBounds(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder);

  public A removeFromBounds(io.sundr.model.TypeRef... items);

  public A removeAllFromBounds(java.util.Collection<io.sundr.model.TypeRef> items);

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.TypeRef> getBounds();

  public java.util.List<io.sundr.model.TypeRef> buildBounds();

  public io.sundr.model.TypeRef buildBound(java.lang.Integer index);

  public io.sundr.model.TypeRef buildFirstBound();

  public io.sundr.model.TypeRef buildLastBound();

  public io.sundr.model.TypeRef buildMatchingBound(
      Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public java.lang.Boolean hasMatchingBound(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public A withBounds(java.util.List<io.sundr.model.TypeRef> bounds);

  public A withBounds(io.sundr.model.TypeRef... bounds);

  public java.lang.Boolean hasBounds();

  public A addToTypeParamRefBounds(java.lang.Integer index, TypeParamRef item);

  public A setToTypeParamRefBounds(java.lang.Integer index, io.sundr.model.TypeParamRef item);

  public A addToTypeParamRefBounds(io.sundr.model.TypeParamRef... items);

  public A addAllToTypeParamRefBounds(java.util.Collection<io.sundr.model.TypeParamRef> items);

  public A removeFromTypeParamRefBounds(io.sundr.model.TypeParamRef... items);

  public A removeAllFromTypeParamRefBounds(java.util.Collection<io.sundr.model.TypeParamRef> items);

  public A removeMatchingFromTypeParamRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(java.lang.Integer index,
      io.sundr.model.TypeParamRef item);

  public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound();

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(
      io.sundr.model.TypeParamRef item);

  public A addToWildcardRefBounds(java.lang.Integer index, io.sundr.model.WildcardRef item);

  public A setToWildcardRefBounds(java.lang.Integer index, io.sundr.model.WildcardRef item);

  public A addToWildcardRefBounds(io.sundr.model.WildcardRef... items);

  public A addAllToWildcardRefBounds(java.util.Collection<io.sundr.model.WildcardRef> items);

  public A removeFromWildcardRefBounds(io.sundr.model.WildcardRef... items);

  public A removeAllFromWildcardRefBounds(java.util.Collection<io.sundr.model.WildcardRef> items);

  public A removeMatchingFromWildcardRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(java.lang.Integer index,
      io.sundr.model.WildcardRef item);

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound();

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(
      io.sundr.model.WildcardRef item);

  public A addToClassRefBounds(java.lang.Integer index, ClassRef item);

  public A setToClassRefBounds(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A addToClassRefBounds(io.sundr.model.ClassRef... items);

  public A addAllToClassRefBounds(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeFromClassRefBounds(io.sundr.model.ClassRef... items);

  public A removeAllFromClassRefBounds(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeMatchingFromClassRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(java.lang.Integer index,
      io.sundr.model.ClassRef item);

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound();

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(io.sundr.model.ClassRef item);

  public A addToPrimitiveRefBounds(java.lang.Integer index, PrimitiveRef item);

  public A setToPrimitiveRefBounds(java.lang.Integer index, io.sundr.model.PrimitiveRef item);

  public A addToPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items);

  public A addAllToPrimitiveRefBounds(java.util.Collection<io.sundr.model.PrimitiveRef> items);

  public A removeFromPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items);

  public A removeAllFromPrimitiveRefBounds(java.util.Collection<io.sundr.model.PrimitiveRef> items);

  public A removeMatchingFromPrimitiveRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(java.lang.Integer index,
      io.sundr.model.PrimitiveRef item);

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound();

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(
      io.sundr.model.PrimitiveRef item);

  public A addToVoidRefBounds(java.lang.Integer index, VoidRef item);

  public A setToVoidRefBounds(java.lang.Integer index, io.sundr.model.VoidRef item);

  public A addToVoidRefBounds(io.sundr.model.VoidRef... items);

  public A addAllToVoidRefBounds(java.util.Collection<io.sundr.model.VoidRef> items);

  public A removeFromVoidRefBounds(io.sundr.model.VoidRef... items);

  public A removeAllFromVoidRefBounds(java.util.Collection<io.sundr.model.VoidRef> items);

  public A removeMatchingFromVoidRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(java.lang.Integer index, io.sundr.model.VoidRef item);

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound();

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(io.sundr.model.VoidRef item);

  public interface TypeParamRefBoundsNested<N>
      extends Nested<N>, TypeParamRefFluent<WildcardRefFluent.TypeParamRefBoundsNested<N>> {
    public N and();

    public N endTypeParamRefBound();

  }

  public interface WildcardRefBoundsNested<N>
      extends io.sundr.builder.Nested<N>, WildcardRefFluent<WildcardRefFluent.WildcardRefBoundsNested<N>> {
    public N and();

    public N endWildcardRefBound();

  }

  public interface ClassRefBoundsNested<N>
      extends io.sundr.builder.Nested<N>, ClassRefFluent<WildcardRefFluent.ClassRefBoundsNested<N>> {
    public N and();

    public N endClassRefBound();

  }

  public interface PrimitiveRefBoundsNested<N>
      extends io.sundr.builder.Nested<N>, PrimitiveRefFluent<WildcardRefFluent.PrimitiveRefBoundsNested<N>> {
    public N and();

    public N endPrimitiveRefBound();

  }

  public interface VoidRefBoundsNested<N>
      extends io.sundr.builder.Nested<N>, VoidRefFluent<WildcardRefFluent.VoidRefBoundsNested<N>> {
    public N and();

    public N endVoidRefBound();

  }

}
