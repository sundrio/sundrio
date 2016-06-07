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

import io.sundr.builder.BaseFluent;
import io.sundr.builder.VisitableBuilder;

import java.util.ArrayList;
import java.util.List;

public class SourceFluentImpl<A extends SourceFluent<A>> extends BaseFluent<A> implements SourceFluent<A>{

     List<VisitableBuilder<TypeDef, ?>> types = new ArrayList();
public SourceFluentImpl(){
    
}
public SourceFluentImpl( Source instance ){
    this.withTypes(instance.getTypes()); 
}

    public A addToTypes( TypeDef ...items){
    for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.add(builder);this.types.add(builder);} return (A)this;
    }
    public A removeFromTypes( TypeDef ...items){
    for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.remove(builder);this.types.remove(builder);} return (A)this;
    }
    public List<TypeDef> getTypes(){
    return build(types);
    }
    public A withTypes( List<TypeDef> types){
    this.types.clear();if (types != null) {for (TypeDef item : types){this.addToTypes(item);}} return (A) this;
    }
    public A withTypes( TypeDef ...types){
    this.types.clear(); if (types != null) {for (TypeDef item :types){ this.addToTypes(item);}} return (A) this;
    }
    public TypesNested<A> addNewType(){
    return new TypesNestedImpl();
    }
    public TypesNested<A> addNewTypeLike( TypeDef item){
    return new TypesNestedImpl(item);
    }
    public boolean equals( Object o){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
SourceFluentImpl that = (SourceFluentImpl) o;
if (types != null ? !types.equals(that.types) :that.types != null) return false;
return true;

    }

    public class TypesNestedImpl<N> extends TypeDefFluentImpl<TypesNested<N>> implements TypesNested<N>{

        private final TypeDefBuilder builder;
    
             TypesNestedImpl (){
        this.builder = new TypeDefBuilder(this);
        }
             TypesNestedImpl ( TypeDef item){
        this.builder = new TypeDefBuilder(this, item);
        }
    
            public N endType(){
            return and();
        }
            public N and(){
            return (N) SourceFluentImpl.this.addToTypes(builder.build());
        }
    
}


}
