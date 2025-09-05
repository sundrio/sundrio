package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Cast implements Expression {

  private final TypeRef type;
  private final Expression expression;

  public Cast(TypeRef type, Expression expression) {
    this.type = type;
    this.expression = expression;
  }

  public Cast(Class type, Expression expression) {
    this(ClassRef.forClass(type), expression);
  }

  public Cast(Class type, Property property) {
    this(ClassRef.forClass(type), property);
  }

  public Expression getExpression() {
    return expression;
  }

  public TypeRef getType() {
    return type;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (type instanceof ClassRef) {
      refs.addAll(((ClassRef) type).getReferences());
    }
    if (expression != null) {
      refs.addAll(expression.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    return OP + type.render() + CP + SPACE + expression.renderExpression();
  }
}
