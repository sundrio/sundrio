package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
public interface ClassRefFluent<A extends ClassRefFluent<A>> extends TypeRefFluent<A> {
  public String getFullyQualifiedName();

  public A withFullyQualifiedName(java.lang.String fullyQualifiedName);

  public Boolean hasFullyQualifiedName();

  public int getDimensions();

  public A withDimensions(int dimensions);

  public java.lang.Boolean hasDimensions();

  public A addToArguments(VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToArguments(Integer index, io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder);

  public A addToArguments(java.lang.Integer index, io.sundr.model.TypeRef item);

  public A setToArguments(java.lang.Integer index, io.sundr.model.TypeRef item);

  public A addToArguments(io.sundr.model.TypeRef... items);

  public A addAllToArguments(Collection<io.sundr.model.TypeRef> items);

  public A removeFromArguments(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder);

  public A removeFromArguments(io.sundr.model.TypeRef... items);

  public A removeAllFromArguments(java.util.Collection<io.sundr.model.TypeRef> items);

  /**
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.TypeRef> getArguments();

  public java.util.List<io.sundr.model.TypeRef> buildArguments();

  public io.sundr.model.TypeRef buildArgument(java.lang.Integer index);

  public io.sundr.model.TypeRef buildFirstArgument();

  public io.sundr.model.TypeRef buildLastArgument();

  public io.sundr.model.TypeRef buildMatchingArgument(
      Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public java.lang.Boolean hasMatchingArgument(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public A withArguments(java.util.List<io.sundr.model.TypeRef> arguments);

  public A withArguments(io.sundr.model.TypeRef... arguments);

  public java.lang.Boolean hasArguments();

  public A addToTypeParamRefArguments(java.lang.Integer index, TypeParamRef item);

  public A setToTypeParamRefArguments(java.lang.Integer index, io.sundr.model.TypeParamRef item);

  public A addToTypeParamRefArguments(io.sundr.model.TypeParamRef... items);

  public A addAllToTypeParamRefArguments(java.util.Collection<io.sundr.model.TypeParamRef> items);

  public A removeFromTypeParamRefArguments(io.sundr.model.TypeParamRef... items);

  public A removeAllFromTypeParamRefArguments(java.util.Collection<io.sundr.model.TypeParamRef> items);

  public A removeMatchingFromTypeParamRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public ClassRefFluent.TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(java.lang.Integer index,
      io.sundr.model.TypeParamRef item);

  public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();

  public io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(
      io.sundr.model.TypeParamRef item);

  public A addToWildcardRefArguments(java.lang.Integer index, WildcardRef item);

  public A setToWildcardRefArguments(java.lang.Integer index, io.sundr.model.WildcardRef item);

  public A addToWildcardRefArguments(io.sundr.model.WildcardRef... items);

  public A addAllToWildcardRefArguments(java.util.Collection<io.sundr.model.WildcardRef> items);

  public A removeFromWildcardRefArguments(io.sundr.model.WildcardRef... items);

  public A removeAllFromWildcardRefArguments(java.util.Collection<io.sundr.model.WildcardRef> items);

  public A removeMatchingFromWildcardRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public ClassRefFluent.WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(java.lang.Integer index,
      io.sundr.model.WildcardRef item);

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument();

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(
      io.sundr.model.WildcardRef item);

  public A addToClassRefArguments(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A setToClassRefArguments(java.lang.Integer index, io.sundr.model.ClassRef item);

  public A addToClassRefArguments(io.sundr.model.ClassRef... items);

  public A addAllToClassRefArguments(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeFromClassRefArguments(io.sundr.model.ClassRef... items);

  public A removeAllFromClassRefArguments(java.util.Collection<io.sundr.model.ClassRef> items);

  public A removeMatchingFromClassRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public ClassRefFluent.ClassRefArgumentsNested<A> setNewClassRefArgumentLike(java.lang.Integer index,
      io.sundr.model.ClassRef item);

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument();

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(io.sundr.model.ClassRef item);

  public A addToPrimitiveRefArguments(java.lang.Integer index, PrimitiveRef item);

  public A setToPrimitiveRefArguments(java.lang.Integer index, io.sundr.model.PrimitiveRef item);

  public A addToPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items);

  public A addAllToPrimitiveRefArguments(java.util.Collection<io.sundr.model.PrimitiveRef> items);

  public A removeFromPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items);

  public A removeAllFromPrimitiveRefArguments(java.util.Collection<io.sundr.model.PrimitiveRef> items);

  public A removeMatchingFromPrimitiveRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(java.lang.Integer index,
      io.sundr.model.PrimitiveRef item);

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(
      io.sundr.model.PrimitiveRef item);

  public A addToVoidRefArguments(java.lang.Integer index, VoidRef item);

  public A setToVoidRefArguments(java.lang.Integer index, io.sundr.model.VoidRef item);

  public A addToVoidRefArguments(io.sundr.model.VoidRef... items);

  public A addAllToVoidRefArguments(java.util.Collection<io.sundr.model.VoidRef> items);

  public A removeFromVoidRefArguments(io.sundr.model.VoidRef... items);

  public A removeAllFromVoidRefArguments(java.util.Collection<io.sundr.model.VoidRef> items);

  public A removeMatchingFromVoidRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate);

  public ClassRefFluent.VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(java.lang.Integer index,
      io.sundr.model.VoidRef item);

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument();

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(io.sundr.model.VoidRef item);

  public interface TypeParamRefArgumentsNested<N>
      extends Nested<N>, TypeParamRefFluent<ClassRefFluent.TypeParamRefArgumentsNested<N>> {
    public N and();

    public N endTypeParamRefArgument();

  }

  public interface WildcardRefArgumentsNested<N>
      extends io.sundr.builder.Nested<N>, WildcardRefFluent<ClassRefFluent.WildcardRefArgumentsNested<N>> {
    public N and();

    public N endWildcardRefArgument();

  }

  public interface ClassRefArgumentsNested<N>
      extends io.sundr.builder.Nested<N>, ClassRefFluent<ClassRefFluent.ClassRefArgumentsNested<N>> {
    public N and();

    public N endClassRefArgument();

  }

  public interface PrimitiveRefArgumentsNested<N>
      extends io.sundr.builder.Nested<N>, PrimitiveRefFluent<ClassRefFluent.PrimitiveRefArgumentsNested<N>> {
    public N and();

    public N endPrimitiveRefArgument();

  }

  public interface VoidRefArgumentsNested<N>
      extends io.sundr.builder.Nested<N>, VoidRefFluent<ClassRefFluent.VoidRefArgumentsNested<N>> {
    public N and();

    public N endVoidRefArgument();

  }

}
