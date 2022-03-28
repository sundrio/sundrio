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
public interface MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A> {
  public A addToComments(Integer index, String item);

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

  public Boolean hasMatchingComment(java.util.function.Predicate<java.lang.String> predicate);

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

  public MethodFluent.AnnotationsNested<A> addNewAnnotation();

  public io.sundr.model.MethodFluent.AnnotationsNested<A> addNewAnnotationLike(io.sundr.model.AnnotationRef item);

  public io.sundr.model.MethodFluent.AnnotationsNested<A> setNewAnnotationLike(java.lang.Integer index,
      io.sundr.model.AnnotationRef item);

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editAnnotation(java.lang.Integer index);

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editFirstAnnotation();

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editLastAnnotation();

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

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

  public MethodFluent.ParametersNested<A> addNewParameter();

  public io.sundr.model.MethodFluent.ParametersNested<A> addNewParameterLike(io.sundr.model.TypeParamDef item);

  public io.sundr.model.MethodFluent.ParametersNested<A> setNewParameterLike(java.lang.Integer index,
      io.sundr.model.TypeParamDef item);

  public io.sundr.model.MethodFluent.ParametersNested<A> editParameter(java.lang.Integer index);

  public io.sundr.model.MethodFluent.ParametersNested<A> editFirstParameter();

  public io.sundr.model.MethodFluent.ParametersNested<A> editLastParameter();

  public io.sundr.model.MethodFluent.ParametersNested<A> editMatchingParameter(
      java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate);

  public java.lang.String getName();

  public A withName(java.lang.String name);

  public java.lang.Boolean hasName();

