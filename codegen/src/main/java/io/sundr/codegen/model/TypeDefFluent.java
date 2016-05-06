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

import java.util.Set;

public interface TypeDefFluent<A extends TypeDefFluent<A>> extends Fluent<A>,ModifierSupportFluent<A>{


    public Kind getKind();    public A withKind(Kind kind);    public String getPackageName();    public A withPackageName(String packageName);    public String getName();    public A withName(String name);    public A addToAnnotations(TypeRef... items);    public A removeFromAnnotations(TypeRef... items);    public Set<TypeRef> getAnnotations();    public A withAnnotations(Set<TypeRef> annotations);    public A withAnnotations(TypeRef... annotations);    public AnnotationsNested<A> addNewAnnotation();    public AnnotationsNested<A> addNewAnnotationLike(TypeRef item);    public A addToExtendsList(TypeRef... items);    public A removeFromExtendsList(TypeRef... items);    public Set<TypeRef> getExtendsList();    public A withExtendsList(Set<TypeRef> extendsList);    public A withExtendsList(TypeRef... extendsList);    public ExtendsListNested<A> addNewExtendsList();    public ExtendsListNested<A> addNewExtendsListLike(TypeRef item);    public A addToImplementsList(TypeRef... items);    public A removeFromImplementsList(TypeRef... items);    public Set<TypeRef> getImplementsList();    public A withImplementsList(Set<TypeRef> implementsList);    public A withImplementsList(TypeRef... implementsList);    public ImplementsListNested<A> addNewImplementsList();    public ImplementsListNested<A> addNewImplementsListLike(TypeRef item);    public A withParameters(TypeParamDef... parameters);    public TypeParamDef[] getParameters();    public A addToParameters(TypeParamDef... items);    public A removeFromParameters(TypeParamDef... items);    public ParametersNested<A> addNewParameter();    public ParametersNested<A> addNewParameterLike(TypeParamDef item);    public A addToProperties(Property... items);    public A removeFromProperties(Property... items);    public Set<Property> getProperties();    public A withProperties(Set<Property> properties);    public A withProperties(Property... properties);    public PropertiesNested<A> addNewProperty();    public PropertiesNested<A> addNewPropertyLike(Property item);    public A addToConstructors(Method... items);    public A removeFromConstructors(Method... items);    public Set<Method> getConstructors();    public A withConstructors(Set<Method> constructors);    public A withConstructors(Method... constructors);    public ConstructorsNested<A> addNewConstructor();    public ConstructorsNested<A> addNewConstructorLike(Method item);    public A addToMethods(Method... items);    public A removeFromMethods(Method... items);    public Set<Method> getMethods();    public A withMethods(Set<Method> methods);    public A withMethods(Method... methods);    public MethodsNested<A> addNewMethod();    public MethodsNested<A> addNewMethodLike(Method item);
    public interface AnnotationsNested<N> extends Nested<N>,TypeRefFluent<AnnotationsNested<N>>{
            public N endAnnotation();            public N and();        
}

    public interface ExtendsListNested<N> extends Nested<N>,TypeRefFluent<ExtendsListNested<N>>{
            public N endExtendsList();            public N and();        
}

    public interface ImplementsListNested<N> extends Nested<N>,TypeRefFluent<ImplementsListNested<N>>{
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


}
