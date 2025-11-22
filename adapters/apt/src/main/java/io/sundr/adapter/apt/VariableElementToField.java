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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.model.AnnotationRef;
import io.sundr.model.Field;
import io.sundr.model.Modifiers;
import io.sundr.model.TypeRef;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class VariableElementToField implements Function<VariableElement, Field> {

  private static final String NEWLINE_PATTERN = "\r|\n";

  private final AptContext context;
  private final Function<TypeMirror, TypeRef> referenceAdapterFunction;
  private final Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction;

  public VariableElementToField(AptContext context, Function<TypeMirror, TypeRef> referenceAdapterFunction,
      Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction) {
    this.context = context;
    this.referenceAdapterFunction = referenceAdapterFunction;
    this.annotationAdapterFunction = annotationAdapterFunction;
  }

  public Field apply(final VariableElement variableElement) {
    String name = variableElement.getSimpleName().toString();

    TypeRef type = referenceAdapterFunction.apply(variableElement.asType());
    boolean isEnum = Types.isEnum(type);
    List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
    for (AnnotationMirror annotationMirror : variableElement.getAnnotationMirrors()) {
      annotations.add(annotationAdapterFunction.apply(annotationMirror));
    }

    String comments = context.getElements().getDocComment(variableElement);
    List<String> commentList = Strings.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());

    return new Field(
        Modifiers.from(variableElement.getModifiers()),
        new HashMap<>(),
        commentList,
        annotations,
        type,
        name,
        Optional.empty(),
        isEnum,
        false);
  }

}