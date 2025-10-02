package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class PropertyRef extends WithScope implements ExpressionOrStatement {

  private final Property property;

  public PropertyRef(Property property, Expression scope) {
    super(scope);
    this.property = property;
  }

  public PropertyRef(TypeRef type, String name, Expression scope) {
    super(scope);
    this.property = Property.newProperty(type, name);
  }

  public PropertyRef(String name, Expression scope) {
    super(scope);
    this.property = Property.newProperty(ClassRef.OBJECT, name);
  }

  public PropertyRef(Property property) {
    super(null);
    this.property = property;
  }

  public Property getProperty() {
    return property;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (property != null) {
      refs.addAll(property.getReferences());
    }
    if (getScope() != null) {
      refs.addAll(getScope().getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    if (getScope() != null) {
      return getScope().renderExpression() + DOT + property.getName();
    }
    return property.getName();
  }

  @Override
  public String toString() {
    return "PropertyRef [property=" + property + ", scope=" + getScope() + "]";
  }

}
