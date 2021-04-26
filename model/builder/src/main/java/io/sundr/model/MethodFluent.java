package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

public interface MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A> {

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

  public io.sundr.model.MethodFluent.AnnotationsNested<A> addNewAnnotation();

  public io.sundr.model.MethodFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

  public io.sundr.model.MethodFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item);

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editAnnotation(int index);

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editFirstAnnotation();

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editLastAnnotation();

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

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

  public io.sundr.model.MethodFluent.ParametersNested<A> addNewParameter();

  public io.sundr.model.MethodFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item);

  public io.sundr.model.MethodFluent.ParametersNested<A> setNewParameterLike(int index, TypeParamDef item);

  public io.sundr.model.MethodFluent.ParametersNested<A> editParameter(int index);

  public io.sundr.model.MethodFluent.ParametersNested<A> editFirstParameter();

  public io.sundr.model.MethodFluent.ParametersNested<A> editLastParameter();

  public io.sundr.model.MethodFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate);

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

  /**
   * This method has been deprecated, please use method buildReturnType instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public TypeRef getReturnType();

  public TypeRef buildReturnType();

  public A withReturnType(TypeRef returnType);

  public Boolean hasReturnType();

  public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType);

  public io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType();

  public io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item);

  public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType);

  public io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType();

  public io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item);

  public A withClassRefReturnType(ClassRef classRefReturnType);

  public io.sundr.model.MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType();

  public io.sundr.model.MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item);

  public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType);

  public io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType();

  public io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item);

  public A withVoidRefReturnType(VoidRef voidRefReturnType);

  public io.sundr.model.MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType();

  public io.sundr.model.MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item);

  public A addToArguments(int index, Property item);

  public A setToArguments(int index, Property item);

  public A addToArguments(Property... items);

  public A addAllToArguments(Collection<Property> items);

  public A removeFromArguments(Property... items);

  public A removeAllFromArguments(Collection<Property> items);

  public A removeMatchingFromArguments(Predicate<PropertyBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<Property> getArguments();

  public List<Property> buildArguments();

  public Property buildArgument(int index);

  public Property buildFirstArgument();

  public Property buildLastArgument();

  public Property buildMatchingArgument(Predicate<PropertyBuilder> predicate);

  public Boolean hasMatchingArgument(Predicate<PropertyBuilder> predicate);

  public A withArguments(List<Property> arguments);

  public A withArguments(Property... arguments);

  public Boolean hasArguments();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> addNewArgument();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> addNewArgumentLike(Property item);

  public io.sundr.model.MethodFluent.ArgumentsNested<A> setNewArgumentLike(int index, Property item);

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editArgument(int index);

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editFirstArgument();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editLastArgument();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editMatchingArgument(Predicate<PropertyBuilder> predicate);

  public boolean isVarArgPreferred();

  public A withVarArgPreferred(boolean varArgPreferred);

  public Boolean hasVarArgPreferred();

  public A addToExceptions(int index, ClassRef item);

  public A setToExceptions(int index, ClassRef item);

  public A addToExceptions(ClassRef... items);

  public A addAllToExceptions(Collection<ClassRef> items);

  public A removeFromExceptions(ClassRef... items);

  public A removeAllFromExceptions(Collection<ClassRef> items);

  public A removeMatchingFromExceptions(Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildExceptions instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<ClassRef> getExceptions();

  public List<ClassRef> buildExceptions();

  public ClassRef buildException(int index);

  public ClassRef buildFirstException();

  public ClassRef buildLastException();

  public ClassRef buildMatchingException(Predicate<ClassRefBuilder> predicate);

  public Boolean hasMatchingException(Predicate<ClassRefBuilder> predicate);

  public A withExceptions(List<ClassRef> exceptions);

  public A withExceptions(ClassRef... exceptions);

  public Boolean hasExceptions();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> addNewException();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> addNewExceptionLike(ClassRef item);

  public io.sundr.model.MethodFluent.ExceptionsNested<A> setNewExceptionLike(int index, ClassRef item);

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editException(int index);

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editFirstException();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editLastException();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editMatchingException(Predicate<ClassRefBuilder> predicate);

  public boolean isDefaultMethod();

  public A withDefaultMethod(boolean defaultMethod);

  public Boolean hasDefaultMethod();

  /**
   * This method has been deprecated, please use method buildBlock instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public Block getBlock();

  public Block buildBlock();

  public A withBlock(Block block);

  public Boolean hasBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> withNewBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> withNewBlockLike(Block item);

  public io.sundr.model.MethodFluent.BlockNested<A> editBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlockLike(Block item);

  public interface AnnotationsNested<N>
      extends Nested<N>, AnnotationRefFluent<io.sundr.model.MethodFluent.AnnotationsNested<N>> {

    public N and();

    public N endAnnotation();
  }

  public interface ParametersNested<N> extends Nested<N>, TypeParamDefFluent<io.sundr.model.MethodFluent.ParametersNested<N>> {

    public N and();

    public N endParameter();
  }

  public interface TypeParamRefReturnTypeNested<N>
      extends Nested<N>, TypeParamRefFluent<io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<N>> {

    public N and();

    public N endTypeParamRefReturnType();
  }

  public interface WildcardRefReturnTypeNested<N>
      extends Nested<N>, WildcardRefFluent<io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<N>> {

    public N and();

    public N endWildcardRefReturnType();
  }

  public interface ClassRefReturnTypeNested<N>
      extends Nested<N>, ClassRefFluent<io.sundr.model.MethodFluent.ClassRefReturnTypeNested<N>> {

    public N and();

    public N endClassRefReturnType();
  }

  public interface PrimitiveRefReturnTypeNested<N>
      extends Nested<N>, PrimitiveRefFluent<io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<N>> {

    public N and();

    public N endPrimitiveRefReturnType();
  }

  public interface VoidRefReturnTypeNested<N>
      extends Nested<N>, VoidRefFluent<io.sundr.model.MethodFluent.VoidRefReturnTypeNested<N>> {

    public N and();

    public N endVoidRefReturnType();
  }

  public interface ArgumentsNested<N> extends Nested<N>, PropertyFluent<io.sundr.model.MethodFluent.ArgumentsNested<N>> {

    public N and();

    public N endArgument();
  }

  public interface ExceptionsNested<N> extends Nested<N>, ClassRefFluent<io.sundr.model.MethodFluent.ExceptionsNested<N>> {

    public N and();

    public N endException();
  }

  public interface BlockNested<N> extends Nested<N>, BlockFluent<io.sundr.model.MethodFluent.BlockNested<N>> {

    public N and();

    public N endBlock();
  }

}
