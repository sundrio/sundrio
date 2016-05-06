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

public interface TypeRefFluent<A extends TypeRefFluent<A>> extends Fluent<A>, AttributeSupportFluent<A> {


    public TypeDef getDefinition();

    public A withDefinition(TypeDef definition);

    public DefinitionNested<A> withNewDefinition();

    public DefinitionNested<A> withNewDefinitionLike(TypeDef item);

    public DefinitionNested<A> editDefinition();

    public int getDimensions();

    public A withDimensions(int dimensions);

    public A withArguments(ParameterReference... arguments);

    public ParameterReference[] getArguments();

    public A addToArguments(ParameterReference... items);

    public A removeFromArguments(ParameterReference... items);

    public A addToTypeParamRefArguments(TypeParamRef... items);

    public A removeFromTypeParamRefArguments(TypeParamRef... items);

    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();

    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);

    public A addToTypeRefArguments(TypeRef... items);

    public A removeFromTypeRefArguments(TypeRef... items);

    public TypeRefArgumentsNested<A> addNewTypeRefArgument();

    public TypeRefArgumentsNested<A> addNewTypeRefArgumentLike(TypeRef item);

    public interface DefinitionNested<N> extends Nested<N>, TypeDefFluent<DefinitionNested<N>> {
        public N endDefinition();

        public N and();
    }

    public interface TypeParamRefArgumentsNested<N> extends Nested<N>, TypeParamRefFluent<TypeParamRefArgumentsNested<N>> {
        public N endTypeParamRefArgument();

        public N and();
    }

    public interface TypeRefArgumentsNested<N> extends Nested<N>, TypeRefFluent<TypeRefArgumentsNested<N>> {
        public N endTypeRefArgument();

        public N and();
    }


}
