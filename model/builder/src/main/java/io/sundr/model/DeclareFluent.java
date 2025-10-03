package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.RuntimeException;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class DeclareFluent<A extends io.sundr.model.DeclareFluent<A>> extends BaseFluent<A> {

  private ArrayList<PropertyBuilder> properties = new ArrayList<PropertyBuilder>();
  private Optional<VisitableBuilder<? extends Expression, ?>> value = Optional.empty();

  public DeclareFluent() {
  }

  public DeclareFluent(Declare instance) {
    this.copyInstance(instance);
  }

  public A addAllToProperties(Collection<Property> items) {
    if (this.properties == null) {
      this.properties = new ArrayList();
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
      this.properties = new ArrayList();
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
      this.properties = new ArrayList();
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

  public Optional<Expression> buildValue() {
    return this.value != null ? this.value.map(v -> v.build()) : Optional.empty();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "Multiply":

        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);

      case "NewArray":

        return (VisitableBuilder<T, ?>) new NewArrayBuilder((NewArray) item);

      case "InstanceOf":

        return (VisitableBuilder<T, ?>) new InstanceOfBuilder((InstanceOf) item);

      case "MethodCall":

        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);

      case "ClassRef":

        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);

      case "Inverse":

        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);

      case "Index":

        return (VisitableBuilder<T, ?>) new IndexBuilder((Index) item);

      case "GreaterThanOrEqual":

        return (VisitableBuilder<T, ?>) new GreaterThanOrEqualBuilder((GreaterThanOrEqual) item);

      case "BitwiseAnd":

        return (VisitableBuilder<T, ?>) new BitwiseAndBuilder((BitwiseAnd) item);

      case "Minus":

        return (VisitableBuilder<T, ?>) new MinusBuilder((Minus) item);

      case "LogicalOr":

        return (VisitableBuilder<T, ?>) new LogicalOrBuilder((LogicalOr) item);

      case "NotEquals":

        return (VisitableBuilder<T, ?>) new NotEqualsBuilder((NotEquals) item);

      case "Divide":

        return (VisitableBuilder<T, ?>) new DivideBuilder((Divide) item);

      case "LessThan":

        return (VisitableBuilder<T, ?>) new LessThanBuilder((LessThan) item);

      case "BitwiseOr":

        return (VisitableBuilder<T, ?>) new BitwiseOrBuilder((BitwiseOr) item);

      case "PropertyRef":

        return (VisitableBuilder<T, ?>) new PropertyRefBuilder((PropertyRef) item);

      case "RightShift":

        return (VisitableBuilder<T, ?>) new RightShiftBuilder((RightShift) item);

      case "Super":

        return (VisitableBuilder<T, ?>) new SuperBuilder((Super) item);

      case "GreaterThan":

        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);

      case "Declare":

        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);

      case "Cast":

        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);

      case "Modulo":

        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);

      case "DotClass":

        return (VisitableBuilder<T, ?>) new DotClassBuilder((DotClass) item);

      case "ValueRef":

        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);

      case "LeftShift":

        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);

      case "Empty":

        return (VisitableBuilder<T, ?>) new EmptyBuilder((Empty) item);

      case "Ternary":

        return (VisitableBuilder<T, ?>) new TernaryBuilder((Ternary) item);

      case "BinaryExpression":

        return (VisitableBuilder<T, ?>) new BinaryExpressionBuilder((BinaryExpression) item);

      case "Equals":

        return (VisitableBuilder<T, ?>) new EqualsBuilder((Equals) item);

      case "Enclosed":

        return (VisitableBuilder<T, ?>) new EnclosedBuilder((Enclosed) item);

      case "PreDecrement":

        return (VisitableBuilder<T, ?>) new PreDecrementBuilder((PreDecrement) item);

      case "PostDecrement":

        return (VisitableBuilder<T, ?>) new PostDecrementBuilder((PostDecrement) item);

      case "Lambda":

        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);

      case "Not":

        return (VisitableBuilder<T, ?>) new NotBuilder((Not) item);

      case "Assign":

        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);

      case "This":

        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);

      case "Negative":

        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);

      case "LogicalAnd":

        return (VisitableBuilder<T, ?>) new LogicalAndBuilder((LogicalAnd) item);

      case "PostIncrement":

        return (VisitableBuilder<T, ?>) new PostIncrementBuilder((PostIncrement) item);

      case "RightUnsignedShift":

        return (VisitableBuilder<T, ?>) new RightUnsignedShiftBuilder((RightUnsignedShift) item);

      case "Plus":

        return (VisitableBuilder<T, ?>) new PlusBuilder((Plus) item);

      case "Construct":

        return (VisitableBuilder<T, ?>) new ConstructBuilder((Construct) item);

      case "Xor":

        return (VisitableBuilder<T, ?>) new XorBuilder((Xor) item);

      case "PreIncrement":

        return (VisitableBuilder<T, ?>) new PreIncrementBuilder((PreIncrement) item);

      case "Property":

        return (VisitableBuilder<T, ?>) new PropertyBuilder((Property) item);

      case "LessThanOrEqual":

        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);

      case "ContextRef":

        return (VisitableBuilder<T, ?>) new ContextRefBuilder((ContextRef) item);

      case "Positive":

        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
  }

  protected void copyInstance(Declare instance) {
    if (instance != null) {
      this.withProperties(instance.getProperties());
      this.withValue(instance.getValue());
    }
  }

  public PropertiesNested<A> editFirstProperty() {
    if (properties.size() == 0) {
      throw new RuntimeException(String.format("Can't edit first %s. The list is empty.", "properties"));
    }
    return this.setNewPropertyLike(0, this.buildProperty(0));
  }

  public PropertiesNested<A> editLastProperty() {
    int index = properties.size() - 1;
    if (index < 0) {
      throw new RuntimeException(String.format("Can't edit last %s. The list is empty.", "properties"));
    }
    return this.setNewPropertyLike(index, this.buildProperty(index));
  }

  public PropertiesNested<A> editMatchingProperty(Predicate<PropertyBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < properties.size(); i++) {
      if (predicate.test(properties.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0) {
      throw new RuntimeException(String.format("Can't edit matching %s. No match found.", "properties"));
    }
    return this.setNewPropertyLike(index, this.buildProperty(index));
  }

  public PropertiesNested<A> editProperty(int index) {
    if (properties.size() <= index) {
      throw new RuntimeException(String.format("Can't edit %s. Index exceeds size.", "properties"));
    }
    return this.setNewPropertyLike(index, this.buildProperty(index));
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    DeclareFluent that = (DeclareFluent) o;
    if (!(Objects.equals(properties, that.properties))) {
      return false;
    }
    if (!(Objects.equals(value, that.value))) {
      return false;
    }
    return true;
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
    return Objects.hash(properties, value);
  }

  public A removeAllFromProperties(Collection<Property> items) {
    if (this.properties == null) {
      return (A) this;
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      this.properties.remove(builder);
    }
    return (A) this;
  }

  public A removeFromProperties(Property... items) {
    if (this.properties == null) {
      return (A) this;
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      this.properties.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromProperties(Predicate<PropertyBuilder> predicate) {
    if (properties == null) {
      return (A) this;
    }
    Iterator<PropertyBuilder> each = properties.iterator();
    List visitables = _visitables.get("properties");
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
      this.properties = new ArrayList();
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
    if (!(properties == null) && !(properties.isEmpty())) {
      sb.append("properties:");
      sb.append(properties);
      sb.append(",");
    }
    if (!(value == null)) {
      sb.append("value:");
      sb.append(value);
    }
    sb.append("}");
    return sb.toString();
  }

  public AssignValueNested<A> withNewAssignValue() {
    return new AssignValueNested(null);
  }

  public AssignValueNested<A> withNewAssignValueLike(Assign item) {
    return new AssignValueNested(item);
  }

  public BinaryExpressionValueNested<A> withNewBinaryExpressionValue() {
    return new BinaryExpressionValueNested(null);
  }

  public BinaryExpressionValueNested<A> withNewBinaryExpressionValueLike(BinaryExpression item) {
    return new BinaryExpressionValueNested(item);
  }

  public BitwiseAndValueNested<A> withNewBitwiseAndValue() {
    return new BitwiseAndValueNested(null);
  }

  public A withNewBitwiseAndValue(Object left, Object right) {
    return (A) this.withValue(new BitwiseAnd(left, right));
  }

  public BitwiseAndValueNested<A> withNewBitwiseAndValueLike(BitwiseAnd item) {
    return new BitwiseAndValueNested(item);
  }

  public BitwiseOrValueNested<A> withNewBitwiseOrValue() {
    return new BitwiseOrValueNested(null);
  }

  public A withNewBitwiseOrValue(Object left, Object right) {
    return (A) this.withValue(new BitwiseOr(left, right));
  }

  public BitwiseOrValueNested<A> withNewBitwiseOrValueLike(BitwiseOr item) {
    return new BitwiseOrValueNested(item);
  }

  public CastValueNested<A> withNewCastValue() {
    return new CastValueNested(null);
  }

  public CastValueNested<A> withNewCastValueLike(Cast item) {
    return new CastValueNested(item);
  }

  public ClassRefValueNested<A> withNewClassRefValue() {
    return new ClassRefValueNested(null);
  }

  public ClassRefValueNested<A> withNewClassRefValueLike(ClassRef item) {
    return new ClassRefValueNested(item);
  }

  public ConstructValueNested<A> withNewConstructValue() {
    return new ConstructValueNested(null);
  }

  public ConstructValueNested<A> withNewConstructValueLike(Construct item) {
    return new ConstructValueNested(item);
  }

  public ContextRefValueNested<A> withNewContextRefValue() {
    return new ContextRefValueNested(null);
  }

  public A withNewContextRefValue(String name) {
    return (A) this.withValue(new ContextRef(name));
  }

  public ContextRefValueNested<A> withNewContextRefValueLike(ContextRef item) {
    return new ContextRefValueNested(item);
  }

  public DeclareValueNested<A> withNewDeclareValue() {
    return new DeclareValueNested(null);
  }

  public A withNewDeclareValue(Class type, String name) {
    return (A) this.withValue(new Declare(type, name));
  }

  public A withNewDeclareValue(Class type, String name, Object value) {
    return (A) this.withValue(new Declare(type, name, value));
  }

  public DeclareValueNested<A> withNewDeclareValueLike(Declare item) {
    return new DeclareValueNested(item);
  }

  public DivideValueNested<A> withNewDivideValue() {
    return new DivideValueNested(null);
  }

  public A withNewDivideValue(Object left, Object right) {
    return (A) this.withValue(new Divide(left, right));
  }

  public DivideValueNested<A> withNewDivideValueLike(Divide item) {
    return new DivideValueNested(item);
  }

  public DotClassValueNested<A> withNewDotClassValue() {
    return new DotClassValueNested(null);
  }

  public DotClassValueNested<A> withNewDotClassValueLike(DotClass item) {
    return new DotClassValueNested(item);
  }

  public EmptyValueNested<A> withNewEmptyValue() {
    return new EmptyValueNested(null);
  }

  public EmptyValueNested<A> withNewEmptyValueLike(Empty item) {
    return new EmptyValueNested(item);
  }

  public EnclosedValueNested<A> withNewEnclosedValue() {
    return new EnclosedValueNested(null);
  }

  public EnclosedValueNested<A> withNewEnclosedValueLike(Enclosed item) {
    return new EnclosedValueNested(item);
  }

  public EqualsValueNested<A> withNewEqualsValue() {
    return new EqualsValueNested(null);
  }

  public A withNewEqualsValue(Object left, Object right) {
    return (A) this.withValue(new Equals(left, right));
  }

  public EqualsValueNested<A> withNewEqualsValueLike(Equals item) {
    return new EqualsValueNested(item);
  }

  public GreaterThanOrEqualValueNested<A> withNewGreaterThanOrEqualValue() {
    return new GreaterThanOrEqualValueNested(null);
  }

  public A withNewGreaterThanOrEqualValue(Object left, Object right) {
    return (A) this.withValue(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualValueNested<A> withNewGreaterThanOrEqualValueLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualValueNested(item);
  }

  public GreaterThanValueNested<A> withNewGreaterThanValue() {
    return new GreaterThanValueNested(null);
  }

  public A withNewGreaterThanValue(Object left, Object right) {
    return (A) this.withValue(new GreaterThan(left, right));
  }

  public GreaterThanValueNested<A> withNewGreaterThanValueLike(GreaterThan item) {
    return new GreaterThanValueNested(item);
  }

  public IndexValueNested<A> withNewIndexValue() {
    return new IndexValueNested(null);
  }

  public IndexValueNested<A> withNewIndexValueLike(Index item) {
    return new IndexValueNested(item);
  }

  public InstanceOfValueNested<A> withNewInstanceOfValue() {
    return new InstanceOfValueNested(null);
  }

  public InstanceOfValueNested<A> withNewInstanceOfValueLike(InstanceOf item) {
    return new InstanceOfValueNested(item);
  }

  public InverseValueNested<A> withNewInverseValue() {
    return new InverseValueNested(null);
  }

  public InverseValueNested<A> withNewInverseValueLike(Inverse item) {
    return new InverseValueNested(item);
  }

  public LambdaValueNested<A> withNewLambdaValue() {
    return new LambdaValueNested(null);
  }

  public LambdaValueNested<A> withNewLambdaValueLike(Lambda item) {
    return new LambdaValueNested(item);
  }

  public LeftShiftValueNested<A> withNewLeftShiftValue() {
    return new LeftShiftValueNested(null);
  }

  public A withNewLeftShiftValue(Object left, Object right) {
    return (A) this.withValue(new LeftShift(left, right));
  }

  public LeftShiftValueNested<A> withNewLeftShiftValueLike(LeftShift item) {
    return new LeftShiftValueNested(item);
  }

  public LessThanOrEqualValueNested<A> withNewLessThanOrEqualValue() {
    return new LessThanOrEqualValueNested(null);
  }

  public A withNewLessThanOrEqualValue(Object left, Object right) {
    return (A) this.withValue(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualValueNested<A> withNewLessThanOrEqualValueLike(LessThanOrEqual item) {
    return new LessThanOrEqualValueNested(item);
  }

  public LessThanValueNested<A> withNewLessThanValue() {
    return new LessThanValueNested(null);
  }

  public A withNewLessThanValue(Object left, Object right) {
    return (A) this.withValue(new LessThan(left, right));
  }

  public LessThanValueNested<A> withNewLessThanValueLike(LessThan item) {
    return new LessThanValueNested(item);
  }

  public LogicalAndValueNested<A> withNewLogicalAndValue() {
    return new LogicalAndValueNested(null);
  }

  public A withNewLogicalAndValue(Object left, Object right) {
    return (A) this.withValue(new LogicalAnd(left, right));
  }

  public LogicalAndValueNested<A> withNewLogicalAndValueLike(LogicalAnd item) {
    return new LogicalAndValueNested(item);
  }

  public LogicalOrValueNested<A> withNewLogicalOrValue() {
    return new LogicalOrValueNested(null);
  }

  public A withNewLogicalOrValue(Object left, Object right) {
    return (A) this.withValue(new LogicalOr(left, right));
  }

  public LogicalOrValueNested<A> withNewLogicalOrValueLike(LogicalOr item) {
    return new LogicalOrValueNested(item);
  }

  public MethodCallValueNested<A> withNewMethodCallValue() {
    return new MethodCallValueNested(null);
  }

  public MethodCallValueNested<A> withNewMethodCallValueLike(MethodCall item) {
    return new MethodCallValueNested(item);
  }

  public MinusValueNested<A> withNewMinusValue() {
    return new MinusValueNested(null);
  }

  public A withNewMinusValue(Object left, Object right) {
    return (A) this.withValue(new Minus(left, right));
  }

  public MinusValueNested<A> withNewMinusValueLike(Minus item) {
    return new MinusValueNested(item);
  }

  public ModuloValueNested<A> withNewModuloValue() {
    return new ModuloValueNested(null);
  }

  public A withNewModuloValue(Object left, Object right) {
    return (A) this.withValue(new Modulo(left, right));
  }

  public ModuloValueNested<A> withNewModuloValueLike(Modulo item) {
    return new ModuloValueNested(item);
  }

  public MultiplyValueNested<A> withNewMultiplyValue() {
    return new MultiplyValueNested(null);
  }

  public A withNewMultiplyValue(Object left, Object right) {
    return (A) this.withValue(new Multiply(left, right));
  }

  public MultiplyValueNested<A> withNewMultiplyValueLike(Multiply item) {
    return new MultiplyValueNested(item);
  }

  public NegativeValueNested<A> withNewNegativeValue() {
    return new NegativeValueNested(null);
  }

  public NegativeValueNested<A> withNewNegativeValueLike(Negative item) {
    return new NegativeValueNested(item);
  }

  public NewArrayValueNested<A> withNewNewArrayValue() {
    return new NewArrayValueNested(null);
  }

  public A withNewNewArrayValue(Class type, Integer[] sizes) {
    return (A) this.withValue(new NewArray(type, sizes));
  }

  public NewArrayValueNested<A> withNewNewArrayValueLike(NewArray item) {
    return new NewArrayValueNested(item);
  }

  public NotEqualsValueNested<A> withNewNotEqualsValue() {
    return new NotEqualsValueNested(null);
  }

  public A withNewNotEqualsValue(Object left, Object right) {
    return (A) this.withValue(new NotEquals(left, right));
  }

  public NotEqualsValueNested<A> withNewNotEqualsValueLike(NotEquals item) {
    return new NotEqualsValueNested(item);
  }

  public NotValueNested<A> withNewNotValue() {
    return new NotValueNested(null);
  }

  public NotValueNested<A> withNewNotValueLike(Not item) {
    return new NotValueNested(item);
  }

  public PlusValueNested<A> withNewPlusValue() {
    return new PlusValueNested(null);
  }

  public A withNewPlusValue(Object left, Object right) {
    return (A) this.withValue(new Plus(left, right));
  }

  public PlusValueNested<A> withNewPlusValueLike(Plus item) {
    return new PlusValueNested(item);
  }

  public PositiveValueNested<A> withNewPositiveValue() {
    return new PositiveValueNested(null);
  }

  public PositiveValueNested<A> withNewPositiveValueLike(Positive item) {
    return new PositiveValueNested(item);
  }

  public PostDecrementValueNested<A> withNewPostDecrementValue() {
    return new PostDecrementValueNested(null);
  }

  public PostDecrementValueNested<A> withNewPostDecrementValueLike(PostDecrement item) {
    return new PostDecrementValueNested(item);
  }

  public PostIncrementValueNested<A> withNewPostIncrementValue() {
    return new PostIncrementValueNested(null);
  }

  public PostIncrementValueNested<A> withNewPostIncrementValueLike(PostIncrement item) {
    return new PostIncrementValueNested(item);
  }

  public PreDecrementValueNested<A> withNewPreDecrementValue() {
    return new PreDecrementValueNested(null);
  }

  public PreDecrementValueNested<A> withNewPreDecrementValueLike(PreDecrement item) {
    return new PreDecrementValueNested(item);
  }

  public PreIncrementValueNested<A> withNewPreIncrementValue() {
    return new PreIncrementValueNested(null);
  }

  public PreIncrementValueNested<A> withNewPreIncrementValueLike(PreIncrement item) {
    return new PreIncrementValueNested(item);
  }

  public PropertyRefValueNested<A> withNewPropertyRefValue() {
    return new PropertyRefValueNested(null);
  }

  public PropertyRefValueNested<A> withNewPropertyRefValueLike(PropertyRef item) {
    return new PropertyRefValueNested(item);
  }

  public PropertyValueNested<A> withNewPropertyValue() {
    return new PropertyValueNested(null);
  }

  public PropertyValueNested<A> withNewPropertyValueLike(Property item) {
    return new PropertyValueNested(item);
  }

  public RightShiftValueNested<A> withNewRightShiftValue() {
    return new RightShiftValueNested(null);
  }

  public A withNewRightShiftValue(Object left, Object right) {
    return (A) this.withValue(new RightShift(left, right));
  }

  public RightShiftValueNested<A> withNewRightShiftValueLike(RightShift item) {
    return new RightShiftValueNested(item);
  }

  public RightUnsignedShiftValueNested<A> withNewRightUnsignedShiftValue() {
    return new RightUnsignedShiftValueNested(null);
  }

  public A withNewRightUnsignedShiftValue(Object left, Object right) {
    return (A) this.withValue(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftValueNested<A> withNewRightUnsignedShiftValueLike(RightUnsignedShift item) {
    return new RightUnsignedShiftValueNested(item);
  }

  public SuperValueNested<A> withNewSuperValue() {
    return new SuperValueNested(null);
  }

  public SuperValueNested<A> withNewSuperValueLike(Super item) {
    return new SuperValueNested(item);
  }

  public TernaryValueNested<A> withNewTernaryValue() {
    return new TernaryValueNested(null);
  }

  public TernaryValueNested<A> withNewTernaryValueLike(Ternary item) {
    return new TernaryValueNested(item);
  }

  public ThisValueNested<A> withNewThisValue() {
    return new ThisValueNested(null);
  }

  public ThisValueNested<A> withNewThisValueLike(This item) {
    return new ThisValueNested(item);
  }

  public ValueRefValueNested<A> withNewValueRefValue() {
    return new ValueRefValueNested(null);
  }

  public A withNewValueRefValue(Object value) {
    return (A) this.withValue(new ValueRef(value));
  }

  public ValueRefValueNested<A> withNewValueRefValueLike(ValueRef item) {
    return new ValueRefValueNested(item);
  }

  public XorValueNested<A> withNewXorValue() {
    return new XorValueNested(null);
  }

  public A withNewXorValue(Object left, Object right) {
    return (A) this.withValue(new Xor(left, right));
  }

  public XorValueNested<A> withNewXorValueLike(Xor item) {
    return new XorValueNested(item);
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
      VisitableBuilder<? extends Expression, ?> b = builder(value.get());
      _visitables.get("value").add(b);
      this.value = Optional.of(b);
    }
    return (A) this;
  }

  public A withValue(Expression value) {
    if (value == null) {
      this.value = Optional.empty();
    } else {
      VisitableBuilder<? extends Expression, ?> b = builder(value);
      _visitables.get("value").add(b);
      this.value = Optional.of(b);
    }
    return (A) this;
  }

  public class AssignValueNested<N> extends AssignFluent<AssignValueNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignValueNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endAssignValue() {
      return and();
    }

  }

  public class BinaryExpressionValueNested<N> extends BinaryExpressionFluent<BinaryExpressionValueNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionValueNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endBinaryExpressionValue() {
      return and();
    }

  }

  public class BitwiseAndValueNested<N> extends BitwiseAndFluent<BitwiseAndValueNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndValueNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endBitwiseAndValue() {
      return and();
    }

  }

  public class BitwiseOrValueNested<N> extends BitwiseOrFluent<BitwiseOrValueNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrValueNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endBitwiseOrValue() {
      return and();
    }

  }

  public class CastValueNested<N> extends CastFluent<CastValueNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastValueNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endCastValue() {
      return and();
    }

  }

  public class ClassRefValueNested<N> extends ClassRefFluent<ClassRefValueNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefValueNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endClassRefValue() {
      return and();
    }

  }

  public class ConstructValueNested<N> extends ConstructFluent<ConstructValueNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructValueNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endConstructValue() {
      return and();
    }

  }

  public class ContextRefValueNested<N> extends ContextRefFluent<ContextRefValueNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefValueNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endContextRefValue() {
      return and();
    }

  }

  public class DeclareValueNested<N> extends DeclareFluent<DeclareValueNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareValueNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endDeclareValue() {
      return and();
    }

  }

  public class DivideValueNested<N> extends DivideFluent<DivideValueNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideValueNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endDivideValue() {
      return and();
    }

  }

  public class DotClassValueNested<N> extends DotClassFluent<DotClassValueNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassValueNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endDotClassValue() {
      return and();
    }

  }

  public class EmptyValueNested<N> extends EmptyFluent<EmptyValueNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyValueNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endEmptyValue() {
      return and();
    }

  }

  public class EnclosedValueNested<N> extends EnclosedFluent<EnclosedValueNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedValueNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endEnclosedValue() {
      return and();
    }

  }

  public class EqualsValueNested<N> extends EqualsFluent<EqualsValueNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsValueNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endEqualsValue() {
      return and();
    }

  }

  public class GreaterThanOrEqualValueNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualValueNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualValueNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endGreaterThanOrEqualValue() {
      return and();
    }

  }

  public class GreaterThanValueNested<N> extends GreaterThanFluent<GreaterThanValueNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanValueNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endGreaterThanValue() {
      return and();
    }

  }

  public class IndexValueNested<N> extends IndexFluent<IndexValueNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexValueNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endIndexValue() {
      return and();
    }

  }

  public class InstanceOfValueNested<N> extends InstanceOfFluent<InstanceOfValueNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfValueNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endInstanceOfValue() {
      return and();
    }

  }

  public class InverseValueNested<N> extends InverseFluent<InverseValueNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseValueNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endInverseValue() {
      return and();
    }

  }

  public class LambdaValueNested<N> extends LambdaFluent<LambdaValueNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaValueNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endLambdaValue() {
      return and();
    }

  }

  public class LeftShiftValueNested<N> extends LeftShiftFluent<LeftShiftValueNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftValueNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endLeftShiftValue() {
      return and();
    }

  }

  public class LessThanOrEqualValueNested<N> extends LessThanOrEqualFluent<LessThanOrEqualValueNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualValueNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endLessThanOrEqualValue() {
      return and();
    }

  }

  public class LessThanValueNested<N> extends LessThanFluent<LessThanValueNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanValueNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endLessThanValue() {
      return and();
    }

  }

  public class LogicalAndValueNested<N> extends LogicalAndFluent<LogicalAndValueNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndValueNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endLogicalAndValue() {
      return and();
    }

  }

  public class LogicalOrValueNested<N> extends LogicalOrFluent<LogicalOrValueNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrValueNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endLogicalOrValue() {
      return and();
    }

  }

  public class MethodCallValueNested<N> extends MethodCallFluent<MethodCallValueNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallValueNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endMethodCallValue() {
      return and();
    }

  }

  public class MinusValueNested<N> extends MinusFluent<MinusValueNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusValueNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endMinusValue() {
      return and();
    }

  }

  public class ModuloValueNested<N> extends ModuloFluent<ModuloValueNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloValueNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endModuloValue() {
      return and();
    }

  }

  public class MultiplyValueNested<N> extends MultiplyFluent<MultiplyValueNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyValueNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endMultiplyValue() {
      return and();
    }

  }

  public class NegativeValueNested<N> extends NegativeFluent<NegativeValueNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeValueNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endNegativeValue() {
      return and();
    }

  }

  public class NewArrayValueNested<N> extends NewArrayFluent<NewArrayValueNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayValueNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endNewArrayValue() {
      return and();
    }

  }

  public class NotEqualsValueNested<N> extends NotEqualsFluent<NotEqualsValueNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsValueNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endNotEqualsValue() {
      return and();
    }

  }

  public class NotValueNested<N> extends NotFluent<NotValueNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotValueNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endNotValue() {
      return and();
    }

  }

  public class PlusValueNested<N> extends PlusFluent<PlusValueNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusValueNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPlusValue() {
      return and();
    }

  }

  public class PositiveValueNested<N> extends PositiveFluent<PositiveValueNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveValueNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPositiveValue() {
      return and();
    }

  }

  public class PostDecrementValueNested<N> extends PostDecrementFluent<PostDecrementValueNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementValueNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPostDecrementValue() {
      return and();
    }

  }

  public class PostIncrementValueNested<N> extends PostIncrementFluent<PostIncrementValueNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementValueNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPostIncrementValue() {
      return and();
    }

  }

  public class PreDecrementValueNested<N> extends PreDecrementFluent<PreDecrementValueNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementValueNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPreDecrementValue() {
      return and();
    }

  }

  public class PreIncrementValueNested<N> extends PreIncrementFluent<PreIncrementValueNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementValueNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPreIncrementValue() {
      return and();
    }

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

  public class PropertyRefValueNested<N> extends PropertyRefFluent<PropertyRefValueNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefValueNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPropertyRefValue() {
      return and();
    }

  }

  public class PropertyValueNested<N> extends PropertyFluent<PropertyValueNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyValueNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endPropertyValue() {
      return and();
    }

  }

  public class RightShiftValueNested<N> extends RightShiftFluent<RightShiftValueNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftValueNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endRightShiftValue() {
      return and();
    }

  }

  public class RightUnsignedShiftValueNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftValueNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftValueNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endRightUnsignedShiftValue() {
      return and();
    }

  }

  public class SuperValueNested<N> extends SuperFluent<SuperValueNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperValueNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endSuperValue() {
      return and();
    }

  }

  public class TernaryValueNested<N> extends TernaryFluent<TernaryValueNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryValueNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endTernaryValue() {
      return and();
    }

  }

  public class ThisValueNested<N> extends ThisFluent<ThisValueNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisValueNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endThisValue() {
      return and();
    }

  }

  public class ValueRefValueNested<N> extends ValueRefFluent<ValueRefValueNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefValueNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endValueRefValue() {
      return and();
    }

  }

  public class XorValueNested<N> extends XorFluent<XorValueNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorValueNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) DeclareFluent.this.withValue(builder.build());
    }

    public N endXorValue() {
      return and();
    }

  }
}
