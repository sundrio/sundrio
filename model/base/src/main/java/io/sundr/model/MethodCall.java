package io.sundr.model;

import java.util.List;
import java.util.stream.Collectors;

public class MethodCall implements Expression {

  private final String name;
  private final Expression scope;
  private final List<TypeRef> parameters;
  private List<Expression> arguments;

  public MethodCall(String name, Expression scope, List<TypeRef> parameters, List<Expression> arguments) {
    this.name = name;
    this.scope = scope;
    this.parameters = parameters;
    this.arguments = arguments;
  }

  public String getName() {
    return name;
  }

  public Expression getScope() {
    return scope;
  }

  public List<TypeRef> getParameters() {
    return parameters;
  }

  public List<Expression> getArguments() {
    return arguments;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    if (scope != null) {
      sb.append(scope.render()).append(DOT);
      if (!arguments.isEmpty()) {
        sb.append("<");
        sb.append(parameters.stream().map(TypeRef::render).collect(Collectors.joining(", ")));
        sb.append(">");
      }
    }

    sb.append(name).append(OP);
    if (!arguments.isEmpty()) {
      sb.append(arguments.stream().map(Expression::render).collect(Collectors.joining(", ")));
    }

    if (!arguments.isEmpty()) {
      sb.append(arguments.stream().map(Expression::render).collect(Collectors.joining(", ")));
    }
    sb.append(CP);
    return sb.toString();
  }
}
