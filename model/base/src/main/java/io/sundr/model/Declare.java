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
    this(Arrays.asList(property), Optional.of(valueProperty));
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

  //
  // Static factory methods
  //
  public static Declare newInstance(String name, Class type, Expression... arguments) {
    if (arguments.length == 0) {
      return new Declare(Property.newProperty(ClassRef.forClass(type), name), new Construct(type));
    } else if (arguments.length == 1) {
      return new Declare(Property.newProperty(ClassRef.forClass(type), name), new Construct(type, arguments[0]));
    } else {
      return new Declare(Property.newProperty(ClassRef.forClass(type), name), new Construct(type, arguments));
    }
  }

  public static Declare newInstance(String name, ClassRef type, Expression... arguments) {
    if (arguments.length == 0) {
      return new Declare(Property.newProperty(type, name), new Construct(type));
    } else if (arguments.length == 1) {
      return new Declare(Property.newProperty(type, name), new Construct(type, arguments[0]));
    } else {
      return new Declare(Property.newProperty(type, name), new Construct(type, arguments));
    }
  }

  public static Declare cast(String name, ClassRef type, Expression target) {
    return new Declare(Property.newProperty(type, name), new Cast(type, target));
  }

  public List<Property> getProperties() {
    return properties;
  }

  public Optional<Expression> getValue() {
    return value;
  }

  @Override
  public String render() {
    return renderExpression();
  }

  @Override
  public String renderExpression() {
    StringBuilder sb = new StringBuilder();
    TypeRef typeRef = properties.get(0).getTypeRef();
    sb.append(typeRef.render());
    sb.append(SPACE);
    sb.append(properties.stream().map(Property::getName).collect(Collectors.joining(", ")));
    sb.append(value.map(v -> " = " + v.renderExpression()).orElse(""));
    return sb.toString();
  }
}
