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

public class WildcardRefFluentImpl<A extends WildcardRefFluent<A>> extends TypeRefFluentImpl<A> implements WildcardRefFluent<A>{

    private List<VisitableBuilder<? extends TypeRef,?>> bounds =  new ArrayList<VisitableBuilder<? extends TypeRef,?>>();

    public WildcardRefFluentImpl(){
    }
    public WildcardRefFluentImpl(WildcardRef instance){
            this.withBounds(instance.getBounds()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToBounds(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof VoidRef){addToVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds((ClassRef)item);}
} return (A)this;
    }

    public A addAllToBounds(Collection<TypeRef> items){
            for (TypeRef item : items) {if (item instanceof VoidRef){addToVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds((ClassRef)item);}
} return (A)this;
    }

    public A removeFromBounds(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof VoidRef){removeFromVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefBounds((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefBounds((ClassRef)item);}
} return (A)this;
    }

    public A removeAllFromBounds(Collection<TypeRef> items){
            for (TypeRef item : items) {if (item instanceof VoidRef){removeFromVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefBounds((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefBounds((ClassRef)item);}
} return (A)this;
    }

    
/**
 * This method has been deprecated, please use method buildBounds instead.
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

    public TypeRef buildMatchingBound(Predicate<Builder<? extends TypeRef>> predicate){
            for (Builder<? extends TypeRef> item: bounds) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withBounds(List<TypeRef> bounds){
            _visitables.removeAll(this.bounds);
            this.bounds.clear();
            if (bounds != null) {for (TypeRef item : bounds){this.addToBounds(item);}} return (A) this;
    }

    public A withBounds(TypeRef... bounds){
            this.bounds.clear(); if (bounds != null) {for (TypeRef item :bounds){ this.addToBounds(item);}} return (A) this;
    }

    public Boolean hasBounds(){
            return bounds!= null && !bounds.isEmpty();
    }

    public A addToVoidRefBounds(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToVoidRefBounds(Collection<VoidRef> items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromVoidRefBounds(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public A removeAllFromVoidRefBounds(Collection<VoidRef> items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound(){
            return new VoidRefBoundsNestedImpl();
    }

    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item){
            return new VoidRefBoundsNestedImpl(item);
    }

    public A addToWildcardRefBounds(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToWildcardRefBounds(Collection<WildcardRef> items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromWildcardRefBounds(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public A removeAllFromWildcardRefBounds(Collection<WildcardRef> items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound(){
            return new WildcardRefBoundsNestedImpl();
    }

    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item){
            return new WildcardRefBoundsNestedImpl(item);
    }

    public A addToPrimitiveRefBounds(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToPrimitiveRefBounds(Collection<PrimitiveRef> items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromPrimitiveRefBounds(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public A removeAllFromPrimitiveRefBounds(Collection<PrimitiveRef> items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound(){
            return new PrimitiveRefBoundsNestedImpl();
    }

    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item){
            return new PrimitiveRefBoundsNestedImpl(item);
    }

    public A addToTypeParamRefBounds(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToTypeParamRefBounds(Collection<TypeParamRef> items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromTypeParamRefBounds(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public A removeAllFromTypeParamRefBounds(Collection<TypeParamRef> items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound(){
            return new TypeParamRefBoundsNestedImpl();
    }

    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item){
            return new TypeParamRefBoundsNestedImpl(item);
    }

    public A addToClassRefBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A addAllToClassRefBounds(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder);} return (A)this;
    }

    public A removeFromClassRefBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public A removeAllFromClassRefBounds(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.bounds.remove(builder);} return (A)this;
    }

    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound(){
            return new ClassRefBoundsNestedImpl();
    }

    public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item){
            return new ClassRefBoundsNestedImpl(item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WildcardRefFluentImpl that = (WildcardRefFluentImpl) o;
            if (bounds != null ? !bounds.equals(that.bounds) :that.bounds != null) return false;
            return true;
    }


    public class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<WildcardRefFluent.VoidRefBoundsNested<N>> implements WildcardRefFluent.VoidRefBoundsNested<N>,Nested<N>{

            private final VoidRefBuilder builder;
    
            VoidRefBoundsNestedImpl(VoidRef item){
                    this.builder = new VoidRefBuilder(this, item);
            }
            VoidRefBoundsNestedImpl(){
                    this.builder = new VoidRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.addToVoidRefBounds(builder.build());
    }
    public N endVoidRefBound(){
            return and();
    }

}
    public class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefFluent.WildcardRefBoundsNested<N>> implements WildcardRefFluent.WildcardRefBoundsNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;
    
            WildcardRefBoundsNestedImpl(WildcardRef item){
                    this.builder = new WildcardRefBuilder(this, item);
            }
            WildcardRefBoundsNestedImpl(){
                    this.builder = new WildcardRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.addToWildcardRefBounds(builder.build());
    }
    public N endWildcardRefBound(){
            return and();
    }

}
    public class PrimitiveRefBoundsNestedImpl<N> extends PrimitiveRefFluentImpl<WildcardRefFluent.PrimitiveRefBoundsNested<N>> implements WildcardRefFluent.PrimitiveRefBoundsNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;
    
            PrimitiveRefBoundsNestedImpl(PrimitiveRef item){
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
            PrimitiveRefBoundsNestedImpl(){
                    this.builder = new PrimitiveRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.addToPrimitiveRefBounds(builder.build());
    }
    public N endPrimitiveRefBound(){
            return and();
    }

}
    public class TypeParamRefBoundsNestedImpl<N> extends TypeParamRefFluentImpl<WildcardRefFluent.TypeParamRefBoundsNested<N>> implements WildcardRefFluent.TypeParamRefBoundsNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;
    
            TypeParamRefBoundsNestedImpl(TypeParamRef item){
                    this.builder = new TypeParamRefBuilder(this, item);
            }
            TypeParamRefBoundsNestedImpl(){
                    this.builder = new TypeParamRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.addToTypeParamRefBounds(builder.build());
    }
    public N endTypeParamRefBound(){
            return and();
    }

}
    public class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<WildcardRefFluent.ClassRefBoundsNested<N>> implements WildcardRefFluent.ClassRefBoundsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ClassRefBoundsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefBoundsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) WildcardRefFluentImpl.this.addToClassRefBounds(builder.build());
    }
    public N endClassRefBound(){
            return and();
    }

}


}
