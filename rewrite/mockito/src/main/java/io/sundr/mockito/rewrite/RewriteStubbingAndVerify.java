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
import java.util.List;
import java.util.Set;

import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.ScanningRecipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.Comment;
import org.openrewrite.java.tree.Expression;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JavaType;
import org.openrewrite.java.tree.Space;
import org.openrewrite.java.tree.Statement;
import org.openrewrite.java.tree.TextComment;
import org.openrewrite.marker.Markers;

/**
 * Rewrites {@code Mockito.when(mock.method(args)).thenX(...)} and
 * {@code Mockito.verify(mock[, mode]).method(args)} chains into the fluent
 * {@code Mocks.mock(mock).when()/.verify()} DSL, mapping each positional argument to a
 * {@code withXxx} / {@code withXxxMatching} / {@code capturingXxx} builder call by parameter name.
 * <p>
 * Any chain that cannot be rewritten confidently and completely is left exactly as-is with a
 * {@code // TODO mockito-annotations: manual migration} comment inserted above it.
 */
public class RewriteStubbingAndVerify extends ScanningRecipe<RewriteStubbingAndVerify.Accumulator> {

  static final class Accumulator {
    final MarkerPackages.Scan scan = new MarkerPackages.Scan();
  }

  @Override
  public String getDisplayName() {
    return "Rewrite Mockito stubbing and verification to the fluent DSL";
  }

  @Override
  public String getDescription() {
    return "Converts Mockito.when(...).thenX(...) and Mockito.verify(...) call sites into the "
        + "io.sundr mockito-annotations fluent DSL, leaving unmappable call sites untouched with a "
        + "manual-migration TODO.";
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
    return new Visitor(acc.scan);
  }

  private static final Set<String> DO_FAMILY = Set.of(
      "doReturn", "doThrow", "doAnswer", "doNothing", "doCallRealMethod");

  private static final class Visitor extends JavaIsoVisitor<ExecutionContext> {

    private final MarkerPackages.Scan scan;
    private String aggregatorPackage;
    private Cursor cuCursor;
    private final Set<String> consumedMatchers = new java.util.HashSet<>();
    private String mapFailureReason;

    Visitor(MarkerPackages.Scan scan) {
      this.scan = scan;
    }

    @Override
    public J.CompilationUnit visitCompilationUnit(J.CompilationUnit cu, ExecutionContext ctx) {
      cuCursor = new Cursor(null, cu);
      aggregatorPackage = MarkerPackages.aggregatorPackage(scan, MarkerPackages.moduleBaseDir(cu.getSourcePath()));
      return super.visitCompilationUnit(cu, ctx);
    }

    private String text(J tree) {
      return tree.printTrimmed(new Cursor(cuCursor, tree)).trim();
    }

    @Override
    public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method, ExecutionContext ctx) {
      J.MethodInvocation mi = super.visitMethodInvocation(method, ctx);

      consumedMatchers.clear();
      Rewrite rewrite = tryStubbing(mi);
      String rootStatic = "when";
      if (rewrite == null) {
        rewrite = tryVerify(mi);
        rootStatic = "verify";
      }
      String doFactory = null;
      if (rewrite == null) {
        doFactory = doFactoryOf(mi);
        if (doFactory != null) {
          rewrite = tryDoStubbing(mi);
          rootStatic = doFactory;
        }
      }
      if (rewrite == null) {
        return mi;
      }
      if (rewrite.unmappable) {
        markTodo(rewrite.reason);
        return mi;
      }
      if (rewrite.voidTerminal) {
        J.VariableDeclarations enclosingDecl = initializerDeclaration(mi);
        if (enclosingDecl != null) {
          doAfterVisit(new DropInitializerVisitor(enclosingDecl));
        }
      }
      J.MethodInvocation replaced = JavaTemplate.builder(rewrite.text)
          .contextSensitive()
          .build()
          .apply(getCursor(), mi.getCoordinates().replace());
      removeNowUnusedImports(rootStatic);
      maybeAddAggregatorImport();
      return replaced.withPrefix(mi.getPrefix());
    }

