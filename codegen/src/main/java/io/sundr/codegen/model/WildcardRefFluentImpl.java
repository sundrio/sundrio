/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.Nested;


public class WildcardRefFluentImpl<A extends WildcardRefFluent<A>> extends AbstractTypeRefFluentImpl<A> implements WildcardRefFluent<A>{

    private java.util.List<TypeRef> bounds = new java.util.ArrayList<TypeRef>();    java.util.List<io.sundr.builder.VisitableBuilder<VoidRef,?>> voidRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<VoidRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<WildcardRef,?>> wildcardRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<WildcardRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<PrimitiveRef,?>> primitiveRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<PrimitiveRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<TypeParamRef,?>> typeParamRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<TypeParamRef,?>>();    java.util.List<io.sundr.builder.VisitableBuilder<ClassRef,?>> classRefBounds =  new java.util.ArrayList<io.sundr.builder.VisitableBuilder<ClassRef,?>>();
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
    public A removeFromBounds(TypeRef... items){
            for (TypeRef item : items) {if (item instanceof VoidRef){removeFromVoidRefBounds((VoidRef)item);}
 else if (item instanceof WildcardRef){removeFromWildcardRefBounds((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefBounds((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefBounds((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefBounds((ClassRef)item);}
} return (A)this;
        }
    public java.util.List<TypeRef> getBounds(){
            return aggregate(this.<TypeRef>build(classRefBounds), this.<TypeRef>build(primitiveRefBounds), this.<TypeRef>build(typeParamRefBounds), this.<TypeRef>build(voidRefBounds), this.<TypeRef>build(wildcardRefBounds));
        }
    public A withBounds(java.util.List<TypeRef> bounds){
            this.bounds.clear();
            if (bounds != null) {for (TypeRef item : bounds){this.addToBounds(item);}} return (A) this;
        }
    public A withBounds(TypeRef[] bounds){
            this.bounds.clear(); if (bounds != null) {for (TypeRef item :bounds){ this.addToBounds(item);}} return (A) this;
        }
    public A addToVoidRefBounds(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.voidRefBounds.add(builder);} return (A)this;
        }
    public A removeFromVoidRefBounds(VoidRef... items){
            for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.voidRefBounds.remove(builder);} return (A)this;
        }
    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound(){
            return new VoidRefBoundsNestedImpl();
        }
    public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item){
            return new VoidRefBoundsNestedImpl(item);
        }
    public A addToWildcardRefBounds(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.wildcardRefBounds.add(builder);} return (A)this;
        }
    public A removeFromWildcardRefBounds(WildcardRef... items){
            for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.wildcardRefBounds.remove(builder);} return (A)this;
        }
    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound(){
            return new WildcardRefBoundsNestedImpl();
        }
    public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item){
            return new WildcardRefBoundsNestedImpl(item);
        }
    public A addToPrimitiveRefBounds(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.primitiveRefBounds.add(builder);} return (A)this;
        }
    public A removeFromPrimitiveRefBounds(PrimitiveRef... items){
            for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.primitiveRefBounds.remove(builder);} return (A)this;
        }
    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound(){
            return new PrimitiveRefBoundsNestedImpl();
        }
    public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item){
            return new PrimitiveRefBoundsNestedImpl(item);
        }
    public A addToTypeParamRefBounds(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.typeParamRefBounds.add(builder);} return (A)this;
        }
    public A removeFromTypeParamRefBounds(TypeParamRef... items){
            for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.typeParamRefBounds.remove(builder);} return (A)this;
        }
    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound(){
            return new TypeParamRefBoundsNestedImpl();
        }
    public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item){
            return new TypeParamRefBoundsNestedImpl(item);
        }
    public A addToClassRefBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.classRefBounds.add(builder);} return (A)this;
        }
    public A removeFromClassRefBounds(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.classRefBounds.remove(builder);} return (A)this;
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
            if (voidRefBounds != null ? !voidRefBounds.equals(that.voidRefBounds) :that.voidRefBounds != null) return false;
            if (wildcardRefBounds != null ? !wildcardRefBounds.equals(that.wildcardRefBounds) :that.wildcardRefBounds != null) return false;
            if (primitiveRefBounds != null ? !primitiveRefBounds.equals(that.primitiveRefBounds) :that.primitiveRefBounds != null) return false;
            if (typeParamRefBounds != null ? !typeParamRefBounds.equals(that.typeParamRefBounds) :that.typeParamRefBounds != null) return false;
            if (classRefBounds != null ? !classRefBounds.equals(that.classRefBounds) :that.classRefBounds != null) return false;
            return true;
        }

    public class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<WildcardRefFluent.VoidRefBoundsNested<N>> implements WildcardRefFluent.VoidRefBoundsNested<N>,Nested<N>{

        private final VoidRefBuilder builder;

            VoidRefBoundsNestedImpl(){
                    this.builder = new VoidRefBuilder(this);
                }
            VoidRefBoundsNestedImpl(VoidRef item){
                    this.builder = new VoidRefBuilder(this, item);
                }

            public N endVoidRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToVoidRefBounds(builder.build());
                }

}
    public class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefFluent.WildcardRefBoundsNested<N>> implements WildcardRefFluent.WildcardRefBoundsNested<N>,Nested<N>{

        private final WildcardRefBuilder builder;

            WildcardRefBoundsNestedImpl(){
                    this.builder = new WildcardRefBuilder(this);
                }
            WildcardRefBoundsNestedImpl(WildcardRef item){
                    this.builder = new WildcardRefBuilder(this, item);
                }

            public N endWildcardRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToWildcardRefBounds(builder.build());
                }

}
    public class PrimitiveRefBoundsNestedImpl<N> extends PrimitiveRefFluentImpl<WildcardRefFluent.PrimitiveRefBoundsNested<N>> implements WildcardRefFluent.PrimitiveRefBoundsNested<N>,Nested<N>{

        private final PrimitiveRefBuilder builder;

            PrimitiveRefBoundsNestedImpl(){
                    this.builder = new PrimitiveRefBuilder(this);
                }
            PrimitiveRefBoundsNestedImpl(PrimitiveRef item){
                    this.builder = new PrimitiveRefBuilder(this, item);
                }

            public N endPrimitiveRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToPrimitiveRefBounds(builder.build());
                }

}
    public class TypeParamRefBoundsNestedImpl<N> extends TypeParamRefFluentImpl<WildcardRefFluent.TypeParamRefBoundsNested<N>> implements WildcardRefFluent.TypeParamRefBoundsNested<N>,Nested<N>{

        private final TypeParamRefBuilder builder;

            TypeParamRefBoundsNestedImpl(){
                    this.builder = new TypeParamRefBuilder(this);
                }
            TypeParamRefBoundsNestedImpl(TypeParamRef item){
                    this.builder = new TypeParamRefBuilder(this, item);
                }

            public N endTypeParamRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToTypeParamRefBounds(builder.build());
                }

}
    class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<WildcardRefFluent.ClassRefBoundsNested<N>> implements WildcardRefFluent.ClassRefBoundsNested<N>,Nested<N>{

        private final ClassRefBuilder builder;
    
            ClassRefBoundsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
                }
            ClassRefBoundsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
                }
    
            public N endClassRefBound(){
                    return and();
                }
            public N and(){
                    return (N) WildcardRefFluentImpl.this.addToClassRefBounds(builder.build());
                }
    
}


}
