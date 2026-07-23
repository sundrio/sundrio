/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.mockito.rewrite;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openrewrite.ExecutionContext;
import org.openrewrite.ScanningRecipe;
import org.openrewrite.SourceFile;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaParser;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JavaType;
import org.openrewrite.java.tree.TypeUtils;

/**
 * Scans the module's test sources for every type used as a mock — the class literal in
 * {@code mock(X.class)} and the field type of {@code @org.mockito.Mock} fields — collects the
 * distinct set, computes their least-common-denominator package, and generates
 * {@code <lcd-package>/MocksConfig.java} annotated with {@code @Mockables({...})}. If the marker
 * already exists the value set is merged rather than duplicated.
 */
public class GenerateMarker extends ScanningRecipe<GenerateMarker.Accumulator> {

  private static final Set<String> DO_FAMILY = Set.of(
      "doReturn", "doThrow", "doAnswer", "doNothing", "doCallRealMethod");

  static final class Accumulator {
    final TreeSet<String> mockTypes = new TreeSet<>();
    boolean markerExists;
    String existingMarkerPackage;
  }

  @Override
  public String getDisplayName() {
    return "Generate the @Mockables marker (MocksConfig)";
  }

  @Override
  public String getDescription() {
    return "Collects every mocked type in the test sources and generates a MocksConfig marker "
        + "annotated with @Mockables in their least-common-denominator package.";
  }

