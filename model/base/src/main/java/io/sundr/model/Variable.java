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

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Variable<T extends TypeRef> extends ModifierSupport
    implements WithName, Expression, Commentable, Annotatable {

  private final List<String> comments;
  private final List<AnnotationRef> annotations;
  private final T typeRef;
  private final String name;
  private final Optional<Expression> initialValue;
  private final boolean enumConstant;
  private final boolean synthetic;

  protected Variable(Modifiers modifiers, Map<AttributeKey, Object> attributes, List<String> comments,
      List<AnnotationRef> annotations, T typeRef, String name, Optional<Expression> initialValue,
      boolean enumConstant, boolean synthetic) {
    super(modifiers, attributes);
    this.comments = comments != null ? comments : Collections.emptyList();
    this.annotations = annotations != null ? annotations : Collections.emptyList();
    this.typeRef = typeRef;
    this.name = name;
    this.initialValue = initialValue != null ? initialValue : Optional.empty();
    this.enumConstant = enumConstant;
    this.synthetic = synthetic;
  }

  public List<String> getComments() {
    return comments;
  }

  public List<AnnotationRef> getAnnotations() {
    return annotations;
  }

  public T getTypeRef() {
    return typeRef;
  }

  public String getName() {
    return name;
  }

  public Optional<Expression> getInitialValue() {
    return initialValue;
  }

  public boolean isEnumConstant() {
    return enumConstant;
  }

  public boolean isSynthetic() {
    return synthetic;
  }

  public String getNameCapitalized() {
    return Stream.of(name.split("[^a-zA-Z0-9]"))
        .filter(s -> s != null && s.length() > 0)
        .map(v -> Character.toUpperCase(v.charAt(0)) + v.substring(1))
        .collect(Collectors.joining());
  }

  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new LinkedHashSet<ClassRef>();

    for (AnnotationRef annotationRef : annotations) {
      refs.addAll(annotationRef.getReferences());
    }

    if (typeRef instanceof ClassRef) {
      ClassRef classRef = (ClassRef) typeRef;
      refs.addAll(classRef.getReferences());
    }

    if (getAttributes().containsKey(ALSO_IMPORT)) {
      Object obj = getAttributes().get(ALSO_IMPORT);
      if (obj instanceof ClassRef) {
        refs.add((ClassRef) obj);
      } else if (obj instanceof java.util.Collection) {
        refs.addAll((java.util.Collection<? extends ClassRef>) obj);
      }
    }
    return refs;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Variable<?> other = (Variable<?>) obj;
    if (!getModifiers().equals(other.getModifiers()))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (typeRef == null) {
      if (other.typeRef != null)
        return false;
    } else if (!typeRef.equals(other.typeRef))
      return false;
    if (enumConstant != other.isEnumConstant())
      return false;
    if (synthetic != other.isSynthetic())
      return false;
    return true;
  }

  @Override
  public String renderExpression() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getModifiers().hashCode();
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((typeRef == null) ? 0 : typeRef.hashCode());
    return result;
  }

  public Field asField() {
    return Field.newField(typeRef, name);
  }

  public Argument asArgument() {
    return Argument.newArgument(typeRef, name);
  }

  public LocalVariable asLocalVariable() {
    return LocalVariable.newLocalVariable(typeRef, name);
  }

  @Override
  public String toString() {
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

    sb.append(typeRef).append(SPACE);
    sb.append(name);

    return sb.toString();
  }
}
