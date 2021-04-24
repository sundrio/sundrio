package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.model.builder.Nested;

public interface TypeDefFluent<A extends TypeDefFluent<A>> extends ModifierSupportFluent<A> {

  public Kind getKind();

  public A withKind(Kind kind);

  public Boolean hasKind();

  public String getPackageName();

  public A withPackageName(String packageName);

  public Boolean hasPackageName();

  public A withNewPackageName(StringBuilder arg1);

  public A withNewPackageName(int[] arg1, int arg2, int arg3);

  public A withNewPackageName(char[] arg1);

  public A withNewPackageName(StringBuffer arg1);

  public A withNewPackageName(byte[] arg1, int arg2);

  public A withNewPackageName(byte[] arg1);

  public A withNewPackageName(char[] arg1, int arg2, int arg3);

  public A withNewPackageName(byte[] arg1, int arg2, int arg3);

  public A withNewPackageName(byte[] arg1, int arg2, int arg3, int arg4);

  public A withNewPackageName(String arg1);

  public String getName();

  public A withName(String name);

  public Boolean hasName();

  public A withNewName(StringBuilder arg1);

  public A withNewName(int[] arg1, int arg2, int arg3);

  public A withNewName(char[] arg1);

  public A withNewName(StringBuffer arg1);

  public A withNewName(byte[] arg1, int arg2);

  public A withNewName(byte[] arg1);

  public A withNewName(char[] arg1, int arg2, int arg3);

  public A withNewName(byte[] arg1, int arg2, int arg3);

  public A withNewName(byte[] arg1, int arg2, int arg3, int arg4);

  public A withNewName(String arg1);

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

  public Boolean hasMatchingComment(Predicate<String> predicate);

  public A withComments(List<String> comments);

  public A withComments(String... comments);

  public Boolean hasComments();

  public A addNewComment(StringBuilder arg1);

  public A addNewComment(int[] arg1, int arg2, int arg3);

  public A addNewComment(char[] arg1);

  public A addNewComment(StringBuffer arg1);

  public A addNewComment(byte[] arg1, int arg2);

  public A addNewComment(byte[] arg1);

  public A addNewComment(char[] arg1, int arg2, int arg3);

  public A addNewComment(byte[] arg1, int arg2, int arg3);

  public A addNewComment(byte[] arg1, int arg2, int arg3, int arg4);

  public A addNewComment(String arg1);

  public A addToAnnotations(int index, AnnotationRef item);

  public A setToAnnotations(int index, AnnotationRef item);

  public A addToAnnotations(AnnotationRef... items);

  public A addAllToAnnotations(Collection<AnnotationRef> items);

  public A removeFromAnnotations(AnnotationRef... items);

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

  public AnnotationRef buildAnnotation(int index);

  public AnnotationRef buildFirstAnnotation();

  public AnnotationRef buildLastAnnotation();

  public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  public Boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  public A withAnnotations(List<AnnotationRef> annotations);

  public A withAnnotations(AnnotationRef... annotations);

