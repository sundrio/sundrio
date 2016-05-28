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

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

import java.util.List;
import java.util.Set;

public interface TypeDefFluent<A extends TypeDefFluent<A>> extends Fluent<A>,ModifierSupportFluent<A>{


    public Kind getKind();    public A withKind(Kind kind);    public String getPackageName();    public A withPackageName(String packageName);    public String getName();    public A withName(String name);    public A addToAnnotations(ClassRef... items);    public A removeFromAnnotations(ClassRef... items);    public Set<ClassRef> getAnnotations();    public A withAnnotations(Set<ClassRef> annotations);    public A withAnnotations(ClassRef... annotations);    public AnnotationsNested<A> addNewAnnotation();    public AnnotationsNested<A> addNewAnnotationLike(ClassRef item);    public A addToExtendsList(ClassRef... items);    public A removeFromExtendsList(ClassRef... items);    public Set<ClassRef> getExtendsList();    public A withExtendsList(Set<ClassRef> extendsList);    public A withExtendsList(ClassRef... extendsList);    public ExtendsListNested<A> addNewExtendsList();    public ExtendsListNested<A> addNewExtendsListLike(ClassRef item);    public A addToImplementsList(ClassRef... items);    public A removeFromImplementsList(ClassRef... items);    public Set<ClassRef> getImplementsList();    public A withImplementsList(Set<ClassRef> implementsList);    public A withImplementsList(ClassRef... implementsList);    public ImplementsListNested<A> addNewImplementsList();    public ImplementsListNested<A> addNewImplementsListLike(ClassRef item);    public A addToParameters(TypeParamDef... items);    public A removeFromParameters(TypeParamDef... items);    public List<TypeParamDef> getParameters();    public A withParameters(List<TypeParamDef> parameters);    public A withParameters(TypeParamDef... parameters);    public ParametersNested<A> addNewParameter();    public ParametersNested<A> addNewParameterLike(TypeParamDef item);    public A addToProperties(Property... items);    public A removeFromProperties(Property... items);    public Set<Property> getProperties();    public A withProperties(Set<Property> properties);    public A withProperties(Property... properties);    public PropertiesNested<A> addNewProperty();    public PropertiesNested<A> addNewPropertyLike(Property item);    public A addToConstructors(Method... items);    public A removeFromConstructors(Method... items);    public Set<Method> getConstructors();    public A withConstructors(Set<Method> constructors);    public A withConstructors(Method... constructors);    public ConstructorsNested<A> addNewConstructor();    public ConstructorsNested<A> addNewConstructorLike(Method item);    public A addToMethods(Method... items);    public A removeFromMethods(Method... items);    public Set<Method> getMethods();    public A withMethods(Set<Method> methods);    public A withMethods(Method... methods);    public MethodsNested<A> addNewMethod();    public MethodsNested<A> addNewMethodLike(Method item);    public A addToInnerTypes(TypeDef... items);    public A removeFromInnerTypes(TypeDef... items);    public Set<TypeDef> getInnerTypes();    public A withInnerTypes(Set<TypeDef> innerTypes);    public A withInnerTypes(TypeDef... innerTypes);    public InnerTypesNested<A> addNewInnerType();    public InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);
    public interface AnnotationsNested<N> extends Nested<N>,ClassRefFluent<AnnotationsNested<N>>{
            public N endAnnotation();            public N and();        
}

    public interface ExtendsListNested<N> extends Nested<N>,ClassRefFluent<ExtendsListNested<N>>{
            public N endExtendsList();            public N and();        
}

    public interface ImplementsListNested<N> extends Nested<N>,ClassRefFluent<ImplementsListNested<N>>{
            public N endImplementsList();            public N and();        
}

    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<ParametersNested<N>>{
            public N endParameter();            public N and();        
}

    public interface PropertiesNested<N> extends Nested<N>,PropertyFluent<PropertiesNested<N>>{
            public N endProperty();            public N and();        
}

    public interface ConstructorsNested<N> extends Nested<N>,MethodFluent<ConstructorsNested<N>>{
            public N endConstructor();            public N and();        
}

    public interface MethodsNested<N> extends Nested<N>,MethodFluent<MethodsNested<N>>{
            public N endMethod();            public N and();        
}

    public interface InnerTypesNested<N> extends Nested<N>,TypeDefFluent<InnerTypesNested<N>>{
            public N endInnerType();            public N and();        
}


}
