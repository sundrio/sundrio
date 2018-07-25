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

import java.util.Collection;
import java.util.List;

import io.sundr.builder.Builder;
import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;

public interface TypeDefFluent<A extends TypeDefFluent<A>> extends ModifierSupportFluent<A>{


    public Kind getKind();
    public A withKind(Kind kind);
    public Boolean hasKind();
    public String getPackageName();
    public A withPackageName(String packageName);
    public Boolean hasPackageName();
    public String getName();
    public A withName(String name);
    public Boolean hasName();
    public A addToAnnotations(int index, AnnotationRef item);
    public A setToAnnotations(int index, AnnotationRef item);
    public A addToAnnotations(AnnotationRef... items);
    public A addAllToAnnotations(Collection<AnnotationRef> items);
    public A removeFromAnnotations(AnnotationRef... items);
    public A removeAllFromAnnotations(Collection<AnnotationRef> items);

    /**
     * This method has been deprecated, please use method buildAnnotations instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<AnnotationRef> getAnnotations();
    public List<AnnotationRef> buildAnnotations();
    public AnnotationRef buildAnnotation(int index);
    public AnnotationRef buildFirstAnnotation();
    public AnnotationRef buildLastAnnotation();
    public AnnotationRef buildMatchingAnnotation(Predicate<Builder<? extends AnnotationRef>> predicate);
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public Boolean hasAnnotations();
    public AnnotationsNested<A> addNewAnnotation();
    public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
    public A addToExtendsList(int index, ClassRef item);
    public A setToExtendsList(int index, ClassRef item);
    public A addToExtendsList(ClassRef... items);
    public A addAllToExtendsList(Collection<ClassRef> items);
    public A removeFromExtendsList(ClassRef... items);
    public A removeAllFromExtendsList(Collection<ClassRef> items);

    /**
     * This method has been deprecated, please use method buildExtendsList instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<ClassRef> getExtendsList();
    public List<ClassRef> buildExtendsList();
    public ClassRef buildExtendsList(int index);
    public ClassRef buildFirstExtendsList();
    public ClassRef buildLastExtendsList();
    public ClassRef buildMatchingExtendsList(Predicate<Builder<? extends ClassRef>> predicate);
    public A withExtendsList(List<ClassRef> extendsList);
    public A withExtendsList(ClassRef... extendsList);
    public Boolean hasExtendsList();
    public ExtendsListNested<A> addNewExtendsList();
    public ExtendsListNested<A> addNewExtendsListLike(ClassRef item);
    public A addToImplementsList(int index, ClassRef item);
    public A setToImplementsList(int index, ClassRef item);
    public A addToImplementsList(ClassRef... items);
    public A addAllToImplementsList(Collection<ClassRef> items);
    public A removeFromImplementsList(ClassRef... items);
    public A removeAllFromImplementsList(Collection<ClassRef> items);

    /**
     * This method has been deprecated, please use method buildImplementsList instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<ClassRef> getImplementsList();
    public List<ClassRef> buildImplementsList();
    public ClassRef buildImplementsList(int index);
    public ClassRef buildFirstImplementsList();
    public ClassRef buildLastImplementsList();
    public ClassRef buildMatchingImplementsList(Predicate<Builder<? extends ClassRef>> predicate);
    public A withImplementsList(List<ClassRef> implementsList);
    public A withImplementsList(ClassRef... implementsList);
    public Boolean hasImplementsList();
    public ImplementsListNested<A> addNewImplementsList();
    public ImplementsListNested<A> addNewImplementsListLike(ClassRef item);
    public A addToParameters(int index, TypeParamDef item);
    public A setToParameters(int index, TypeParamDef item);
    public A addToParameters(TypeParamDef... items);
    public A addAllToParameters(Collection<TypeParamDef> items);
    public A removeFromParameters(TypeParamDef... items);
    public A removeAllFromParameters(Collection<TypeParamDef> items);

    /**
     * This method has been deprecated, please use method buildParameters instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<TypeParamDef> getParameters();
    public List<TypeParamDef> buildParameters();
    public TypeParamDef buildParameter(int index);
    public TypeParamDef buildFirstParameter();
    public TypeParamDef buildLastParameter();
    public TypeParamDef buildMatchingParameter(Predicate<Builder<? extends TypeParamDef>> predicate);
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public Boolean hasParameters();
    public ParametersNested<A> addNewParameter();
    public ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public A addToProperties(int index, Property item);
    public A setToProperties(int index, Property item);
    public A addToProperties(Property... items);
    public A addAllToProperties(Collection<Property> items);
    public A removeFromProperties(Property... items);
    public A removeAllFromProperties(Collection<Property> items);

    /**
     * This method has been deprecated, please use method buildProperties instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<Property> getProperties();
    public List<Property> buildProperties();
    public Property buildProperty(int index);
    public Property buildFirstProperty();
    public Property buildLastProperty();
    public Property buildMatchingProperty(Predicate<Builder<? extends Property>> predicate);
    public A withProperties(List<Property> properties);
    public A withProperties(Property... properties);
    public Boolean hasProperties();
    public PropertiesNested<A> addNewProperty();
    public PropertiesNested<A> addNewPropertyLike(Property item);
    public A addToConstructors(int index, Method item);
    public A setToConstructors(int index, Method item);
    public A addToConstructors(Method... items);
    public A addAllToConstructors(Collection<Method> items);
    public A removeFromConstructors(Method... items);
    public A removeAllFromConstructors(Collection<Method> items);

    /**
     * This method has been deprecated, please use method buildConstructors instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<Method> getConstructors();
    public List<Method> buildConstructors();
    public Method buildConstructor(int index);
    public Method buildFirstConstructor();
    public Method buildLastConstructor();
    public Method buildMatchingConstructor(Predicate<Builder<? extends Method>> predicate);
    public A withConstructors(List<Method> constructors);
    public A withConstructors(Method... constructors);
    public Boolean hasConstructors();
    public ConstructorsNested<A> addNewConstructor();
    public ConstructorsNested<A> addNewConstructorLike(Method item);
    public A addToMethods(int index, Method item);
    public A setToMethods(int index, Method item);
    public A addToMethods(Method... items);
    public A addAllToMethods(Collection<Method> items);
    public A removeFromMethods(Method... items);
    public A removeAllFromMethods(Collection<Method> items);

    /**
     * This method has been deprecated, please use method buildMethods instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<Method> getMethods();
    public List<Method> buildMethods();
    public Method buildMethod(int index);
    public Method buildFirstMethod();
    public Method buildLastMethod();
    public Method buildMatchingMethod(Predicate<Builder<? extends Method>> predicate);
    public A withMethods(List<Method> methods);
    public A withMethods(Method... methods);
    public Boolean hasMethods();
    public MethodsNested<A> addNewMethod();
    public MethodsNested<A> addNewMethodLike(Method item);

    /**
     * This method has been deprecated, please use method buildOuterType instead.
     * @return The buildable object.
     */
    @Deprecated
    public TypeDef getOuterType();
    public TypeDef buildOuterType();
    public A withOuterType(TypeDef outerType);
    public Boolean hasOuterType();
    public OuterTypeNested<A> withNewOuterType();
    public OuterTypeNested<A> withNewOuterTypeLike(TypeDef item);
    public OuterTypeNested<A> editOuterType();
    public OuterTypeNested<A> editOrNewOuterType();
    public OuterTypeNested<A> editOrNewOuterTypeLike(TypeDef item);
    public A addToInnerTypes(int index, TypeDef item);
    public A setToInnerTypes(int index, TypeDef item);
    public A addToInnerTypes(TypeDef... items);
    public A addAllToInnerTypes(Collection<TypeDef> items);
    public A removeFromInnerTypes(TypeDef... items);
    public A removeAllFromInnerTypes(Collection<TypeDef> items);

