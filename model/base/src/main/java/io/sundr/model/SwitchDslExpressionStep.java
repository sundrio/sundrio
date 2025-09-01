package io.sundr.model;

import java.util.List;
import java.util.Map;

public class SwitchDslExpressionStep {
  private Expression expression;

  SwitchDslExpressionStep(Expression expression) {
    this.expression = expression;
  }

  public SwitchDslCaseStep inCase(Object key, Statement... statements) {
    return new SwitchDslCaseStep(expression).inCase(key, statements);
  }

  public SwitchDslCaseStep inCase(ValueRef key, Statement... statements) {
    return new SwitchDslCaseStep(expression).inCase(key, statements);
  }

  public SwitchDslCaseStep inCase(Object key, List<Statement> statements) {
    return new SwitchDslCaseStep(expression).inCase(key, statements);
  }

  public SwitchDslCaseStep inCase(ValueRef key, List<Statement> statements) {
    return new SwitchDslCaseStep(expression).inCase(key, statements);
  }

  public SwitchDslCaseStep inCase(Object key, Block block) {
    return new SwitchDslCaseStep(expression).inCase(key, block);
  }

  public SwitchDslCaseStep inCase(ValueRef key, Block block) {
    return new SwitchDslCaseStep(expression).inCase(key, block);
  }

  public SwitchDslCaseStep cases(Map<ValueRef, Block> cases) {
    return new SwitchDslCaseStep(expression).cases(cases);
  }

  public Switch defaultCase(Statement... statements) {
    return new SwitchDslCaseStep(expression).defaultCase(statements);
  }

  public Switch defaultCase(List<Statement> statements) {
    return new SwitchDslCaseStep(expression).defaultCase(statements);
  }

  public Switch defaultCase(Block block) {
    return new SwitchDslCaseStep(expression).defaultCase(block);
  }
}