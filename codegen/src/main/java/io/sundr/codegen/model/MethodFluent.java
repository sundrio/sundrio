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

import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;

import java.util.Collection;
import java.util.List;

public interface MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A>{


    public A addToComments(int index, String item);
    public A setToComments(int index, String item);
    public A addToComments(String... items);
    public A addAllToComments(Collection<String> items);
    public A removeFromComments(String... items);
    public A removeAllFromComments(Collection<String> items);
    public List<String> getComments();
    public String getComment(int index);
    public String getFirstComment();
    public String getLastComment();
    public String getMatchingComment(Predicate<String> predicate);
    public Boolean hasMatchingComment(Predicate<String> predicate);
    public A withComments(List<String> comments);
    public A withComments(String... comments);
    public Boolean hasComments();
    public A addNewComment(String arg1);
    public A addNewComment(StringBuilder arg1);
    public A addNewComment(StringBuffer arg1);
    public A addToAnnotations(int index, AnnotationRef item);
    public A setToAnnotations(int index, AnnotationRef item);
    public A addToAnnotations(AnnotationRef... items);
    public A addAllToAnnotations(Collection<AnnotationRef> items);
    public A removeFromAnnotations(AnnotationRef... items);
    public A removeAllFromAnnotations(Collection<AnnotationRef> items);

/**
 * This method has been deprecated, please use method buildAnnotations instead.
 * @return The buildable object.
 */
@Deprecated public List<AnnotationRef> getAnnotations();
    public List<AnnotationRef> buildAnnotations();
    public AnnotationRef buildAnnotation(int index);
    public AnnotationRef buildFirstAnnotation();
    public AnnotationRef buildLastAnnotation();
    public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);
    public Boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public Boolean hasAnnotations();
    public MethodFluent.AnnotationsNested<A> addNewAnnotation();
    public MethodFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
    public MethodFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item);
    public MethodFluent.AnnotationsNested<A> editAnnotation(int index);
    public MethodFluent.AnnotationsNested<A> editFirstAnnotation();
    public MethodFluent.AnnotationsNested<A> editLastAnnotation();
    public MethodFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);
    public A addToParameters(int index, TypeParamDef item);
    public A setToParameters(int index, TypeParamDef item);
    public A addToParameters(TypeParamDef... items);
    public A addAllToParameters(Collection<TypeParamDef> items);
    public A removeFromParameters(TypeParamDef... items);
    public A removeAllFromParameters(Collection<TypeParamDef> items);

/**
 * This method has been deprecated, please use method buildParameters instead.
 * @return The buildable object.
 */
@Deprecated public List<TypeParamDef> getParameters();
    public List<TypeParamDef> buildParameters();
    public TypeParamDef buildParameter(int index);
    public TypeParamDef buildFirstParameter();
    public TypeParamDef buildLastParameter();
    public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate);
    public Boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate);
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public Boolean hasParameters();
    public MethodFluent.ParametersNested<A> addNewParameter();
    public MethodFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public MethodFluent.ParametersNested<A> setNewParameterLike(int index, TypeParamDef item);
    public MethodFluent.ParametersNested<A> editParameter(int index);
    public MethodFluent.ParametersNested<A> editFirstParameter();
    public MethodFluent.ParametersNested<A> editLastParameter();
    public MethodFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate);
    public String getName();
    public A withName(String name);
    public Boolean hasName();
    public A withNewName(String arg1);
    public A withNewName(StringBuilder arg1);
    public A withNewName(StringBuffer arg1);

/**
 * This method has been deprecated, please use method buildReturnType instead.
 * @return The buildable object.
 */
@Deprecated public TypeRef getReturnType();
    public TypeRef buildReturnType();
    public A withReturnType(TypeRef returnType);
    public Boolean hasReturnType();
    public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType);
    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType();
    public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item);
    public A withVoidRefReturnType(VoidRef voidRefReturnType);
    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType();
    public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item);
    public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType);
    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType();
    public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item);
    public A withClassRefReturnType(ClassRef classRefReturnType);
    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType();
    public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item);
    public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType);
    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType();
    public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item);
    public A addToArguments(int index, Property item);
    public A setToArguments(int index, Property item);
    public A addToArguments(Property... items);
    public A addAllToArguments(Collection<Property> items);
    public A removeFromArguments(Property... items);
    public A removeAllFromArguments(Collection<Property> items);

