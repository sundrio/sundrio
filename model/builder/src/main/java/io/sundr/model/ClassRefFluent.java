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
import io.sundr.builder.VisitableBuilder;

public interface ClassRefFluent<A extends ClassRefFluent<A>> extends TypeRefFluent<A> {

  public String getFullyQualifiedName();

  public A withFullyQualifiedName(String fullyQualifiedName);

  public Boolean hasFullyQualifiedName();

  public A withNewFullyQualifiedName(StringBuilder arg1);

  public A withNewFullyQualifiedName(int[] arg1, int arg2, int arg3);

  public A withNewFullyQualifiedName(char[] arg1);

  public A withNewFullyQualifiedName(StringBuffer arg1);

  public A withNewFullyQualifiedName(byte[] arg1, int arg2);

  public A withNewFullyQualifiedName(byte[] arg1);

  public A withNewFullyQualifiedName(char[] arg1, int arg2, int arg3);

  public A withNewFullyQualifiedName(byte[] arg1, int arg2, int arg3);

  public A withNewFullyQualifiedName(byte[] arg1, int arg2, int arg3, int arg4);

  public A withNewFullyQualifiedName(String arg1);

  public int getDimensions();

  public A withDimensions(int dimensions);

  public Boolean hasDimensions();

  public A addToArguments(VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToArguments(int index, VisitableBuilder<? extends TypeRef, ?> builder);

  public A addToArguments(int index, TypeRef item);

  public A setToArguments(int index, TypeRef item);

  public A addToArguments(TypeRef... items);

  public A addAllToArguments(Collection<TypeRef> items);

  public A removeFromArguments(VisitableBuilder<? extends TypeRef, ?> builder);

  public A removeFromArguments(TypeRef... items);

  public A removeAllFromArguments(Collection<TypeRef> items);

  public A removeMatchingFromArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  /**
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeRef> getArguments();

  public List<TypeRef> buildArguments();

  public TypeRef buildArgument(int index);

  public TypeRef buildFirstArgument();

  public TypeRef buildLastArgument();

  public TypeRef buildMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public Boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public A withArguments(List<TypeRef> arguments);

  public A withArguments(TypeRef... arguments);

  public Boolean hasArguments();

  public A addToTypeParamRefArguments(int index, TypeParamRef item);

  public A setToTypeParamRefArguments(int index, TypeParamRef item);

  public A addToTypeParamRefArguments(TypeParamRef... items);

  public A addAllToTypeParamRefArguments(Collection<TypeParamRef> items);

  public A removeFromTypeParamRefArguments(TypeParamRef... items);

  public A removeAllFromTypeParamRefArguments(Collection<TypeParamRef> items);

  public A removeMatchingFromTypeParamRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(int index,
      TypeParamRef item);

  public io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument();

  public io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item);

  public A addToWildcardRefArguments(int index, WildcardRef item);

  public A setToWildcardRefArguments(int index, WildcardRef item);

  public A addToWildcardRefArguments(WildcardRef... items);

  public A addAllToWildcardRefArguments(Collection<WildcardRef> items);

  public A removeFromWildcardRefArguments(WildcardRef... items);

  public A removeAllFromWildcardRefArguments(Collection<WildcardRef> items);

  public A removeMatchingFromWildcardRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(int index, WildcardRef item);

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument();

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item);

  public A addToClassRefArguments(int index, ClassRef item);

  public A setToClassRefArguments(int index, ClassRef item);

  public A addToClassRefArguments(ClassRef... items);

  public A addAllToClassRefArguments(Collection<ClassRef> items);

  public A removeFromClassRefArguments(ClassRef... items);

  public A removeAllFromClassRefArguments(Collection<ClassRef> items);

  public A removeMatchingFromClassRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> setNewClassRefArgumentLike(int index, ClassRef item);

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument();

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item);

  public A addToPrimitiveRefArguments(int index, PrimitiveRef item);

  public A setToPrimitiveRefArguments(int index, PrimitiveRef item);

  public A addToPrimitiveRefArguments(PrimitiveRef... items);

  public A addAllToPrimitiveRefArguments(Collection<PrimitiveRef> items);

  public A removeFromPrimitiveRefArguments(PrimitiveRef... items);

  public A removeAllFromPrimitiveRefArguments(Collection<PrimitiveRef> items);

  public A removeMatchingFromPrimitiveRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(int index,
      PrimitiveRef item);

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument();

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item);

  public A addToVoidRefArguments(int index, VoidRef item);

  public A setToVoidRefArguments(int index, VoidRef item);

  public A addToVoidRefArguments(VoidRef... items);

  public A addAllToVoidRefArguments(Collection<VoidRef> items);

  public A removeFromVoidRefArguments(VoidRef... items);

  public A removeAllFromVoidRefArguments(Collection<VoidRef> items);

  public A removeMatchingFromVoidRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate);

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(int index, VoidRef item);

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument();

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item);

  public interface TypeParamRefArgumentsNested<N>
      extends Nested<N>, TypeParamRefFluent<io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<N>> {

    public N and();

    public N endTypeParamRefArgument();
  }

  public interface WildcardRefArgumentsNested<N>
      extends Nested<N>, WildcardRefFluent<io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<N>> {

    public N and();

    public N endWildcardRefArgument();
  }

  public interface ClassRefArgumentsNested<N>
      extends Nested<N>, ClassRefFluent<io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<N>> {

    public N and();

    public N endClassRefArgument();
  }

  public interface PrimitiveRefArgumentsNested<N>
      extends Nested<N>, PrimitiveRefFluent<io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<N>> {

    public N and();

    public N endPrimitiveRefArgument();
  }

  public interface VoidRefArgumentsNested<N>
      extends Nested<N>, VoidRefFluent<io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<N>> {

    public N and();

    public N endVoidRefArgument();
  }

}
