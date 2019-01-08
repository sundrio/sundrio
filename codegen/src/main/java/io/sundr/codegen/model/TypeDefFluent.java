/*
 *      Copyright 2018 The original authors.
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

import io.sundr.builder.Nested;
import io.sundr.builder.Predicate;

import java.util.Collection;
import java.util.List;

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
    public A addToComments(int index, String item);
    public A setToComments(int index, String item);
    public A addToComments(String... items);
    public A addAllToComments(Collection<String> items);
    public A removeFromComments(String... items);
    public A removeAllFromComments(Collection<String> items);
    public List<String> getComments();
    public String getComment(int index);
    public String getFirstComment();
    public String getLastComment();
    public String getMatchingComment(Predicate<String> predicate);
    public A withComments(List<String> comments);
    public A withComments(String... comments);
    public Boolean hasComments();
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
@Deprecated public List<AnnotationRef> getAnnotations();
    public List<AnnotationRef> buildAnnotations();
    public AnnotationRef buildAnnotation(int index);
    public AnnotationRef buildFirstAnnotation();
    public AnnotationRef buildLastAnnotation();
    public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);
    public A withAnnotations(List<AnnotationRef> annotations);
    public A withAnnotations(AnnotationRef... annotations);
    public Boolean hasAnnotations();
    public TypeDefFluent.AnnotationsNested<A> addNewAnnotation();
    public TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);
    public TypeDefFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item);
    public TypeDefFluent.AnnotationsNested<A> editAnnotation(int index);
    public TypeDefFluent.AnnotationsNested<A> editFirstAnnotation();
    public TypeDefFluent.AnnotationsNested<A> editLastAnnotation();
    public TypeDefFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);
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
@Deprecated public List<ClassRef> getExtendsList();
    public List<ClassRef> buildExtendsList();
    public ClassRef buildExtendsList(int index);
    public ClassRef buildFirstExtendsList();
    public ClassRef buildLastExtendsList();
    public ClassRef buildMatchingExtendsList(Predicate<ClassRefBuilder> predicate);
    public A withExtendsList(List<ClassRef> extendsList);
    public A withExtendsList(ClassRef... extendsList);
    public Boolean hasExtendsList();
    public TypeDefFluent.ExtendsListNested<A> addNewExtendsList();
    public TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item);
    public TypeDefFluent.ExtendsListNested<A> setNewExtendsListLike(int index, ClassRef item);
    public TypeDefFluent.ExtendsListNested<A> editExtendsList(int index);
    public TypeDefFluent.ExtendsListNested<A> editFirstExtendsList();
    public TypeDefFluent.ExtendsListNested<A> editLastExtendsList();
    public TypeDefFluent.ExtendsListNested<A> editMatchingExtendsList(Predicate<ClassRefBuilder> predicate);
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
@Deprecated public List<ClassRef> getImplementsList();
    public List<ClassRef> buildImplementsList();
    public ClassRef buildImplementsList(int index);
    public ClassRef buildFirstImplementsList();
    public ClassRef buildLastImplementsList();
    public ClassRef buildMatchingImplementsList(Predicate<ClassRefBuilder> predicate);
    public A withImplementsList(List<ClassRef> implementsList);
    public A withImplementsList(ClassRef... implementsList);
    public Boolean hasImplementsList();
    public TypeDefFluent.ImplementsListNested<A> addNewImplementsList();
    public TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item);
    public TypeDefFluent.ImplementsListNested<A> setNewImplementsListLike(int index, ClassRef item);
    public TypeDefFluent.ImplementsListNested<A> editImplementsList(int index);
    public TypeDefFluent.ImplementsListNested<A> editFirstImplementsList();
    public TypeDefFluent.ImplementsListNested<A> editLastImplementsList();
    public TypeDefFluent.ImplementsListNested<A> editMatchingImplementsList(Predicate<ClassRefBuilder> predicate);
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
@Deprecated public List<TypeParamDef> getParameters();
    public List<TypeParamDef> buildParameters();
    public TypeParamDef buildParameter(int index);
    public TypeParamDef buildFirstParameter();
    public TypeParamDef buildLastParameter();
    public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate);
    public A withParameters(List<TypeParamDef> parameters);
    public A withParameters(TypeParamDef... parameters);
    public Boolean hasParameters();
    public TypeDefFluent.ParametersNested<A> addNewParameter();
    public TypeDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);
    public TypeDefFluent.ParametersNested<A> setNewParameterLike(int index, TypeParamDef item);
    public TypeDefFluent.ParametersNested<A> editParameter(int index);
    public TypeDefFluent.ParametersNested<A> editFirstParameter();
    public TypeDefFluent.ParametersNested<A> editLastParameter();
    public TypeDefFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate);
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
@Deprecated public List<Property> getProperties();
    public List<Property> buildProperties();
    public Property buildProperty(int index);
    public Property buildFirstProperty();
    public Property buildLastProperty();
    public Property buildMatchingProperty(Predicate<PropertyBuilder> predicate);
    public A withProperties(List<Property> properties);
    public A withProperties(Property... properties);
    public Boolean hasProperties();
    public TypeDefFluent.PropertiesNested<A> addNewProperty();
    public TypeDefFluent.PropertiesNested<A> addNewPropertyLike(Property item);
    public TypeDefFluent.PropertiesNested<A> setNewPropertyLike(int index, Property item);
    public TypeDefFluent.PropertiesNested<A> editProperty(int index);
    public TypeDefFluent.PropertiesNested<A> editFirstProperty();
    public TypeDefFluent.PropertiesNested<A> editLastProperty();
    public TypeDefFluent.PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate);
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
@Deprecated public List<Method> getConstructors();
    public List<Method> buildConstructors();
    public Method buildConstructor(int index);
    public Method buildFirstConstructor();
    public Method buildLastConstructor();
    public Method buildMatchingConstructor(Predicate<MethodBuilder> predicate);
    public A withConstructors(List<Method> constructors);
    public A withConstructors(Method... constructors);
    public Boolean hasConstructors();
    public TypeDefFluent.ConstructorsNested<A> addNewConstructor();
    public TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item);
    public TypeDefFluent.ConstructorsNested<A> setNewConstructorLike(int index, Method item);
    public TypeDefFluent.ConstructorsNested<A> editConstructor(int index);
    public TypeDefFluent.ConstructorsNested<A> editFirstConstructor();
    public TypeDefFluent.ConstructorsNested<A> editLastConstructor();
    public TypeDefFluent.ConstructorsNested<A> editMatchingConstructor(Predicate<MethodBuilder> predicate);
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
@Deprecated public List<Method> getMethods();
    public List<Method> buildMethods();
    public Method buildMethod(int index);
    public Method buildFirstMethod();
    public Method buildLastMethod();
    public Method buildMatchingMethod(Predicate<MethodBuilder> predicate);
    public A withMethods(List<Method> methods);
    public A withMethods(Method... methods);
    public Boolean hasMethods();
    public TypeDefFluent.MethodsNested<A> addNewMethod();
    public TypeDefFluent.MethodsNested<A> addNewMethodLike(Method item);
    public TypeDefFluent.MethodsNested<A> setNewMethodLike(int index, Method item);
    public TypeDefFluent.MethodsNested<A> editMethod(int index);
    public TypeDefFluent.MethodsNested<A> editFirstMethod();
    public TypeDefFluent.MethodsNested<A> editLastMethod();
    public TypeDefFluent.MethodsNested<A> editMatchingMethod(Predicate<MethodBuilder> predicate);

/**
 * This method has been deprecated, please use method buildOuterType instead.
 * @return The buildable object.
 */
