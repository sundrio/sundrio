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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Field extends Variable<TypeRef> implements Erasable<Field> {

  public Field(Modifiers modifiers, Map<AttributeKey, Object> attributes, List<String> comments,
      List<AnnotationRef> annotations, TypeRef typeRef, String name, Optional<Expression> initialValue,
      boolean enumConstant, boolean synthetic) {
    super(modifiers, attributes, comments, annotations, validateTypeRef(typeRef), name, initialValue, enumConstant, synthetic);
  }

  public Field(List<AnnotationRef> annotations, TypeRef typeRef, String name, List<String> comments, boolean enumConstant,
      boolean synthetic, Modifiers modifiers, Map<AttributeKey, Object> attributes) {
    super(modifiers, attributes, comments, annotations, validateTypeRef(typeRef), name, Optional.empty(), enumConstant,
        synthetic);
  }

  public Field(List<AnnotationRef> annotations, TypeRef typeRef, String name, List<String> comments, Modifiers modifiers,
      Map<AttributeKey, Object> attributes) {
    super(modifiers, attributes, comments, annotations, validateTypeRef(typeRef), name, Optional.empty(), false, false);
  }

  public static Field newField(String name) {
    return newField(ClassRef.OBJECT, name);
  }

  public static Field newField(Class<?> type, String name) {
    return newField(ClassRef.forClass(type), name);
  }

  public static Field newField(TypeRef typeRef, String name) {
    return new Field(java.util.Collections.emptyList(), typeRef, name, java.util.Collections.emptyList(),
        Modifiers.create(), new HashMap<>());
  }

  private static TypeRef validateTypeRef(TypeRef typeRef) {
    // Validation for VarRef will be added when LocalVariable.VarRef is implemented
    return typeRef;
  }

  protected Field withoutModifiers() {
    return new Field(getAnnotations(), getTypeRef(), getName(), getComments(), isEnumConstant(), isSynthetic(),
        Modifiers.create(), getAttributes());
  }

  @Override
  public Field withErasure() {
    TypeRef erasedType = getTypeRef() instanceof TypeParamRef ? ((TypeParamRef) getTypeRef()).withErasure() : getTypeRef();
    return new Field(getAnnotations(), erasedType, getName(), getComments(), isEnumConstant(), isSynthetic(),
        Modifiers.create(), getAttributes());
  }

  @Override
  public String getErasure() {
    return withErasure().render();
  }

  public Field withoutInitialValue() {
    return new Field(getModifiers(), getAttributes(), getComments(), getAnnotations(), getTypeRef(), getName(),
        Optional.empty(), isEnumConstant(), isSynthetic());
  }

  public Field withInitialValue(Object initialValue) {
    return new Field(getModifiers(), getAttributes(), getComments(), getAnnotations(), getTypeRef(), getName(),
        Optional.of(ValueRef.from(initialValue)), isEnumConstant(), isSynthetic());
  }

  public Field withInitialValue(Expression initialValue) {
    return new Field(getModifiers(), getAttributes(), getComments(), getAnnotations(), getTypeRef(), getName(),
        Optional.of(initialValue), isEnumConstant(), isSynthetic());
  }

  public Field withInitialValue(Optional<Expression> initialValue) {
    return new Field(getModifiers(), getAttributes(), getComments(), getAnnotations(), getTypeRef(), getName(),
        initialValue, isEnumConstant(), isSynthetic());
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();

    if (isPublic()) {
      sb.append(PUBLIC).append(SPACE);
    } else if (isProtected()) {
      sb.append(PROTECTED).append(SPACE);
    } else if (isPrivate()) {
      sb.append(PRIVATE).append(SPACE);
    }

    if (isStatic()) {
      sb.append(STATIC).append(SPACE);
    }

    if (isFinal()) {
      sb.append(FINAL).append(SPACE);
    }

    sb.append(getTypeRef().render()).append(SPACE);
    sb.append(getName());

    return sb.toString();
  }
}
