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
import io.sundr.builder.VisitableBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WildcardRefFluentImpl<A extends WildcardRefFluent<A>> extends TypeRefFluentImpl<A> implements WildcardRefFluent<A>{

    private List<VisitableBuilder<? extends TypeRef,?>> bounds =  new ArrayList<VisitableBuilder<? extends TypeRef,?>>();

    public WildcardRefFluentImpl(){
    }
    public WildcardRefFluentImpl(WildcardRef instance){
            this.withBounds(instance.getBounds()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToBounds(VisitableBuilder<? extends TypeRef,?> builder){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            _visitables.get("bounds").add(builder);this.bounds.add(builder); return (A)this;
    }

    public A addToBounds(int index,VisitableBuilder<? extends TypeRef,?> builder){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            _visitables.get("bounds").add(index, builder);this.bounds.add(index, builder); return (A)this;
    }

    public A addToBounds(int index,TypeRef item){
            if (item instanceof PrimitiveRef){addToPrimitiveRefBounds(index, (PrimitiveRef)item);}
 else if (item instanceof VoidRef){addToVoidRefBounds(index, (VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds(index, (WildcardRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds(index, (ClassRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds(index, (TypeParamRef)item);}

            return (A)this;
    }

    public A setToBounds(int index,TypeRef item){
            if (item instanceof PrimitiveRef){setToPrimitiveRefBounds(index, (PrimitiveRef)item);}
 else if (item instanceof VoidRef){setToVoidRefBounds(index, (VoidRef)item);}
 else if (item instanceof WildcardRef){setToWildcardRefBounds(index, (WildcardRef)item);}
 else if (item instanceof ClassRef){setToClassRefBounds(index, (ClassRef)item);}
 else if (item instanceof TypeParamRef){setToTypeParamRefBounds(index, (TypeParamRef)item);}

            return (A)this;
    }

    public A addToBounds(TypeRef... items){
             if (items != null && items.length > 0 && this.bounds== null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (TypeRef item : items) { 
            if (item instanceof PrimitiveRef){addToPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof VoidRef){addToVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds((ClassRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds((TypeParamRef)item);}

            else {  VisitableBuilder<? extends TypeRef,?> builder = builderOf(item); _visitables.get("bounds").add(builder);this.bounds.add(builder); }
            } return (A)this;
    }

    public A addAllToBounds(Collection<TypeRef> items){
             if (items != null && items.size() > 0 && this.bounds== null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (TypeRef item : items) { 
            if (item instanceof PrimitiveRef){addToPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof VoidRef){addToVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds((ClassRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds((TypeParamRef)item);}

            else {  VisitableBuilder<? extends TypeRef,?> builder = builderOf(item); _visitables.get("bounds").add(builder);this.bounds.add(builder); }
            } return (A)this;
    }

    public A removeFromBounds(VisitableBuilder<? extends TypeRef,?> builder){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            _visitables.get("bounds").remove(builder);this.bounds.remove(builder); return (A)this;
    }

    public A removeFromBounds(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof PrimitiveRef){removeFromPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof VoidRef){removeFromVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefBounds((ClassRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefBounds((TypeParamRef)item);}

            else {  VisitableBuilder<? extends TypeRef,?> builder = builderOf(item); _visitables.get("bounds").remove(builder);this.bounds.remove(builder); }
            } return (A)this;
    }

    public A removeAllFromBounds(Collection<TypeRef> items){
            for (TypeRef item : items) {if (item instanceof PrimitiveRef){removeFromPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof VoidRef){removeFromVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefBounds((ClassRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefBounds((TypeParamRef)item);}

            else {  VisitableBuilder<? extends TypeRef,?> builder = builderOf(item); _visitables.get("bounds").remove(builder);this.bounds.remove(builder); }
            } return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildBounds instead.
 * @return The buildable object.
 */
@Deprecated public List<TypeRef> getBounds(){
            return build(bounds);
    }

    public List<TypeRef> buildBounds(){
            return build(bounds);
    }

    public TypeRef buildBound(int index){
            return this.bounds.get(index).build();
    }

    public TypeRef buildFirstBound(){
            return this.bounds.get(0).build();
    }

    public TypeRef buildLastBound(){
            return this.bounds.get(bounds.size() - 1).build();
    }

    public TypeRef buildMatchingBound(Predicate<VisitableBuilder<? extends TypeRef,?>> predicate){
            for (VisitableBuilder<? extends TypeRef,?> item: bounds) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public Boolean hasMatchingBound(Predicate<VisitableBuilder<? extends TypeRef,?>> predicate){
            for (VisitableBuilder<? extends TypeRef,?> item: bounds) { if(predicate.apply(item)){return true;} } return false;
    }

    public A withBounds(List<TypeRef> bounds){
            if (this.bounds != null) { _visitables.get("bounds").removeAll(this.bounds);}
            if (bounds != null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>(); for (TypeRef item : bounds){this.addToBounds(item);}} else { this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();} return (A) this;
    }

    public A withBounds(TypeRef... bounds){
            if (this.bounds != null) {this.bounds.clear();}
            if (bounds != null) {for (TypeRef item :bounds){ this.addToBounds(item);}} return (A) this;
    }

    public Boolean hasBounds(){
            return bounds != null && !bounds.isEmpty();
    }

    public A addToPrimitiveRefBounds(int index,PrimitiveRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);this.bounds.add(index >= 0 ? index : bounds.size(), builder); return (A)this;
    }

    public A setToPrimitiveRefBounds(int index,PrimitiveRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
            if (index < 0 || index >= _visitables.get("bounds").size()) { _visitables.get("bounds").add(builder); } else { _visitables.get("bounds").set(index, builder);}
            if (index < 0 || index >= bounds.size()) { bounds.add(builder); } else { bounds.set(index, builder);}
             return (A)this;
    }

    public A addToPrimitiveRefBounds(PrimitiveRef... items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToPrimitiveRefBounds(Collection<PrimitiveRef> items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromPrimitiveRefBounds(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public A removeAllFromPrimitiveRefBounds(Collection<PrimitiveRef> items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound(){
            return new PrimitiveRefBoundsNestedImpl();
    }

    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item){
            return new PrimitiveRefBoundsNestedImpl(-1, item);
    }

    public WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(int index,PrimitiveRef item){
            return new PrimitiveRefBoundsNestedImpl(index, item);
    }

    public A addToVoidRefBounds(int index,VoidRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);this.bounds.add(index >= 0 ? index : bounds.size(), builder); return (A)this;
    }

    public A setToVoidRefBounds(int index,VoidRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            VoidRefBuilder builder = new VoidRefBuilder(item);
            if (index < 0 || index >= _visitables.get("bounds").size()) { _visitables.get("bounds").add(builder); } else { _visitables.get("bounds").set(index, builder);}
            if (index < 0 || index >= bounds.size()) { bounds.add(builder); } else { bounds.set(index, builder);}
             return (A)this;
    }

    public A addToVoidRefBounds(VoidRef... items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToVoidRefBounds(Collection<VoidRef> items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromVoidRefBounds(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public A removeAllFromVoidRefBounds(Collection<VoidRef> items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound(){
            return new VoidRefBoundsNestedImpl();
    }

    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item){
            return new VoidRefBoundsNestedImpl(-1, item);
    }

    public WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(int index,VoidRef item){
            return new VoidRefBoundsNestedImpl(index, item);
    }

    public A addToWildcardRefBounds(int index,WildcardRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);this.bounds.add(index >= 0 ? index : bounds.size(), builder); return (A)this;
    }

    public A setToWildcardRefBounds(int index,WildcardRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            WildcardRefBuilder builder = new WildcardRefBuilder(item);
            if (index < 0 || index >= _visitables.get("bounds").size()) { _visitables.get("bounds").add(builder); } else { _visitables.get("bounds").set(index, builder);}
            if (index < 0 || index >= bounds.size()) { bounds.add(builder); } else { bounds.set(index, builder);}
             return (A)this;
    }

    public A addToWildcardRefBounds(WildcardRef... items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToWildcardRefBounds(Collection<WildcardRef> items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromWildcardRefBounds(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public A removeAllFromWildcardRefBounds(Collection<WildcardRef> items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound(){
            return new WildcardRefBoundsNestedImpl();
    }

    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item){
            return new WildcardRefBoundsNestedImpl(-1, item);
    }

    public WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(int index,WildcardRef item){
            return new WildcardRefBoundsNestedImpl(index, item);
    }

    public A addToClassRefBounds(int index,ClassRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);this.bounds.add(index >= 0 ? index : bounds.size(), builder); return (A)this;
    }

    public A setToClassRefBounds(int index,ClassRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            ClassRefBuilder builder = new ClassRefBuilder(item);
            if (index < 0 || index >= _visitables.get("bounds").size()) { _visitables.get("bounds").add(builder); } else { _visitables.get("bounds").set(index, builder);}
            if (index < 0 || index >= bounds.size()) { bounds.add(builder); } else { bounds.set(index, builder);}
             return (A)this;
    }

    public A addToClassRefBounds(ClassRef... items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToClassRefBounds(Collection<ClassRef> items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromClassRefBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public A removeAllFromClassRefBounds(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound(){
            return new ClassRefBoundsNestedImpl();
    }

    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item){
            return new ClassRefBoundsNestedImpl(-1, item);
    }

    public WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(int index,ClassRef item){
            return new ClassRefBoundsNestedImpl(index, item);
    }

    public A addToTypeParamRefBounds(int index,TypeParamRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);this.bounds.add(index >= 0 ? index : bounds.size(), builder); return (A)this;
    }

    public A setToTypeParamRefBounds(int index,TypeParamRef item){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
            if (index < 0 || index >= _visitables.get("bounds").size()) { _visitables.get("bounds").add(builder); } else { _visitables.get("bounds").set(index, builder);}
            if (index < 0 || index >= bounds.size()) { bounds.add(builder); } else { bounds.set(index, builder);}
             return (A)this;
    }

    public A addToTypeParamRefBounds(TypeParamRef... items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToTypeParamRefBounds(Collection<TypeParamRef> items){
            if (this.bounds == null) {this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef,?>>();}
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.get("bounds").add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromTypeParamRefBounds(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public A removeAllFromTypeParamRefBounds(Collection<TypeParamRef> items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.get("bounds").remove(builder);if (this.bounds != null) {this.bounds.remove(builder);}} return (A)this;
    }

    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound(){
            return new TypeParamRefBoundsNestedImpl();
    }

    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item){
            return new TypeParamRefBoundsNestedImpl(-1, item);
    }

    public WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(int index,TypeParamRef item){
            return new TypeParamRefBoundsNestedImpl(index, item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WildcardRefFluentImpl that = (WildcardRefFluentImpl) o;
            if (bounds != null ? !bounds.equals(that.bounds) :that.bounds != null) return false;
            return true;
    }


    public class PrimitiveRefBoundsNestedImpl<N> extends PrimitiveRefFluentImpl<WildcardRefFluent.PrimitiveRefBoundsNested<N>> implements WildcardRefFluent.PrimitiveRefBoundsNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;
        private final int index;
    
            PrimitiveRefBoundsNestedImpl(int index,PrimitiveRef item){
                    this.index = index;
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
            PrimitiveRefBoundsNestedImpl(){
                    this.index = -1;
                    this.builder = new PrimitiveRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.setToPrimitiveRefBounds(index, builder.build());
    }
    public N endPrimitiveRefBound(){
            return and();
    }

}
    public class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<WildcardRefFluent.VoidRefBoundsNested<N>> implements WildcardRefFluent.VoidRefBoundsNested<N>,Nested<N>{

            private final VoidRefBuilder builder;
        private final int index;
    
            VoidRefBoundsNestedImpl(int index,VoidRef item){
                    this.index = index;
                    this.builder = new VoidRefBuilder(this, item);
            }
            VoidRefBoundsNestedImpl(){
                    this.index = -1;
                    this.builder = new VoidRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.setToVoidRefBounds(index, builder.build());
    }
    public N endVoidRefBound(){
            return and();
    }

}
    public class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefFluent.WildcardRefBoundsNested<N>> implements WildcardRefFluent.WildcardRefBoundsNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;
        private final int index;
    
            WildcardRefBoundsNestedImpl(int index,WildcardRef item){
                    this.index = index;
                    this.builder = new WildcardRefBuilder(this, item);
            }
            WildcardRefBoundsNestedImpl(){
                    this.index = -1;
                    this.builder = new WildcardRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.setToWildcardRefBounds(index, builder.build());
    }
    public N endWildcardRefBound(){
            return and();
    }

}
    public class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<WildcardRefFluent.ClassRefBoundsNested<N>> implements WildcardRefFluent.ClassRefBoundsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;
    
            ClassRefBoundsNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefBoundsNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.setToClassRefBounds(index, builder.build());
    }
    public N endClassRefBound(){
            return and();
    }

}
    public class TypeParamRefBoundsNestedImpl<N> extends TypeParamRefFluentImpl<WildcardRefFluent.TypeParamRefBoundsNested<N>> implements WildcardRefFluent.TypeParamRefBoundsNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;
        private final int index;
    
            TypeParamRefBoundsNestedImpl(int index,TypeParamRef item){
                    this.index = index;
                    this.builder = new TypeParamRefBuilder(this, item);
            }
            TypeParamRefBoundsNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeParamRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.setToTypeParamRefBounds(index, builder.build());
    }
    public N endTypeParamRefBound(){
            return and();
    }

}


}