@Deprecated public TypeDef getOuterType();
    public TypeDef buildOuterType();
    public A withOuterType(TypeDef outerType);
    public Boolean hasOuterType();
    public TypeDefFluent.OuterTypeNested<A> withNewOuterType();
    public TypeDefFluent.OuterTypeNested<A> withNewOuterTypeLike(TypeDef item);
    public TypeDefFluent.OuterTypeNested<A> editOuterType();
    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterType();
    public TypeDefFluent.OuterTypeNested<A> editOrNewOuterTypeLike(TypeDef item);
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
@Deprecated public List<TypeDef> getInnerTypes();
    public List<TypeDef> buildInnerTypes();
    public TypeDef buildInnerType(int index);
    public TypeDef buildFirstInnerType();
    public TypeDef buildLastInnerType();
    public TypeDef buildMatchingInnerType(Predicate<TypeDefBuilder> predicate);
    public A withInnerTypes(List<TypeDef> innerTypes);
    public A withInnerTypes(TypeDef... innerTypes);
    public Boolean hasInnerTypes();
    public TypeDefFluent.InnerTypesNested<A> addNewInnerType();
    public TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);
    public TypeDefFluent.InnerTypesNested<A> setNewInnerTypeLike(int index, TypeDef item);
    public TypeDefFluent.InnerTypesNested<A> editInnerType(int index);
    public TypeDefFluent.InnerTypesNested<A> editFirstInnerType();
    public TypeDefFluent.InnerTypesNested<A> editLastInnerType();
    public TypeDefFluent.InnerTypesNested<A> editMatchingInnerType(Predicate<TypeDefBuilder> predicate);

    public interface AnnotationsNested<N> extends Nested<N>,AnnotationRefFluent<TypeDefFluent.AnnotationsNested<N>>{


    public N and();    public N endAnnotation();
}
    public interface ExtendsListNested<N> extends Nested<N>,ClassRefFluent<TypeDefFluent.ExtendsListNested<N>>{


    public N and();    public N endExtendsList();
}
    public interface ImplementsListNested<N> extends Nested<N>,ClassRefFluent<TypeDefFluent.ImplementsListNested<N>>{


    public N and();    public N endImplementsList();
}
    public interface ParametersNested<N> extends Nested<N>,TypeParamDefFluent<TypeDefFluent.ParametersNested<N>>{


    public N and();    public N endParameter();
}
    public interface PropertiesNested<N> extends Nested<N>,PropertyFluent<TypeDefFluent.PropertiesNested<N>>{


    public N and();    public N endProperty();
}
    public interface ConstructorsNested<N> extends Nested<N>,MethodFluent<TypeDefFluent.ConstructorsNested<N>>{


    public N and();    public N endConstructor();
}
    public interface MethodsNested<N> extends Nested<N>,MethodFluent<TypeDefFluent.MethodsNested<N>>{


    public N and();    public N endMethod();
}
    public interface OuterTypeNested<N> extends Nested<N>,TypeDefFluent<TypeDefFluent.OuterTypeNested<N>>{


    public N and();    public N endOuterType();
}
    public interface InnerTypesNested<N> extends Nested<N>,TypeDefFluent<TypeDefFluent.InnerTypesNested<N>>{

        
    public N and();    public N endInnerType();
}


}
