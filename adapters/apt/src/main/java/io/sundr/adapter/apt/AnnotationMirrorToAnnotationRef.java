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

package io.sundr.adapter.apt;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.SundrException;
import io.sundr.adapter.apt.visitors.TypeRefTypeVisitor;
import io.sundr.model.AnnotationRef;
import io.sundr.model.AnnotationRefBuilder;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeRef;

public class AnnotationMirrorToAnnotationRef implements Function<AnnotationMirror, AnnotationRef> {

  private static final String EMPTY_PARENTHESIS = "()";
  private static final String EMPTY = "";

  private final AptContext context;
  private final Function<TypeMirror, TypeRef> referenceAdapterFunction;

  public AnnotationMirrorToAnnotationRef(AptContext context, Function<TypeMirror, TypeRef> referenceAdapterFunction) {
    this.context = context;
    this.referenceAdapterFunction = referenceAdapterFunction;
  }

  @Override
  public AnnotationRef apply(AnnotationMirror item) {
    TypeRef annotationType = item.getAnnotationType().accept(new TypeRefTypeVisitor(context), 0);
    Map<String, Object> parameters = new HashMap<String, Object>();
    if (annotationType instanceof ClassRef) {
      for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : item.getElementValues()
          .entrySet()) {
        String key = entry.getKey().toString().replace(EMPTY_PARENTHESIS, EMPTY);
        Object value = mapAnnotationValue(entry.getValue().getValue());
        parameters.put(key, value);
      }
      return new AnnotationRefBuilder().withClassRef((ClassRef) annotationType).withParameters(parameters).build();
    }
    throw new IllegalStateException("Annotation type: [" + annotationType + "] is not a class reference.");
  }

  private Object mapAnnotationValue(Object value) {
    if (value instanceof Collection) {
      List list = (List) ((Collection) value).stream().map(this::mapAnnotationValue).collect(Collectors.toList());
      if (list.isEmpty()) {
        return null;
      }
      return toArray(list);
    } else if (value instanceof AnnotationMirror) {
      return apply((AnnotationMirror) value);
    } else if (value instanceof AnnotationValue) {
      return ((AnnotationValue) value).getValue();
    } else if (value instanceof TypeMirror) {
      return referenceAdapterFunction.apply((TypeMirror) value);
    } else {
      return value;
    }
  }

  /**
   * Convert the specified {@link List} into an array.
   * 
   * @return an {@link Object} instance that holds the array.
   */
  private static Object toArray(List list) {
    if (list == null || list.size() == 0) {
      return null;
    }
    try {
      Class type = toPrimitive(list.get(0).getClass());
      Object result = Array.newInstance(type, list.size());
      for (int i = 0; i < list.size(); i++) {
        Array.set(result, i, list.get(i));
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw new SundrException(e);
    }
  }

  /**
   * Converts boxed type into into its primitve equivalent.
   * This method also supports {@link com.sun.tools.javac.code.Symbol$VarSymbol} which are used internally and that makes the
   * method extremely falky.
   * 
   * @return a matching primitve or the input type if no match was found.
   */
  private static Class toPrimitive(Class t) {
    String s = t.toString(); //We need to compare using toString() to cover `Symbol$VarSymbol`.
    if (s.contains(Boolean.class.getName())) {
      return boolean.class;
    }
    if (s.contains(Character.class.getName())) {
      return char.class;
    }
    if (s.contains(Short.class.getName())) {
      return short.class;
    }
    if (s.contains(Integer.class.getName())) {
      return int.class;
    }
    if (s.contains(Long.class.getName())) {
      return long.class;
    }
    if (s.contains(Double.class.getName())) {
      return double.class;
    }
    if (s.contains(Float.class.getName())) {
      return float.class;
    }
    return t;
  }
}
