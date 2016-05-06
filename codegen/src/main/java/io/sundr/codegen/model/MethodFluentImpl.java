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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MethodFluentImpl<A extends MethodFluent<A>> extends ModifierSupportFluentImpl<A> implements MethodFluent<A>{

     Set<VisitableBuilder<ClassRef, ?>> annotations = new LinkedHashSet();     Set<VisitableBuilder<TypeParamDef, ?>> parameters = new LinkedHashSet();     String name;     TypeRef returnType;     List<VisitableBuilder<Property, ?>> arguments = new ArrayList();     Set<VisitableBuilder<ClassRef, ?>> exceptions = new LinkedHashSet();     VisitableBuilder<Block, ?> block;
public MethodFluentImpl(){
    
}
public MethodFluentImpl( Method instance ){
    this.withAnnotations(instance.getAnnotations()); this.withParameters(instance.getParameters()); this.withName(instance.getName()); this.withReturnType(instance.getReturnType()); this.withArguments(instance.getArguments()); this.withExceptions(instance.getExceptions()); this.withBlock(instance.getBlock()); this.withModifiers(instance.getModifiers()); this.withAttributes(instance.getAttributes()); 
}

    public A addToAnnotations( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.annotations.add(builder);} return (A)this;
    }
    public A removeFromAnnotations( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.annotations.remove(builder);} return (A)this;
    }
    public Set<ClassRef> getAnnotations(){
    return build(annotations);
    }
    public A withAnnotations( Set<ClassRef> annotations){
    this.annotations.clear();if (annotations != null) {for (ClassRef item : annotations){this.addToAnnotations(item);}} return (A) this;
    }
    public A withAnnotations( ClassRef ...annotations){
    this.annotations.clear(); if (annotations != null) {for (ClassRef item :annotations){ this.addToAnnotations(item);}} return (A) this;
    }
    public AnnotationsNested<A> addNewAnnotation(){
    return new AnnotationsNestedImpl();
    }
    public AnnotationsNested<A> addNewAnnotationLike( ClassRef item){
    return new AnnotationsNestedImpl(item);
    }
    public A addToParameters( TypeParamDef ...items){
    for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }
    public A removeFromParameters( TypeParamDef ...items){
    for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }
    public Set<TypeParamDef> getParameters(){
    return build(parameters);
    }
    public A withParameters( Set<TypeParamDef> parameters){
    this.parameters.clear();if (parameters != null) {for (TypeParamDef item : parameters){this.addToParameters(item);}} return (A) this;
    }
    public A withParameters( TypeParamDef ...parameters){
    this.parameters.clear(); if (parameters != null) {for (TypeParamDef item :parameters){ this.addToParameters(item);}} return (A) this;
    }
    public ParametersNested<A> addNewParameter(){
    return new ParametersNestedImpl();
    }
    public ParametersNested<A> addNewParameterLike( TypeParamDef item){
    return new ParametersNestedImpl(item);
    }
    public String getName(){
    return this.name;
    }
    public A withName( String name){
    this.name=name; return (A) this;
    }
    public TypeRef getReturnType(){
    return this.returnType;
    }
    public A withReturnType( TypeRef returnType){
    this.returnType=returnType; return (A) this;
    }
    public A addToArguments( Property ...items){
    for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.arguments.add(builder);} return (A)this;
    }
    public A removeFromArguments( Property ...items){
    for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.arguments.remove(builder);} return (A)this;
    }
    public List<Property> getArguments(){
    return build(arguments);
    }
    public A withArguments( List<Property> arguments){
    this.arguments.clear();if (arguments != null) {for (Property item : arguments){this.addToArguments(item);}} return (A) this;
    }
    public A withArguments( Property ...arguments){
    this.arguments.clear(); if (arguments != null) {for (Property item :arguments){ this.addToArguments(item);}} return (A) this;
    }
    public ArgumentsNested<A> addNewArgument(){
    return new ArgumentsNestedImpl();
    }
    public ArgumentsNested<A> addNewArgumentLike( Property item){
    return new ArgumentsNestedImpl(item);
    }
    public A addToExceptions( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.exceptions.add(builder);} return (A)this;
    }
    public A removeFromExceptions( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.exceptions.remove(builder);} return (A)this;
    }
    public Set<ClassRef> getExceptions(){
    return build(exceptions);
    }
    public A withExceptions( Set<ClassRef> exceptions){
    this.exceptions.clear();if (exceptions != null) {for (ClassRef item : exceptions){this.addToExceptions(item);}} return (A) this;
    }
    public A withExceptions( ClassRef ...exceptions){
    this.exceptions.clear(); if (exceptions != null) {for (ClassRef item :exceptions){ this.addToExceptions(item);}} return (A) this;
    }
    public ExceptionsNested<A> addNewException(){
    return new ExceptionsNestedImpl();
    }
    public ExceptionsNested<A> addNewExceptionLike( ClassRef item){
    return new ExceptionsNestedImpl(item);
    }
    public Block getBlock(){
    return this.block!=null?this.block.build():null;
    }
    public A withBlock( Block block){
    if (block!=null){ this.block= new BlockBuilder(block); _visitables.add(this.block);} return (A) this;
    }
    public BlockNested<A> withNewBlock(){
    return new BlockNestedImpl();
    }
    public BlockNested<A> withNewBlockLike( Block item){
    return new BlockNestedImpl(item);
    }
    public BlockNested<A> editBlock(){
    return withNewBlockLike(getBlock());
    }
    public boolean equals( Object o){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
MethodFluentImpl that = (MethodFluentImpl) o;
if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
if (parameters != null ? !parameters.equals(that.parameters) :that.parameters != null) return false;
if (name != null ? !name.equals(that.name) :that.name != null) return false;
if (returnType != null ? !returnType.equals(that.returnType) :that.returnType != null) return false;
if (arguments != null ? !arguments.equals(that.arguments) :that.arguments != null) return false;
if (exceptions != null ? !exceptions.equals(that.exceptions) :that.exceptions != null) return false;
if (block != null ? !block.equals(that.block) :that.block != null) return false;
return true;

    }

    public class AnnotationsNestedImpl<N> extends ClassRefFluentImpl<AnnotationsNested<N>> implements AnnotationsNested<N>{

        private final ClassRefBuilder builder;
    
             AnnotationsNestedImpl (){
        this.builder = new ClassRefBuilder(this);
        }
             AnnotationsNestedImpl ( ClassRef item){
        this.builder = new ClassRefBuilder(this, item);
        }
    
            public N endAnnotation(){
            return and();
        }
            public N and(){
            return (N) MethodFluentImpl.this.addToAnnotations(builder.build());
        }
    
}
    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<ParametersNested<N>> implements ParametersNested<N>{

        private final TypeParamDefBuilder builder;
    
             ParametersNestedImpl (){
        this.builder = new TypeParamDefBuilder(this);
        }
             ParametersNestedImpl ( TypeParamDef item){
        this.builder = new TypeParamDefBuilder(this, item);
        }
    
            public N endParameter(){
            return and();
        }
            public N and(){
            return (N) MethodFluentImpl.this.addToParameters(builder.build());
        }
    
}
    public class ArgumentsNestedImpl<N> extends PropertyFluentImpl<ArgumentsNested<N>> implements ArgumentsNested<N>{

        private final PropertyBuilder builder;
    
             ArgumentsNestedImpl (){
        this.builder = new PropertyBuilder(this);
        }
             ArgumentsNestedImpl ( Property item){
        this.builder = new PropertyBuilder(this, item);
        }
    
            public N endArgument(){
            return and();
        }
            public N and(){
            return (N) MethodFluentImpl.this.addToArguments(builder.build());
        }
    
}
    public class ExceptionsNestedImpl<N> extends ClassRefFluentImpl<ExceptionsNested<N>> implements ExceptionsNested<N>{

        private final ClassRefBuilder builder;
    
             ExceptionsNestedImpl (){
        this.builder = new ClassRefBuilder(this);
        }
             ExceptionsNestedImpl ( ClassRef item){
        this.builder = new ClassRefBuilder(this, item);
        }
    
            public N endException(){
            return and();
        }
            public N and(){
            return (N) MethodFluentImpl.this.addToExceptions(builder.build());
        }
    
}
    public class BlockNestedImpl<N> extends BlockFluentImpl<BlockNested<N>> implements BlockNested<N>{

        private final BlockBuilder builder;
    
             BlockNestedImpl (){
        this.builder = new BlockBuilder(this);
        }
             BlockNestedImpl ( Block item){
        this.builder = new BlockBuilder(this, item);
        }
    
            public N endBlock(){
            return and();
        }
            public N and(){
            return (N) MethodFluentImpl.this.withBlock(builder.build());
        }
    
}


}
