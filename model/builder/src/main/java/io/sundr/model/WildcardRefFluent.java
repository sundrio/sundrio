package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.model.builder.Nested;
import io.sundr.model.builder.VisitableBuilder;

public interface WildcardRefFluent<A extends WildcardRefFluent<A>> extends TypeRefFluent<A> {

  public io.sundr.model.WildcardRef.BoundKind getBoundKind();

  public A withBoundKind(io.sundr.model.WildcardRef.BoundKind boundKind);

  public Boolean hasBoundKind();

  public A addToBounds(io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToBounds(int index, io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToBounds(int index, TypeRef item);

  public A setToBounds(int index, TypeRef item);

  public A addToBounds(TypeRef... items);

  public A addAllToBounds(Collection<TypeRef> items);

  public A removeFromBounds(io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder);

  public A removeFromBounds(TypeRef... items);

  public A removeAllFromBounds(Collection<TypeRef> items);

  public A removeMatchingFromBounds(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeRef> getBounds();

  public List<TypeRef> buildBounds();

  public TypeRef buildBound(int index);

  public TypeRef buildFirstBound();

  public TypeRef buildLastBound();

  public TypeRef buildMatchingBound(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public Boolean hasMatchingBound(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public A withBounds(List<TypeRef> bounds);

  public A withBounds(TypeRef... bounds);

  public Boolean hasBounds();

  public A addToTypeParamRefBounds(int index, TypeParamRef item);

  public A setToTypeParamRefBounds(int index, TypeParamRef item);

  public A addToTypeParamRefBounds(TypeParamRef... items);

  public A addAllToTypeParamRefBounds(Collection<TypeParamRef> items);

  public A removeFromTypeParamRefBounds(TypeParamRef... items);

  public A removeAllFromTypeParamRefBounds(Collection<TypeParamRef> items);

  public A removeMatchingFromTypeParamRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(int index, TypeParamRef item);

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound();

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item);

  public A addToWildcardRefBounds(int index, WildcardRef item);

  public A setToWildcardRefBounds(int index, WildcardRef item);

  public A addToWildcardRefBounds(WildcardRef... items);

  public A addAllToWildcardRefBounds(Collection<WildcardRef> items);

  public A removeFromWildcardRefBounds(WildcardRef... items);

  public A removeAllFromWildcardRefBounds(Collection<WildcardRef> items);

  public A removeMatchingFromWildcardRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(int index, WildcardRef item);

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound();

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item);

  public A addToClassRefBounds(int index, ClassRef item);

  public A setToClassRefBounds(int index, ClassRef item);

  public A addToClassRefBounds(ClassRef... items);

  public A addAllToClassRefBounds(Collection<ClassRef> items);

  public A removeFromClassRefBounds(ClassRef... items);

  public A removeAllFromClassRefBounds(Collection<ClassRef> items);

  public A removeMatchingFromClassRefBounds(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(int index, ClassRef item);

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound();

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item);

  public A addToPrimitiveRefBounds(int index, PrimitiveRef item);

  public A setToPrimitiveRefBounds(int index, PrimitiveRef item);

  public A addToPrimitiveRefBounds(PrimitiveRef... items);

  public A addAllToPrimitiveRefBounds(Collection<PrimitiveRef> items);

  public A removeFromPrimitiveRefBounds(PrimitiveRef... items);

  public A removeAllFromPrimitiveRefBounds(Collection<PrimitiveRef> items);

  public A removeMatchingFromPrimitiveRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(int index, PrimitiveRef item);

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound();

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item);

  public A addToVoidRefBounds(int index, VoidRef item);

  public A setToVoidRefBounds(int index, VoidRef item);

  public A addToVoidRefBounds(VoidRef... items);

  public A addAllToVoidRefBounds(Collection<VoidRef> items);

  public A removeFromVoidRefBounds(VoidRef... items);

  public A removeAllFromVoidRefBounds(Collection<VoidRef> items);

  public A removeMatchingFromVoidRefBounds(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(int index, VoidRef item);

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound();

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item);

  public interface TypeParamRefBoundsNested<N> extends io.sundr.model.builder.Nested<N>,
      TypeParamRefFluent<io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<N>> {

    public N and();

    public N endTypeParamRefBound();
  }

  public interface WildcardRefBoundsNested<N>
      extends io.sundr.model.builder.Nested<N>, WildcardRefFluent<io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<N>> {

    public N and();

    public N endWildcardRefBound();
  }

  public interface ClassRefBoundsNested<N>
      extends io.sundr.model.builder.Nested<N>, ClassRefFluent<io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<N>> {

    public N and();

    public N endClassRefBound();
  }

  public interface PrimitiveRefBoundsNested<N> extends io.sundr.model.builder.Nested<N>,
      PrimitiveRefFluent<io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<N>> {

    public N and();

    public N endPrimitiveRefBound();
  }

  public interface VoidRefBoundsNested<N>
      extends io.sundr.model.builder.Nested<N>, VoidRefFluent<io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<N>> {

    public N and();

    public N endVoidRefBound();
  }

}