/**
 * This method has been deprecated, please use method buildArguments instead.
 * @return The buildable object.
 */
@Deprecated public List<Property> getArguments();
    public List<Property> buildArguments();
    public Property buildArgument(int index);
    public Property buildFirstArgument();
    public Property buildLastArgument();
    public Property buildMatchingArgument(Predicate<PropertyBuilder> predicate);
    public Boolean hasMatchingArgument(Predicate<PropertyBuilder> predicate);
    public A withArguments(List<Property> arguments);
    public A withArguments(Property... arguments);
    public Boolean hasArguments();
    public MethodFluent.ArgumentsNested<A> addNewArgument();
    public MethodFluent.ArgumentsNested<A> addNewArgumentLike(Property item);
    public MethodFluent.ArgumentsNested<A> setNewArgumentLike(int index, Property item);
    public MethodFluent.ArgumentsNested<A> editArgument(int index);
    public MethodFluent.ArgumentsNested<A> editFirstArgument();
    public MethodFluent.ArgumentsNested<A> editLastArgument();
    public MethodFluent.ArgumentsNested<A> editMatchingArgument(Predicate<PropertyBuilder> predicate);
    public boolean isVarArgPreferred();
    public A withVarArgPreferred(boolean varArgPreferred);
    public Boolean hasVarArgPreferred();
    public A addToExceptions(int index, ClassRef item);
    public A setToExceptions(int index, ClassRef item);
    public A addToExceptions(ClassRef... items);
    public A addAllToExceptions(Collection<ClassRef> items);
    public A removeFromExceptions(ClassRef... items);
    public A removeAllFromExceptions(Collection<ClassRef> items);

/**
 * This method has been deprecated, please use method buildExceptions instead.
 * @return The buildable object.
 */
@Deprecated public List<ClassRef> getExceptions();
    public List<ClassRef> buildExceptions();
    public ClassRef buildException(int index);
    public ClassRef buildFirstException();
    public ClassRef buildLastException();
    public ClassRef buildMatchingException(Predicate<ClassRefBuilder> predicate);
    public Boolean hasMatchingException(Predicate<ClassRefBuilder> predicate);
    public A withExceptions(List<ClassRef> exceptions);
    public A withExceptions(ClassRef... exceptions);
    public Boolean hasExceptions();
    public MethodFluent.ExceptionsNested<A> addNewException();
    public MethodFluent.ExceptionsNested<A> addNewExceptionLike(ClassRef item);
    public MethodFluent.ExceptionsNested<A> setNewExceptionLike(int index, ClassRef item);
    public MethodFluent.ExceptionsNested<A> editException(int index);
    public MethodFluent.ExceptionsNested<A> editFirstException();
    public MethodFluent.ExceptionsNested<A> editLastException();
    public MethodFluent.ExceptionsNested<A> editMatchingException(Predicate<ClassRefBuilder> predicate);

/**
 * This method has been deprecated, please use method buildBlock instead.
 * @return The buildable object.
 */
@Deprecated public Block getBlock();
    public Block buildBlock();
    public A withBlock(Block block);
    public Boolean hasBlock();
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
    public interface PrimitiveRefReturnTypeNested<N> extends Nested<N>,PrimitiveRefFluent<MethodFluent.PrimitiveRefReturnTypeNested<N>>{


    public N and();    public N endPrimitiveRefReturnType();
}
    public interface VoidRefReturnTypeNested<N> extends Nested<N>,VoidRefFluent<MethodFluent.VoidRefReturnTypeNested<N>>{


    public N and();    public N endVoidRefReturnType();
}
    public interface WildcardRefReturnTypeNested<N> extends Nested<N>,WildcardRefFluent<MethodFluent.WildcardRefReturnTypeNested<N>>{


    public N and();    public N endWildcardRefReturnType();
}
    public interface ClassRefReturnTypeNested<N> extends Nested<N>,ClassRefFluent<MethodFluent.ClassRefReturnTypeNested<N>>{


    public N and();    public N endClassRefReturnType();
}
    public interface TypeParamRefReturnTypeNested<N> extends Nested<N>,TypeParamRefFluent<MethodFluent.TypeParamRefReturnTypeNested<N>>{


    public N and();    public N endTypeParamRefReturnType();
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
