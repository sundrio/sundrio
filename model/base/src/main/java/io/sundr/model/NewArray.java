package io.sundr.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (type instanceof ClassRef) {
      refs.addAll(((ClassRef) type).getReferences());
    }
    if (expressions != null) {
      for (Expression expr : expressions) {
        if (expr != null) {
          refs.addAll(expr.getReferences());
        }
      }
    }
    return refs;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(type.render());
    sb.append(expressions.stream().map(Expression::renderExpression).collect(Collectors.joining(", ", "[", "]")));
    return sb.toString();
  }

}
