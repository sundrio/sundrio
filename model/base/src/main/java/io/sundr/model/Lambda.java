package io.sundr.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda implements ExpressionOrStatement {

  private final List<String> parameters;
  private final Statement statement;

  public Lambda(List<String> parameters, Statement statement) {
    this.parameters = parameters;
    this.statement = statement;
  }

  public Lambda(String parameter, Statement value) {
    this(Arrays.asList(parameter), value);
  }

  public Lambda(List<String> parameters, Expression expression) {
    this(parameters, new Return(expression));
  }

  public Lambda(String parameter, Expression expression) {
    this(Arrays.asList(parameter), new Return(expression));
  }

  public List<String> getParameters() {
    return parameters;
  }

  public Statement getStatement() {
    return statement;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    if (parameters.size() == 1) {
      sb.append(parameters.get(0));
    } else {
      sb.append(parameters.stream().collect(Collectors.joining(", ", "(", ")")));
    }
    sb.append(" -> ");
    //Return is implied so no need to render it.
    if (statement instanceof Return) {
      sb.append(((Return) statement).getExpression().renderExpression());
    } else {
      sb.append(Renderable.noSemicolon(statement.render()));
    }
    return sb.toString();
  }
}
