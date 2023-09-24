/*
 *      Copyright 2019 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Property extends ModifierSupport implements Renderable, Commentable, Annotatable {

  private final List<AnnotationRef> annotations;
  private final TypeRef typeRef;
  private final String name;
  private final List<String> comments;
  private final boolean enumConstant;
  private final boolean synthetic;

  public Property(List<AnnotationRef> annotations, TypeRef typeRef, String name, List<String> comments, boolean enumConstant,
      boolean synthetic, Modifiers modifiers, Map<AttributeKey, Object> attributes) {
    super(modifiers, attributes);
    this.annotations = annotations;
    this.typeRef = typeRef;
    this.name = name;
    this.comments = comments;
    this.enumConstant = enumConstant;
    this.synthetic = synthetic;
  }

  @Deprecated
  public Property(List<AnnotationRef> annotations, TypeRef typeRef, String name, List<String> comments, Modifiers modifiers,
      Map<AttributeKey, Object> attributes) {
    super(modifiers, attributes);
    this.annotations = annotations;
    this.typeRef = typeRef;
    this.name = name;
    this.comments = comments;
    this.enumConstant = false;
    this.synthetic = false;
  }

  public static Property newProperty(TypeRef typeRef, String name) {
    return new Property(Collections.emptyList(), typeRef, name, Collections.emptyList(), false, false, Modifiers.create(),
        new HashMap<>());
  }

  public List<AnnotationRef> getAnnotations() {
    return annotations;
  }

  public TypeRef getTypeRef() {
    return typeRef;
  }

  public String getName() {
    return name;
  }

  public List<String> getComments() {
    return this.comments;
  }

  public boolean isEnumConstant() {
    return this.enumConstant;
  }

  public boolean isSynthetic() {
    return this.synthetic;
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
    for (AnnotationRef a : getAnnotations()) {
      refs.addAll(a.getClassRef().getReferences());
    }

    if (getAttributes().containsKey(ALSO_IMPORT)) {
      Object obj = getAttributes().get(ALSO_IMPORT);
      if (obj instanceof ClassRef) {
        refs.add((ClassRef) obj);
      } else if (obj instanceof Collection) {
        refs.addAll((Collection<? extends ClassRef>) obj);
      }
    }
    return refs;
  }

  /**
   * This is only used for rendering method arguments, where we usually string modifiers.
   * 
   * @return the property without any modifiers
   */
  protected Property withoutModiers() {
    return new Property(annotations, typeRef, name, comments, enumConstant, synthetic, Modifiers.create(), getAttributes());
  }

  /**
   * Get the property after the type erasure is applied.
   * This in essense means that the type parameters are replaced by Object.
   * 
   * @return the 'erased` property.
   */
  public Property withErasure() {
    return new Property(annotations, typeRef instanceof TypeParamRef ? ((TypeParamRef) typeRef).withErasure() : typeRef, name,
        comments, enumConstant, synthetic, Modifiers.create(), getAttributes());
  }

  protected String getDefaultValue() {
    Object value = getAttribute(INIT);
    if (getTypeRef() instanceof ClassRef && ((ClassRef) getTypeRef()).getFullyQualifiedName().equals(JAVA_LANG_STRING)
        && getTypeRef().getDimensions() == 0 && !String.valueOf(value).startsWith("\"")) {
      return "\"" + value + "\"";
    } else {
      return String.valueOf(value);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Property other = (Property) obj;
    if (!modifiers.equals(other.modifiers))
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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + modifiers.hashCode();
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((typeRef == null) ? 0 : typeRef.hashCode());
    return result;
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

    sb.append(typeRef.render()).append(SPACE);
    sb.append(name);

    return sb.toString();
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
