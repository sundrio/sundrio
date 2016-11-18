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

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

import java.util.List;

public interface TypeParamDefFluent<A extends TypeParamDefFluent<A>> extends Fluent<A>, AttributeSupportFluent<A> {


    public String getName();

    public A withName(String name);

    public A addToBounds(ClassRef... items);

    public A removeFromBounds(ClassRef... items);

    public List<ClassRef> getBounds();

    public A withBounds(List<ClassRef> bounds);

    public A withBounds(ClassRef... bounds);

    public BoundsNested<A> addNewBound();

    public BoundsNested<A> addNewBoundLike(ClassRef item);

    public interface BoundsNested<N> extends Nested<N>, ClassRefFluent<BoundsNested<N>> {
        public N endBound();

        public N and();
    }


}
