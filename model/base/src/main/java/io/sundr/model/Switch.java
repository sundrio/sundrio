package io.sundr.model;

import java.util.Map;
import java.util.Optional;

public class Switch implements Statement {

  private final Expression expression;
  private final Map<ValueRef, Block> cases;
  private final Optional<Block> defaultCase;

  public Switch(Expression expression, Map<ValueRef, Block> cases, Optional<Block> defaultCase) {
    this.expression = expression;
    this.cases = cases;
    this.defaultCase = defaultCase;
  }

  public Expression getExpression() {
    return expression;
  }

  public Map<ValueRef, Block> getCases() {
    return cases;
  }

  public Optional<Block> getDefaultCase() {
    return defaultCase;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("switch");
    sb.append(SPACE);
    sb.append(OP);
    sb.append(expression.renderExpression());
    sb.append(CP);
    sb.append(SPACE);
    sb.append(OB);
    for (Map.Entry<ValueRef, Block> entry : cases.entrySet()) {
      sb.append(NEWLINE);
      sb.append(tab("case " + entry.getKey().render() + ":"));
      sb.append(NEWLINE);
      sb.append(tab(tab(entry.getValue().render(), NEWLINE, "break;")));
    }
    defaultCase.ifPresent(d -> {
      sb.append(NEWLINE);
      sb.append(tab("default:"));
      sb.append(NEWLINE);
      sb.append(tab(tab(d.render())));
    });
    sb.append(NEWLINE);
    sb.append(CB);
    return sb.toString();
  }
}