  /**
   * This method has been deprecated, please use method buildReturnType instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public TypeRef getReturnType();

  public io.sundr.model.TypeRef buildReturnType();

  public A withReturnType(io.sundr.model.TypeRef returnType);

  public java.lang.Boolean hasReturnType();

  public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType);

  public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType();

  public io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(
      io.sundr.model.TypeParamRef item);

  public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType);

  public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType();

  public io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(
      io.sundr.model.WildcardRef item);

  public A withClassRefReturnType(ClassRef classRefReturnType);

  public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType();

  public io.sundr.model.MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(io.sundr.model.ClassRef item);

  public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType);

  public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType();

  public io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(
      io.sundr.model.PrimitiveRef item);

  public A withVoidRefReturnType(VoidRef voidRefReturnType);

  public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType();

  public io.sundr.model.MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(io.sundr.model.VoidRef item);

  public A addToArguments(java.lang.Integer index, Property item);

  public A setToArguments(java.lang.Integer index, io.sundr.model.Property item);

  public A addToArguments(io.sundr.model.Property... items);

  public A addAllToArguments(java.util.Collection<io.sundr.model.Property> items);

  public A removeFromArguments(io.sundr.model.Property... items);

  public A removeAllFromArguments(java.util.Collection<io.sundr.model.Property> items);

  public A removeMatchingFromArguments(java.util.function.Predicate<PropertyBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Property> getArguments();

  public java.util.List<io.sundr.model.Property> buildArguments();

  public io.sundr.model.Property buildArgument(java.lang.Integer index);

  public io.sundr.model.Property buildFirstArgument();

  public io.sundr.model.Property buildLastArgument();

  public io.sundr.model.Property buildMatchingArgument(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate);

  public java.lang.Boolean hasMatchingArgument(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate);

  public A withArguments(java.util.List<io.sundr.model.Property> arguments);

  public A withArguments(io.sundr.model.Property... arguments);

  public java.lang.Boolean hasArguments();

  public MethodFluent.ArgumentsNested<A> addNewArgument();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> addNewArgumentLike(io.sundr.model.Property item);

  public io.sundr.model.MethodFluent.ArgumentsNested<A> setNewArgumentLike(java.lang.Integer index,
      io.sundr.model.Property item);

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editArgument(java.lang.Integer index);

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editFirstArgument();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editLastArgument();

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editMatchingArgument(
      java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate);

  public boolean isVarArgPreferred();

  public A withVarArgPreferred(boolean varArgPreferred);

  public java.lang.Boolean hasVarArgPreferred();

  public A addToExceptions(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A setToExceptions(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A addToExceptions(io.sundr.model.ClassRef... items);

  public A addAllToExceptions(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeFromExceptions(io.sundr.model.ClassRef... items);

  public A removeAllFromExceptions(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeMatchingFromExceptions(java.util.function.Predicate<ClassRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildExceptions instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.ClassRef> getExceptions();

  public java.util.List<io.sundr.model.ClassRef> buildExceptions();

  public io.sundr.model.ClassRef buildException(java.lang.Integer index);

  public io.sundr.model.ClassRef buildFirstException();

  public io.sundr.model.ClassRef buildLastException();

  public io.sundr.model.ClassRef buildMatchingException(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public java.lang.Boolean hasMatchingException(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public A withExceptions(java.util.List<io.sundr.model.ClassRef> exceptions);

  public A withExceptions(io.sundr.model.ClassRef... exceptions);

  public java.lang.Boolean hasExceptions();

  public MethodFluent.ExceptionsNested<A> addNewException();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> addNewExceptionLike(io.sundr.model.ClassRef item);

  public io.sundr.model.MethodFluent.ExceptionsNested<A> setNewExceptionLike(java.lang.Integer index,
      io.sundr.model.ClassRef item);

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editException(java.lang.Integer index);

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editFirstException();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editLastException();

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editMatchingException(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate);

  public boolean isDefaultMethod();

  public A withDefaultMethod(boolean defaultMethod);

  public java.lang.Boolean hasDefaultMethod();

  /**
   * This method has been deprecated, please use method buildBlock instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public Block getBlock();

  public io.sundr.model.Block buildBlock();

  public A withBlock(io.sundr.model.Block block);

  public java.lang.Boolean hasBlock();

  public MethodFluent.BlockNested<A> withNewBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> withNewBlockLike(io.sundr.model.Block item);

  public io.sundr.model.MethodFluent.BlockNested<A> editBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlock();

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlockLike(io.sundr.model.Block item);

  public A withVarArgPreferred();

  public A withDefaultMethod();

  public interface AnnotationsNested<N> extends Nested<N>, AnnotationRefFluent<MethodFluent.AnnotationsNested<N>> {
    public N and();

    public N endAnnotation();

  }

  public interface ParametersNested<N>
      extends io.sundr.builder.Nested<N>, TypeParamDefFluent<MethodFluent.ParametersNested<N>> {
    public N and();

    public N endParameter();

  }

  public interface TypeParamRefReturnTypeNested<N>
      extends io.sundr.builder.Nested<N>, TypeParamRefFluent<MethodFluent.TypeParamRefReturnTypeNested<N>> {
    public N and();

    public N endTypeParamRefReturnType();

  }

  public interface WildcardRefReturnTypeNested<N>
      extends io.sundr.builder.Nested<N>, WildcardRefFluent<MethodFluent.WildcardRefReturnTypeNested<N>> {
    public N and();

    public N endWildcardRefReturnType();

  }

  public interface ClassRefReturnTypeNested<N>
      extends io.sundr.builder.Nested<N>, ClassRefFluent<MethodFluent.ClassRefReturnTypeNested<N>> {
    public N and();

    public N endClassRefReturnType();

  }

  public interface PrimitiveRefReturnTypeNested<N>
      extends io.sundr.builder.Nested<N>, PrimitiveRefFluent<MethodFluent.PrimitiveRefReturnTypeNested<N>> {
    public N and();

    public N endPrimitiveRefReturnType();

  }

  public interface VoidRefReturnTypeNested<N>
      extends io.sundr.builder.Nested<N>, VoidRefFluent<MethodFluent.VoidRefReturnTypeNested<N>> {
    public N and();

    public N endVoidRefReturnType();

  }

  public interface ArgumentsNested<N> extends io.sundr.builder.Nested<N>, PropertyFluent<MethodFluent.ArgumentsNested<N>> {
    public N and();

    public N endArgument();

  }

  public interface ExceptionsNested<N> extends io.sundr.builder.Nested<N>, ClassRefFluent<MethodFluent.ExceptionsNested<N>> {
    public N and();

    public N endException();

  }

  public interface BlockNested<N> extends io.sundr.builder.Nested<N>, BlockFluent<MethodFluent.BlockNested<N>> {
    public N and();

    public N endBlock();

  }

}
