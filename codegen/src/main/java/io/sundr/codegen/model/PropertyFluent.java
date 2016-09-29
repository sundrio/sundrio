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

import io.sundr.builder.Nested;
import java.util.List;
import java.lang.String;

public interface PropertyFluent<A extends PropertyFluent<A>> extends ModifierSupportFluent<A>{


    public A addToAnnotations(AnnotationRef... items);
    public A removeFromAnnotations(AnnotationRef... items);
    public List<AnnotationRef> getAnnotations();
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public PropertyFluent.AnnotationsNested<A> addNewAnnotation();
    public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
    public TypeRef getTypeRef();
    public A withTypeRef(TypeRef typeRef);
    public A withVoidRefTypeRef(VoidRef voidRefTypeRef);
    public PropertyFluent.VoidRefTypeRefNested<A> withNewVoidRefTypeRef();
    public PropertyFluent.VoidRefTypeRefNested<A> withNewVoidRefTypeRefLike(VoidRef item);
    public A withWildcardRefTypeRef(WildcardRef wildcardRefTypeRef);
    public PropertyFluent.WildcardRefTypeRefNested<A> withNewWildcardRefTypeRef();
    public PropertyFluent.WildcardRefTypeRefNested<A> withNewWildcardRefTypeRefLike(WildcardRef item);
    public A withPrimitiveRefTypeRef(PrimitiveRef primitiveRefTypeRef);
    public PropertyFluent.PrimitiveRefTypeRefNested<A> withNewPrimitiveRefTypeRef();
    public PropertyFluent.PrimitiveRefTypeRefNested<A> withNewPrimitiveRefTypeRefLike(PrimitiveRef item);
    public A withTypeParamRefTypeRef(TypeParamRef typeParamRefTypeRef);
    public PropertyFluent.TypeParamRefTypeRefNested<A> withNewTypeParamRefTypeRef();
    public PropertyFluent.TypeParamRefTypeRefNested<A> withNewTypeParamRefTypeRefLike(TypeParamRef item);
    public A withClassRefTypeRef(ClassRef classRefTypeRef);
    public PropertyFluent.ClassRefTypeRefNested<A> withNewClassRefTypeRef();
    public PropertyFluent.ClassRefTypeRefNested<A> withNewClassRefTypeRefLike(ClassRef item);
    public String getName();
    public A withName(String name);

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<PropertyFluent.AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface VoidRefTypeRefNested<N> extends Nested<N>,VoidRefFluent<PropertyFluent.VoidRefTypeRefNested<N>>{


    public N and();    public N endVoidRefTypeRef();
}
    public interface WildcardRefTypeRefNested<N> extends Nested<N>,WildcardRefFluent<PropertyFluent.WildcardRefTypeRefNested<N>>{


    public N and();    public N endWildcardRefTypeRef();
}
    public interface PrimitiveRefTypeRefNested<N> extends Nested<N>,PrimitiveRefFluent<PropertyFluent.PrimitiveRefTypeRefNested<N>>{


    public N and();    public N endPrimitiveRefTypeRef();
}
    public interface TypeParamRefTypeRefNested<N> extends Nested<N>,TypeParamRefFluent<PropertyFluent.TypeParamRefTypeRefNested<N>>{


    public N and();    public N endTypeParamRefTypeRef();
}
    public interface ClassRefTypeRefNested<N> extends Nested<N>,ClassRefFluent<PropertyFluent.ClassRefTypeRefNested<N>>{

        
    public N and();    public N endClassRefTypeRef();
}


}
