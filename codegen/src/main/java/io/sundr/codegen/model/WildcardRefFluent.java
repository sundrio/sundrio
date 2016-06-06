/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.Nested;

public interface WildcardRefFluent<A extends WildcardRefFluent<A>> extends AbstractTypeRefFluent<A>{


    public A addToBounds(TypeRef... items);    public A removeFromBounds(TypeRef... items);    public java.util.List<TypeRef> getBounds();    public A withBounds(java.util.List<TypeRef> bounds);    public A withBounds(TypeRef[] bounds);    public A addToVoidRefBounds(VoidRef... items);    public A removeFromVoidRefBounds(VoidRef... items);    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound();    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item);    public A addToWildcardRefBounds(WildcardRef... items);    public A removeFromWildcardRefBounds(WildcardRef... items);    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound();    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item);    public A addToPrimitiveRefBounds(PrimitiveRef... items);    public A removeFromPrimitiveRefBounds(PrimitiveRef... items);    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound();    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item);    public A addToTypeParamRefBounds(TypeParamRef... items);    public A removeFromTypeParamRefBounds(TypeParamRef... items);    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound();    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item);    public A addToClassRefBounds(ClassRef... items);    public A removeFromClassRefBounds(ClassRef... items);    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound();    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item);
    public interface VoidRefBoundsNested<N> extends Nested<N>,VoidRefFluent<WildcardRefFluent.VoidRefBoundsNested<N>>{
            public N endVoidRefBound();            public N and();
}

    public interface WildcardRefBoundsNested<N> extends Nested<N>,WildcardRefFluent<WildcardRefFluent.WildcardRefBoundsNested<N>>{
            public N endWildcardRefBound();            public N and();
}

    public interface PrimitiveRefBoundsNested<N> extends Nested<N>,PrimitiveRefFluent<WildcardRefFluent.PrimitiveRefBoundsNested<N>>{
            public N endPrimitiveRefBound();            public N and();
}

    public interface TypeParamRefBoundsNested<N> extends Nested<N>,TypeParamRefFluent<WildcardRefFluent.TypeParamRefBoundsNested<N>>{
            public N endTypeParamRefBound();            public N and();
}

    interface ClassRefBoundsNested<N> extends Nested<N>,ClassRefFluent<WildcardRefFluent.ClassRefBoundsNested<N>>{
            public N endClassRefBound();            public N and();        
}


}
