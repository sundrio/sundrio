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

  public A withBoundKind(WildcardRef.BoundKind boundKind);

  public Boolean hasBoundKind();

  public A addToBounds(VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToBounds(Integer index, VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToBounds(Integer index, TypeRef item);

  public A setToBounds(Integer index, TypeRef item);

  public A addToBounds(io.sundr.model.TypeRef... items);

  public A addAllToBounds(Collection<TypeRef> items);

  public A removeFromBounds(VisitableBuilder<? extends TypeRef, ?> builder);

  public A removeFromBounds(io.sundr.model.TypeRef... items);

  public A removeAllFromBounds(Collection<TypeRef> items);

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeRef> getBounds();

  public List<TypeRef> buildBounds();

  public TypeRef buildBound(Integer index);

  public TypeRef buildFirstBound();

  public TypeRef buildLastBound();

  public TypeRef buildMatchingBound(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public Boolean hasMatchingBound(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public A withBounds(List<TypeRef> bounds);

  public A withBounds(io.sundr.model.TypeRef... bounds);

  public Boolean hasBounds();

  public A addToTypeParamRefBounds(Integer index, TypeParamRef item);

  public A setToTypeParamRefBounds(Integer index, TypeParamRef item);

  public A addToTypeParamRefBounds(io.sundr.model.TypeParamRef... items);

  public A addAllToTypeParamRefBounds(Collection<TypeParamRef> items);

  public A removeFromTypeParamRefBounds(io.sundr.model.TypeParamRef... items);

  public A removeAllFromTypeParamRefBounds(Collection<TypeParamRef> items);

  public A removeMatchingFromTypeParamRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(Integer index, TypeParamRef item);

  public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound();

  public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item);

  public A addToWildcardRefBounds(Integer index, WildcardRef item);

  public A setToWildcardRefBounds(Integer index, WildcardRef item);

  public A addToWildcardRefBounds(io.sundr.model.WildcardRef... items);

  public A addAllToWildcardRefBounds(Collection<WildcardRef> items);

  public A removeFromWildcardRefBounds(io.sundr.model.WildcardRef... items);

  public A removeAllFromWildcardRefBounds(Collection<WildcardRef> items);

  public A removeMatchingFromWildcardRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(Integer index, WildcardRef item);

  public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound();

  public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item);

  public A addToClassRefBounds(Integer index, ClassRef item);

  public A setToClassRefBounds(Integer index, ClassRef item);

  public A addToClassRefBounds(io.sundr.model.ClassRef... items);

  public A addAllToClassRefBounds(Collection<ClassRef> items);

  public A removeFromClassRefBounds(io.sundr.model.ClassRef... items);

  public A removeAllFromClassRefBounds(Collection<ClassRef> items);

  public A removeMatchingFromClassRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(Integer index, ClassRef item);

  public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound();

  public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item);

  public A addToPrimitiveRefBounds(Integer index, PrimitiveRef item);

  public A setToPrimitiveRefBounds(Integer index, PrimitiveRef item);

  public A addToPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items);

  public A addAllToPrimitiveRefBounds(Collection<PrimitiveRef> items);

  public A removeFromPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items);

  public A removeAllFromPrimitiveRefBounds(Collection<PrimitiveRef> items);

  public A removeMatchingFromPrimitiveRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(Integer index, PrimitiveRef item);

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound();

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item);

  public A addToVoidRefBounds(Integer index, VoidRef item);

  public A setToVoidRefBounds(Integer index, VoidRef item);

  public A addToVoidRefBounds(io.sundr.model.VoidRef... items);

  public A addAllToVoidRefBounds(Collection<VoidRef> items);

  public A removeFromVoidRefBounds(io.sundr.model.VoidRef... items);

  public A removeAllFromVoidRefBounds(Collection<VoidRef> items);

  public A removeMatchingFromVoidRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(Integer index, VoidRef item);

  public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound();

  public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item);

  public interface TypeParamRefBoundsNested<N>
      extends Nested<N>, TypeParamRefFluent<WildcardRefFluent.TypeParamRefBoundsNested<N>> {
    public N and();

    public N endTypeParamRefBound();

  }

  public interface WildcardRefBoundsNested<N>
      extends Nested<N>, WildcardRefFluent<WildcardRefFluent.WildcardRefBoundsNested<N>> {
    public N and();

    public N endWildcardRefBound();

  }

  public interface ClassRefBoundsNested<N> extends Nested<N>, ClassRefFluent<WildcardRefFluent.ClassRefBoundsNested<N>> {
    public N and();

    public N endClassRefBound();

  }

  public interface PrimitiveRefBoundsNested<N>
      extends Nested<N>, PrimitiveRefFluent<WildcardRefFluent.PrimitiveRefBoundsNested<N>> {
    public N and();

    public N endPrimitiveRefBound();

  }

  public interface VoidRefBoundsNested<N> extends Nested<N>, VoidRefFluent<WildcardRefFluent.VoidRefBoundsNested<N>> {
    public N and();

    public N endVoidRefBound();

  }

}
