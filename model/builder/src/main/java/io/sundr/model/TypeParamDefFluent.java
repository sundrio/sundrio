package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

/**
 * Generated
 */
public interface TypeParamDefFluent<A extends TypeParamDefFluent<A>> extends AttributeSupportFluent<A> {
  public String getName();

  public A withName(java.lang.String name);

  public Boolean hasName();

  public A addToBounds(Integer index, ClassRef item);

  public A setToBounds(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A addToBounds(io.sundr.model.ClassRef... items);

  public A addAllToBounds(Collection<io.sundr.model.ClassRef> items);

  public A removeFromBounds(io.sundr.model.ClassRef... items);

  public A removeAllFromBounds(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeMatchingFromBounds(Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.ClassRef> getBounds();

  public java.util.List<io.sundr.model.ClassRef> buildBounds();

  public io.sundr.model.ClassRef buildBound(java.lang.Integer index);

  public io.sundr.model.ClassRef buildFirstBound();

  public io.sundr.model.ClassRef buildLastBound();

  public io.sundr.model.ClassRef buildMatchingBound(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public java.lang.Boolean hasMatchingBound(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public A withBounds(java.util.List<io.sundr.model.ClassRef> bounds);

  public A withBounds(io.sundr.model.ClassRef... bounds);

  public java.lang.Boolean hasBounds();

  public TypeParamDefFluent.BoundsNested<A> addNewBound();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> addNewBoundLike(io.sundr.model.ClassRef item);

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> setNewBoundLike(java.lang.Integer index,
      io.sundr.model.ClassRef item);

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editBound(java.lang.Integer index);

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editFirstBound();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editLastBound();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editMatchingBound(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public interface BoundsNested<N> extends Nested<N>, ClassRefFluent<TypeParamDefFluent.BoundsNested<N>> {
    public N and();

    public N endBound();

  }

}
