package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.util.Map;

import io.sundr.builder.Nested;

/**
 * Generated
 */
public interface AnnotationRefFluent<A extends AnnotationRefFluent<A>> extends AttributeSupportFluent<A> {

  /**
   * This method has been deprecated, please use method buildClassRef instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public ClassRef getClassRef();

  public io.sundr.model.ClassRef buildClassRef();

  public A withClassRef(io.sundr.model.ClassRef classRef);

  public Boolean hasClassRef();

  public AnnotationRefFluent.ClassRefNested<A> withNewClassRef();

  public io.sundr.model.AnnotationRefFluent.ClassRefNested<A> withNewClassRefLike(io.sundr.model.ClassRef item);

  public io.sundr.model.AnnotationRefFluent.ClassRefNested<A> editClassRef();

  public io.sundr.model.AnnotationRefFluent.ClassRefNested<A> editOrNewClassRef();

  public io.sundr.model.AnnotationRefFluent.ClassRefNested<A> editOrNewClassRefLike(io.sundr.model.ClassRef item);

  public A addToParameters(String key, Object value);

  public A addToParameters(Map<java.lang.String, java.lang.Object> map);

  public A removeFromParameters(java.lang.String key);

  public A removeFromParameters(java.util.Map<java.lang.String, java.lang.Object> map);

  public java.util.Map<java.lang.String, java.lang.Object> getParameters();

  public <K, V> A withParameters(java.util.Map<java.lang.String, java.lang.Object> parameters);

  public java.lang.Boolean hasParameters();

  public interface ClassRefNested<N> extends Nested<N>, ClassRefFluent<AnnotationRefFluent.ClassRefNested<N>> {
    public N and();

    public N endClassRef();

  }

}
