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

public interface WildcardRefFluent<A extends WildcardRefFluent<A>> extends TypeRefFluent<A>{


    public A addToBounds(int index, TypeRef item);
    public A setToBounds(int index, TypeRef item);
    public A addToBounds(TypeRef... items);
    public A addAllToBounds(Collection<TypeRef> items);
    public A removeFromBounds(TypeRef... items);
    public A removeAllFromBounds(Collection<TypeRef> items);
    
/**
 * This method has been deprecated, please use method buildBounds instead.
 */
@Deprecated public List<TypeRef> getBounds();
    public List<TypeRef> buildBounds();
    public TypeRef buildBound(int index);
    public TypeRef buildFirstBound();
    public TypeRef buildLastBound();
    public TypeRef buildMatchingBound(Predicate<Builder<? extends TypeRef>> predicate);
    public A withBounds(List<TypeRef> bounds);
    public A withBounds(TypeRef... bounds);
    public Boolean hasBounds();
    public A addToVoidRefBounds(int index, VoidRef item);
    public A setToVoidRefBounds(int index, VoidRef item);
    public A addToVoidRefBounds(VoidRef... items);
    public A addAllToVoidRefBounds(Collection<VoidRef> items);
    public A removeFromVoidRefBounds(VoidRef... items);
    public A removeAllFromVoidRefBounds(Collection<VoidRef> items);
    public VoidRefBoundsNested<A> addNewVoidRefBound();
    public VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item);
    public A addToWildcardRefBounds(int index, WildcardRef item);
    public A setToWildcardRefBounds(int index, WildcardRef item);
    public A addToWildcardRefBounds(WildcardRef... items);
    public A addAllToWildcardRefBounds(Collection<WildcardRef> items);
    public A removeFromWildcardRefBounds(WildcardRef... items);
    public A removeAllFromWildcardRefBounds(Collection<WildcardRef> items);
    public WildcardRefBoundsNested<A> addNewWildcardRefBound();
    public WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item);
    public A addToPrimitiveRefBounds(int index, PrimitiveRef item);
    public A setToPrimitiveRefBounds(int index, PrimitiveRef item);
    public A addToPrimitiveRefBounds(PrimitiveRef... items);
    public A addAllToPrimitiveRefBounds(Collection<PrimitiveRef> items);
    public A removeFromPrimitiveRefBounds(PrimitiveRef... items);
    public A removeAllFromPrimitiveRefBounds(Collection<PrimitiveRef> items);
    public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound();
    public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item);
    public A addToTypeParamRefBounds(int index, TypeParamRef item);
    public A setToTypeParamRefBounds(int index, TypeParamRef item);
    public A addToTypeParamRefBounds(TypeParamRef... items);
    public A addAllToTypeParamRefBounds(Collection<TypeParamRef> items);
    public A removeFromTypeParamRefBounds(TypeParamRef... items);
    public A removeAllFromTypeParamRefBounds(Collection<TypeParamRef> items);
    public TypeParamRefBoundsNested<A> addNewTypeParamRefBound();
    public TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item);
    public A addToClassRefBounds(int index, ClassRef item);
    public A setToClassRefBounds(int index, ClassRef item);
    public A addToClassRefBounds(ClassRef... items);
    public A addAllToClassRefBounds(Collection<ClassRef> items);
    public A removeFromClassRefBounds(ClassRef... items);
    public A removeAllFromClassRefBounds(Collection<ClassRef> items);
    public ClassRefBoundsNested<A> addNewClassRefBound();
    public ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item);

    public interface VoidRefBoundsNested<N> extends Nested<N>,VoidRefFluent<VoidRefBoundsNested<N>>{


    public N and();    public N endVoidRefBound();
}
    public interface WildcardRefBoundsNested<N> extends Nested<N>,WildcardRefFluent<WildcardRefBoundsNested<N>>{


    public N and();    public N endWildcardRefBound();
}
    public interface PrimitiveRefBoundsNested<N> extends Nested<N>,PrimitiveRefFluent<PrimitiveRefBoundsNested<N>>{


    public N and();    public N endPrimitiveRefBound();
}
    public interface TypeParamRefBoundsNested<N> extends Nested<N>,TypeParamRefFluent<TypeParamRefBoundsNested<N>>{


    public N and();    public N endTypeParamRefBound();
}
    public interface ClassRefBoundsNested<N> extends Nested<N>,ClassRefFluent<ClassRefBoundsNested<N>>{

        
    public N and();    public N endClassRefBound();
}


}
