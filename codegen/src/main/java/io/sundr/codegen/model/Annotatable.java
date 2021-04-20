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

package io.sundr.codegen.model;

import java.util.List;
import java.util.stream.Collectors;

public interface Annotatable extends Node {

  List<AnnotationRef> getAnnotations();

  /**
   * Render the annotations.
   */
  default String renderAnnotations(String indent) {
    StringBuilder sb = new StringBuilder();
    if (getAnnotations() != null && !getAnnotations().isEmpty()) {
      sb.append(NEWLINE);
      sb.append(
          getAnnotations().stream().map(Renderable::render).map(line -> indent + line + NEWLINE).collect(Collectors.joining()));
      sb.append(indent); //This one is to make sure that lines with annotations are aligned with the rest
    }
    return sb.toString();
  }
}
