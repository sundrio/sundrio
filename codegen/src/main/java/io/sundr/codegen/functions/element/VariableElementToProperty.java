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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;

import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;
import io.sundr.model.AnnotationRef;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeRef;

public class VariableElementToProperty implements Function<VariableElement, Property> {

  private static final String NEWLINE_PATTERN = "\r|\n";

  private final ElementContext context;

  public VariableElementToProperty(ElementContext context) {
    this.context = context;
  }

  public Property apply(final VariableElement variableElement) {
    String name = variableElement.getSimpleName().toString();

    TypeRef type = context.getTypeMirrorToTypeRef().apply(variableElement.asType());
    List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
    for (AnnotationMirror annotationMirror : variableElement.getAnnotationMirrors()) {
      annotations.add(context.getAnnotationMirrorToAnnotationRef().apply(annotationMirror));
    }

    String comments = CodegenContext.getContext().getElements().getDocComment(variableElement);
    List<String> commentList = StringUtils.isNullOrEmpty(comments) ? new ArrayList<>()
        : Arrays.stream(comments.split(NEWLINE_PATTERN)).map(String::trim).filter(s -> !s.isEmpty())
            .collect(Collectors.toList());
    return new PropertyBuilder().withComments(commentList).withName(name).withTypeRef(type).withAnnotations(annotations)
        .withModifiers(TypeUtils.modifiersToInt(variableElement.getModifiers())).build();
  }

}
