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

import java.util.List;

import io.sundr.builder.Nested;

public interface MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A>{


    public A addToComments(String... items);
    public A removeFromComments(String... items);
    public List<String> getComments();
    public A withComments(List<String> comments);
    public A withComments(String... comments);
    public A addToAnnotations(AnnotationRef... items);
    public A removeFromAnnotations(AnnotationRef... items);
    @Deprecated public List<AnnotationRef> getAnnotations();
    public List<AnnotationRef> buildAnnotations();
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public MethodFluent.AnnotationsNested<A> addNewAnnotation();
    public MethodFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
    public A addToParameters(TypeParamDef... items);
    public A removeFromParameters(TypeParamDef... items);
    @Deprecated public List<TypeParamDef> getParameters();
    public List<TypeParamDef> buildParameters();
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public MethodFluent.ParametersNested<A> addNewParameter();
    public MethodFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public String getName();
    public A withName(String name);
    @Deprecated public TypeRef getReturnType();
    public TypeRef buildReturnType();
    public A withReturnType(TypeRef returnType);
    public A withVoidRefReturnType(VoidRef voidRefReturnType);
    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType();
    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item);
    public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType);
    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType();
    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item);
    public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType);
    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType();
    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item);
    public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType);
    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType();
    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item);
    public A withClassRefReturnType(ClassRef classRefReturnType);
    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType();
    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item);
    public A addToArguments(Property... items);
    public A removeFromArguments(Property... items);
    @Deprecated public List<Property> getArguments();
    public List<Property> buildArguments();
    public A withArguments(List<Property> arguments);
    public A withArguments(Property... arguments);
    public MethodFluent.ArgumentsNested<A> addNewArgument();
    public MethodFluent.ArgumentsNested<A> addNewArgumentLike(Property item);
    public boolean isVarArgPreferred();
    public A withVarArgPreferred(boolean varArgPreferred);
    public A addToExceptions(ClassRef... items);
    public A removeFromExceptions(ClassRef... items);
    @Deprecated public List<ClassRef> getExceptions();
    public List<ClassRef> buildExceptions();
    public A withExceptions(List<ClassRef> exceptions);
    public A withExceptions(ClassRef... exceptions);
    public MethodFluent.ExceptionsNested<A> addNewException();
    public MethodFluent.ExceptionsNested<A> addNewExceptionLike(ClassRef item);
    @Deprecated public Block getBlock();
    public Block buildBlock();
    public A withBlock(Block block);
    public MethodFluent.BlockNested<A> withNewBlock();
    public MethodFluent.BlockNested<A> withNewBlockLike(Block item);
    public MethodFluent.BlockNested<A> editBlock();
    public MethodFluent.BlockNested<A> editOrNewBlock();
    public MethodFluent.BlockNested<A> editOrNewBlockLike(Block item);

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<MethodFluent.AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<MethodFluent.ParametersNested<N>>{


    public N and();    public N endParameter();
}
    public interface VoidRefReturnTypeNested<N> extends Nested<N>,VoidRefFluent<MethodFluent.VoidRefReturnTypeNested<N>>{


    public N and();    public N endVoidRefReturnType();
}
    public interface WildcardRefReturnTypeNested<N> extends Nested<N>,WildcardRefFluent<MethodFluent.WildcardRefReturnTypeNested<N>>{


    public N and();    public N endWildcardRefReturnType();
}
    public interface PrimitiveRefReturnTypeNested<N> extends Nested<N>,PrimitiveRefFluent<MethodFluent.PrimitiveRefReturnTypeNested<N>>{


    public N and();    public N endPrimitiveRefReturnType();
}
    public interface TypeParamRefReturnTypeNested<N> extends Nested<N>,TypeParamRefFluent<MethodFluent.TypeParamRefReturnTypeNested<N>>{


    public N and();    public N endTypeParamRefReturnType();
}
    public interface ClassRefReturnTypeNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.ClassRefReturnTypeNested<N>>{


    public N and();    public N endClassRefReturnType();
}
    public interface ArgumentsNested<N> extends Nested<N>,PropertyFluent<MethodFluent.ArgumentsNested<N>>{


    public N and();    public N endArgument();
}
    public interface ExceptionsNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.ExceptionsNested<N>>{


    public N and();    public N endException();
}
    public interface BlockNested<N> extends Nested<N>,BlockFluent<MethodFluent.BlockNested<N>>{

        
    public N and();    public N endBlock();
}


}
