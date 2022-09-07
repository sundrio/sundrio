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

  public A withKind(Kind kind);

  public Boolean hasKind();

  public String getPackageName();

  public A withPackageName(String packageName);

  public Boolean hasPackageName();

  public String getName();

  public A withName(String name);

  public Boolean hasName();

  public A addToComments(Integer index, String item);

  public A setToComments(Integer index, String item);

  public A addToComments(java.lang.String... items);

  public A addAllToComments(Collection<String> items);

  public A removeFromComments(java.lang.String... items);

  public A removeAllFromComments(Collection<String> items);

  public List<String> getComments();

  public String getComment(Integer index);

  public String getFirstComment();

  public String getLastComment();

  public String getMatchingComment(Predicate<String> predicate);

  public Boolean hasMatchingComment(Predicate<String> predicate);

  public A withComments(List<String> comments);

  public A withComments(java.lang.String... comments);

  public Boolean hasComments();

  public A addToAnnotations(Integer index, AnnotationRef item);

  public A setToAnnotations(Integer index, AnnotationRef item);

  public A addToAnnotations(io.sundr.model.AnnotationRef... items);

  public A addAllToAnnotations(Collection<AnnotationRef> items);

  public A removeFromAnnotations(io.sundr.model.AnnotationRef... items);

  public A removeAllFromAnnotations(Collection<AnnotationRef> items);

  public A removeMatchingFromAnnotations(Predicate<AnnotationRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildAnnotations instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<AnnotationRef> getAnnotations();

  public List<AnnotationRef> buildAnnotations();

  public AnnotationRef buildAnnotation(Integer index);

  public AnnotationRef buildFirstAnnotation();

  public AnnotationRef buildLastAnnotation();

  public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  public Boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  public A withAnnotations(List<AnnotationRef> annotations);

  public A withAnnotations(io.sundr.model.AnnotationRef... annotations);

  public Boolean hasAnnotations();

  public TypeDefFluent.AnnotationsNested<A> addNewAnnotation();

  public TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

  public TypeDefFluent.AnnotationsNested<A> setNewAnnotationLike(Integer index, AnnotationRef item);

  public TypeDefFluent.AnnotationsNested<A> editAnnotation(Integer index);

  public TypeDefFluent.AnnotationsNested<A> editFirstAnnotation();

  public TypeDefFluent.AnnotationsNested<A> editLastAnnotation();

  public TypeDefFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  public A addToExtendsList(Integer index, ClassRef item);

  public A setToExtendsList(Integer index, ClassRef item);

  public A addToExtendsList(io.sundr.model.ClassRef... items);

  public A addAllToExtendsList(Collection<ClassRef> items);

  public A removeFromExtendsList(io.sundr.model.ClassRef... items);

  public A removeAllFromExtendsList(Collection<ClassRef> items);

  public A removeMatchingFromExtendsList(Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildExtendsList instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<ClassRef> getExtendsList();

  public List<ClassRef> buildExtendsList();

  public ClassRef buildExtendsList(Integer index);

  public ClassRef buildFirstExtendsList();

  public ClassRef buildLastExtendsList();

  public ClassRef buildMatchingExtendsList(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingExtendsList(Predicate<ClassRefBuilder> predicate);

  public A withExtendsList(List<ClassRef> extendsList);

  public A withExtendsList(io.sundr.model.ClassRef... extendsList);

  public Boolean hasExtendsList();

  public TypeDefFluent.ExtendsListNested<A> addNewExtendsList();

  public TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item);

  public TypeDefFluent.ExtendsListNested<A> setNewExtendsListLike(Integer index, ClassRef item);

  public TypeDefFluent.ExtendsListNested<A> editExtendsList(Integer index);

  public TypeDefFluent.ExtendsListNested<A> editFirstExtendsList();

  public TypeDefFluent.ExtendsListNested<A> editLastExtendsList();

  public TypeDefFluent.ExtendsListNested<A> editMatchingExtendsList(Predicate<ClassRefBuilder> predicate);

  public A addToImplementsList(Integer index, ClassRef item);

  public A setToImplementsList(Integer index, ClassRef item);

  public A addToImplementsList(io.sundr.model.ClassRef... items);

  public A addAllToImplementsList(Collection<ClassRef> items);

  public A removeFromImplementsList(io.sundr.model.ClassRef... items);

  public A removeAllFromImplementsList(Collection<ClassRef> items);

  public A removeMatchingFromImplementsList(Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildImplementsList instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<ClassRef> getImplementsList();

  public List<ClassRef> buildImplementsList();

  public ClassRef buildImplementsList(Integer index);

  public ClassRef buildFirstImplementsList();

  public ClassRef buildLastImplementsList();

  public ClassRef buildMatchingImplementsList(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingImplementsList(Predicate<ClassRefBuilder> predicate);

  public A withImplementsList(List<ClassRef> implementsList);

  public A withImplementsList(io.sundr.model.ClassRef... implementsList);

  public Boolean hasImplementsList();

  public TypeDefFluent.ImplementsListNested<A> addNewImplementsList();

  public TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item);

  public TypeDefFluent.ImplementsListNested<A> setNewImplementsListLike(Integer index, ClassRef item);

  public TypeDefFluent.ImplementsListNested<A> editImplementsList(Integer index);

  public TypeDefFluent.ImplementsListNested<A> editFirstImplementsList();

  public TypeDefFluent.ImplementsListNested<A> editLastImplementsList();

  public TypeDefFluent.ImplementsListNested<A> editMatchingImplementsList(Predicate<ClassRefBuilder> predicate);

  public A addToParameters(Integer index, TypeParamDef item);

  public A setToParameters(Integer index, TypeParamDef item);

  public A addToParameters(io.sundr.model.TypeParamDef... items);

  public A addAllToParameters(Collection<TypeParamDef> items);

  public A removeFromParameters(io.sundr.model.TypeParamDef... items);

  public A removeAllFromParameters(Collection<TypeParamDef> items);

  public A removeMatchingFromParameters(Predicate<TypeParamDefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildParameters instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeParamDef> getParameters();

  public List<TypeParamDef> buildParameters();

  public TypeParamDef buildParameter(Integer index);

  public TypeParamDef buildFirstParameter();

  public TypeParamDef buildLastParameter();

  public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

  public Boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

  public A withParameters(List<TypeParamDef> parameters);

  public A withParameters(io.sundr.model.TypeParamDef... parameters);

  public Boolean hasParameters();

  public TypeDefFluent.ParametersNested<A> addNewParameter();

  public TypeDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);

  public TypeDefFluent.ParametersNested<A> setNewParameterLike(Integer index, TypeParamDef item);

  public TypeDefFluent.ParametersNested<A> editParameter(Integer index);

  public TypeDefFluent.ParametersNested<A> editFirstParameter();

  public TypeDefFluent.ParametersNested<A> editLastParameter();

  public TypeDefFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

  public A addToProperties(Integer index, Property item);

  public A setToProperties(Integer index, Property item);

  public A addToProperties(io.sundr.model.Property... items);

  public A addAllToProperties(Collection<Property> items);

  public A removeFromProperties(io.sundr.model.Property... items);

  public A removeAllFromProperties(Collection<Property> items);

  public A removeMatchingFromProperties(Predicate<PropertyBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildProperties instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<Property> getProperties();

  public List<Property> buildProperties();

  public Property buildProperty(Integer index);

  public Property buildFirstProperty();

  public Property buildLastProperty();

  public Property buildMatchingProperty(Predicate<PropertyBuilder> predicate);

  public Boolean hasMatchingProperty(Predicate<PropertyBuilder> predicate);

  public A withProperties(List<Property> properties);

  public A withProperties(io.sundr.model.Property... properties);

  public Boolean hasProperties();

  public TypeDefFluent.PropertiesNested<A> addNewProperty();

  public TypeDefFluent.PropertiesNested<A> addNewPropertyLike(Property item);

  public TypeDefFluent.PropertiesNested<A> setNewPropertyLike(Integer index, Property item);

  public TypeDefFluent.PropertiesNested<A> editProperty(Integer index);

  public TypeDefFluent.PropertiesNested<A> editFirstProperty();

  public TypeDefFluent.PropertiesNested<A> editLastProperty();

  public TypeDefFluent.PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate);

  public A addToConstructors(Integer index, Method item);

  public A setToConstructors(Integer index, Method item);

  public A addToConstructors(io.sundr.model.Method... items);

  public A addAllToConstructors(Collection<Method> items);

  public A removeFromConstructors(io.sundr.model.Method... items);

  public A removeAllFromConstructors(Collection<Method> items);

  public A removeMatchingFromConstructors(Predicate<MethodBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildConstructors instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<Method> getConstructors();

  public List<Method> buildConstructors();

  public Method buildConstructor(Integer index);

  public Method buildFirstConstructor();

  public Method buildLastConstructor();

  public Method buildMatchingConstructor(Predicate<MethodBuilder> predicate);

  public Boolean hasMatchingConstructor(Predicate<MethodBuilder> predicate);

  public A withConstructors(List<Method> constructors);

  public A withConstructors(io.sundr.model.Method... constructors);

  public Boolean hasConstructors();

  public TypeDefFluent.ConstructorsNested<A> addNewConstructor();

  public TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item);

  public TypeDefFluent.ConstructorsNested<A> setNewConstructorLike(Integer index, Method item);

  public TypeDefFluent.ConstructorsNested<A> editConstructor(Integer index);

  public TypeDefFluent.ConstructorsNested<A> editFirstConstructor();

  public TypeDefFluent.ConstructorsNested<A> editLastConstructor();

  public TypeDefFluent.ConstructorsNested<A> editMatchingConstructor(Predicate<MethodBuilder> predicate);

  public A addToMethods(Integer index, Method item);

  public A setToMethods(Integer index, Method item);

  public A addToMethods(io.sundr.model.Method... items);

  public A addAllToMethods(Collection<Method> items);

  public A removeFromMethods(io.sundr.model.Method... items);

  public A removeAllFromMethods(Collection<Method> items);

  public A removeMatchingFromMethods(Predicate<MethodBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildMethods instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<Method> getMethods();

  public List<Method> buildMethods();

  public Method buildMethod(Integer index);

  public Method buildFirstMethod();

  public Method buildLastMethod();

  public Method buildMatchingMethod(Predicate<MethodBuilder> predicate);

  public Boolean hasMatchingMethod(Predicate<MethodBuilder> predicate);

  public A withMethods(List<Method> methods);

  public A withMethods(io.sundr.model.Method... methods);

  public Boolean hasMethods();

  public TypeDefFluent.MethodsNested<A> addNewMethod();

  public TypeDefFluent.MethodsNested<A> addNewMethodLike(Method item);

  public TypeDefFluent.MethodsNested<A> setNewMethodLike(Integer index, Method item);

  public TypeDefFluent.MethodsNested<A> editMethod(Integer index);

  public TypeDefFluent.MethodsNested<A> editFirstMethod();

  public TypeDefFluent.MethodsNested<A> editLastMethod();

  public TypeDefFluent.MethodsNested<A> editMatchingMethod(Predicate<MethodBuilder> predicate);

  public String getOuterTypeName();

  public A withOuterTypeName(String outerTypeName);

  public Boolean hasOuterTypeName();

  public A addToInnerTypes(Integer index, TypeDef item);

  public A setToInnerTypes(Integer index, TypeDef item);

  public A addToInnerTypes(io.sundr.model.TypeDef... items);

  public A addAllToInnerTypes(Collection<TypeDef> items);

  public A removeFromInnerTypes(io.sundr.model.TypeDef... items);

  public A removeAllFromInnerTypes(Collection<TypeDef> items);

  public A removeMatchingFromInnerTypes(Predicate<TypeDefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildInnerTypes instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeDef> getInnerTypes();

  public List<TypeDef> buildInnerTypes();

  public TypeDef buildInnerType(Integer index);

  public TypeDef buildFirstInnerType();

  public TypeDef buildLastInnerType();

  public TypeDef buildMatchingInnerType(Predicate<TypeDefBuilder> predicate);

  public Boolean hasMatchingInnerType(Predicate<TypeDefBuilder> predicate);

  public A withInnerTypes(List<TypeDef> innerTypes);

  public A withInnerTypes(io.sundr.model.TypeDef... innerTypes);

  public Boolean hasInnerTypes();

  public A addNewInnerType(String fullyQualifiedName);

  public TypeDefFluent.InnerTypesNested<A> addNewInnerType();

  public TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);

  public TypeDefFluent.InnerTypesNested<A> setNewInnerTypeLike(Integer index, TypeDef item);

  public TypeDefFluent.InnerTypesNested<A> editInnerType(Integer index);

  public TypeDefFluent.InnerTypesNested<A> editFirstInnerType();

  public TypeDefFluent.InnerTypesNested<A> editLastInnerType();

  public TypeDefFluent.InnerTypesNested<A> editMatchingInnerType(Predicate<TypeDefBuilder> predicate);

  public interface AnnotationsNested<N> extends Nested<N>, AnnotationRefFluent<TypeDefFluent.AnnotationsNested<N>> {
    public N and();

    public N endAnnotation();

  }

  public interface ExtendsListNested<N> extends Nested<N>, ClassRefFluent<TypeDefFluent.ExtendsListNested<N>> {
    public N and();

    public N endExtendsList();

  }

  public interface ImplementsListNested<N> extends Nested<N>, ClassRefFluent<TypeDefFluent.ImplementsListNested<N>> {
    public N and();

    public N endImplementsList();

  }

  public interface ParametersNested<N> extends Nested<N>, TypeParamDefFluent<TypeDefFluent.ParametersNested<N>> {
    public N and();

    public N endParameter();

  }

  public interface PropertiesNested<N> extends Nested<N>, PropertyFluent<TypeDefFluent.PropertiesNested<N>> {
    public N and();

    public N endProperty();

  }

  public interface ConstructorsNested<N> extends Nested<N>, MethodFluent<TypeDefFluent.ConstructorsNested<N>> {
    public N and();

    public N endConstructor();

  }

  public interface MethodsNested<N> extends Nested<N>, MethodFluent<TypeDefFluent.MethodsNested<N>> {
    public N and();

    public N endMethod();

  }

  public interface InnerTypesNested<N> extends Nested<N>, TypeDefFluent<TypeDefFluent.InnerTypesNested<N>> {
    public N and();

    public N endInnerType();

  }

}
