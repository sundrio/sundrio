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

import java.util.Set;

public interface MethodFluent<A extends MethodFluent<A>> extends Fluent<A>, ModifierSupportFluent<A> {


    public A addToAnnotations(TypeRef... items);

    public A removeFromAnnotations(TypeRef... items);

    public Set<TypeRef> getAnnotations();

    public A withAnnotations(Set<TypeRef> annotations);

    public A withAnnotations(TypeRef... annotations);

    public A addToParameters(TypeParamDef... items);

    public A removeFromParameters(TypeParamDef... items);

    public Set<TypeParamDef> getParameters();

    public A withParameters(Set<TypeParamDef> parameters);

    public A withParameters(TypeParamDef... parameters);

    public String getName();

    public A withName(String name);

    public TypeRef getReturnType();

    public A withReturnType(TypeRef returnType);

    public A withArguments(Property... arguments);

    public Property[] getArguments();

    public A addToArguments(Property... items);

    public A removeFromArguments(Property... items);

    public ArgumentsNested<A> addNewArgument();

    public ArgumentsNested<A> addNewArgumentLike(Property item);

    public A addToExceptions(TypeRef... items);

    public A removeFromExceptions(TypeRef... items);

    public Set<TypeRef> getExceptions();

    public A withExceptions(Set<TypeRef> exceptions);

    public A withExceptions(TypeRef... exceptions);

    public Block getBlock();

    public A withBlock(Block block);

    public interface ArgumentsNested<N> extends Nested<N>, PropertyFluent<ArgumentsNested<N>> {
        public N endArgument();

        public N and();
    }


}