    /**
     * The variable declaration whose sole initializer is {@code mi}, or {@code null} if {@code mi}
     * is not a variable initializer. A verify chain rewrites to a {@code void} terminal, so an
     * assignment of the original Mockito verify result (which returned the method's type) must lose
     * its left-hand side rather than assign {@code void}.
     */
    private J.VariableDeclarations initializerDeclaration(J.MethodInvocation mi) {
      J.VariableDeclarations decl = getCursor().firstEnclosing(J.VariableDeclarations.class);
      if (decl == null || decl.getVariables().size() != 1) {
        return null;
      }
      Expression initializer = decl.getVariables().get(0).getInitializer();
      return initializer != null && initializer.getId().equals(mi.getId()) ? decl : null;
    }

    /**
     * The rewritten call site references the generated {@code Mocks} aggregator, which is generated
     * into the mocked types' least-common-denominator package. Import it so the reference resolves
     * from call sites in any other package; a no-op when the call site already lives in that package
     * or when no aggregator package could be determined.
     */
    private void maybeAddAggregatorImport() {
      if (aggregatorPackage != null) {
        String fqn = aggregatorPackage.isEmpty()
            ? MockitoNames.AGGREGATOR
            : aggregatorPackage + "." + MockitoNames.AGGREGATOR;
        maybeAddImport(fqn, false);
      }
    }

    private void removeNowUnusedImports(String rootStatic) {
      maybeRemoveImport(MockitoNames.MOCKITO + "." + rootStatic);
      for (String matcher : consumedMatchers) {
        maybeRemoveImport(MockitoNames.MOCKITO + "." + matcher);
        maybeRemoveImport(MockitoNames.ARGUMENT_MATCHERS + "." + matcher);
      }
    }

    private void markTodo(String reason) {
      Statement stmt = getCursor().firstEnclosing(Statement.class);
      if (stmt != null) {
        String text = MockitoNames.TODO_PREFIX + (reason == null ? "manual migration" : reason);
        doAfterVisit(new TodoCommentVisitor(stmt, text));
      }
    }

    // ---- Stubbing: Mockito.when(mock.method(args)).thenX(...) ----------------------------------

    private Rewrite tryStubbing(J.MethodInvocation mi) {
      String terminal = mi.getSimpleName();
      if (!terminal.startsWith("then")) {
        return null;
      }
      if (!(mi.getSelect() instanceof J.MethodInvocation)) {
        return null;
      }
      J.MethodInvocation whenCall = (J.MethodInvocation) mi.getSelect();
      if (!"when".equals(whenCall.getSimpleName()) || whenCall.getArguments().size() != 1) {
        return null;
      }
      if (!isMockitoOwned(whenCall)) {
        return null;
      }
      Expression whenArg = whenCall.getArguments().get(0);
      if (!(whenArg instanceof J.MethodInvocation)) {
        return null;
      }
      J.MethodInvocation invoked = (J.MethodInvocation) whenArg;
      String receiver = simpleReceiver(invoked.getSelect());
      if (receiver == null) {
        return Rewrite.unmappable(MockitoNames.REASON_RECEIVER);
      }
      List<String> withers = mapArguments(invoked, false);
      if (withers == null) {
        return Rewrite.unmappable(mapFailureReason);
      }
      String args = renderArgs(mi.getArguments());
      String text = "Mocks.mock(" + receiver + ").when()." + invoked.getSimpleName() + "()"
          + String.join("", withers) + "." + terminal + "(" + args + ")";
      return Rewrite.of(text);
    }

    // ---- Answer-first do-family: Mockito.doX(v).when(mock).method(args) -------------------------

    /**
     * Returns the do-family factory name ({@code doReturn}/{@code doThrow}/...) heading a
     * {@code doX(...).when(mock).method(args)} chain whose leaf is {@code mi}, or {@code null}
     * when {@code mi} does not close such a chain.
     */
    private String doFactoryOf(J.MethodInvocation mi) {
      if (!(mi.getSelect() instanceof J.MethodInvocation)) {
        return null;
      }
      J.MethodInvocation whenCall = (J.MethodInvocation) mi.getSelect();
      if (!"when".equals(whenCall.getSimpleName()) || whenCall.getArguments().size() != 1) {
        return null;
      }
      if (!(whenCall.getSelect() instanceof J.MethodInvocation)) {
        return null;
      }
      J.MethodInvocation doCall = (J.MethodInvocation) whenCall.getSelect();
      if (!DO_FAMILY.contains(doCall.getSimpleName()) || !isMockitoOwned(doCall)) {
        return null;
      }
      return doCall.getSimpleName();
    }

