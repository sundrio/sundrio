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

public class LocalVariable extends Variable<TypeRef> implements Erasable<LocalVariable> {

  private final boolean isFinal;

  public LocalVariable(List<String> comments, List<AnnotationRef> annotations, TypeRef typeRef, String name,
      Optional<Expression> initialValue, boolean isFinal, Map<AttributeKey, Object> attributes) {
    super(Modifiers.create(), attributes, comments, annotations, typeRef, name, initialValue, false, false);
    this.isFinal = isFinal;
  }

  public static LocalVariable newLocalVariable(String name) {
    return newLocalVariable(ClassRef.OBJECT, name);
  }

  public static LocalVariable newLocalVariable(Class<?> type, String name) {
    return newLocalVariable(ClassRef.forClass(type), name);
  }

  public static LocalVariable newLocalVariable(TypeRef typeRef, String name) {
    return new LocalVariable(Collections.emptyList(), Collections.emptyList(), typeRef, name,
        Optional.empty(), false, new HashMap<>());
  }

  public static LocalVariable newFinalLocalVariable(TypeRef typeRef, String name) {
    return new LocalVariable(Collections.emptyList(), Collections.emptyList(), typeRef, name,
        Optional.empty(), true, new HashMap<>());
  }

  public static LocalVariable withVarInference(String name, Expression initializer) {
    return new LocalVariable(Collections.emptyList(), Collections.emptyList(), new VarRef(), name,
        Optional.of(initializer), false, new HashMap<>());
  }

  public static LocalVariable withFinalVarInference(String name, Expression initializer) {
    return new LocalVariable(Collections.emptyList(), Collections.emptyList(), new VarRef(), name,
        Optional.of(initializer), true, new HashMap<>());
  }

  @Override
  public boolean isFinal() {
    return isFinal;
  }

  public LocalVariable withFinal(boolean isFinal) {
    return new LocalVariable(getComments(), getAnnotations(), getTypeRef(), getName(), getInitialValue(), isFinal,
        getAttributes());
  }

  public LocalVariable withInitialValue(Expression initialValue) {
    return new LocalVariable(getComments(), getAnnotations(), getTypeRef(), getName(), Optional.of(initialValue), isFinal,
        getAttributes());
  }

  public LocalVariable withInitialValue(Optional<Expression> initialValue) {
    return new LocalVariable(getComments(), getAnnotations(), getTypeRef(), getName(), initialValue, isFinal, getAttributes());
  }

  public boolean isVarInferred() {
    return getTypeRef() instanceof VarRef;
  }

  public LocalVariable withInferredType(TypeRef inferredType) {
    if (!(getTypeRef() instanceof VarRef)) {
      throw new IllegalStateException("Can only resolve type for var-inferred variables");
    }
    VarRef varRef = (VarRef) getTypeRef();
    return new LocalVariable(getComments(), getAnnotations(), varRef.withInferredType(inferredType),
        getName(), getInitialValue(), isFinal, getAttributes());
  }

  @Override
  public LocalVariable withErasure() {
    TypeRef erasedType = getTypeRef() instanceof TypeParamRef ? ((TypeParamRef) getTypeRef()).withErasure() : getTypeRef();
    return new LocalVariable(getComments(), getAnnotations(), erasedType, getName(), getInitialValue(), isFinal,
        getAttributes());
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

    if (getInitialValue().isPresent()) {
      sb.append(" = ").append(getInitialValue().get().renderExpression());
    }

    return sb.toString();
  }

  @Override
  public String toString() {
    return render();
  }

  // Private inner class for var type inference
  public static class VarRef extends TypeRef {

    private final TypeRef inferredType;
    private final int dimensions;

    private VarRef() {
      this(null, 0, Collections.emptyMap());
    }

    private VarRef(TypeRef inferredType, int dimensions, Map<AttributeKey, Object> attributes) {
      super(attributes);
      this.inferredType = inferredType;
      this.dimensions = dimensions;
    }

    public VarRef withInferredType(TypeRef type) {
      return new VarRef(type, dimensions, getAttributes());
    }

    @Override
    public String getName() {
      return inferredType != null ? inferredType.getName() : "var";
    }

    @Override
    public int getDimensions() {
      return dimensions;
    }

    @Override
    public VarRef withDimensions(int dimensions) {
      return new VarRef(inferredType, dimensions, getAttributes());
    }

    public TypeRef getInferredType() {
      return inferredType;
    }

    public boolean isResolved() {
      return inferredType != null;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      VarRef that = (VarRef) o;

      if (dimensions != that.dimensions)
        return false;
      return inferredType != null ? inferredType.equals(that.inferredType) : that.inferredType == null;
    }

    @Override
    public int hashCode() {
      int result = inferredType != null ? inferredType.hashCode() : 0;
      result = 31 * result + dimensions;
      return result;
    }

    @Override
    public String render() {
      if (inferredType != null) {
        StringBuilder sb = new StringBuilder(inferredType.render());
        for (int i = 0; i < dimensions; i++) {
          sb.append("[]");
        }
        return sb.toString();
      }
      return "var";
    }

    @Override
    public String toString() {
      return render();
    }
  }
}