  public Boolean hasAnnotations();

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> addNewAnnotation();

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item);

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editAnnotation(int index);

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editFirstAnnotation();

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editLastAnnotation();

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  public A addToExtendsList(int index, ClassRef item);

  public A setToExtendsList(int index, ClassRef item);

  public A addToExtendsList(ClassRef... items);

  public A addAllToExtendsList(Collection<ClassRef> items);

  public A removeFromExtendsList(ClassRef... items);

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

  public ClassRef buildExtendsList(int index);

  public ClassRef buildFirstExtendsList();

  public ClassRef buildLastExtendsList();

  public ClassRef buildMatchingExtendsList(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingExtendsList(Predicate<ClassRefBuilder> predicate);

  public A withExtendsList(List<ClassRef> extendsList);

  public A withExtendsList(ClassRef... extendsList);

  public Boolean hasExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> addNewExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(ClassRef item);

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> setNewExtendsListLike(int index, ClassRef item);

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editExtendsList(int index);

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editFirstExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editLastExtendsList();

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editMatchingExtendsList(Predicate<ClassRefBuilder> predicate);

  public A addToImplementsList(int index, ClassRef item);

  public A setToImplementsList(int index, ClassRef item);

  public A addToImplementsList(ClassRef... items);

  public A addAllToImplementsList(Collection<ClassRef> items);

  public A removeFromImplementsList(ClassRef... items);

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

  public ClassRef buildImplementsList(int index);

  public ClassRef buildFirstImplementsList();

  public ClassRef buildLastImplementsList();

  public ClassRef buildMatchingImplementsList(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingImplementsList(Predicate<ClassRefBuilder> predicate);

  public A withImplementsList(List<ClassRef> implementsList);

  public A withImplementsList(ClassRef... implementsList);

  public Boolean hasImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> addNewImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(ClassRef item);

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> setNewImplementsListLike(int index, ClassRef item);

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editImplementsList(int index);

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editFirstImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editLastImplementsList();

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editMatchingImplementsList(Predicate<ClassRefBuilder> predicate);

  public A addToParameters(int index, TypeParamDef item);

  public A setToParameters(int index, TypeParamDef item);

  public A addToParameters(TypeParamDef... items);

  public A addAllToParameters(Collection<TypeParamDef> items);

  public A removeFromParameters(TypeParamDef... items);

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

  public TypeParamDef buildParameter(int index);

  public TypeParamDef buildFirstParameter();

  public TypeParamDef buildLastParameter();

  public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

  public Boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

  public A withParameters(List<TypeParamDef> parameters);

  public A withParameters(TypeParamDef... parameters);

  public Boolean hasParameters();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> addNewParameter();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);

  public io.sundr.model.TypeDefFluent.ParametersNested<A> setNewParameterLike(int index, TypeParamDef item);

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editParameter(int index);

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editFirstParameter();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editLastParameter();

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

  public A addToProperties(int index, Property item);

  public A setToProperties(int index, Property item);

  public A addToProperties(Property... items);

  public A addAllToProperties(Collection<Property> items);

  public A removeFromProperties(Property... items);

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

  public Property buildProperty(int index);

  public Property buildFirstProperty();

  public Property buildLastProperty();

  public Property buildMatchingProperty(Predicate<PropertyBuilder> predicate);

  public Boolean hasMatchingProperty(Predicate<PropertyBuilder> predicate);

  public A withProperties(List<Property> properties);

  public A withProperties(Property... properties);

  public Boolean hasProperties();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> addNewProperty();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> addNewPropertyLike(Property item);

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> setNewPropertyLike(int index, Property item);

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editProperty(int index);

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editFirstProperty();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editLastProperty();

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate);

  public A addToConstructors(int index, Method item);

  public A setToConstructors(int index, Method item);

  public A addToConstructors(Method... items);

  public A addAllToConstructors(Collection<Method> items);

  public A removeFromConstructors(Method... items);

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

  public Method buildConstructor(int index);

  public Method buildFirstConstructor();

  public Method buildLastConstructor();

  public Method buildMatchingConstructor(Predicate<MethodBuilder> predicate);

  public Boolean hasMatchingConstructor(Predicate<MethodBuilder> predicate);

  public A withConstructors(List<Method> constructors);

  public A withConstructors(Method... constructors);

  public Boolean hasConstructors();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> addNewConstructor();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(Method item);

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> setNewConstructorLike(int index, Method item);

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editConstructor(int index);

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editFirstConstructor();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editLastConstructor();

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editMatchingConstructor(Predicate<MethodBuilder> predicate);

  public A addToMethods(int index, Method item);

  public A setToMethods(int index, Method item);

  public A addToMethods(Method... items);

  public A addAllToMethods(Collection<Method> items);

  public A removeFromMethods(Method... items);

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

  public Method buildMethod(int index);

  public Method buildFirstMethod();

  public Method buildLastMethod();

  public Method buildMatchingMethod(Predicate<MethodBuilder> predicate);

  public Boolean hasMatchingMethod(Predicate<MethodBuilder> predicate);

  public A withMethods(List<Method> methods);

  public A withMethods(Method... methods);

  public Boolean hasMethods();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> addNewMethod();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> addNewMethodLike(Method item);

  public io.sundr.model.TypeDefFluent.MethodsNested<A> setNewMethodLike(int index, Method item);

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editMethod(int index);

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editFirstMethod();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editLastMethod();

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editMatchingMethod(Predicate<MethodBuilder> predicate);

  public String getOuterTypeName();

  public A withOuterTypeName(String outerTypeName);

  public Boolean hasOuterTypeName();

  public A withNewOuterTypeName(StringBuilder arg1);

  public A withNewOuterTypeName(int[] arg1, int arg2, int arg3);

  public A withNewOuterTypeName(char[] arg1);

  public A withNewOuterTypeName(StringBuffer arg1);

  public A withNewOuterTypeName(byte[] arg1, int arg2);

  public A withNewOuterTypeName(byte[] arg1);

  public A withNewOuterTypeName(char[] arg1, int arg2, int arg3);

  public A withNewOuterTypeName(byte[] arg1, int arg2, int arg3);

  public A withNewOuterTypeName(byte[] arg1, int arg2, int arg3, int arg4);

  public A withNewOuterTypeName(String arg1);

  public A addToInnerTypes(int index, TypeDef item);

  public A setToInnerTypes(int index, TypeDef item);

  public A addToInnerTypes(TypeDef... items);

  public A addAllToInnerTypes(Collection<TypeDef> items);

  public A removeFromInnerTypes(TypeDef... items);

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

  public TypeDef buildInnerType(int index);

  public TypeDef buildFirstInnerType();

  public TypeDef buildLastInnerType();

  public TypeDef buildMatchingInnerType(Predicate<TypeDefBuilder> predicate);

  public Boolean hasMatchingInnerType(Predicate<TypeDefBuilder> predicate);

  public A withInnerTypes(List<TypeDef> innerTypes);

  public A withInnerTypes(TypeDef... innerTypes);

  public Boolean hasInnerTypes();

  public A addNewInnerType(String fullyQualifiedName);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> addNewInnerType();

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(TypeDef item);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> setNewInnerTypeLike(int index, TypeDef item);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editInnerType(int index);

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editFirstInnerType();

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editLastInnerType();

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editMatchingInnerType(Predicate<TypeDefBuilder> predicate);

  public interface AnnotationsNested<N>
      extends io.sundr.model.builder.Nested<N>, AnnotationRefFluent<io.sundr.model.TypeDefFluent.AnnotationsNested<N>> {

    public N and();

    public N endAnnotation();
  }

  public interface ExtendsListNested<N>
      extends io.sundr.model.builder.Nested<N>, ClassRefFluent<io.sundr.model.TypeDefFluent.ExtendsListNested<N>> {

    public N and();

    public N endExtendsList();
  }

  public interface ImplementsListNested<N>
      extends io.sundr.model.builder.Nested<N>, ClassRefFluent<io.sundr.model.TypeDefFluent.ImplementsListNested<N>> {

    public N and();

    public N endImplementsList();
  }

  public interface ParametersNested<N>
      extends io.sundr.model.builder.Nested<N>, TypeParamDefFluent<io.sundr.model.TypeDefFluent.ParametersNested<N>> {

    public N and();

    public N endParameter();
  }

  public interface PropertiesNested<N>
      extends io.sundr.model.builder.Nested<N>, PropertyFluent<io.sundr.model.TypeDefFluent.PropertiesNested<N>> {

    public N and();

    public N endProperty();
  }

  public interface ConstructorsNested<N>
      extends io.sundr.model.builder.Nested<N>, MethodFluent<io.sundr.model.TypeDefFluent.ConstructorsNested<N>> {

    public N and();

    public N endConstructor();
  }

  public interface MethodsNested<N>
      extends io.sundr.model.builder.Nested<N>, MethodFluent<io.sundr.model.TypeDefFluent.MethodsNested<N>> {

    public N and();

    public N endMethod();
  }

  public interface InnerTypesNested<N>
      extends io.sundr.model.builder.Nested<N>, TypeDefFluent<io.sundr.model.TypeDefFluent.InnerTypesNested<N>> {

    public N and();

    public N endInnerType();
  }

}
