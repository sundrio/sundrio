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
import java.lang.String;
import io.sundr.builder.Nested;

public interface PropertyFluent<A extends PropertyFluent<A>> extends ModifierSupportFluent<A>{


    public A addToAnnotations(ClassRef... items);
    public A removeFromAnnotations(ClassRef... items);
    public List<ClassRef> getAnnotations();
    public A withAnnotations(List<ClassRef> annotations);
    public A withAnnotations(ClassRef... annotations);
    public PropertyFluent.AnnotationsNested<A> addNewAnnotation();
    public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(ClassRef item);
    public TypeRef getTypeRef();
    public A withTypeRef(TypeRef typeRef);
    public String getName();
    public A withName(String name);

    public interface AnnotationsNested<N> extends Nested<N>,ClassRefFluent<PropertyFluent.AnnotationsNested<N>>{

        
    public N endAnnotation();    public N and();
}


}
