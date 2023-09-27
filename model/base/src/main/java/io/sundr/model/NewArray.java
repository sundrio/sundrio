package io.sundr.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewArray implements Expression {

  private final TypeRef type;
  private final List<Expression> expressions;

  public NewArray(TypeRef type, List<Expression> expressions) {
    this.type = type;
    this.expressions = expressions;
  }

  public NewArray(Class<?> type, Expression... expressions) {
    this(ClassRef.forClass(type), Arrays.asList(expressions));
  }

  public NewArray(TypeRef type, Expression... expressions) {
    this(type, Arrays.asList(expressions));
  }

  public NewArray(Class<?> type, Integer... sizes) {
    this(ClassRef.forClass(type), Arrays.stream(sizes).map(ValueRef::from).collect(Collectors.toList()));
  }

  public NewArray(TypeRef type, Integer... sizes) {
    this(type, Arrays.stream(sizes).map(ValueRef::from).collect(Collectors.toList()));
  }

  public TypeRef getType() {
    return type;
  }

  public List<Expression> getExpressions() {
    return expressions;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(type.render());
    sb.append(expressions.stream().map(Expression::render).collect(Collectors.joining(", ", "[", "]")));
    return sb.toString();
  }

}
