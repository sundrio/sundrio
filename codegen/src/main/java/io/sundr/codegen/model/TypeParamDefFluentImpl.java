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

import io.sundr.builder.Builder;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;
import io.sundr.builder.VisitableBuilder;

public class TypeParamDefFluentImpl<A extends TypeParamDefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeParamDefFluent<A>{

    private String name;
    private List<VisitableBuilder<? extends ClassRef,?>> bounds =  new ArrayList<VisitableBuilder<? extends ClassRef,?>>();

    public TypeParamDefFluentImpl(){
    }
    public TypeParamDefFluentImpl(TypeParamDef instance){
            this.withName(instance.getName()); 
            this.withBounds(instance.getBounds()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
    }

    public Boolean hasName(){
            return this.name!=null;
    }

    public A addToBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToBounds(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public A removeAllFromBounds(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildBounds instead.
 */
@Deprecated public List<ClassRef> getBounds(){
            return build(bounds);
    }

    public List<ClassRef> buildBounds(){
            return build(bounds);
    }

    public ClassRef buildBound(int index){
            return this.bounds.get(index).build();
    }

    public ClassRef buildFirstBound(){
            return this.bounds.get(0).build();
    }

    public ClassRef buildLastBound(){
            return this.bounds.get(bounds.size() - 1).build();
    }

    public ClassRef buildMatchingBound(Predicate<Builder<? extends ClassRef>> predicate){
            for (Builder<? extends ClassRef> item: bounds) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withBounds(List<ClassRef> bounds){
            _visitables.removeAll(this.bounds);
            this.bounds.clear();
            if (bounds != null) {for (ClassRef item : bounds){this.addToBounds(item);}} return (A) this;
    }

    public A withBounds(ClassRef... bounds){
            this.bounds.clear(); if (bounds != null) {for (ClassRef item :bounds){ this.addToBounds(item);}} return (A) this;
    }

    public Boolean hasBounds(){
            return bounds!= null && !bounds.isEmpty();
    }

    public TypeParamDefFluent.BoundsNested<A> addNewBound(){
            return new BoundsNestedImpl();
    }

    public TypeParamDefFluent.BoundsNested<A> addNewBoundLike(ClassRef item){
            return new BoundsNestedImpl(item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            TypeParamDefFluentImpl that = (TypeParamDefFluentImpl) o;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            if (bounds != null ? !bounds.equals(that.bounds) :that.bounds != null) return false;
            return true;
    }


    public class BoundsNestedImpl<N> extends ClassRefFluentImpl<TypeParamDefFluent.BoundsNested<N>> implements TypeParamDefFluent.BoundsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            BoundsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            BoundsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) TypeParamDefFluentImpl.this.addToBounds(builder.build());
    }
    public N endBound(){
            return and();
    }

}


}
