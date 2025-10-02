package io.sundr.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Synchronized implements Statement {

  private final Expression lockExpression;
  private final Statement body;

  public Synchronized(Expression lockExpression, Statement body) {
    this.lockExpression = lockExpression;
    this.body = body;
  }

  public Expression getLockExpression() {
    return lockExpression;
  }

  public Statement getBody() {
    return body;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    refs.addAll(lockExpression.getReferences());
    refs.addAll(body.getReferences());
    return refs;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("synchronized").append(SPACE).append(OP).append(lockExpression.render()).append(CP)
        .append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(body.renderStatement()));
    sb.append(CB).append(NEWLINE);
    return sb.toString();
  }

  //
  // DSL
  //

  public static DslLockStep on(Expression lockExpression) {
    return new DslLockStep(lockExpression);
  }

  public static class DslLockStep {
    private Expression lockExpression;

    DslLockStep(Expression lockExpression) {
      this.lockExpression = lockExpression;
    }

    public Synchronized body(Statement... statements) {
      return new Synchronized(lockExpression, Block.wrap(statements));
    }

    public Synchronized body(List<Statement> statements) {
      return new Synchronized(lockExpression, Block.wrap(statements));
    }
  }
}