  @Override
  public Accumulator getInitialValue(ExecutionContext ctx) {
    return new Accumulator();
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getScanner(Accumulator acc) {
    return new JavaIsoVisitor<ExecutionContext>() {

      @Override
      public J.ClassDeclaration visitClassDeclaration(J.ClassDeclaration classDecl, ExecutionContext ctx) {
        if (MockitoNames.MARKER.equals(classDecl.getSimpleName()) && classDecl.getType() != null) {
          acc.markerExists = true;
          String fqn = classDecl.getType().getFullyQualifiedName();
          acc.existingMarkerPackage = packageOf(fqn);
        }
        return super.visitClassDeclaration(classDecl, ctx);
      }

      @Override
      public J.VariableDeclarations visitVariableDeclarations(J.VariableDeclarations multiVariable,
          ExecutionContext ctx) {
        boolean annotatedMock = multiVariable.getLeadingAnnotations().stream()
            .anyMatch(a -> TypeUtils.isOfClassType(a.getType(), MockitoNames.MOCK_ANNOTATION));
        if (annotatedMock) {
          addType(multiVariable.getType());
        }
        return super.visitVariableDeclarations(multiVariable, ctx);
      }

      @Override
      public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method, ExecutionContext ctx) {
        if ("mock".equals(method.getSimpleName()) && method.getArguments().size() >= 1) {
          J.FieldAccess fa = classLiteral(method.getArguments().get(0));
          if (fa != null) {
            addType(fa.getTarget().getType());
          }
        }
        addWhenVerifyReceiverType(method);
        return super.visitMethodInvocation(method, ctx);
      }

      /**
       * The receiver inside {@code when(recv.method(...))} / {@code verify(recv)} is a mock by
       * definition, even for Spring-managed mocks never created via {@code mock(...)}. Add its type
       * so {@code Mocks.mock(recv)} resolves; skip when the type cannot be inferred.
       */
      private void addWhenVerifyReceiverType(J.MethodInvocation method) {
        String name = method.getSimpleName();
        if (!"when".equals(name) && !"verify".equals(name) || method.getArguments().isEmpty()) {
          return;
        }
        Object arg = method.getArguments().get(0);
        JavaType receiverType = null;
        if ("when".equals(name) && isDoFamilyChain(method) && arg instanceof org.openrewrite.java.tree.Expression) {
          // Answer-first do-family: doX(v).when(mock) — the argument IS the mock.
          receiverType = ((org.openrewrite.java.tree.Expression) arg).getType();
        } else if ("when".equals(name) && arg instanceof J.MethodInvocation) {
          org.openrewrite.java.tree.Expression recv = ((J.MethodInvocation) arg).getSelect();
          receiverType = recv == null ? null : recv.getType();
        } else if ("verify".equals(name) && arg instanceof org.openrewrite.java.tree.Expression) {
          receiverType = ((org.openrewrite.java.tree.Expression) arg).getType();
        }
        addType(receiverType);
      }

      /**
       * True when this {@code when(...)} is the {@code .when(mock)} step of a
       * {@code doX(...).when(mock).method(...)} answer-first chain: its select is a Mockito
       * do-family static.
       */
      private boolean isDoFamilyChain(J.MethodInvocation whenCall) {
        if (!(whenCall.getSelect() instanceof J.MethodInvocation)) {
          return false;
        }
        return DO_FAMILY.contains(((J.MethodInvocation) whenCall.getSelect()).getSimpleName());
      }

      private J.FieldAccess classLiteral(Object arg) {
        if (arg instanceof J.FieldAccess) {
          J.FieldAccess fa = (J.FieldAccess) arg;
          if ("class".equals(fa.getSimpleName())) {
            return fa;
          }
        }
        return null;
      }

      private void addType(JavaType type) {
        JavaType.FullyQualified fq = TypeUtils.asFullyQualified(type);
        if (fq != null) {
          acc.mockTypes.add(fq.getFullyQualifiedName());
        }
      }
    };
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getVisitor(Accumulator acc) {
    return new JavaIsoVisitor<ExecutionContext>() {
      @Override
      public J.Annotation visitAnnotation(J.Annotation annotation, ExecutionContext ctx) {
        J.Annotation a = super.visitAnnotation(annotation, ctx);
        if (!TypeUtils.isOfClassType(a.getType(), MockitoNames.MOCKABLES_ANNOTATION)) {
          return a;
        }
        J.ClassDeclaration enclosing = getCursor().firstEnclosing(J.ClassDeclaration.class);
        if (enclosing == null || !MockitoNames.MARKER.equals(enclosing.getSimpleName())) {
          return a;
        }
        TreeSet<String> present = new TreeSet<>();
        if (a.getArguments() != null) {
          for (var arg : a.getArguments()) {
            if (arg instanceof J.NewArray) {
              for (var e : ((J.NewArray) arg).getInitializer()) {
                J.FieldAccess fa = e instanceof J.FieldAccess ? (J.FieldAccess) e : null;
                if (fa != null && fa.getTarget().getType() != null) {
                  JavaType.FullyQualified fq = TypeUtils.asFullyQualified(fa.getTarget().getType());
                  if (fq != null) {
                    present.add(fq.getFullyQualifiedName());
                  }
                }
              }
            }
          }
        }
        TreeSet<String> merged = new TreeSet<>(present);
        merged.addAll(acc.mockTypes);
        if (merged.size() == present.size()) {
          return a;
        }
        StringBuilder literals = new StringBuilder();
        for (String fqn : merged) {
          if (literals.length() > 0) {
            literals.append(", ");
          }
          literals.append(fqn).append(".class");
        }
        return org.openrewrite.java.JavaTemplate.builder("@Mockables({ " + literals + " })")
            .imports(MockitoNames.MOCKABLES_ANNOTATION)
            .contextSensitive()
            .build()
            .apply(getCursor(), a.getCoordinates().replace());
      }
    };
  }

  @Override
  public Collection<SourceFile> generate(Accumulator acc, Collection<SourceFile> generatedInThisCycle,
      ExecutionContext ctx) {
    if (acc.markerExists || acc.mockTypes.isEmpty()) {
      return Collections.emptyList();
    }
    String pkg = leastCommonPackage(acc.mockTypes);
    String source = markerSource(pkg, acc.mockTypes);
    SourceFile parsed = JavaParser.fromJavaVersion().build()
        .parse(ctx, source)
        .findFirst()
        .orElse(null);
    if (parsed == null) {
      return Collections.emptyList();
    }
    SourceFile generated = parsed.withSourcePath(Paths.get(
        "src/test/java/" + pkg.replace('.', '/') + "/" + MockitoNames.MARKER + ".java"));
    return Collections.singletonList(generated);
  }

  static String markerSource(String pkg, Collection<String> types) {
    List<String> shortNames = new ArrayList<>();
    List<String> imports = new ArrayList<>();
    for (String fqn : types) {
      shortNames.add(simpleName(fqn) + ".class");
      if (!packageOf(fqn).equals(pkg)) {
        imports.add("import " + fqn + ";");
      }
    }
    StringBuilder sb = new StringBuilder();
    if (!pkg.isEmpty()) {
      sb.append("package ").append(pkg).append(";\n\n");
    }
    sb.append("import io.sundr.mockito.annotations.Mockables;\n");
    for (String imp : imports) {
      sb.append(imp).append("\n");
    }
    sb.append("\n@Mockables({ ").append(String.join(", ", shortNames)).append(" })\n");
    sb.append("public class ").append(MockitoNames.MARKER).append(" {\n}\n");
    return sb.toString();
  }

  static String leastCommonPackage(Collection<String> types) {
    List<String> packages = new ArrayList<>();
    for (String fqn : types) {
      packages.add(packageOf(fqn));
    }
    if (packages.isEmpty()) {
      return "";
    }
    String[] first = packages.get(0).split("\\.");
    int common = first.length;
    for (String p : packages) {
      String[] parts = p.split("\\.");
      int i = 0;
      while (i < common && i < parts.length && parts[i].equals(first[i])) {
        i++;
      }
      common = i;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < common; i++) {
      if (i > 0) {
        sb.append('.');
      }
      sb.append(first[i]);
    }
    return sb.toString();
  }

  private static String packageOf(String fqn) {
    int idx = fqn.lastIndexOf('.');
    return idx < 0 ? "" : fqn.substring(0, idx);
  }

  private static String simpleName(String fqn) {
    int idx = fqn.lastIndexOf('.');
    return idx < 0 ? fqn : fqn.substring(idx + 1);
  }
}
