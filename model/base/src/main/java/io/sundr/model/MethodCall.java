package io.sundr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MethodCall implements ExpressionOrStatement {

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

  public MethodCall(String name, Expression scope, Expression... arguments) {
    this(name, scope, new ArrayList<TypeRef>(), Arrays.asList(arguments));
  }

  public MethodCall(String name, ClassRef scope, Expression... arguments) {
    this(name, ValueRef.from(scope), arguments);
  }

  public MethodCall(String name, Class scope, Expression... arguments) {
    this(name, ValueRef.from(ClassRef.forClass(scope)), arguments);
  }

  public MethodCall(String name, Object scope, Expression... arguments) {
    this(name, ValueRef.from(scope), arguments);
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
      // ValueRef of class / ClassRef are rendered using the .class suffix.
      // In this case we need to remove that.
      sb.append(scope.renderExpression().replaceAll(Pattern.quote(".class") + "$", "")).append(DOT);
      if (!parameters.isEmpty()) {
        sb.append("<");
        sb.append(parameters.stream().map(TypeRef::render).collect(Collectors.joining(", ")));
        sb.append(">");
      }
    }

    sb.append(name).append(OP);
    if (!arguments.isEmpty()) {
      sb.append(arguments.stream().map(Expression::renderExpression).collect(Collectors.joining(", ")));
    }
    sb.append(CP);
    return sb.toString();
  }
}
