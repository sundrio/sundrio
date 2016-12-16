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
import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;

public interface SourceFluent<A extends SourceFluent<A>> extends Fluent<A>{


    public A addToTypes(int index, TypeDef item);
    public A setToTypes(int index, TypeDef item);
    public A addToTypes(TypeDef... items);
    public A addAllToTypes(Collection<TypeDef> items);
    public A removeFromTypes(TypeDef... items);
    public A removeAllFromTypes(Collection<TypeDef> items);
    
/**
 * This method has been deprecated, please use method buildTypes instead.
 */
@Deprecated public List<TypeDef> getTypes();
    public List<TypeDef> buildTypes();
    public TypeDef buildType(int index);
    public TypeDef buildFirstType();
    public TypeDef buildLastType();
    public TypeDef buildMatchingType(Predicate<Builder<? extends TypeDef>> predicate);
    public A withTypes(List<TypeDef> types);
    public A withTypes(TypeDef... types);
    public Boolean hasTypes();
    public TypesNested<A> addNewType();
    public TypesNested<A> addNewTypeLike(TypeDef item);

    public interface TypesNested<N> extends Nested<N>,TypeDefFluent<TypesNested<N>>{

        
    public N and();    public N endType();
}


}
