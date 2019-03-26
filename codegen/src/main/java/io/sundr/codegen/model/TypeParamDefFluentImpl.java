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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypeParamDefFluentImpl<A extends TypeParamDefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeParamDefFluent<A>{

    private String name;
    private List<ClassRefBuilder> bounds =  new ArrayList<ClassRefBuilder>();

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
            return this.name != null;
    }

    public A withNewName(String arg1){
            return (A)withName(new String(arg1));
    }

    public A withNewName(StringBuilder arg1){
            return (A)withName(new String(arg1));
    }

    public A withNewName(StringBuffer arg1){
            return (A)withName(new String(arg1));
    }

    public A addToBounds(int index,ClassRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<ClassRefBuilder>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(index >= 0 ? index : _visitables.size(), builder);this.bounds.add(index >= 0 ? index : bounds.size(), builder); return (A)this;
    }

    public A setToBounds(int index,ClassRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<ClassRefBuilder>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);
            if (index < 0 || index >= _visitables.size()) { _visitables.add(builder); } else { _visitables.set(index, builder);}
            if (index < 0 || index >= bounds.size()) { bounds.add(builder); } else { bounds.set(index, builder);}
             return (A)this;
    }

    public A addToBounds(ClassRef... items){
            if (this.bounds == null) {this.bounds = new ArrayList<ClassRefBuilder>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToBounds(Collection<ClassRef> items){
            if (this.bounds == null) {this.bounds = new ArrayList<ClassRefBuilder>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public A removeAllFromBounds(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildBounds instead.
 * @return The buildable object.
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

    public ClassRef buildMatchingBound(Predicate<ClassRefBuilder> predicate){
            for (ClassRefBuilder item: bounds) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingBound(Predicate<ClassRefBuilder> predicate){
            for (ClassRefBuilder item: bounds) { if(predicate.apply(item)){return true;} } return false;
    }

    public A withBounds(List<ClassRef> bounds){
            if (this.bounds != null) { _visitables.removeAll(this.bounds);}
            if (bounds != null) {this.bounds = new ArrayList<ClassRefBuilder>(); for (ClassRef item : bounds){this.addToBounds(item);}} else { this.bounds = new ArrayList<ClassRefBuilder>();} return (A) this;
    }

    public A withBounds(ClassRef... bounds){
            if (this.bounds != null) {this.bounds.clear();}
            if (bounds != null) {for (ClassRef item :bounds){ this.addToBounds(item);}} return (A) this;
    }

    public Boolean hasBounds(){
            return bounds != null && !bounds.isEmpty();
    }

    public TypeParamDefFluent.BoundsNested<A> addNewBound(){
            return new BoundsNestedImpl();
    }

    public TypeParamDefFluent.BoundsNested<A> addNewBoundLike(ClassRef item){
            return new BoundsNestedImpl(-1, item);
    }

    public TypeParamDefFluent.BoundsNested<A> setNewBoundLike(int index,ClassRef item){
            return new BoundsNestedImpl(index, item);
    }

    public TypeParamDefFluent.BoundsNested<A> editBound(int index){
            if (bounds.size() <= index) throw new RuntimeException("Can't edit bounds. Index exceeds size.");
            return setNewBoundLike(index, buildBound(index));
    }

    public TypeParamDefFluent.BoundsNested<A> editFirstBound(){
            if (bounds.size() == 0) throw new RuntimeException("Can't edit first bounds. The list is empty.");
            return setNewBoundLike(0, buildBound(0));
    }

    public TypeParamDefFluent.BoundsNested<A> editLastBound(){
            int index = bounds.size() - 1;
            if (index < 0) throw new RuntimeException("Can't edit last bounds. The list is empty.");
            return setNewBoundLike(index, buildBound(index));
    }

    public TypeParamDefFluent.BoundsNested<A> editMatchingBound(Predicate<ClassRefBuilder> predicate){
            int index = -1;
            for (int i=0;i<bounds.size();i++) { 
            if (predicate.apply(bounds.get(i))) {index = i; break;}
            } 
            if (index < 0) throw new RuntimeException("Can't edit matching bounds. No match found.");
            return setNewBoundLike(index, buildBound(index));
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
        private final int index;
    
            BoundsNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            BoundsNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) TypeParamDefFluentImpl.this.setToBounds(index, builder.build());
    }
    public N endBound(){
            return and();
    }

}


}
