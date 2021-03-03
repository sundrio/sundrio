/*
 *      Copyright 2019 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.codegen.model;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

public interface TypeParamDefFluent<A extends TypeParamDefFluent<A>> extends AttributeSupportFluent<A> {

  public String getName();

  public A withName(String name);

  public Boolean hasName();

  public A withNewName(String arg1);

  public A withNewName(StringBuilder arg1);

  public A withNewName(StringBuffer arg1);

  public A addToBounds(int index, ClassRef item);

  public A setToBounds(int index, ClassRef item);

  public A addToBounds(ClassRef... items);

  public A addAllToBounds(Collection<ClassRef> items);

  public A removeFromBounds(ClassRef... items);

  public A removeAllFromBounds(Collection<ClassRef> items);

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

  public TypeParamDefFluent.BoundsNested<A> addNewBound();

  public TypeParamDefFluent.BoundsNested<A> addNewBoundLike(ClassRef item);

  public TypeParamDefFluent.BoundsNested<A> setNewBoundLike(int index, ClassRef item);

  public TypeParamDefFluent.BoundsNested<A> editBound(int index);

  public TypeParamDefFluent.BoundsNested<A> editFirstBound();

  public TypeParamDefFluent.BoundsNested<A> editLastBound();

  public TypeParamDefFluent.BoundsNested<A> editMatchingBound(Predicate<ClassRefBuilder> predicate);

  public interface BoundsNested<N> extends Nested<N>, ClassRefFluent<TypeParamDefFluent.BoundsNested<N>> {

    public N and();

    public N endBound();
  }

}