    private Rewrite tryDoStubbing(J.MethodInvocation mi) {
      J.MethodInvocation whenCall = (J.MethodInvocation) mi.getSelect();
      J.MethodInvocation doCall = (J.MethodInvocation) whenCall.getSelect();
      String receiver = simpleReceiver(whenCall.getArguments().get(0));
      if (receiver == null) {
        return Rewrite.unmappable(MockitoNames.REASON_RECEIVER);
      }
      List<String> withers = mapArguments(mi, false);
      if (withers == null) {
        return Rewrite.unmappable(mapFailureReason);
      }
      String value = renderArgs(doCall.getArguments());
      String text = "Mocks." + doCall.getSimpleName() + "(" + value + ").when(" + receiver + ")."
          + mi.getSimpleName() + "()" + String.join("", withers) + ".done()";
      return Rewrite.of(text);
    }

    // ---- Verify: Mockito.verify(mock[, mode]).method(args) ------------------------------------

    private Rewrite tryVerify(J.MethodInvocation mi) {
      if (!(mi.getSelect() instanceof J.MethodInvocation)) {
        return null;
      }
      J.MethodInvocation verifyCall = (J.MethodInvocation) mi.getSelect();
      if (!"verify".equals(verifyCall.getSimpleName())) {
        return null;
      }
      if (!isMockitoOwned(verifyCall)) {
        return null;
      }
      List<Expression> vArgs = verifyCall.getArguments();
      if (vArgs.isEmpty() || vArgs.size() > 2) {
        return null;
      }
      String receiver = simpleReceiver(vArgs.get(0));
      if (receiver == null) {
        return Rewrite.unmappable(MockitoNames.REASON_RECEIVER);
      }
      List<String> withers = mapArguments(mi, true);
      if (withers == null) {
        return Rewrite.unmappable(mapFailureReason);
      }
      String terminal;
      if (vArgs.size() == 1) {
        terminal = "called()";
      } else {
        terminal = verificationTerminal(vArgs.get(1));
        if (terminal == null) {
          return Rewrite.unmappable(MockitoNames.REASON_UNMAPPABLE_ARG);
        }
      }
      String text = "Mocks.mock(" + receiver + ").verify()." + mi.getSimpleName() + "()"
          + String.join("", withers) + "." + terminal;
      return Rewrite.ofVoid(text);
    }

    private String verificationTerminal(Expression mode) {
      if (!(mode instanceof J.MethodInvocation)) {
        return "verified(" + text(mode) + ")";
      }
      J.MethodInvocation m = (J.MethodInvocation) mode;
      switch (m.getSimpleName()) {
        case "times":
        case "atLeast":
        case "atMost":
          return m.getSimpleName() + "(" + text(m.getArguments().get(0)) + ")";
        case "never":
        case "atLeastOnce":
        case "only":
          return m.getSimpleName() + "()";
        default:
          return "verified(" + text(mode) + ")";
      }
    }

    // ---- Argument -> wither mapping ------------------------------------------------------------

    /** A no-argument invocation needs no withers, so it maps without requiring type attribution. */
    private boolean hasNoArguments(J.MethodInvocation invoked) {
      List<Expression> args = invoked.getArguments();
      return args.isEmpty() || (args.size() == 1 && args.get(0) instanceof J.Empty);
    }

    private List<String> mapArguments(J.MethodInvocation invoked, boolean verify) {
      if (hasNoArguments(invoked)) {
        return new ArrayList<>();
      }
      JavaType.Method mt = invoked.getMethodType();
      if (mt == null) {
        mapFailureReason = MockitoNames.REASON_NO_TYPE;
        return null;
      }
      List<String> paramNames = mt.getParameterNames();
      List<Expression> args = invoked.getArguments();
      if (paramNames.size() != args.size()) {
        mapFailureReason = MockitoNames.REASON_NO_TYPE;
        return null;
      }
      boolean overloaded = isOverloaded(mt);
      List<String> withers = new ArrayList<>();
      for (int i = 0; i < args.size(); i++) {
        String param = paramNames.get(i);
        if (param == null || param.matches("arg\\d+")) {
          mapFailureReason = MockitoNames.REASON_SYNTHETIC_PARAMS;
          return null;
        }
        String cap = MockitoNames.capitalize(param);
        String wither = mapArgument(args.get(i), cap, verify, overloaded);
        if (UNMAPPABLE.equals(wither)) {
          mapFailureReason = MockitoNames.REASON_UNMAPPABLE_ARG;
          return null;
        }
        if (wither != null) {
          withers.add(wither);
        }
      }
      return withers;
    }

