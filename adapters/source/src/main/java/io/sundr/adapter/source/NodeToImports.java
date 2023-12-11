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
import com.github.javaparser.ast.NamedNode;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.QualifiedNameExpr;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;

public class NodeToImports implements Function<Node, Set<ClassRef>> {

  private static final String SEPARATOR = ".";

  @Override
  public Set<ClassRef> apply(Node node) {
    Set<ClassRef> imports = new LinkedHashSet<ClassRef>();
    if (node instanceof NamedNode) {
      String name = ((NamedNode) node).getName();
      Node current = node;
      while (!(current instanceof CompilationUnit)) {
        current = current.getParentNode();
      }
      CompilationUnit compilationUnit = (CompilationUnit) current;
      for (ImportDeclaration importDecl : compilationUnit.getImports()) {
        String className = null;
        String packageName = null;
        NameExpr importExpr = importDecl.getName();
        if (importExpr instanceof QualifiedNameExpr) {
          QualifiedNameExpr qualifiedNameExpr = (QualifiedNameExpr) importExpr;
          className = qualifiedNameExpr.getName();
          packageName = qualifiedNameExpr.getQualifier().toString();
        } else if (importDecl.getName().getName().endsWith(SEPARATOR + name)) {
          String importName = importDecl.getName().getName();
          packageName = importName.substring(0, importName.length() - name.length() - 1);
        }
        if (className != null && !className.isEmpty()) {
          imports.add(new ClassRefBuilder().withFullyQualifiedName(packageName + "." + className).build());
        }
      }
    }
    return imports;
  }
}
