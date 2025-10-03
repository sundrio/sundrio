package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ModifiersFluent<A extends io.sundr.model.ModifiersFluent<A>> extends BaseFluent<A> {

  private boolean _abstract;
  private boolean _final;
  private boolean _native;
  private boolean _private;
  private boolean _protected;
  private boolean _public;
  private boolean _static;
  private boolean _synchronized;
  private boolean _transient;

  public ModifiersFluent() {
  }

  public ModifiersFluent(Modifiers instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(Modifiers instance) {
    instance = instance != null ? instance : new Modifiers();
    if (instance != null) {
      this.withPrivate(instance.isPrivate());
      this.withProtected(instance.isProtected());
      this.withPublic(instance.isPublic());
      this.withAbstract(instance.isAbstract());
      this.withFinal(instance.isFinal());
      this.withNative(instance.isNative());
      this.withStatic(instance.isStatic());
      this.withSynchronized(instance.isSynchronized());
      this.withTransient(instance.isTransient());
    }
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
    ModifiersFluent that = (ModifiersFluent) o;
    if (_private != that._private) {
      return false;
    }
    if (_protected != that._protected) {
      return false;
    }
    if (_public != that._public) {
      return false;
    }
    if (_abstract != that._abstract) {
      return false;
    }
    if (_final != that._final) {
      return false;
    }
    if (_native != that._native) {
      return false;
    }
    if (_static != that._static) {
      return false;
    }
    if (_synchronized != that._synchronized) {
      return false;
    }
    if (_transient != that._transient) {
      return false;
    }
    return true;
  }

  public boolean hasAbstract() {
    return true;
  }

  public boolean hasFinal() {
    return true;
  }

  public boolean hasNative() {
    return true;
  }

  public boolean hasPrivate() {
    return true;
  }

  public boolean hasProtected() {
    return true;
  }

  public boolean hasPublic() {
    return true;
  }

  public boolean hasStatic() {
    return true;
  }

  public boolean hasSynchronized() {
    return true;
  }

  public boolean hasTransient() {
    return true;
  }

  public int hashCode() {
    return Objects.hash(_private, _protected, _public, _abstract, _final, _native, _static, _synchronized, _transient);
  }

  public boolean isAbstract() {
    return this._abstract;
  }

  public boolean isFinal() {
    return this._final;
  }

  public boolean isNative() {
    return this._native;
  }

  public boolean isPrivate() {
    return this._private;
  }

  public boolean isProtected() {
    return this._protected;
  }

  public boolean isPublic() {
    return this._public;
  }

  public boolean isStatic() {
    return this._static;
  }

  public boolean isSynchronized() {
    return this._synchronized;
  }

  public boolean isTransient() {
    return this._transient;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("_private:");
    sb.append(_private);
    sb.append(",");
    sb.append("_protected:");
    sb.append(_protected);
    sb.append(",");
    sb.append("_public:");
    sb.append(_public);
    sb.append(",");
    sb.append("_abstract:");
    sb.append(_abstract);
    sb.append(",");
    sb.append("_final:");
    sb.append(_final);
    sb.append(",");
    sb.append("_native:");
    sb.append(_native);
    sb.append(",");
    sb.append("_static:");
    sb.append(_static);
    sb.append(",");
    sb.append("_synchronized:");
    sb.append(_synchronized);
    sb.append(",");
    sb.append("_transient:");
    sb.append(_transient);
    sb.append("}");
    return sb.toString();
  }

  public A withAbstract() {
    return withAbstract(true);
  }

  public A withAbstract(boolean _abstract) {
    this._abstract = _abstract;
    return (A) this;
  }

  public A withFinal() {
    return withFinal(true);
  }

  public A withFinal(boolean _final) {
    this._final = _final;
    return (A) this;
  }

  public A withNative() {
    return withNative(true);
  }

  public A withNative(boolean _native) {
    this._native = _native;
    return (A) this;
  }

  public A withPrivate() {
    return withPrivate(true);
  }

  public A withPrivate(boolean _private) {
    this._private = _private;
    return (A) this;
  }

  public A withProtected() {
    return withProtected(true);
  }

  public A withProtected(boolean _protected) {
    this._protected = _protected;
    return (A) this;
  }

  public A withPublic() {
    return withPublic(true);
  }

  public A withPublic(boolean _public) {
    this._public = _public;
    return (A) this;
  }

  public A withStatic() {
    return withStatic(true);
  }

  public A withStatic(boolean _static) {
    this._static = _static;
    return (A) this;
  }

  public A withSynchronized() {
    return withSynchronized(true);
  }

  public A withSynchronized(boolean _synchronized) {
    this._synchronized = _synchronized;
    return (A) this;
  }

  public A withTransient() {
    return withTransient(true);
  }

  public A withTransient(boolean _transient) {
    this._transient = _transient;
    return (A) this;
  }

}
