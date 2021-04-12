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

package io.sundr.codegen.functions.element;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.codegen.converters.TypeRefTypeVisitor;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.AnnotationRefBuilder;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeRef;

public class AnnotationMirrorToAnnotationRef implements Function<AnnotationMirror, AnnotationRef> {

  private static final String EMPTY_PARENTHESIS = "()";
  private static final String EMPTY = "";

  private final ElementContext context;

  public AnnotationMirrorToAnnotationRef(ElementContext context) {
    this.context = context;
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
      return ((Collection) value).stream().map(this::mapAnnotationValue).collect(Collectors.toList());
    } else if (value instanceof AnnotationMirror) {
      return apply((AnnotationMirror) value);
    } else if (value instanceof AnnotationValue) {
      return ((AnnotationValue) value).getValue();
    } else if (value instanceof TypeMirror) {
      return context.getTypeMirrorToTypeRef().apply((TypeMirror) value);
    } else {
      return value;
    }
  }

}