    /**
     * A mocked method is overloaded when its declaring type declares more than one method sharing
     * its name. Then an {@code any()} argument's slot is load-bearing: it selects the overload by
     * arity, so it must be pinned rather than dropped. If the declaring type's methods cannot be
     * enumerated (type not fully on the rewrite classpath), treat it as not overloaded so the
     * current, cleaner behavior applies rather than guessing.
     */
    private boolean isOverloaded(JavaType.Method mt) {
      JavaType.FullyQualified declaring = mt.getDeclaringType();
      if (declaring == null) {
        return false;
      }
      int sameName = 0;
      for (JavaType.Method candidate : declaring.getMethods()) {
        if (candidate.getName().equals(mt.getName())) {
          sameName++;
        }
      }
      return sameName > 1;
    }

    private String mapArgument(Expression arg, String cap, boolean verify, boolean overloaded) {
      if (arg instanceof J.MethodInvocation) {
        J.MethodInvocation mi = (J.MethodInvocation) arg;
        String name = mi.getSimpleName();
        if (MockitoNames.ANY_MATCHERS.contains(name)) {
          consumedMatchers.add(name);
          return overloaded ? ".with" + cap + "Any()" : null;
        }
        if ("eq".equals(name)) {
          consumedMatchers.add(name);
          return ".with" + cap + "(" + text(mi.getArguments().get(0)) + ")";
        }
        if (MockitoNames.THAT_MATCHERS.contains(name)) {
          Expression matcher = mi.getArguments().get(0);
          if (!isInlineMatcher(matcher)) {
            return UNMAPPABLE;
          }
          consumedMatchers.add(name);
          return ".with" + cap + "Matching(" + text(matcher) + ")";
        }
        if (MockitoNames.STRING_MATCHERS.contains(name) && mi.getArguments().size() == 1
            && isMatcherOwned(mi)) {
          consumedMatchers.add(name);
          String expected = text(mi.getArguments().get(0));
          return ".with" + cap + "Matching(__s -> __s != null && __s." + name + "(" + expected + "))";
        }
        if ("capture".equals(name) && mi.getSelect() != null) {
          return verify ? ".capturing" + cap + "(" + text(mi.getSelect()) + ")" : UNMAPPABLE;
        }
        return ".with" + cap + "(" + text(arg) + ")";
      }
      if (arg instanceof J.Identifier || arg instanceof J.Literal || arg instanceof J.FieldAccess) {
        return ".with" + cap + "(" + text(arg) + ")";
      }
      return UNMAPPABLE;
    }

    private boolean isInlineMatcher(Expression matcher) {
      return matcher instanceof J.Lambda
          || matcher instanceof J.MemberReference
          || matcher instanceof J.NewClass;
    }

    // ---- helpers ------------------------------------------------------------------------------

    /**
     * The receiver appearing inside {@code when(recv.method(...))} / {@code verify(recv)} IS a mock
     * by definition. Accept a bare identifier or {@code this.<name>} (normalized to {@code <name>});
     * anything else (a method call, chained expression, array access) is left for manual migration.
     */
    private String simpleReceiver(Expression recv) {
      if (recv instanceof J.Identifier) {
        return ((J.Identifier) recv).getSimpleName();
      }
      if (recv instanceof J.FieldAccess) {
        J.FieldAccess fa = (J.FieldAccess) recv;
        if (fa.getTarget() instanceof J.Identifier
            && "this".equals(((J.Identifier) fa.getTarget()).getSimpleName())) {
          return fa.getSimpleName();
        }
      }
      return null;
    }

    private boolean isMockitoOwned(J.MethodInvocation mi) {
      JavaType.Method mt = mi.getMethodType();
      return mt != null && mt.getDeclaringType() != null
          && MockitoNames.MOCKITO.equals(mt.getDeclaringType().getFullyQualifiedName());
    }

