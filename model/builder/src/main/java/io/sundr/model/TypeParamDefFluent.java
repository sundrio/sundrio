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

  public A withName(String name);

  public Boolean hasName();

  public A addToBounds(Integer index, ClassRef item);

  public A setToBounds(Integer index, ClassRef item);

  public A addToBounds(io.sundr.model.ClassRef... items);

  public A addAllToBounds(Collection<ClassRef> items);

  public A removeFromBounds(io.sundr.model.ClassRef... items);

  public A removeAllFromBounds(Collection<ClassRef> items);

  public A removeMatchingFromBounds(Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<ClassRef> getBounds();

  public List<ClassRef> buildBounds();

  public ClassRef buildBound(Integer index);

  public ClassRef buildFirstBound();

  public ClassRef buildLastBound();

  public ClassRef buildMatchingBound(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingBound(Predicate<ClassRefBuilder> predicate);

  public A withBounds(List<ClassRef> bounds);

  public A withBounds(io.sundr.model.ClassRef... bounds);

  public Boolean hasBounds();

  public TypeParamDefFluent.BoundsNested<A> addNewBound();

  public TypeParamDefFluent.BoundsNested<A> addNewBoundLike(ClassRef item);

  public TypeParamDefFluent.BoundsNested<A> setNewBoundLike(Integer index, ClassRef item);

  public TypeParamDefFluent.BoundsNested<A> editBound(Integer index);

  public TypeParamDefFluent.BoundsNested<A> editFirstBound();

  public TypeParamDefFluent.BoundsNested<A> editLastBound();

  public TypeParamDefFluent.BoundsNested<A> editMatchingBound(Predicate<ClassRefBuilder> predicate);

  public interface BoundsNested<N> extends Nested<N>, ClassRefFluent<TypeParamDefFluent.BoundsNested<N>> {
    public N and();

    public N endBound();

  }

}
