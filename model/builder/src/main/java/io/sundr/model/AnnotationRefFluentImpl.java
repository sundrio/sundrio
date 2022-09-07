package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.LinkedHashMap;
import java.util.Map;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class AnnotationRefFluentImpl<A extends AnnotationRefFluent<A>> extends AttributeSupportFluentImpl<A>
    implements AnnotationRefFluent<A> {
  public AnnotationRefFluentImpl() {
  }

  public AnnotationRefFluentImpl(AnnotationRef instance) {
    this.withClassRef(instance.getClassRef());
    this.withParameters(instance.getParameters());
    this.withAttributes(instance.getAttributes());
  }

  private ClassRefBuilder classRef;
  private Map<String, Object> parameters = new LinkedHashMap<String, Object>();

  /**
   * This method has been deprecated, please use method buildClassRef instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public ClassRef getClassRef() {
    return this.classRef != null ? this.classRef.build() : null;
  }

  public ClassRef buildClassRef() {
    return this.classRef != null ? this.classRef.build() : null;
  }

  public A withClassRef(ClassRef classRef) {
    _visitables.get("classRef").remove(this.classRef);
    if (classRef != null) {
      this.classRef = new ClassRefBuilder(classRef);
      _visitables.get("classRef").add(this.classRef);
    } else {
      this.classRef = null;
      _visitables.get("classRef").remove(this.classRef);
    }
    return (A) this;
  }

  public Boolean hasClassRef() {
    return this.classRef != null;
  }

  public AnnotationRefFluent.ClassRefNested<A> withNewClassRef() {
    return new AnnotationRefFluentImpl.ClassRefNestedImpl();
  }

  public AnnotationRefFluent.ClassRefNested<A> withNewClassRefLike(ClassRef item) {
    return new AnnotationRefFluentImpl.ClassRefNestedImpl(item);
  }

  public AnnotationRefFluent.ClassRefNested<A> editClassRef() {
    return withNewClassRefLike(getClassRef());
  }

  public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRef() {
    return withNewClassRefLike(getClassRef() != null ? getClassRef() : new ClassRefBuilder().build());
  }

  public AnnotationRefFluent.ClassRefNested<A> editOrNewClassRefLike(ClassRef item) {
    return withNewClassRefLike(getClassRef() != null ? getClassRef() : item);
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

  public A addToParameters(Map<String, Object> map) {
    if (this.parameters == null && map != null) {
      this.parameters = new LinkedHashMap();
    }
    if (map != null) {
      this.parameters.putAll(map);
    }
    return (A) this;
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

  public Map<String, Object> getParameters() {
    return this.parameters;
  }

  public <K, V> A withParameters(Map<String, Object> parameters) {
    if (parameters == null) {
      this.parameters = null;
    } else {
      this.parameters = new LinkedHashMap(parameters);
    }
    return (A) this;
  }

  public Boolean hasParameters() {
    return this.parameters != null;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    AnnotationRefFluentImpl that = (AnnotationRefFluentImpl) o;
    if (classRef != null ? !classRef.equals(that.classRef) : that.classRef != null)
      return false;
    if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(classRef, parameters, super.hashCode());
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

  class ClassRefNestedImpl<N> extends ClassRefFluentImpl<AnnotationRefFluent.ClassRefNested<N>>
      implements AnnotationRefFluent.ClassRefNested<N>, Nested<N> {
    ClassRefNestedImpl(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefNestedImpl() {
      this.builder = new ClassRefBuilder(this);
    }

    ClassRefBuilder builder;

    public N and() {
      return (N) AnnotationRefFluentImpl.this.withClassRef(builder.build());
    }

    public N endClassRef() {
      return and();
    }

  }

}
