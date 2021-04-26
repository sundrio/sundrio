package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

public interface SourceFluent<A extends SourceFluent<A>> extends Fluent<A> {

  public A addToTypes(int index, TypeDef item);

  public A setToTypes(int index, TypeDef item);

  public A addToTypes(TypeDef... items);

  public A addAllToTypes(Collection<TypeDef> items);

  public A removeFromTypes(TypeDef... items);

  public A removeAllFromTypes(Collection<TypeDef> items);

  public A removeMatchingFromTypes(Predicate<TypeDefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildTypes instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeDef> getTypes();

  public List<TypeDef> buildTypes();

  public TypeDef buildType(int index);

  public TypeDef buildFirstType();

  public TypeDef buildLastType();

  public TypeDef buildMatchingType(Predicate<TypeDefBuilder> predicate);

  public Boolean hasMatchingType(Predicate<TypeDefBuilder> predicate);

  public A withTypes(List<TypeDef> types);

  public A withTypes(TypeDef... types);

  public Boolean hasTypes();

  public A addNewType(String fullyQualifiedName);

  public io.sundr.model.SourceFluent.TypesNested<A> addNewType();

  public io.sundr.model.SourceFluent.TypesNested<A> addNewTypeLike(TypeDef item);

  public io.sundr.model.SourceFluent.TypesNested<A> setNewTypeLike(int index, TypeDef item);

  public io.sundr.model.SourceFluent.TypesNested<A> editType(int index);

  public io.sundr.model.SourceFluent.TypesNested<A> editFirstType();

  public io.sundr.model.SourceFluent.TypesNested<A> editLastType();

  public io.sundr.model.SourceFluent.TypesNested<A> editMatchingType(Predicate<TypeDefBuilder> predicate);

  public interface TypesNested<N> extends Nested<N>, TypeDefFluent<io.sundr.model.SourceFluent.TypesNested<N>> {

    public N and();

    public N endType();
  }

}
