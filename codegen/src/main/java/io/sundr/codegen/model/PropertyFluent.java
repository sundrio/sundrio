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

public interface PropertyFluent<A extends PropertyFluent<A>> extends ModifierSupportFluent<A>{


    public A addToAnnotations(int index, AnnotationRef item);
    public A setToAnnotations(int index, AnnotationRef item);
    public A addToAnnotations(AnnotationRef... items);
    public A addAllToAnnotations(Collection<AnnotationRef> items);
    public A removeFromAnnotations(AnnotationRef... items);
    public A removeAllFromAnnotations(Collection<AnnotationRef> items);

    /**
     * This method has been deprecated, please use method buildAnnotations instead.
     */
    @Deprecated
    public List<AnnotationRef> getAnnotations();
    public List<AnnotationRef> buildAnnotations();
    public AnnotationRef buildAnnotation(int index);
    public AnnotationRef buildFirstAnnotation();
    public AnnotationRef buildLastAnnotation();
    public AnnotationRef buildMatchingAnnotation(Predicate<Builder<? extends AnnotationRef>> predicate);
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public Boolean hasAnnotations();
    public AnnotationsNested<A> addNewAnnotation();
    public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

    /**
     * This method has been deprecated, please use method buildTypeRef instead.
     */
    @Deprecated
    public TypeRef getTypeRef();
    public TypeRef buildTypeRef();
    public A withTypeRef(TypeRef typeRef);
    public Boolean hasTypeRef();
    public A withVoidRefTypeRef(VoidRef voidRefTypeRef);
    public VoidRefTypeRefNested<A> withNewVoidRefTypeRef();
    public VoidRefTypeRefNested<A> withNewVoidRefTypeRefLike(VoidRef item);
    public A withWildcardRefTypeRef(WildcardRef wildcardRefTypeRef);
    public WildcardRefTypeRefNested<A> withNewWildcardRefTypeRef();
    public WildcardRefTypeRefNested<A> withNewWildcardRefTypeRefLike(WildcardRef item);
    public A withPrimitiveRefTypeRef(PrimitiveRef primitiveRefTypeRef);
    public PrimitiveRefTypeRefNested<A> withNewPrimitiveRefTypeRef();
    public PrimitiveRefTypeRefNested<A> withNewPrimitiveRefTypeRefLike(PrimitiveRef item);
    public A withTypeParamRefTypeRef(TypeParamRef typeParamRefTypeRef);
    public TypeParamRefTypeRefNested<A> withNewTypeParamRefTypeRef();
    public TypeParamRefTypeRefNested<A> withNewTypeParamRefTypeRefLike(TypeParamRef item);
    public A withClassRefTypeRef(ClassRef classRefTypeRef);
    public ClassRefTypeRefNested<A> withNewClassRefTypeRef();
    public ClassRefTypeRefNested<A> withNewClassRefTypeRefLike(ClassRef item);
    public String getName();
    public A withName(String name);
    public Boolean hasName();

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface VoidRefTypeRefNested<N> extends Nested<N>,VoidRefFluent<VoidRefTypeRefNested<N>>{


    public N and();    public N endVoidRefTypeRef();
}
    public interface WildcardRefTypeRefNested<N> extends Nested<N>,WildcardRefFluent<WildcardRefTypeRefNested<N>>{


    public N and();    public N endWildcardRefTypeRef();
}
    public interface PrimitiveRefTypeRefNested<N> extends Nested<N>,PrimitiveRefFluent<PrimitiveRefTypeRefNested<N>>{


    public N and();    public N endPrimitiveRefTypeRef();
}
    public interface TypeParamRefTypeRefNested<N> extends Nested<N>,TypeParamRefFluent<TypeParamRefTypeRefNested<N>>{


    public N and();    public N endTypeParamRefTypeRef();
}
    public interface ClassRefTypeRefNested<N> extends Nested<N>,ClassRefFluent<ClassRefTypeRefNested<N>>{

        
    public N and();    public N endClassRefTypeRef();
}


}
