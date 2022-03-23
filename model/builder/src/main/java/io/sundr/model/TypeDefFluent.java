package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

/**
 * Generated
 */
public interface TypeDefFluent<A extends TypeDefFluent<A>> extends ModifierSupportFluent<A> {
  public Kind getKind();

  public A withKind(io.sundr.model.Kind kind);

  public Boolean hasKind();

  public String getPackageName();

  public A withPackageName(java.lang.String packageName);

  public java.lang.Boolean hasPackageName();

  public java.lang.String getName();

  public A withName(java.lang.String name);

  public java.lang.Boolean hasName();

  public A addToComments(Integer index, java.lang.String item);

  public A setToComments(java.lang.Integer index, java.lang.String item);

  public A addToComments(java.lang.String... items);

  public A addAllToComments(Collection<java.lang.String> items);

  public A removeFromComments(java.lang.String... items);

  public A removeAllFromComments(java.util.Collection<java.lang.String> items);

  public List<java.lang.String> getComments();

  public java.lang.String getComment(java.lang.Integer index);

  public java.lang.String getFirstComment();

  public java.lang.String getLastComment();

  public java.lang.String getMatchingComment(Predicate<java.lang.String> predicate);

  public java.lang.Boolean hasMatchingComment(java.util.function.Predicate<java.lang.String> predicate);

  public A withComments(java.util.List<java.lang.String> comments);

  public A withComments(java.lang.String... comments);

  public java.lang.Boolean hasComments();

  public A addToAnnotations(java.lang.Integer index, AnnotationRef item);

  public A setToAnnotations(java.lang.Integer index, io.sundr.model.AnnotationRef item);

  public A addToAnnotations(io.sundr.model.AnnotationRef... items);

  public A addAllToAnnotations(java.util.Collection<io.sundr.model.AnnotationRef> items);

  public A removeFromAnnotations(io.sundr.model.AnnotationRef... items);

  public A removeAllFromAnnotations(java.util.Collection<io.sundr.model.AnnotationRef> items);

