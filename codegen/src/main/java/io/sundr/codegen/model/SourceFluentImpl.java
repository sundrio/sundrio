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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Builder;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

public class SourceFluentImpl<A extends SourceFluent<A>> extends BaseFluent<A> implements SourceFluent<A>{

    private List<VisitableBuilder<? extends TypeDef,?>> types =  new ArrayList<VisitableBuilder<? extends TypeDef,?>>();

    public SourceFluentImpl(){
    }
    public SourceFluentImpl(Source instance){
            this.withTypes(instance.getTypes()); 
    }

    public A addToTypes(int index,TypeDef item){
            TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("types").add(builder);this.types.add(builder); return (A)this;
    }

    public A setToTypes(int index,TypeDef item){
            TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("types").add(builder);this.types.add(builder); return (A)this;
    }

    public A addToTypes(TypeDef... items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("types").add(builder);this.types.add(builder);} return (A)this;
    }

    public A addAllToTypes(Collection<TypeDef> items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.get("types").add(builder);this.types.add(builder);} return (A)this;
    }

    public A removeFromTypes(TypeDef... items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.remove(builder);this.types.remove(builder);} return (A)this;
    }

    public A removeAllFromTypes(Collection<TypeDef> items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.remove(builder);this.types.remove(builder);} return (A)this;
    }


    /**
     * This method has been deprecated, please use method buildTypes instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<TypeDef> getTypes() {
        return build(types);
    }

    public List<TypeDef> buildTypes(){
            return build(types);
    }

    public TypeDef buildType(int index){
            return this.types.get(index).build();
    }

    public TypeDef buildFirstType(){
            return this.types.get(0).build();
    }

    public TypeDef buildLastType(){
            return this.types.get(types.size() - 1).build();
    }

    public TypeDef buildMatchingType(Predicate<Builder<? extends TypeDef>> predicate){
            for (Builder<? extends TypeDef> item: types) { if(predicate.test(item)){return item.build();} } return null;
    }

    public A withTypes(List<TypeDef> types){
            _visitables.get("types").removeAll(this.types);
            this.types.clear();
            if (types != null) {for (TypeDef item : types){this.addToTypes(item);}} return (A) this;
    }

    public A withTypes(TypeDef... types){
            this.types.clear(); if (types != null) {for (TypeDef item :types){ this.addToTypes(item);}} return (A) this;
    }

    public Boolean hasTypes(){
            return types!= null && !types.isEmpty();
    }

    public TypesNested<A> addNewType(){
            return new TypesNestedImpl();
    }

    public TypesNested<A> addNewTypeLike(TypeDef item){
            return new TypesNestedImpl(-1, item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SourceFluentImpl that = (SourceFluentImpl) o;
            if (types != null ? !types.equals(that.types) :that.types != null) return false;
            return true;
    }


    public class TypesNestedImpl<N> extends TypeDefFluentImpl<TypesNested<N>> implements TypesNested<N>,Nested<N>{

            private final TypeDefBuilder builder;
        private final int index;
    
            TypesNestedImpl(int index,TypeDef item){
                    this.index = index;
                    this.builder = new TypeDefBuilder(this, item);
            }
            TypesNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeDefBuilder(this);
            }
    
    public N and(){
            return (N) SourceFluentImpl.this.addToTypes(index, builder.build());
    }
    public N endType(){
            return and();
    }

}


}
