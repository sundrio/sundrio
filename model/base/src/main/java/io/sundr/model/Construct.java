package io.sundr.model;

import java.util.List;

public class Construct implements Expression {

  private final ClassRef type;
  private final List<TypeRef> parameters;
  private final List<Expression> arguments;

  public Construct(ClassRef type, List<TypeRef> parameters, List<Expression> arguments) {
    this.type = type;
    this.parameters = parameters;
    this.arguments = arguments;
  }

  public ClassRef getType() {
    return type;
  }

  public List<TypeRef> getParameters() {
    return parameters;
  }

  public List<Expression> getarguments() {
    return arguments;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(type.getFullyQualifiedName());
    if (!parameters.isEmpty()) {
      sb.append(parameters.stream().map(TypeRef::render).collect(java.util.stream.Collectors.joining(", ", "<", ">")));
    }
    sb.append(arguments.stream().map(Expression::render).collect(java.util.stream.Collectors.joining(", ", "(", ")")));
    return sb.toString();
  }
}
