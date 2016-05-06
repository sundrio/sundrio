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

public class TypeDefFluentImpl<A extends TypeDefFluent<A>> extends ModifierSupportFluentImpl<A> implements TypeDefFluent<A>{

     Kind kind;     String packageName;     String name;     Set<VisitableBuilder<ClassRef, ?>> annotations = new LinkedHashSet();     Set<VisitableBuilder<ClassRef, ?>> extendsList = new LinkedHashSet();     Set<VisitableBuilder<ClassRef, ?>> implementsList = new LinkedHashSet();     List<VisitableBuilder<TypeParamDef, ?>> parameters = new ArrayList();     Set<VisitableBuilder<Property, ?>> properties = new LinkedHashSet();     Set<VisitableBuilder<Method, ?>> constructors = new LinkedHashSet();     Set<VisitableBuilder<Method, ?>> methods = new LinkedHashSet();
public TypeDefFluentImpl(){
    
}
public TypeDefFluentImpl( TypeDef instance ){
    this.withKind(instance.getKind()); this.withPackageName(instance.getPackageName()); this.withName(instance.getName()); this.withAnnotations(instance.getAnnotations()); this.withExtendsList(instance.getExtendsList()); this.withImplementsList(instance.getImplementsList()); this.withParameters(instance.getParameters()); this.withProperties(instance.getProperties()); this.withConstructors(instance.getConstructors()); this.withMethods(instance.getMethods()); this.withModifiers(instance.getModifiers()); this.withAttributes(instance.getAttributes()); 
}

