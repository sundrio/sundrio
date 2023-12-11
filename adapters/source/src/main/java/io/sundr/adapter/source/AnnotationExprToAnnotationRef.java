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

package io.sundr.adapter.source;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;

import io.sundr.model.AnnotationRef;
import io.sundr.model.AnnotationRefBuilder;

public class AnnotationExprToAnnotationRef implements Function<AnnotationExpr, AnnotationRef> {

  private static final Function<Node, String> PACKAGENAME = new NodeToPackage();

  @Override
  public AnnotationRef apply(AnnotationExpr annotation) {
    String name = annotation.getName().getName();
    String packageName = PACKAGENAME.apply(annotation);
    Map<String, Object> parameters = new HashMap<>();

    if (annotation instanceof SingleMemberAnnotationExpr) {
      SingleMemberAnnotationExpr single = (SingleMemberAnnotationExpr) annotation;
      String key = "value";
      Object value = single.getMemberValue().toString();
      parameters.put(key, value);
    } else if (annotation instanceof NormalAnnotationExpr) {
      NormalAnnotationExpr normal = (NormalAnnotationExpr) annotation;
      for (MemberValuePair pair : normal.getPairs()) {
        String key = pair.getName().toString();
        Object value = pair.getData();
        parameters.put(key, value);
      }
    }
    return new AnnotationRefBuilder().withNewClassRef().withFullyQualifiedName(packageName + "." + name).endClassRef()
        .withParameters(parameters)
        .build();
  }
}
