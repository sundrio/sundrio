package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.LinkedHashMap;
import java.util.Map;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class AttributeSupportFluentImpl<A extends AttributeSupportFluent<A>> extends BaseFluent<A>
    implements AttributeSupportFluent<A> {
  public AttributeSupportFluentImpl() {
  }

  public AttributeSupportFluentImpl(AttributeSupport instance) {
    this.withAttributes(instance.getAttributes());
  }

  private Map<AttributeKey, Object> attributes = new LinkedHashMap<AttributeKey, Object>();

  public A addToAttributes(AttributeKey key, Object value) {
    if (this.attributes == null && key != null && value != null) {
      this.attributes = new LinkedHashMap();
    }
    if (key != null && value != null) {
      this.attributes.put(key, value);
    }
    return (A) this;
  }

  public A addToAttributes(Map<AttributeKey, Object> map) {
    if (this.attributes == null && map != null) {
      this.attributes = new LinkedHashMap();
    }
    if (map != null) {
      this.attributes.putAll(map);
    }
    return (A) this;
  }

  public A removeFromAttributes(AttributeKey key) {
    if (this.attributes == null) {
      return (A) this;
    }
    if (key != null && this.attributes != null) {
      this.attributes.remove(key);
    }
    return (A) this;
  }

  public A removeFromAttributes(Map<AttributeKey, Object> map) {
    if (this.attributes == null) {
      return (A) this;
    }
    if (map != null) {
      for (Object key : map.keySet()) {
        if (this.attributes != null) {
          this.attributes.remove(key);
        }
      }
    }
    return (A) this;
  }

  public Map<AttributeKey, Object> getAttributes() {
    return this.attributes;
  }

  public <K, V> A withAttributes(Map<AttributeKey, Object> attributes) {
    if (attributes == null) {
      this.attributes = null;
    } else {
      this.attributes = new LinkedHashMap(attributes);
    }
    return (A) this;
  }

  public Boolean hasAttributes() {
    return this.attributes != null;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AttributeSupportFluentImpl that = (AttributeSupportFluentImpl) o;
    if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(attributes, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (attributes != null && !attributes.isEmpty()) {
      sb.append("attributes:");
      sb.append(attributes);
    }
    sb.append("}");
    return sb.toString();
  }

}
