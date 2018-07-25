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

    public A addToBounds(int index,TypeRef item){
            if (item instanceof VoidRef){addToVoidRefBounds(index, (VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds(index, (WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefBounds(index, (PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds(index, (TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds(index, (ClassRef)item);}

            return (A)this;
    }

    public A setToBounds(int index,TypeRef item){
            if (item instanceof VoidRef){setToVoidRefBounds(index, (VoidRef)item);}
 else if (item instanceof WildcardRef){setToWildcardRefBounds(index, (WildcardRef)item);}
 else if (item instanceof PrimitiveRef){setToPrimitiveRefBounds(index, (PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){setToTypeParamRefBounds(index, (TypeParamRef)item);}
 else if (item instanceof ClassRef){setToClassRefBounds(index, (ClassRef)item);}

            return (A)this;
    }

    public A addToBounds(TypeRef... items){
            for (TypeRef item : items) { 
            if (item instanceof VoidRef){addToVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){addToWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefBounds((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefBounds((ClassRef)item);}

            } return (A)this;
    }

    public A addAllToBounds(Collection<TypeRef> items){
            for (TypeRef item : items) { 
            if (item instanceof VoidRef){addToVoidRefBounds((VoidRef)item);}
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
     * @return The buildable object.
     */
    @Deprecated
    public List<TypeRef> getBounds() {
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

    public A addToVoidRefBounds(int index,VoidRef item){
            VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
    }

    public A setToVoidRefBounds(int index,VoidRef item){
            VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
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

    public VoidRefBoundsNested<A> addNewVoidRefBound(){
            return new VoidRefBoundsNestedImpl();
    }

    public VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item){
            return new VoidRefBoundsNestedImpl(-1, item);
    }

    public A addToWildcardRefBounds(int index,WildcardRef item){
            WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
    }

    public A setToWildcardRefBounds(int index,WildcardRef item){
            WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
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

    public WildcardRefBoundsNested<A> addNewWildcardRefBound(){
            return new WildcardRefBoundsNestedImpl();
    }

    public WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item){
            return new WildcardRefBoundsNestedImpl(-1, item);
    }

    public A addToPrimitiveRefBounds(int index,PrimitiveRef item){
            PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
    }

    public A setToPrimitiveRefBounds(int index,PrimitiveRef item){
            PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
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

    public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound(){
            return new PrimitiveRefBoundsNestedImpl();
    }

    public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item){
            return new PrimitiveRefBoundsNestedImpl(-1, item);
    }

    public A addToTypeParamRefBounds(int index,TypeParamRef item){
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
    }

    public A setToTypeParamRefBounds(int index,TypeParamRef item){
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
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

    public TypeParamRefBoundsNested<A> addNewTypeParamRefBound(){
            return new TypeParamRefBoundsNestedImpl();
    }

    public TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item){
            return new TypeParamRefBoundsNestedImpl(-1, item);
    }

    public A addToClassRefBounds(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
    }

    public A setToClassRefBounds(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.bounds.add(builder); return (A)this;
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

    public ClassRefBoundsNested<A> addNewClassRefBound(){
            return new ClassRefBoundsNestedImpl();
    }

    public ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item){
            return new ClassRefBoundsNestedImpl(-1, item);
    }

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            WildcardRefFluentImpl that = (WildcardRefFluentImpl) o;
            if (bounds != null ? !bounds.equals(that.bounds) :that.bounds != null) return false;
            return true;
    }


    public class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<VoidRefBoundsNested<N>> implements VoidRefBoundsNested<N>,Nested<N>{

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
            return (N) WildcardRefFluentImpl.this.addToVoidRefBounds(index, builder.build());
    }
    public N endVoidRefBound(){
            return and();
    }

}
    public class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefBoundsNested<N>> implements WildcardRefBoundsNested<N>,Nested<N>{

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
            return (N) WildcardRefFluentImpl.this.addToWildcardRefBounds(index, builder.build());
    }
    public N endWildcardRefBound(){
            return and();
    }

}
    public class PrimitiveRefBoundsNestedImpl<N> extends PrimitiveRefFluentImpl<PrimitiveRefBoundsNested<N>> implements PrimitiveRefBoundsNested<N>,Nested<N>{

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
            return (N) WildcardRefFluentImpl.this.addToPrimitiveRefBounds(index, builder.build());
    }
    public N endPrimitiveRefBound(){
            return and();
    }

}
    public class TypeParamRefBoundsNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefBoundsNested<N>> implements TypeParamRefBoundsNested<N>,Nested<N>{

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
            return (N) WildcardRefFluentImpl.this.addToTypeParamRefBounds(index, builder.build());
    }
    public N endTypeParamRefBound(){
            return and();
    }

}
    public class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<ClassRefBoundsNested<N>> implements ClassRefBoundsNested<N>,Nested<N>{

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
            return (N) WildcardRefFluentImpl.this.addToClassRefBounds(index, builder.build());
    }
    public N endClassRefBound(){
            return and();
    }

}


}
