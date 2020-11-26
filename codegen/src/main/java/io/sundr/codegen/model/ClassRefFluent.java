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
import io.sundr.builder.VisitableBuilder;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface ClassRefFluent<A extends ClassRefFluent<A>> extends TypeRefFluent<A>{


    
/**
 * This method has been deprecated, please use method buildDefinition instead.
 * @return The buildable object.
 */
@Deprecated public TypeDef getDefinition();
    public TypeDef buildDefinition();
    public A withDefinition(TypeDef definition);
    public Boolean hasDefinition();
    public ClassRefFluent.DefinitionNested<A> withNewDefinition();
    public ClassRefFluent.DefinitionNested<A> withNewDefinitionLike(TypeDef item);
    public ClassRefFluent.DefinitionNested<A> editDefinition();
    public ClassRefFluent.DefinitionNested<A> editOrNewDefinition();
    public ClassRefFluent.DefinitionNested<A> editOrNewDefinitionLike(TypeDef item);
    public String getFullyQualifiedName();
    public A withFullyQualifiedName(String fullyQualifiedName);
    public Boolean hasFullyQualifiedName();
    public A withNewFullyQualifiedName(String arg1);
    public A withNewFullyQualifiedName(StringBuilder arg1);
    public A withNewFullyQualifiedName(StringBuffer arg1);
    public int getDimensions();
    public A withDimensions(int dimensions);
    public Boolean hasDimensions();
    public A addToArguments(VisitableBuilder<? extends TypeRef, ?> builder);
    public A addToArguments(int index, VisitableBuilder<? extends TypeRef, ?> builder);
    public A addToArguments(int index, TypeRef item);
    public A setToArguments(int index, TypeRef item);
    public A addToArguments(TypeRef... items);
    public A addAllToArguments(Collection<TypeRef> items);
    public A removeFromArguments(VisitableBuilder<? extends TypeRef, ?> builder);
    public A removeFromArguments(TypeRef... items);
    public A removeAllFromArguments(Collection<TypeRef> items);

/**
 * This method has been deprecated, please use method buildArguments instead.
 * @return The buildable object.
 */
@Deprecated public List<TypeRef> getArguments();
    public List<TypeRef> buildArguments();
    public TypeRef buildArgument(int index);
    public TypeRef buildFirstArgument();
    public TypeRef buildLastArgument();
    public TypeRef buildMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);
    public Boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);
    public A withArguments(List<TypeRef> arguments);
    public A withArguments(TypeRef... arguments);
    public Boolean hasArguments();
    public A addToPrimitiveRefArguments(int index, PrimitiveRef item);
    public A setToPrimitiveRefArguments(int index, PrimitiveRef item);
    public A addToPrimitiveRefArguments(PrimitiveRef... items);
    public A addAllToPrimitiveRefArguments(Collection<PrimitiveRef> items);
    public A removeFromPrimitiveRefArguments(PrimitiveRef... items);
    public A removeAllFromPrimitiveRefArguments(Collection<PrimitiveRef> items);
    public ClassRefFluent.PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(int index, PrimitiveRef item);
    public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();
    public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item);
    public A addToVoidRefArguments(int index, VoidRef item);
    public A setToVoidRefArguments(int index, VoidRef item);
    public A addToVoidRefArguments(VoidRef... items);
    public A addAllToVoidRefArguments(Collection<VoidRef> items);
    public A removeFromVoidRefArguments(VoidRef... items);
    public A removeAllFromVoidRefArguments(Collection<VoidRef> items);
    public ClassRefFluent.VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(int index, VoidRef item);
    public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument();
    public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item);
    public A addToWildcardRefArguments(int index, WildcardRef item);
    public A setToWildcardRefArguments(int index, WildcardRef item);
    public A addToWildcardRefArguments(WildcardRef... items);
    public A addAllToWildcardRefArguments(Collection<WildcardRef> items);
    public A removeFromWildcardRefArguments(WildcardRef... items);
    public A removeAllFromWildcardRefArguments(Collection<WildcardRef> items);
    public ClassRefFluent.WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(int index, WildcardRef item);
    public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument();
    public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item);
    public A addToClassRefArguments(int index, ClassRef item);
    public A setToClassRefArguments(int index, ClassRef item);
    public A addToClassRefArguments(ClassRef... items);
    public A addAllToClassRefArguments(Collection<ClassRef> items);
    public A removeFromClassRefArguments(ClassRef... items);
    public A removeAllFromClassRefArguments(Collection<ClassRef> items);
    public ClassRefFluent.ClassRefArgumentsNested<A> setNewClassRefArgumentLike(int index, ClassRef item);
    public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument();
    public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item);
    public A addToTypeParamRefArguments(int index, TypeParamRef item);
    public A setToTypeParamRefArguments(int index, TypeParamRef item);
    public A addToTypeParamRefArguments(TypeParamRef... items);
    public A addAllToTypeParamRefArguments(Collection<TypeParamRef> items);
    public A removeFromTypeParamRefArguments(TypeParamRef... items);
    public A removeAllFromTypeParamRefArguments(Collection<TypeParamRef> items);
    public ClassRefFluent.TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(int index, TypeParamRef item);
    public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();
    public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);

    public interface DefinitionNested<N> extends Nested<N>,TypeDefFluent<ClassRefFluent.DefinitionNested<N>>{


    public N and();    public N endDefinition();
}
    public interface PrimitiveRefArgumentsNested<N> extends Nested<N>,PrimitiveRefFluent<ClassRefFluent.PrimitiveRefArgumentsNested<N>>{


    public N and();    public N endPrimitiveRefArgument();
}
    public interface VoidRefArgumentsNested<N> extends Nested<N>,VoidRefFluent<ClassRefFluent.VoidRefArgumentsNested<N>>{


    public N and();    public N endVoidRefArgument();
}
    public interface WildcardRefArgumentsNested<N> extends Nested<N>,WildcardRefFluent<ClassRefFluent.WildcardRefArgumentsNested<N>>{


    public N and();    public N endWildcardRefArgument();
}
    public interface ClassRefArgumentsNested<N> extends Nested<N>,ClassRefFluent<ClassRefFluent.ClassRefArgumentsNested<N>>{


    public N and();    public N endClassRefArgument();
}
    public interface TypeParamRefArgumentsNested<N> extends Nested<N>,TypeParamRefFluent<ClassRefFluent.TypeParamRefArgumentsNested<N>>{

        
    public N and();    public N endTypeParamRefArgument();
}


}
