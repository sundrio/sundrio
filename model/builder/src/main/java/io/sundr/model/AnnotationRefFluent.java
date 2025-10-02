package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.LinkedHashMap;
import java.util.Map;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class AnnotationRefFluent<A extends AnnotationRefFluent<A>> extends AttributeSupportFluent<A> {

  private ClassRefBuilder classRef;
  private Map<String, Object> parameters = new LinkedHashMap<String, Object>();

  public AnnotationRefFluent() {
  }

  public AnnotationRefFluent(AnnotationRef instance) {
    this.copyInstance(instance);
  }

  public A addToParameters(Map<String, Object> map) {
    if (this.parameters == null && map != null) {
      this.parameters = new LinkedHashMap();
    }
    if (map != null) {
      this.parameters.putAll(map);
    }
    return (A) this;
  }

  public A addToParameters(String key, Object value) {
    if (this.parameters == null && key != null && value != null) {
      this.parameters = new LinkedHashMap();
    }
    if (key != null && value != null) {
      this.parameters.put(key, value);
    }
    return (A) this;
  }

  public ClassRef buildClassRef() {
    return this.classRef != null ? this.classRef.build() : null;
  }

  protected void copyInstance(AnnotationRef instance) {
    if (instance != null) {
      this.withClassRef(instance.getClassRef());
      this.withParameters(instance.getParameters());
      this.withAttributes(instance.getAttributes());
    }
  }

  public ClassRefNested<A> editClassRef() {
    return withNewClassRefLike(java.util.Optional.ofNullable(buildClassRef()).orElse(null));
  }

  public ClassRefNested<A> editOrNewClassRef() {
    return withNewClassRefLike(java.util.Optional.ofNullable(buildClassRef()).orElse(new ClassRefBuilder().build()));
  }

  public ClassRefNested<A> editOrNewClassRefLike(ClassRef item) {
    return withNewClassRefLike(java.util.Optional.ofNullable(buildClassRef()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    AnnotationRefFluent that = (AnnotationRefFluent) o;
    if (!java.util.Objects.equals(classRef, that.classRef))
      return false;
    if (!java.util.Objects.equals(parameters, that.parameters))
      return false;
    return true;
  }

  public Map<String, Object> getParameters() {
    return this.parameters;
  }

  public boolean hasClassRef() {
    return this.classRef != null;
  }

  public boolean hasParameters() {
    return this.parameters != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(classRef, parameters, super.hashCode());
  }

  public A removeFromParameters(String key) {
    if (this.parameters == null) {
      return (A) this;
    }
    if (key != null && this.parameters != null) {
      this.parameters.remove(key);
    }
    return (A) this;
  }

  public A removeFromParameters(Map<String, Object> map) {
    if (this.parameters == null) {
      return (A) this;
    }
    if (map != null) {
      for (Object key : map.keySet()) {
        if (this.parameters != null) {
          this.parameters.remove(key);
        }
      }
    }
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (classRef != null) {
      sb.append("classRef:");
      sb.append(classRef + ",");
    }
    if (parameters != null && !parameters.isEmpty()) {
      sb.append("parameters:");
      sb.append(parameters);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withClassRef(ClassRef classRef) {
    this._visitables.remove("classRef");
    if (classRef != null) {
      this.classRef = new ClassRefBuilder(classRef);
      this._visitables.get("classRef").add(this.classRef);
    } else {
      this.classRef = null;
      this._visitables.get("classRef").remove(this.classRef);
    }
    return (A) this;
  }

  public ClassRefNested<A> withNewClassRef() {
    return new ClassRefNested(null);
  }

  public ClassRefNested<A> withNewClassRefLike(ClassRef item) {
    return new ClassRefNested(item);
  }

  public <K, V> A withParameters(Map<String, Object> parameters) {
    if (parameters == null) {
      this.parameters = null;
    } else {
      this.parameters = new LinkedHashMap(parameters);
    }
    return (A) this;
  }

  public class ClassRefNested<N> extends ClassRefFluent<ClassRefNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) AnnotationRefFluent.this.withClassRef(builder.build());
    }

    public N endClassRef() {
      return and();
    }

  }
}
