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

  public A addToAttributes(Map<AttributeKey, Object> map);

  public A removeFromAttributes(AttributeKey key);

  public A removeFromAttributes(Map<AttributeKey, Object> map);

  public Map<AttributeKey, Object> getAttributes();

  public <K, V> A withAttributes(Map<AttributeKey, Object> attributes);

  public Boolean hasAttributes();

}
