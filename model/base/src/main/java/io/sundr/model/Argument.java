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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Argument extends Variable<TypeRef> implements Erasable<Argument> {

  private final boolean isFinal;

  public Argument(List<String> comments, List<AnnotationRef> annotations, TypeRef typeRef, String name,
      boolean isFinal, Map<AttributeKey, Object> attributes) {
    super(Modifiers.create(), attributes, comments, annotations, typeRef, name, Optional.empty(), false, false);
    this.isFinal = isFinal;
  }

  public static Argument newArgument(String name) {
    return newArgument(ClassRef.OBJECT, name);
  }

  public static Argument newArgument(Class<?> type, String name) {
    return newArgument(ClassRef.forClass(type), name);
  }

  public static Argument newArgument(TypeRef typeRef, String name) {
    return new Argument(Collections.emptyList(), Collections.emptyList(), typeRef, name, false, new HashMap<>());
  }

  public static Argument newFinalArgument(TypeRef typeRef, String name) {
    return new Argument(Collections.emptyList(), Collections.emptyList(), typeRef, name, true, new HashMap<>());
  }

  @Override
  public boolean isFinal() {
    return isFinal;
  }

  public Argument withFinal(boolean isFinal) {
    return new Argument(getComments(), getAnnotations(), getTypeRef(), getName(), isFinal, getAttributes());
  }

  @Override
  public Argument withErasure() {
    TypeRef erasedType = getTypeRef() instanceof TypeParamRef ? ((TypeParamRef) getTypeRef()).withErasure() : getTypeRef();
    return new Argument(getComments(), getAnnotations(), erasedType, getName(), isFinal, getAttributes());
  }

  @Override
  public String getErasure() {
    return withErasure().render();
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();

    if (isFinal) {
      sb.append(FINAL).append(SPACE);
    }

    sb.append(getTypeRef().render()).append(SPACE);
    sb.append(getName());

    return sb.toString();
  }

  @Override
  public String toString() {
    return render();
  }
}
