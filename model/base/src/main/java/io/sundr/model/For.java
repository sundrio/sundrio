package io.sundr.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (init != null) {
      for (Expression expr : init) {
        if (expr != null) {
          refs.addAll(expr.getReferences());
        }
      }
    }
    if (compare != null) {
      refs.addAll(compare.getReferences());
    }
    if (update != null) {
      for (Expression expr : update) {
        if (expr != null) {
          refs.addAll(expr.getReferences());
        }
      }
    }
    if (body != null) {
      refs.addAll(body.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("for").append(SPACE).append(OP);
    sb.append(init.stream().map(e -> e.renderExpression()).map(Renderable::noSemicolon).collect(Collectors.joining(",")));
    sb.append(SEMICOLN);
    sb.append(compare.renderExpression());
    sb.append(SEMICOLN);
    sb.append(update.stream().map(e -> e.renderExpression()).collect(Collectors.joining(",")));
    sb.append(CP);
    sb.append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(body.renderStatement()));
    sb.append(CB).append(NEWLINE);
    return sb.toString();
  }

  //
  // DSL
  //

  public static DslInit init(Property prop, Object expression) {
    return new DslInit(Arrays.asList(new Declare(prop, expression)));
  }

  public static DslInit init(Expression... init) {
    return new DslInit(Arrays.asList(init));
  }

  public static DslInit init(List<Expression> init) {
    return new DslInit(init);
  }

  public static class DslInit {
    private List<Expression> init;

    DslInit(List<Expression> init) {
      this.init = init;
    }

    public DslCompareStep compare(Expression compare) {
      return new DslCompareStep(init, compare);
    }

    public DslCompareStep eq(Expression left, Expression right) {
      return new DslCompareStep(init, Expression.eq(left, right));
    }

    public DslCompareStep ge(Expression left, Expression right) {
      return new DslCompareStep(init, new GreaterThanOrEqual(left, right));
    }

    public DslCompareStep gt(Expression left, Expression right) {
      return new DslCompareStep(init, new GreaterThan(left, right));
    }

    public DslCompareStep le(Expression left, Expression right) {
      return new DslCompareStep(init, new LessThanOrEqual(left, right));
    }

    public DslCompareStep lt(Expression left, Expression right) {
      return new DslCompareStep(init, new LessThan(left, right));
    }

    public DslCompareStep ne(Expression left, Expression right) {
      return new DslCompareStep(init, Expression.ne(left, right));
    }

    public DslCompareStep isNull(Expression expr) {
      return new DslCompareStep(init, Expression.isNull(expr));
    }

    public DslCompareStep notNull(Expression expr) {
      return new DslCompareStep(init, Expression.notNull(expr));
    }
  }

  public static class DslCompareStep {
    private List<Expression> init;
    private Expression compare;

    DslCompareStep(List<Expression> init, Expression compare) {
      this.init = init;
      this.compare = compare;
    }

    public DslBodyStep update(Expression... update) {
      return new DslBodyStep(init, compare, Arrays.asList(update));
    }

    public DslBodyStep update(List<Expression> update) {
      return new DslBodyStep(init, compare, update);
    }
  }

  public static class DslBodyStep {
    private List<Expression> init;
    private Expression compare;
    private List<Expression> update;

    DslBodyStep(List<Expression> init, Expression compare, List<Expression> update) {
      this.init = init;
      this.compare = compare;
      this.update = update;
    }

    public For body(Statement... statements) {
      return new For(init, compare, update, Block.wrap(statements));
    }
  }
}
