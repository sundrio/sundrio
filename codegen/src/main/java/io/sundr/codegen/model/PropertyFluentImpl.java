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

import io.sundr.builder.VisitableBuilder;
import java.util.LinkedHashSet;
import java.lang.String;
import io.sundr.builder.Nested;

import java.util.List;
import java.util.Set;
import java.lang.Object;

public class PropertyFluentImpl<A extends PropertyFluent<A>> extends ModifierSupportFluentImpl<A> implements PropertyFluent<A>{

    private Set<VisitableBuilder<? extends ClassRef,?>> annotations =  new LinkedHashSet<VisitableBuilder<? extends ClassRef,?>>();
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

    public A addToAnnotations(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }

    public A removeFromAnnotations(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }

    public List<ClassRef> getAnnotations(){
            return build(annotations);
    }

    public A withAnnotations(List<ClassRef> annotations){
            this.annotations.clear();
            if (annotations != null) {for (ClassRef item : annotations){this.addToAnnotations(item);}} return (A) this;
    }

    public A withAnnotations(ClassRef... annotations){
            this.annotations.clear(); if (annotations != null) {for (ClassRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }

    public PropertyFluent.AnnotationsNested<A> addNewAnnotation(){
            return new AnnotationsNestedImpl();
    }

    public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(ClassRef item){
            return new AnnotationsNestedImpl(item);
    }

    public TypeRef getTypeRef(){
            return this.typeRef!=null?this.typeRef.build():null;
    }

    public A withTypeRef(TypeRef typeRef){
            if (typeRef instanceof VoidRef){ this.typeRef= new VoidRefBuilder((VoidRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof WildcardRef){ this.typeRef= new WildcardRefBuilder((WildcardRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof PrimitiveRef){ this.typeRef= new PrimitiveRefBuilder((PrimitiveRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof TypeParamRef){ this.typeRef= new TypeParamRefBuilder((TypeParamRef)typeRef); _visitables.add(this.typeRef);}
            if (typeRef instanceof ClassRef){ this.typeRef= new ClassRefBuilder((ClassRef)typeRef); _visitables.add(this.typeRef);}
            return (A) this;
    }

    public String getName(){
            return this.name;
    }

    public A withName(String name){
            this.name=name; return (A) this;
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


    public class AnnotationsNestedImpl<N> extends ClassRefFluentImpl<PropertyFluent.AnnotationsNested<N>> implements PropertyFluent.AnnotationsNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
    
            AnnotationsNestedImpl(){
                    this.builder = new ClassRefBuilder(this);
            }
            AnnotationsNestedImpl(ClassRef item){
                    this.builder = new ClassRefBuilder(this, item);
            }
    
    public N endAnnotation(){
            return and();
    }
    public N and(){
            return (N) PropertyFluentImpl.this.addToAnnotations(builder.build());
    }

}


}
