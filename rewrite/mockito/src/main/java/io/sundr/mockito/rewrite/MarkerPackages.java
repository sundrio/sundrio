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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.Expression;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JavaType;
import org.openrewrite.java.tree.TypeUtils;

/**
 * Single source of truth for locating the generated {@code Mocks} aggregator. Both the marker
 * generator and the call-site rewriters share the same scan: they collect the same set of mocked
 * types and the package of any existing {@code @Mockables}-annotated marker, then resolve the
 * aggregator package the same way, so they cannot disagree on where {@code Mocks} lives.
 */
final class MarkerPackages {

  private static final Set<String> DO_FAMILY = Set.of(
      "doReturn", "doThrow", "doAnswer", "doNothing", "doCallRealMethod");

  private MarkerPackages() {
  }

  /**
   * The shared state accumulated while scanning all sources: every type used as a mock, and the
   * package of every existing {@code @Mockables}-annotated marker class (the aggregator lives in the
   * marker's package). The rewriters and the marker generator all populate and read this so they
   * agree on the single aggregator location.
   */
  static final class Scan {
    final TreeSet<String> mockTypes = new TreeSet<>();
    final TreeSet<String> markerPackages = new TreeSet<>();
  }

  /**
   * A visitor collecting every type used as a mock: the class literal in {@code mock(X.class)}, the
   * field type of a {@code @Mock}/{@code @MockBean} field, and the receiver inside
   * {@code when(recv.method(...))} / {@code verify(recv)} / {@code doX(v).when(recv)} chains. Add
   * one to a recipe's scanner or run it over a compilation unit to accumulate into {@code types}.
   */
  static JavaIsoVisitor<org.openrewrite.ExecutionContext> collector(Collection<String> types) {
    return new JavaIsoVisitor<org.openrewrite.ExecutionContext>() {

      @Override
      public J.VariableDeclarations visitVariableDeclarations(J.VariableDeclarations multiVariable,
          org.openrewrite.ExecutionContext ctx) {
        boolean annotatedMock = multiVariable.getLeadingAnnotations().stream()
            .anyMatch(a -> TypeUtils.isOfClassType(a.getType(), MockitoNames.MOCK_ANNOTATION)
                || TypeUtils.isOfClassType(a.getType(), MockitoNames.MOCK_BEAN_ANNOTATION));
        if (annotatedMock) {
          addType(multiVariable.getType());
        }
        return super.visitVariableDeclarations(multiVariable, ctx);
      }

      @Override
      public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method,
          org.openrewrite.ExecutionContext ctx) {
        if ("mock".equals(method.getSimpleName()) && method.getArguments().size() >= 1) {
          J.FieldAccess fa = classLiteral(method.getArguments().get(0));
          if (fa != null) {
            addType(fa.getTarget().getType());
          }
        }
        addWhenVerifyReceiverType(method);
        return super.visitMethodInvocation(method, ctx);
      }

      private void addWhenVerifyReceiverType(J.MethodInvocation method) {
        String name = method.getSimpleName();
        if (!"when".equals(name) && !"verify".equals(name) || method.getArguments().isEmpty()) {
          return;
        }
        Object arg = method.getArguments().get(0);
        JavaType receiverType = null;
        if ("when".equals(name) && isDoFamilyChain(method) && arg instanceof Expression) {
          receiverType = ((Expression) arg).getType();
        } else if ("when".equals(name) && arg instanceof J.MethodInvocation) {
          Expression recv = ((J.MethodInvocation) arg).getSelect();
          receiverType = recv == null ? null : recv.getType();
        } else if ("verify".equals(name) && arg instanceof Expression) {
          receiverType = ((Expression) arg).getType();
        }
        addType(receiverType);
      }

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
          types.add(fq.getFullyQualifiedName());
        }
      }
    };
  }

  /**
   * A visitor that, on top of the mocked-type {@link #collector(Collection)}, also records the
   * package of any class annotated with {@code @Mockables} regardless of its name (hand-written
   * markers are often not named {@code MocksConfig}). Wire it into every recipe's scanner so the
   * marker generator and the rewriters observe the same existing markers and mocked types.
   */
  static JavaIsoVisitor<org.openrewrite.ExecutionContext> scanner(Scan scan) {
    JavaIsoVisitor<org.openrewrite.ExecutionContext> collector = collector(scan.mockTypes);
    return new JavaIsoVisitor<org.openrewrite.ExecutionContext>() {

      @Override
      public J.ClassDeclaration visitClassDeclaration(J.ClassDeclaration classDecl,
          org.openrewrite.ExecutionContext ctx) {
        if (classDecl.getType() != null && hasMockablesAnnotation(classDecl)) {
          scan.markerPackages.add(packageOf(classDecl.getType().getFullyQualifiedName()));
        }
        return super.visitClassDeclaration(classDecl, ctx);
      }

      @Override
      public J.VariableDeclarations visitVariableDeclarations(J.VariableDeclarations multiVariable,
          org.openrewrite.ExecutionContext ctx) {
        collector.visitVariableDeclarations(multiVariable, ctx);
        return super.visitVariableDeclarations(multiVariable, ctx);
      }

      @Override
      public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method,
          org.openrewrite.ExecutionContext ctx) {
        collector.visitMethodInvocation(method, ctx);
        return super.visitMethodInvocation(method, ctx);
      }
    };
  }

  /**
   * Whether the class carries {@code @Mockables}. Matches by attributed type when available and
   * falls back to the annotation's simple name, so a freshly generated marker (parsed without the
   * annotation on the classpath, hence unattributed) is still recognized and never duplicated.
   */
  static boolean hasMockablesAnnotation(J.ClassDeclaration classDecl) {
    return classDecl.getLeadingAnnotations().stream().anyMatch(MarkerPackages::isMockables);
  }

  private static boolean isMockables(J.Annotation a) {
    return TypeUtils.isOfClassType(a.getType(), MockitoNames.MOCKABLES_ANNOTATION)
        || MockitoNames.MOCKABLES_SIMPLE.equals(annotationSimpleName(a.getAnnotationType()));
  }

  private static String annotationSimpleName(org.openrewrite.java.tree.NameTree name) {
    if (name instanceof J.Identifier) {
      return ((J.Identifier) name).getSimpleName();
    }
    if (name instanceof J.FieldAccess) {
      return ((J.FieldAccess) name).getSimpleName();
    }
    return null;
  }

  /**
   * The package the {@code Mocks} aggregator lives in, resolved from the shared {@link Scan} in this
   * priority order: (1) the package of an existing {@code @Mockables}-annotated marker if one was
   * found anywhere in the sources, because the aggregator is generated into the marker's package;
   * (2) otherwise the least-common-denominator package of the mocked types, which is where
   * {@link GenerateMarker} will create a fresh marker. Returns {@code null} when neither a marker nor
   * a mocked type was found so callers can skip adding an import that would point nowhere. When more
   * than one marker exists the first in sorted order is chosen deterministically.
   */
  static String aggregatorPackage(Scan scan) {
    if (!scan.markerPackages.isEmpty()) {
      return scan.markerPackages.first();
    }
    return aggregatorPackage(scan.mockTypes);
  }

  /**
   * The package the {@code Mocks} aggregator is generated into when no marker exists: the
   * least-common-denominator package of the given mocked types. With a single type it is that type's
   * package; with several it is their common package prefix. Returns {@code null} when no mocked
   * types were found so callers can skip adding an import that would point nowhere.
   */
  static String aggregatorPackage(Collection<String> mockTypes) {
    if (mockTypes.isEmpty()) {
      return null;
    }
    return leastCommonPackage(mockTypes);
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

  static String packageOf(String fqn) {
    int idx = fqn.lastIndexOf('.');
    return idx < 0 ? "" : fqn.substring(0, idx);
  }

  static String simpleName(String fqn) {
    int idx = fqn.lastIndexOf('.');
    return idx < 0 ? fqn : fqn.substring(idx + 1);
  }
}
