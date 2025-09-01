package io.sundr.model;

public class PropertyRef implements Expression {

  private final Property property;
  private final Expression scope;

  public PropertyRef(Property property, Expression scope) {
    this.property = property;
    this.scope = scope;
  }

  public PropertyRef(TypeRef type, String name, Expression scope) {
    this.property = Property.newProperty(type, name);
    this.scope = scope;
  }

  public PropertyRef(String name, Expression scope) {
    this.property = Property.newProperty(ClassRef.OBJECT, name);
    this.scope = scope;
  }

  public PropertyRef(Property property) {
    this.property = property;
    this.scope = null;
  }

  public Property getProperty() {
    return property;
  }

  public Expression getScope() {
    return scope;
  }

  @Override
  public String render() {
    if (scope != null) {
      return scope.renderExpression() + DOT + property.getName();
    }
    return property.getName();
  }
}
