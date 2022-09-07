package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

/**
 * Generated
 */
public interface SourceFluent<A extends SourceFluent<A>> extends Fluent<A> {
  public A addToTypes(Integer index, TypeDef item);

  public A setToTypes(Integer index, TypeDef item);

  public A addToTypes(io.sundr.model.TypeDef... items);

  public A addAllToTypes(Collection<TypeDef> items);

  public A removeFromTypes(io.sundr.model.TypeDef... items);

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

  public TypeDef buildType(Integer index);

  public TypeDef buildFirstType();

  public TypeDef buildLastType();

  public TypeDef buildMatchingType(Predicate<TypeDefBuilder> predicate);

  public Boolean hasMatchingType(Predicate<TypeDefBuilder> predicate);

  public A withTypes(List<TypeDef> types);

  public A withTypes(io.sundr.model.TypeDef... types);

  public Boolean hasTypes();

  public A addNewType(String fullyQualifiedName);

  public SourceFluent.TypesNested<A> addNewType();

  public SourceFluent.TypesNested<A> addNewTypeLike(TypeDef item);

  public SourceFluent.TypesNested<A> setNewTypeLike(Integer index, TypeDef item);

  public SourceFluent.TypesNested<A> editType(Integer index);

  public SourceFluent.TypesNested<A> editFirstType();

  public SourceFluent.TypesNested<A> editLastType();

  public SourceFluent.TypesNested<A> editMatchingType(Predicate<TypeDefBuilder> predicate);

  public interface TypesNested<N> extends Nested<N>, TypeDefFluent<SourceFluent.TypesNested<N>> {
    public N and();

    public N endType();

  }

}
