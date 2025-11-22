package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;
import java.util.Optional;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class FieldRefFluent<A extends io.sundr.model.FieldRefFluent<A>> extends WithScopeFluent<A> {

  private FieldBuilder field;

  public FieldRefFluent() {
  }

  public FieldRefFluent(FieldRef instance) {
    this.copyInstance(instance);
  }

  public Field buildField() {
    return this.field != null ? this.field.build() : null;
  }

  protected void copyInstance(FieldRef instance) {
    if (instance != null) {
      this.withField(instance.getField());
      this.withScope(instance.getScope());
    }
  }

  public FieldNested<A> editField() {
    return this.withNewFieldLike(Optional.ofNullable(this.buildField()).orElse(null));
  }

  public FieldNested<A> editOrNewField() {
    return this.withNewFieldLike(Optional.ofNullable(this.buildField()).orElse(new FieldBuilder().build()));
  }

  public FieldNested<A> editOrNewFieldLike(Field item) {
    return this.withNewFieldLike(Optional.ofNullable(this.buildField()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    FieldRefFluent that = (FieldRefFluent) o;
    if (!(Objects.equals(field, that.field))) {
      return false;
    }
    return true;
  }

  public boolean hasField() {
    return this.field != null;
  }

  public int hashCode() {
    return Objects.hash(field);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(field == null)) {
      sb.append("field:");
      sb.append(field);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withField(Field field) {
    this._visitables.remove("field");
    if (field != null) {
      this.field = new FieldBuilder(field);
      this._visitables.get("field").add(this.field);
    } else {
      this.field = null;
      this._visitables.get("field").remove(this.field);
    }
    return (A) this;
  }

  public FieldNested<A> withNewField() {
    return new FieldNested(null);
  }

  public FieldNested<A> withNewFieldLike(Field item) {
    return new FieldNested(item);
  }

  public class FieldNested<N> extends FieldFluent<FieldNested<N>> implements Nested<N> {

    FieldBuilder builder;

    FieldNested(Field item) {
      this.builder = new FieldBuilder(this, item);
    }

    public N and() {
      return (N) FieldRefFluent.this.withField(builder.build());
    }

    public N endField() {
      return and();
    }

  }
}
