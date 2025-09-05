package io.sundr.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Construct implements Expression {

  private final ClassRef type;
  private final List<TypeRef> parameters;
  private final List<Expression> arguments;

  public Construct(ClassRef type, List<TypeRef> parameters, List<Expression> arguments) {
    this.type = type;
    this.parameters = parameters;
    this.arguments = arguments;
  }

  public Construct(ClassRef type, List<Expression> arguments) {
    this(type, Collections.emptyList(), arguments);
  }

  public Construct(ClassRef type, Expression... arguments) {
    this(type, Collections.emptyList(), Arrays.asList(arguments));
  }

  public Construct(Class type, List<TypeRef> parameters, List<Expression> arguments) {
    this(ClassRef.forName(type.getName()), parameters, arguments);
  }

  public Construct(Class type, List<Expression> arguments) {
    this(ClassRef.forName(type.getName()), Collections.emptyList(), arguments);
  }

  public Construct(Class type, Expression... arguments) {
    this(ClassRef.forName(type.getName()), Collections.emptyList(), Arrays.asList(arguments));
  }

  public ClassRef getType() {
    return type;
  }

  public List<TypeRef> getParameters() {
    return parameters;
  }

  public List<Expression> getArguments() {
    return arguments;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    refs.addAll(type.getReferences());
    for (TypeRef param : parameters) {
      if (param instanceof ClassRef) {
        refs.addAll(((ClassRef) param).getReferences());
      }
    }
    for (Expression arg : arguments) {
      refs.addAll(arg.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(type.getFullyQualifiedName());
    if (!parameters.isEmpty()) {
      sb.append(parameters.stream().map(TypeRef::render).collect(java.util.stream.Collectors.joining(", ", "<", ">")));
    }
    sb.append(
        arguments.stream().map(Expression::renderExpression).collect(java.util.stream.Collectors.joining(", ", "(", ")")));
    return sb.toString();
  }
}
