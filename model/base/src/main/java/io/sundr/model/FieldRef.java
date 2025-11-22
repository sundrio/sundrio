package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class FieldRef extends WithScope implements ExpressionOrStatement {

  private final Field field;

  public FieldRef(Field field, Expression scope) {
    super(scope);
    this.field = field;
  }

  public FieldRef(TypeRef type, String name, Expression scope) {
    super(scope);
    this.field = Field.newField(type, name);
  }

  public FieldRef(String name, Expression scope) {
    super(scope);
    this.field = Field.newField(ClassRef.OBJECT, name);
  }

  public FieldRef(Field field) {
    super(null);
    this.field = field;
  }

  public Field getField() {
    return field;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (field != null) {
      refs.addAll(field.getReferences());
    }
    if (getScope() != null) {
      refs.addAll(getScope().getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    if (getScope() != null) {
      return getScope().renderExpression() + DOT + field.getName();
    }
    return field.getName();
  }

  @Override
  public String toString() {
    return "FieldRef [field=" + field + ", scope=" + getScope() + "]";
  }

}
