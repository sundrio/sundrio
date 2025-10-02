package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class DeclareFluent<A extends DeclareFluent<A>> extends BaseFluent<A> {

  private ArrayList<PropertyBuilder> properties = new ArrayList<PropertyBuilder>();
  private Optional<Expression> value = Optional.empty();

  public DeclareFluent() {
  }

  public DeclareFluent(Declare instance) {
    this.copyInstance(instance);
  }

  public A addAllToProperties(Collection<Property> items) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").add(builder);
      this.properties.add(builder);
    }
    return (A) this;
  }

  public PropertiesNested<A> addNewProperty() {
    return new PropertiesNested(-1, null);
  }

  public PropertiesNested<A> addNewPropertyLike(Property item) {
    return new PropertiesNested(-1, item);
  }

  public A addToProperties(Property... items) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").add(builder);
      this.properties.add(builder);
    }
    return (A) this;
  }

  public A addToProperties(int index, Property item) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    if (index < 0 || index >= properties.size()) {
      _visitables.get("properties").add(builder);
      properties.add(builder);
    } else {
      _visitables.get("properties").add(builder);
      properties.add(index, builder);
    }
    return (A) this;
  }

  public Property buildFirstProperty() {
    return this.properties.get(0).build();
  }

  public Property buildLastProperty() {
    return this.properties.get(properties.size() - 1).build();
  }

  public Property buildMatchingProperty(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : properties) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public List<Property> buildProperties() {
    return this.properties != null ? build(properties) : null;
  }

  public Property buildProperty(int index) {
    return this.properties.get(index).build();
  }

  protected void copyInstance(Declare instance) {
    if (instance != null) {
      this.withProperties(instance.getProperties());
      this.withValue(instance.getValue());
    }
  }

  public PropertiesNested<A> editFirstProperty() {
    if (properties.size() == 0)
      throw new RuntimeException("Can't edit first properties. The list is empty.");
    return setNewPropertyLike(0, buildProperty(0));
  }

  public PropertiesNested<A> editLastProperty() {
    int index = properties.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last properties. The list is empty.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < properties.size(); i++) {
      if (predicate.test(properties.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching properties. No match found.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public PropertiesNested<A> editProperty(int index) {
    if (properties.size() <= index)
      throw new RuntimeException("Can't edit properties. Index exceeds size.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    DeclareFluent that = (DeclareFluent) o;
    if (!java.util.Objects.equals(properties, that.properties))
      return false;
    if (!java.util.Objects.equals(value, that.value))
      return false;
    return true;
  }

  public Optional<Expression> getValue() {
    return this.value;
  }

  public boolean hasMatchingProperty(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : properties) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasProperties() {
    return this.properties != null && !(this.properties.isEmpty());
  }

  public boolean hasValue() {
    return this.value != null && this.value.isPresent();
  }

  public int hashCode() {
    return java.util.Objects.hash(properties, value, super.hashCode());
  }

  public A removeAllFromProperties(Collection<Property> items) {
    if (this.properties == null)
      return (A) this;
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      this.properties.remove(builder);
    }
    return (A) this;
  }

  public A removeFromProperties(Property... items) {
    if (this.properties == null)
      return (A) this;
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      this.properties.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromProperties(Predicate<PropertyBuilder> predicate) {
    if (properties == null)
      return (A) this;
    final Iterator<PropertyBuilder> each = properties.iterator();
    final List visitables = _visitables.get("properties");
    while (each.hasNext()) {
      PropertyBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public PropertiesNested<A> setNewPropertyLike(int index, Property item) {
    return new PropertiesNested(index, item);
  }

  public A setToProperties(int index, Property item) {
    if (this.properties == null) {
      this.properties = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    if (index < 0 || index >= properties.size()) {
      _visitables.get("properties").add(builder);
      properties.add(builder);
    } else {
      _visitables.get("properties").add(builder);
      properties.set(index, builder);
    }
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (properties != null && !properties.isEmpty()) {
      sb.append("properties:");
      sb.append(properties + ",");
    }
    if (value != null) {
      sb.append("value:");
      sb.append(value);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withProperties(List<Property> properties) {
    if (this.properties != null) {
      this._visitables.get("properties").clear();
    }
    if (properties != null) {
      this.properties = new ArrayList();
      for (Property item : properties) {
        this.addToProperties(item);
      }
    } else {
      this.properties = null;
    }
    return (A) this;
  }

  public A withProperties(Property... properties) {
    if (this.properties != null) {
      this.properties.clear();
      _visitables.remove("properties");
    }
    if (properties != null) {
      for (Property item : properties) {
        this.addToProperties(item);
      }
    }
    return (A) this;
  }

  public A withValue(Optional<Expression> value) {
    if (value == null || !(value.isPresent())) {
      this.value = Optional.empty();
    } else {
      this.value = value;
    }
    return (A) this;
  }

  public A withValue(Expression value) {
    if (value == null) {
      this.value = Optional.empty();
    } else {
      this.value = Optional.of(value);
    }
    return (A) this;
  }

  public class PropertiesNested<N> extends PropertyFluent<PropertiesNested<N>> implements Nested<N> {

    PropertyBuilder builder;
    int index;

    PropertiesNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.setToProperties(index, builder.build());
    }

    public N endProperty() {
      return and();
    }

  }
}
