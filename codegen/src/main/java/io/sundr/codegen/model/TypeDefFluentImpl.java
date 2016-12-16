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

public class TypeDefFluentImpl<A extends TypeDefFluent<A>> extends ModifierSupportFluentImpl<A> implements TypeDefFluent<A>{

    private Kind kind;
    private String packageName;
    private String name;
    private List<VisitableBuilder<? extends AnnotationRef,?>> annotations =  new ArrayList<VisitableBuilder<? extends AnnotationRef,?>>();
    private List<VisitableBuilder<? extends ClassRef,?>> extendsList =  new ArrayList<VisitableBuilder<? extends ClassRef,?>>();
    private List<VisitableBuilder<? extends ClassRef,?>> implementsList =  new ArrayList<VisitableBuilder<? extends ClassRef,?>>();
    private List<VisitableBuilder<? extends TypeParamDef,?>> parameters =  new ArrayList<VisitableBuilder<? extends TypeParamDef,?>>();
    private List<VisitableBuilder<? extends Property,?>> properties =  new ArrayList<VisitableBuilder<? extends Property,?>>();
    private List<VisitableBuilder<? extends Method,?>> constructors =  new ArrayList<VisitableBuilder<? extends Method,?>>();
    private List<VisitableBuilder<? extends Method,?>> methods =  new ArrayList<VisitableBuilder<? extends Method,?>>();
    private VisitableBuilder<? extends TypeDef,?> outerType;
    private List<VisitableBuilder<? extends TypeDef,?>> innerTypes =  new ArrayList<VisitableBuilder<? extends TypeDef,?>>();

    public TypeDefFluentImpl(){
    }
    public TypeDefFluentImpl(TypeDef instance){
            this.withKind(instance.getKind()); 
            this.withPackageName(instance.getPackageName()); 
            this.withName(instance.getName()); 
            this.withAnnotations(instance.getAnnotations()); 
            this.withExtendsList(instance.getExtendsList()); 
            this.withImplementsList(instance.getImplementsList()); 
            this.withParameters(instance.getParameters()); 
            this.withProperties(instance.getProperties()); 
            this.withConstructors(instance.getConstructors()); 
            this.withMethods(instance.getMethods()); 
            this.withOuterType(instance.getOuterType()); 
            this.withInnerTypes(instance.getInnerTypes()); 
            this.withModifiers(instance.getModifiers()); 
            this.withAttributes(instance.getAttributes()); 
    }

    public Kind getKind(){
            return this.kind;
    }

    public A withKind(Kind kind){
            this.kind=kind; return (A) this;
    }

    public Boolean hasKind(){
            return this.kind!=null;
    }

    public String getPackageName(){
            return this.packageName;
    }

    public A withPackageName(String packageName){
            this.packageName=packageName; return (A) this;
    }

    public Boolean hasPackageName(){
            return this.packageName!=null;
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
 */
@Deprecated public List<AnnotationRef> getAnnotations(){
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

    public A addToExtendsList(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.extendsList.add(builder); return (A)this;
    }

    public A setToExtendsList(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.extendsList.add(builder); return (A)this;
    }

    public A addToExtendsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.extendsList.add(builder);} return (A)this;
    }

