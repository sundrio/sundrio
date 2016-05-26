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

import io.sundr.builder.VisitableBuilder;

import java.util.ArrayList;
import java.util.List;

public class ClassRefFluentImpl<A extends ClassRefFluent<A>> extends AttributeSupportFluentImpl<A> implements ClassRefFluent<A>{

     VisitableBuilder<TypeDef, ?> definition;     int dimensions;     List<TypeRef> arguments = new ArrayList();     List<VisitableBuilder<WildcardRef, ?>> wildcardRefArguments = new ArrayList();     List<VisitableBuilder<PrimitiveRef, ?>> primitiveRefArguments = new ArrayList();     List<VisitableBuilder<TypeParamRef, ?>> typeParamRefArguments = new ArrayList();     List<VisitableBuilder<ClassRef, ?>> classRefArguments = new ArrayList();     List<VisitableBuilder<VoidRef, ?>> voidRefArguments = new ArrayList();
public ClassRefFluentImpl(){
    
}
public ClassRefFluentImpl( ClassRef instance ){
    this.withDefinition(instance.getDefinition()); this.withDimensions(instance.getDimensions()); this.withArguments(instance.getArguments()); this.withAttributes(instance.getAttributes()); 
}

    public TypeDef getDefinition(){
    return this.definition!=null?this.definition.build():null;
    }
    public A withDefinition( TypeDef definition){
    if (definition!=null){ this.definition= new TypeDefBuilder(definition); _visitables.add(this.definition);} return (A) this;
    }
    public DefinitionNested<A> withNewDefinition(){
    return new DefinitionNestedImpl();
    }
    public DefinitionNested<A> withNewDefinitionLike( TypeDef item){
    return new DefinitionNestedImpl(item);
    }
    public DefinitionNested<A> editDefinition(){
    return withNewDefinitionLike(getDefinition());
    }
    public int getDimensions(){
    return this.dimensions;
    }
    public A withDimensions( int dimensions){
    this.dimensions=dimensions; return (A) this;
    }
    public A addToArguments( TypeRef ...items){
    for (TypeRef item : items) {if (item instanceof WildcardRef){addToWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){addToPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){addToTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){addToClassRefArguments((ClassRef)item);}
 else if (item instanceof VoidRef){addToVoidRefArguments((VoidRef)item);}
} return (A)this;
    }
    public A removeFromArguments( TypeRef ...items){
    for (TypeRef item : items) {if (item instanceof WildcardRef){removeFromWildcardRefArguments((WildcardRef)item);}
 else if (item instanceof PrimitiveRef){removeFromPrimitiveRefArguments((PrimitiveRef)item);}
 else if (item instanceof TypeParamRef){removeFromTypeParamRefArguments((TypeParamRef)item);}
 else if (item instanceof ClassRef){removeFromClassRefArguments((ClassRef)item);}
 else if (item instanceof VoidRef){removeFromVoidRefArguments((VoidRef)item);}
} return (A)this;
    }
    public List<TypeRef> getArguments(){
    return aggregate(this.<TypeRef>build(classRefArguments), this.<TypeRef>build(primitiveRefArguments), this.<TypeRef>build(typeParamRefArguments), this.<TypeRef>build(voidRefArguments), this.<TypeRef>build(wildcardRefArguments));
    }
    public A withArguments( List<TypeRef> arguments){
    this.arguments.clear();if (arguments != null) {for (TypeRef item : arguments){this.addToArguments(item);}} return (A) this;
    }
    public A withArguments( TypeRef ...arguments){
    this.arguments.clear(); if (arguments != null) {for (TypeRef item :arguments){ this.addToArguments(item);}} return (A) this;
    }
    public A addToWildcardRefArguments( WildcardRef ...items){
    for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.add(builder);this.wildcardRefArguments.add(builder);} return (A)this;
    }
    public A removeFromWildcardRefArguments( WildcardRef ...items){
    for (WildcardRef item : items) {WildcardRefBuilder builder = new WildcardRefBuilder(item);_visitables.remove(builder);this.wildcardRefArguments.remove(builder);} return (A)this;
    }
    public WildcardRefArgumentsNested<A> addNewWildcardRefArgument(){
    return new WildcardRefArgumentsNestedImpl();
    }
    public WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike( WildcardRef item){
    return new WildcardRefArgumentsNestedImpl(item);
    }
    public A addToPrimitiveRefArguments( PrimitiveRef ...items){
    for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.add(builder);this.primitiveRefArguments.add(builder);} return (A)this;
    }
    public A removeFromPrimitiveRefArguments( PrimitiveRef ...items){
    for (PrimitiveRef item : items) {PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);_visitables.remove(builder);this.primitiveRefArguments.remove(builder);} return (A)this;
    }
    public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument(){
    return new PrimitiveRefArgumentsNestedImpl();
    }
    public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike( PrimitiveRef item){
    return new PrimitiveRefArgumentsNestedImpl(item);
    }
    public A addToTypeParamRefArguments( TypeParamRef ...items){
    for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.add(builder);this.typeParamRefArguments.add(builder);} return (A)this;
    }
    public A removeFromTypeParamRefArguments( TypeParamRef ...items){
    for (TypeParamRef item : items) {TypeParamRefBuilder builder = new TypeParamRefBuilder(item);_visitables.remove(builder);this.typeParamRefArguments.remove(builder);} return (A)this;
    }
    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument(){
    return new TypeParamRefArgumentsNestedImpl();
    }
    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike( TypeParamRef item){
    return new TypeParamRefArgumentsNestedImpl(item);
    }
    public A addToClassRefArguments( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.classRefArguments.add(builder);} return (A)this;
    }
    public A removeFromClassRefArguments( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.classRefArguments.remove(builder);} return (A)this;
    }
    public ClassRefArgumentsNested<A> addNewClassRefArgument(){
    return new ClassRefArgumentsNestedImpl();
    }
    public ClassRefArgumentsNested<A> addNewClassRefArgumentLike( ClassRef item){
    return new ClassRefArgumentsNestedImpl(item);
    }
    public A addToVoidRefArguments( VoidRef ...items){
    for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.add(builder);this.voidRefArguments.add(builder);} return (A)this;
    }
    public A removeFromVoidRefArguments( VoidRef ...items){
    for (VoidRef item : items) {VoidRefBuilder builder = new VoidRefBuilder(item);_visitables.remove(builder);this.voidRefArguments.remove(builder);} return (A)this;
    }
    public VoidRefArgumentsNested<A> addNewVoidRefArgument(){
    return new VoidRefArgumentsNestedImpl();
    }
    public VoidRefArgumentsNested<A> addNewVoidRefArgumentLike( VoidRef item){
    return new VoidRefArgumentsNestedImpl(item);
    }
    public boolean equals( Object o){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
ClassRefFluentImpl that = (ClassRefFluentImpl) o;
if (definition != null ? !definition.equals(that.definition) :that.definition != null) return false;
if (dimensions != that.dimensions) return false;
if (arguments != null ? !arguments.equals(that.arguments) :that.arguments != null) return false;
if (wildcardRefArguments != null ? !wildcardRefArguments.equals(that.wildcardRefArguments) :that.wildcardRefArguments != null) return false;
if (primitiveRefArguments != null ? !primitiveRefArguments.equals(that.primitiveRefArguments) :that.primitiveRefArguments != null) return false;
if (typeParamRefArguments != null ? !typeParamRefArguments.equals(that.typeParamRefArguments) :that.typeParamRefArguments != null) return false;
if (classRefArguments != null ? !classRefArguments.equals(that.classRefArguments) :that.classRefArguments != null) return false;
if (voidRefArguments != null ? !voidRefArguments.equals(that.voidRefArguments) :that.voidRefArguments != null) return false;
return true;

    }

    public class DefinitionNestedImpl<N> extends TypeDefFluentImpl<DefinitionNested<N>> implements DefinitionNested<N>{

        private final TypeDefBuilder builder;
    
             DefinitionNestedImpl (){
        this.builder = new TypeDefBuilder(this);
        }
             DefinitionNestedImpl ( TypeDef item){
        this.builder = new TypeDefBuilder(this, item);
        }
    
            public N endDefinition(){
            return and();
        }
            public N and(){
            return (N) ClassRefFluentImpl.this.withDefinition(builder.build());
        }
    
}
    public class WildcardRefArgumentsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefArgumentsNested<N>> implements WildcardRefArgumentsNested<N>{

        private final WildcardRefBuilder builder;
    
             WildcardRefArgumentsNestedImpl (){
        this.builder = new WildcardRefBuilder(this);
        }
             WildcardRefArgumentsNestedImpl ( WildcardRef item){
        this.builder = new WildcardRefBuilder(this, item);
        }
    
            public N endWildcardRefArgument(){
            return and();
        }
            public N and(){
            return (N) ClassRefFluentImpl.this.addToWildcardRefArguments(builder.build());
        }
    
}
    public class PrimitiveRefArgumentsNestedImpl<N> extends PrimitiveRefFluentImpl<PrimitiveRefArgumentsNested<N>> implements PrimitiveRefArgumentsNested<N>{

        private final PrimitiveRefBuilder builder;
    
             PrimitiveRefArgumentsNestedImpl (){
        this.builder = new PrimitiveRefBuilder(this);
        }
             PrimitiveRefArgumentsNestedImpl ( PrimitiveRef item){
        this.builder = new PrimitiveRefBuilder(this, item);
        }
    
            public N endPrimitiveRefArgument(){
            return and();
        }
            public N and(){
            return (N) ClassRefFluentImpl.this.addToPrimitiveRefArguments(builder.build());
        }
    
}
    public class TypeParamRefArgumentsNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefArgumentsNested<N>> implements TypeParamRefArgumentsNested<N>{

        private final TypeParamRefBuilder builder;
    
             TypeParamRefArgumentsNestedImpl (){
        this.builder = new TypeParamRefBuilder(this);
        }
             TypeParamRefArgumentsNestedImpl ( TypeParamRef item){
        this.builder = new TypeParamRefBuilder(this, item);
        }
    
            public N endTypeParamRefArgument(){
            return and();
        }
            public N and(){
            return (N) ClassRefFluentImpl.this.addToTypeParamRefArguments(builder.build());
        }
    
}
    public class ClassRefArgumentsNestedImpl<N> extends ClassRefFluentImpl<ClassRefArgumentsNested<N>> implements ClassRefArgumentsNested<N>{

        private final ClassRefBuilder builder;
    
             ClassRefArgumentsNestedImpl (){
        this.builder = new ClassRefBuilder(this);
        }
             ClassRefArgumentsNestedImpl ( ClassRef item){
        this.builder = new ClassRefBuilder(this, item);
        }
    
            public N endClassRefArgument(){
            return and();
        }
            public N and(){
            return (N) ClassRefFluentImpl.this.addToClassRefArguments(builder.build());
        }
    
}
    public class VoidRefArgumentsNestedImpl<N> extends VoidRefFluentImpl<VoidRefArgumentsNested<N>> implements VoidRefArgumentsNested<N>{

        private final VoidRefBuilder builder;
    
             VoidRefArgumentsNestedImpl (){
        this.builder = new VoidRefBuilder(this);
        }
             VoidRefArgumentsNestedImpl ( VoidRef item){
        this.builder = new VoidRefBuilder(this, item);
        }
    
            public N endVoidRefArgument(){
            return and();
        }
            public N and(){
            return (N) ClassRefFluentImpl.this.addToVoidRefArguments(builder.build());
        }
    
}


}
