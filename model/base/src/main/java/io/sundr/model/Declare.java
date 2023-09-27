package io.sundr.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Declare implements ExpressionOrStatement {

  private final List<Property> properties;
  private final Optional<Expression> value;

  public Declare(List<Property> properties, Optional<Expression> value) {
    this.properties = properties;
    this.value = value;
  }

  //
  // Auxliliary constructors
  //
  public Declare(Property property, Expression expression) {
    this(Arrays.asList(property), Optional.of(expression));
  }

  public Declare(Property property, Object value, Object... rest) {
    this(Arrays.asList(property), Optional.of(ValueRef.from(value, rest)));
  }

  public Declare(Property property, Property valueProperty) {
    this(Arrays.asList(property), Optional.of(valueProperty.toReference()));
  }

  public Declare(Property property) {
    this.properties = Arrays.asList(property);
    this.value = Optional.empty();
  }

  public Declare(Class type, String name) {
    this.properties = Arrays.asList(Property.newProperty(ClassRef.forName(type.getName()), name));
    this.value = Optional.empty();
  }

  public Declare(Class type, String name, Object value) {
    this.properties = Arrays.asList(Property.newProperty(ClassRef.forClass(type), name));
    this.value = Optional.of(ValueRef.from(value));
  }

  public List<Property> getProperties() {
    return properties;
  }

  public Optional<Expression> getValue() {
    return value;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    TypeRef typeRef = properties.get(0).getTypeRef();
    sb.append(typeRef.render());
    sb.append(SPACE);
    sb.append(properties.stream().map(Property::getName).collect(Collectors.joining(", ")));
    sb.append(value.map(v -> " = " + v.render()).orElse(""));
    sb.append(SEMICOLN);
    return sb.toString();
  }
}