    public A addAllToExtendsList(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.extendsList.add(builder);} return (A)this;
    }

    public A removeFromExtendsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.extendsList.remove(builder);} return (A)this;
    }

    public A removeAllFromExtendsList(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.extendsList.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildExtendsList instead.
 */
@Deprecated public List<ClassRef> getExtendsList(){
            return build(extendsList);
    }

    public List<ClassRef> buildExtendsList(){
            return build(extendsList);
    }

    public ClassRef buildExtendsList(int index){
            return this.extendsList.get(index).build();
    }

    public ClassRef buildFirstExtendsList(){
            return this.extendsList.get(0).build();
    }

    public ClassRef buildLastExtendsList(){
            return this.extendsList.get(extendsList.size() - 1).build();
    }

    public ClassRef buildMatchingExtendsList(Predicate<Builder<? extends ClassRef>> predicate){
            for (Builder<? extends ClassRef> item: extendsList) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withExtendsList(List<ClassRef> extendsList){
            _visitables.removeAll(this.extendsList);
            this.extendsList.clear();
            if (extendsList != null) {for (ClassRef item : extendsList){this.addToExtendsList(item);}} return (A) this;
    }

    public A withExtendsList(ClassRef... extendsList){
            this.extendsList.clear(); if (extendsList != null) {for (ClassRef item :extendsList){ this.addToExtendsList(item);}} return (A) this;
    }

    public Boolean hasExtendsList(){
            return extendsList!= null && !extendsList.isEmpty();
    }

    public ExtendsListNested<A> addNewExtendsList(){
            return new ExtendsListNestedImpl();
    }

    public ExtendsListNested<A> addNewExtendsListLike(ClassRef item){
            return new ExtendsListNestedImpl(-1, item);
    }

    public A addToImplementsList(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.implementsList.add(builder); return (A)this;
    }

    public A setToImplementsList(int index,ClassRef item){
            ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.implementsList.add(builder); return (A)this;
    }

    public A addToImplementsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.implementsList.add(builder);} return (A)this;
    }

    public A addAllToImplementsList(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.add(builder);this.implementsList.add(builder);} return (A)this;
    }

    public A removeFromImplementsList(ClassRef... items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.implementsList.remove(builder);} return (A)this;
    }

    public A removeAllFromImplementsList(Collection<ClassRef> items){
            for (ClassRef item : items) {ClassRefBuilder builder = new ClassRefBuilder(item);_visitables.remove(builder);this.implementsList.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildImplementsList instead.
 */
@Deprecated public List<ClassRef> getImplementsList(){
            return build(implementsList);
    }

    public List<ClassRef> buildImplementsList(){
            return build(implementsList);
    }

    public ClassRef buildImplementsList(int index){
            return this.implementsList.get(index).build();
    }

    public ClassRef buildFirstImplementsList(){
            return this.implementsList.get(0).build();
    }

    public ClassRef buildLastImplementsList(){
            return this.implementsList.get(implementsList.size() - 1).build();
    }

    public ClassRef buildMatchingImplementsList(Predicate<Builder<? extends ClassRef>> predicate){
            for (Builder<? extends ClassRef> item: implementsList) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withImplementsList(List<ClassRef> implementsList){
            _visitables.removeAll(this.implementsList);
            this.implementsList.clear();
            if (implementsList != null) {for (ClassRef item : implementsList){this.addToImplementsList(item);}} return (A) this;
    }

    public A withImplementsList(ClassRef... implementsList){
            this.implementsList.clear(); if (implementsList != null) {for (ClassRef item :implementsList){ this.addToImplementsList(item);}} return (A) this;
    }

    public Boolean hasImplementsList(){
            return implementsList!= null && !implementsList.isEmpty();
    }

    public ImplementsListNested<A> addNewImplementsList(){
            return new ImplementsListNestedImpl();
    }

    public ImplementsListNested<A> addNewImplementsListLike(ClassRef item){
            return new ImplementsListNestedImpl(-1, item);
    }

    public A addToParameters(int index,TypeParamDef item){
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder); return (A)this;
    }

    public A setToParameters(int index,TypeParamDef item){
            TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder); return (A)this;
    }

    public A addToParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A addAllToParameters(Collection<TypeParamDef> items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.add(builder);this.parameters.add(builder);} return (A)this;
    }

    public A removeFromParameters(TypeParamDef... items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }

    public A removeAllFromParameters(Collection<TypeParamDef> items){
            for (TypeParamDef item : items) {TypeParamDefBuilder builder = new TypeParamDefBuilder(item);_visitables.remove(builder);this.parameters.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildParameters instead.
 */
@Deprecated public List<TypeParamDef> getParameters(){
            return build(parameters);
    }

    public List<TypeParamDef> buildParameters(){
            return build(parameters);
    }

    public TypeParamDef buildParameter(int index){
            return this.parameters.get(index).build();
    }

    public TypeParamDef buildFirstParameter(){
            return this.parameters.get(0).build();
    }

    public TypeParamDef buildLastParameter(){
            return this.parameters.get(parameters.size() - 1).build();
    }

    public TypeParamDef buildMatchingParameter(Predicate<Builder<? extends TypeParamDef>> predicate){
            for (Builder<? extends TypeParamDef> item: parameters) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withParameters(List<TypeParamDef> parameters){
            _visitables.removeAll(this.parameters);
            this.parameters.clear();
            if (parameters != null) {for (TypeParamDef item : parameters){this.addToParameters(item);}} return (A) this;
    }

    public A withParameters(TypeParamDef... parameters){
            this.parameters.clear(); if (parameters != null) {for (TypeParamDef item :parameters){ this.addToParameters(item);}} return (A) this;
    }

    public Boolean hasParameters(){
            return parameters!= null && !parameters.isEmpty();
    }

    public ParametersNested<A> addNewParameter(){
            return new ParametersNestedImpl();
    }

    public ParametersNested<A> addNewParameterLike(TypeParamDef item){
            return new ParametersNestedImpl(-1, item);
    }

    public A addToProperties(int index,Property item){
            PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.properties.add(builder); return (A)this;
    }

    public A setToProperties(int index,Property item){
            PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.properties.add(builder); return (A)this;
    }

    public A addToProperties(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.properties.add(builder);} return (A)this;
    }

    public A addAllToProperties(Collection<Property> items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.add(builder);this.properties.add(builder);} return (A)this;
    }

    public A removeFromProperties(Property... items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.properties.remove(builder);} return (A)this;
    }

    public A removeAllFromProperties(Collection<Property> items){
            for (Property item : items) {PropertyBuilder builder = new PropertyBuilder(item);_visitables.remove(builder);this.properties.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildProperties instead.
 */
@Deprecated public List<Property> getProperties(){
            return build(properties);
    }

    public List<Property> buildProperties(){
            return build(properties);
    }

    public Property buildProperty(int index){
            return this.properties.get(index).build();
    }

    public Property buildFirstProperty(){
            return this.properties.get(0).build();
    }

    public Property buildLastProperty(){
            return this.properties.get(properties.size() - 1).build();
    }

    public Property buildMatchingProperty(Predicate<Builder<? extends Property>> predicate){
            for (Builder<? extends Property> item: properties) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withProperties(List<Property> properties){
            _visitables.removeAll(this.properties);
            this.properties.clear();
            if (properties != null) {for (Property item : properties){this.addToProperties(item);}} return (A) this;
    }

    public A withProperties(Property... properties){
            this.properties.clear(); if (properties != null) {for (Property item :properties){ this.addToProperties(item);}} return (A) this;
    }

    public Boolean hasProperties(){
            return properties!= null && !properties.isEmpty();
    }

    public PropertiesNested<A> addNewProperty(){
            return new PropertiesNestedImpl();
    }

    public PropertiesNested<A> addNewPropertyLike(Property item){
            return new PropertiesNestedImpl(-1, item);
    }

    public A addToConstructors(int index,Method item){
            MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.constructors.add(builder); return (A)this;
    }

    public A setToConstructors(int index,Method item){
            MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.constructors.add(builder); return (A)this;
    }

    public A addToConstructors(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.constructors.add(builder);} return (A)this;
    }

    public A addAllToConstructors(Collection<Method> items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.constructors.add(builder);} return (A)this;
    }

    public A removeFromConstructors(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.constructors.remove(builder);} return (A)this;
    }

    public A removeAllFromConstructors(Collection<Method> items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.constructors.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildConstructors instead.
 */
@Deprecated public List<Method> getConstructors(){
            return build(constructors);
    }

    public List<Method> buildConstructors(){
            return build(constructors);
    }

    public Method buildConstructor(int index){
            return this.constructors.get(index).build();
    }

    public Method buildFirstConstructor(){
            return this.constructors.get(0).build();
    }

    public Method buildLastConstructor(){
            return this.constructors.get(constructors.size() - 1).build();
    }

    public Method buildMatchingConstructor(Predicate<Builder<? extends Method>> predicate){
            for (Builder<? extends Method> item: constructors) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withConstructors(List<Method> constructors){
            _visitables.removeAll(this.constructors);
            this.constructors.clear();
            if (constructors != null) {for (Method item : constructors){this.addToConstructors(item);}} return (A) this;
    }

    public A withConstructors(Method... constructors){
            this.constructors.clear(); if (constructors != null) {for (Method item :constructors){ this.addToConstructors(item);}} return (A) this;
    }

    public Boolean hasConstructors(){
            return constructors!= null && !constructors.isEmpty();
    }

    public ConstructorsNested<A> addNewConstructor(){
            return new ConstructorsNestedImpl();
    }

    public ConstructorsNested<A> addNewConstructorLike(Method item){
            return new ConstructorsNestedImpl(-1, item);
    }

    public A addToMethods(int index,Method item){
            MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.methods.add(builder); return (A)this;
    }

    public A setToMethods(int index,Method item){
            MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.methods.add(builder); return (A)this;
    }

    public A addToMethods(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.methods.add(builder);} return (A)this;
    }

    public A addAllToMethods(Collection<Method> items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.add(builder);this.methods.add(builder);} return (A)this;
    }

    public A removeFromMethods(Method... items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.methods.remove(builder);} return (A)this;
    }

    public A removeAllFromMethods(Collection<Method> items){
            for (Method item : items) {MethodBuilder builder = new MethodBuilder(item);_visitables.remove(builder);this.methods.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildMethods instead.
 */
@Deprecated public List<Method> getMethods(){
            return build(methods);
    }

    public List<Method> buildMethods(){
            return build(methods);
    }

    public Method buildMethod(int index){
            return this.methods.get(index).build();
    }

    public Method buildFirstMethod(){
            return this.methods.get(0).build();
    }

    public Method buildLastMethod(){
            return this.methods.get(methods.size() - 1).build();
    }

    public Method buildMatchingMethod(Predicate<Builder<? extends Method>> predicate){
            for (Builder<? extends Method> item: methods) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withMethods(List<Method> methods){
            _visitables.removeAll(this.methods);
            this.methods.clear();
            if (methods != null) {for (Method item : methods){this.addToMethods(item);}} return (A) this;
    }

    public A withMethods(Method... methods){
            this.methods.clear(); if (methods != null) {for (Method item :methods){ this.addToMethods(item);}} return (A) this;
    }

    public Boolean hasMethods(){
            return methods!= null && !methods.isEmpty();
    }

    public MethodsNested<A> addNewMethod(){
            return new MethodsNestedImpl();
    }

    public MethodsNested<A> addNewMethodLike(Method item){
            return new MethodsNestedImpl(-1, item);
    }


/**
 * This method has been deprecated, please use method buildOuterType instead.
 */
@Deprecated public TypeDef getOuterType(){
            return this.outerType!=null?this.outerType.build():null;
    }

    public TypeDef buildOuterType(){
            return this.outerType!=null?this.outerType.build():null;
    }

    public A withOuterType(TypeDef outerType){
            _visitables.remove(this.outerType);
            if (outerType!=null){ this.outerType= new TypeDefBuilder(outerType); _visitables.add(this.outerType);} return (A) this;
    }

    public Boolean hasOuterType(){
            return this.outerType!=null;
    }

    public OuterTypeNested<A> withNewOuterType(){
            return new OuterTypeNestedImpl();
    }

    public OuterTypeNested<A> withNewOuterTypeLike(TypeDef item){
            return new OuterTypeNestedImpl(item);
    }

    public OuterTypeNested<A> editOuterType(){
            return withNewOuterTypeLike(getOuterType());
    }

    public OuterTypeNested<A> editOrNewOuterType(){
            return withNewOuterTypeLike(getOuterType() != null ? getOuterType(): new TypeDefBuilder().build());
    }

    public OuterTypeNested<A> editOrNewOuterTypeLike(TypeDef item){
            return withNewOuterTypeLike(getOuterType() != null ? getOuterType(): item);
    }

    public A addToInnerTypes(int index,TypeDef item){
            TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.add(builder);this.innerTypes.add(builder); return (A)this;
    }

    public A setToInnerTypes(int index,TypeDef item){
            TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.add(builder);this.innerTypes.add(builder); return (A)this;
    }

    public A addToInnerTypes(TypeDef... items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.add(builder);this.innerTypes.add(builder);} return (A)this;
    }

    public A addAllToInnerTypes(Collection<TypeDef> items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.add(builder);this.innerTypes.add(builder);} return (A)this;
    }

    public A removeFromInnerTypes(TypeDef... items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.remove(builder);this.innerTypes.remove(builder);} return (A)this;
    }

    public A removeAllFromInnerTypes(Collection<TypeDef> items){
            for (TypeDef item : items) {TypeDefBuilder builder = new TypeDefBuilder(item);_visitables.remove(builder);this.innerTypes.remove(builder);} return (A)this;
    }


