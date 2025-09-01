package io.sundr.model;

import java.util.Arrays;
import java.util.List;

public class IfDslConditionStep {
  private Expression condition;

  IfDslConditionStep(Expression condition) {
    this.condition = condition;
  }

  public IfDslConditionStep and(Expression expr) {
    return new IfDslConditionStep(Expression.and(this.condition, expr));
  }

  public IfDslConditionStep or(Expression expr) {
    return new IfDslConditionStep(Expression.or(this.condition, expr));
  }

  public IfDslThenStep then(Statement... statement) {
    return new IfDslThenStep(condition, Arrays.asList(statement));
  }

  public IfDslThenStep then(List<Statement> statements) {
    return new IfDslThenStep(condition, statements);
  }
}