  public A removeMatchingFromAnnotations(java.util.function.Predicate<AnnotationRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildAnnotations instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public java.util.List<io.sundr.model.AnnotationRef> getAnnotations();

  public java.util.List<io.sundr.model.AnnotationRef> buildAnnotations();

  public io.sundr.model.AnnotationRef buildAnnotation(java.lang.Integer index);

  public io.sundr.model.AnnotationRef buildFirstAnnotation();

  public io.sundr.model.AnnotationRef buildLastAnnotation();

  public io.sundr.model.AnnotationRef buildMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

  public java.lang.Boolean hasMatchingAnnotation(java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

  public A withAnnotations(java.util.List<io.sundr.model.AnnotationRef> annotations);

  public A withAnnotations(io.sundr.model.AnnotationRef... annotations);

  public java.lang.Boolean hasAnnotations();

  public TypeDefFluent.AnnotationsNested<A> addNewAnnotation();

  public TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(io.sundr.model.AnnotationRef item);

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> setNewAnnotationLike(java.lang.Integer index,
      io.sundr.model.AnnotationRef item);

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editAnnotation(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editFirstAnnotation();

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editLastAnnotation();

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

  public A addToExtendsList(java.lang.Integer index, ClassRef item);

  public A setToExtendsList(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A addToExtendsList(io.sundr.model.ClassRef... items);

  public A addAllToExtendsList(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeFromExtendsList(io.sundr.model.ClassRef... items);

  public A removeAllFromExtendsList(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeMatchingFromExtendsList(java.util.function.Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildExtendsList instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.ClassRef> getExtendsList();

  public java.util.List<io.sundr.model.ClassRef> buildExtendsList();

  public io.sundr.model.ClassRef buildExtendsList(java.lang.Integer index);

  public io.sundr.model.ClassRef buildFirstExtendsList();

  public io.sundr.model.ClassRef buildLastExtendsList();

  public io.sundr.model.ClassRef buildMatchingExtendsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public java.lang.Boolean hasMatchingExtendsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public A withExtendsList(java.util.List<io.sundr.model.ClassRef> extendsList);

  public A withExtendsList(io.sundr.model.ClassRef... extendsList);

  public java.lang.Boolean hasExtendsList();

  public TypeDefFluent.ExtendsListNested<A> addNewExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(io.sundr.model.ClassRef item);

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> setNewExtendsListLike(java.lang.Integer index,
      io.sundr.model.ClassRef item);

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editExtendsList(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editFirstExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editLastExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editMatchingExtendsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public A addToImplementsList(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A setToImplementsList(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A addToImplementsList(io.sundr.model.ClassRef... items);

  public A addAllToImplementsList(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeFromImplementsList(io.sundr.model.ClassRef... items);

  public A removeAllFromImplementsList(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeMatchingFromImplementsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildImplementsList instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.ClassRef> getImplementsList();

  public java.util.List<io.sundr.model.ClassRef> buildImplementsList();

  public io.sundr.model.ClassRef buildImplementsList(java.lang.Integer index);

  public io.sundr.model.ClassRef buildFirstImplementsList();

  public io.sundr.model.ClassRef buildLastImplementsList();

  public io.sundr.model.ClassRef buildMatchingImplementsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public java.lang.Boolean hasMatchingImplementsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public A withImplementsList(java.util.List<io.sundr.model.ClassRef> implementsList);

  public A withImplementsList(io.sundr.model.ClassRef... implementsList);

  public java.lang.Boolean hasImplementsList();

  public TypeDefFluent.ImplementsListNested<A> addNewImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(io.sundr.model.ClassRef item);

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> setNewImplementsListLike(java.lang.Integer index,
      io.sundr.model.ClassRef item);

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editImplementsList(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editFirstImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editLastImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editMatchingImplementsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public A addToParameters(java.lang.Integer index, TypeParamDef item);

  public A setToParameters(java.lang.Integer index, io.sundr.model.TypeParamDef item);

  public A addToParameters(io.sundr.model.TypeParamDef... items);

  public A addAllToParameters(java.util.Collection<io.sundr.model.TypeParamDef> items);

  public A removeFromParameters(io.sundr.model.TypeParamDef... items);

  public A removeAllFromParameters(java.util.Collection<io.sundr.model.TypeParamDef> items);

  public A removeMatchingFromParameters(java.util.function.Predicate<TypeParamDefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildParameters instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.TypeParamDef> getParameters();

  public java.util.List<io.sundr.model.TypeParamDef> buildParameters();

  public io.sundr.model.TypeParamDef buildParameter(java.lang.Integer index);

  public io.sundr.model.TypeParamDef buildFirstParameter();

  public io.sundr.model.TypeParamDef buildLastParameter();

  public io.sundr.model.TypeParamDef buildMatchingParameter(
      java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate);

  public java.lang.Boolean hasMatchingParameter(java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate);

  public A withParameters(java.util.List<io.sundr.model.TypeParamDef> parameters);

  public A withParameters(io.sundr.model.TypeParamDef... parameters);

  public java.lang.Boolean hasParameters();

  public TypeDefFluent.ParametersNested<A> addNewParameter();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> addNewParameterLike(io.sundr.model.TypeParamDef item);

  public io.sundr.model.TypeDefFluent.ParametersNested<A> setNewParameterLike(java.lang.Integer index,
      io.sundr.model.TypeParamDef item);

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editParameter(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editFirstParameter();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editLastParameter();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editMatchingParameter(
      java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate);

  public A addToProperties(java.lang.Integer index, Property item);

  public A setToProperties(java.lang.Integer index, io.sundr.model.Property item);

  public A addToProperties(io.sundr.model.Property... items);

  public A addAllToProperties(java.util.Collection<io.sundr.model.Property> items);

  public A removeFromProperties(io.sundr.model.Property... items);

  public A removeAllFromProperties(java.util.Collection<io.sundr.model.Property> items);

  public A removeMatchingFromProperties(java.util.function.Predicate<PropertyBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildProperties instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Property> getProperties();

  public java.util.List<io.sundr.model.Property> buildProperties();

  public io.sundr.model.Property buildProperty(java.lang.Integer index);

  public io.sundr.model.Property buildFirstProperty();

  public io.sundr.model.Property buildLastProperty();

  public io.sundr.model.Property buildMatchingProperty(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate);

  public java.lang.Boolean hasMatchingProperty(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate);

  public A withProperties(java.util.List<io.sundr.model.Property> properties);

  public A withProperties(io.sundr.model.Property... properties);

  public java.lang.Boolean hasProperties();

  public TypeDefFluent.PropertiesNested<A> addNewProperty();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> addNewPropertyLike(io.sundr.model.Property item);

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> setNewPropertyLike(java.lang.Integer index,
      io.sundr.model.Property item);

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editProperty(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editFirstProperty();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editLastProperty();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editMatchingProperty(
      java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate);

  public A addToConstructors(java.lang.Integer index, Method item);

  public A setToConstructors(java.lang.Integer index, io.sundr.model.Method item);

  public A addToConstructors(io.sundr.model.Method... items);

  public A addAllToConstructors(java.util.Collection<io.sundr.model.Method> items);

  public A removeFromConstructors(io.sundr.model.Method... items);

  public A removeAllFromConstructors(java.util.Collection<io.sundr.model.Method> items);

  public A removeMatchingFromConstructors(java.util.function.Predicate<MethodBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildConstructors instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Method> getConstructors();

  public java.util.List<io.sundr.model.Method> buildConstructors();

  public io.sundr.model.Method buildConstructor(java.lang.Integer index);

  public io.sundr.model.Method buildFirstConstructor();

  public io.sundr.model.Method buildLastConstructor();

  public io.sundr.model.Method buildMatchingConstructor(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  public java.lang.Boolean hasMatchingConstructor(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  public A withConstructors(java.util.List<io.sundr.model.Method> constructors);

  public A withConstructors(io.sundr.model.Method... constructors);

  public java.lang.Boolean hasConstructors();

  public TypeDefFluent.ConstructorsNested<A> addNewConstructor();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(io.sundr.model.Method item);

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> setNewConstructorLike(java.lang.Integer index,
      io.sundr.model.Method item);

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editConstructor(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editFirstConstructor();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editLastConstructor();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editMatchingConstructor(
      java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  public A addToMethods(java.lang.Integer index, io.sundr.model.Method item);

  public A setToMethods(java.lang.Integer index, io.sundr.model.Method item);

  public A addToMethods(io.sundr.model.Method... items);

  public A addAllToMethods(java.util.Collection<io.sundr.model.Method> items);

  public A removeFromMethods(io.sundr.model.Method... items);

  public A removeAllFromMethods(java.util.Collection<io.sundr.model.Method> items);

  public A removeMatchingFromMethods(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildMethods instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Method> getMethods();

  public java.util.List<io.sundr.model.Method> buildMethods();

  public io.sundr.model.Method buildMethod(java.lang.Integer index);

  public io.sundr.model.Method buildFirstMethod();

  public io.sundr.model.Method buildLastMethod();

  public io.sundr.model.Method buildMatchingMethod(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  public java.lang.Boolean hasMatchingMethod(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  public A withMethods(java.util.List<io.sundr.model.Method> methods);

  public A withMethods(io.sundr.model.Method... methods);

  public java.lang.Boolean hasMethods();

  public TypeDefFluent.MethodsNested<A> addNewMethod();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> addNewMethodLike(io.sundr.model.Method item);

  public io.sundr.model.TypeDefFluent.MethodsNested<A> setNewMethodLike(java.lang.Integer index, io.sundr.model.Method item);

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editMethod(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editFirstMethod();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editLastMethod();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editMatchingMethod(
      java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate);

  public java.lang.String getOuterTypeName();

  public A withOuterTypeName(java.lang.String outerTypeName);

  public java.lang.Boolean hasOuterTypeName();

  public A addToInnerTypes(java.lang.Integer index, io.sundr.model.TypeDef item);

  public A setToInnerTypes(java.lang.Integer index, io.sundr.model.TypeDef item);

  public A addToInnerTypes(io.sundr.model.TypeDef... items);

  public A addAllToInnerTypes(java.util.Collection<io.sundr.model.TypeDef> items);

  public A removeFromInnerTypes(io.sundr.model.TypeDef... items);

  public A removeAllFromInnerTypes(java.util.Collection<io.sundr.model.TypeDef> items);

  public A removeMatchingFromInnerTypes(java.util.function.Predicate<TypeDefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildInnerTypes instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.TypeDef> getInnerTypes();

  public java.util.List<io.sundr.model.TypeDef> buildInnerTypes();

  public io.sundr.model.TypeDef buildInnerType(java.lang.Integer index);

  public io.sundr.model.TypeDef buildFirstInnerType();

  public io.sundr.model.TypeDef buildLastInnerType();

  public io.sundr.model.TypeDef buildMatchingInnerType(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate);

  public java.lang.Boolean hasMatchingInnerType(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate);

  public A withInnerTypes(java.util.List<io.sundr.model.TypeDef> innerTypes);

  public A withInnerTypes(io.sundr.model.TypeDef... innerTypes);

  public java.lang.Boolean hasInnerTypes();

  public A addNewInnerType(java.lang.String fullyQualifiedName);

  public TypeDefFluent.InnerTypesNested<A> addNewInnerType();

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(io.sundr.model.TypeDef item);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> setNewInnerTypeLike(java.lang.Integer index,
      io.sundr.model.TypeDef item);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editInnerType(java.lang.Integer index);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editFirstInnerType();

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editLastInnerType();

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editMatchingInnerType(
      java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate);

  public interface AnnotationsNested<N> extends Nested<N>, AnnotationRefFluent<TypeDefFluent.AnnotationsNested<N>> {
    public N and();

    public N endAnnotation();

  }

  public interface ExtendsListNested<N> extends io.sundr.builder.Nested<N>, ClassRefFluent<TypeDefFluent.ExtendsListNested<N>> {
    public N and();

    public N endExtendsList();

  }

  public interface ImplementsListNested<N>
      extends io.sundr.builder.Nested<N>, ClassRefFluent<TypeDefFluent.ImplementsListNested<N>> {
    public N and();

    public N endImplementsList();

  }

  public interface ParametersNested<N>
      extends io.sundr.builder.Nested<N>, TypeParamDefFluent<TypeDefFluent.ParametersNested<N>> {
    public N and();

    public N endParameter();

  }

  public interface PropertiesNested<N> extends io.sundr.builder.Nested<N>, PropertyFluent<TypeDefFluent.PropertiesNested<N>> {
    public N and();

    public N endProperty();

  }

  public interface ConstructorsNested<N> extends io.sundr.builder.Nested<N>, MethodFluent<TypeDefFluent.ConstructorsNested<N>> {
    public N and();

    public N endConstructor();

  }

  public interface MethodsNested<N> extends io.sundr.builder.Nested<N>, MethodFluent<TypeDefFluent.MethodsNested<N>> {
    public N and();

    public N endMethod();

  }

  public interface InnerTypesNested<N> extends io.sundr.builder.Nested<N>, TypeDefFluent<TypeDefFluent.InnerTypesNested<N>> {
    public N and();

    public N endInnerType();

  }

}