/**
 * This method has been deprecated, please use method buildInnerTypes instead.
 */
@Deprecated public List<TypeDef> getInnerTypes(){
            return build(innerTypes);
    }

    public List<TypeDef> buildInnerTypes(){
            return build(innerTypes);
    }

    public TypeDef buildInnerType(int index){
            return this.innerTypes.get(index).build();
    }

    public TypeDef buildFirstInnerType(){
            return this.innerTypes.get(0).build();
    }

    public TypeDef buildLastInnerType(){
            return this.innerTypes.get(innerTypes.size() - 1).build();
    }

    public TypeDef buildMatchingInnerType(Predicate<Builder<? extends TypeDef>> predicate){
            for (Builder<? extends TypeDef> item: innerTypes) { if(predicate.apply(item)){return item.build();} } return null;
    }

    public A withInnerTypes(List<TypeDef> innerTypes){
            _visitables.removeAll(this.innerTypes);
            this.innerTypes.clear();
            if (innerTypes != null) {for (TypeDef item : innerTypes){this.addToInnerTypes(item);}} return (A) this;
    }

    public A withInnerTypes(TypeDef... innerTypes){
            this.innerTypes.clear(); if (innerTypes != null) {for (TypeDef item :innerTypes){ this.addToInnerTypes(item);}} return (A) this;
    }

    public Boolean hasInnerTypes(){
            return innerTypes!= null && !innerTypes.isEmpty();
    }

    public InnerTypesNested<A> addNewInnerType(){
            return new InnerTypesNestedImpl();
    }

    public InnerTypesNested<A> addNewInnerTypeLike(TypeDef item){
            return new InnerTypesNestedImpl(-1, item);
    }

    public boolean equals(Object o){
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
            if (outerType != null ? !outerType.equals(that.outerType) :that.outerType != null) return false;
            if (innerTypes != null ? !innerTypes.equals(that.innerTypes) :that.innerTypes != null) return false;
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
            return (N) TypeDefFluentImpl.this.addToAnnotations(index, builder.build());
    }
    public N endAnnotation(){
            return and();
    }

}
    public class ExtendsListNestedImpl<N> extends ClassRefFluentImpl<ExtendsListNested<N>> implements ExtendsListNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;

            ExtendsListNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ExtendsListNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.addToExtendsList(index, builder.build());
    }
    public N endExtendsList(){
            return and();
    }

}
    public class ImplementsListNestedImpl<N> extends ClassRefFluentImpl<ImplementsListNested<N>> implements ImplementsListNested<N>,Nested<N>{

            private final ClassRefBuilder builder;
        private final int index;

            ImplementsListNestedImpl(int index,ClassRef item){
                    this.index = index;
                    this.builder = new ClassRefBuilder(this, item);
            }
            ImplementsListNestedImpl(){
                    this.index = -1;
                    this.builder = new ClassRefBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.addToImplementsList(index, builder.build());
    }
    public N endImplementsList(){
            return and();
    }

}
    public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<ParametersNested<N>> implements ParametersNested<N>,Nested<N>{

            private final TypeParamDefBuilder builder;
        private final int index;

            ParametersNestedImpl(int index,TypeParamDef item){
                    this.index = index;
                    this.builder = new TypeParamDefBuilder(this, item);
            }
            ParametersNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeParamDefBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.addToParameters(index, builder.build());
    }
    public N endParameter(){
            return and();
    }

}
    public class PropertiesNestedImpl<N> extends PropertyFluentImpl<PropertiesNested<N>> implements PropertiesNested<N>,Nested<N>{

            private final PropertyBuilder builder;
        private final int index;

            PropertiesNestedImpl(int index,Property item){
                    this.index = index;
                    this.builder = new PropertyBuilder(this, item);
            }
            PropertiesNestedImpl(){
                    this.index = -1;
                    this.builder = new PropertyBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.addToProperties(index, builder.build());
    }
    public N endProperty(){
            return and();
    }

}
    public class ConstructorsNestedImpl<N> extends MethodFluentImpl<ConstructorsNested<N>> implements ConstructorsNested<N>,Nested<N>{

            private final MethodBuilder builder;
        private final int index;

            ConstructorsNestedImpl(int index,Method item){
                    this.index = index;
                    this.builder = new MethodBuilder(this, item);
            }
            ConstructorsNestedImpl(){
                    this.index = -1;
                    this.builder = new MethodBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.addToConstructors(index, builder.build());
    }
    public N endConstructor(){
            return and();
    }

}
    public class MethodsNestedImpl<N> extends MethodFluentImpl<MethodsNested<N>> implements MethodsNested<N>,Nested<N>{

            private final MethodBuilder builder;
        private final int index;

            MethodsNestedImpl(int index,Method item){
                    this.index = index;
                    this.builder = new MethodBuilder(this, item);
            }
            MethodsNestedImpl(){
                    this.index = -1;
                    this.builder = new MethodBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.addToMethods(index, builder.build());
    }
    public N endMethod(){
            return and();
    }

}
    public class OuterTypeNestedImpl<N> extends TypeDefFluentImpl<OuterTypeNested<N>> implements OuterTypeNested<N>,Nested<N>{

            private final TypeDefBuilder builder;

            OuterTypeNestedImpl(TypeDef item){
                    this.builder = new TypeDefBuilder(this, item);
            }
            OuterTypeNestedImpl(){
                    this.builder = new TypeDefBuilder(this);
            }

    public N and(){
            return (N) TypeDefFluentImpl.this.withOuterType(builder.build());
    }
    public N endOuterType(){
            return and();
    }

}
    public class InnerTypesNestedImpl<N> extends TypeDefFluentImpl<InnerTypesNested<N>> implements InnerTypesNested<N>,Nested<N>{

            private final TypeDefBuilder builder;
        private final int index;
    
            InnerTypesNestedImpl(int index,TypeDef item){
                    this.index = index;
                    this.builder = new TypeDefBuilder(this, item);
            }
            InnerTypesNestedImpl(){
                    this.index = -1;
                    this.builder = new TypeDefBuilder(this);
            }
    
    public N and(){
            return (N) TypeDefFluentImpl.this.addToInnerTypes(index, builder.build());
    }
    public N endInnerType(){
            return and();
    }

}


}
