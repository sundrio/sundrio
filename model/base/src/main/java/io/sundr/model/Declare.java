package io.sundr.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Declare implements Statement {

  private final List<Property> properties;
  private final Optional<Expression> value;

  public Declare(List<Property> properties, Optional<Expression> value) {
    this.properties = properties;
    this.value = value;
  }

  public Declare(Property property, Object value, Object... rest) {
    this(Arrays.asList(property), Optional.of(ValueRef.from(value, rest)));
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