    public Kind getKind(){
    return this.kind;
    }
    public A withKind( Kind kind){
    this.kind=kind; return (A) this;
    }
    public String getPackageName(){
    return this.packageName;
    }
    public A withPackageName( String packageName){
    this.packageName=packageName; return (A) this;
    }
    public String getName(){
    return this.name;
    }
    public A withName( String name){
    this.name=name; return (A) this;
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
    public A addToExtendsList( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.extendsList.add(builder);} return (A)this;
    }
    public A removeFromExtendsList( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.extendsList.remove(builder);} return (A)this;
    }
    public Set<ClassRef> getExtendsList(){
    return build(extendsList);
    }
    public A withExtendsList( Set<ClassRef> extendsList){
    this.extendsList.clear();if (extendsList != null) {for (ClassRef item : extendsList){this.addToExtendsList(item);}} return (A) this;
    }
    public A withExtendsList( ClassRef ...extendsList){
    this.extendsList.clear(); if (extendsList != null) {for (ClassRef item :extendsList){ this.addToExtendsList(item);}} return (A) this;
    }
    public ExtendsListNested<A> addNewExtendsList(){
    return new ExtendsListNestedImpl();
    }
    public ExtendsListNested<A> addNewExtendsListLike( ClassRef item){
    return new ExtendsListNestedImpl(item);
    }
    public A addToImplementsList( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.implementsList.add(builder);} return (A)this;
    }
    public A removeFromImplementsList( ClassRef ...items){
    for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.implementsList.remove(builder);} return (A)this;
    }
    public Set<ClassRef> getImplementsList(){
    return build(implementsList);
    }
    public A withImplementsList( Set<ClassRef> implementsList){
    this.implementsList.clear();if (implementsList != null) {for (ClassRef item : implementsList){this.addToImplementsList(item);}} return (A) this;
    }
    public A withImplementsList( ClassRef ...implementsList){
    this.implementsList.clear(); if (implementsList != null) {for (ClassRef item :implementsList){ this.addToImplementsList(item);}} return (A) this;
    }
    public ImplementsListNested<A> addNewImplementsList(){
    return new ImplementsListNestedImpl();
    }
    public ImplementsListNested<A> addNewImplementsListLike( ClassRef item){
    return new ImplementsListNestedImpl(item);
    }
    public A addToParameters( TypeParamDef ...items){
    for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }
    public A removeFromParameters( TypeParamDef ...items){
    for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }
    public List<TypeParamDef> getParameters(){
    return build(parameters);
    }
    public A withParameters( List<TypeParamDef> parameters){
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
    public A addToProperties( Property ...items){
    for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.properties.add(builder);} return (A)this;
    }
    public A removeFromProperties( Property ...items){
    for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.properties.remove(builder);} return (A)this;
    }
    public Set<Property> getProperties(){
    return build(properties);
    }
    public A withProperties( Set<Property> properties){
    this.properties.clear();if (properties != null) {for (Property item : properties){this.addToProperties(item);}} return (A) this;
    }
    public A withProperties( Property ...properties){
    this.properties.clear(); if (properties != null) {for (Property item :properties){ this.addToProperties(item);}} return (A) this;
    }
    public PropertiesNested<A> addNewProperty(){
    return new PropertiesNestedImpl();
    }
    public PropertiesNested<A> addNewPropertyLike( Property item){
    return new PropertiesNestedImpl(item);
    }
    public A addToConstructors( Method ...items){
    for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.constructors.add(builder);} return (A)this;
    }
    public A removeFromConstructors( Method ...items){
    for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.constructors.remove(builder);} return (A)this;
    }
    public Set<Method> getConstructors(){
    return build(constructors);
    }
    public A withConstructors( Set<Method> constructors){
    this.constructors.clear();if (constructors != null) {for (Method item : constructors){this.addToConstructors(item);}} return (A) this;
    }
    public A withConstructors( Method ...constructors){
    this.constructors.clear(); if (constructors != null) {for (Method item :constructors){ this.addToConstructors(item);}} return (A) this;
    }
    public ConstructorsNested<A> addNewConstructor(){
    return new ConstructorsNestedImpl();
    }
    public ConstructorsNested<A> addNewConstructorLike( Method item){
    return new ConstructorsNestedImpl(item);
    }
    public A addToMethods( Method ...items){
    for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.methods.add(builder);} return (A)this;
    }
    public A removeFromMethods( Method ...items){
    for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.methods.remove(builder);} return (A)this;
    }
    public Set<Method> getMethods(){
    return build(methods);
    }
    public A withMethods( Set<Method> methods){
    this.methods.clear();if (methods != null) {for (Method item : methods){this.addToMethods(item);}} return (A) this;
    }
    public A withMethods( Method ...methods){
    this.methods.clear(); if (methods != null) {for (Method item :methods){ this.addToMethods(item);}} return (A) this;
    }
    public MethodsNested<A> addNewMethod(){
    return new MethodsNestedImpl();
    }
    public MethodsNested<A> addNewMethodLike( Method item){
    return new MethodsNestedImpl(item);
    }
    public boolean equals( Object o){
    
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;
if (!super.equals(o)) return false;
TypeDefFluentImpl that = (TypeDefFluentImpl) o;
if (kind != null ? !kind.equals(that.kind) :that.kind != null) return false;
if (packageName != null ? !packageName.equals(that.packageName) :that.packageName != null) return false;
if (name != null ? !name.equals(that.name) :that.name != null) return false;
if (annotations != null ? !annotations.equals(that.annotations) :that.annotations != null) return false;
if (extendsList != null ? !extendsList.equals(that.extendsList) :that.extendsList != null) return false;
if (implementsList != null ? !implementsList.equals(that.implementsList) :that.implementsList != null) return false;
if (parameters != null ? !parameters.equals(that.parameters) :that.parameters != null) return false;
if (properties != null ? !properties.equals(that.properties) :that.properties != null) return false;
if (constructors != null ? !constructors.equals(that.constructors) :that.constructors != null) return false;
if (methods != null ? !methods.equals(that.methods) :that.methods != null) return false;
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
            return (N) TypeDefFluentImpl.this.addToAnnotations(builder.build());
        }
    
}
    public class ExtendsListNestedImpl<N> extends ClassRefFluentImpl<ExtendsListNested<N>> implements ExtendsListNested<N>{

        private final ClassRefBuilder builder;
    
             ExtendsListNestedImpl (){
        this.builder = new ClassRefBuilder(this);
        }
             ExtendsListNestedImpl ( ClassRef item){
        this.builder = new ClassRefBuilder(this, item);
        }
    
            public N endExtendsList(){
            return and();
        }
            public N and(){
            return (N) TypeDefFluentImpl.this.addToExtendsList(builder.build());
        }
    
}
    public class ImplementsListNestedImpl<N> extends ClassRefFluentImpl<ImplementsListNested<N>> implements ImplementsListNested<N>{

        private final ClassRefBuilder builder;
    
             ImplementsListNestedImpl (){
        this.builder = new ClassRefBuilder(this);
        }
             ImplementsListNestedImpl ( ClassRef item){
        this.builder = new ClassRefBuilder(this, item);
        }
    
            public N endImplementsList(){
            return and();
        }
            public N and(){
            return (N) TypeDefFluentImpl.this.addToImplementsList(builder.build());
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
            return (N) TypeDefFluentImpl.this.addToParameters(builder.build());
        }
    
}
    public class PropertiesNestedImpl<N> extends PropertyFluentImpl<PropertiesNested<N>> implements PropertiesNested<N>{

        private final PropertyBuilder builder;
    
             PropertiesNestedImpl (){
        this.builder = new PropertyBuilder(this);
        }
             PropertiesNestedImpl ( Property item){
        this.builder = new PropertyBuilder(this, item);
        }
    
            public N endProperty(){
            return and();
        }
            public N and(){
            return (N) TypeDefFluentImpl.this.addToProperties(builder.build());
        }
    
}
    public class ConstructorsNestedImpl<N> extends MethodFluentImpl<ConstructorsNested<N>> implements ConstructorsNested<N>{

        private final MethodBuilder builder;
    
             ConstructorsNestedImpl (){
        this.builder = new MethodBuilder(this);
        }
             ConstructorsNestedImpl ( Method item){
        this.builder = new MethodBuilder(this, item);
        }
    
            public N endConstructor(){
            return and();
        }
            public N and(){
            return (N) TypeDefFluentImpl.this.addToConstructors(builder.build());
        }
    
}
    public class MethodsNestedImpl<N> extends MethodFluentImpl<MethodsNested<N>> implements MethodsNested<N>{

        private final MethodBuilder builder;
    
             MethodsNestedImpl (){
        this.builder = new MethodBuilder(this);
        }
             MethodsNestedImpl ( Method item){
        this.builder = new MethodBuilder(this, item);
        }
    
            public N endMethod(){
            return and();
        }
            public N and(){
            return (N) TypeDefFluentImpl.this.addToMethods(builder.build());
        }
    
}


}
