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

public interface TypeDefFluent<A extends TypeDefFluent<A>> extends Fluent<A>, ModifierSupportFluent<A> {


    public Kind getKind();

    public A withKind(Kind kind);

    public String getPackageName();

    public A withPackageName(String packageName);

    public String getName();

    public A withName(String name);

    public A addToAnnotations(ClassRef... items);

    public A removeFromAnnotations(ClassRef... items);

    public List<ClassRef> getAnnotations();

    public A withAnnotations(List<ClassRef> annotations);

    public A withAnnotations(ClassRef... annotations);

    public AnnotationsNested<A> addNewAnnotation();

    public AnnotationsNested<A> addNewAnnotationLike(ClassRef item);

    public A addToExtendsList(ClassRef... items);

    public A removeFromExtendsList(ClassRef... items);

    public List<ClassRef> getExtendsList();

    public A withExtendsList(List<ClassRef> extendsList);

    public A withExtendsList(ClassRef... extendsList);

    public ExtendsListNested<A> addNewExtendsList();

    public ExtendsListNested<A> addNewExtendsListLike(ClassRef item);

    public A addToImplementsList(ClassRef... items);

    public A removeFromImplementsList(ClassRef... items);

    public List<ClassRef> getImplementsList();

    public A withImplementsList(List<ClassRef> implementsList);

    public A withImplementsList(ClassRef... implementsList);

    public ImplementsListNested<A> addNewImplementsList();

    public ImplementsListNested<A> addNewImplementsListLike(ClassRef item);

    public A addToParameters(TypeParamDef... items);

    public A removeFromParameters(TypeParamDef... items);

    public List<TypeParamDef> getParameters();

    public A withParameters(List<TypeParamDef> parameters);

    public A withParameters(TypeParamDef... parameters);

    public ParametersNested<A> addNewParameter();

    public ParametersNested<A> addNewParameterLike(TypeParamDef item);

    public A addToProperties(Property... items);

    public A removeFromProperties(Property... items);

    public List<Property> getProperties();

    public A withProperties(List<Property> properties);

    public A withProperties(Property... properties);

    public PropertiesNested<A> addNewProperty();

    public PropertiesNested<A> addNewPropertyLike(Property item);

    public A addToConstructors(Method... items);

    public A removeFromConstructors(Method... items);

    public List<Method> getConstructors();

    public A withConstructors(List<Method> constructors);

    public A withConstructors(Method... constructors);

    public ConstructorsNested<A> addNewConstructor();

    public ConstructorsNested<A> addNewConstructorLike(Method item);

    public A addToMethods(Method... items);

    public A removeFromMethods(Method... items);

    public List<Method> getMethods();

    public A withMethods(List<Method> methods);

    public A withMethods(Method... methods);

    public MethodsNested<A> addNewMethod();

    public MethodsNested<A> addNewMethodLike(Method item);

    public TypeDef getOuterType();

    public A withOuterType(TypeDef outerType);

    public OuterTypeNested<A> withNewOuterType();

    public OuterTypeNested<A> withNewOuterTypeLike(TypeDef item);

    public OuterTypeNested<A> editOuterType();

    public A addToInnerTypes(TypeDef... items);

    public A removeFromInnerTypes(TypeDef... items);

    public List<TypeDef> getInnerTypes();

    public A withInnerTypes(List<TypeDef> innerTypes);

    public A withInnerTypes(TypeDef... innerTypes);

    public InnerTypesNested<A> addNewInnerType();

    public InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);

    public interface AnnotationsNested<N> extends Nested<N>, ClassRefFluent<AnnotationsNested<N>> {
        public N endAnnotation();

        public N and();
    }

    public interface ExtendsListNested<N> extends Nested<N>, ClassRefFluent<ExtendsListNested<N>> {
        public N endExtendsList();

        public N and();
    }

    public interface ImplementsListNested<N> extends Nested<N>, ClassRefFluent<ImplementsListNested<N>> {
        public N endImplementsList();

        public N and();
    }

    public interface ParametersNested<N> extends Nested<N>, TypeParamDefFluent<ParametersNested<N>> {
        public N endParameter();

        public N and();
    }

    public interface PropertiesNested<N> extends Nested<N>, PropertyFluent<PropertiesNested<N>> {
        public N endProperty();

        public N and();
    }

    public interface ConstructorsNested<N> extends Nested<N>, MethodFluent<ConstructorsNested<N>> {
        public N endConstructor();

        public N and();
    }

    public interface MethodsNested<N> extends Nested<N>, MethodFluent<MethodsNested<N>> {
        public N endMethod();

        public N and();
    }

    public interface OuterTypeNested<N> extends Nested<N>, TypeDefFluent<OuterTypeNested<N>> {
        public N endOuterType();

        public N and();
    }

    public interface InnerTypesNested<N> extends Nested<N>, TypeDefFluent<InnerTypesNested<N>> {
        public N endInnerType();

        public N and();
    }


}
