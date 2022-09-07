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

  public A withFullyQualifiedName(String fullyQualifiedName);

  public Boolean hasFullyQualifiedName();

  public int getDimensions();

  public A withDimensions(int dimensions);

  public Boolean hasDimensions();

  public A addToArguments(VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToArguments(Integer index, VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToArguments(Integer index, TypeRef item);

  public A setToArguments(Integer index, TypeRef item);

  public A addToArguments(io.sundr.model.TypeRef... items);

  public A addAllToArguments(Collection<TypeRef> items);

  public A removeFromArguments(VisitableBuilder<? extends TypeRef, ?> builder);

  public A removeFromArguments(io.sundr.model.TypeRef... items);

  public A removeAllFromArguments(Collection<TypeRef> items);

  /**
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeRef> getArguments();

  public List<TypeRef> buildArguments();

  public TypeRef buildArgument(Integer index);

  public TypeRef buildFirstArgument();

  public TypeRef buildLastArgument();

  public TypeRef buildMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public Boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public A withArguments(List<TypeRef> arguments);

  public A withArguments(io.sundr.model.TypeRef... arguments);

  public Boolean hasArguments();

  public A addToTypeParamRefArguments(Integer index, TypeParamRef item);

  public A setToTypeParamRefArguments(Integer index, TypeParamRef item);

  public A addToTypeParamRefArguments(io.sundr.model.TypeParamRef... items);

  public A addAllToTypeParamRefArguments(Collection<TypeParamRef> items);

  public A removeFromTypeParamRefArguments(io.sundr.model.TypeParamRef... items);

  public A removeAllFromTypeParamRefArguments(Collection<TypeParamRef> items);

  public A removeMatchingFromTypeParamRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public ClassRefFluent.TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(Integer index, TypeParamRef item);

  public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();

  public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);

  public A addToWildcardRefArguments(Integer index, WildcardRef item);

  public A setToWildcardRefArguments(Integer index, WildcardRef item);

  public A addToWildcardRefArguments(io.sundr.model.WildcardRef... items);

  public A addAllToWildcardRefArguments(Collection<WildcardRef> items);

  public A removeFromWildcardRefArguments(io.sundr.model.WildcardRef... items);

  public A removeAllFromWildcardRefArguments(Collection<WildcardRef> items);

  public A removeMatchingFromWildcardRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public ClassRefFluent.WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(Integer index, WildcardRef item);

  public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument();

  public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item);

  public A addToClassRefArguments(Integer index, ClassRef item);

  public A setToClassRefArguments(Integer index, ClassRef item);

  public A addToClassRefArguments(io.sundr.model.ClassRef... items);

  public A addAllToClassRefArguments(Collection<ClassRef> items);

  public A removeFromClassRefArguments(io.sundr.model.ClassRef... items);

  public A removeAllFromClassRefArguments(Collection<ClassRef> items);

  public A removeMatchingFromClassRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public ClassRefFluent.ClassRefArgumentsNested<A> setNewClassRefArgumentLike(Integer index, ClassRef item);

  public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument();

  public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item);

  public A addToPrimitiveRefArguments(Integer index, PrimitiveRef item);

  public A setToPrimitiveRefArguments(Integer index, PrimitiveRef item);

  public A addToPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items);

  public A addAllToPrimitiveRefArguments(Collection<PrimitiveRef> items);

  public A removeFromPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items);

  public A removeAllFromPrimitiveRefArguments(Collection<PrimitiveRef> items);

  public A removeMatchingFromPrimitiveRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(Integer index, PrimitiveRef item);

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item);

  public A addToVoidRefArguments(Integer index, VoidRef item);

  public A setToVoidRefArguments(Integer index, VoidRef item);

  public A addToVoidRefArguments(io.sundr.model.VoidRef... items);

  public A addAllToVoidRefArguments(Collection<VoidRef> items);

  public A removeFromVoidRefArguments(io.sundr.model.VoidRef... items);

  public A removeAllFromVoidRefArguments(Collection<VoidRef> items);

  public A removeMatchingFromVoidRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public ClassRefFluent.VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(Integer index, VoidRef item);

  public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument();

  public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item);

  public interface TypeParamRefArgumentsNested<N>
      extends Nested<N>, TypeParamRefFluent<ClassRefFluent.TypeParamRefArgumentsNested<N>> {
    public N and();

    public N endTypeParamRefArgument();

  }

  public interface WildcardRefArgumentsNested<N>
      extends Nested<N>, WildcardRefFluent<ClassRefFluent.WildcardRefArgumentsNested<N>> {
    public N and();

    public N endWildcardRefArgument();

  }

  public interface ClassRefArgumentsNested<N> extends Nested<N>, ClassRefFluent<ClassRefFluent.ClassRefArgumentsNested<N>> {
    public N and();

    public N endClassRefArgument();

  }

  public interface PrimitiveRefArgumentsNested<N>
      extends Nested<N>, PrimitiveRefFluent<ClassRefFluent.PrimitiveRefArgumentsNested<N>> {
    public N and();

    public N endPrimitiveRefArgument();

  }

  public interface VoidRefArgumentsNested<N> extends Nested<N>, VoidRefFluent<ClassRefFluent.VoidRefArgumentsNested<N>> {
    public N and();

    public N endVoidRefArgument();

  }

}
