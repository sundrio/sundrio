/*
 * Copyright 2016 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.model;

import java.lang.reflect.Modifier;
import java.util.Map;

public class ModifierSupport extends AttributeSupport {

  public static final String ABSTRACT = "abstract";
  public static final String PUBLIC = "public";
  public static final String PROTECTED = "protected";
  public static final String PRIVATE = "private";
  public static final String STATIC = "static";
  public static final String FINAL = "final";
  public static final String SYNCHRONIZED = "synchronized";

  protected final int modifiers;

  public ModifierSupport(int modifiers, Map<AttributeKey, Object> attributes) {
    super(attributes);
    this.modifiers = modifiers;
  }

  public boolean isPrivate() {
    return Modifier.isPrivate(modifiers);
  }

  public boolean isProtected() {
    return Modifier.isProtected(modifiers);
  }

  public boolean isPublic() {
    return Modifier.isPublic(modifiers);
  }

  public boolean isFinal() {
    return Modifier.isFinal(modifiers);
  }

  public boolean isStatic() {
    return Modifier.isStatic(modifiers);
  }

  public boolean isAbstract() {
    return Modifier.isAbstract(modifiers);
  }

  public boolean isSynchronized() {
    return Modifier.isSynchronized(modifiers);
  }

  public boolean isTransient() {
    return Modifier.isTransient(modifiers);
  }

  public int getModifiers() {
    return modifiers;
  }

  public void renderModifiers(StringBuilder sb) {
    if (isPublic()) {
      sb.append(PUBLIC).append(SPACE);
    } else if (isProtected()) {
      sb.append(PROTECTED).append(SPACE);
    } else if (isPrivate()) {
      sb.append(PRIVATE).append(SPACE);
    }
    if (isSynchronized()) {
      sb.append(SYNCHRONIZED).append(SPACE);
    }
    if (isStatic()) {
      sb.append(STATIC).append(SPACE);
    }
    if (isAbstract()) {
      sb.append(ABSTRACT).append(SPACE);
    }
    if (isFinal()) {
      sb.append(FINAL).append(SPACE);
    }
  }

  public String renderModifiers() {
    StringBuilder sb = new StringBuilder();
    renderModifiers(sb);
    return sb.toString();
  }

}
