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

  public A setToTypes(java.lang.Integer index, io.sundr.model.TypeDef item);

  public A addToTypes(io.sundr.model.TypeDef... items);

  public A addAllToTypes(Collection<io.sundr.model.TypeDef> items);

  public A removeFromTypes(io.sundr.model.TypeDef... items);

  public A removeAllFromTypes(java.util.Collection<io.sundr.model.TypeDef> items);

  public A removeMatchingFromTypes(Predicate<TypeDefBuilder> predicate);

  /**
   * This method has been deprecated, please use method buildTypes instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.TypeDef> getTypes();

  public java.util.List<io.sundr.model.TypeDef> buildTypes();

  public io.sundr.model.TypeDef buildType(java.lang.Integer index);

  public io.sundr.model.TypeDef buildFirstType();

  public io.sundr.model.TypeDef buildLastType();

  public io.sundr.model.TypeDef buildMatchingType(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate);

  public Boolean hasMatchingType(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate);

  public A withTypes(java.util.List<io.sundr.model.TypeDef> types);

  public A withTypes(io.sundr.model.TypeDef... types);

  public java.lang.Boolean hasTypes();

  public A addNewType(String fullyQualifiedName);

  public SourceFluent.TypesNested<A> addNewType();

  public io.sundr.model.SourceFluent.TypesNested<A> addNewTypeLike(io.sundr.model.TypeDef item);

  public io.sundr.model.SourceFluent.TypesNested<A> setNewTypeLike(java.lang.Integer index, io.sundr.model.TypeDef item);

  public io.sundr.model.SourceFluent.TypesNested<A> editType(java.lang.Integer index);

  public io.sundr.model.SourceFluent.TypesNested<A> editFirstType();

  public io.sundr.model.SourceFluent.TypesNested<A> editLastType();

  public io.sundr.model.SourceFluent.TypesNested<A> editMatchingType(
      java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate);

  public interface TypesNested<N> extends Nested<N>, TypeDefFluent<SourceFluent.TypesNested<N>> {
    public N and();

    public N endType();

  }

}
