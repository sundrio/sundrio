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
import java.util.Set;

public interface MethodFluent<A extends MethodFluent<A>> extends Fluent<A>,ModifierSupportFluent<A>{


    public A addToAnnotations(ClassRef... items);    public A removeFromAnnotations(ClassRef... items);    public Set<ClassRef> getAnnotations();    public A withAnnotations(Set<ClassRef> annotations);    public A withAnnotations(ClassRef... annotations);    public AnnotationsNested<A> addNewAnnotation();    public AnnotationsNested<A> addNewAnnotationLike(ClassRef item);    public A addToParameters(TypeParamDef... items);    public A removeFromParameters(TypeParamDef... items);    public Set<TypeParamDef> getParameters();    public A withParameters(Set<TypeParamDef> parameters);    public A withParameters(TypeParamDef... parameters);    public ParametersNested<A> addNewParameter();    public ParametersNested<A> addNewParameterLike(TypeParamDef item);    public String getName();    public A withName(String name);    public TypeRef getReturnType();    public A withReturnType(TypeRef returnType);    public A addToArguments(Property... items);    public A removeFromArguments(Property... items);    public List<Property> getArguments();    public A withArguments(List<Property> arguments);    public A withArguments(Property... arguments);    public ArgumentsNested<A> addNewArgument();    public ArgumentsNested<A> addNewArgumentLike(Property item);    public A addToExceptions(ClassRef... items);    public A removeFromExceptions(ClassRef... items);    public Set<ClassRef> getExceptions();    public A withExceptions(Set<ClassRef> exceptions);    public A withExceptions(ClassRef... exceptions);    public ExceptionsNested<A> addNewException();    public ExceptionsNested<A> addNewExceptionLike(ClassRef item);    public Block getBlock();    public A withBlock(Block block);    public BlockNested<A> withNewBlock();    public BlockNested<A> withNewBlockLike(Block item);    public BlockNested<A> editBlock();
    public interface AnnotationsNested<N> extends Nested<N>,ClassRefFluent<AnnotationsNested<N>>{
            public N endAnnotation();            public N and();        
}

    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<ParametersNested<N>>{
            public N endParameter();            public N and();        
}

    public interface ArgumentsNested<N> extends Nested<N>,PropertyFluent<ArgumentsNested<N>>{
            public N endArgument();            public N and();        
}

    public interface ExceptionsNested<N> extends Nested<N>,ClassRefFluent<ExceptionsNested<N>>{
            public N endException();            public N and();        
}

    public interface BlockNested<N> extends Nested<N>,BlockFluent<BlockNested<N>>{
            public N endBlock();            public N and();        
}


}
