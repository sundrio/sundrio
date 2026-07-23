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

import java.util.Set;

import org.openrewrite.ExecutionContext;
import org.openrewrite.ScanningRecipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.Expression;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JavaType;

/**
 * Replaces the Mockito statics delegated by the generated {@code Mocks} aggregator
 * ({@code verifyNoInteractions}, {@code verifyNoMoreInteractions}, {@code inOrder}, {@code reset},
 * {@code clearInvocations}, {@code timeout}, {@code after}, and bare {@code verify}/{@code when} not
 * already folded into a fluent chain) with the corresponding {@code Mocks} call, so a suite needs a
 * single import of the generated aggregator instead of {@code org.mockito.Mockito}.
 */
public class RewriteDelegatedStatics extends ScanningRecipe<RewriteDelegatedStatics.Accumulator> {

  static final class Accumulator {
    final MarkerPackages.Scan scan = new MarkerPackages.Scan();
  }

  private static final Set<String> DELEGATED = Set.of(
      "verifyNoInteractions", "verifyNoMoreInteractions", "inOrder", "reset", "clearInvocations",
      "timeout", "after", "verify", "when");

  @Override
  public String getDisplayName() {
    return "Rewrite delegated Mockito statics to the Mocks aggregator";
  }

  @Override
  public String getDescription() {
    return "Replaces Mockito.verifyNoInteractions/inOrder/reset/... and leftover bare "
        + "verify/when calls with the generated Mocks aggregator equivalents.";
  }

  @Override
  public Accumulator getInitialValue(ExecutionContext ctx) {
    return new Accumulator();
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getScanner(Accumulator acc) {
    return MarkerPackages.scanner(acc.scan);
  }

  @Override
  public TreeVisitor<?, ExecutionContext> getVisitor(Accumulator acc) {
    String aggregatorPackage = MarkerPackages.aggregatorPackage(acc.scan);
    return new JavaIsoVisitor<ExecutionContext>() {
      @Override
      public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method, ExecutionContext ctx) {
        J.MethodInvocation mi = super.visitMethodInvocation(method, ctx);
        if (!DELEGATED.contains(mi.getSimpleName()) || !isMockitoOwned(mi)) {
          return mi;
        }
        if (isStubbingOrVerifyChainHead(mi)) {
          return mi;
        }
        StringBuilder args = new StringBuilder();
        for (int i = 0; i < mi.getArguments().size(); i++) {
          Expression a = mi.getArguments().get(i);
          if (a instanceof J.Empty) {
            continue;
          }
          if (args.length() > 0) {
            args.append(", ");
          }
          args.append(a.printTrimmed(new org.openrewrite.Cursor(getCursor(), a)).trim());
        }
        String text = "Mocks." + mi.getSimpleName() + "(" + args + ")";
        J.MethodInvocation replaced = JavaTemplate.builder(text)
            .contextSensitive()
            .build()
            .apply(getCursor(), mi.getCoordinates().replace());
        maybeRemoveImport(MockitoNames.MOCKITO + "." + mi.getSimpleName());
        maybeAddAggregatorImport();
        return replaced.withPrefix(mi.getPrefix());
      }

      /**
       * Import the generated {@code Mocks} aggregator (generated into the mocked types'
       * least-common-denominator package) so the rewritten reference resolves from call sites in any
       * other package; a no-op when the call site already lives in that package.
       */
      private void maybeAddAggregatorImport() {
        if (aggregatorPackage != null) {
          String fqn = aggregatorPackage.isEmpty()
              ? MockitoNames.AGGREGATOR
              : aggregatorPackage + "." + MockitoNames.AGGREGATOR;
          maybeAddImport(fqn, false);
        }
      }

      /**
       * A {@code when(...)} that is the select of a {@code .thenX(...)} call, or a
       * {@code verify(...)} that is the select of a subsequent {@code .method(...)} call, is the
       * head of a stubbing/verify chain owned by {@link RewriteStubbingAndVerify}. Leave it alone so
       * step 3 never partially rewrites a chain that step 2 left with a TODO.
       */
      private boolean isStubbingOrVerifyChainHead(J.MethodInvocation mi) {
        if (!"when".equals(mi.getSimpleName()) && !"verify".equals(mi.getSimpleName())) {
          return false;
        }
        J parent = getCursor().getParentTreeCursor().getValue();
        return parent instanceof J.MethodInvocation
            && ((J.MethodInvocation) parent).getSelect() == mi;
      }

      private boolean isMockitoOwned(J.MethodInvocation mi) {
        JavaType.Method mt = mi.getMethodType();
        if (mt != null && mt.getDeclaringType() != null) {
          return MockitoNames.MOCKITO.equals(mt.getDeclaringType().getFullyQualifiedName());
        }
        boolean selectIsMockito = mi.getSelect() instanceof J.Identifier
            && "Mockito".equals(((J.Identifier) mi.getSelect()).getSimpleName());
        return selectIsMockito || mi.getSelect() == null;
      }
    };
  }
}
