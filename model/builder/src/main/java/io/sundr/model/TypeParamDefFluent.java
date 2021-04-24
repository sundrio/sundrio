package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.model.builder.Nested;

public interface TypeParamDefFluent<A extends TypeParamDefFluent<A>> extends AttributeSupportFluent<A> {

  public String getName();

  public A withName(String name);

  public Boolean hasName();

  public A withNewName(StringBuilder arg1);

  public A withNewName(int[] arg1, int arg2, int arg3);

  public A withNewName(char[] arg1);

  public A withNewName(StringBuffer arg1);

  public A withNewName(byte[] arg1, int arg2);

  public A withNewName(byte[] arg1);

  public A withNewName(char[] arg1, int arg2, int arg3);

  public A withNewName(byte[] arg1, int arg2, int arg3);

  public A withNewName(byte[] arg1, int arg2, int arg3, int arg4);

  public A withNewName(String arg1);

  public A addToBounds(int index, ClassRef item);

  public A setToBounds(int index, ClassRef item);

  public A addToBounds(ClassRef... items);

  public A addAllToBounds(Collection<ClassRef> items);

  public A removeFromBounds(ClassRef... items);

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

  public ClassRef buildBound(int index);

  public ClassRef buildFirstBound();

  public ClassRef buildLastBound();

  public ClassRef buildMatchingBound(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingBound(Predicate<ClassRefBuilder> predicate);

  public A withBounds(List<ClassRef> bounds);

  public A withBounds(ClassRef... bounds);

  public Boolean hasBounds();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> addNewBound();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> addNewBoundLike(ClassRef item);

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> setNewBoundLike(int index, ClassRef item);

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editBound(int index);

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editFirstBound();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editLastBound();

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editMatchingBound(Predicate<ClassRefBuilder> predicate);

  public interface BoundsNested<N>
      extends io.sundr.model.builder.Nested<N>, ClassRefFluent<io.sundr.model.TypeParamDefFluent.BoundsNested<N>> {

    public N and();

    public N endBound();
  }

}
