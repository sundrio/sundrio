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

public class PropertyFluentImpl<A extends PropertyFluent<A>> extends ModifierSupportFluentImpl<A> implements PropertyFluent<A>{

    private List<VisitableBuilder<? extends AnnotationRef,?>> annotations =  new ArrayList<VisitableBuilder<? extends AnnotationRef,?>>();
    private VisitableBuilder<? extends TypeRef,?> typeRef;
    private String name;

    public PropertyFluentImpl(){
    }
    public PropertyFluentImpl(Property instance){
            this.withAnnotations(instance.getAnnotations()); 
            this.withTypeRef(instance.getTypeRef()); 
            this.withName(instance.getName()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public A addToAnnotations(int index,AnnotationRef item){
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder); return (A)this;
    }

    public A setToAnnotations(int index,AnnotationRef item){
            AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder); return (A)this;
    }

    public A addToAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A addAllToAnnotations(Collection<AnnotationRef> items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(AnnotationRef... items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }

    public A removeAllFromAnnotations(Collection<AnnotationRef> items){
            for (AnnotationRef item : items) {AnnotationRefBuilder builder = new AnnotationRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }


    /**
     * This method has been deprecated, please use method buildAnnotations instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<AnnotationRef> getAnnotations() {
        return build(annotations);
    }

    public List<AnnotationRef> buildAnnotations(){
            return build(annotations);
    }

    public AnnotationRef buildAnnotation(int index){
            return this.annotations.get(index).build();
    }

    public AnnotationRef buildFirstAnnotation(){
            return this.annotations.get(0).build();
    }

    public AnnotationRef buildLastAnnotation(){
            return this.annotations.get(annotations.size() - 1).build();
    }

    public AnnotationRef buildMatchingAnnotation(Predicate<Builder<? extends AnnotationRef>> predicate){
            for (Builder<? extends AnnotationRef> item: annotations) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withAnnotations(List<AnnotationRef> annotations){
            _visitables.removeAll(this.annotations);
            this.annotations.clear();
            if (annotations != null) {for (AnnotationRef item : annotations){this.addToAnnotations(item);}} return (A) this;
    }

    public A withAnnotations(AnnotationRef... annotations){
            this.annotations.clear(); if (annotations != null) {for (AnnotationRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public Boolean hasAnnotations(){
            return annotations!= null && !annotations.isEmpty();
    }

    public AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item){
            return new AnnotationsNestedImpl(-1, item);
    }


    /**
     * This method has been deprecated, please use method buildTypeRef instead.
     * @return The buildable object.
     */
    @Deprecated
    public TypeRef getTypeRef() {
        return this.typeRef != null ? this.typeRef.build() : null;
    }

    public TypeRef buildTypeRef(){
            return this.typeRef!=null?this.typeRef.build():null;
    }

    public A withTypeRef(TypeRef typeRef){
            _visitables.remove(this.typeRef);
            if (typeRef instanceof VoidRef){ this.typeRef= new VoidRefBuilder((VoidRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof WildcardRef){ this.typeRef= new WildcardRefBuilder((WildcardRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof PrimitiveRef){ this.typeRef= new PrimitiveRefBuilder((PrimitiveRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof TypeParamRef){ this.typeRef= new TypeParamRefBuilder((TypeParamRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof ClassRef){ this.typeRef= new ClassRefBuilder((ClassRef)typeRef); _visitables.add(this.typeRef);}
            return (A) this;
    }

    public Boolean hasTypeRef(){
            return this.typeRef!=null;
    }

    public A withVoidRefTypeRef(VoidRef voidRefTypeRef){
            _visitables.remove(this.typeRef);
            if (voidRefTypeRef!=null){ this.typeRef= new VoidRefBuilder(voidRefTypeRef); _visitables.add(this.typeRef);} return (A) this;
    }

    public VoidRefTypeRefNested<A> withNewVoidRefTypeRef(){
            return new VoidRefTypeRefNestedImpl();
    }

    public VoidRefTypeRefNested<A> withNewVoidRefTypeRefLike(VoidRef item){
            return new VoidRefTypeRefNestedImpl(item);
    }

    public A withWildcardRefTypeRef(WildcardRef wildcardRefTypeRef){
            _visitables.remove(this.typeRef);
            if (wildcardRefTypeRef!=null){ this.typeRef= new WildcardRefBuilder(wildcardRefTypeRef); _visitables.add(this.typeRef);} return (A) this;
    }

    public WildcardRefTypeRefNested<A> withNewWildcardRefTypeRef(){
            return new WildcardRefTypeRefNestedImpl();
    }

    public WildcardRefTypeRefNested<A> withNewWildcardRefTypeRefLike(WildcardRef item){
            return new WildcardRefTypeRefNestedImpl(item);
    }

    public A withPrimitiveRefTypeRef(PrimitiveRef primitiveRefTypeRef){
            _visitables.remove(this.typeRef);
            if (primitiveRefTypeRef!=null){ this.typeRef= new PrimitiveRefBuilder(primitiveRefTypeRef); _visitables.add(this.typeRef);} return (A) this;
    }

    public PrimitiveRefTypeRefNested<A> withNewPrimitiveRefTypeRef(){
            return new PrimitiveRefTypeRefNestedImpl();
    }

    public PrimitiveRefTypeRefNested<A> withNewPrimitiveRefTypeRefLike(PrimitiveRef item){
            return new PrimitiveRefTypeRefNestedImpl(item);
    }

    public A withTypeParamRefTypeRef(TypeParamRef typeParamRefTypeRef){
            _visitables.remove(this.typeRef);
            if (typeParamRefTypeRef!=null){ this.typeRef= new TypeParamRefBuilder(typeParamRefTypeRef); _visitables.add(this.typeRef);} return (A) this;
    }

    public TypeParamRefTypeRefNested<A> withNewTypeParamRefTypeRef(){
            return new TypeParamRefTypeRefNestedImpl();
    }

    public TypeParamRefTypeRefNested<A> withNewTypeParamRefTypeRefLike(TypeParamRef item){
            return new TypeParamRefTypeRefNestedImpl(item);
    }

    public A withClassRefTypeRef(ClassRef classRefTypeRef){
            _visitables.remove(this.typeRef);
            if (classRefTypeRef!=null){ this.typeRef= new ClassRefBuilder(classRefTypeRef); _visitables.add(this.typeRef);} return (A) this;
    }

    public ClassRefTypeRefNested<A> withNewClassRefTypeRef(){
            return new ClassRefTypeRefNestedImpl();
    }

    public ClassRefTypeRefNested<A> withNewClassRefTypeRefLike(ClassRef item){
            return new ClassRefTypeRefNestedImpl(item);
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

    public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            PropertyFluentImpl that = (PropertyFluentImpl) o;
            if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
            if (typeRef != null ? !typeRef.equals(that.typeRef) :that.typeRef != null) return false;
            if (name != null ? !name.equals(that.name) :that.name != null) return false;
            return true;
    }


    public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<AnnotationsNested<N>> implements AnnotationsNested<N>,Nested<N>{

            private final AnnotationRefBuilder builder;
        private final int index;

            AnnotationsNestedImpl(int index,AnnotationRef item){
                    this.index = index;
                    this.builder = new AnnotationRefBuilder(this, item);
            }
            AnnotationsNestedImpl(){
                    this.index = -1;
                    this.builder = new AnnotationRefBuilder(this);
            }

    public N and(){
            return (N) PropertyFluentImpl.this.addToAnnotations(index, builder.build());
    }
    public N endAnnotation(){
            return and();
    }

}
    public class VoidRefTypeRefNestedImpl<N> extends VoidRefFluentImpl<VoidRefTypeRefNested<N>> implements VoidRefTypeRefNested<N>,Nested<N>{

            private final VoidRefBuilder builder;

            VoidRefTypeRefNestedImpl(VoidRef item){
                    this.builder = new VoidRefBuilder(this, item);
            }
            VoidRefTypeRefNestedImpl(){
                    this.builder = new VoidRefBuilder(this);
            }

    public N and(){
            return (N) PropertyFluentImpl.this.withVoidRefTypeRef(builder.build());
    }
    public N endVoidRefTypeRef(){
            return and();
    }

}
    public class WildcardRefTypeRefNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefTypeRefNested<N>> implements WildcardRefTypeRefNested<N>,Nested<N>{

            private final WildcardRefBuilder builder;

            WildcardRefTypeRefNestedImpl(WildcardRef item){
                    this.builder = new WildcardRefBuilder(this, item);
            }
            WildcardRefTypeRefNestedImpl(){
                    this.builder = new WildcardRefBuilder(this);
            }

    public N and(){
            return (N) PropertyFluentImpl.this.withWildcardRefTypeRef(builder.build());
    }
    public N endWildcardRefTypeRef(){
            return and();
    }

}
    public class PrimitiveRefTypeRefNestedImpl<N> extends PrimitiveRefFluentImpl<PrimitiveRefTypeRefNested<N>> implements PrimitiveRefTypeRefNested<N>,Nested<N>{

            private final PrimitiveRefBuilder builder;

            PrimitiveRefTypeRefNestedImpl(PrimitiveRef item){
                    this.builder = new PrimitiveRefBuilder(this, item);
            }
            PrimitiveRefTypeRefNestedImpl(){
                    this.builder = new PrimitiveRefBuilder(this);
            }

    public N and(){
            return (N) PropertyFluentImpl.this.withPrimitiveRefTypeRef(builder.build());
    }
    public N endPrimitiveRefTypeRef(){
            return and();
    }

}
    public class TypeParamRefTypeRefNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefTypeRefNested<N>> implements TypeParamRefTypeRefNested<N>,Nested<N>{

            private final TypeParamRefBuilder builder;

            TypeParamRefTypeRefNestedImpl(TypeParamRef item){
                    this.builder = new TypeParamRefBuilder(this, item);
            }
            TypeParamRefTypeRefNestedImpl(){
                    this.builder = new TypeParamRefBuilder(this);
            }

    public N and(){
            return (N) PropertyFluentImpl.this.withTypeParamRefTypeRef(builder.build());
    }
    public N endTypeParamRefTypeRef(){
            return and();
    }

}
    public class ClassRefTypeRefNestedImpl<N> extends ClassRefFluentImpl<ClassRefTypeRefNested<N>> implements ClassRefTypeRefNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            ClassRefTypeRefNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
            ClassRefTypeRefNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
    
    public N and(){
            return (N) PropertyFluentImpl.this.withClassRefTypeRef(builder.build());
    }
    public N endClassRefTypeRef(){
            return and();
    }

}


}
