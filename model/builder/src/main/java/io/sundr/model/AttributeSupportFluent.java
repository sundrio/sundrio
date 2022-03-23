package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.util.Map;

import io.sundr.builder.Fluent;

/**
 * Generated
 */
public interface AttributeSupportFluent<A extends AttributeSupportFluent<A>> extends Fluent<A> {
  public A addToAttributes(AttributeKey key, Object value);

  public A addToAttributes(Map<io.sundr.model.AttributeKey, java.lang.Object> map);

  public A removeFromAttributes(io.sundr.model.AttributeKey key);

  public A removeFromAttributes(java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> map);

  public java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> getAttributes();

  public <K, V> A withAttributes(java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> attributes);

  public Boolean hasAttributes();

}
