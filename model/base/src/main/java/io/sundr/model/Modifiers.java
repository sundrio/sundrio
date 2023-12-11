/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.model;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

public class Modifiers {

  private final boolean _private;
  private final boolean _protected;
  private final boolean _public;
  private final boolean _abstract;
  private final boolean _final;
  private final boolean _native;
  private final boolean _static;
  private final boolean _synchronized;
  private final boolean _transient;

  public Modifiers() {
    this(false, false, false, false, false, false, false, false, false);
  }

  public Modifiers(boolean _private, boolean _protected, boolean _public, boolean _abstract,
      boolean _final, boolean _native, boolean _static, boolean _synchronized, boolean _transient) {
    this._private = _private;
    this._protected = _protected;
    this._public = _public;
    this._abstract = _abstract;
    this._final = _final;
    this._native = _native;
    this._static = _static;
    this._synchronized = _synchronized;
    this._transient = _transient;
  }

  public int toInt() {
    int result = 0;

    if (_private) {
      result = result | java.lang.reflect.Modifier.PRIVATE;
    }
    if (_protected) {
      result = result | java.lang.reflect.Modifier.PROTECTED;
    }
    if (_public) {
      result = result | java.lang.reflect.Modifier.PUBLIC;
    }
    if (_abstract) {
      result = result | java.lang.reflect.Modifier.ABSTRACT;
    }
    if (_final) {
      result = result | java.lang.reflect.Modifier.FINAL;
    }
    if (_native) {
      result = result | java.lang.reflect.Modifier.NATIVE;
    }

    if (_static) {
      result = result | java.lang.reflect.Modifier.STATIC;
    }
    if (_transient) {
      result = result | java.lang.reflect.Modifier.TRANSIENT;
    }

    return result;
  }

  public static Modifiers create() {
    return new Modifiers(false, false, false, false, false, false, false, false, false);
  }

  public static Modifiers from(int modifiers) {
    return new Modifiers(Modifier.isPrivate(modifiers), Modifier.isProtected(modifiers),
        Modifier.isPublic(modifiers), Modifier.isAbstract(modifiers), Modifier.isFinal(modifiers), Modifier.isNative(modifiers),
        Modifier.isStatic(modifiers), Modifier.isSynchronized(modifiers), Modifier.isTransient(modifiers));
  }

  public static Modifiers from(Collection<javax.lang.model.element.Modifier> modifiers) {
    if (modifiers == null) {
      return create();
    }

    return from(modifiers.stream().distinct().map(m -> {
      switch (m) {
        case ABSTRACT:
          return java.lang.reflect.Modifier.ABSTRACT;
        case FINAL:
          return java.lang.reflect.Modifier.FINAL;
        case NATIVE:
          return java.lang.reflect.Modifier.NATIVE;
        case PRIVATE:
          return java.lang.reflect.Modifier.PRIVATE;
        case PROTECTED:
          return java.lang.reflect.Modifier.PROTECTED;
        case PUBLIC:
          return java.lang.reflect.Modifier.PUBLIC;
        case STATIC:
          return java.lang.reflect.Modifier.STATIC;
        case SYNCHRONIZED:
          return java.lang.reflect.Modifier.SYNCHRONIZED;
        case TRANSIENT:
          return java.lang.reflect.Modifier.TRANSIENT;
        default:
          return 0;
      }
    }).reduce(0, (result, m) -> result | m));
  }

  public static Modifiers from(javax.lang.model.element.Modifier... modifiers) {
    return from(Arrays.asList(modifiers));
  }

  public boolean isPrivate() {
    return _private;
  }

  public boolean isProtected() {
    return _protected;
  }

  public boolean isPublic() {
    return _public;
  }

  public boolean isAbstract() {
    return _abstract;
  }

  public boolean isFinal() {
    return _final;
  }

  public boolean isNative() {
    return _native;
  }

  public boolean isStatic() {
    return _static;
  }

  public boolean isSynchronized() {
    return _synchronized;
  }

  public boolean isTransient() {
    return _transient;
  }

  @Override
  public int hashCode() {
    return toInt();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Modifiers other = (Modifiers) obj;
    return toInt() == other.toInt();
  }
}