    /**
     * Whether the invocation is a Mockito argument matcher, declared on either {@code Mockito} or
     * {@code ArgumentMatchers}. Guards the string-matcher mapping so a user method that merely shares
     * a name (e.g. a domain {@code contains(...)}) is never rewritten into a matcher lambda.
     */
    private boolean isMatcherOwned(J.MethodInvocation mi) {
      JavaType.Method mt = mi.getMethodType();
      if (mt == null || mt.getDeclaringType() == null) {
        return false;
      }
      String owner = mt.getDeclaringType().getFullyQualifiedName();
      return MockitoNames.MOCKITO.equals(owner) || MockitoNames.ARGUMENT_MATCHERS.equals(owner);
    }

    private String renderArgs(List<Expression> args) {
      List<String> parts = new ArrayList<>();
      for (Expression a : args) {
        parts.add(text(a));
      }
      return String.join(", ", parts);
    }
  }

  private static final String UNMAPPABLE = "<unmappable>";

  /**
   * Drops the left-hand side of a variable declaration, leaving only its initializer as an
   * expression statement. Used when a verify chain whose Mockito form returned the method's type is
   * rewritten to a {@code void} terminal: {@code Type x = Mockito.verify(m).f();} becomes
   * {@code Mocks.mock(m).verify().f().called();}. The initializer is preserved verbatim (it has
   * already been rewritten by the enclosing visitor), only the declaration is unwrapped.
   */
  private static final class DropInitializerVisitor extends JavaIsoVisitor<ExecutionContext> {
    private final J.VariableDeclarations target;

    DropInitializerVisitor(J.VariableDeclarations target) {
      this.target = target;
    }

    @Override
    public J.Block visitBlock(J.Block block, ExecutionContext ctx) {
      J.Block b = super.visitBlock(block, ctx);
      return b.withStatements(org.openrewrite.internal.ListUtils.map(b.getStatements(), statement -> {
        if (!(statement instanceof J.VariableDeclarations) || !statement.getId().equals(target.getId())) {
          return statement;
        }
        Expression initializer = ((J.VariableDeclarations) statement).getVariables().get(0).getInitializer();
        if (!(initializer instanceof Statement)) {
          return statement;
        }
        return ((Statement) initializer).withPrefix(statement.getPrefix());
      }));
    }
  }

  /** Attaches a manual-migration TODO comment, explaining the reason, to a statement's prefix. */
  private static final class TodoCommentVisitor extends JavaIsoVisitor<ExecutionContext> {
    private final Statement target;
    private final String text;

    TodoCommentVisitor(Statement target, String text) {
      this.target = target;
      this.text = text;
    }

    @Override
    public Statement visitStatement(Statement statement, ExecutionContext ctx) {
      Statement s = super.visitStatement(statement, ctx);
      if (!s.getId().equals(target.getId())) {
        return s;
      }
      Space prefix = s.getPrefix();
      for (Comment c : prefix.getComments()) {
        if (c instanceof TextComment && ((TextComment) c).getText().contains(MockitoNames.TODO_PREFIX.trim())) {
          return s;
        }
      }
      String indent = indentOf(s, prefix);
      List<Comment> comments = new ArrayList<>(prefix.getComments());
      comments.add(new TextComment(false, text, "\n" + indent, Markers.EMPTY));
      return s.withPrefix(prefix.withComments(comments));
    }

    private String indentOf(Statement s, Space prefix) {
      String ws = prefix.getWhitespace();
      if (ws.contains("\n")) {
        return ws.substring(ws.lastIndexOf('\n') + 1);
      }
      J.Block block = getCursor().firstEnclosing(J.Block.class);
      if (block != null) {
        for (Statement sibling : block.getStatements()) {
          String sw = sibling.getPrefix().getWhitespace();
          if (sw.contains("\n")) {
            return sw.substring(sw.lastIndexOf('\n') + 1);
          }
        }
      }
      return "";
    }
  }

  private static final class Rewrite {
    final String text;
    final boolean unmappable;
    final String reason;
    final boolean voidTerminal;

    private Rewrite(String text, boolean unmappable, String reason, boolean voidTerminal) {
      this.text = text;
      this.unmappable = unmappable;
      this.reason = reason;
      this.voidTerminal = voidTerminal;
    }

    static Rewrite of(String text) {
      return new Rewrite(text, false, null, false);
    }

    static Rewrite ofVoid(String text) {
      return new Rewrite(text, false, null, true);
    }

    static Rewrite unmappable(String reason) {
      return new Rewrite(null, true, reason, false);
    }
  }
}
