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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Name;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;

public class NodeToImports implements Function<Node, Set<ClassRef>> {

  private static final String SEPARATOR = ".";

  @Override
  public Set<ClassRef> apply(Node node) {
    Set<ClassRef> imports = new LinkedHashSet<ClassRef>();
    if (node instanceof TypeDeclaration) {
      Node current = node;
      while (!(current instanceof CompilationUnit)) {
        current = current.getParentNode().orElse(null);
        if (current == null) {
          return imports;
        }
      }
      CompilationUnit compilationUnit = (CompilationUnit) current;
      for (ImportDeclaration importDecl : compilationUnit.getImports()) {
        Name importName = importDecl.getName();
        String className = importName.getIdentifier();
        String packageName = importName.getQualifier().map(Name::asString).orElse("");
        if (className != null && !className.isEmpty()) {
          imports.add(new ClassRefBuilder().withFullyQualifiedName(packageName + "." + className).build());
        }
      }
    }
    return imports;
  }
}
