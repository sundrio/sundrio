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

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> addNewAnnotation();

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item);

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item);

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editAnnotation(int index);

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editFirstAnnotation();

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editLastAnnotation();

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate);

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

  public io.sundr.model.PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType();

  public io.sundr.model.PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item);

  public A withWildcardRefType(WildcardRef wildcardRefType);

  public io.sundr.model.PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType();

  public io.sundr.model.PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item);

  public A withClassRefType(ClassRef classRefType);

  public io.sundr.model.PropertyFluent.ClassRefTypeNested<A> withNewClassRefType();

  public io.sundr.model.PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item);

  public A withPrimitiveRefType(PrimitiveRef primitiveRefType);

  public io.sundr.model.PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType();

  public io.sundr.model.PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item);

  public A withVoidRefType(VoidRef voidRefType);

  public io.sundr.model.PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType();

  public io.sundr.model.PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item);

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

  public interface AnnotationsNested<N>
      extends io.sundr.model.builder.Nested<N>, AnnotationRefFluent<io.sundr.model.PropertyFluent.AnnotationsNested<N>> {

    public N and();

    public N endAnnotation();
  }

  public interface TypeParamRefTypeNested<N>
      extends io.sundr.model.builder.Nested<N>, TypeParamRefFluent<io.sundr.model.PropertyFluent.TypeParamRefTypeNested<N>> {

    public N and();

    public N endTypeParamRefType();
  }

  public interface WildcardRefTypeNested<N>
      extends io.sundr.model.builder.Nested<N>, WildcardRefFluent<io.sundr.model.PropertyFluent.WildcardRefTypeNested<N>> {

    public N and();

    public N endWildcardRefType();
  }

  public interface ClassRefTypeNested<N>
      extends io.sundr.model.builder.Nested<N>, ClassRefFluent<io.sundr.model.PropertyFluent.ClassRefTypeNested<N>> {

    public N and();

    public N endClassRefType();
  }

  public interface PrimitiveRefTypeNested<N>
      extends io.sundr.model.builder.Nested<N>, PrimitiveRefFluent<io.sundr.model.PropertyFluent.PrimitiveRefTypeNested<N>> {

    public N and();

    public N endPrimitiveRefType();
  }

  public interface VoidRefTypeNested<N>
      extends io.sundr.model.builder.Nested<N>, VoidRefFluent<io.sundr.model.PropertyFluent.VoidRefTypeNested<N>> {

    public N and();

    public N endVoidRefType();
  }

}
