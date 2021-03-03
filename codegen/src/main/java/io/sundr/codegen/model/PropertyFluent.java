package io.sundr.codegen.model;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;

public interface PropertyFluent<A extends PropertyFluent<A>> extends ModifierSupportFluent<A> {

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

  public PropertyFluent.AnnotationsNested<A> addNewAnnotation();

  public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

  public PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item);

  public PropertyFluent.AnnotationsNested<A> editAnnotation(int index);

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

  public A withPrimitiveRefType(PrimitiveRef primitiveRefType);

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType();

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item);

  public A withVoidRefType(VoidRef voidRefType);

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType();

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item);

  public A withWildcardRefType(WildcardRef wildcardRefType);

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType();

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item);

  public A withClassRefType(ClassRef classRefType);

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefType();

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item);

  public A withTypeParamRefType(TypeParamRef typeParamRefType);

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType();

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item);

  public String getName();

  public A withName(String name);

  public Boolean hasName();

  public A withNewName(String arg1);

  public A withNewName(StringBuilder arg1);

  public A withNewName(StringBuffer arg1);

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

  public A addNewComment(String arg1);

  public A addNewComment(StringBuilder arg1);

  public A addNewComment(StringBuffer arg1);

  public interface AnnotationsNested<N> extends Nested<N>, AnnotationRefFluent<PropertyFluent.AnnotationsNested<N>> {

    public N and();

    public N endAnnotation();
  }

  public interface PrimitiveRefTypeNested<N> extends Nested<N>, PrimitiveRefFluent<PropertyFluent.PrimitiveRefTypeNested<N>> {

    public N and();

    public N endPrimitiveRefType();
  }

  public interface VoidRefTypeNested<N> extends Nested<N>, VoidRefFluent<PropertyFluent.VoidRefTypeNested<N>> {

    public N and();

    public N endVoidRefType();
  }

  public interface WildcardRefTypeNested<N> extends Nested<N>, WildcardRefFluent<PropertyFluent.WildcardRefTypeNested<N>> {

    public N and();

    public N endWildcardRefType();
  }

  public interface ClassRefTypeNested<N> extends Nested<N>, ClassRefFluent<PropertyFluent.ClassRefTypeNested<N>> {

    public N and();

    public N endClassRefType();
  }

  public interface TypeParamRefTypeNested<N> extends Nested<N>, TypeParamRefFluent<PropertyFluent.TypeParamRefTypeNested<N>> {

    public N and();

    public N endTypeParamRefType();
  }

}
