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

  public ClassRef buildClassRef();

  public A withClassRef(ClassRef classRef);

  public Boolean hasClassRef();

  public AnnotationRefFluent.ClassRefNested<A> withNewClassRef();

  public AnnotationRefFluent.ClassRefNested<A> withNewClassRefLike(ClassRef item);

  public AnnotationRefFluent.ClassRefNested<A> editClassRef();

  public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRef();

  public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRefLike(ClassRef item);

  public A addToParameters(String key, Object value);

  public A addToParameters(Map<String, Object> map);

  public A removeFromParameters(String key);

  public A removeFromParameters(Map<String, Object> map);

  public Map<String, Object> getParameters();

  public <K, V> A withParameters(Map<String, Object> parameters);

  public Boolean hasParameters();

  public interface ClassRefNested<N> extends Nested<N>, ClassRefFluent<AnnotationRefFluent.ClassRefNested<N>> {
    public N and();

    public N endClassRef();

  }

}