    /**
     * This method has been deprecated, please use method buildInnerTypes instead.
     * @return The buildable object.
     */
    @Deprecated
    public List<TypeDef> getInnerTypes();
    public List<TypeDef> buildInnerTypes();
    public TypeDef buildInnerType(int index);
    public TypeDef buildFirstInnerType();
    public TypeDef buildLastInnerType();
    public TypeDef buildMatchingInnerType(Predicate<Builder<? extends TypeDef>> predicate);
    public A withInnerTypes(List<TypeDef> innerTypes);
    public A withInnerTypes(TypeDef... innerTypes);
    public Boolean hasInnerTypes();
    public InnerTypesNested<A> addNewInnerType();
    public InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface ExtendsListNested<N> extends Nested<N>,ClassRefFluent<ExtendsListNested<N>>{


    public N and();    public N endExtendsList();
}
    public interface ImplementsListNested<N> extends Nested<N>,ClassRefFluent<ImplementsListNested<N>>{


    public N and();    public N endImplementsList();
}
    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<ParametersNested<N>>{


    public N and();    public N endParameter();
}
    public interface PropertiesNested<N> extends Nested<N>,PropertyFluent<PropertiesNested<N>>{


    public N and();    public N endProperty();
}
    public interface ConstructorsNested<N> extends Nested<N>,MethodFluent<ConstructorsNested<N>>{


    public N and();    public N endConstructor();
}
    public interface MethodsNested<N> extends Nested<N>,MethodFluent<MethodsNested<N>>{


    public N and();    public N endMethod();
}
    public interface OuterTypeNested<N> extends Nested<N>,TypeDefFluent<OuterTypeNested<N>>{


    public N and();    public N endOuterType();
}
    public interface InnerTypesNested<N> extends Nested<N>,TypeDefFluent<InnerTypesNested<N>>{

        
    public N and();    public N endInnerType();
}


}
