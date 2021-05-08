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

import java.util.function.Function;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NamedNode;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.QualifiedNameExpr;

public class NodeToPackage implements Function<Node, String> {

  private static final String JAVA_LANG = "java.lang";
  private static final String SEPARATOR = ".";

  @Override
  public String apply(Node node) {
    String name = null;
    if (node instanceof NamedNode) {
      name = ((NamedNode) node).getName();
    }

    if (node instanceof AnnotationExpr) {
      name = ((AnnotationExpr) node).getName().getName();
    }

    Node current = node;
    while (!(current instanceof CompilationUnit)) {
      current = current.getParentNode();
    }

    CompilationUnit compilationUnit = (CompilationUnit) current;

    for (ImportDeclaration importDecl : compilationUnit.getImports()) {
      NameExpr importExpr = importDecl.getName();
      if (importExpr instanceof QualifiedNameExpr) {
        QualifiedNameExpr qualifiedNameExpr = (QualifiedNameExpr) importExpr;
        String className = qualifiedNameExpr.getName();
        if (name.equals(className)) {
          return qualifiedNameExpr.getQualifier().toString();
        }
      } else if (importDecl.getName().getName().endsWith(SEPARATOR + name)) {
        String importName = importDecl.getName().getName();
        return importName.substring(0, importName.length() - name.length() - 1);
      }
    }

    try {
      Class.forName(JAVA_LANG + "." + name);
      return JAVA_LANG;
    } catch (ClassNotFoundException ex) {
      return compilationUnit.getPackage().getPackageName();
    }
  }
}
