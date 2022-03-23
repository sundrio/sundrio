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
public interface PropertyFluent<A extends PropertyFluent<A>> extends ModifierSupportFluent<A> {
  public A addToAnnotations(Integer index, AnnotationRef item);

  public A setToAnnotations(java.lang.Integer index, io.sundr.model.AnnotationRef item);

  public A addToAnnotations(io.sundr.model.AnnotationRef... items);

  public A addAllToAnnotations(Collection<io.sundr.model.AnnotationRef> items);

  public A removeFromAnnotations(io.sundr.model.AnnotationRef... items);

  public A removeAllFromAnnotations(java.util.Collection<io.sundr.model.AnnotationRef> items);

  public A removeMatchingFromAnnotations(Predicate<AnnotationRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildAnnotations instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.AnnotationRef> getAnnotations();

  public java.util.List<io.sundr.model.AnnotationRef> buildAnnotations();

  public io.sundr.model.AnnotationRef buildAnnotation(java.lang.Integer index);

  public io.sundr.model.AnnotationRef buildFirstAnnotation();

  public io.sundr.model.AnnotationRef buildLastAnnotation();

  public io.sundr.model.AnnotationRef buildMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

  public Boolean hasMatchingAnnotation(java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

  public A withAnnotations(java.util.List<io.sundr.model.AnnotationRef> annotations);

  public A withAnnotations(io.sundr.model.AnnotationRef... annotations);

  public java.lang.Boolean hasAnnotations();

  public PropertyFluent.AnnotationsNested<A> addNewAnnotation();

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(io.sundr.model.AnnotationRef item);

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(java.lang.Integer index,
      io.sundr.model.AnnotationRef item);

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editAnnotation(java.lang.Integer index);

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editFirstAnnotation();

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editLastAnnotation();

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildTypeRef instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public TypeRef getTypeRef();

  public io.sundr.model.TypeRef buildTypeRef();

  public A withTypeRef(io.sundr.model.TypeRef typeRef);

  public java.lang.Boolean hasTypeRef();

  public A withTypeParamRefType(TypeParamRef typeParamRefType);

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType();

  public io.sundr.model.PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(io.sundr.model.TypeParamRef item);

  public A withWildcardRefType(WildcardRef wildcardRefType);

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType();

  public io.sundr.model.PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(io.sundr.model.WildcardRef item);

  public A withClassRefType(ClassRef classRefType);

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefType();

  public io.sundr.model.PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(io.sundr.model.ClassRef item);

  public A withPrimitiveRefType(PrimitiveRef primitiveRefType);

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType();

  public io.sundr.model.PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(io.sundr.model.PrimitiveRef item);

  public A withVoidRefType(VoidRef voidRefType);

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType();

  public io.sundr.model.PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(io.sundr.model.VoidRef item);

  public String getName();

  public A withName(java.lang.String name);

  public java.lang.Boolean hasName();

  public A addToComments(java.lang.Integer index, java.lang.String item);

  public A setToComments(java.lang.Integer index, java.lang.String item);

  public A addToComments(java.lang.String... items);

  public A addAllToComments(java.util.Collection<java.lang.String> items);

  public A removeFromComments(java.lang.String... items);

  public A removeAllFromComments(java.util.Collection<java.lang.String> items);

  public java.util.List<java.lang.String> getComments();

  public java.lang.String getComment(java.lang.Integer index);

  public java.lang.String getFirstComment();

  public java.lang.String getLastComment();

  public java.lang.String getMatchingComment(java.util.function.Predicate<java.lang.String> predicate);

  public java.lang.Boolean hasMatchingComment(java.util.function.Predicate<java.lang.String> predicate);

  public A withComments(java.util.List<java.lang.String> comments);

  public A withComments(java.lang.String... comments);

  public java.lang.Boolean hasComments();

  public interface AnnotationsNested<N> extends Nested<N>, AnnotationRefFluent<PropertyFluent.AnnotationsNested<N>> {
    public N and();

    public N endAnnotation();

  }

  public interface TypeParamRefTypeNested<N>
      extends io.sundr.builder.Nested<N>, TypeParamRefFluent<PropertyFluent.TypeParamRefTypeNested<N>> {
    public N and();

    public N endTypeParamRefType();

  }

  public interface WildcardRefTypeNested<N>
      extends io.sundr.builder.Nested<N>, WildcardRefFluent<PropertyFluent.WildcardRefTypeNested<N>> {
    public N and();

    public N endWildcardRefType();

  }

  public interface ClassRefTypeNested<N>
      extends io.sundr.builder.Nested<N>, ClassRefFluent<PropertyFluent.ClassRefTypeNested<N>> {
    public N and();

    public N endClassRefType();

  }

  public interface PrimitiveRefTypeNested<N>
      extends io.sundr.builder.Nested<N>, PrimitiveRefFluent<PropertyFluent.PrimitiveRefTypeNested<N>> {
    public N and();

    public N endPrimitiveRefType();

  }

  public interface VoidRefTypeNested<N> extends io.sundr.builder.Nested<N>, VoidRefFluent<PropertyFluent.VoidRefTypeNested<N>> {
    public N and();

    public N endVoidRefType();

  }

}
