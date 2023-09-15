/*
 *      Copyright 2016 The original authors.
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationRef extends AttributeSupport implements Renderable {

  private final ClassRef classRef;
  private final Map<String, Object> parameters;

  public AnnotationRef(ClassRef classRef, Map<String, Object> parameters, Map<AttributeKey, Object> attributes) {
    super(attributes);
    this.classRef = classRef;
    this.parameters = parameters;
  }

  public ClassRef getClassRef() {
    return classRef;
  }

  public Map<String, Object> getParameters() {
    return parameters;
  }

  public Set<ClassRef> getReferences() {
    Set<ClassRef> result = new HashSet<>();
    result.add(classRef);

    for (Object o : parameters.values()) {
      if (o instanceof ClassRef) {
        result.add((ClassRef) o);
      } else if (o instanceof AnnotationRef) {
        result.addAll(((AnnotationRef) o).getReferences());
      } else if (o instanceof Collection) {
        for (Object i : (Collection) o) {
          if (i instanceof ClassRef) {
            result.addAll(((ClassRef) i).getReferences());
          } else if (i instanceof AnnotationRef) {
            result.addAll(((AnnotationRef) i).getReferences());
          }
        }
      }
    }
    return result;
  }

  private static String toString(Object value) {
    if (value == null) {
      return "null";
    } else if (value.getClass().isArray()) {
      int length = Array.getLength(value);
      Object[] array = new Object[length];
      for (int i = 0; i < length; i++) {
        array[i] = Array.get(value, i);
      }
      return OB + Arrays.stream(array).map(AnnotationRef::toString).collect(Collectors.joining(",")) + CB;
    } else if (value instanceof Collection) {
      return OB + ((Collection) value).stream().map(AnnotationRef::toString).collect(Collectors.joining(",")) + CB;
    } else if (value instanceof PrimitiveRef || value instanceof Number || value instanceof Boolean
        || value.getClass().isPrimitive()) {
      return String.valueOf(value);
    } else if (value instanceof ClassRef) {
      return ((ClassRef) value).getFullyQualifiedName() + ".class";
    } else if (value instanceof Class) {
      return ((Class) value).getSimpleName() + ".class";
    } else if (value instanceof AnnotationRef) {
      return value.toString();
    } else {
      return DQ + value + DQ;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    AnnotationRef that = (AnnotationRef) o;

    if (!classRef.equals(that.classRef))
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    return classRef.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(AT).append(classRef.toString());

    if (parameters != null && parameters.size() > 0) {
      sb.append(OP);
      boolean first = true;

      if (parameters.size() == 1 && parameters.containsKey("value")) {
        Object value = parameters.get("value");
        sb.append(value);
      } else {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
          Object value = entry.getValue();
          if (first) {
            first = false;
          } else {
            sb.append(SPACE).append(COMA);
          }
          sb.append(entry.getKey()).append(SPACE).append(EQ).append(SPACE).append(toString(value));
        }
      }
      sb.append(CP);
    }
    return sb.toString();
  }
}
