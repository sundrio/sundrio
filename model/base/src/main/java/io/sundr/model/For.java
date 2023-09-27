package io.sundr.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class For implements Statement {

  private final List<Expression> init;
  private final Expression compare;
  private final List<Expression> update;
  private final Statement body;

  public For(List<Expression> init, Expression compare, List<Expression> update, Statement body) {
    this.init = init;
    this.compare = compare;
    this.update = update;
    this.body = body;
  }

  public For(Expression init, Expression compare, Expression update, Statement body) {
    this.init = Arrays.asList(init);
    this.compare = compare;
    this.update = Arrays.asList(update);
    this.body = body;
  }

  public List<Expression> getInit() {
    return init;
  }

  public Expression getCompare() {
    return compare;
  }

  public List<Expression> getUpdate() {
    return update;
  }

  public Statement getBody() {
    return body;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("for").append(SPACE).append(OP);
    sb.append(init.stream().map(e -> e.render()).map(Renderable::noSemicolon).collect(Collectors.joining(",")));
    sb.append(SEMICOLN);
    sb.append(compare.render());
    sb.append(SEMICOLN);
    sb.append(update.stream().map(e -> e.render()).collect(Collectors.joining(",")));
    sb.append(CP);
    sb.append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(body.render()));
    sb.append(CB).append(NEWLINE);
    return sb.toString();
  }
}
