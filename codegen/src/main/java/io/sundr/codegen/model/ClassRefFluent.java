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

public interface ClassRefFluent<A extends ClassRefFluent<A>> extends Fluent<A>,AbstractTypeRefFluent<A>{


    public TypeDef getDefinition();    public A withDefinition(TypeDef definition);    public DefinitionNested<A> withNewDefinition();    public DefinitionNested<A> withNewDefinitionLike(TypeDef item);    public DefinitionNested<A> editDefinition();    public int getDimensions();    public A withDimensions(int dimensions);    public A addToArguments(TypeRef... items);    public A removeFromArguments(TypeRef... items);    public List<TypeRef> getArguments();    public A withArguments(List<TypeRef> arguments);    public A withArguments(TypeRef... arguments);    public A addToWildcardRefArguments(WildcardRef... items);    public A removeFromWildcardRefArguments(WildcardRef... items);    public WildcardRefArgumentsNested<A> addNewWildcardRefArgument();    public WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item);    public A addToPrimitiveRefArguments(PrimitiveRef... items);    public A removeFromPrimitiveRefArguments(PrimitiveRef... items);    public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();    public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item);    public A addToTypeParamRefArguments(TypeParamRef... items);    public A removeFromTypeParamRefArguments(TypeParamRef... items);    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);    public A addToClassRefArguments(ClassRef... items);    public A removeFromClassRefArguments(ClassRef... items);    public ClassRefArgumentsNested<A> addNewClassRefArgument();    public ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item);    public A addToVoidRefArguments(VoidRef... items);    public A removeFromVoidRefArguments(VoidRef... items);    public VoidRefArgumentsNested<A> addNewVoidRefArgument();    public VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item);
    public interface DefinitionNested<N> extends Nested<N>,TypeDefFluent<DefinitionNested<N>>{
            public N endDefinition();            public N and();        
}

    public interface WildcardRefArgumentsNested<N> extends Nested<N>,WildcardRefFluent<WildcardRefArgumentsNested<N>>{
            public N endWildcardRefArgument();            public N and();        
}

    public interface PrimitiveRefArgumentsNested<N> extends Nested<N>,PrimitiveRefFluent<PrimitiveRefArgumentsNested<N>>{
            public N endPrimitiveRefArgument();            public N and();        
}

    public interface TypeParamRefArgumentsNested<N> extends Nested<N>,TypeParamRefFluent<TypeParamRefArgumentsNested<N>>{
            public N endTypeParamRefArgument();            public N and();        
}

    public interface ClassRefArgumentsNested<N> extends Nested<N>,ClassRefFluent<ClassRefArgumentsNested<N>>{
            public N endClassRefArgument();            public N and();        
}

    public interface VoidRefArgumentsNested<N> extends Nested<N>,VoidRefFluent<VoidRefArgumentsNested<N>>{
            public N endVoidRefArgument();            public N and();        
}


}
