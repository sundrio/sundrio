/*
 *      Copyright 2016 The original authors.
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

import io.sundr.builder.Builder;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;

public interface TypeParamDefFluent<A extends TypeParamDefFluent<A>> extends AttributeSupportFluent<A>{


    public String getName();
    public A withName(String name);
    public Boolean hasName();
    public A addToBounds(int index, ClassRef item);
    public A setToBounds(int index, ClassRef item);
    public A addToBounds(ClassRef... items);
    public A addAllToBounds(Collection<ClassRef> items);
    public A removeFromBounds(ClassRef... items);
    public A removeAllFromBounds(Collection<ClassRef> items);
    
/**
 * This method has been deprecated, please use method buildBounds instead.
 */
@Deprecated public List<ClassRef> getBounds();
    public List<ClassRef> buildBounds();
    public ClassRef buildBound(int index);
    public ClassRef buildFirstBound();
    public ClassRef buildLastBound();
    public ClassRef buildMatchingBound(Predicate<Builder<? extends ClassRef>> predicate);
    public A withBounds(List<ClassRef> bounds);
    public A withBounds(ClassRef... bounds);
    public Boolean hasBounds();
    public BoundsNested<A> addNewBound();
    public BoundsNested<A> addNewBoundLike(ClassRef item);

    public interface BoundsNested<N> extends Nested<N>,ClassRefFluent<BoundsNested<N>>{

        
    public N and();    public N endBound();
}


}
