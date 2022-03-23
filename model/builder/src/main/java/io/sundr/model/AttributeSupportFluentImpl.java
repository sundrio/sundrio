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

  public AttributeSupportFluentImpl(io.sundr.model.AttributeSupport instance) {
    this.withAttributes(instance.getAttributes());
  }

  private Map<AttributeKey, Object> attributes = new LinkedHashMap<io.sundr.model.AttributeKey, java.lang.Object>();

  public A addToAttributes(io.sundr.model.AttributeKey key, java.lang.Object value) {
    if (this.attributes == null && key != null && value != null) {
      this.attributes = new java.util.LinkedHashMap();
    }
    if (key != null && value != null) {
      this.attributes.put(key, value);
    }
    return (A) this;
  }

  public A addToAttributes(java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> map) {
    if (this.attributes == null && map != null) {
      this.attributes = new java.util.LinkedHashMap();
    }
    if (map != null) {
      this.attributes.putAll(map);
    }
    return (A) this;
  }

  public A removeFromAttributes(io.sundr.model.AttributeKey key) {
    if (this.attributes == null) {
      return (A) this;
    }
    if (key != null && this.attributes != null) {
      this.attributes.remove(key);
    }
    return (A) this;
  }

  public A removeFromAttributes(java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> map) {
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

  public java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> getAttributes() {
    return this.attributes;
  }

  public <K, V> A withAttributes(java.util.Map<io.sundr.model.AttributeKey, java.lang.Object> attributes) {
    if (attributes == null) {
      this.attributes = null;
    } else {
      this.attributes = new java.util.LinkedHashMap(attributes);
    }
    return (A) this;
  }

  public Boolean hasAttributes() {
    return this.attributes != null;
  }

  public boolean equals(java.lang.Object o) {
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
