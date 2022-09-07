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

  public PropertyFluent.AnnotationsNested<A> addNewAnnotation();

  public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

  public PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(Integer index, AnnotationRef item);

  public PropertyFluent.AnnotationsNested<A> editAnnotation(Integer index);

  public PropertyFluent.AnnotationsNested<A> editFirstAnnotation();

  public PropertyFluent.AnnotationsNested<A> editLastAnnotation();

  public PropertyFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildTypeRef instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public TypeRef getTypeRef();

  public TypeRef buildTypeRef();

  public A withTypeRef(TypeRef typeRef);

  public Boolean hasTypeRef();

  public A withTypeParamRefType(TypeParamRef typeParamRefType);

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType();

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item);

  public A withWildcardRefType(WildcardRef wildcardRefType);

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType();

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item);

  public A withClassRefType(ClassRef classRefType);

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefType();

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item);

  public A withPrimitiveRefType(PrimitiveRef primitiveRefType);

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType();

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item);

  public A withVoidRefType(VoidRef voidRefType);

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType();

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item);

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

  public interface AnnotationsNested<N> extends Nested<N>, AnnotationRefFluent<PropertyFluent.AnnotationsNested<N>> {
    public N and();

    public N endAnnotation();

  }

  public interface TypeParamRefTypeNested<N> extends Nested<N>, TypeParamRefFluent<PropertyFluent.TypeParamRefTypeNested<N>> {
    public N and();

    public N endTypeParamRefType();

  }

  public interface WildcardRefTypeNested<N> extends Nested<N>, WildcardRefFluent<PropertyFluent.WildcardRefTypeNested<N>> {
    public N and();

    public N endWildcardRefType();

  }

  public interface ClassRefTypeNested<N> extends Nested<N>, ClassRefFluent<PropertyFluent.ClassRefTypeNested<N>> {
    public N and();

    public N endClassRefType();

  }

  public interface PrimitiveRefTypeNested<N> extends Nested<N>, PrimitiveRefFluent<PropertyFluent.PrimitiveRefTypeNested<N>> {
    public N and();

    public N endPrimitiveRefType();

  }

  public interface VoidRefTypeNested<N> extends Nested<N>, VoidRefFluent<PropertyFluent.VoidRefTypeNested<N>> {
    public N and();

    public N endVoidRefType();

  }

}
